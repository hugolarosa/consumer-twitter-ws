package com.hlarosa.consumidor.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tweet implements Serializable{
	
	private static final long serialVersionUID = 4894729030347835498L;
	
	@Id
    @GeneratedValue
	private Long id;
	private String usuario;
	@Column(length = 560)
	private String texto;
	private String localizacion;
	private String validacion;
	
	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tweet(Long id, String usuario, String texto, String localizacion, String validacion) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.texto = texto;
		this.localizacion = localizacion;
		this.validacion = validacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getValidacion() {
		return validacion;
	}

	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

	@Override
	public String toString() {
		return "Twit [id=" + id + ", usuario=" + usuario + ", texto=" + texto + ", localizacion=" + localizacion
				+ ", validacion=" + validacion + "]";
	}	

}
