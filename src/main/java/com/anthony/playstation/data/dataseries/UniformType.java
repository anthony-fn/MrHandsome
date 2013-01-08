package com.anthony.playstation.data.dataseries;

import java.io.Serializable;

import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.ConfigurationException;

public class UniformType implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2411473258393831747L;
	private int m_id = 0;
	private String m_name ="";
	private DataUnitType m_valueType = DataUnitType.DefaultUnit;
	
	public UniformType( int id, String name, DataUnitType type )
	{
		setTypeID(id);
		setTypeName(name);
		setValueType(type);
	}
	
	public UniformType( int id, String name, String type ) throws ConfigurationException
	{
		setTypeID(id);
		setTypeName(name);
		setValueType(type);
	}

	public boolean equals( UniformType type )
	{
		if( (this.m_id == type.getTypeID()) 
				&& (this.m_name.equals(type.getTypeName()))
				&& (this.m_valueType == type.getValueType()) )
			return true;
		else 
			return false;
	}
	public int getTypeID()
	{
		return m_id;
	}

	public void setTypeID(int m_id)
	{
		this.m_id = m_id;
	}

	public String getTypeName()
	{
		return m_name;
	}

	public void setTypeName(String m_name)
	{
		this.m_name = m_name;
	}

	public DataUnitType getValueType()
	{
		return m_valueType;
	}

	public void setValueType(DataUnitType m_type)
	{
		this.m_valueType = m_type;
	}
	
	public void setValueType( String type ) throws ConfigurationException
	{
		if( type.equals(DataUnitType.StringUnit.toString()) )
			this.setValueType(DataUnitType.StringUnit);
		else if ( type.equals(DataUnitType.ValueUnit.toString()) )
			this.setValueType(DataUnitType.ValueUnit);
		
		else throw new ConfigurationException("Unsupported DataUnitType "+type);
	}
}
