/**   
* @Title: 		DataDumpJobFactory.java
* @Package 		com.anthony.playstation.executor
* @Description: 
* 				Definition for DataDumpJobFactory, which is a derived class from AJobFactory.
* @author 		Anthony Fan
* @date 		2013-1-16 
* @time 		17:00:32
* @version 		V 1.0   
*/
package com.anthony.playstation.executor;

import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.JobOperationException;

/**
 */
public class DataDumpJobFactory extends AJobFactory
{
	/**
	 * Field m_source.
	 * Value: {@value m_source}
	 */
	private static ADataIOProxyFactory m_source = null;
	/**
	 * Field m_target.
	 * Value: {@value m_target}
	 */
	private static ADataIOProxyFactory m_target = null;
	/**
	 * Field m_mapping.
	 */
	private MappingInfo m_mapping = null;
	
	/**
	 * Constructor for DataDumpJobFactory.
	 * @param source ADataIOProxyFactory
	 * @param target ADataIOProxyFactory
	 */
	public DataDumpJobFactory( ADataIOProxyFactory source, ADataIOProxyFactory target )
	{
		if( m_source == null)
			m_source = source;
		if( m_target == null )
			m_target = target; 
	}
	
	
	/**
	 * Method getOneJob.
	 * @param mapping Object
	 * @return AJob
	 * @throws JobOperationException
	 */
	@Override
	public AJob getOneJob( Object mapping ) throws JobOperationException
	{
		DataDumpJob job = null;
		m_mapping = (MappingInfo) mapping;
		try
		{
			job = new DataDumpJob(m_source.getDataProxy(), m_target.getDataProxy(), m_mapping);			
		} catch (DataProxyOperationException e)
		{
			throw new JobOperationException("Job creation exception. Error: "+e.getMessage(), 
					new Exception(e));
		}
		return job;
	}	
}
