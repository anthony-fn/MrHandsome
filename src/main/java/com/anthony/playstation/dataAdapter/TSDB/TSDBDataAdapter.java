package com.anthony.playstation.dataAdapter.TSDB;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.anthony.playstation.TSDBToUniform;
import com.anthony.playstation.TSDBToUniformDB;
import com.anthony.playstation.UniformTypeDB;
import com.anthony.playstation.data.DataSeries;
import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.data.ValueDataUnit;
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
	
	//private MappingType m_mappingType = null;
	
	//public TSDBDataAdapter()
	//{}
	//public TSDBDataAdapter( MappingType mappingType )
	//{
	//	m_mappingType = mappingType;
	//}
	
	public static List<DataSeries> loadSeries( TSDBToUniform tsdbToUni, MappingInfo mapping, byte[] content ) throws DataAdapterException
	{
		/*if( tsdbToUni.getMappingType() != m_mappingType )
			throw new DataAdapterException("Mismatch of mapping type. Adapter with"+ m_mappingType.toString()
					+" but input mapping is "+tsdbToUni.getMappingType().toString());
		*/
		Class<?> classObject = null;
		List<DataSeries> result = new LinkedList<DataSeries>();
		try
		{
			classObject = Class
					.forName("com.morningstar.data.tsapi.blobData.BlobDLLLL");
			CompressionPackage cp = GZipAgent.Decompress(content);
			@SuppressWarnings("unchecked")
			List<BaseBlob> tempList = BaseBlob.Parse(cp.GetUnzippedData(), (Class<? extends BaseBlob>) classObject);
			
			Object[] abc = (Object[]) values.get(tempList.get(0));
			
			List<Integer> unilist = tsdbToUni.getUniformList();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	@SuppressWarnings("unchecked")
	public List<DataSeries> loadSeries( MappingInfo mapping, byte[] content ) throws DataAdapterException
	{
		return null;
		/*if( mapping.getMapping() != m_mappingType )
			throw new DataAdapterException("Mismatch of mapping type. Adapter with"+ m_mappingType.toString()
					+" but input mapping is "+mapping.getMapping().toString());
		
		Class<?> classObject = null;
		List<DataSeries> result = new LinkedList<DataSeries>();
		try
		{
			classObject = Class
					.forName("com.morningstar.data.tsapi.blobData.BlobDLLLL");
			CompressionPackage cp = GZipAgent.Decompress(content);
			List<BaseBlob> tempList = BaseBlob.Parse(cp.GetUnzippedData(), (Class<? extends BaseBlob>) classObject);
			
			Object[] abc = (Object[]) values.get(tempList.get(0));
			
			for( int i = 0; i < abc.length-1; i++)
			{
				result.add(new DataSeries(""));
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
		}
		
		return result;*/
		
	}

	@Override
	public List<DataSeries> parseData(byte[] content) throws DataAdapterException
	{
		// TODO Auto-generated method stub
		return null;
	}
}
