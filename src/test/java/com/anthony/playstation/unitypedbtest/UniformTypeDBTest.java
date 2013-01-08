package com.anthony.playstation.unitypedbtest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.ConfigurationException;

public class UniformTypeDBTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@Test
	public void testLoad()
	{
		try
		{
			//UniformTypeDB.load("DataDefination/UniformDataTypes.xml");
			UniformType type = UniformTypeDB.getType(1);
			assertEquals(type.getTypeID(),1);
			assertEquals(type.getTypeName(), "OpenMarketPrice");
			
			//assertEquals(type.getValueType(), DataUnitType.ValueUnit);
			
			
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetType() throws ConfigurationException
	{
		/*try
		{
			UniformTypeDB.load("DataDefination/UniformDataTypes.xml");
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		UniformType testType = new UniformType(1, "OpenMarketPrice", DataUnitType.ValueUnit);
		assertEquals(testType.equals(UniformTypeDB.getType(1)),true );
	}

}
