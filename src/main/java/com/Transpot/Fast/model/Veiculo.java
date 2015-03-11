package com.Transpot.Fast.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Veiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue
	private Long id;
	
	
		
	@NotBlank
	@Column(name = "tipo", length = 20, nullable = false)
	private String tipo;
	
	@Column(name = "placa", length = 9, nullable = false)
	@NotNull @NotBlank
	private String placa;
	
	@Column(name = "cor", length = 50, nullable = false)
	@NotNull @NotBlank
	private String cor;
	
	@Column(name = "vagas",  nullable = false) @Min(0) @Max(99)
	private int vagas;
	
	@Column(name = "vagas_disponiveis",  nullable = false) @Min(0) @Max(99)
	private int vagasDisponiveis;
		
	@Column(name = "modelo", length = 100, nullable = false)
	@NotBlank @NotNull
	private String modelo;
	
	@Column(name = "ano", length = 5, nullable = false)
	private int ano;
	@Temporal(TemporalType.DATE)
	private Date dataDeCadastro;
	@Temporal(TemporalType.DATE)
	private Date ultimaAtualizacao;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}



	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

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

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", tipo=" + tipo + ", placa=" + placa
				+ ", cor=" + cor + ", vagas=" + vagas + ", vagasDisponiveis="
				+ vagasDisponiveis + ", modelo=" + modelo + ", ano=" + ano
				+ ", dataDeCadastro=" + dataDeCadastro + ", ultimaAtualizacao="
				+ ultimaAtualizacao + "]";
	}


	
	
	
}
