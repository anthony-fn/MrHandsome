package com.anthony.playstation.unitypedbtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAdapter.TSDB.TSDBToUniform;
import com.anthony.playstation.dataAdapter.TSDB.TSDBToUniformDB;
import com.anthony.playstation.exceptions.ConfigurationException;

public class TSDBToUniformDBTest
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
			//TSDBToUniformDB.load("DataDefination/TSDBToUniform.xml");
			
			TSDBToUniform uni = TSDBToUniformDB.getUniformType(2, MappingType.MappingCorporateActionAdjustment);
			
			assertEquals(uni.getMappingType(), MappingType.MappingCorporateActionAdjustment);
			assertEquals(uni.getName(),"tsMarketPrice");
			List<Integer> mylist = uni.getUniformList();
			
			assertEquals(4, mylist.size());
			assertEquals(5, mylist.get(0).intValue());
			assertEquals(6, mylist.get(1).intValue());
			assertEquals(7, mylist.get(2).intValue());
			assertEquals(8, mylist.get(3).intValue());
			
			
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
