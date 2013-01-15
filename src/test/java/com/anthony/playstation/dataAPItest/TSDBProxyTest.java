package com.anthony.playstation.dataAPItest;

import static org.junit.Assert.*;

import java.util.List;

import mstar.production.common.ConfigManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;

public class TSDBProxyTest
{
	private static TSDBProxyFactory m_factory = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		try
		{
			m_factory = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"), "");
		} catch (DataProxyOperationException e)
		{
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		try
		{
			m_factory.closeFactory();
		} catch (DataProxyOperationException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoadData()
	{
		try
		{
			ADataIOProxy proxy = m_factory.getDataProxy();
			
			assertNotNull(proxy);
			
			MappingInfo mapping = new MappingInfo();
			mapping.setObjectId("0P000080EB");
			mapping.setMapping(MappingType.MappingBaseObject);
			mapping.setTsType(2);
			List<DataSeries> result = proxy.loadData(mapping.getObjectId(), mapping, new TSDBDataAdapter());
			
			assertNotNull(result);
			assertEquals(result.size(), 4);
		} catch (DataProxyOperationException e)
		{
			fail(e.getMessage());
		} catch (DataIOException e)
		{
			fail(e.getMessage());
		}
	}

	@Test 
	public void testSaveData()
	{
		try
		{
			ADataIOProxy proxy = m_factory.getDataProxy();
			try
			{
				proxy.saveData(null, null);
				fail("Should not have the save function for TSDBDataProxy!");
			} catch (DataIOException e)
			{
			}
		} catch (DataProxyOperationException e)
		{
			fail(e.getMessage());
		}
	}
}
