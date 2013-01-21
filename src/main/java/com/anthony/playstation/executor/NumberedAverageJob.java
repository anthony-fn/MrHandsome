package com.anthony.playstation.executor;

import java.util.List;

import com.anthony.playstation.calculation.operators.CalculationResultType;
import com.anthony.playstation.calculation.operators.Calculator;
import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.mapping.UniformMapping;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.CalculationException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.JobOperationException;

public class NumberedAverageJob extends AJob{
	private UniformMapping m_mapping = null;
	private ADataIOProxy m_src = null;
	private ADataIOProxy m_tar = null;
	private ADataAdapter m_adapter = null;
	

	public NumberedAverageJob(ADataIOProxy source, ADataIOProxy target, 
			UniformMapping mapping) throws DataProxyOperationException {
		
		m_src = source;
		m_tar = target;
		m_mapping = mapping;
		m_adapter = new ProtoBufAdapter();
		this.setStatus(JobStatus.Inited);
	}

	@Override
	public Object call() throws Exception {
		JobStatus status = JobStatus.Running;
		this.setStatus(status);
		
		try {
			List<DataSeries> src = m_src.loadData(m_mapping.getID(), m_mapping.getUniformType(), m_adapter);
			
			if( src==null || src.size() != 1 )
			{
				this.setStatus(JobStatus.Succeed);
				return CalculationResultType.EmptyInput;
			}
			
			m_tar.saveData(m_adapter, Calculator.FiveDaysAverage(src.get(0)));
			m_tar.saveData(m_adapter, Calculator.TenDaysAverage(src.get(0)));
			m_tar.saveData(m_adapter, Calculator.TwentyDaysAverage(src.get(0)));
			m_tar.saveData(m_adapter, Calculator.SixtyDaysAverage(src.get(0)));
			status = JobStatus.Succeed;
		} catch (DataIOException e) {
			status = JobStatus.Failed;
			this.setMessage("IO failed for "+m_mapping.toString() +" "+e.getMessage());
			return CalculationResultType.InvalidInput;
		} catch (CalculationException e) {
			status = JobStatus.Failed;
			this.setMessage("Calculation failed for "+m_mapping.toString() +" "+e.getMessage());
			return CalculationResultType.Failed;
		}
		finally
		{
			this.setStatus(status);
		}
		return CalculationResultType.Succeed;
	}

	@Override
	public boolean handleResult(Object result) throws JobOperationException {
		System.out.println(m_mapping.toString()+"\t"+((CalculationResultType)result).toString());
		return true;
	}

}
