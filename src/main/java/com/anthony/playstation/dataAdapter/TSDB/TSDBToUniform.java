/**   
* @Title: 		TSDBToUniform.java
* @Package 		com.anthony.playstation.dataAdapter.TSDB
* @Description: 
* 				Class TSDBToUniform
* @author 		Anthony Fan
* @date 		2013-1-14 
* @time 		10:46:03
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAdapter.TSDB;

import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.exceptions.ConfigurationException;

/**
 * Class TSDBToUniform is a mapping class which indicates the data relation between TSDB's data point and uniformed data points.
 * Each instance represents one TSDB data point (TSType).
 * The overall mapping information locates in DataDefinition/TSDBToUniform.xml
 * 
 * More contents should be added to this class while new mapping types are added.
 */
public class TSDBToUniform
{
	/**
	 * Field m_tstype.	TSType ID
	 */
	private int m_tstype = 0;
	
	/**
	 * Field m_name.	TSType name
	 */
	private String m_name = "";
	
	/**
	 * Field m_mapping.	TSDB mapping type
	 */
	private MappingType m_mapping = MappingType.MappingBaseObject;
	
	/**
	 * Field m_className.	The class's name for this data type defined in OldTSAPI.
	 */
	private String m_className = "";
	
	/**
	 * Field m_list.		The list of uniform data types.
	 */
	private List<Integer> m_list = new LinkedList<Integer>();
	
	/**
	 * Constructor for TSDBToUniform.
	 * @param tstype int
	 * @param name String
	 * @param type MappingType
	 * @param className String
	 */
	public TSDBToUniform( int tstype, String name, MappingType type, String className )
	{
		m_tstype = tstype;
		setName(name);
		setMappingType(type);
		setClassName(className);
	}
	
	/**
	 * Method addUniformType.
	 * @param i int
	 * @throws ConfigurationException.	Duplicated uniform type IDs are added.
	 */
	public void addUniformType( int i ) throws ConfigurationException
	{
		if( m_list.contains(i))
			throw new ConfigurationException( "The defination for TSType="+m_tstype+" has already included UniformType "+i+".");
		
		m_list.add(i);
	}
	
	/**
	 * Method addUniformTypes.
	 * The argument should be a "," separated list, and each unit should be a number.
	 *  
	 * @param types String
	 * @throws ConfigurationException
	 */
	public void addUniformTypes( String types ) throws ConfigurationException
	{
		String [] typeList = types.split(",");
		
		for( int i = 0; i < typeList.length; i ++ )
		{
			
			this.addUniformType(Integer.parseInt(typeList[i]));
			
		}
	}

	/**
	 * Method getUniformList.
	 * @return List<Integer>
	 */
	public List<Integer> getUniformList()
	{
		return m_list;
	}
	
	/**
	 * Method getName.
	 * @return String
	 */
	public String getName()
	{
		return m_name;
	}

	/**
	 * Method setName.
	 * @param m_name String
	 */
	public void setName(String m_name)
	{
		this.m_name = m_name;
	}

	/**
	 * Method getMappingType.
	 * @return MappingType
	 */
	public MappingType getMappingType()
	{
		return m_mapping;
	}

	/**
	 * Method setMappingType.
	 * @param m_mapping MappingType
	 */
	public void setMappingType(MappingType m_mapping)
	{
		this.m_mapping = m_mapping;
	}

	/**
	 * Method getClassName.
	 * @return String
	 */
	public String getClassName()
	{
		return m_className;
	}

	/**
	 * Method setClassName.
	 * @param m_className String
	 */
	public void setClassName(String m_className)
	{
		this.m_className = m_className;
	}
}
