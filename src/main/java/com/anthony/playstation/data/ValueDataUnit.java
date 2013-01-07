package com.anthony.playstation.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class ValueDataUnit extends ADataUnit{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251354759542239246L;
	private float m_value = 0;
	
	public ValueDataUnit( Calendar cal, float value )
	{
		this.setCalendar(cal);
		this.setValue(value);
		this.setType(DataUnitType.ValueUnit);
	}

	public float getValue() {
		return m_value;
	}

	public void setValue(float m_value) {
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
		
		return (int) (m_value-((ValueDataUnit)unit).getValue());
	}

	@Override
	public void print()
	{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(this.getCalendar().getTime())+"\t"+m_value);
	}
	
}
