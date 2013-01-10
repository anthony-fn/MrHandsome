package com.anthony.playstation.unitypedbtest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import mstar.production.common.ConfigManager;

import org.junit.Test;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.dataAPI.TSDBProxyFactory;
import com.anthony.playstation.dataAdapter.TSDB.TSDBDataAdapter;
import com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter;
import com.anthony.playstation.exceptions.DataIOException;
import com.anthony.playstation.exceptions.DataProxyOperationException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;

public class DumpResultTest
{
	@Test
	public void testLoad()
	{
		try
		{
			ADataIOProxyFactory tsdb = new TSDBProxyFactory(ConfigManager.getInstance().getString("TSDB_Source"),
					ConfigManager.getInstance().getString("TSDB_Target"));
			ADataIOProxyFactory local = new LocalFileProxyFactory("DataStorage",
					"DataStorage");
			
			MappingInfo mapping = new MappingInfo();
			mapping.setObjectId("0P0000KWDJ");
			mapping.setTsType(2);
			mapping.setMapping(MappingType.MappingCorporateActionAdjustment);			
			
			TSDBDataAdapter adapter = new TSDBDataAdapter();
			List<DataSeries> lists = tsdb.getDataProxy().loadData(mapping.getObjectId(), mapping, adapter);
			
			DataSeries temp1 = lists.get(1);
			
			ProtoBufAdapter adapterPro = new ProtoBufAdapter();
			List<DataSeries> temp = local.getDataProxy().loadData(mapping.getObjectId(), temp1.getUniType(), adapterPro);
			
			tsdb.closeFactory();
			local.closeFactory();
			assertEquals( true, temp.get(0).equals(temp1));
			
		} catch (DataProxyOperationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataIOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataUnitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
