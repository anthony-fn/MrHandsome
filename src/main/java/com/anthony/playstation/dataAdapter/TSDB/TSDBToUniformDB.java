/**   
* @Title: 		TSDBToUniformDB.java
* @Package 		com.anthony.playstation.dataAdapter.TSDB
* @Description: 
* 				The DB like class TSDBToUniformDB, and the key class TSDBToUniformDBKey
* @author 		Anthony Fan
* @date 		2013-1-14 
* @time 		14:04:53
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAdapter.TSDB;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mstar.production.common.ConfigManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.exceptions.ConfigurationException;

/**
 * Used in the Map inside TSDBToUniform
 */
class TSDBToUniformDBKey{
	
	/**
	 * Field m_tstype.
	 */
	private int m_tstype = 0;
	/**
	 * Field m_type.
	 */
	private MappingType m_type = MappingType.MappingBaseObject;
	
	/**
	 * Constructor for TSDBToUniformDBKey.
	 * @param tstype int
	 * @param mappingType MappingType
	 */
	TSDBToUniformDBKey( int tstype, MappingType mappingType )
	{
		m_tstype = tstype;
		m_type = mappingType;
	}
	
	/**
	 * Method getTSType.
	 * @return int
	 */
	int getTSType()
	{
		return m_tstype;
	}
	
	/**
	 * Method getTSMappingType.
	 * @return MappingType
	 */
	MappingType getTSMappingType()
	{
		return m_type;
	}
	
	/**
	 * Method equals.
	 * @param key TSDBToUniformDBKey
	 * @return boolean
	 */
	boolean equals(TSDBToUniformDBKey key )
	{
		if( (m_tstype == key.getTSType()) && (m_type == key.getTSMappingType()) )
			return true;
		else 
			return false;
	}
	
	/**
	 * Method toString.
	 * @return String
	 */
	public String toString()
	{
		return m_type.toString()+"\t"+m_tstype;
	}
}

/**
 * DB like class which contains all the mapping information between TSDB's data format and the uniformed data type.
 */
public abstract class TSDBToUniformDB
{
	/**
	 * Field m_list.
	 * Value: {@value m_list}
	 */
	private static Map<String, TSDBToUniform> m_list = new HashMap<String, TSDBToUniform>();
	/**
	 * Field m_inited.
	 * Value: {@value m_inited}
	 */
	private static boolean m_inited = false;
	
	/**
	 * Method getMappingType.
	 * @param type String
	 * @return MappingType
	 * @throws ConfigurationException
	 */
	private static MappingType getMappingType( String type ) throws ConfigurationException
	{
		if( type == null || type.isEmpty() )
			throw new ConfigurationException("Invalide mappingType input "+type);
		
		if( type.equals("BaseObject"))
			return MappingType.MappingBaseObject;
		else if ( type.equals("Corporation"))
			return MappingType.MappingCorporateActionAdjustment;
		else
			throw new ConfigurationException("Invalide mappingType input "+type);
	}
	
	/**
	 * Method getUniformType.
	 * @param tsType int
	 * @param type MappingType
	 * @return TSDBToUniform
	 * @throws ConfigurationException
	 */
	public static TSDBToUniform getUniformType( int tsType, MappingType type ) throws ConfigurationException
	{
		if( !m_inited )
		{
			TSDBToUniformDB.load();
		}
		TSDBToUniformDBKey key = new TSDBToUniformDBKey( tsType, type );
		return m_list.get(key.toString());
	}
	
	/**
	 * Method load.
	 * @throws ConfigurationException
	 */
	private synchronized static void load () throws ConfigurationException
	{
		String fileName = ConfigManager.getInstance().getString("TSDBMappingtoUniform");
		if(m_inited)
			return;
		
		DocumentBuilderFactory factory ;
		DocumentBuilder builder;		
			
		try
		{
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fileName);
			doc.normalize();
			
			NodeList typeList = doc.getElementsByTagName("TSType");
			
			for( int i = 0; i < typeList.getLength(); i ++ )
			{
				Element unit = (Element)typeList.item(i);
				int tsType = Integer.parseInt(unit.getAttribute("tsType"));
				String unitType = unit.getAttribute("name");
				String className = unit.getAttribute("className");
				
				NodeList mappingList = unit.getElementsByTagName("Mapping");
				
				for( int j = 0; j < mappingList.getLength(); j ++ )
				{
					Element mapp = (Element)mappingList.item(j);
					
					String mappingType = mapp.getAttribute("type");
					String unitTypeList = mapp.getTextContent();
					MappingType tsMappingType = TSDBToUniformDB.getMappingType(mappingType);
					TSDBToUniformDBKey key = new TSDBToUniformDBKey(tsType, tsMappingType);
					
					if( m_list.containsKey(key) )
						throw new ConfigurationException("Duplicated defination for TSDBToUniform in "+fileName+" for "
								+key.toString());
					else{
						
						TSDBToUniform toUniform = new TSDBToUniform(tsType, unitType, tsMappingType, className);
						toUniform.addUniformTypes(unitTypeList);
						m_list.put(key.toString(), toUniform);
					}						
				}
				
			}
		} catch (ParserConfigurationException e)
		{
			throw new ConfigurationException("Can't init TSDBToUniformDB "+e.getMessage(), e);
		} catch (SAXException e)
		{
			throw new ConfigurationException("Can't init TSDBToUniformDB "+e.getMessage(), e);
		} catch (IOException e)
		{
			throw new ConfigurationException("Can't init TSDBToUniformDB "+e.getMessage(), e);
		}
		m_inited = true;
			
	}
}
