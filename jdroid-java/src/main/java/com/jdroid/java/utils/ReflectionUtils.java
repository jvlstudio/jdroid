package com.jdroid.java.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import com.jdroid.java.domain.Identifiable;
import com.jdroid.java.exception.UnexpectedException;

/**
 * Reflection related utilities
 */
public abstract class ReflectionUtils {
	
	/**
	 * Create a class for the specified type,.
	 * 
	 * @param <T> the class to instantiate to be returned
	 * @param clazz the class to be instantiated
	 * @return an instance of the class specified
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new UnexpectedException("Unable to instantiate class [" + clazz.getSimpleName() + "]", e);
		}
	}
	
	/**
	 * Create a class for the specified type, using the specified constructor with the passed parameters.
	 * 
	 * @param <T> the class to instantiate to be returned
	 * @param clazz the class to be instantiated
	 * @param parameterTypes a constructor with this parameters will be used to instantiate the class
	 * @param parameterValues parameter values to be used when instantiating
	 * @return an instance of the class specified
	 */
	public static <T> T newInstance(Class<T> clazz, Collection<Class<?>> parameterTypes,
			Collection<Object> parameterValues) {
		try {
			Constructor<T> constructor = clazz.getConstructor(parameterTypes.toArray(new Class[0]));
			return constructor.newInstance(parameterValues.toArray(new Object[0]));
		} catch (Exception e) {
			throw new UnexpectedException("Unable to instantiate class [" + clazz.getSimpleName() + "]", e);
		}
	}
	
	/**
	 * Set a value in the given object without using getters or setters
	 * 
	 * @param object The object where we want to null the expression
	 * @param expression The expression we want to null
	 * @param value The new value to set
	 */
	public static void set(Object object, String expression, Object value) {
		Field field = ReflectionUtils.getField(object.getClass(), expression);
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (SecurityException e) {
			throw new UnexpectedException(e);
		} catch (IllegalArgumentException e) {
			throw new UnexpectedException(e);
		} catch (IllegalAccessException e) {
			throw new UnexpectedException(e);
		} finally {
			field.setAccessible(false);
		}
	}
	
	/**
	 * @param field
	 * @param object
	 * @return Object
	 */
	public static Object get(Field field, Object object) {
		field.setAccessible(true);
		try {
			return field.get(object);
		} catch (IllegalArgumentException e) {
			throw new UnexpectedException(e);
		} catch (IllegalAccessException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * @param object
	 * @param fieldName
	 * @return Object
	 */
	public static Object get(Object object, String fieldName) {
		Field field = getField(object, fieldName);
		field.setAccessible(true);
		return get(field, object);
	}
	
	/**
	 * @param object
	 * @param fieldName
	 * @return Field
	 */
	public static Field getField(Object object, String fieldName) {
		try {
			return object.getClass().getDeclaredField(fieldName);
		} catch (SecurityException e) {
			throw new UnexpectedException(e);
		} catch (NoSuchFieldException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Returns a {@link Field} from a class or any of its super classes.
	 * 
	 * @param clazz The Class whose {@link Field} is looked for
	 * @param fieldName The name of the {@link Field} to get
	 * @return The {@link Field}
	 */
	public static Field getField(Class<?> clazz, String fieldName) {
		
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			
			// If the field wasn't found in the object class, its superclass must
			// be checked
			if (clazz.getSuperclass() != null) {
				return ReflectionUtils.getField(clazz.getSuperclass(), fieldName);
			}
			// If the field wasn't found and the object doesn't have a
			// superclass, an exception is thrown
			throw new UnexpectedException("The class '" + clazz.getName() + "' doesn't have a field named '"
					+ fieldName + "'.");
		}
	}
	
	/**
	 * Returns the value of a given {@link Field} from an {@link Object}. Or null if the {@link Object} doesn't have a
	 * {@link Field} with the given name.
	 * 
	 * @param <T> The type of the value
	 * @param object The {@link Object} whose value is being retrieved
	 * @param fieldName The name of the {@link Field}
	 * @return The value of the {@link Field}
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object object, String fieldName) {
		Field field = ReflectionUtils.getField(object.getClass(), fieldName);
		field.setAccessible(true);
		try {
			return (T)field.get(object);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * @param object
	 * @param fieldName
	 * @return Class<?>
	 */
	public static Class<?> getType(Object object, String fieldName) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			return field.getType();
		} catch (SecurityException e) {
			throw new UnexpectedException(e);
		} catch (NoSuchFieldException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public static void setId(Identifiable identifiable, Long id) {
		ReflectionUtils.set(identifiable, "id", id);
	}
}
