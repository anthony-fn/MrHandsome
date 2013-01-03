/*
 * 
 */
package com.anthony.playstation;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//import mstar.production.common.*;

import mstar.production.common.ConfigManager;

import org.apache.log4j.Logger;

import com.anthony.playstation.configuration.ChinaEquity;
import com.anthony.playstation.configuration.ChinaEquityDataDescription;
import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxy;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.dataDump.DataDumper;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataDumpException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.JobBatchException;
import com.anthony.playstation.exceptions.JobOperationException;
//import mstar.production.common.*;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.executor.AJobBatch;
import com.anthony.playstation.executor.AJobFactory;
import com.anthony.playstation.executor.DataDumpJobFactory;
import com.anthony.playstation.executor.FixedJobBatch;
import com.anthony.playstation.executor.LocalExecutor;


// TODO: Auto-generated Javadoc
/**
 * The Class PlayStation.
 */
public class PlayStation
{
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(PlayStation.class);
	//private static DataDumpForTSDB m_dumper = new DataDumpForTSDB();

	public static void DumpDataFromTSDBToLocal( List<ChinaEquity> list ) throws DataProxyOperationException, DataDumpException, JobBatchException, JobOperationException
	{
			ADataIOProxyFactory tsdb = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
			ADataIOProxyFactory local = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			
			LocalExecutor executor = new LocalExecutor(Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadNum")),
					Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadMaxWait")));
			
			AJobFactory dumpFactory = new DataDumpJobFactory(tsdb, local);
			
			int totalNum = list.size();
			int index = 0;
			int error = 0;
			AJobBatch jobBatch = new FixedJobBatch(totalNum*2);
			for( ChinaEquity equity : list )
			{
				++index;
				MappingInfo mappingObj = new MappingInfo();
				
				mappingObj.setObjectId(equity.getPerformanceID());
				mappingObj.setTsType(2);
				
				mappingObj.setMapping(MappingType.MappingBaseObject);
				dumpFactory.LoadFactory(mappingObj);
				jobBatch.pushOneJob(dumpFactory.getOneJob());
				
				
				MappingInfo mappingCor = new MappingInfo();
				
				mappingCor.setObjectId(equity.getPerformanceID());
				mappingCor.setTsType(2);
				
				mappingCor.setMapping(MappingType.MappingCorporateActionAdjustment);
				dumpFactory.LoadFactory(mappingCor);
				jobBatch.pushOneJob(dumpFactory.getOneJob());
				logger.info("Data dumped "+index+"/"+totalNum);
				
			}

			executor.submit(jobBatch);
			
			while( !jobBatch.isFinished() )
			{
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info(jobBatch.getRemaining()+"/"+jobBatch.getJobNum());
			}
			
			
			tsdb.closeFactory();
			local.closeFactory();
			executor.dispose();
	}
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		logger.info("This is the start of a beautiful journey!");
		
		try
		{
			ChinaEquityMarket market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
			ChinaEquityDataDescription desc = new ChinaEquityDataDescription("DataDescriptions/TSDB/ChinaEquity.tsdbtypes");
			
			if( (market.getEquityNumber() <= 0) || (desc.getDataTypeNumber() <= 0) )
			{
				logger.info("No member to deal with!");
				return;
			}
			
			PlayStation.DumpDataFromTSDBToLocal(market.getMemberList());
			logger.info("Finished!");
			
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataProxyOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataDumpException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobBatchException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
