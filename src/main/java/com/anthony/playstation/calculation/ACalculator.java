package com.anthony.playstation.calculation;

public abstract class ACalculator {
	
	private String m_name = "";
	
	public ACalculator( String name )
	{
		m_name = name;
	}

	public String getCalculatorName()
	{
		return m_name;
	}
}
