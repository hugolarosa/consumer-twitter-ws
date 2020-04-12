package com.hlarosa.consumidor.service;

import java.util.LinkedList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hlarosa.consumidor.api.TweetRequest;
import com.hlarosa.consumidor.api.TweetResponse;
import com.hlarosa.consumidor.dao.HashtagRepository;
import com.hlarosa.consumidor.dao.TweetRepository;
import com.hlarosa.consumidor.dto.Hashtag;
import com.hlarosa.consumidor.dto.Tweet;

import lombok.extern.slf4j.Slf4j;
import twitter4j.HashtagEntity;
import twitter4j.Status;

@Slf4j
@Service
public class TweetService {

	@Autowired TweetRepository tweetRepository;
	@Autowired HashtagRepository hashtagRepository;
	
	@Autowired Mapper mapper;
	
	@Value("${twitter.followers}")
	private int followers;
	
	public Tweet saveTweet(Tweet tweet) {
		return tweetRepository.saveAndFlush(tweet);
	}
	
	public Hashtag saveHashtag(Hashtag hashtag) {
		return hashtagRepository.saveAndFlush(hashtag);		
	}
	
	public Hashtag hashtagEntityToHashtag(HashtagEntity hashtagEntity) {
		Hashtag h = new Hashtag();
		h.setText(hashtagEntity.getText());
		return h;
	}
	
	public Tweet statusToTweet(Status status) {
		Tweet t = new Tweet();
		t.setUsuario(status.getUser().getScreenName());
		t.setTexto(status.getText());
		t.setLocalizacion(status.getUser().getLocation());
		t.setValidacion("NO");		
		return t;
	}
	
	public Tweet saveTweet(Status status) {		
		Tweet t = null;
		try {
			if(status.getUser().getFollowersCount() > followers) {
				t = statusToTweet(status);
				t = saveTweet(t);
				Hashtag h = null;
				for(HashtagEntity he : status.getHashtagEntities()) {
					h = hashtagEntityToHashtag(he);
					h = saveHashtag(h);
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage());			
		}
		return t;
	}
	
	public List<TweetResponse> getTweets(){
		List<TweetResponse> lista = new LinkedList<TweetResponse>();
		for(Tweet t : tweetRepository.findAll()) {
			TweetResponse tr = mapper.map(t, TweetResponse.class);
			lista.add(tr);
		}
		return lista;
	}
	
	public Tweet findById(Long id) {
		return tweetRepository.getOne(id);
	}
	
	public TweetResponse markTweet(Tweet tweet) {
		tweet.setValidacion("SI");
		tweet = tweetRepository.saveAndFlush(tweet);
		return mapper.map(tweet, TweetResponse.class);
	}
	
	public List<TweetResponse> getTweetsByValidationAndUser(TweetRequest tweetRequest){
		List<TweetResponse> lista = new LinkedList<TweetResponse>();
		for(Tweet tweet : tweetRepository.findByValidacionAndUsuario(tweetRequest.getValidacion(), tweetRequest.getUsuario() ) ) {
			TweetResponse tr = mapper.map(tweet, TweetResponse.class);
			lista.add(tr);
		}
		return lista;
	}
	
}
