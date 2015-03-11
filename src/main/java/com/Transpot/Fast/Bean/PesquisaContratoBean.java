package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.Transpot.Fast.Repositorio.RepositorioContrato;
import com.Transpot.Fast.model.Contrato;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.SessionUtil;

@Named
@ViewScoped
public class PesquisaContratoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	RepositorioContrato repositorioContrato;

	List<Contrato> contratosMotorista = new ArrayList<Contrato>();

	public List<Contrato> todos() {

		return null;
	}

	public void porMotorista() {

		Motorista m = (Motorista) SessionUtil.getParam("MotoristaLogado");

		contratosMotorista = repositorioContrato.porMotorista(m.getId() + "");

		for (Contrato contrato : contratosMotorista) {

			System.out.println(contrato.toString());
		}
	}

	public Contrato porUsuario(Usuario usuario) {

		return null;
	}

	public void remover(Contrato contrato) {

	}

	public List<Contrato> getContratoMotorista() {
		return contratosMotorista;
	}

	public void setContratoMotorista(List<Contrato> contraroMotorista) {
		this.contratosMotorista = contraroMotorista;
	}

	
	
	
}