package com.Transpot.Fast.Service;


import java.io.Serializable;

import javax.inject.Inject;

import com.Transpot.Fast.Repositorio.RepositorioMotorista;
import com.Transpot.Fast.Repositorio.RepositorioUsuario;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.FacesUtil;

public class ValidarcoesService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	RepositorioUsuario repositorioUsuario;
	@Inject
	RepositorioMotorista repositorioMotorista;
	/**
	 * 
	 * @param Email
	 * 
	 * Verifica se O email ja esta cadastrado
	 * 
	 * @return
	 */
	
	
	
	public Boolean VerificaEmail(String email) {
		Usuario usuarioExistente = repositorioUsuario.porEmail(email);
		Motorista motoristaExistente = repositorioMotorista.porEmail(email);	
		
		if (usuarioExistente != null) {
			email();
			throw new NegocioException("Já existe um usuario com o email informado.")
			
			;
		}else if (motoristaExistente != null) {
			email();
			throw new NegocioException("Já existe um usuario com o email informado.");
		}else{
			return true;
		}
		
	}
	
	public void email(){
		
		
		FacesUtil.addInfoMessage("Já existe um usuario com o email informado.");
		
	}
	
	public Boolean VerificaCpf(String cpf) {
		Usuario usuarioExistente = repositorioUsuario.porEmail(cpf);
		Motorista motoristaExistente = repositorioMotorista.porEmail(cpf);	
		
		if (usuarioExistente != null) {
			throw new NegocioException("Já existe um usuario com o CPF informado.")
			
			;
		}else if (motoristaExistente != null) {
			throw new NegocioException("Já existe um usuario com o CPF informado.");
		}else{
			return true;
		}
		
	}
	
	
}
