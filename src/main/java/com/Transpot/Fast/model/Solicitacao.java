package com.Transpot.Fast.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * 
 * Criado e desenvolvido por min, Para meu TCC do curso de Analize e Desenvolvimento de sistema 
 * 
 * Designer, Teste, DBA, Desenvolvedor...
 *
 * @author Rudson Porfirio Do Nascimento
 */
@Entity
public class Solicitacao implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private long id;
	private Date dataSolicitacao;
	private Date horaIda;
	private Date horaVolta;
	private EnderecoDestino enderecoDestino;
	private Usuario usuario;
	private Motorista motorista;
	private StatusSolicitacao statusSolicitacao;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	public Date getHoraIda() {
		return horaIda;
	}
	public void setHoraIda(Date horaIda) {
		this.horaIda = horaIda;
	}
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	public Date getHoraVolta() {
		return horaVolta;
	}
	public void setHoraVolta(Date horaVolta) {
		this.horaVolta = horaVolta;
	}
	
	@Embedded
	public EnderecoDestino getEnderecoDestino() {
		return enderecoDestino;
	}
	public void setEnderecoDestino(EnderecoDestino enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}
	@OneToOne
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@OneToOne
	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	public StatusSolicitacao getStatusSolicitacao() {
		return statusSolicitacao;
	}
	public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitacao other = (Solicitacao) obj;
		if (id != other.id)
			return false;
		return true;
	} 
	
	
	
	
	
	

}
