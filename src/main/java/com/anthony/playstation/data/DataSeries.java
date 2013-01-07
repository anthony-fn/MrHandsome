package com.anthony.playstation.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.UniformType;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class DataSeries implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5760160401423144772L;

	
	private List<ADataUnit> m_list = new LinkedList<ADataUnit>();
	private String m_name = "";
	private String m_objID = "";
	private UniformType m_uniType = null;
	private Date m_startDate = null;
	private Date m_endDate = null;
	
	public DataSeries( UniformType type, String perID )
	{
		m_uniType = type;
		m_name = m_uniType.getTypeName();
		m_objID = perID;
	}
	
	public DataSeries( String name )
	{
		m_name = name;
	}
	
	public boolean compare( DataSeries src ) throws InvalidDataUnitException
	{
		if( src == null )
			return false;
		
		if( !m_name.equals(src.getSeriesName()))
			return false;
		else if( !m_uniType.equals(src.getUniType()) )
			return false;
		else if(!m_objID.equals(src.getPerformanceID() ) )
			return false;
		else if(m_list.size() != src.getSeriesSize() )
			return false;
		
		for( int i = 0; i < m_list.size(); i ++ )
		{
			System.out.println("Compare : "+(i+1)+"/"+m_list.size());
			if( 0 != m_list.get(i).compareData(src.getUnitList().get(i)) )
				return false;
			
			if( 0 != m_list.get(i).compareValue(src.getUnitList().get(i)) )
				return false;
		}
		
		return true;
	}
	
	private boolean isValidType( ADataUnit unit )
	{
		if( m_list.size() == 0 )
			return true;
		
		return m_list.get(0).getType() == unit.getType();
	}
	
	public void print()
	{
		System.out.println( m_name);
		
		for( ADataUnit data : m_list )
		{
			data.print();
		}
	}
	
	public String getFileName()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(m_objID+"_"+m_name);
		
		return sb.toString();
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
		if( m_list.size() == 0 )
		{
			m_list.add(unit);
			return;
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
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				sb.append("Adding an exiting DataUnit into data series ");
				sb.append(sdf.format(unit.getCalendar().getTime()));
				
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

	public UniformType getUniType()
	{
		return m_uniType;
	}

	public void setUniType(UniformType m_uniType)
	{
		this.m_uniType = m_uniType;
	}

	public String getPerformanceID()
	{
		return m_objID;
	}

	public void setPerformanceID(String m_objID)
	{
		this.m_objID = m_objID;
	}

}
