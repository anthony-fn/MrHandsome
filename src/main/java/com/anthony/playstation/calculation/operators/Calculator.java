package com.anthony.playstation.calculation.operators;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.CalculationException;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.InvalidOperationException;

public class Calculator {
	private static final int CalculatedDefaultSeries = 9997;
	private static final int CalculatedValueSeries = 9998;
	private static final int CalculatedStringSeries = 9999;

	private static UniformType getType( int type, DataUnitType uniType ) throws ConfigurationException
	{
		if( type == 0 )
		{
			if( uniType == DataUnitType.StringUnit)
				return UniformTypeDB.getType(CalculatedStringSeries);
			else if (uniType == DataUnitType.ValueUnit)
				return UniformTypeDB.getType(CalculatedValueSeries);
			else
				return UniformTypeDB.getType(CalculatedDefaultSeries);
		}
		
		return UniformTypeDB.getType(type);
	}
	
	public static DataSeries FiveDaysAverage( DataSeries src ) throws CalculationException
	{
		return NumberedAverage(9, 5, src);
	}
	
	public static DataSeries TenDaysAverage( DataSeries src ) throws CalculationException
	{
		return NumberedAverage(10, 10, src);
	}
	
	public static DataSeries TwentyDaysAverage( DataSeries src ) throws CalculationException
	{
		return NumberedAverage( 11, 20, src);
	}
	
	public static DataSeries SixtyDaysAverage( DataSeries src ) throws CalculationException
	{
		return NumberedAverage( 12, 60, src );
	}
	
	public static DataSeries NumberedAverage( int type, int numberOfDays, DataSeries src ) throws CalculationException
	{
		UniformType resulttype = null;
		try {
			resulttype = getType( type, src.getValueType());
		} catch (ConfigurationException e) {
			throw new CalculationException("Invalid UniformType input "+type, new Exception(e));
		}
		
		NumberedAverage operator = null;
		try {
			operator = new NumberedAverage(numberOfDays);
		} catch (InvalidOperationException e) {
			throw new CalculationException( "Can't create operator "+e.getMessage(), new Exception(e));
		}
		DataSeries result = null;
		try {
			result = operator.operate(resulttype, src);
		} catch (InvalidOperationException e) {
			throw new CalculationException("Calculatioin error "+e.getMessage(), new Exception(e));
		}
		return result;
	}
	

}
