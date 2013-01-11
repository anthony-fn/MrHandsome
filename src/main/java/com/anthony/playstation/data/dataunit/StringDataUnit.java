/**   
* @Title: 		StringDataUnit.java
* @Package 		com.anthony.playstation.data.dataunit
* @Description: 
* 				Contains the class StringDataUnit
* @author 		Anthony Fan
* @date 		2013-1-10 
* @time 		11:37:14
* @version 		V 1.0   
*/
package com.anthony.playstation.data.dataunit;

import java.util.Calendar;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;


/**
 * The Class StringDataUnit, which is a derived class of ADataUnit.
 * 
 * simply a pair of <Calendar, String>.
 */
public class StringDataUnit extends ADataUnit{

	/** The String value. */
	private String m_value = "";
	
	/**
	 * Instantiates a new string data unit.
	 *
	 * @param cal Calendar. Only keeps the data yyyy-MM-dd.
	 * @param value String
	 * @throws InvalidDataUnitException 
	 */
	public StringDataUnit( Calendar cal, String value )
	{
		this.setCalendar(cal);
		this.setValue(value);
		try {
			this.setType(DataUnitType.StringUnit);
		} catch (InvalidDataUnitException e) {
			//Should not happen
		}
	}

	/**
	 * Gets the String value.
	 *
	 * @return String value
	 */
	@Override
	public String getValue() {
		return m_value;
	}

	/**
	 * Sets the String value.
	 *
	 * @param The new String value
	 */
	public void setValue(String m_value) {
		this.m_value = m_value;
	}

	/* (non-Javadoc)
	 * @see com.anthony.playstation.data.ADataUnit#compareValue(com.anthony.playstation.data.ADataUnit)
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
		
		return m_value.compareTo( ((StringDataUnit)unit).getValue());
	}
	
	/* (non-Javadoc)
	 * @see com.anthony.playstation.data.ADataUnit#print()
	 */
	@Override
	public void print()
	{
		System.out.println(this.getCalendar().toString()+"\t"+m_value);
	}

	/* (non-Javadoc)
	 * @see com.anthony.playstation.data.ADataUnit#setValue()
	 */
	@Override
	public void setValue(Object value) throws InvalidDataUnitException
	{
		if(!( value instanceof java.lang.String ) )
			throw new InvalidDataUnitException("Assiging a non-String value to StringDataUnit .");
		
		m_value = (String)value;
	}
	
	@Override
	public void setType( DataUnitType type ) throws InvalidDataUnitException
	{
		if( this.getType() != DataUnitType.DefaultUnit )
			throw new InvalidDataUnitException("Should not change the DataUnitType for an existing DataUnit instance.");
		else if ( type != DataUnitType.StringUnit )
			throw new InvalidDataUnitException("Instance of StringDataUnit could only be set to have DataUnitType.StringUnit");
		super.setType(type);
	}
	
	
}
