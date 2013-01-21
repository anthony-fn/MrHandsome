package com.anthony.playstation.executor;

import com.anthony.playstation.data.mapping.UniformMapping;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.JobOperationException;

public class NumberedAverageJobFactory extends AJobFactory{

	private ADataIOProxyFactory m_source = null;
	private ADataIOProxyFactory m_target = null;
	public NumberedAverageJobFactory(  ADataIOProxyFactory source, ADataIOProxyFactory target  )
	{
		m_source = source;
		m_target = target;
	}
	@Override
	public AJob getOneJob(Object obj) throws JobOperationException {
		
		UniformMapping mapping = (UniformMapping)obj;
		AJob result = null;
		try {
			 result = new NumberedAverageJob(m_source.getDataProxy(), m_target.getDataProxy(), 
					mapping);
		} catch (DataProxyOperationException e) {
			throw new JobOperationException("Can't get a NumberedAverageJob for "+mapping.toString(), new Exception (e));
		}
		
		return result;
	}

}
