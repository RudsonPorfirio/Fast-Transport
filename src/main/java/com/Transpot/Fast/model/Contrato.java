package com.Transpot.Fast.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private Motorista motorista;

	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", motorista=" + motorista.toString() + ", usuario="
				+ usuario.toString() + "]";
	}

	
	
	
	
}
