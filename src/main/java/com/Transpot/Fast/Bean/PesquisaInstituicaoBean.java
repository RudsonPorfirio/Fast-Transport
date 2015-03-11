package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.Transpot.Fast.Repositorio.RepositorioInstituicao;
import com.Transpot.Fast.model.Instituicao;
import com.Transpot.Fast.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaInstituicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositorioInstituicao repositorioInstituicao;
	@Inject
	private Instituicao instituicao;
	@Inject
	private Instituicao instituicaoSelecionada;
	
	private List<Instituicao> instituicaoFiltrados;
	
	
	
	
	public PesquisaInstituicaoBean() {
		instituicao = new Instituicao();
		
	}
	
	public void pesquisar() {
		
		instituicaoFiltrados= repositorioInstituicao.filtrados(instituicao);
						
	}
	
	public void excluir() {
		
		repositorioInstituicao.remover(instituicaoSelecionada);
		instituicaoFiltrados.remove(instituicaoSelecionada);
		
		FacesUtil.addInfoMessage("Instituição " + instituicaoSelecionada.getNome() 
				+ " excluído com sucesso.");
	}

	
	public Instituicao getInstituicao() {
		return instituicao;
	}

	public List<Instituicao> getInstituicaoFiltrados() {
		return instituicaoFiltrados;
	}

	
	
	public Instituicao getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Instituicao instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}






	


}