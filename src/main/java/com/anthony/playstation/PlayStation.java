package com.anthony.playstation;

import java.util.List;

import mstar.production.common.ConfigManager;

import org.apache.log4j.Logger;

import com.anthony.playstation.configuration.ChinaEquity;
import com.anthony.playstation.configuration.ChinaEquityDataDescription;
import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.configuration.Exceptions.ConfigurationException;
import com.anthony.playstation.dataDump.DataDumpForTSDB;


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

		String dataSource = ConfigManager.getInstance().getString("DataSource", "abc");
		String dataTarget = ConfigManager.getInstance().getString("DataTarget", "def");
		
		logger.info("Data Source: "+dataSource);
		logger.info("Data Target: "+dataTarget);
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
