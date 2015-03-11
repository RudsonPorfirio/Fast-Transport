package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.Transpot.Fast.Repositorio.RepositorioInstituicao;
import com.Transpot.Fast.model.Endereco;
import com.Transpot.Fast.model.Instituicao;
import com.Transpot.Fast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroInstituicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Instituicao instituicao;
	
	@Inject
	Endereco endereco;
	
	@Inject 
	RepositorioInstituicao repositorioInstituicao;
	

	public String cadastrarInstituicao() {
		
		instituicao.setEndereco(endereco);
		instituicao.setDataCadastro(new Date());
		repositorioInstituicao.CadastrarInstituicao(instituicao);
		
		
		limpar();
		FacesUtil.addInfoMessage("Instituição salva com sucesso");
		return  "admin/CadastroInstituicao.xhtml";
		
		
		
	}
	
	
	private void limpar(){
		endereco = new Endereco();
		instituicao = new Instituicao();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

}
