package com.anthony.playstation.dataunittest;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.ConfigurationException;

public class UniformTypeDBTest
{
	
	private static final Logger logger = Logger.getLogger(UniformTypeDBTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		logger.info("*****Start to test class UniformTypeTest*****");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		logger.info("*****Finished testing class UniformTypeTest*****");
	}

	@Test
	public void testGetType()
	{
		try
		{
			UniformType type1 = UniformTypeDB.getType(1);
			
			assertEquals(type1.getTypeID(), 1);
			assertEquals(type1.getTypeName(), "OpenMarketPrice");
			
			DataUnitType valueType = type1.getValueType();
			assertEquals( valueType, DataUnitType.ValueUnit);
			
			assertTrue(!(type1.equals(UniformTypeDB.getType(2))));
			
		} catch (ConfigurationException e)
		{
			fail(e.getMessage());
		}
	}

}
