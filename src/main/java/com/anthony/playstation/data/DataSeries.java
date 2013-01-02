package com.anthony.playstation.data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class DataSeries {
	private List<ADataUnit> m_list = new LinkedList<ADataUnit>();
	private String m_name = "";
	private Date m_startDate = null;
	private Date m_endDate = null;
	
	public DataSeries( String name )
	{
		m_name = name;
	}
	
	private boolean isValidType( ADataUnit unit )
	{
		if( m_list.size() == 0 )
			return true;
		
		return m_list.get(0).getType() == unit.getType();
	}
	
	public void addUnit( ADataUnit unit ) throws InvalidDataUnitException
	{
		if( !this.isValidType(unit) )
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Target unit: "+this.getUnitType().toString());
			sb.append(" but you're trying to add a "+unit.getType().toString()+" !");
			throw new InvalidDataUnitException( sb.toString(), null);
		}
		int index = 0;
		for( ADataUnit tempunit : m_list )
		{
			if(tempunit.compareData(unit) <0 )
			{
				m_list.add(index, unit);
				return;
			}
			else if ( tempunit.compareData(unit) > 0 )
			{
				index++;
				continue;
			}
			else
			{
				StringBuilder sb = new StringBuilder();
				
				sb.append("Adding an exiting DataUnit into data series "+unit.getCalendar().toString());
				
				throw new InvalidDataUnitException(sb.toString(), null);
			}
		}
	}

	public DataUnitType getUnitType()
	{
		if( m_list.size() == 0 )
			return DataUnitType.DefaultUnit;
		
		else return m_list.get(0).getType();
	}
	
	public String getSeriesName() {
		return m_name;
	}

	public void setSeriesName(String m_name) {
		this.m_name = m_name;
	}

	public List<ADataUnit> getUnitList() {
		return m_list;
	}

	public void setUnitList(List<ADataUnit> m_list) {
		this.m_list = m_list;
	}

	public Date getStartDate() {
		return m_startDate;
	}

	public void setStartDate(Date m_startDate) {
		this.m_startDate = m_startDate;
	}

	public Date getEndDate() {
		return m_endDate;
	}

	public void setEndDate(Date m_endDate) {
		this.m_endDate = m_endDate;
	}

	public int getSeriesSize() {
		return m_list.size();
	}

}
