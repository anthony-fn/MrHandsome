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
import com.anthony.playstation.data.DataSeries;
import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxy;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter;
import com.anthony.playstation.dataDump.DataDumper;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.DataDumpException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;
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
			//ADataIOProxyFactory tsdb = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
			//		ConfigManager.getInstance().getString("TSDB_Target"));
			ADataIOProxyFactory local = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			
			LocalExecutor executor = new LocalExecutor(Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadNum")),
					Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadMaxWait")));
			
			AJobFactory dumpFactory = new DataDumpJobFactory(local, local);
			
			int totalNum = list.size();
			int index = 0;
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
			
			
			//tsdb.closeFactory();
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
			TSDBToUniformDB.load("DataDefination/TSDBToUniform.xml");
			UniformTypeDB.load("DataDefination/UniformDataTypes.xml");
			ChinaEquityMarket market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
			//ChinaEquityDataDescription desc = new ChinaEquityDataDescription("DataDescriptions/TSDB/ChinaEquity.tsdbtypes");
			DumpDataFromTSDBToLocal(market.getMemberList());
			
			/*ADataIOProxyFactory local = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			ADataIOProxy proxy = local.getDataProxy();
			
			TSDBToUniformDB.load("DataDefination/TSDBToUniform.xml");
			UniformTypeDB.load("DataDefination/UniformDataTypes.xml");
			MappingInfo mapping = new MappingInfo();
			mapping.setObjectId("0P0000MO7M");
			mapping.setTsType(2);
			mapping.setMapping(MappingType.MappingCorporateActionAdjustment);
			
			byte[] result = proxy.loadData(mapping);
			//TSDBDataAdapter adapter = new TSDBDataAdapter();
			
			List<DataSeries> data = TSDBDataAdapter.loadSeries(TSDBToUniformDB.getUniformType(2, mapping.getMapping()), mapping,
					result);
			DataSeries temp1 = data.get(1);
			//////////////////////////////
			
			for( DataSeries series : data )
			{
				proxy.saveUniformedData(series);
			}
			DataSeries temp = proxy.loadUniformdData("0P0000MO7M", UniformTypeDB.getType(6));
			//temp.print();
			
			if( temp.compare(temp1) )
				logger.info("Data matched !");
			else
			{
				logger.info("Doesn't match !");
			}
			if( (market.getEquityNumber() <= 0) || (desc.getDataTypeNumber() <= 0) )
			{
				logger.info("No member to deal with!");
				return;
			}
			*/
			//PlayStation.DumpDataFromTSDBToLocal(market.getMemberList());
			logger.info("Finished!");
			
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataProxyOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataDumpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobBatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
