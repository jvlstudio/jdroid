package com.jdroid.java.context;

import com.jdroid.java.utils.PropertiesUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class GitContext {
	
	private static final String GIT_PROPERTIES_RESOURCE_NAME = "git.properties";
	private static final String COMMIT_TIME = PropertiesUtils.getStringProperty("git.commit.time");
	private static final String BUILD_TIME = PropertiesUtils.getStringProperty("git.build.time");
	private static final String COMMIT_ID = PropertiesUtils.getStringProperty("git.commit.id");
	
	private static final GitContext INSTANCE = new GitContext();
	
	private GitContext() {
		PropertiesUtils.loadProperties(GIT_PROPERTIES_RESOURCE_NAME);
	}
	
	/**
	 * @return The {@link GitContext} instance
	 */
	public static GitContext get() {
		return INSTANCE;
	}
	
	public String getCommitTime() {
		return COMMIT_TIME;
	}
	
	public String getBuildTime() {
		return BUILD_TIME;
	}
	
	public String getCommitId() {
		return COMMIT_ID;
	}
}
