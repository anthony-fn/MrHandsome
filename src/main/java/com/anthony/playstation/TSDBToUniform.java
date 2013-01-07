package com.anthony.playstation;

import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.exceptions.ConfigurationException;

public class TSDBToUniform
{
	private int m_tstype = 0;
	private String m_name = "";
	private MappingType m_mapping = MappingType.MappingBaseObject;
	private List<Integer> m_list = new LinkedList<Integer>();
	
	public TSDBToUniform( int tstype, String name, MappingType type )
	{
		m_tstype = tstype;
		setName(name);
		setMappingType(type);
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
}
