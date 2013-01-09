package com.anthony.playstation.configurationtest;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.configuration.ChinaEquityDataDescription;
import com.anthony.playstation.exceptions.ConfigurationException;

public class ChinaEquityDataDescriptionTest {

	private static final Logger logger = Logger.getLogger(ChinaEquityDataDescriptionTest.class);
	private static ChinaEquityDataDescription m_description = null;
	private static int m_success = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("*****Start to test class ChinaEquityDataDescriptionTest*****");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info(m_success + " out of 3 succeed !");
		logger.info("*****Finished testing class ChinaEquityDataDescriptionTest*****");
	}

	@Test
	public void testChinaEquityDataDescription() {	
		try {
			m_description = new ChinaEquityDataDescription("DataDescriptions/TSDB/ChinaEquity.tsdbtypes");
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
		assertNotNull(m_description);
		m_success++;
	}

	@Test
	public void testGetDataTypeNumber() {
		assertEquals(1, m_description.getDataTypeNumber());
		m_success++;
	}
	
	@Test
	public void testReset() {
		m_description.Reset();
		assertNotNull(m_description);
		assertEquals(0, m_description.getDataTypeNumber());
		m_success++;
	}
}
