/**   
* @Title: 		DataSeries.java
* @Package 		com.anthony.playstation.data.dataseries
* @Description: 
* 				Contains class DataSeries
* @author 		Anthony Fan
* @date 		2013-1-9 
* @time 		11:02:14
* @version 		V 1.0   
*/
package com.anthony.playstation.data.dataseries;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.PlaystationUtility;
import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

/**
 * The Class DataSeries is the main data format in MrHandSome.
 * It's main part is a list of <data, value> pair.
 */
public class DataSeries{
	
	/** The list which contains all the value. */
	private List<ADataUnit> m_list = new LinkedList<ADataUnit>();
	
	/** The ObjectID.
	 * 	For ChinaEquity, it's performanceID in TSDB.
	 *  */
	private String m_objID = "";
	
	/** UniformType of this series. */
	private UniformType m_uniType = null;
	
	/** Start date of the records. */
	private Calendar m_startDate = null;
	
	/** End date of the records. */
	private Calendar m_endDate = null;
	
	/**
	 * Instantiates an empty DataSeries with basic data type information.
	 *
	 * @param type UniformType
	 * @param perID String
	 */
	public DataSeries( UniformType type, String perID )
	{
		m_uniType = type;
		m_objID = perID;
		m_startDate = PlaystationUtility.unifyCalendar(null);
		m_endDate = PlaystationUtility.unifyCalendar(null);
		
	}
	
	/**
	 * Compare if two DataSeries have the same values.
	 *
	 * @param src DataSeries
	 * @return true, if two DataSeries are equal
	 * @throws InvalidDataUnitException the invalid data unit exception
	 */
	public boolean equals( DataSeries src ) throws InvalidDataUnitException
	{
		if( src == null )
			return false;
		else if( !m_uniType.equals(src.getUniType()) )
			return false;
		else if(!m_objID.equals(src.getPerformanceID() ) )
			return false;
		else if(m_list.size() != src.getSeriesSize() )
			return false;
		
		for( int i = 0; i < m_list.size(); i ++ )
		{
			if( 0 != m_list.get(i).compareDate(src.getUnitList().get(i)) )
				return false;
			
			if( 0 != m_list.get(i).compareValue(src.getUnitList().get(i)) )
				return false;
		}
		
		return true;
	}
	
	/**
	 * Checks if a DataUnit is the same with the data units in this DataSeries
	 *
	 * @param unit the unit
	 * @return true, if is valid type
	 */
	private boolean isValidType( ADataUnit unit )
	{
		if( m_list.size() == 0 )
			return true;
		
		return m_list.get(0).getType() == unit.getType();
	}
	
	/**
	 * Gets the file name in local storage.
	 * 
	 * Object+"_"+UniformType.getName()
	 *
	 * @return the file name
	 */
	public String getFileName()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(m_objID+"_"+m_uniType.getTypeName());
		
		return sb.toString();
	}

	private void setDates( Calendar date )
	{
		this.setStartDate(date);
		this.setEndDate(date);
	}
	
	/**
	 * Add a new DataUnit into current DataSeries.
	 * 
	 * Conditions:
	 * 1. The new DataUnit must have the same type.
	 * 2. If the Date value in the new DataUnit is already in DataSeries, an exception would be thrown.
	 *
	 * @param unit ADataUnit.
	 * @throws InvalidDataUnitException the invalid data unit exception
	 */
	public void addUnit( ADataUnit unit ) throws InvalidDataUnitException
	{
		//new unit should have the same UniformType.
		if( !this.isValidType(unit) )
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Target unit: "+this.getValueType().toString());
			sb.append(" but you're trying to add a "+unit.getType().toString()+" !");
			throw new InvalidDataUnitException( sb.toString(), null);
		}
		if( m_list.size() == 0 )
		{
			//If the DataSeries is still empty.
			m_list.add(unit);
			return;
		}
		
		int index = 0;
		for( ADataUnit tempunit : m_list )
		{			
			if(tempunit.compareDate(unit) <0 )
			{
				m_list.add(index, unit);
				this.setDates(unit.getCalendar());
				return;
			}
			else if ( tempunit.compareDate(unit) > 0 )
			{					
				index++;
				continue;
			}
			else
			{
				// If the Date value in new DataUnit has already been in current DataSeries, throw an exception.
				StringBuilder sb = new StringBuilder();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				
				sb.append("Adding an exiting DataUnit into data series ");
				sb.append(sdf.format(unit.getCalendar().getTime()));
				
				throw new InvalidDataUnitException(sb.toString(), null);
			}
		}
	}

	/**
	 * Gets the value type.
	 *
	 * @return DataUnitType
	 */
	public DataUnitType getValueType()
	{
		if( m_list.size() == 0 )
			return DataUnitType.DefaultUnit;
		
		else return m_list.get(0).getType();
	}

	/**
	 * Gets the unit list.
	 *
	 * @return the unit list
	 */
	public List<ADataUnit> getUnitList() {
		return m_list;
	}

	/**
	 * Sets the unit list.
	 *
	 * @param m_list the new unit list
	 */
	public void setUnitList(List<ADataUnit> m_list) {
		this.m_list = m_list;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Calendar getStartDate() {
		return m_startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param m_startDate the new start date
	 */
	private void setStartDate(Calendar startDate) {
		if( m_startDate == null )
			m_startDate = startDate;
		else if ( m_startDate.compareTo(startDate) > 0 )
			m_startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Calendar getEndDate() {
		return m_endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param m_endDate the new end date
	 */
	private void setEndDate(Calendar endDate) {
		
		if( m_endDate == null )
			m_endDate = endDate;
		else if ( m_endDate.compareTo(endDate) < 0 )
		{
			m_endDate = endDate;
		}
	}

	public Object getValue( Calendar cal )
	{
		PlaystationUtility.unifyCalendar(cal);
		
		if( cal.compareTo(m_startDate) < 0 || cal.compareTo(m_endDate) > 0 )
			return null;
		
		for( ADataUnit unit : m_list )
		{
			if( cal.compareTo(unit.getCalendar()) == 0 )
				return unit.getValue();
		}
		return null;
	}
	/**
	 * Gets the series size.
	 *
	 * @return the series size
	 */
	public int getSeriesSize() {
		return m_list.size();
	}

	/**
	 * Gets the uni type.
	 *
	 * @return the uni type
	 */
	public UniformType getUniType()
	{
		return m_uniType;
	}

	/**
	 * Sets the uni type.
	 *
	 * @param m_uniType the new uni type
	 */
	public void setUniType(UniformType m_uniType)
	{
		this.m_uniType = m_uniType;
	}

	/**
	 * Gets the performance id.
	 *
	 * @return the performance id
	 */
	public String getPerformanceID()
	{
		return m_objID;
	}

	/**
	 * Sets the performance id.
	 *
	 * @param m_objID the new performance id
	 */
	public void setPerformanceID(String m_objID)
	{
		this.m_objID = m_objID;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("DataSeries "+ this.getPerformanceID() + "\t" + this.getUniType().getTypeName()+"\n");
		
		for( ADataUnit unit : m_list )
		{
			sb.append(unit.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public ADataUnit getUnit( Calendar cal )
	{
		PlaystationUtility.unifyCalendar(cal);
		
		if( cal.compareTo(m_startDate) < 0 || cal.compareTo(m_endDate) > 0 )
			return null;
		
		for( ADataUnit unit : m_list )
		{
			if( cal.compareTo(unit.getCalendar()) == 0 )
				try
				{
					return unit.getClass().getConstructor(Calendar.class, Object.class).newInstance(unit.getCalendar(), unit.getValue());
				} catch (InstantiationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

}
