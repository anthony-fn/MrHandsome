package com.anthony.playstation.calculation;

import com.anthony.playstation.calculation.operators.OperatorType;

public abstract class AOperator {

	private String m_name = "Operator";
	private OperatorType m_type = OperatorType.NullOperator;

	public AOperator( String name )
	{
		m_name = name;
	}
	
	public String getOperatorName() {
		return m_name;
	}

	public void setOperatorName(String m_name) {
		this.m_name = m_name;
	}

	public OperatorType getOperatorType() {
		return m_type;
	}

	public void setOperatorType(OperatorType m_type) {
		this.m_type = m_type;
	}
}
