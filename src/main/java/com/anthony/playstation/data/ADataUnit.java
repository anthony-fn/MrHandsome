/**   
* @Title: 		ADataUnit.java
* @Package 		com.anthony.playstation.data
* @Description: 
* 				Contains the abstract class ADataUnit
* @author 		Anthony Fan
* @date 		2013-1-10 
* @time 		14:32:54
* @version 		V 1.0   
*/
package com.anthony.playstation.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

/**
 */
public abstract class ADataUnit{

	/**
	 * Field m_cal.
	 */
	private Calendar m_cal = null;
	/**
	 * Field m_type.
	 */
	private DataUnitType m_type = DataUnitType.DefaultUnit;
			
	/**
	 * Method getCalendar.
	 * @return Calendar. Only yyyy-MM-dd is kept.
	 */
	public Calendar getCalendar() {
		return m_cal;
	}
	
	/**
	 * Method getDateString. Get the string version of Calendar value within ADataUnit with the format yyyy-MM-dd 
	 * @return String
	 */
	public String getDateString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(m_cal.getTime());
	}

	/**
	 * Method setCalendar.
	 * @param Calendar. Only yyyy-MM-dd is kept.
	 */
	public void setCalendar(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		this.m_cal = cal;
	}
	
	/**
	 * Method setCalendar.
	 * @param date String, with the format "yyyy-MM-dd"
	 * @throws InvalidDataUnitException if the input string is not of the correct format.
	 */
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

	protected void setType( DataUnitType type )
	{
		m_type = type;
	}
	/**
	 * Method getType.
	 * @return DataUnitType. The DataUnitType value of current instance, default is DataUnitType.DefaultValue
	 */
	public DataUnitType getType() {
		return m_type;
	}
	
	/**
	 * Method compareDate.
	 * Compare the Calendar value of two ADataUnits. 
	 * The Calendar in both this and "unit" only has the value for yyyy-MM-dd
	 * 
	 * @param unit ADataUnit
	 * @return Less than 0, if this.Calendar is before the calendar in "unit";
	 * 0,	if this.Calendar has the same date value with "unit";
	 * larger than 0, if this.Calendar is after the calendar in "unit";
	 */
	public int compareDate( ADataUnit unit )
	{
		Calendar cal = unit.getCalendar();
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return m_cal.compareTo(cal);
	}
	
	/**
	 * Abstract method setValue.
	 * Derived classes should convert "value" into different types.
	 * @throws InvalidDataUnitException 
	 */
	public abstract void setValue( Object value) throws InvalidDataUnitException;
	
	/**
	 * Abstract method getValue.
	 * 
	 * @return Object
	 */
	public abstract Object getValue();
	/**
	 * Abstract method print.
	 */
	public abstract void print();
	
	/**
	 * Abstract method compareValue.
	 * Should be implemented in derived classes.
	 * @param unit ADataUnit
	 * @return int
	 * 
	 * < 0 	for less;
	 * 0	equal;
	 * >0	for larger
	 * @throws InvalidDataUnitException
	 */
	public abstract int compareValue( ADataUnit unit) throws InvalidDataUnitException;
}
