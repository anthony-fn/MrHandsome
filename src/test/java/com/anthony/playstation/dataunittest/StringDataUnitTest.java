package com.anthony.playstation.dataunittest;

import static org.junit.Assert.*;

import java.util.Calendar;
import org.junit.Test;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.data.dataunit.StringDataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class StringDataUnitTest
{
	@Test
	public void testGetUniverseValue()
	{
		Calendar cal = Calendar.getInstance();
		ADataUnit unit = null;
		unit = new StringDataUnit(cal, "MrHandsome");
		
		Object obj = unit.getValue();
		
		assertTrue(((String)obj).equals("MrHandsome"));
	}

	@Test
	public void testCompareValue()
	{
		Calendar cal = Calendar.getInstance();
		
		ADataUnit unit = null;
		ADataUnit unit1 = null;
		ADataUnit unit2 = null;
		unit = new StringDataUnit(cal, "BradPitt");
		unit1 = new StringDataUnit(cal, "TomCruise");
		unit2 = new StringDataUnit(cal, "BradPitt"); 
		
		try
		{
			assertTrue( unit.compareValue(unit1) < 0);
			assertTrue( unit1.compareValue(unit) > 0);
			assertTrue( unit.compareValue(unit2) == 0);
		} catch (InvalidDataUnitException e)
		{
			fail();
		}
		
	}

	@Test
	public void testGetValue()
	{
		Calendar cal = Calendar.getInstance();
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		
		assertTrue(unit.getValue().equals("BradPitt"));
	}

	@Test
	public void testSetValue()
	{
		Calendar cal = Calendar.getInstance();
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		unit.setValue("TomCruise");
		
		assertTrue(unit.getValue().equals("TomCruise"));
	}

	@Test
	public void testGetCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		
		Calendar cal1 = unit.getCalendar();
		
		assertEquals(cal1.get(Calendar.YEAR), 1901);
		assertEquals(cal1.get(Calendar.MONTH), 1);
		assertEquals(cal1.get(Calendar.DAY_OF_MONTH), 1);
	}

	@Test
	public void testGetDateString()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		assertTrue(unit.getDateString().equals("1901-02-01"));
	}

	@Test
	public void testSetCalendarCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, 1901);
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.DAY_OF_MONTH, 2);
		
		unit.setCalendar(cal1);
		assertEquals( unit.getCalendar().get(Calendar.DAY_OF_MONTH), 2);
	}

	@Test
	public void testSetCalendarString()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		
		try
		{
			unit.setCalendar("abc");
			fail();
		} catch (InvalidDataUnitException e)
		{
		}
		
		try
		{
			unit.setCalendar("1202-01-01");
		} catch (InvalidDataUnitException e)
		{
			fail();
		}
		
		assertTrue(unit.getDateString().equals("1202-01-01"));
	}

	@Test
	public void testGetType()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit unit = null;
		unit = new StringDataUnit(cal, "BradPitt");
		
		assertEquals(unit.getType(), DataUnitType.StringUnit);
	}
	
	@Test
	public void testSetType()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit m_unit = null;
		
		m_unit = new StringDataUnit(cal, "BradPitt");
			
		
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
	public void testCompareDate()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1901);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, 1901);
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		
		StringDataUnit unit = null;
		StringDataUnit unit1 = null;
		unit = new StringDataUnit(cal, "BradPitt");
		unit1 = new StringDataUnit( cal1, "TomCruise");
		
		assertTrue( 0 == unit.compareDate(unit1));
		
		cal.add(Calendar.YEAR, 1);
		System.out.println(unit.compareDate(unit1));
		assertTrue( 0 < unit.compareDate(unit1));
		assertTrue( 0 > unit1.compareDate(unit));
		
	}

}
