package com.hlarosa.consumidor.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetResponse {

	private Long id;
	private String usuario;
	private String texto;
	private String localizacion;
	private String validacion;
	
}
