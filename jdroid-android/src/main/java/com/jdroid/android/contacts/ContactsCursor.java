package com.jdroid.android.contacts;

import java.io.InputStream;
import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import com.jdroid.android.domain.FileContent;

/**
 * 
 * @author Maxi Rosson
 */
public class ContactsCursor extends CursorWrapper {
	
	public static Cursor getContactCursor(Activity activity) {
		Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
		String[] projection = new String[] { BaseColumns._ID, ContactsContract.CommonDataKinds.Photo.CONTACT_ID,
				Contacts.DISPLAY_NAME, CommonDataKinds.Email.DATA };
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";
		return activity.managedQuery(uri, projection, null, null, sortOrder);
	}
	
	public ContactsCursor(Activity activity) {
		super(ContactsCursor.getContactCursor(activity));
	}
	
	public Long getContactId() {
		return getLong(0);
	}
	
	public Long getPhotoContactId() {
		return getLong(1);
	}
	
	public String getDisplayName() {
		return getString(2);
	}
	
	public String getEmail() {
		return getString(3);
	}
	
	public FileContent getContactFileContent() {
		return new ContactFileContent(getPhotoContactId());
	}
	
	public Bitmap getPhoto(Activity activity) {
		Uri contactPhotoUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, getPhotoContactId());
		InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(activity.getContentResolver(),
			contactPhotoUri);
		return BitmapFactory.decodeStream(input);
	}
}
