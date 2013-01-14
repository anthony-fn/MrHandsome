package com.anthony.playstation.dataAdaptertest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import mstar.production.common.ConfigManager;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.StringDataUnit;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;


public class ProtoBufAdapterTest
{
	private static UniformType m_valueType = null;
	private static DataSeries m_valueSeries = null;
	private static UniformType m_stringType = null;
	private static DataSeries m_stringSeries = null;
	private static ADataIOProxyFactory m_factory = null;
	private static ADataIOProxy m_proxy = null;
	
	private static ProtoBufAdapter m_adapter = new ProtoBufAdapter();
	private static final Logger logger = Logger.getLogger(ProtoBufAdapterTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		try {
			m_factory = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			m_proxy = m_factory.getDataProxy();
			
			m_valueType = UniformTypeDB.getType(10000);
			m_valueSeries = new DataSeries(m_valueType, "ValueTest");
			m_stringType = UniformTypeDB.getType(10001);
			m_stringSeries = new DataSeries( m_stringType, "StringTest");
			
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-01", 12));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-02", 13));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-03", 14));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-04", 15));
			m_valueSeries.addUnit(new ValueDataUnit("1901-01-05", 16));
			
			m_stringSeries.addUnit(new StringDataUnit("1901-01-01", "a"));
			m_stringSeries.addUnit(new StringDataUnit("1901-01-02", "b"));
			m_stringSeries.addUnit(new StringDataUnit("1901-01-03", "c"));
			m_stringSeries.addUnit(new StringDataUnit("1901-01-04", "d"));
			m_stringSeries.addUnit(new StringDataUnit("1901-01-05", "e"));
			
		} catch (InvalidDataUnitException e) {
			fail(e.getMessage());
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
		
		logger.info("*****Start to test class ProtoBufAdapterTest*****");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		try {
			m_factory.closeFactory();
		} catch (DataProxyOperationException e) {
			fail(e.getMessage());
		}
		logger.info("*****Finished testing class ProtoBufAdapterTest*****");
	}

	private boolean compareBytes( byte[] src, byte[] tar )
	{
		if( src == null || tar == null )
			return false;
		
		else if ( src.length != tar.length)
			return false;
		
		for( int i = 0; i < src.length; i ++ )
		{
			if( src[i] != tar[i])
				return false;
		}
		
		return true;
	}
	private void testSerialize( DataSeries series )
	{
		String name = "DataStorage/"+series.getFileName();
		File datafile = new File(name);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(datafile);
			ByteArrayOutputStream resultOut=new ByteArrayOutputStream(1024); 
			byte[] temp=new byte[1024];  
		          
		    int size=0;  
		          
		    while((size=fis.read(temp))!=-1)  
		    {
		    	resultOut.write(temp,0,size); 
		    }  
		    
		    byte [] src = m_adapter.serializeSeries(series);
		    if( !compareBytes(src, resultOut.toByteArray()));
		    fis.close();
		        
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (DataAdapterException e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testSerializeSeries()
	{
		testSerialize(m_valueSeries);
		testSerialize(m_stringSeries);
	}

	@Test
	public void testLoadSeries()
	{
		try {
			List<DataSeries> value = m_proxy.loadData("ValueTest", m_valueType, m_adapter);
			List<DataSeries> string = m_proxy.loadData("StringTest", m_stringType, m_adapter);
			
			if( value == null || value.size()!= 1 || !m_valueSeries.equals(value.get(0)) )
				fail("Invalid data read for ValueTest");
			if( string == null || string.size() != 1 || !m_stringSeries.equals(string.get(0)))
				fail("Invalid data read for StringTest");
			
			
		} catch (DataIOException e) {
			fail(e.getMessage());
		} catch (InvalidDataUnitException e) {
			fail("DataSeries unequal "+e.getMessage());
		}
	}

}
