package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.Transpot.Fast.Repositorio.RepositorioInstituicao;
import com.Transpot.Fast.Repositorio.RepositorioSolicitacao;
import com.Transpot.Fast.model.EnderecoDestino;
import com.Transpot.Fast.model.Instituicao;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.Solicitacao;
import com.Transpot.Fast.model.StatusSolicitacao;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroSolicitacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Instituicao instituicaoSelecionada;

	@Inject
	private Motorista motoristaSelecionada;
	
	@Inject
	private RepositorioSolicitacao repositorioSolicitacao;
	
	@Inject
	private Solicitacao solicitacao;

	
	
	@Inject
	EnderecoDestino enderecoDestino;

	private List<Instituicao> listaInstituicao;

	public void salvarSolicitacao() {
		System.out.println("salvarSolicitacao ...");
		
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Usuario u = (Usuario) session.getAttribute("UsuarioLogado");

		solicitacao.setDataSolicitacao(new Date());
		solicitacao.setMotorista(motoristaSelecionada);
		solicitacao.setStatusSolicitacao(StatusSolicitacao.ESPERA);
		solicitacao.setUsuario(u);
		solicitacao.setEnderecoDestino(enderecoDestino);
	
		repositorioSolicitacao.CadastrarSolicitacao(solicitacao);
		FacesUtil.addInfoMessage("Solicitação Registrada com sucesso.");

	}

	@Inject
	private RepositorioInstituicao repositorioInstituicao;

	public void preencherCampos() {
		System.out.println("preencher campos ...");

		// endereco = new Endereco();

		if (instituicaoSelecionada == null) {
		//	endereco = new Endereco();
			FacesUtil.addErrorMessage("Selecione uma Instituição");
		} else {

			enderecoDestino.setBairro( instituicaoSelecionada.getEndereco().getBairro());
			enderecoDestino.setCep( instituicaoSelecionada.getEndereco().getCep());
			enderecoDestino.setCidade( instituicaoSelecionada.getEndereco().getCidade());
			enderecoDestino.setLogradouro( instituicaoSelecionada.getEndereco().getLogradouro());
			enderecoDestino.setIntituicao(instituicaoSelecionada);
		}
	}

	public void inicializar() {
		System.out.println("inicializando...");

		try {
			listaInstituicao = new ArrayList<Instituicao>();
			listaInstituicao = repositorioInstituicao.todas();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<Instituicao> getListaInstituicao() {
		return listaInstituicao;
	}

	public void setListaInstituicao(List<Instituicao> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
	}

	public Instituicao getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Instituicao instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}


	public Motorista getMotoristaSelecionada() {
		return motoristaSelecionada;
	}

	public void setMotoristaSelecionada(Motorista motoristaSelecionada) {
		this.motoristaSelecionada = motoristaSelecionada;
	}

	public EnderecoDestino getEnderecoDestino() {
		return enderecoDestino;
	}

	public void setEnderecoDestino(EnderecoDestino enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	
	
	
}
