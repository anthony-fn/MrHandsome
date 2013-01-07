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

import com.anthony.playstation.exceptions.ConfigurationException;

public abstract class UniformTypeDB
{
	private static Map<Integer, UniformType> m_types = new HashMap<Integer, UniformType>();
	private static boolean m_inited = false;
	
	
	public synchronized static void load( String file ) throws ConfigurationException
	{
		if( m_inited )
			return;
		DocumentBuilderFactory factory ;
		DocumentBuilder builder;
		try
		{
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse(file);
			doc.normalize();
			NodeList typeList = doc.getElementsByTagName("Type");
			for( int i = 0; i < typeList.getLength(); i ++ )
			{
				Element unit = (Element)typeList.item(i);
				int unitID = Integer.parseInt(unit.getAttribute("id"));
				String unitType = unit.getAttribute("type");
				String unitName = unit.getTextContent();
				
				if( m_types.containsKey(unitID))
					throw new ConfigurationException("Duplicate UniformType ID: "+unitID+" .");
				
				UniformType uni = new UniformType( unitID, unitName, unitType);
				m_types.put(unitID, uni);
			}
		} catch (ParserConfigurationException e)
		{
			throw new ConfigurationException("Can't init UniformTypeDB",e);
		} catch (SAXException e)
		{
			throw new ConfigurationException("Can't init UniformTypeDB",e);
		} catch (IOException e)
		{
			throw new ConfigurationException("Can't init UniformTypeDB",e);
		}
		
		
		m_inited = true;
		
	}
	
	public static UniformType getType( int type ) throws ConfigurationException
	{
		if( !m_inited )
			throw new ConfigurationException("UniformTypeDB hasn't been inited!");
		return m_types.get(type);
	}
}
