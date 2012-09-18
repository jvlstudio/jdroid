package com.jdroid.javaweb.utils;

import java.util.Collection;
import java.util.NoSuchElementException;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A set of useful functions used against a Collection
 * 
 * @author Maxi Rosson
 */
public abstract class CollectionUtils extends com.jdroid.java.utils.CollectionUtils {
	
	/**
	 * Default string separator
	 */
	public static final String DEFAULT_SEPARATOR = ", ";
	
	protected CollectionUtils() {
		// Helper class
	}
	
	/**
	 * Join a collection of string elements with a given token
	 * 
	 * @param token A string that will work as glue character between elements
	 * @param objects all the elements in the collection
	 * @return a String with all the elements in the collection joined with the token
	 */
	public static String join(String token, Collection<?> objects) {
		return CollectionUtils.join(token, objects, Functions.toStringFunction());
	}
	
	/**
	 * Join a collection of string elements with a given token
	 * 
	 * @param <F> The object types
	 * 
	 * @param token A string that will work as glue character between elements
	 * @param objects all the elements in the collection
	 * @param transformer The transformer to be applied for each object
	 * @return a String with all the elements in the collection joined with the token
	 */
	public static <F> String join(String token, Collection<F> objects, Function<? super F, String> transformer) {
		Joiner joiner = Joiner.on(token);
		return joiner.join(Iterables.transform(objects, transformer));
	}
	
	/**
	 * Same as join(String, Collection<String>) but with the token ", "
	 * 
	 * @param objects all the elements in the collection
	 * @return a String with all the elements in the collection joined with the token
	 * 
	 * @see CollectionUtils#join(String, Collection)
	 */
	public static String join(Collection<?> objects) {
		return CollectionUtils.join(CollectionUtils.DEFAULT_SEPARATOR, objects);
	}
	
	/**
	 * Same as join(String, Collection<String>, Function) but with the token ", "
	 * 
	 * @param <F> The object types
	 * 
	 * @param objects all the elements in the collection
	 * @param transformer The transformer to be applied for each object
	 * 
	 * @return a String with all the elements in the collection joined with the token
	 * 
	 * @see CollectionUtils#join(String, Collection)
	 */
	public static <F> String join(Collection<F> objects, Function<? super F, String> transformer) {
		return CollectionUtils.join(CollectionUtils.DEFAULT_SEPARATOR, objects, transformer);
	}
	
	/**
	 * Return the first element in '<code>candidates</code>' that is complies with a determined {@link Predicate}. If no
	 * element matches returns <code>null</code>. Iteration order is {@link Collection} implementation specific.
	 * 
	 * @param <T> The generic type of the objects contained within the collections.
	 * @param predicate The {@link Predicate} that applies to the candidates.
	 * @param candidates The candidates to search for.
	 * @return {@link Object} The first present object, or <code>null</code> if not found.
	 */
	public static <T> T findFirstMatch(Predicate<T> predicate, Collection<T> candidates) {
		if (isEmpty(candidates)) {
			return null;
		}
		for (T candidate : candidates) {
			if (predicate.apply(candidate)) {
				return candidate;
			}
		}
		return null;
	}
	
	/**
	 * Returns the first element in {@link Iterable} that satisfies the given predicate, else return null
	 * 
	 * @param <T> The item type
	 * @param iterable The {@link Iterable}
	 * @param predicate The {@link Predicate}
	 * @return The element
	 */
	public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
		try {
			return Iterables.find(iterable, predicate);
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
