package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.EmailException;

import com.Transpot.Fast.Repositorio.RepositorioMotorista;
import com.Transpot.Fast.Service.Email;
import com.Transpot.Fast.Service.Util;
import com.Transpot.Fast.Service.ValidarcoesService;
import com.Transpot.Fast.model.Endereco;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.StatusMotorista;
import com.Transpot.Fast.model.Veiculo;
import com.Transpot.Fast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Motorista motorista;

	@Inject
	Endereco endereco;

	@Inject
	Veiculo veiculo;

	@Inject
	RepositorioMotorista repositorioMotorista;

	@Inject
	ValidarcoesService validar;

	public void cadastrarMotorista() {

		try {

			if (motorista.getSenha() == null) {

				motorista.setSenha(Util.gerarSenha());

			}

			motorista.setDataDeCadastro(new Date());
			motorista.setStatusMotorista(StatusMotorista.INATIVO);
			motorista.setVeiculo(veiculo);
			motorista.setEndereco(endereco);

			if (motorista.getId() == 0) {
				if (validar.VerificaEmail(motorista.getEmail())) {
					if (validar.VerificaCpf(motorista.getCpf())) {
						repositorioMotorista.CadastrarMotorista(motorista);

						FacesUtil.addInfoMessage("Usuário salvo com sucesso");

						 enviarSenha();
						limpar();
					}
				}

			} else {
				repositorioMotorista.CadastrarMotorista(motorista);

				FacesUtil.addInfoMessage("Usuário atualizado com sucesso");

			}

		} catch (Exception e) {

		}
	}

	
	  public void enviarSenha() throws EmailException {
	  Email.sendEmail(motorista.getEmail(), motorista.getSenha()); }
	 

	public void limpar() {
		motorista = new Motorista();
		veiculo = new Veiculo();
		endereco = new Endereco();

	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

}
