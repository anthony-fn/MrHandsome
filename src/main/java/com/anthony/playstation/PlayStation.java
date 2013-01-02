/*
 * 
 */
package com.anthony.playstation;

import java.io.File;
import java.util.List;

//import mstar.production.common.*;

import org.apache.log4j.Logger;

import com.anthony.playstation.configuration.ChinaEquity;
import com.anthony.playstation.configuration.ChinaEquityDataDescription;
import com.anthony.playstation.configuration.ChinaEquityMarket;
import com.anthony.playstation.data.MappingInfo;
import com.anthony.playstation.data.MappingType;
import com.anthony.playstation.dataAPI.ADataIOProxy;
import com.anthony.playstation.dataAPI.ADataIOProxyFactory;
import com.anthony.playstation.dataAPI.LocalFileProxy;
import com.anthony.playstation.dataAPI.LocalFileProxyFactory;
import com.anthony.playstation.exceptions.ConfigurationException;
import com.anthony.playstation.exceptions.DataIOException;
//import mstar.production.common.*;
import com.anthony.playstation.exceptions.DataProxyOperationException;


// TODO: Auto-generated Javadoc
/**
 * The Class PlayStation.
 */
public class PlayStation
{
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(PlayStation.class);
	//private static DataDumpForTSDB m_dumper = new DataDumpForTSDB();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		logger.info("This is the start of a beautiful journey!");
		
		//String dataSource = ConfigManager.getInstance().getString("DataSource", "abc");
		//String dataTarget = ConfigManager.getInstance().getString("DataTarget", "def");
		
		//logger.info("Data Source: "+dataSource);
		//logger.info("Data Target: "+dataTarget);
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
			ADataIOProxyFactory factory = new LocalFileProxyFactory("Data");
			ADataIOProxy proxy = factory.getDataProxy();
			
			for( ChinaEquity equity : list )
			{
				++index;
				MappingInfo mapping = new MappingInfo();
				
				mapping.setObjectId(equity.getPerformanceID());
				mapping.setTsType(2);
				
				mapping.setMapping(MappingType.MappingBaseObject);
				
				String filePath = "Data/"+mapping.getObjectId();
				
				File datafile = new File(filePath+"-base");
				byte[] content = ((LocalFileProxy)proxy).loadDataTemp(datafile, mapping);
				proxy.saveData(mapping, content);
				datafile.delete();
				
				datafile = new File(filePath+"-corp");
				mapping.setMapping(MappingType.MappingCorporateActionAdjustment);
				content = ((LocalFileProxy)proxy).loadDataTemp(datafile, mapping);
				proxy.saveData(mapping, content);
				datafile.delete();
				
				//m_dumper.DumpPriceToLocal( equity.getPerformanceID(), 2);
				logger.info("Data dumped "+index+"/"+totalNum);
				
			}
			factory.closeFactory();
			//m_dumper.Dispose();
			logger.info("Finished!");
			
		} catch (ConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataProxyOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
