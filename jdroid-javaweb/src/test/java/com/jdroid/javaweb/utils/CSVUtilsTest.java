package com.jdroid.javaweb.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.testng.annotations.Test;
import com.jdroid.java.collections.Lists;
import com.jdroid.java.exception.UnexpectedException;
import com.jdroid.javaweb.Assert;
import com.jdroid.javaweb.utils.CSVUtils;

/**
 * Tests the {@link CSVUtils} class
 * 
 */
public class CSVUtilsTest {
	
	/**
	 * Tests the {@link CSVUtils#fromCSV(String)} method
	 */
	@Test
	public void fromCSV() {
		String csv = "1,2,3,4\n,5,\n6,\t7\t,8";
		List<String> expected = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8");
		Assert.assertEqualsNoOrder(CSVUtils.fromCSV(csv), expected);
	}
	
	/**
	 * Tests the {@link CSVUtils#fromCSV(InputStream)} method
	 */
	@SuppressWarnings("resource")
	@Test
	public void fromCSVInputStream() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("files/csv_file.csv");
		List<String> expected = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		Assert.assertEqualsNoOrder(CSVUtils.fromCSV(inputStream), expected);
	}
	
	/**
	 * Tests the negative case of {@link CSVUtils#fromCSV(File)} method
	 */
	@Test(expectedExceptions = UnexpectedException.class)
	public void fromCSVFileNegative() {
		File file = new File("someFile.csv");
		CSVUtils.fromCSV(file);
	}
	
	/**
	 * Tests the negative case of {@link CSVUtils#fromCSV(Reader)} method
	 * 
	 * @throws IOException Config exception
	 */
	@Test(expectedExceptions = UnexpectedException.class)
	public void fromCSVReaderNegative() throws IOException {
		Reader reader = new StringReader("");
		reader.close();
		CSVUtils.fromCSV(reader);
	}
	
	/**
	 * Tests the {@link CSVUtils#toCSV(java.util.Collection)} method
	 */
	@Test
	public void toCSV() {
		List<String> values = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		String expected = "1,2,3,4,5,6,7,8,9,10,11";
		org.testng.Assert.assertEquals(CSVUtils.toCSV(values), expected);
	}
	
	/**
	 * Tests the {@link CSVUtils#toCSVFile(java.util.Collection)} method
	 */
	@Test
	public void toCSVFile() {
		List<String> values = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		File file = CSVUtils.toCSVFile(values);
		org.testng.Assert.assertEquals(CSVUtils.fromCSV(file), values);
	}
	
	/**
	 * Tests the {@link CSVUtils#toMultipleColumnCSVFile(List)} method
	 */
	@Test
	public void toMultipleColumnCSVFile() {
		
		List<String[]> values = Lists.newArrayList(new String[] { "1", "a" }, new String[] { "2", "b" }, new String[] {
				"3", "c" });
		File csvFile = CSVUtils.toMultipleColumnCSVFile(values);
		org.testng.Assert.assertEquals(CSVUtils.fromCSV(csvFile), Lists.newArrayList("1", "a", "2", "b", "3", "c"));
	}
	
}
