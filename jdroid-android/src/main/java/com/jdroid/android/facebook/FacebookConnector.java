package com.jdroid.android.facebook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.jdroid.android.exception.CommonErrorCode;
import com.jdroid.java.collections.Lists;

/**
 * 
 * @author Maxi Rosson
 */
public class FacebookConnector {
	
	private Facebook facebook;
	
	public FacebookConnector(String appId) {
		this(appId, null);
	}
	
	public FacebookConnector(String appId, String accessToken) {
		facebook = new Facebook(appId);
		facebook.setAccessToken(accessToken);
	}
	
	public void post(String message, String description, String name, String picure, String link) {
		try {
			Bundle parameters = new Bundle();
			parameters.putString("message", message);
			parameters.putString("description", description);
			parameters.putString("name", name);
			parameters.putString("picture", picure);
			parameters.putString("link", link);
			String response = facebook.request("me/feed", parameters, "POST");
			Util.parseJson(response);
		} catch (FacebookError e) {
			handleFacebookError(e);
		} catch (FileNotFoundException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
		} catch (MalformedURLException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
		} catch (IOException e) {
			throw CommonErrorCode.CONNECTION_ERROR.newApplicationException(e);
		} catch (JSONException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
		}
	}
	
	public List<String> getFriendsIds() {
		List<String> facebookIds = Lists.newArrayList();
		try {
			Bundle parameters = new Bundle();
			parameters.putString("fields", "id");
			String response = facebook.request("me/friends", parameters);
			JSONArray jArray = Util.parseJson(response).getJSONArray("data");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject array_element = jArray.getJSONObject(i);
				facebookIds.add(array_element.getString("id"));
			}
			
		} catch (FacebookError e) {
			handleFacebookError(e);
		} catch (JSONException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
		} catch (MalformedURLException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newApplicationException(e);
		} catch (IOException e) {
			throw CommonErrorCode.CONNECTION_ERROR.newApplicationException(e);
		}
		return facebookIds;
	}
	
	private void handleFacebookError(FacebookError e) {
		if (FacebookErrorType.find(e).equals(FacebookErrorType.OAUTH_ERROR)) {
			throw new InvalidFacebookTokenException();
		} else {
			throw CommonErrorCode.FACEBOOK_ERROR.newApplicationException(e);
		}
	}
	
	/**
	 * Connects to facebook granting only basic permissions. {@link FacebookConnector#connectCallback(int, int, Intent)}
	 * must be called on the {@link Activity#onActivityResult(int, int, Intent)} to complete login.
	 * 
	 * @param activity The {@link Activity} where the authorization dialog will be shown.
	 */
	public void connect(Activity activity) {
		facebook.authorize(activity, new DefaultFacebookDialogListener());
	}
	
	/**
	 * IMPORTANT: This method must be invoked at the top of the calling activity's onActivityResult() function or
	 * Facebook authentication will not function properly!
	 * 
	 * @param requestCode The request code for the {@link Activity#onActivityResult(int, int, Intent)}.
	 * @param resultCode The result code obtained on {@link Activity#onActivityResult(int, int, Intent)}.
	 * @param data The data intent obtained on {@link Activity#onActivityResult(int, int, Intent)}.
	 * @return The access token required to connect to Facebook.
	 */
	public String connectCallback(int requestCode, int resultCode, Intent data) {
		facebook.authorizeCallback(requestCode, resultCode, data);
		return facebook.getAccessToken();
	}
}
