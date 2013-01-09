/**   
* @Title: 		ChinaEquityDataDescription.java 
* @Package 		com.anthony.playstation.configuration 
* @Description:  
* 				The defination for class ChinaEquityDataDescription.
* @author 		Anthony Fan
* @date 		2013-1-8 
* @time 		23:54:06 
* @version 		V 1.0   
*/
package com.anthony.playstation.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.anthony.playstation.exceptions.ConfigurationException;


/**
 * The Class ChinaEquityDataDescription is a DB like class.
 * It contains all the China Equity Market related data points in TSDB, including the mapping types for each data point. 
 */
public class ChinaEquityDataDescription
{
	
	/** If the Map of data point mapping information has been initialized. */
	private static boolean m_inited = false;
	
	/** A hash map which contains all the data points and related mapping type in TSDB
	 *  Key : 	TSTypeID
	 *  Value: 	A list of mapping types for the specific TSType in key.
	 *  
	 *  Valid Strings in Value so far
	 *  1	-	BaseObject
	 *  2	-	CorporateActionAdjustment*/
	private static Map<String, List<String>> m_members = new HashMap<String, List<String>>();
	
	/** TSType numbers in the hash map */
	private static int m_number = 0;
	
	/**
	 * Instantiates a new China equity data description.
	 * 
	 * @param fileName
	 *            The configuration file path.
	 * @throws ConfigurationException
	 *            Any kinds of exception while loading/parsing.
	 */
	public ChinaEquityDataDescription( String fileName ) throws ConfigurationException
	{
		//Single instance only.
		if( m_inited )
			return;
		initMembers(fileName);
	}
	
	
	/**
	 * Initialize the members from the configuration file.
	 * 
	 * @param fileName
	 *            The configuration file path.
	 * @throws ConfigurationException
	 *            Any kinds of exception while loading/parsing.
	 */
	private void initMembers( String fileName ) throws ConfigurationException
	{
		//The file should lie in the resource folder
		InputStream stream = ChinaEquityDataDescription.class.getClassLoader().getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		String line = "";
		
		try
		{
			while( (line = reader.readLine()) != null )
			{
				//Ignore the lines started with "#" in the configuration file.
				if( line.startsWith("#"))
					continue;
				
				String[] temp = line.split("\t");
				
				if( m_members.containsKey(temp[0]))
				{
					//If the key is already in the map, check if the mapping in this line has been added to the map-value list.
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
					//Can't find the key in map, then add a new unit.
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
	
	/**
	 * Reset the hash map. 
	 * Don't use it directly.
	 */
	public void Reset()
	{
		m_members.clear();
		m_number = m_members.size();
		m_inited = false;
	}

	/**
	 * Gets the data type number.
	 * 
	 * @return TSType numbers in the hash map
	 */
	public int getDataTypeNumber()
	{
		return m_number;
	}
}
