package com.anthony.playstation.calculation;

import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.exceptions.InvalidExpressionException;

public abstract class AExpression {
	
	private String m_name = "Expression";
	
	public AExpression( String name )
	{
		m_name = name;
	}
	
	public String getExpressionName()
	{
		return m_name;
	}

	
	public abstract DataSeries evaluate(UniformType type, List<DataSeries> srcSeries)
			throws InvalidExpressionException;
}
