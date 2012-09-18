package com.jdroid.android.twitter;

import java.util.List;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import com.jdroid.android.exception.CommonErrorCode;
import com.jdroid.java.collections.Lists;

/**
 * 
 * @author Maxi Rosson
 */
public class TwitterConnector {
	
	private Twitter twitter;
	
	public TwitterConnector(String twitterAccessToken, String twitterAccessTokenSecret) {
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(TwitterBridgeActivity.OAUTH_TOKEN, TwitterBridgeActivity.SECRET_KEY);
		AccessToken accessToken = new AccessToken(twitterAccessToken, twitterAccessTokenSecret);
		twitter.setOAuthAccessToken(accessToken);
	}
	
	public void post(String message) {
		try {
			twitter.updateStatus(message);
		} catch (TwitterException e) {
			handleTwitterError(e);
		}
	}
	
	/**
	 * Get the Twitter ids for my friends
	 * 
	 * @return The friends ids
	 */
	public List<Long> getFriendsIds() {
		List<Long> twitterIds = Lists.newArrayList();
		try {
			long[] friendsIds = twitter.getFriendsIDs(-1).getIDs();
			for (int i = 0; i < friendsIds.length; i++) {
				twitterIds.add(Long.valueOf(friendsIds[i]));
			}
			
		} catch (TwitterException e) {
			handleTwitterError(e);
		}
		return twitterIds;
	}
	
	private void handleTwitterError(TwitterException e) {
		if (TwitterErrorType.find(e).equals(TwitterErrorType.OAUTH_ERROR)) {
			throw new InvalidTwitterTokenException();
		} else {
			throw CommonErrorCode.TWITTER_ERROR.newApplicationException(e);
		}
	}
	
}
