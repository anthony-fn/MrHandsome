package com.anthony.playstation.configuration;

public abstract class ASecurityType
{
	private String m_typeName = "";
	private int m_type = 0;
	
	public ASecurityType( String name )
	{
		m_typeName = name;
	}
	
	public ASecurityType( String name, int type )
	{
		m_typeName = name;
		m_type = type;
	}

	public String getTypeName()
	{
		return m_typeName;
	}
	
	public int getType()
	{
		return m_type;
	}

	public void setType(String typeName, int type)
	{
		m_typeName = typeName;
		m_type = type;
	}

}
