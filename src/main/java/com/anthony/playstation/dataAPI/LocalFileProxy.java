package com.anthony.playstation.dataAPI;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.exceptions.DataIOException;

public class LocalFileProxy extends ADataIOProxy{

	private String m_prefix = "";
	
	private String getFileName( MappingInfo mapping )
	{
		StringBuilder sb = new StringBuilder();
		sb.append(m_prefix+"/");
		
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
	public LocalFileProxy( String prefix )
	{
		m_prefix = prefix;
	}
	
	public byte[] loadDataTemp( File datafile, MappingInfo mapping) throws DataIOException
	{
		byte [] result = null;
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
	public byte[] loadData(MappingInfo mapping) throws DataIOException {
		byte[] result = null;
		File datafile = new File(this.getFileName(mapping));
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
		
		File datafile = new File(this.getFileName(mapping));
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
			fos.flush();
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
