package com.anthony.playstation.dataunittest;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
		System.out.println(m_unit.getValue());
		assertTrue( m_unit.getValue() == 20);
		init();
	}

	@Test
	public void testCompareValue()
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
		fail("Not yet implemented");
	}

	@Test
	public void testValueDataUnitStringFloat()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetValue()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetCalendar()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetDateString()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetCalendarCalendar()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetCalendarString()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetType()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetType()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testCompareDate()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetValue1()
	{
		fail("Not yet implemented");
	}

}
