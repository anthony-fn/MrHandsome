package com.anthony.playstation.calculation.operators;

public enum CalculationResultType {

	Succeed,
	Failed,
	InvalidInput,
	EmptyInput;
	
	public String toString()
	{
		return name();
	}
}
