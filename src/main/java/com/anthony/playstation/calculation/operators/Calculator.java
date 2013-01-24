package com.anthony.playstation.calculation.operators;

import java.util.Calendar;

import com.anthony.playstation.PlaystationUtility;
import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.exceptions.CalculationException;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;
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
	
	private static UniformType getUnitType( DataUnitType type ) throws ConfigurationException
	{
		switch( type )
		{
		case ValueUnit:
			return UniformTypeDB.getType(CalculatedValueSeries);
		case StringUnit:
			return UniformTypeDB.getType(CalculatedStringSeries);
		
		default:
			return UniformTypeDB.getType(CalculatedDefaultSeries);
		}
	}
	
	
	
	public static DataSeries largerThan( DataSeries front, DataSeries end ) throws CalculationException
	{
		if( front == null || end == null || front.getValueType() != end.getValueType())
			throw new CalculationException("Invalid input to compare");
		DataSeries result = null;
		UniformType type = null;
		
		try{
			getUnitType(front.getValueType());
		}
		catch( ConfigurationException e)
		{
			throw new CalculationException("Can't init result series "+e.getMessage(), new Exception(e));
		}
		
		result = new DataSeries(type, "");
		
		Calendar from = PlaystationUtility.copyCalendar(PlaystationUtility.getStart(front.getStartDate(), end.getStartDate()));
		Calendar to = PlaystationUtility.copyCalendar(PlaystationUtility.getEnd(front.getEndDate(), end.getEndDate()));
		
		for( ; from.compareTo(to) <= 0; from.add(Calendar.DAY_OF_YEAR, 1))
		{
			ADataUnit a = front.getUnit(from);
			ADataUnit b = front.getUnit(from);
			
			try
			{
				if( a.compareValue(b) > 0 )
					result.addUnit(a);
			} catch (InvalidDataUnitException e)
			{
				throw new CalculationException("Can't add data unit into result "+e.getMessage());
			}
		}
		
		return result;
	}

}
