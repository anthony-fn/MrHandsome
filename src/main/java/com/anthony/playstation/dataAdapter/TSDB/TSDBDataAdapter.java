package com.anthony.playstation.dataAdapter.TSDB;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformTypeDB;
import com.anthony.playstation.data.dataunit.ValueDataUnit;
import com.anthony.playstation.data.mapping.MappingInfo;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.InitializationException;
import com.anthony.playstation.exceptions.InvalidDataUnitException;
import com.morningstar.data.tsapi.blobData.BaseBlob;
import com.morningstar.data.tsapi.blobData.TpTinyDate;
import com.morningstar.data.tsapi.impl.CompressionPackage;
import com.morningstar.data.tsapi.impl.GZipAgent;

public class TSDBDataAdapter extends ADataAdapter
{
	
	private static Field values = null;
	static {
		try {
			values = BaseBlob.class.getDeclaredField("values");
			values.setAccessible(true);
		} catch (SecurityException e) {
			throw new InitializationException( "Failed to init a TSDBDataAdapter. ", e);
		} catch (NoSuchFieldException e) {
			throw new InitializationException( "Failed to init a TSDBDataAdapter. ", e);
		}
		
	}

	@Override
	public byte[] serializeSeries(DataSeries series) throws DataAdapterException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataSeries> loadSeries(Object mappingInput, byte[] content) throws DataAdapterException
	{
		TSDBToUniform localToUni = null; 
		MappingInfo mapping = (MappingInfo)mappingInput;
		Class<?> classObject = null;
		List<DataSeries> result = new LinkedList<DataSeries>();
		try
		{
			localToUni = TSDBToUniformDB.getUniformType(mapping.getTsType(), mapping.getMapping());
			classObject = Class
					.forName(localToUni.getClassName());
			CompressionPackage cp = GZipAgent.Decompress(content);
			@SuppressWarnings("unchecked")
			List<BaseBlob> tempList = BaseBlob.Parse(cp.GetUnzippedData(), (Class<? extends BaseBlob>) classObject);
			
			Object[] abc = (Object[]) values.get(tempList.get(0));
			
			List<Integer> unilist = localToUni.getUniformList();
			for( int i = 0; i < abc.length-1; i++)
			{
				result.add(new DataSeries(UniformTypeDB.getType(unilist.get(i)),
						mapping.getObjectId()));
			}
			
			for( BaseBlob blob : tempList )
			{
				Object[] val = (Object[]) values.get(blob);
				int size = val.length;
				Object[] data = new Object[size];
				System.arraycopy(val, 0, data, 0, size);
				
				Calendar cal = ((TpTinyDate) data[0]).GetCalendar();
				for( int index = 1; index < size;index ++ )
				{
					result.get(index-1).addUnit(
							new ValueDataUnit( cal, Float.valueOf(Number.class.cast(data[index]).toString())));
				}
			}
		} catch (ClassNotFoundException e)
		{
			throw new DataAdapterException("Faild to transfer data !", e);
		} catch (Exception e)
		{
			throw new DataAdapterException("Faild to transfer data !", e);
		} catch (InvalidDataUnitException e)
		{
			throw new DataAdapterException("Faild to transfer data !", new Exception(e));
		} catch (ConfigurationException e)
		{
			throw new DataAdapterException("Faild to transfer data !", new Exception(e));
		}
		
		return result;		
	}
	
}
