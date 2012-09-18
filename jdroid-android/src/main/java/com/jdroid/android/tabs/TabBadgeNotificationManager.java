package com.jdroid.android.tabs;

import java.util.Map;
import android.os.Handler;
import android.os.Message;
import com.jdroid.java.collections.Maps;

/**
 * Manager to control the badge of the {@link TabAction}s
 * 
 * @author Maxi Rosson
 */
public class TabBadgeNotificationManager {
	
	private final static Map<TabAction, Handler> tabHandlers = Maps.newHashMap();
	private final static Map<TabAction, Integer> tabBadges = Maps.newHashMap();
	
	/**
	 * Register the {@link TabAction} to start listening badge updates
	 * 
	 * @param tab The {@link TabAction} to register
	 * @param handler The {@link Handler} used to dispatch the messages
	 */
	public static void registerTabHandler(TabAction tab, Handler handler) {
		tabHandlers.put(tab, handler);
	}
	
	/**
	 * Unregister the {@link TabAction} to stop listening badge updates
	 * 
	 * @param tab The {@link TabAction} to unregister
	 */
	public static void unRegisterTabHandler(TabAction tab) {
		tabHandlers.remove(tab);
	}
	
	/**
	 * Update the badge number on the {@link TabAction}
	 * 
	 * @param tab The {@link TabAction} to update
	 * @param badges The new badge number to display
	 */
	public static void updateBadge(TabAction tab, Integer badges) {
		tabBadges.put(tab, badges);
		notifyBadgeUpdate(tab);
	}
	
	/**
	 * Increase the badge number on the {@link TabAction}
	 * 
	 * @param tab The {@link TabAction} to update
	 */
	public static void increaseBadge(TabAction tab) {
		tabBadges.put(tab, getBadges(tab) + 1);
		notifyBadgeUpdate(tab);
	}
	
	private static void notifyBadgeUpdate(TabAction tab) {
		Handler handler = tabHandlers.get(tab);
		if (handler != null) {
			handler.sendMessage(Message.obtain());
		}
	}
	
	/**
	 * @param tab The {@link TabAction}
	 * @return The badge number of the {@link TabAction}
	 */
	public static Integer getBadges(TabAction tab) {
		Integer badges = tabBadges.get(tab);
		return badges != null ? badges : 0;
	}
	
	public static void clearBadges() {
		for (TabAction tab : tabBadges.keySet()) {
			tabBadges.remove(tab);
			notifyBadgeUpdate(tab);
		}
	}
}
