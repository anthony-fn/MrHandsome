/**
 * Test case for class ChinaEquity and ASecurity
 */
package com.anthony.playstation.configurationtest;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.configuration.ChinaEquity;

/**
 * @author Anthony
 *
 */
public class ChinaEquityTest {

	private static final Logger logger = Logger.getLogger(ChinaEquityTest.class);
	private static ChinaEquity m_equity = null;
	private static int m_success = 0;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("*****Start to test class ChinaEquity*****");
		
		m_equity = new ChinaEquity("Mr", "Hand", "Some");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info(m_success + " out of 5 succeed !");
		logger.info("*****Finished testing class ChinaEquity*****");
	}

	/**
	 * Test method for {@link com.anthony.playstation.configuration.ChinaEquity#getPerformanceID()}.
	 */
	@Test
	public void testGetPerformanceID() {
		assertEquals("Some",m_equity.getPerformanceID());
		m_success++;
	}

	/**
	 * Test method for {@link com.anthony.playstation.configuration.ASecurity#getSecurityname()}.
	 */
	@Test
	public void testGetSecurityname() {
		assertEquals("Mr", m_equity.getSecurityname());
		m_success++;
	}

	/**
	 * Test method for {@link com.anthony.playstation.configuration.ASecurity#setSecurityname(java.lang.String)}.
	 */
	@Test
	public void testSetSecurityname() {
		m_equity.setSecurityname("MrAfterSet");
		assertEquals("MrAfterSet", m_equity.getSecurityname());
		m_success++;
	}

	/**
	 * Test method for {@link com.anthony.playstation.configuration.ASecurity#getSecurityID()}.
	 */
	@Test
	public void testGetSecurityID() {
		assertEquals("Hand", m_equity.getSecurityID());
		m_success++;
	}

	/**
	 * Test method for {@link com.anthony.playstation.configuration.ASecurity#setSecurityID(java.lang.String)}.
	 */
	@Test
	public void testSetSecurityID() {
		m_equity.setSecurityID("HandAfterSet");
		assertEquals("HandAfterSet", m_equity.getSecurityID());
		m_success++;
	}

}
