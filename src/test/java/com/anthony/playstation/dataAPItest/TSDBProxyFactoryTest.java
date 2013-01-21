package com.anthony.playstation.dataAPItest;

import static org.junit.Assert.*;

import mstar.production.common.ConfigManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.exceptions.DataProxyOperationException;

public class TSDBProxyFactoryTest
{
	private static ADataIOProxyFactory m_factory = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		try {
			m_factory = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		try {
			m_factory.closeFactory();
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetDataProxy()
	{
		try {
			ADataIOProxy proxy = m_factory.getDataProxy();
			assertNotNull(proxy);
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCloseFactory()
	{
		/*ADataIOProxyFactory factory = null;
		try {
			 factory = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
			 
			 assertNotNull(factory);
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
		
		try {
			factory.closeFactory();
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
		@SuppressWarnings("unused")
		ADataIOProxy proxy = null;
		try {
			 proxy = factory.getDataProxy();
			fail("Should not be able to create instance of ADataIOProxy.");
		} catch (DataProxyOperationException e) {
		}*/
	}

	@Test
	public void testTSDBProxyFactory()
	{
		assertNotNull(m_factory);
	}

}
