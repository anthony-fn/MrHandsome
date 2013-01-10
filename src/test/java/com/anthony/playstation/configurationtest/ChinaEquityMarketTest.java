package com.anthony.playstation.configurationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.configuration.ChinaEquity;
import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.exceptions.ConfigurationException;

public class ChinaEquityMarketTest {

	private static final Logger logger = Logger.getLogger(ChinaEquityMarketTest.class);
	private static ChinaEquityMarket m_market = null;
	private static int m_success = 0;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("*****Start to test class ChinaEquityMarketTest*****");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info(m_success + " out of 4 succeed !");
		logger.info("*****Finished testing class ChinaEquityMarketTest*****");
	}

	
	public void testChinaEquityMarket() {
		try {
			m_market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
		assertNotNull(m_market);
		m_success ++;
	}

	
	public void testGetMemberList() {
		List<ChinaEquity> list = m_market.getMemberList();
		
		assertEquals( list.size(), m_market.getEquityNumber());
		m_success ++;
	}

	
	public void testGetEquityNumber() {
		assertEquals(2575, m_market.getEquityNumber());
		m_success++;
	}
	
	
	public void testReset() {
		m_market.Reset();
		assertNotNull(m_market);
		assertEquals(0, m_market.getEquityNumber());
		m_success++;
	}
	
	@Test
	public void test()
	{
		ChinaEquityMarketTest test = new ChinaEquityMarketTest();
		
		test.testChinaEquityMarket();
		test.testGetMemberList();
		test.testGetEquityNumber();
		test.testReset();
	}
	
}
