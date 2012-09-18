package com.jdroid.android.contacts;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.jdroid.android.domain.FileContent;

/**
 * 
 * @author Maxi Rosson
 */
public class ContactFileContent extends FileContent {
	
	private Long rawContactId;
	
	public ContactFileContent(Long rawContactId) {
		this.rawContactId = rawContactId;
	}
	
	/**
	 * @see com.jdroid.android.domain.FileContent#getUri()
	 */
	@Override
	public Uri getUri() {
		return ContentUris.withAppendedId(Contacts.CONTENT_URI, rawContactId);
	}
}
