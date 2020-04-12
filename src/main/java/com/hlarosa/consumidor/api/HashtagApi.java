package com.hlarosa.consumidor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hlarosa.consumidor.service.HashtagService;

@RestController
public class HashtagApi {
	
	@Autowired HashtagService hashtagService;
	
	@RequestMapping(value="/hashtagmasusados", method=RequestMethod.GET)
	public List<HashtagRequest> getTweets() {
		return hashtagService.hashtagsMoreUsed();
	}

}
