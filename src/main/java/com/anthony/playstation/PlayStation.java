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
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxy;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter;
import com.anthony.playstation.dataAdapter.TSDB.TSDBToUniformDB;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
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
			ADataIOProxyFactory tsdb = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
			ADataIOProxyFactory local = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			
			LocalExecutor executor = new LocalExecutor(Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadNum")),
					Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadMaxWait")));
			
			AJobFactory dumpFactory = new DataDumpJobFactory(tsdb, local);
			
			int totalNum = list.size();
			int index = 0;
			AJobBatch jobBatch = new FixedJobBatch(totalNum*3);
			for( ChinaEquity equity : list )
			{
				++index;
				MappingInfo mappingObj = new MappingInfo();
				
				mappingObj.setObjectId(equity.getPerformanceID());
				mappingObj.setTsType(2);
				
				mappingObj.setMapping(MappingType.MappingBaseObject);
				jobBatch.pushOneJob(dumpFactory.getOneJob(mappingObj));
				
				
				MappingInfo mappingCor = new MappingInfo();
				
				mappingCor.setObjectId(equity.getPerformanceID());
				mappingCor.setTsType(2);
				
				mappingCor.setMapping(MappingType.MappingCorporateActionAdjustment);
				jobBatch.pushOneJob(dumpFactory.getOneJob(mappingCor));
				
				MappingInfo mappingDiv = new MappingInfo();
				mappingDiv.setObjectId(equity.getPerformanceID());
				mappingDiv.setTsType(712);
				mappingDiv.setMapping(MappingType.MappingBaseObject);
				jobBatch.pushOneJob(dumpFactory.getOneJob(mappingDiv));
				
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
	
	public static void abc ( )
	{
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
			float abc = 123;
			Object test = abc;
			String test2 = "123";
			
			System.out.println(test2.equals((String)test));
			ChinaEquityDataDescription desc = new ChinaEquityDataDescription("DataDescriptions/TSDB/ChinaEquity.tsdbtypes");
			ChinaEquityMarket market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
			DumpDataFromTSDBToLocal(market.getMemberList());
			
			/*ADataIOProxyFactory local = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			ADataIOProxy proxy = local.getDataProxy();
		
			MappingInfo mapping = new MappingInfo();
			mapping.setObjectId("0P0000MO7M");
			mapping.setTsType(2);
			mapping.setMapping(MappingType.MappingCorporateActionAdjustment);
			
			byte[] result = proxy.loadData(mapping);
			TSDBDataAdapter adapter = new TSDBDataAdapter();
			ProtoBufAdapter proAdapter = new ProtoBufAdapter();
			List<DataSeries> data = adapter.loadSeries(mapping,
					result);
			
			DataSeries temp1 = data.get(1);
			//////////////////////////////
			
			/*for( DataSeries series : data )
			{
				//proxy.saveUniformedData(series);
				((LocalFileProxy)proxy).saveUniformData( proAdapter, series);
				
			}
			//DataSeries temp = proxy.loadUniformdData("0P0000MO7M", UniformTypeDB.getType(6));
			
			
			DataSeries temp = ((LocalFileProxy)proxy).loadUniformdDataFromProto(mapping.getObjectId(), UniformTypeDB.getType(6), proAdapter);
			//temp.print();
			
			if( temp.compare(temp1) )
				logger.info("Data matched !");
			else
			{
				logger.info("Doesn't match !");
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
