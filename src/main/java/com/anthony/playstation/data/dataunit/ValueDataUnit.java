/**   
* @Title: 		ValueDataUnit.java
* @Package 		com.anthony.playstation.data.dataunit
* @Description: 
* 				Contains the class ValueDataUnit.
* @author 		Anthony Fan
* @date 		2013-1-10 
* @time 		14:28:29
* @version 		V 1.0   
*/
package com.anthony.playstation.data.dataunit;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

/**
 * The Class ValueDataUnit, which is a derived class of ADataUnit.
 * 
 * simply a pair of <Calendar, float>.
 */
public class ValueDataUnit extends ADataUnit{

	/**
	 * Field m_value.
	 */
	private float m_value = 0;
	
	/**
	 * Constructor for ValueDataUnit.
	 * @param cal Calendar
	 * @param value float
	 */
	public ValueDataUnit( Calendar cal, float value )
	{
		this.setCalendar(cal);
		this.setValue(value);
		this.setType(DataUnitType.ValueUnit);
	}
	
	/**
	 * Constructor for ValueDataUnit.
	 * @param cal String
	 * @param value float
	 * @throws InvalidDataUnitException
	 */
	public ValueDataUnit( String cal, float value ) throws InvalidDataUnitException
	{
		this.setCalendar(cal);
		this.setValue(value);
		this.setType(DataUnitType.ValueUnit);
	}

	/**
	 * Method getValue.
	 * @return float
	 */
	@Override
	public Float getValue() {
		return m_value;
	}
	

	/**
	 * Method setValue.
	 * @param m_value float
	 */
	@Override
	public void setValue(Object m_value) {
		this.m_value = (Float)m_value;
	}
	
	public void setValue( int value)
	{
		this.m_value = value;
	}
	
	public void setValue( long value )
	{
		this.m_value = value;
	}
	
	public void setValue( float value )
	{
		this.m_value = value;
	}
	
	public void setValue( double value )
	{
		this.m_value = (float)value;
	}

	/**
	 * Method compareValue.
	 * @param unit ADataUnit
	 * @return int
	 * @throws InvalidDataUnitException
	 */
	@Override
	public int compareValue(ADataUnit unit) throws InvalidDataUnitException {
		if( unit.getType() != this.getType())
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Comparing a DataUnit with type "+ unit.getType().toString());
			sb.append(" to a DataUnit with type "+this.getType().toString() + " !");
			throw new InvalidDataUnitException( sb.toString(), null);
		}
		
		return (int) (m_value-(Float)((ValueDataUnit)unit).getValue());
	}

	/**
	 * Method print.
	 */
	@Override
	public void print()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(this.getCalendar().getTime())+"\t"+m_value);
	}
	
}
