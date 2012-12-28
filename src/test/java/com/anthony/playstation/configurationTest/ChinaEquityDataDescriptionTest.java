package com.anthony.playstation.configurationTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.configuration.ChinaEquityDataDescription;
import com.anthony.playstation.configuration.Exceptions.ConfigurationException;

public class ChinaEquityDataDescriptionTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@Test
	public void testChinaEquityDataDescription()
	{
		try
		{
			ChinaEquityDataDescription description = new ChinaEquityDataDescription("DataDescriptions/TSDB/ChinaEquity.tsdbtypes");
			assertEquals( 51, description.getDataTypeNumber());
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception: "+e.getMessage());
		}
	}

	@Test
	public void testReset()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetEquityNumber()
	{
		fail("Not yet implemented");
	}

}
