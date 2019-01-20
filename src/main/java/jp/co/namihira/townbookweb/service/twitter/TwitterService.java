package jp.co.namihira.townbookweb.service.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.twitter.TwitterClient;

@Service
public class TwitterService {

	@Autowired
	private TwitterClient twitterClient;
	
	@Value("${twitter.account}")
	private String twitterAccount = "";
	
	public void postDM(final String screenName, final String text) {
		twitterClient.postDM(screenName, text);
	}
	
	public void postDMtoAdmin(final String text) {
		twitterClient.postDM(twitterAccount, text);
	}
	
}
