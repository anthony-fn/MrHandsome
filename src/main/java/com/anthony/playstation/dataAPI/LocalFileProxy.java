package com.anthony.playstation.dataAPI;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.anthony.playstation.UniformType;
import com.anthony.playstation.data.DataSeries;
import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.exceptions.DataIOException;

public class LocalFileProxy extends ADataIOProxy{

	private String m_srcprefix = "";
	private String m_tarprefix = "";
	
	private String getFileName( String prefix, MappingInfo mapping )
	{
		StringBuilder sb = new StringBuilder();
		sb.append(prefix+"/");
		
		switch( mapping.getMapping() )
		{
		case MappingBaseObject:
			sb.append(mapping.getObjectId()+"_"+mapping.getTsType()+"_"+MappingType.MappingBaseObject.toString());
			break;
		case MappingCorporateActionAdjustment:
			sb.append(mapping.getObjectId()+"_"+mapping.getTsType()+"_"+MappingType.MappingCorporateActionAdjustment.toString());
			break;
		default:
			break;
		}
		return sb.toString();
	}
	
	public LocalFileProxy( String srcprefix , String tarprefix)
	{
		if( srcprefix == null )
			m_srcprefix="";
		else
			m_srcprefix = srcprefix;
		
		if( tarprefix == null )
			m_tarprefix="";
		else
			m_tarprefix = tarprefix;
		
	}
	
	public int saveUniformedData( DataSeries series ) throws DataIOException
	{
		String fileName = m_tarprefix+"/"+series.getFileName();
		File dataFile = new File(fileName);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		if(dataFile.exists())
			dataFile.delete();
		
		try
		{
			fos = new FileOutputStream(dataFile);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(series);
			oos.flush();
			
		} catch (FileNotFoundException e)
		{
			throw new DataIOException("Can't create target file for "+series.getPerformanceID()
					+" "+series.getSeriesName()+" .");
		} catch (IOException e)
		{
			throw new DataIOException("Failed to save data for "+series.getPerformanceID()
					+" "+series.getSeriesName()+" .", e);
		}
		
		finally {
			try
			{
				oos.close();
				fos.close();				
			} catch (IOException e)
			{
				throw new DataIOException("Failed to save data for "+series.getPerformanceID()
						+" "+series.getSeriesName()+" .", e);
			}
			
		}
		
		return 0;
	}
	
	public DataSeries loadUniformdData(String objID, UniformType type ) throws DataIOException
	{
		String fileName = m_srcprefix+ "/" +objID+"_"+type.getTypeName();
		File file = new File(fileName);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		DataSeries result = null;
		try
		{
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			result = (DataSeries)ois.readObject();
			
			
		} catch (FileNotFoundException e)
		{
			throw new DataIOException("Can't find source file for "+objID+" "+type.getTypeName()+" .");
		} catch (IOException e)
		{
			throw new DataIOException("Loading data from local file failed for "+objID+" "+type.getTypeName()+" failed.",
					e);
		} catch (ClassNotFoundException e)
		{
			throw new DataIOException("Loading data from local file failed for "+objID+" "+type.getTypeName()+" failed.",
					e);
		}
		finally{
			try
			{
				ois.close();
				fis.close();
			} catch (IOException e)
			{
				throw new DataIOException("Loading data from local file failed for "+objID+" "+type.getTypeName()+" failed.",
						e);
			}
			
		}
		
		return result;
	}
	@Override
	public byte[] loadData(MappingInfo mapping) throws DataIOException {
		byte[] result = null;
		File datafile = new File(this.getFileName(m_srcprefix,mapping));
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
	          
	       result = resultOut.toByteArray();
			
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
			throw new DataIOException("Loading data from local file failed for "+mapping.toString(), e);
		}
		return result;
	}

	@Override
	public int saveData(MappingInfo mapping, byte[] content)
			throws DataIOException {
		
		File datafile = new File(this.getFileName(m_tarprefix,mapping));
		if(datafile.exists())
			datafile.delete();
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			datafile.createNewFile();
			fos = new FileOutputStream(datafile, true );
			bos = new BufferedOutputStream(fos);
			bos.write(content);
			bos.flush();
			//fos.flush();
		} catch (IOException e) {
			throw new DataIOException("Saving data to local file failed for "+mapping.toString(), e);
		}
		finally{
			try{
				bos.close();
				fos.close();
			} catch( IOException e )
			{
				throw new DataIOException("Closing local file failed for "+mapping.toString(), e);
			}
		}
		
		return 0;
	}

}
