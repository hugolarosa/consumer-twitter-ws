package com.hlarosa.consumidor.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hlarosa.consumidor.dto.Tweet;
import com.hlarosa.consumidor.service.TweetService;

@RestController
public class TweetApi {
	
	@Autowired TweetService tweetService;
	
	@RequestMapping(value="/consultartweets", method=RequestMethod.GET)
	public List<TweetResponse> getTweets() {
		return tweetService.getTweets();
	}
	
	@RequestMapping(value="/marcartweet", method=RequestMethod.POST)
	public TweetResponse markTweet(@RequestBody @Valid TweetRequest tweetRequest) {
		Tweet tweet = tweetService.findById(tweetRequest.getId());
		return tweetService.markTweet(tweet);
	}
	
	@RequestMapping(value="/validadosporusuario", method=RequestMethod.POST)
	public List<TweetResponse> validateByUser(@RequestBody @Valid TweetRequest tweetRequest){
		return tweetService.getTweetsByValidationAndUser(tweetRequest);
	}
	
}
