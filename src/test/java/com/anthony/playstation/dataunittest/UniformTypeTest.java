package com.anthony.playstation.dataunittest;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.ConfigurationException;

public class UniformTypeTest
{

	private static final Logger logger = Logger.getLogger(UniformTypeTest.class);
	private static UniformType m_type1 = new UniformType( 1, "MrHandsome", DataUnitType.DefaultUnit);
	private static UniformType m_type2 = null; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		logger.info("*****Start to test class UniformTypeTest*****");
		try
		{
			m_type2 = new UniformType( 1, "MrHandsome", "DefaultUnit");
		} catch (ConfigurationException e)
		{
			fail();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		logger.info("*****Finished testing class DataSeriesTest*****");
	}

	@Test
	public void testUniformTypeIntStringDataUnitType()
	{
		assertNotNull(m_type1);
	}

	@Test
	public void testUniformTypeIntStringString()
	{
		assertNotNull(m_type2);
	}

	@Test
	public void testEqualsUniformType()
	{
		assertTrue(m_type1.equals(m_type2));
	}

	@Test
	public void testGetTypeID()
	{
		assertEquals(1, m_type1.getTypeID());
	}

	@Test
	public void testSetTypeID()
	{
		UniformType temp = new UniformType( 1, "MrHandsome", DataUnitType.DefaultUnit);
		temp.setTypeID(2);
		
		assertEquals(2, temp.getTypeID());
	}

	@Test
	public void testGetTypeName()
	{
		assertTrue(m_type1.getTypeName().equals("MrHandsome"));
	}

	@Test
	public void testSetTypeName()
	{
		UniformType temp = new UniformType( 1, "MrHandsome", DataUnitType.DefaultUnit);
		
		temp.setTypeName("BradPitt");
		
		assertTrue(temp.getTypeName().equals("BradPitt"));
	}

	@Test
	public void testGetValueType()
	{		
		assertEquals( m_type1.getValueType(), DataUnitType.DefaultUnit);
	}

	@Test
	public void testSetValueTypeDataUnitType()
	{
		UniformType temp = new UniformType( 1, "MrHandsome", DataUnitType.DefaultUnit);
		
		temp.setValueType(DataUnitType.StringUnit);
		assertEquals(temp.getValueType(), DataUnitType.StringUnit);
	}

	@Test
	public void testSetValueTypeString()
	{
		UniformType temp = new UniformType( 1, "MrHandsome", DataUnitType.DefaultUnit);
		
		try
		{
			temp.setValueType("abc");
			fail();
		} catch (ConfigurationException e)
		{
		}
		
		try
		{
			temp.setValueType("StringUnit");
			assertEquals( temp.getValueType(), DataUnitType.StringUnit);
		} catch (ConfigurationException e)
		{
			fail();
		}
	}

}
