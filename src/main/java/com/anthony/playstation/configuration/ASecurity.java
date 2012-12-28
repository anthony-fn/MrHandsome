package com.anthony.playstation.configuration;

public abstract class ASecurity
{
	private String m_name = "";
	private String m_secID = "";
	
	public ASecurity( String secID )
	{
		setSecurityID(secID);
	}
	public ASecurity( String name, String secID )
	{
		setSecurityID(secID);
		setSecurityname(name);
	}

	public String getSecurityname()
	{
		return m_name;
	}

	public void setSecurityname(String name)
	{
		m_name = name;
	}

	public String getSecurityID()
	{
		return m_secID;
	}

	public void setSecurityID(String secID)
	{
		m_secID = secID;
	}
}
