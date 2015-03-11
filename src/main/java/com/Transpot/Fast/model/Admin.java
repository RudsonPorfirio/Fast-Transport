package com.Transpot.Fast.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
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
public class Admin implements Serializable {

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
	
	private String rg;

	private String cpf;
	private Date dataDeCadastro;

	
	private Endereco endereco;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable=false)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}


	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="endereco_usuario")
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Admin other = (Admin) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", telefone="
				+ telefone + ", celular=" + celular + ", celular2=" + celular2
				+ ", email=" + email + ", senha=" + senha + ", rg=" + rg
				+ ", cpf=" + cpf + ", dataDeCadastro=" + dataDeCadastro
				 + ", endereco="
				+ endereco + "]";
	}
	
	

}
