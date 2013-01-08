package com.anthony.playstation.data.dataunit;

import java.util.Calendar;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class StringDataUnit extends ADataUnit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5006123960266863949L;
	private String m_value = "";
	
	public StringDataUnit( Calendar cal, String value )
	{
		this.setCalendar(cal);
		this.setValue(value);
		this.setType(DataUnitType.StringUnit);
	}

	public String getValue() {
		return m_value;
	}

	public void setValue(String m_value) {
		this.m_value = m_value;
	}

	@Override
	public Object getUniverseValue() {
		return (Object)this.getValue();
	}

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
	
	@Override
	public void print()
	{
		System.out.println(this.getCalendar().toString()+"\t"+m_value);
	}
	
}
