package com.jdroid.java.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.jdroid.java.Assert;
import com.jdroid.java.collections.Lists;
import com.jdroid.java.collections.Sets;
import com.jdroid.java.utils.StringUtils;

/**
 * StringUtils Test
 */
public class StringUtilsTest {
	
	/**
	 * @return The different scenarios for test {@link StringUtils#toAlphanumeric(String)}
	 */
	@DataProvider
	public Iterator<Object[]> toAlphanumericProvider() {
		List<Object[]> cases = Lists.newArrayList();
		cases.add(new Object[] { "text", "text" });
		cases.add(new Object[] { "text.", "text" });
		cases.add(new Object[] { "text.\\", "text" });
		cases.add(new Object[] { "?text.\\", "text" });
		cases.add(new Object[] { " ?text.\\", " text" });
		cases.add(new Object[] { "text.\\text2", "texttext2" });
		cases.add(new Object[] { "text.\\text2??", "texttext2" });
		cases.add(new Object[] { "text.\\text2??text3", "texttext2text3" });
		cases.add(new Object[] { "text .\\text2 ??text3", "text text2 text3" });
		cases.add(new Object[] { "text text2 text3", "text text2 text3" });
		cases.add(new Object[] { "text  text2", "text  text2" });
		return cases.iterator();
	}
	
	/**
	 * @param text The text to transform
	 * @param expectedText The expected transformed text
	 */
	@Test(dataProvider = "toAlphanumericProvider")
	public void toAlphanumeric(String text, String expectedText) {
		org.testng.Assert.assertEquals(StringUtils.toAlphanumeric(text), expectedText);
	}
	
	/**
	 * @return The different scenarios for test {@link StringUtils#truncate(String, Integer)}
	 */
	@DataProvider
	public Iterator<Object[]> truncateProvider() {
		List<Object[]> cases = Lists.newArrayList();
		cases.add(new Object[] { null, 5, null, null });
		cases.add(new Object[] { "", 5, "", "" });
		cases.add(new Object[] { "text1", 1, "t", "" });
		cases.add(new Object[] { "text1", 2, "te", "" });
		cases.add(new Object[] { "text1", 3, "tex", "" });
		cases.add(new Object[] { "text1", 4, "t...", "" });
		cases.add(new Object[] { "text1", 5, "text1", "text1" });
		cases.add(new Object[] { "text1", 6, "text1", "text1" });
		cases.add(new Object[] { "text1", 10, "text1", "text1" });
		cases.add(new Object[] { "text1 text2", 4, "t...", "" });
		cases.add(new Object[] { "text1 text2", 5, "te...", "text1" });
		cases.add(new Object[] { "text1 text2", 6, "tex...", "text1" });
		cases.add(new Object[] { "text1 text2", 7, "text...", "text1" });
		cases.add(new Object[] { "text1 text2", 11, "text1 text2", "text1 text2" });
		cases.add(new Object[] { "text1 text2 ", 11, "text1 te...", "text1 text2" });
		cases.add(new Object[] { "text1  text2 ", 11, "text1  t...", "text1 " });
		return cases.iterator();
	}
	
	/**
	 * @param text The text to truncate
	 * @param maxCharacters The maximum amount of characters allowed
	 * @param expectedWordsTruncatedText The expected truncated text with words truncated
	 * @param expectedWordsNotTruncatedText The expected truncated text without words truncated
	 */
	@Test(dataProvider = "truncateProvider")
	public void truncate(String text, Integer maxCharacters, String expectedWordsTruncatedText,
			String expectedWordsNotTruncatedText) {
		org.testng.Assert.assertEquals(StringUtils.truncate(text, maxCharacters), expectedWordsTruncatedText);
		org.testng.Assert.assertEquals(StringUtils.truncate(text, maxCharacters, true), expectedWordsTruncatedText);
		org.testng.Assert.assertEquals(StringUtils.truncate(text, maxCharacters, false), expectedWordsNotTruncatedText);
	}
	
	/**
	 * @return The different scenarios
	 */
	@DataProvider
	public Iterator<Object[]> extractPlaceholdersProvider() {
		List<Object[]> cases = Lists.newArrayList();
		cases.add(new Object[] { "", Sets.newHashSet() });
		cases.add(new Object[] { "a", Sets.newHashSet() });
		cases.add(new Object[] { "${a}", Sets.newHashSet("a") });
		cases.add(new Object[] { "${a}${a}", Sets.newHashSet("a") });
		cases.add(new Object[] { "${a}${b}", Sets.newHashSet("a", "b") });
		return cases.iterator();
	}
	
	/**
	 * Verifies the {@link StringUtils#extractPlaceHolders(String)} method
	 * 
	 * @param string The string
	 * @param placeholderNames The placeholder names
	 */
	@Test(dataProvider = "extractPlaceholdersProvider")
	public void extractPlaceholders(String string, Set<String> placeholderNames) {
		Set<String> result = StringUtils.extractPlaceHolders(string);
		Assert.assertEqualsNoOrder(result, placeholderNames);
	}
}
