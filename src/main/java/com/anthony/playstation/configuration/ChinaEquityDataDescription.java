package com.anthony.playstation.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.anthony.playstation.configuration.Exceptions.ConfigurationException;

public class ChinaEquityDataDescription
{
	private static boolean m_inited = false;
	private static Map<String, List<String>> m_members = new HashMap<String, List<String>>();
	private static int m_number = 0;
	private static final Logger logger = Logger.getLogger(ChinaEquityDataDescription.class);
	
	public ChinaEquityDataDescription( String fileName ) throws ConfigurationException
	{
		if( m_inited )
			return;
		initMembers(fileName);
		ReportSelf();
	}
	
	private void initMembers( String fileName ) throws ConfigurationException
	{
		InputStream stream = ChinaEquityDataDescription.class.getClassLoader().getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		String line = "";
		
		try
		{
			while( (line = reader.readLine()) != null )
			{
				String[] temp = line.split("\t");
				if( m_members.containsKey(temp[0]))
				{
					List<String> tempUnit = m_members.get(temp[0]);
					if( tempUnit.contains(temp[1]))
						continue;
					else 
					{
						tempUnit.add(temp[1]);
					}
				}
				else
				{
					List<String> tempUnit = new LinkedList<String>();
					tempUnit.add(temp[1]);
					m_members.put(temp[0], tempUnit);
				}
			}
			
			reader.close();
		} catch (IOException e)
		{
			try{
				reader.close();
			}
			catch(IOException ex )
			{
				throw new ConfigurationException("Error while reading the Data Description configuration file for ChinaEquityMarket. ");
			}
			throw new ConfigurationException("Error while reading the Data Description configuration file for ChinaEquityMarket. ");
		}
		
		m_number = m_members.size();
		m_inited = true;
	}
	
	public void Reset()
	{
		m_members.clear();
		m_inited = false;
	}
	
	private void ReportSelf()
	{
		logger.info("Init result for the data types for China Equity Market: ");
		logger.info("Data Type number: " + m_members.size());
	}
	
	public int getDataTypeNumber()
	{
		return m_number;
	}
}
