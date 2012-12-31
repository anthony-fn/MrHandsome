package com.anthony.playstation.dataDump;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.anthony.playstation.dataDump.Exceptions.WriteDataFailure;

public class DataDumpForTSDBTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@Test
	public void testDumpPrice()
	{
		DataDumpForTSDB test = new DataDumpForTSDB();
		
		try
		{
			test.DumpPriceToTSDB("0P000080EB", 2);
			
		} catch (WriteDataFailure e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
