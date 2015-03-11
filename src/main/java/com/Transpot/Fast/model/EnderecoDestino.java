package com.Transpot.Fast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

/**
 * 
 * 
 * Criado e desenvolvido por min, Para meu TCC do curso de Analize e Desenvolvimento de sistema 
 * 
 * Designer, Teste, DBA, Desenvolvedor...
 *
 * @author Rudson Porfirio Do Nascimento
 */
@Embeddable
public class EnderecoDestino implements Serializable {

	private static final long serialVersionUID = 1L;

	private Instituicao instituicao;

	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;

	@Column(name= "destino_logradouro", nullable = false)
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	@Column(name= "destino_bairro", nullable = false)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	@Column(name= "destino_cep",nullable = false)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	@Column(name= "destino_cidade",nullable = false)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@OneToOne
	public Instituicao getIntituicao() {
		return instituicao;
	}

	public void setIntituicao(Instituicao intituicao) {
		this.instituicao = intituicao;
	}

}
