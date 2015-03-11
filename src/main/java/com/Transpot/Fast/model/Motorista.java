package com.Transpot.Fast.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * 
 * Criado e desenvolvido por min, Para meu TCC do curso de Analize e
 * Desenvolvimento de sistema
 * 
 * Designer, Teste, DBA, Desenvolvedor...
 *
 * @author Rudson Porfirio Do Nascimento
 */
@Entity
public class Motorista implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;

	private String telefone;
	private String celular;
	private String celular2;
	@NotBlank @NotNull @Email
	private String email;
	@NotBlank @NotNull
	private String senha;
	private Date dataDeCadastro;
	private Date ultimaAtualizacao;
	private Endereco endereco;
	private String rg;

	private String cpf;
	
	private String cnh;
	private StatusMotorista statusMotorista;

	private Veiculo veiculo;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Column(nullable = false)
	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public StatusMotorista getStatusMotorista() {
		return statusMotorista;
	}

	public void setStatusMotorista(StatusMotorista statusMotorista) {
		this.statusMotorista = statusMotorista;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((statusMotorista == null) ? 0 : statusMotorista.hashCode());
		return result;
	}



	@Override
	public String toString() {
		return "Motorista [id=" + id + ", nome=" + nome + ", statusMotorista="
				+ statusMotorista + ", telefone=" + telefone + ", celular="
				+ celular + ", celular2=" + celular2 + ", email=" + email
				+ ", senha=" + senha + ", dataDeCadastro=" + dataDeCadastro
				+ ", ultimaAtualizacao=" + ultimaAtualizacao + ", endereco="
				+ endereco + ", rg=" + rg + ", cpf=" + cpf + ", cnh=" + cnh
				+ ", veiculo=" + veiculo + "]";
	}
	
	

}
