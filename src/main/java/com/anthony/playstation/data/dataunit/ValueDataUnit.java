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

import java.math.BigDecimal;
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
	 * float m_value.
	 */
	private float m_value = 0;
	
	/**
	 * Constructor for ValueDataUnit.
	 * @param cal Calendar
	 * @param value float
	 * @throws InvalidDataUnitException 
	 */
	public ValueDataUnit( Calendar cal, float value )
	{
		this.setCalendar(cal);
		this.setValue(value);
		try {
			this.setType(DataUnitType.ValueUnit);
		} catch (InvalidDataUnitException e) {
			//Should not happen.
		}
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

	public ValueDataUnit( Calendar cal, Object obj ) throws InvalidDataUnitException
	{
		this.setCalendar(cal);
		this.setValue(obj);
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
		BigDecimal bd = new BigDecimal(value);
		m_value = bd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void setValue( long value )
	{
		BigDecimal bd = new BigDecimal(value);
		m_value = bd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void setValue( float value )
	{
		BigDecimal bd = new BigDecimal(value);
		m_value = bd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void setValue( double value )
	{
		BigDecimal bd = new BigDecimal(value);
		m_value = bd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
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
		System.out.println(this.toString());
	}
	
	@Override
	public void setType( DataUnitType type ) throws InvalidDataUnitException
	{
		if( this.getType() != DataUnitType.DefaultUnit )
			throw new InvalidDataUnitException("Should not change the DataUnitType for an existing DataUnit instance.");
		else if ( type != DataUnitType.ValueUnit )
			throw new InvalidDataUnitException("Instance of ValueDataUnit could only be set to have DataUnitType.ValueUnit");
		super.setType(type);
	}

	@Override
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		float temp = m_value;
		BigDecimal bd = new BigDecimal(temp);
		return sdf.format(this.getCalendar().getTime())+"\t"+bd.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
