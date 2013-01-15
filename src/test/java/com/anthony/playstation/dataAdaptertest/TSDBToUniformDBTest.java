package com.anthony.playstation.dataAdaptertest;

import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;
import com.anthony.playstation.data.mapping.MappingType;
import com.anthony.playstation.dataAdapter.TSDB.TSDBToUniform;
import com.anthony.playstation.dataAdapter.TSDB.TSDBToUniformDB;
import com.anthony.playstation.exceptions.ConfigurationException;

/**
 * The class <code>TSDBToUniformDBTest</code> contains tests for the class <code>{@link TSDBToUniformDB}</code>.
 *
 * @generatedBy CodePro at 13-1-14 下午2:26
 * @author afan
 * @version $Revision: 1.0 $
 */
public class TSDBToUniformDBTest
{
	/**
	 * Run the TSDBToUniform getUniformType(int,MappingType) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 13-1-14 下午2:26
	 */
	@Test
	public void testGetUniformType_1()
		throws Exception
	{
		int tsType = 2;
		MappingType type = MappingType.MappingAggregates;

		TSDBToUniform result;
		try
		{
			result = TSDBToUniformDB.getUniformType(tsType, type);
			assertTrue(result == null);
			
			type = MappingType.MappingBaseObject;
			result = TSDBToUniformDB.getUniformType(tsType, type);
			assertNotNull(result);
			assertEquals( result.getMappingType(), type );
			assertEquals( result.getName(), "tsMarketPrice");
			
			List<Integer> uniTypes = result.getUniformList();
			assertEquals( uniTypes.size(), 4);
			assertTrue( uniTypes.get(0)==1);
			assertTrue( uniTypes.get(1)==2);
			assertTrue( uniTypes.get(2)==3);
			assertTrue( uniTypes.get(3)==4);
			
		} catch (ConfigurationException e)
		{
			fail(e.getMessage());
		}
		
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 13-1-14 下午2:26
	 */
	@Before
	public void setUp()
		throws Exception
	{
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 13-1-14 下午2:26
	 */
	@After
	public void tearDown()
		throws Exception
	{
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 13-1-14 下午2:26
	 */
	public static void main(String[] args)
	{
		new org.junit.runner.JUnitCore().run(TSDBToUniformDBTest.class);
	}
}