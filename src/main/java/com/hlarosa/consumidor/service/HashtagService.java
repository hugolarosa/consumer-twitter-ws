package com.hlarosa.consumidor.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hlarosa.consumidor.api.HashtagRequest;
import com.hlarosa.consumidor.dao.HashtagRepository;

@Service
public class HashtagService {

	@Autowired HashtagRepository hashtagRepository;
	
	@Value("${hashtag.number}")
	private int numberhashtag;
	
	public List<HashtagRequest> hashtagsMoreUsed(){
		List<HashtagRequest> lista = new LinkedList<HashtagRequest>();
		for(Object[] o :hashtagRepository.hashtagsMoreUsed(numberhashtag)) {
			HashtagRequest hr = new HashtagRequest();			
			hr.setTexto(String.valueOf(o[0]));
			hr.setCont(Integer.parseInt(String.valueOf(o[1])));			
			lista.add(hr);
		}
		return lista;
	}
	
}
