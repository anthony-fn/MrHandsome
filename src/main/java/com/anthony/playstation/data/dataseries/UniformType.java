/**   
* @Title: 		UniformType.java
* @Package 		com.anthony.playstation.data.dataseries
* @Description: 
* 				Contains the class UniformType
* @author 		Anthony Fan
* @date 		2013-1-9 
* @time 		17:13:16
* @version 		V 1.0   
*/
package com.anthony.playstation.data.dataseries;

import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.ConfigurationException;


/**
 * The Class UniformType describe the type information of the basic data unit in MrHandsome.
 * Which includes, type ID, type Name and UnitType (String , Value).
 * 
 * Right now, all the supported are recorded in a configuration file, normally lies in "DataDefination/UniformDataTypes".
 */
public class UniformType
{
	
	/** ID of the UniformType. */
	private int m_id = 0;
	
	/** Name of the UniformType. */
	private String m_name ="";
	
	/** UnitType(String or Value) of this UniformType. */
	private DataUnitType m_valueType = DataUnitType.DefaultUnit;
	
	public UniformType( int id, String name, DataUnitType type )
	{
		setTypeID(id);
		setTypeName(name);
		setValueType(type);
	}
	
	/**
	 * Instantiates a new uniform type.
	 *
	 * @param id ID of the UniformType
	 * @param name Name of the UniformType
	 * @param type ValueType of the UniformType
	 * @throws ConfigurationException the configuration exception
	 */
	public UniformType( int id, String name, String type ) throws ConfigurationException
	{
		setTypeID(id);
		setTypeName(name);
		setValueType(type);
	}

	/**
	 * Equals.
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public boolean equals( UniformType type )
	{
		if( (this.m_id == type.getTypeID()) 
				&& (this.m_name.equals(type.getTypeName()))
				&& (this.m_valueType == type.getValueType()) )
			return true;
		else 
			return false;
	}
	
	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public int getTypeID()
	{
		return m_id;
	}

	/**
	 * Sets the type id.
	 *
	 * @param m_id the new type id
	 */
	public void setTypeID(int m_id)
	{
		this.m_id = m_id;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	public String getTypeName()
	{
		return m_name;
	}

	/**
	 * Sets the type name.
	 *
	 * @param m_name the new type name
	 */
	public void setTypeName(String m_name)
	{
		this.m_name = m_name;
	}

	/**
	 * Gets the value type.
	 *
	 * @return the value type
	 */
	public DataUnitType getValueType()
	{
		return m_valueType;
	}

	/**
	 * Sets the value type by DataUnitType.
	 *
	 * @param m_type the new value type
	 */
	public void setValueType(DataUnitType m_type)
	{
		this.m_valueType = m_type;
	}
	
	/**
	 * Sets the value type by a String.
	 *
	 * @param type the new value type
	 * @throws ConfigurationException the configuration exception
	 */
	public void setValueType( String type ) throws ConfigurationException
	{
		if( type.equals(DataUnitType.StringUnit.toString()) )
			this.setValueType(DataUnitType.StringUnit);
		else if ( type.equals(DataUnitType.ValueUnit.toString()) )
			this.setValueType(DataUnitType.ValueUnit);
		else if ( type.equals(DataUnitType.DefaultUnit.toString()) )
			this.setValueType(DataUnitType.DefaultUnit);
		
		else throw new ConfigurationException("Unsupported DataUnitType "+type);
	}
}
