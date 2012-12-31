package com.anthony.playstation.dataDump;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.anthony.playstation.dataDump.Exceptions.WriteDataFailure;
import com.morningstar.data.tsapi.BaseObject;
import com.morningstar.data.tsapi.CorporateActionAdjustment;
import com.morningstar.data.tsapi.TSException;
import com.morningstar.data.tsapi.tscontext;

public class DataDumpForTSDB
{
	private  static tscontext context_r = null;
	private  static tscontext context_w = null;
	private  BaseObject ts_r = null;
	private  BaseObject ts_w = null;
	private  CorporateActionAdjustment ts_r_cor = null;
	private  CorporateActionAdjustment ts_w_cor = null;
	
	
	private static final Integer m_lock = 2013;
	
	public DataDumpForTSDB()
	{
		synchronized(m_lock){
			if(context_r==null){
				try {
					context_r = new tscontext("http://tswriter81/Inte-config/ts_proxy.xml");
					context_w = new tscontext("http://tsdevwriter81/Inte-config/ts_proxy.xml");
				} catch (TSException e) {
					// TODO Auto-generated catch block
					System.out.println("TestAggregates()"+e.getErrorInfo());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		ts_r = new BaseObject(context_r);
		ts_w = new BaseObject(context_w);
		ts_r_cor = new CorporateActionAdjustment(context_r);
		ts_w_cor = new CorporateActionAdjustment(context_w);
		
	}
	
	public void DumpPriceToTSDB( String object, int tstype ) throws WriteDataFailure
	{
		try
		{
			byte [] unzipped = ts_r.load_ts_content(tstype, object);
			int result = ts_w.save_ts_content(tstype, object, unzipped);
			
			if( result != 0 )
				throw new WriteDataFailure( "Dump data failed to DEV tsdb. With error No. "+result+" !");
				
		} catch (TSException e)
		{
			throw new WriteDataFailure("Dump data failed to DEV tsdb. With error No. "+e.getErrorCode()+"\nError message"+e.getErrorInfo()+" !");
		}
	}
	
	public void DumpPriceToLocal( String object, int tstype )
	{
		try
		{
			byte[] unzipped = ts_r.load_ts_content(tstype, object);
			String filePath = "Data/"+object;
			
			File datafile = new File(filePath+"-base");
			
			if( datafile.exists() )
			{
				datafile.delete();
			}
			
			datafile.createNewFile();
			FileOutputStream fos = new FileOutputStream(datafile, true );
			fos.write(unzipped);
			fos.flush();
			fos.close();
			
			unzipped = ts_r_cor.load_ts_content(tstype, object);
			datafile = new File(filePath+"-corp");
			if( datafile.exists() )
			{
				datafile.delete();
			}
			
			datafile.createNewFile();
			fos = new FileOutputStream(datafile, true );
			fos.write(unzipped);
			fos.flush();
			fos.close();
		} catch (TSException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Dispose()
	{
		try
		{
			context_r.dispose();
			context_w.dispose();
		} catch (TSException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
