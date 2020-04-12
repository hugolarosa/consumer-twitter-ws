package com.hlarosa.consumidor.twitter;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import twitter4j.TwitterStream;

public class TweetterStreamFactory extends AbstractFactoryBean<TwitterStream>{

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return TwitterStream.class;
	}

	@Override
	protected TwitterStream createInstance() throws Exception {
		// TODO Auto-generated method stub
		return new twitter4j.TwitterStreamFactory().getInstance();
	}
	
	@Override
	protected void destroyInstance(TwitterStream twitterStream) {
		twitterStream.shutdown();
	}

}
