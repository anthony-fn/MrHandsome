package com.anthony.playstation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.exceptions.ConfigurationException;

class TSDBToUniformDBKey{
	
	private int m_tstype = 0;
	private MappingType m_type = MappingType.MappingBaseObject;
	
	TSDBToUniformDBKey( int tstype, MappingType mappingType )
	{
		m_tstype = tstype;
		m_type = mappingType;
	}
	
	int getTSType()
	{
		return m_tstype;
	}
	
	MappingType getTSMappingType()
	{
		return m_type;
	}
	
	boolean equals(TSDBToUniformDBKey key )
	{
		if( (m_tstype == key.getTSType()) && (m_type == key.getTSMappingType()) )
			return true;
		else 
			return false;
	}
	
	public String toString()
	{
		return m_type.toString()+"\t"+m_tstype;
	}
}

public abstract class TSDBToUniformDB
{
	private static Map<String, TSDBToUniform> m_list = new HashMap<String, TSDBToUniform>();
	private static boolean m_inited = false;
	
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
	
	public static TSDBToUniform getUniformType( int tsType, MappingType type ) throws ConfigurationException
	{
		if( !m_inited )
			throw new ConfigurationException("TSDBToUniformDB hasn't been inited!");
		TSDBToUniformDBKey key = new TSDBToUniformDBKey( tsType, type );
		return m_list.get(key.toString());
	}
	
	public synchronized static void load( String fileName ) throws ConfigurationException
	{
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
						
						TSDBToUniform toUniform = new TSDBToUniform(tsType, unitType, tsMappingType);
						toUniform.addUniformTypes(unitTypeList);
						m_list.put(key.toString(), toUniform);
					}						
				}
				
			}
		} catch (ParserConfigurationException e)
		{
			throw new ConfigurationException("Can't init TSDBToUniformDB .", e);
		} catch (SAXException e)
		{
			throw new ConfigurationException("Can't init TSDBToUniformDB .", e);
		} catch (IOException e)
		{
			throw new ConfigurationException("Can't init TSDBToUniformDB .", e);
		}
		m_inited = true;
			
	}
}
