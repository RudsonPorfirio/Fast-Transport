package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.Transpot.Fast.Repositorio.RepositorioMotorista;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.StatusMotorista;
import com.Transpot.Fast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RepositorioMotorista repositorioMotorista;
	@Inject
	private Motorista motorista;
	@Inject
	private Motorista motoristaSelecionada;

	private List<Motorista> motoristaFiltrados;

	public PesquisaMotoristaBean() {
		
	}

	public void pesquisar() {
		motoristaFiltrados = repositorioMotorista.filtrados(motorista);
	}

	public void solicitacaoVagas() {
		
		
		motoristaFiltrados =  repositorioMotorista.ativo();
		
	}
	
	public void ativarOuDesativar() {
		
		if (motoristaSelecionada.getStatusMotorista().toString().equals("INATIVO")) {
			motoristaSelecionada.setStatusMotorista(StatusMotorista.ATIVO);
		} else {
			motoristaSelecionada.setStatusMotorista(StatusMotorista.INATIVO);
		}
		
		repositorioMotorista.CadastrarMotorista(motoristaSelecionada);
		
		FacesUtil.addInfoMessage(" Status do Motorista " + motoristaSelecionada.getNome() 
				+ " Alterado com sucesso para"+ motoristaSelecionada.getStatusMotorista().toString()+".");

	}
	
	public void excluir() {
		repositorioMotorista.remover(motoristaSelecionada);
		motoristaFiltrados.remove(motoristaSelecionada);
		
		FacesUtil.addInfoMessage("Motorista " + motoristaSelecionada.getNome() 
				+ " excluído com sucesso.");
	}
	

	public Motorista getMotoristaSelecionada() {
		return motoristaSelecionada;
	}

	public void setMotoristaSelecionada(Motorista motoristaSelecionada) {
		this.motoristaSelecionada = motoristaSelecionada;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public List<Motorista> getMotoristaFiltrados() {
		return motoristaFiltrados;
	}

}