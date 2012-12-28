package com.anthony.playstation.configurationTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.configuration.Exceptions.ConfigurationException;

public class ChinaEquityMarketTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@Test
	public void testChinaEquityMarketInit()
	{
		try
		{
			ChinaEquityMarket market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
			
			assertEquals( 2575, market.getEquityNumber());
			market.Finalize();
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception: "+e.getMessage());
		}		
	}
	
	@Test
	public void testChinaEquityMarket()
	{
		try
		{
			ChinaEquityMarket market = new ChinaEquityMarket("ChinaEquity.data");
			
			assertEquals( 120, market.getEquityNumber());
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Not yet implemented");
		}		
	}
}
