/**
 * 
 */
package com.anthony.playstation.dataunittest;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

/**
 * @author afan
 *
 */
public class DataSeriesTest
{

	private static final Logger logger = Logger.getLogger(DataSeriesTest.class);
	private static DataSeries m_series = null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		logger.info("*****Start to test class DataSeriesTest*****");
		
		UniformType type = new UniformType(9, "DivendendPerShareOverOneYear", DataUnitType.ValueUnit);
		m_series = new DataSeries(type, "MrHandSome");
		
		try
		{
			ADataUnit unit1 = new ValueDataUnit("1900-01-01", 1);
			ADataUnit unit2 = new ValueDataUnit("1900-01-02", 1);
			ADataUnit unit3 = new ValueDataUnit("1900-01-03", 1);
			ADataUnit unit4 = new ValueDataUnit("1900-01-04", 1);
			ADataUnit unit5 = new ValueDataUnit("1900-01-05", 1);
			m_series.addUnit(unit1);
			m_series.addUnit(unit2);
			m_series.addUnit(unit3);
			m_series.addUnit(unit4);
			m_series.addUnit(unit5);
		} catch (InvalidDataUnitException e)
		{
			assertTrue(false);
		}
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		
		logger.info("*****Finished testing class DataSeriesTest*****");
	}

	/**
	 * Test method for {@link com.anthony.playstation.data.dataseries.DataSeries#DataSeries(com.anthony.playstation.data.dataseries.UniformType, java.lang.String)}.
	 */
	@Test
	public void testDataSeries()
	{
		assertNotNull(m_series);
		assertEquals( 5, m_series.getSeriesSize());
	}

	/**
	 * Test method for {@link com.anthony.playstation.data.dataseries.DataSeries#isEqual(com.anthony.playstation.data.dataseries.DataSeries)}.
	 */
	@Test
	public void testIsEqual()
	{
		UniformType type = new UniformType(9, "DivendendPerShareOverOneYear", DataUnitType.ValueUnit);
		DataSeries m_series2 = new DataSeries(type, "MrHandSome");
		
		try
		{
			ADataUnit unit1 = new ValueDataUnit("1900-01-01", 1);
			ADataUnit unit2 = new ValueDataUnit("1900-01-02", 1);
			ADataUnit unit3 = new ValueDataUnit("1900-01-03", 1);
			ADataUnit unit4 = new ValueDataUnit("1900-01-04", 1);
			ADataUnit unit5 = new ValueDataUnit("1900-01-05", 1);
			m_series2.addUnit(unit1);
			m_series2.addUnit(unit2);
			m_series2.addUnit(unit3);
			m_series2.addUnit(unit4);
			m_series2.addUnit(unit5);
		} catch (InvalidDataUnitException e)
		{
			assertTrue(false);
		}
		
		try
		{
			assertTrue(m_series.equals(m_series2));
			
			
			UniformType type2 = new UniformType(1, "abc", DataUnitType.DefaultUnit);
			m_series2.setUniType(type2);
			assertTrue( !m_series.equals(m_series2));
			
			
			m_series2.setUniType(m_series.getUniType());
			
			ADataUnit unit6 = new ValueDataUnit("1901-01-01", 2);
			
			m_series2.addUnit(unit6);
			assertTrue( !m_series.equals(m_series2));
		} catch (InvalidDataUnitException e)
		{
			fail();
		}
	}

	
	/**
	 * Test method for {@link com.anthony.playstation.data.dataseries.DataSeries#getFileName()}.
	 */
	@Test
	public void testGetFileName()
	{
		assertTrue(m_series.getFileName().equals("MrHandSome_DivendendPerShareOverOneYear"));
	}

	/**
	 * Test method for {@link com.anthony.playstation.data.dataseries.DataSeries#addUnit(com.anthony.playstation.data.ADataUnit)}.
	 */
	@Test
	public void testAddUnit()
	{
		assertTrue(m_series.getSeriesSize() == 5);
		
		ADataUnit unit6;
		try
		{
			unit6 = new ValueDataUnit("1901-01-01", 2);
			m_series.addUnit(unit6);
		} catch (InvalidDataUnitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue( m_series.getSeriesSize() == 6);
	}

	/**
	 * Test method for {@link com.anthony.playstation.data.dataseries.DataSeries#getValueType()}.
	 */
	@Test
	public void testGetValueType()
	{
		assertEquals( m_series.getValueType(), DataUnitType.ValueUnit);
	}

	/**
	 * Test method for {@link com.anthony.playstation.data.dataseries.DataSeries#getUniType()}.
	 */
	@Test
	public void testGetUniType()
	{
		UniformType type = new UniformType(9, "DivendendPerShareOverOneYear", DataUnitType.ValueUnit);
		
		assertTrue( type.equals(m_series.getUniType()));
	}

}
