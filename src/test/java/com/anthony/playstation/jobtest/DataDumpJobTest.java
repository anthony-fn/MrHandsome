package com.anthony.playstation.jobtest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import mstar.production.common.ConfigManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
import com.anthony.playstation.executor.AJob;
import com.anthony.playstation.executor.AJobBatch;
import com.anthony.playstation.executor.AJobFactory;
import com.anthony.playstation.executor.DataDumpJobFactory;
import com.anthony.playstation.executor.JobStatus;
import com.anthony.playstation.executor.LinkedJobBatch;
import com.anthony.playstation.executor.LocalExecutor;

public class DataDumpJobTest
{
	private static LocalExecutor m_executor = null; 
	private static ADataIOProxyFactory m_source = null;
	private static ADataIOProxyFactory m_target = null;
	private static ChinaEquityMarket m_market = null;
	private static AJobFactory m_dumpFactory = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
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
			e.printStackTrace();
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		try
		{
			m_source.closeFactory();
			m_target.closeFactory();
			m_executor.dispose();
		} catch (DataProxyOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Test
	public void testSeveralJobs()
	{
		String IDs = "0P0000TV9W;0P0000VO30;0P00007WV8;0P0000WADV;0P0000WC24;0P0000T1H4;0P0000USO7;0P0000OOQT;0P0000TE6T;0P0000X65J;0P0000RV28;0P00007XSD;0P0000SDYC;0P0000VUYP;0P00007XPJ;0P0000WTIL;0P0000US6L;0P0000WTAQ;0P0000TE6H;0P0000TOKS;0P0000OPU5;0P0000UP8Z;0P0000TQ4U;0P0000U4OG;0P0000VRLI;0P0000TYKV;0P0000WC26;0P0000VYLN;0P0000VVOU";
		String [] IDArray = IDs.split(";");
		List<MappingInfo> mappings = new LinkedList<MappingInfo>();
		
		for( int i = 0; i < IDArray.length; i ++ )
		{
			MappingInfo map = new MappingInfo();
			map.setObjectId(IDArray[i]);
			map.setTsType(712);
			map.setMapping(MappingType.MappingBaseObject);
			
			mappings.add(map);
		}
		
		AJobBatch batch = new LinkedJobBatch();
		for( MappingInfo map : mappings )
		{
			AJob job;
			try
			{
				job = m_dumpFactory.getOneJob(map);
				batch.pushOneJob(job);
			} catch (JobOperationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobBatchException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try
		{
			m_executor.submit(batch);
			
			while( !batch.isFinished() )
			{
				Thread.sleep(1000);
			}
		} catch (JobBatchException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testOneJob()
	{
		MappingInfo mapping = new MappingInfo();
		mapping.setObjectId("0P0000U06Q");
		mapping.setTsType(712);
		mapping.setMapping(MappingType.MappingBaseObject);
		try
		{
			AJob job = m_dumpFactory.getOneJob(mapping);
			AJobBatch batch = new LinkedJobBatch();
			batch.pushOneJob(job);
			m_executor.submit(batch);
			
			while( job.getStatus() != JobStatus.Succeed )
			{
				Thread.sleep(1000);
			}
		} catch (JobOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobBatchException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testIsFailed()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testHandleResult()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDataDumpJobADataIOProxyADataIOProxyObject()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDataDumpJobADataIOProxyADataIOProxyObjectADataAdapterADataAdapter()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMapping()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testCall()
	{
		fail("Not yet implemented");
	}

}
