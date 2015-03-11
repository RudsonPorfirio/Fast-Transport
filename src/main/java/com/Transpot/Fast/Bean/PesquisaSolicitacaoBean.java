package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.Transpot.Fast.Repositorio.RepositorioContrato;
import com.Transpot.Fast.Repositorio.RepositorioSolicitacao;
import com.Transpot.Fast.model.Contrato;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.Solicitacao;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.SessionUtil;

@Named
@ViewScoped
public class PesquisaSolicitacaoBean implements Serializable {

	@Inject
	private RepositorioSolicitacao repositorioSolicitacao;
	@Inject
	private RepositorioContrato repositorioContrato;

	private List<Solicitacao> listSolicitacao;

	@Inject
	private Solicitacao solicitacaoSelecionada;

	private static final long serialVersionUID = 1L;

	public String verSolicitacao() {

		Usuario u = (Usuario) SessionUtil.getParam("UsuarioLogado");

		List<Solicitacao> lisSolicitacaos;

		lisSolicitacaos = repositorioSolicitacao.todos();

		for (Solicitacao solicitacao : lisSolicitacaos) {

			if (solicitacao.getUsuario() == u
					|| solicitacao.getUsuario().getNome().equals(u.getNome())) {

				return "/usuario/MinhaSolicitacoes?faces-redirect=true";

			}

		}

		return "";

	}

	public void motoSolicitacao() {

		Motorista u = (Motorista) SessionUtil.getParam("MotoristaLogado");

		System.out.println(u.toString());

		listSolicitacao = new ArrayList<Solicitacao>();
		List<Solicitacao> listaux = new ArrayList<Solicitacao>();
		listaux = repositorioSolicitacao.todos();

		for (Solicitacao solicitacao : listaux) {

			System.out.println("--------------------------");
			System.out.println("For ... \n Motorista"
					+ solicitacao.getMotorista().getNome());
			if (solicitacao.getMotorista() == u
					|| solicitacao.getMotorista().getNome().equals(u.getNome())) {

				listSolicitacao.add(solicitacao);
				System.out.println(listSolicitacao.size());

			}
		}

	}

	public void aceitarSolicitacacao() {

		
		Contrato contrato = new Contrato();
		
		contrato.setMotorista(solicitacaoSelecionada.getMotorista());
		contrato.setUsuario(solicitacaoSelecionada.getUsuario());

		repositorioContrato.CadastrarSolicitacao(contrato);

		repositorioSolicitacao.remover(solicitacaoSelecionada);

	}

	public List<Solicitacao> getListSolicitacao() {
		return listSolicitacao;
	}

	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

}
