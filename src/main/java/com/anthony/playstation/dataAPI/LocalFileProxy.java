/**   
* @Title: 		LocalFileProxy.java 
* @Package 		com.anthony.playstation.dataAPI 
* @Description:  
* 				Contains the definition of class LocalFileProxy
* @author 		Anthony Fan
* @date 		2013-1-12 
* @time 		23:26:56 
* @version 		V 1.0   
*/
package com.anthony.playstation.dataAPI;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.anthony.playstation.data.dataseries.DataSeries;
import com.anthony.playstation.data.dataseries.UniformType;
import com.anthony.playstation.dataAdapter.ADataAdapter;
import com.anthony.playstation.exceptions.DataAdapterException;
import com.anthony.playstation.exceptions.DataIOException;

/**
 * Class LocalFileProxy, which is derived class from ADataIOProxy
 * @author Anthony
 * @version $Revision: 1.0 $
 */
public class LocalFileProxy extends ADataIOProxy{

	private String m_srcprefix = "";
	private String m_tarprefix = "";
	
	/**
	 * Constructor for LocalFileProxy.
	
	* @param source String
	}*/
	
	public void setSourceDirectory( String source )
	{
		m_srcprefix = source;
	}
	
	/**
	 * Method setTargetDirectory.
	 * @param target String
	 */
	public void setTargetDirectory( String target )
	{
		m_tarprefix = target;
	}
	
	/**
	 * Method getSourceDirectory.
	 * @return String
	 */
	public String getSourceDirectory()
	{
		return m_srcprefix;
	}
	
	/**
	 * Method getTargetDirectory.
	 * @return String
	 */
	public String getTargetDirectory()
	{
		return m_tarprefix;
	}

	/**
	 * Method loadData.
	 * Load data from local directory.
	 * @param objID String
	 * @param mapping Object	Instance of UniformType.
	 * @param adapter ADataAdapter	
	
	
	 * @return List<DataSeries> * @throws DataIOException * @throws DataIOException
	 */
	@Override
	public List<DataSeries> loadData(String objID, Object mapping, ADataAdapter adapter) throws DataIOException
	{
		if( !( adapter instanceof com.anthony.playstation.dataAdapter.protoBuf.ProtoBufAdapter) )
			throw new DataIOException("Invalid Adapter instance !");
		
		UniformType type = (UniformType)mapping;
		
		String fileName = m_srcprefix+ "/" +objID+"_"+type.getTypeName();
		File datafile = new File(fileName);
		List<DataSeries> resultList = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( datafile );
			ByteArrayOutputStream resultOut=new ByteArrayOutputStream(1024); 
			byte[] temp=new byte[1024];  
	          
	        int size=0;  
	          
	        while((size=fis.read(temp))!=-1)  
	        {  
	            resultOut.write(temp,0,size);  
	        }  
	          
	        fis.close();  
	          
	        resultList = adapter.loadSeries(null, resultOut.toByteArray());
			
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			if( fis != null )
			{
				try{
					fis.close();
				}
				catch(IOException ex )
				{
				}
			}
			throw new DataIOException("Loading data from local file failed for "+fileName, e);
		} catch (DataAdapterException e)
		{
			throw new DataIOException("Loading data from local file failed for "+fileName, new Exception(e));
		}
		return resultList;
	}

	/**
	 * Method saveData.
	 * @param adapter ADataAdapter
	 * @param series DataSeries
	
	
	 * @return int * @throws DataIOException * @throws DataIOException
	 */
	@Override
	public int saveData(ADataAdapter adapter, DataSeries series) throws DataIOException
	{
		String fileName = m_tarprefix+"/"+series.getFileName();
		File datafile = new File(fileName);
		if(datafile.exists())
			datafile.delete();
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			datafile.createNewFile();
			fos = new FileOutputStream(datafile, true );
			bos = new BufferedOutputStream(fos);
			bos.write(adapter.serializeSeries(series));
			bos.flush();
		} catch (IOException e) {
			throw new DataIOException("Saving data to local file failed for "+fileName, e);
		} catch (DataAdapterException e)
		{
			throw new DataIOException("Failed to serilize data "+e.getMessage(), new Exception(e));
		}
		finally{
			try{
				bos.close();
				fos.close();
			} catch( IOException e )
			{
				throw new DataIOException("Closing local file failed for "+fileName, e);
			}
		}
		
		return 0;
	}
}
