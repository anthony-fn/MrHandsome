package com.anthony.playstation.dataunittest;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.data.dataunit.StringDataUnit;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class ValueDataUnitTest
{
	
	private static ValueDataUnit m_unit = null;

	private static void init()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		m_unit = new ValueDataUnit(cal, 10);
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		init();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Test
	public void testSetValue()
	{
		m_unit.setValue((float)20);
		assertTrue( m_unit.getValue() == 20);
		init();
	}

	@Test
	public void testCompareValue() throws InvalidDataUnitException
	{
		ValueDataUnit unit = new ValueDataUnit(m_unit.getCalendar(), 10);
		
		try
		{
			assertTrue( 0 == unit.compareValue(m_unit));
		} catch (InvalidDataUnitException e)
		{
			fail();
		}
		
		unit.setValue(20);
		
		try
		{
			assertTrue( 0 < unit.compareValue(m_unit));
			assertTrue( 0 > m_unit.compareValue(unit));
		} catch (InvalidDataUnitException e)
		{
			fail();
		}
		
		StringDataUnit unit2 = new StringDataUnit(m_unit.getCalendar(), "BradPitt");
		
		try
		{
			m_unit.compareValue(unit2);
			fail();
		} catch (InvalidDataUnitException e)
		{
		}
	}

	@Test
	public void testValueDataUnitCalendarFloat()
	{
		assertNotNull(m_unit);
	}

	@Test
	public void testValueDataUnitStringFloat()
	{
		ValueDataUnit unit = null;
		
		try {
			unit = new ValueDataUnit("1234-1-2", 10);
			assertNotNull(unit);
		} catch (InvalidDataUnitException e) {
			fail();
		}
		
		try {
			unit = new ValueDataUnit("MrHandsome", 20);
			fail();
		} catch (InvalidDataUnitException e) {
		}
	}

	@Test
	public void testGetValue()
	{
		assertTrue( m_unit.getValue() == 10);
	}

	@Test
	public void testGetCalendar()
	{
		Calendar cal = m_unit.getCalendar();
		
		assertEquals(cal.get(Calendar.YEAR), 1901);
		assertEquals(cal.get(Calendar.MONTH), 1);
		assertEquals(cal.get(Calendar.DAY_OF_MONTH), 1);
	}

	@Test
	public void testGetDateString()
	{	
		assertTrue(m_unit.getDateString().equals("1901-02-01"));
	}

	@Test
	public void testSetCalendarCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2000);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 11);
		
		m_unit.setCalendar(cal);
		
		assertTrue( m_unit.getDateString().equals("2000-12-11"));
		init();
	}

	@Test
	public void testSetCalendarString()
	{
		try {
			m_unit.setCalendar("2000-12-11");
			
			assertEquals(m_unit.getCalendar().get(Calendar.YEAR), 2000);
			assertEquals(m_unit.getCalendar().get(Calendar.MONTH), 11);
			assertEquals(m_unit.getCalendar().get(Calendar.DAY_OF_MONTH), 11);
		} catch (InvalidDataUnitException e) {
			fail();
		}
		
		try {
			m_unit.setCalendar("avc");
			fail();
		} catch (InvalidDataUnitException e) {
			init();
		}
	}

	@Test
	public void testSetType()
	{
		try {
			m_unit.setType(DataUnitType.StringUnit);
			fail();
		} catch (InvalidDataUnitException e) {
		}
		
		try {
			m_unit.setType(DataUnitType.DefaultUnit);
			fail();
		} catch (InvalidDataUnitException e) {
		}
		
		try {
			m_unit.setType(DataUnitType.ValueUnit);
			fail();
		} catch (InvalidDataUnitException e) {
		}
	}
	@Test
	public void testGetType()
	{
		assertEquals(m_unit.getType(), DataUnitType.ValueUnit);
	}

	@Test
	public void testCompareDate()
	{
		ValueDataUnit unit = null;
		try {
			 unit = new ValueDataUnit("1901-02-01", 10);
			 
			 assertTrue( unit.compareDate(m_unit) == 0 );
			 
			 unit.setCalendar("1901-12-12");
			 
			 assertTrue( 0 < unit.compareDate(m_unit));
			 assertTrue( 0 > m_unit.compareDate(unit));
		} catch (InvalidDataUnitException e) {
			fail();
		}
	}
}
