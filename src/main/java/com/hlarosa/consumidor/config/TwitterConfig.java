package com.hlarosa.consumidor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

import com.hlarosa.consumidor.twitter.TwitterMessageProducer;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Configuration
public class TwitterConfig {

	@Bean
	TwitterStreamFactory twitterStreamFactory() {
		return new TwitterStreamFactory();
	}
	
	@Bean
	TwitterStream twitterStream(TwitterStreamFactory twitterStreamFactory) {
		return twitterStreamFactory.getInstance();
	}
	
	@Bean
	MessageChannel outputChannel() {
		return MessageChannels.direct().get();
	}
	
	@Bean
	TwitterMessageProducer twitterMessageProducer(
			TwitterStream twitterStream, MessageChannel outputChannel) {

		TwitterMessageProducer twitterMessageProducer =
				new TwitterMessageProducer(twitterStream, outputChannel);
		
		return twitterMessageProducer;
	}	
	
}
