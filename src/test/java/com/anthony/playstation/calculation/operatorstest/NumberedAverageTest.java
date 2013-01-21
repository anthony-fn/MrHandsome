package com.anthony.playstation.calculation.operatorstest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.calculation.operators.NumberedAverage;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.InvalidOperationException;

public class NumberedAverageTest
{

	private static ADataIOProxyFactory m_sourceFactory = null;
	private static ADataIOProxy m_source = null;
	private static NumberedAverage m_avg = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		try
		{
			m_sourceFactory = new LocalFileProxyFactory("DataStorage", "DataStorage");
			m_source = m_sourceFactory.getDataProxy();
			m_avg = new NumberedAverage(5);
		} catch (DataProxyOperationException e)
		{
			fail(e.getMessage());
		} catch (InvalidOperationException e)
		{
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		try
		{
			m_sourceFactory.closeFactory();
		} catch (DataProxyOperationException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsValidData()
	{
		assertTrue(m_avg.isValidData(DataUnitType.ValueUnit));
		assertTrue(!m_avg.isValidData(DataUnitType.StringUnit));
	}

	@Test
	public void testOperate()
	{
		UniformType type = null;
		try
		{
			type = UniformTypeDB.getType(8);
			DataSeries series = m_source.loadData("0P000080EB", type, new ProtoBufAdapter()).get(0);
			assertNotNull(series);
			
			int sizea = series.getUnitList().size();
			DataSeries result = m_avg.operate(type, series);
			int sizeb = result.getUnitList().size();
			
			assertTrue((sizea-sizeb)==4);
			
		} catch (ConfigurationException e)
		{
			fail(e.getMessage());
		} catch (DataIOException e)
		{
			fail(e.getMessage());
		} catch (InvalidOperationException e)
		{
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testNumberedAverage()
	{
		assertNotNull(m_avg);
	}

	@Test
	public void testGetNum()
	{
		assertEquals(m_avg.getNum(), 5);
	}

	@Test
	public void testSetNum()
	{
		assertEquals(m_avg.getNum(), 5);
		m_avg.setNum(10);
		assertEquals(m_avg.getNum(), 10);
		
		m_avg.setNum(5);
	}

}
