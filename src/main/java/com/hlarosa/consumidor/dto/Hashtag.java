package com.hlarosa.consumidor.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Hashtag {
	
	@Id
    @GeneratedValue
	private Long id;
	private String text;
	
}
