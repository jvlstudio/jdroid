package com.jdroid.javaweb.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.jdroid.javaweb.guava.predicate.EqualsPropertyPredicate;

/**
 * Tests the {@link CollectionUtils}
 * 
 */
public class CollectionUtilsTest {
	
	/**
	 * @return The different cases to test
	 */
	@DataProvider
	public Object[][] getJoinDataProvider() {
		Object[] case1 = { ",", Lists.newArrayList(1, 2, 3, 4, 5), Functions.toStringFunction(), "1,2,3,4,5" };
		Object[] case2 = { ", ", Lists.newArrayList(1, 2, 3, 4, 5), Functions.toStringFunction(), "1, 2, 3, 4, 5" };
		Object[] case3 = { ", ", Lists.newArrayList(1, 2, 3, 4, 5), new Function<Object, String>() {
			
			@Override
			public String apply(Object from) {
				return "?";
			}
		}, "?, ?, ?, ?, ?" };
		return new Object[][] { case1, case2, case3 };
	}
	
	/**
	 * Tests {@link CollectionUtils#join(String, Collection, Function)} method
	 * 
	 * @param separator The string separator
	 * @param objects The objects
	 * @param function The transformer function
	 * @param expected The expected string
	 */
	@Test(dataProvider = "getJoinDataProvider")
	public void join(String separator, Collection<?> objects, Function<? super Object, String> function, String expected) {
		String actual = CollectionUtils.join(separator, objects, function);
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Tests {@link CollectionUtils#join(Collection)} method
	 */
	@Test
	public void join() {
		String actual = CollectionUtils.join(Lists.newArrayList(1, 2, 3, 4, 5));
		Assert.assertEquals(actual, "1, 2, 3, 4, 5");
	}
	
	/**
	 * @return The different scenarios
	 */
	@DataProvider
	public Iterator<Object[]> findFirstMatchDataProvider() {
		List<Object[]> cases = Lists.newArrayList();
		TestObject expected = new TestObject("other");
		cases.add(new Object[] { Lists.newArrayList(new TestObject("some"), expected), "other", expected });
		cases.add(new Object[] { Lists.newArrayList(new TestObject("some")), "other", null });
		cases.add(new Object[] { Lists.newArrayList(new TestObject("some")), null, null });
		cases.add(new Object[] { Lists.newArrayList(), "other", null });
		return cases.iterator();
	}
	
	/**
	 * Verifies the {@link CollectionUtils#findFirstMatch(com.google.common.base.Predicate, Collection)} method
	 * 
	 * @param objects The object to test
	 * @param value The value to filter
	 * @param expected The expected value
	 */
	@Test(dataProvider = "findFirstMatchDataProvider")
	public void findFirstMatch(Collection<TestObject> objects, Object value, Object expected) {
		Assert.assertEquals(
			CollectionUtils.findFirstMatch(new EqualsPropertyPredicate<TestObject>("value", value), objects), expected);
	}
	
	public class TestObject {
		
		private Object value;
		
		/**
		 * @param value The value
		 */
		public TestObject(Object value) {
			this.value = value;
		}
		
		/**
		 * @return The value
		 */
		public Object getValue() {
			return value;
		}
	}
}
