package com.anthony.playstation.configuration;

import com.anthony.playstation.configuration.ASecurity;

public class ChinaEquity extends ASecurity
{
	private String m_performanceId = "";
	public ChinaEquity(String name, String secID, String performanceID)
	{
		super(name, secID);
		// TODO Auto-generated constructor stub
		
		m_performanceId = performanceID;
	}
	
	public String getPerformanceID ()
	{
		return m_performanceId;
	}

}
