package com.jdroid.javaweb.guava.predicate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.google.common.collect.Lists;

/**
 * Test {@link ContainsPropertyPredicate}
 * 
 */
public class ContainsPropertyPredicateTest {
	
	/**
	 * @return The different scenarios
	 */
	@DataProvider
	public Iterator<Object[]> getScenarios() {
		List<Object[]> cases = Lists.newArrayList();
		cases.add(new Object[] { new TestObject(), "enums", null, false });
		cases.add(new Object[] { new TestObject(), "enums", TestEnum.TEST_1, false });
		cases.add(new Object[] { new TestObject(Lists.newArrayList(TestEnum.TEST_1)), "enums", null, false });
		cases.add(new Object[] { new TestObject(Lists.newArrayList(TestEnum.TEST_1)), "enums", TestEnum.TEST_2, false });
		cases.add(new Object[] { new TestObject(Lists.newArrayList(TestEnum.TEST_1)), "enums", TestEnum.TEST_1, true });
		cases.add(new Object[] { new TestObject(Lists.newArrayList(TestEnum.TEST_1, TestEnum.TEST_2)), "enums",
				TestEnum.TEST_1, true });
		cases.add(new Object[] { new TestObject(Lists.newArrayList(TestEnum.TEST_1, TestEnum.TEST_2, TestEnum.TEST_3)),
				"enums", TestEnum.TEST_1, true });
		cases.add(new Object[] { new TestObject(), "testEnum", null, true });
		cases.add(new Object[] { new TestObject(), "testEnum", Lists.newArrayList(TestEnum.TEST_1), false });
		cases.add(new Object[] { new TestObject(TestEnum.TEST_1), "testEnum", null, false });
		cases.add(new Object[] { new TestObject(TestEnum.TEST_1), "testEnum", Lists.newArrayList(TestEnum.TEST_2),
				false });
		cases.add(new Object[] { new TestObject(TestEnum.TEST_1), "testEnum", Lists.newArrayList(TestEnum.TEST_1), true });
		cases.add(new Object[] { new TestObject(TestEnum.TEST_1), "testEnum",
				Lists.newArrayList(TestEnum.TEST_1, TestEnum.TEST_2), true });
		cases.add(new Object[] { new TestObject(TestEnum.TEST_1), "testEnum",
				Lists.newArrayList(TestEnum.TEST_1, TestEnum.TEST_2, TestEnum.TEST_3), true });
		return cases.iterator();
	}
	
	/**
	 * Verifies {@link ContainsPropertyPredicate#apply(Object)} method
	 * 
	 * @param input The input
	 * @param propertyName The propertyName
	 * @param value The value
	 * @param expected The expected value
	 */
	@Test(dataProvider = "getScenarios")
	public void apply(TestObject input, String propertyName, Object value, boolean expected) {
		ContainsPropertyPredicate<TestObject> predicate = new ContainsPropertyPredicate<TestObject>(propertyName, value);
		Assert.assertEquals(predicate.apply(input), expected);
	}
	
	private enum TestEnum {
		TEST_1,
		TEST_2,
		TEST_3;
	}
	
	public class TestObject {
		
		private TestEnum testEnum;
		private Collection<TestEnum> enums = Lists.newArrayList();
		
		/**
		 * Default constructor
		 */
		public TestObject() {
			// nothing
		}
		
		/**
		 * @param enums The {@link TestEnum}s
		 */
		public TestObject(Collection<TestEnum> enums) {
			this.enums = enums;
		}
		
		/**
		 * @param testEnum The {@link TestEnum}
		 */
		public TestObject(TestEnum testEnum) {
			this.testEnum = testEnum;
		}
		
		/**
		 * @return The {@link TestEnum}
		 */
		public TestEnum getTestEnum() {
			return testEnum;
		}
		
		/**
		 * @return The enums
		 */
		public Collection<TestEnum> getEnums() {
			return enums;
		}
	}
}
