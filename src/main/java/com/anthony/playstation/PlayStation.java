package com.anthony.playstation;

import java.util.List;

import org.apache.log4j.Logger;

import com.anthony.playstation.configuration.ChinaEquity;
import com.anthony.playstation.configuration.ChinaEquityDataDescription;
import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.configuration.Exceptions.ConfigurationException;
import com.anthony.playstation.dataDump.DataDumpForTSDB;
import com.anthony.playstation.dataDump.Exceptions.WriteDataFailure;


public class PlayStation
{
	private static final Logger logger = Logger.getLogger(PlayStation.class);
	private static DataDumpForTSDB m_dumper = new DataDumpForTSDB();

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		logger.info("This is the start of a beautiful journey!");

		try
		{
			ChinaEquityMarket market = new ChinaEquityMarket("MarketDescriptions/ChinaEquity.data");
			ChinaEquityDataDescription desc = new ChinaEquityDataDescription("DataDescriptions/TSDB/ChinaEquity.tsdbtypes");
			
			if( (market.getEquityNumber() <= 0) || (desc.getDataTypeNumber() <= 0) )
			{
				logger.info("No member to deal with!");
				return;
			}
			List<ChinaEquity> list = market.getMemberList();
			int totalNum = market.getEquityNumber();
			int index = 0;
			for( ChinaEquity equity : list )
			{
				++index;
				m_dumper.DumpPriceToLocal( equity.getPerformanceID(), 2);
				logger.info("Data dumped "+index+"/"+totalNum);
				
			}
			
			m_dumper.Dispose();
			logger.info("Finished!");
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
