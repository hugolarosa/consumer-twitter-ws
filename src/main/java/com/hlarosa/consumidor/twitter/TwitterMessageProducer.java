package com.hlarosa.consumidor.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.MessageChannel;
import com.hlarosa.consumidor.service.TweetService;

import lombok.extern.slf4j.Slf4j;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

@Slf4j
public class TwitterMessageProducer extends MessageProducerSupport {
	
	@Autowired TweetService tweetService;
	
	private final TwitterStream twitterStream;

	@Value("${twitter.terms}")
	private String[] terms;
	
	@Value("${twitter.languages}")
	private String[] languages;

	private StatusListener statusListener;
	private FilterQuery filterQuery;
	
	public TwitterMessageProducer(TwitterStream twitterStream, MessageChannel outputChannel) {
		this.twitterStream = twitterStream;
		setOutputChannel(outputChannel);
	}
	
	@Override
	protected void onInit() {
		super.onInit();

		statusListener = new StatusListener();
		
	    filterQuery = new FilterQuery(0, null, terms, null, languages);
	}

	@Override
	public void doStart() {
		twitterStream.addListener(statusListener);
		twitterStream.filter(filterQuery);
	}

	@Override
	public void doStop() {
		twitterStream.cleanUp();
		twitterStream.clearListeners();
	}
		
	StatusListener getStatusListener() {
		return statusListener;
	}

	FilterQuery getFilterQuery() {
		return filterQuery;
	}
	
	class StatusListener extends StatusAdapter {

		@Override
		public void onStatus(Status status) {
			tweetService.saveTweet(status);
		}

		@Override
		public void onException(Exception ex) {
			log.error(ex.getMessage(), ex);
		}

		@Override
		public void onStallWarning(StallWarning warning) {
			log.warn(warning.toString());
		}

	}
	
}
