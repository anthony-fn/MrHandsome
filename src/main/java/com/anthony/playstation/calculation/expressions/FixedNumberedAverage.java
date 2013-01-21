package com.anthony.playstation.calculation.expressions;

import java.util.List;

import com.anthony.playstation.calculation.AExpression;
import com.anthony.playstation.calculation.operators.ASingleOperator;
import com.anthony.playstation.calculation.operators.NumberedAverage;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.InvalidExpressionException;
import com.anthony.playstation.exceptions.InvalidOperationException;

public class FixedNumberedAverage extends AExpression {

	private int m_numberOfDays = 0;
	private ASingleOperator m_operator = null;
	private UniformType m_type = null;
	private static final int RESULT_TYPE = 9998;
	
	public FixedNumberedAverage( int numOfDays ) throws InvalidExpressionException {
		super(numOfDays+"DaysAverageExpression");
		m_numberOfDays = numOfDays;
		try {
			m_operator = new NumberedAverage(m_numberOfDays);
			m_type = UniformTypeDB.getType(RESULT_TYPE);
		} catch (InvalidOperationException e) {
			throw new InvalidExpressionException("Can't create expression with error "+e.getMessage(),
					new Exception(e));
		} catch (ConfigurationException e) {
			throw new InvalidExpressionException("Can't get the type for calculation result "+RESULT_TYPE);
		}
	}

	public int getDayNumber()
	{
		return m_numberOfDays;
	}
	
	@Override
	public DataSeries evaluate(UniformType type, List<DataSeries> srcSeries) throws InvalidExpressionException
	{
		if( srcSeries == null || srcSeries.size() != 1 )
			throw new InvalidExpressionException("Invalid input for FixedNumberedAverage calculation !");
		DataSeries result = null;
		try {
			if(type != null)
				m_type = type;
			result = m_operator.operate(m_type, srcSeries.get(0));
		} catch (InvalidOperationException e) {
			throw new InvalidExpressionException("Can't evaluate with error "+e.getMessage(), new Exception(e));
		}
		return result;
	}
}
