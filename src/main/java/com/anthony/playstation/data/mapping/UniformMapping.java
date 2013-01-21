package com.anthony.playstation.data.mapping;

import com.anthony.playstation.data.dataseries.UniformType;

public class UniformMapping {

	private String m_id = "";
	private UniformType m_type= null;
	
	public UniformMapping( String ID, UniformType type )
	{
		setID(ID);
		m_type = new UniformType(type);
				
	}

	public String getID() {
		return m_id;
	}

	public void setID(String m_id) {
		this.m_id = m_id;
	}
	
	public UniformType getUniformType()
	{
		return m_type;
	}
	
	public String toString()
	{
		return "ID: "+m_id+m_type.toString();
	}
}
