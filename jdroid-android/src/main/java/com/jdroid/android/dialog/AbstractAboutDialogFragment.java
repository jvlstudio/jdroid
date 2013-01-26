package com.jdroid.android.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.jdroid.android.R;
import com.jdroid.android.utils.AndroidUtils;
import com.jdroid.java.http.MimeType;
import com.jdroid.java.utils.DateUtils;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class AbstractAboutDialogFragment extends AbstractDialogFragment {
	
	public void show(Activity activity) {
		FragmentManager fm = ((FragmentActivity)activity).getSupportFragmentManager();
		show(fm, getClass().getSimpleName());
	}
	
	/**
	 * @see com.jdroid.android.dialog.AbstractDialogFragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, 0);
	}
	
	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup,
	 *      android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about_dialog, container, false);
		
		TextView appName = (TextView)view.findViewById(R.id.appName);
		appName.setText(getAppName());
		
		TextView version = (TextView)view.findViewById(R.id.version);
		version.setText(getString(R.string.version, AndroidUtils.getVersionName()));
		
		TextView contactUsLabel = (TextView)view.findViewById(R.id.contactUsLabel);
		TextView contactUsEmail = (TextView)view.findViewById(R.id.contactUsEmail);
		final String contactUsEmailAddress = getContactUsEmail();
		if (contactUsEmailAddress != null) {
			contactUsEmail.setText(contactUsEmailAddress);
		} else {
			contactUsLabel.setVisibility(View.GONE);
			contactUsEmail.setVisibility(View.GONE);
		}
		
		TextView copyright = (TextView)view.findViewById(R.id.copyright);
		copyright.setText(getCopyRightLegend());
		
		TextView allRightsReservedLegend = (TextView)view.findViewById(R.id.allRightsReservedLegend);
		allRightsReservedLegend.setText(getAllRightsReservedLegend());
		
		Button termsOfService = (Button)view.findViewById(R.id.termsOfService);
		final String termsOfUseURL = getTermsOfUseURL();
		if (termsOfUseURL != null) {
			termsOfService.setOnClickListener(new android.view.View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(termsOfUseURL));
					startActivity(intent);
				}
			});
			termsOfService.setVisibility(View.VISIBLE);
		}
		
		Button privacy = (Button)view.findViewById(R.id.privacy);
		final String privacyURL = getPrivacyURL();
		if (privacyURL != null) {
			privacy.setOnClickListener(new android.view.View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(privacyURL));
					startActivity(intent);
				}
			});
			privacy.setVisibility(View.VISIBLE);
		}
		
		Button share = (Button)view.findViewById(R.id.share);
		final String shareEmailSubject = getShareEmailSubject();
		final String shareEmailContent = getShareEmailContent();
		if ((shareEmailSubject != null) && (shareEmailContent != null)) {
			share.setOnClickListener(new android.view.View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType(MimeType.TEXT.toString());
					intent.putExtra(Intent.EXTRA_SUBJECT, shareEmailSubject);
					intent.putExtra(Intent.EXTRA_TEXT, shareEmailContent);
					startActivity(Intent.createChooser(intent, getString(R.string.shareTitle, getAppName())));
				}
			});
		} else {
			share.setVisibility(View.GONE);
		}
		
		view.findViewById(R.id.ok).setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		return view;
	}
	
	protected String getAllRightsReservedLegend() {
		return getString(R.string.allRightsReservedLegend);
	}
	
	protected String getCopyRightLegend() {
		return getString(R.string.copyright, DateUtils.getYear(), getAppName());
	}
	
	protected String getAppName() {
		return getString(R.string.appName);
	}
	
	protected String getTermsOfUseURL() {
		return null;
	}
	
	protected String getPrivacyURL() {
		return null;
	}
	
	protected String getShareEmailSubject() {
		return null;
	}
	
	protected String getShareEmailContent() {
		return null;
	}
	
	protected String getContactUsEmail() {
		return null;
	}
	
}
