package jp.co.namihira.townbookweb.client.twitter;

import org.springframework.stereotype.Service;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class TwitterClient {

    private static final Twitter sender = TwitterFactory.getSingleton();

    public DirectMessage postDM(final String screenName, final String text) {
        DirectMessage dm = null;
        try {
            dm = sender.sendDirectMessage(screenName, text);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return dm;
    }

    public Status tweet(final String text) {
        Status status = null;
        try {
            status = sender.updateStatus(text);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return status;
    }

}
