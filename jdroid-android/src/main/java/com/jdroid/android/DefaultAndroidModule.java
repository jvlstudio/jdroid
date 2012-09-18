package com.jdroid.android;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jdroid.android.context.ErrorReportingContext;
import com.jdroid.android.cookie.CookieRepository;
import com.jdroid.android.cookie.CookieRepositoryImpl;
import com.jdroid.android.mail.GmailMailService;
import com.jdroid.java.mail.MailService;

public class DefaultAndroidModule extends AbstractModule {
	
	/**
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		
		if (AbstractApplication.get().getAndroidApplicationContext().isCookieRepositoryEnabled()) {
			this.bind(CookieRepository.class).to(CookieRepositoryImpl.class).in(Singleton.class);
		}
		
		if (ErrorReportingContext.get().isMailReportingEnabled()) {
			this.bind(MailService.class).to(GmailMailService.class).in(Singleton.class);
		}
	}
	
}
