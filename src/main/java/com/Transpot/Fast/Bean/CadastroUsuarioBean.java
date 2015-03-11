package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;

import com.Transpot.Fast.Repositorio.RepositorioUsuario;
import com.Transpot.Fast.Service.Email;
import com.Transpot.Fast.Service.Util;
import com.Transpot.Fast.Service.ValidarcoesService;
import com.Transpot.Fast.model.Endereco;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Usuario usuario;

	@Inject
	Endereco endereco;

	@Inject
	RepositorioUsuario repositorioUsuario;

	@Inject
	ValidarcoesService validar;

	private void limpar() {
		endereco = new Endereco();
		usuario = new Usuario();
	}

	public void dadoSession() {

		System.out.println("dadoSession");

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Usuario u = (Usuario) session.getAttribute("UsuarioLogado");

		this.usuario = u;

		this.endereco = u.getEndereco();

	}

	public void cadastrarUsuario() throws EmailException  {

		System.out.println("cadastrarUsuario ....");

		boolean enviar = false;

		if (usuario.getSenha() == null) {
			usuario.setSenha(Util.gerarSenha());
			enviar = true;
		}

		usuario.setDataDeCadastro(new Date());
		usuario.setEndereco(endereco);

		if (usuario.getId() == 0) {

			if (validar.VerificaEmail(usuario.getEmail())) {
				if (validar.VerificaCpf(usuario.getCpf())) {
					repositorioUsuario.CadastrarUsuario(usuario);
					if (enviar) {
						 enviarSenha();
					}

					FacesUtil.addInfoMessage("Usuario salva com sucesso");
					limpar();
				}
			}
		} else {
			repositorioUsuario.CadastrarUsuario(usuario);

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("UsuarioLogado", usuario);

			FacesUtil.addInfoMessage("Usuario atualizado com sucesso");
			limpar();
		}
	}

	
	  public void enviarSenha() throws EmailException {
	  Email.sendEmail(usuario.getEmail(), usuario.getSenha()); }
	 
	
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
