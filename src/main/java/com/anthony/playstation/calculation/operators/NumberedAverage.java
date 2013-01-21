/**   
* @Title: 		NumberedEven.java
* @Package 		com.anthony.playstation.calculation.operators
* @Description: 
* 				Class NumberedEven
* @author 		Anthony Fan
* @date 		2013-1-18 
* @time 		11:39:09
* @version 		V 1.0   
*/
package com.anthony.playstation.calculation.operators;

import java.util.ArrayList;
import java.util.List;

import com.anthony.playstation.data.ADataUnit;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataunit.DataUnitType;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.exceptions.InvalidDataUnitException;
import com.anthony.playstation.exceptions.InvalidOperationException;

/**
 */
public class NumberedAverage extends ASingleOperator{
	
	/**
	 * Number of days.
	 */
	private int m_num = 0;
	/**
	 * List to keep the value to be calculated. Should be with the sice m_num.
	 */
	private List<Float> m_list = null;
	
	/**
	 * Constructor for NumberedEven.
	 * @param num int.	 Number of days
	 * @throws InvalidOperationException
	 */
	public NumberedAverage( int num ) throws InvalidOperationException
	{
		super(num+"DaysAverage");
		if( num <= 0 )
		{
			throw new InvalidOperationException("Can't init a NumberedEven operator with a negative number");
		}
		this.setOperatorName("NumberedEven");
		this.setOperatorType(OperatorType.SingleOperator);
		setNum(num);
		
		m_list = new ArrayList<Float>(m_num);
	}

	/**
	 * Method getNum.
	 * @return int Number of days this operator use.
	 */
	public int getNum() {
		return m_num;
	}

	/**
	 * Method setNum.
	 * @param m_num int
	 */
	public void setNum(int m_num) {
		this.m_num = m_num;
	}
	
	/**
	 * Method operate.
	 * @return float
	 * @throws InvalidOperationException
	 */
	private float operate() throws InvalidOperationException
	{
		if( m_list.size() != m_num )
			throw new InvalidOperationException("Operation error...");
		float result = 0;
		for( int i = 0; i < m_num; i ++ )
		{
			result += m_list.get(i);
		}
		
		return result/m_num;
	}
	/**
	 * Method operate.
	 * @param type UniformType. The UniformType information of the result.
	 * @param srcSeries DataSeries	Data source.
	 * @return DataSeries
	 * @throws InvalidOperationException
	 */
	@Override
	public DataSeries operate( UniformType type, DataSeries srcSeries) throws InvalidOperationException
	{
		DataSeries result = null;
		
		if( srcSeries == null )
			throw new InvalidOperationException("SingleOperator can't operate on a null DataSeries instance.");
		if( !isValidData(srcSeries.getValueType()) )
			throw new InvalidOperationException("NumberedEven could only handle ValueDataUnit. ");
		
		List<ADataUnit> src = srcSeries.getUnitList();
		if( src == null || src.size() < m_num )
			return result;
		
		result = new DataSeries( type, srcSeries.getPerformanceID() );
		
		for( int index = src.size(); index > 0; index --)
		{
			ADataUnit unit =src.get(index-1); 
			if( m_list.size() < m_num-1 )
			{
				m_list.add((Float)unit.getValue());
				continue;
			}
			
			m_list.add((Float)unit.getValue());
			ADataUnit resultUnit = new ValueDataUnit(unit.getCalendar(), this.operate());
			try {
				result.addUnit(resultUnit);
			} catch (InvalidDataUnitException e) {
				throw new InvalidOperationException("Calculation error "+e.getMessage(), new Exception(e) );
			}
			
			m_list.remove(0);
		}
		return result;
	}

	/**
	 * Method isValidData.
	 * Check if the source data is with a valid DataUnitType
	 * @param type DataUnitType
	 * @return boolean
	 */
	@Override
	public boolean isValidData(DataUnitType type) {
		return type == DataUnitType.ValueUnit ? true : false;
	}
}
