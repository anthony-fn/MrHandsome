package com.anthony.playstation.calculation.operators;

import com.anthony.playstation.calculation.AOperator;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.InvalidOperationException;

public abstract class ASingleOperator extends AOperator {
	
	public ASingleOperator(String name) {
		super("SingleOperator");
	}
	public abstract boolean isValidData( DataUnitType type );
	public abstract DataSeries operate( UniformType type, DataSeries src) throws InvalidOperationException;
}
