package com.anthony.playstation.dataAdaptertest;

import static org.junit.Assert.*;

import java.util.List;

import mstar.production.common.ConfigManager;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;


public class ProtoBufAdapterTest
{
	private static final Logger logger = Logger.getLogger(ProtoBufAdapterTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		logger.info("*****Start to test class ProtoBufAdapterTest*****");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		logger.info("*****Finished testing class ProtoBufAdapterTest*****");
	}

	@Test
	public void testSerializeSeries()
	{
		MappingInfo mapping = new MappingInfo();
		mapping.setObjectId("0P000084CD");
		mapping.setTsType(2);;
		mapping.setMapping(MappingType.MappingBaseObject);
		
		TSDBProxyFactory tsdbfactory;
		try
		{
			tsdbfactory = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
			LocalFileProxyFactory localfactory = new LocalFileProxyFactory(ConfigManager.getInstance().getString("LocalFile_Source"),
					ConfigManager.getInstance().getString("LocalFile_Target"));
			TSDBDataAdapter adapter = new TSDBDataAdapter();
			ProtoBufAdapter adapter2 = new ProtoBufAdapter(); 
			List<DataSeries> seriesList = tsdbfactory.getDataProxy().loadData(mapping.getObjectId(), mapping, adapter);
			
			for( DataSeries series : seriesList )
			{
				localfactory.getDataProxy().saveData(adapter2, series);
				System.out.println(series.getUniType().getTypeName()+":\t"+series.getSeriesSize());
			}
			
			
			tsdbfactory.closeFactory();
			localfactory.closeFactory();
		} catch (DataProxyOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataIOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testLoadSeries()
	{
		fail("Not yet implemented");
	}
	
	public void test()
	{
		
	}

}
