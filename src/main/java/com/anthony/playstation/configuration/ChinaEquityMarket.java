package com.anthony.playstation.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.anthony.playstation.exceptions.ConfigurationException;

public class ChinaEquityMarket
{
	private static final Logger logger = Logger.getLogger(ChinaEquityMarket.class);
	private static List<ChinaEquity> m_members= new LinkedList<ChinaEquity>(); 
	private static boolean m_inited = false;
	private static int m_number = 0;
	
	
	public ChinaEquityMarket ( String fileName ) throws ConfigurationException
	{
		if( m_inited )
			return;
		initMembers(fileName);
		
		ReportSelf();
	}
	
	public List<ChinaEquity> getMemberList()
	{
		return m_members;
	}
	private void initMembers( String fileName ) throws ConfigurationException
	{
		InputStream stream = ChinaEquityMarket.class.getClassLoader().getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		String line = "";
		
		try
		{
			while( (line = reader.readLine()) != null )
			{
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
	
	public void Reset()
	{
		m_members.clear();
		m_inited = false;
	}
	
	private void ReportSelf()
	{
		logger.info("Init result for China Equity Market: ");
		logger.info("Security number: " + m_members.size());
	}
	
	public void Finalize()
	{
		if( !m_inited )
			return;
		
		for( ChinaEquity unit : m_members )
		{
			if ( unit.getSecurityID().startsWith("1"))
			{
				logger.info(unit.getSecurityname()+"\t"+unit.getSecurityID());
			}
		}
	}
	public int getEquityNumber()
	{
		return m_number;
	}
}
