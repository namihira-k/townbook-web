package jp.co.namihira.townbookweb.service.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.twitter.TwitterClient;

@Service
public class TwitterService {

	@Autowired
	private TwitterClient twitterClient;
	
	public void postDM(final String screenName, final String text) {
		twitterClient.postDM(screenName, text);
	}
	
}
