package com.hlarosa.consumidor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hlarosa.consumidor.dto.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	public List<Tweet> findByValidacionAndUsuario(String validacion, String usuario);
	
}
