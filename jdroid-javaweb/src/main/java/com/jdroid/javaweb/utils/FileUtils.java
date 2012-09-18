package com.jdroid.javaweb.utils;

import java.io.File;
import java.io.IOException;
import org.springframework.util.FileCopyUtils;

/**
 * This class contains functions for working with files within the application.
 */
public abstract class FileUtils extends com.jdroid.java.utils.FileUtils {
	
	/**
	 * @param src The source file
	 * @param dest The destination file
	 * @throws IOException if an I/O error occurs
	 */
	public static void copy(File src, File dest) throws IOException {
		FileCopyUtils.copy(src, dest);
	}
	
	/**
	 * @param src a source folder
	 * @param dest a destination folder
	 * @throws IOException if an I/O error occurs
	 */
	public static void copyDir(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdirs();
			}
			String[] files = src.list();
			for (String fileName : files) {
				FileUtils.copyDir(new File(src, fileName), new File(dest, fileName));
			}
		} else {
			FileUtils.copy(src, dest);
		}
	}
}
