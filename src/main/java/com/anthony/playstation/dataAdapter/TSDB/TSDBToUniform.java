package com.anthony.playstation.dataAdapter.TSDB;

import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.exceptions.ConfigurationException;

public class TSDBToUniform
{
	private int m_tstype = 0;
	private String m_name = "";
	private MappingType m_mapping = MappingType.MappingBaseObject;
	private String m_className = "";
	private List<Integer> m_list = new LinkedList<Integer>();
	
	public TSDBToUniform( int tstype, String name, MappingType type, String className )
	{
		m_tstype = tstype;
		setName(name);
		setMappingType(type);
		setClassName(className);
	}
	
	public void addUniformType( int i ) throws ConfigurationException
	{
		if( m_list.contains(i))
			throw new ConfigurationException( "The defination for TSType="+m_tstype+" has already included UniformType "+i+".");
		
		m_list.add(i);
	}
	
	public void addUniformTypes( String types ) throws ConfigurationException
	{
		String [] typeList = types.split(",");
		
		for( int i = 0; i < typeList.length; i ++ )
		{
			this.addUniformType(Integer.parseInt(typeList[i]));
		}
	}

	public List<Integer> getUniformList()
	{
		return m_list;
	}
	
	public String getName()
	{
		return m_name;
	}

	public void setName(String m_name)
	{
		this.m_name = m_name;
	}

	public MappingType getMappingType()
	{
		return m_mapping;
	}

	public void setMappingType(MappingType m_mapping)
	{
		this.m_mapping = m_mapping;
	}

	public String getClassName()
	{
		return m_className;
	}

	public void setClassName(String m_className)
	{
		this.m_className = m_className;
	}
}
