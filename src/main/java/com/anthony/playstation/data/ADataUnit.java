package com.anthony.playstation.data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public abstract class ADataUnit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7638268717474206448L;
	private Calendar m_cal = null;
	private DataUnitType m_type = DataUnitType.DefaultUnit;
			
	public Calendar getCalendar() {
		return m_cal;
	}
	
	public String getDateString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(m_cal.getTime());
	}

	public void setCalendar(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		this.m_cal = cal;
	}
	
	public void setCalendar( String date ) throws InvalidDataUnitException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		try
		{
			cal.setTime(sdf.parse(date));
			this.setCalendar(cal);
		} catch (ParseException e)
		{
			throw new InvalidDataUnitException("Invalid date input "+date);
		}
	}

	public DataUnitType getType() {
		return m_type;
	}

	public void setType(DataUnitType m_type) {
		this.m_type = m_type;
	}
	
	public int compareData( ADataUnit unit )
	{
		return m_cal.compareTo(unit.getCalendar());
	}
	
	public abstract void print();
	
	public abstract Object getUniverseValue();
	public abstract int compareValue( ADataUnit unit) throws InvalidDataUnitException;
}
