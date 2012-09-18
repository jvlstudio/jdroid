package com.jdroid.javaweb.domain;

import java.io.Serializable;
import com.jdroid.java.utils.Hasher;
import com.jdroid.javaweb.exception.CommonErrorCode;
import com.jdroid.javaweb.exception.InvalidAuthenticationException;

public class Password implements Serializable {
	
	private String hashedPassword;
	
	/**
	 * Default constructor.
	 */
	@SuppressWarnings("unused")
	private Password() {
		// Do nothing, is required by hibernate
	}
	
	/**
	 * Create a new password
	 * 
	 * @param password the new password
	 */
	public Password(String password) {
		hashedPassword = getHasher().hash(password);
	}
	
	/**
	 * Password is verified by a hash function that compares the result of hashing the parameter with the hash of the
	 * saved password.
	 * 
	 * @param password string to be verified
	 * @throws InvalidAuthenticationException in case verification fails
	 */
	public void verify(String password) throws InvalidAuthenticationException {
		if (!hashedPassword.equals(getHasher().hash(password))) {
			throw new InvalidAuthenticationException(CommonErrorCode.INVALID_CREDENTIALS);
		}
	}
	
	/**
	 * @return the {@link Hasher}
	 */
	protected Hasher getHasher() {
		return Hasher.SHA_512;
	}
}
