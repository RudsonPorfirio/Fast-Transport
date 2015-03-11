package com.Transpot.Fast.Autenticacao;



import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.Transpot.Fast.Repositorio.RepositorioAdmin;
import com.Transpot.Fast.Repositorio.RepositorioMotorista;
import com.Transpot.Fast.Repositorio.RepositorioUsuario;
import com.Transpot.Fast.model.Admin;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.FacesUtil;
import com.Transpot.Fast.util.jsf.SessionUtil;


@Named
@SessionScoped
public class AutenticadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	@Inject
	private RepositorioUsuario repositorioUsuario;
	@Inject
	private RepositorioMotorista repositorioMotorista;
	@Inject
	private RepositorioAdmin repositorioAdmin;
	@Inject
	private Admin admin;
	@Inject
	private Motorista motorista;
	@Inject
	private Usuario usuario;

	
	public String autentica() {
		System.out.println("autentica..");
		System.out.println("Email : " + email + "\n Senha : " + senha);

		admin = repositorioAdmin.porEmail(email);

		if (admin != null && admin.getSenha().equals(senha)) {
			System.out.println(admin.toString());

			
			
			SessionUtil.setParam("AdminLogado", this.admin);
			SessionUtil.setParam("tipo", "admin");
			System.out.println("antes");
			return "/admin/Principal?faces-redirect=true";
		}

		usuario = repositorioUsuario.porEmail(email);
		if (usuario != null && usuario.getSenha().equals(senha)) {
			System.out.println("If usuario");

			
			SessionUtil.setParam("tipo", "usuario");
			SessionUtil.setParam("UsuarioLogado", this.usuario);
			return "/usuario/Principal.xhtml?faces-redirect=true";

		}
		
		motorista = repositorioMotorista.porEmail(email);
		if (motorista != null && motorista.getSenha().equals(senha)) {
			System.out.println("If motorist");

			SessionUtil.setParam("tipo", "motorista");
			SessionUtil.setParam("MotoristaLogado", this.motorista);
			return "/motorista/Principal.xhtml?faces-redirect=true";

		} else {
			// se o usuário e senha são inválidos, exibe uma mensagem de erro e
			// devolve a string /login, que referencia a página login.xhtml
			

			FacesUtil.addErrorMessage("usuário e/ou senha inválidos");
			// return "/Login?faces-redirect=true";
			return null;

		}

	}

	/**
	 * M�todo que efetua o logout
	 * 
	 * @return
	 */
	public String registraSaida() {

		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		try {
			
		session.removeAttribute("AdminLogado");
		session.removeAttribute("UsuarioLogado");
		session.removeAttribute("MotoristaLogado");
		
		
		session.removeAttribute("tipo");
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/Login?faces-redirect=true";
	}

	// GETTERS E SETTERS
	public String getUsuario() {
		return email;
	}

	public void setUsuario(String usuario) {
		this.email = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}