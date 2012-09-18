package com.jdroid.java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.jdroid.java.exception.UnexpectedException;

/**
 * 
 * @author Maxi Rosson
 */
public enum Hasher {
	
	SHA_1("SHA-1"),
	SHA_512("SHA-512");
	
	private String algorithm;
	
	private Hasher(String algorithm) {
		this.algorithm = algorithm;
	}
	
	/**
	 * Algorithm that returns a Hashed string of the value given as parameter
	 * 
	 * @param value the string to hash
	 * @return String hashed value.
	 */
	public String hash(String value) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(value.getBytes());
			byte[] hashed = messageDigest.digest();
			return EncryptionUtils.toHex(hashed);
		} catch (NoSuchAlgorithmException e) {
			throw new UnexpectedException(e);
		}
	}
}
