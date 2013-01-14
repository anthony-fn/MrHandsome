package com.anthony.playstation.dataAPItest;

import static org.junit.Assert.*;

import java.util.List;

import mstar.production.common.ConfigManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxy;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class LocalFileProxyTest {
	
	private static UniformType m_valueType = null;
	private static DataSeries m_valueSeries = null;
	private static ADataIOProxyFactory m_factory = null;
	private static ADataIOProxy m_proxy = null;
	
	private static ProtoBufAdapter m_adapter = new ProtoBufAdapter();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			m_factory = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			m_proxy = m_factory.getDataProxy();
			
			m_valueType = UniformTypeDB.getType(10000);
			m_valueSeries = new DataSeries(m_valueType, "ValueTest");
			
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-01", 12));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-02", 13));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-03", 14));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-04", 15));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-05", 16));
			
		} catch (InvalidDataUnitException e) {
			fail(e.getMessage());
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		try {
			m_factory.closeFactory();
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoadData() {
		try {
			List<DataSeries> result = m_proxy.loadData("ValueTest", m_valueType, m_adapter);
			
			assertNotNull( result );
			assertTrue( result.size() == 1);
			assertTrue( result.get(0).equals(m_valueSeries));
		} catch (DataIOException e) {
			fail(e.getMessage());
		} catch (InvalidDataUnitException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSaveData() {
		try {
			int result = m_proxy.saveData(m_adapter, m_valueSeries);
			
			assertEquals( result, 0);
		} catch (DataIOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetSetSourceDirectory() {
		assertTrue(((LocalFileProxy)m_proxy).getSourceDirectory().equalsIgnoreCase(ConfigManager.getInstance().getString("LocalFile_Source")));
		
		((LocalFileProxy)m_proxy).setSourceDirectory("abc");
		assertTrue(((LocalFileProxy)m_proxy).getSourceDirectory().equalsIgnoreCase("abc"));
		
		((LocalFileProxy)m_proxy).setSourceDirectory(ConfigManager.getInstance().getString("LocalFile_Source"));
	}

	@Test
	public void testGetSetTargetDirectory() {
		assertTrue(((LocalFileProxy)m_proxy).getTargetDirectory().equalsIgnoreCase(ConfigManager.getInstance().getString("LocalFile_Target")));
		
		((LocalFileProxy)m_proxy).setTargetDirectory("abc");
		assertTrue(((LocalFileProxy)m_proxy).getTargetDirectory().equalsIgnoreCase("abc"));
		
		((LocalFileProxy)m_proxy).setTargetDirectory(ConfigManager.getInstance().getString("LocalFile_Target"));
	}

}
