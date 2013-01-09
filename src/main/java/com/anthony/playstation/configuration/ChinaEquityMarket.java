/**   
* @Title: 		ChinaEquityMarket.java 
* @Package 		com.anthony.playstation.configuration 
* @Description:  
* 				The defination for class ChinaEquityMarket.
* @author 		Anthony Fan
* @date 		2013-1-9 
* @time 		0:53:49 
* @version 		V 1.0   
*/
package com.anthony.playstation.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


import com.anthony.playstation.exceptions.ConfigurationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ChinaEquityMarket is a DB like class.
 * It contains all the members in Shanghai and Shenzhen Socket exchange market of China.
 */
public class ChinaEquityMarket
{
	
	/** A list that contains all the members in China socket exchange market. */
	private static List<ChinaEquity> m_members= new LinkedList<ChinaEquity>(); 
	
	/** If the List of ChinaEquity has been initialized. */
	private static boolean m_inited = false;
	
	/** ChinaEquity numbers in the list. */
	private static int m_number = 0;
	
	
	/**
	 * Instantiates a ChinaEquity list.
	 * 
	 * @param fileName
	 *            The path of the configuration file.
	 * @throws ConfigurationException
	 *            Any kinds of exception while loading/parsing.
	 */
	public ChinaEquityMarket ( String fileName ) throws ConfigurationException
	{
		if( m_inited )
			return;
		initMembers(fileName);
	}
	
	/**
	 * Gets the member list.
	 * 
	 * @return ChinaEquity member list
	 */
	public List<ChinaEquity> getMemberList()
	{
		return m_members;
	}
	
	/**
	 * Inits the members.
	 * 
	 * @param fileName
	 *            The path of the configuration file.
	 * @throws ConfigurationException
	 *            Any kinds of exception while loading/parsing.
	 */
	private void initMembers( String fileName ) throws ConfigurationException
	{
		//The file should lie in the resource folder
		InputStream stream = ChinaEquityMarket.class.getClassLoader().getResourceAsStream(fileName);
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
				m_members.add(new ChinaEquity(temp[0], temp[1], temp[2]));
			}
			
			reader.close();
		} catch (IOException e)
		{
			try{
				reader.close();
			}
			catch(IOException ex )
			{
				throw new ConfigurationException("Error while reading the market configuration file for ChinaEquityMarket. ");
			}
			throw new ConfigurationException("Error while reading the market configuration file for ChinaEquityMarket. ");
		}
		
		m_number = m_members.size();
		m_inited = true;
	}
	
	/**
	 * Reset.
	 */
	public void Reset()
	{
		m_members.clear();
		m_number = m_members.size();
		m_inited = false;
	}
	
	/**
	 * Gets the equity number.
	 * 
	 * @return the equity number
	 */
	public int getEquityNumber()
	{
		return m_number;
	}
}
