package com.anthony.playstation.functionset;

import mstar.production.common.ConfigManager;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.configuration.ChinaEquity;
import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.JobBatchException;
import com.anthony.playstation.exceptions.JobOperationException;
import com.anthony.playstation.executor.AJobBatch;
import com.anthony.playstation.executor.AJobFactory;
import com.anthony.playstation.executor.DataDumpJobFactory;
import com.anthony.playstation.executor.FixedJobBatch;
import com.anthony.playstation.executor.LocalExecutor;

public class DataDump
{

	private static final Logger logger = Logger.getLogger(DataDump.class);
	
	private static ADataIOProxyFactory m_source = null;
	private static ADataIOProxyFactory m_target = null;
	private static LocalExecutor m_executor = null;
	private static ChinaEquityMarket m_market = null;
	private static AJobFactory m_dumpFactory = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("*****Start to test class DataDump*****");
		
		try
		{
			m_source = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
			m_target = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			
			m_executor = new LocalExecutor(Integer.parseInt(ConfigManager.getInstance().getString("LocalThreadNum")));
			
			m_market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
			
			m_dumpFactory = new DataDumpJobFactory(m_source, m_target);
		} catch (DataProxyOperationException e)
		{
			logger.info(e.getMessage());
			e.printStackTrace();
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try
		{
			m_source.closeFactory();
			m_target.closeFactory();
			m_executor.dispose();
		} catch (DataProxyOperationException e)
		{
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		logger.info("*****Finished testing class DataDump*****");
	}
	
	@Test
	public void dumpDivendendPerShare()
	{
		int totalNum = m_market.getEquityNumber();
		int index = 0;
		AJobBatch jobBatch = null;
		try
		{
			jobBatch = new FixedJobBatch(totalNum);
			for( ChinaEquity equity : m_market.getMemberList() )
			{
				MappingInfo mappingBase = new MappingInfo();
				mappingBase.setObjectId(equity.getPerformanceID());
				mappingBase.setTsType(712);
				mappingBase.setMapping(MappingType.MappingBaseObject);
				
				jobBatch.pushOneJob(m_dumpFactory.getOneJob(mappingBase));
				
				index ++;
				logger.info("Job added to batch "+index+"/"+totalNum);
			}
			
			m_executor.submit(jobBatch);
		} catch (JobBatchException e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (JobOperationException e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		while( ! jobBatch.isFinished() )
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
		
		System.out.println("Failed: "+((FixedJobBatch)jobBatch).checkForFailed());
	}
	
	@Test
	public void dumpPrice()
	{
		int totalNum = m_market.getEquityNumber();
		int index = 0;
		AJobBatch jobBatch = null;
		try
		{
			jobBatch = new FixedJobBatch(totalNum*2);
			for( ChinaEquity equity : m_market.getMemberList() )
			{
				MappingInfo mappingBase = new MappingInfo();
				mappingBase.setObjectId(equity.getPerformanceID());
				mappingBase.setTsType(2);
				mappingBase.setMapping(MappingType.MappingBaseObject);
				
				jobBatch.pushOneJob(m_dumpFactory.getOneJob(mappingBase));
				
				MappingInfo mappingcor = new MappingInfo();
				mappingcor.setObjectId(equity.getPerformanceID());
				mappingcor.setTsType(2);
				mappingcor.setMapping(MappingType.MappingCorporateActionAdjustment);
				
				jobBatch.pushOneJob(m_dumpFactory.getOneJob(mappingcor));
				
				index ++;
				logger.info("Job added to batch "+index+"/"+totalNum*2);
			}
			
			m_executor.submit(jobBatch);
		} catch (JobBatchException e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (JobOperationException e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		while( ! jobBatch.isFinished() )
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
		
	}
	@Test
	public void test()
	{}
}
