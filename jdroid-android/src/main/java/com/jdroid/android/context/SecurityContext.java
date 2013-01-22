package com.jdroid.android.context;

import com.jdroid.android.AbstractApplication;
import com.jdroid.android.domain.User;
import com.jdroid.android.repository.UserRepository;

/**
 * 
 * @author Maxi Rosson
 */
public class SecurityContext {
	
	private static final SecurityContext INSTANCE = new SecurityContext();
	
	private UserRepository userRepository;
	private User user;
	
	/**
	 * @return The {@link SecurityContext} instance
	 */
	public static SecurityContext get() {
		return INSTANCE;
	}
	
	private SecurityContext() {
		userRepository = AbstractApplication.getInstance(UserRepository.class);
		user = userRepository.getUser();
	}
	
	public void attach(User user) {
		this.user = user;
		userRepository.saveUser(user);
	}
	
	public void detachUser() {
		userRepository.removeUser();
		user = null;
	}
	
	/**
	 * @return The user logged in the application
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @return Whether the user is authenticated or not
	 */
	public Boolean isAuthenticated() {
		return user != null;
	}
}
