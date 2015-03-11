package com.Transpot.Fast.Bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.Transpot.Fast.Repositorio.RepositorioUsuario;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private RepositorioUsuario repositorioUsuario;
	@Inject
	private Usuario usuario;
	@Inject
	private Usuario usuarioSelecionada;
	
	private String json;
	
	private List<Usuario> usuarioFiltrados;
	
	public PesquisaUsuarioBean() {
	
		usuario = new Usuario();
	}
	
	public void pesquisar() {
		
		usuarioFiltrados= repositorioUsuario.filtrados(usuario);
	}
	

	
	
	public void excluir() {
		repositorioUsuario.remover(usuarioSelecionada);
		usuarioFiltrados.remove(usuarioSelecionada);
		
		FacesUtil.addInfoMessage("uSUARIO " + usuarioSelecionada.getNome() 
				+ " excluído com sucesso.");
	}


	 public void gerarJson()
	  {
	    this.usuarioFiltrados = this.repositorioUsuario.filtrados(this.usuario);
	    
	    JSONArray jsonArray = new JSONArray();
	    for (Usuario usuario : this.usuarioFiltrados) {
	      try
	      {
	        JSONObject jsonObject = new JSONObject();
	        

	        jsonObject.put("nome", usuario.getNome());
	        jsonObject.put("email", usuario.getEmail());
	        jsonObject.put("senha", criptografia(usuario.getSenha()));
	        jsonArray.put(jsonObject);
	      }
	      catch (JSONException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    this.json = jsonArray.toString();
	  }
	  
	  public static String criptografia(String original)
	  {
	    String senha = null;
	    try
	    {
	      MessageDigest algoritmo = MessageDigest.getInstance("MD5");
	      byte[] messageDigest = algoritmo.digest(original.getBytes("UTF-8"));
	      StringBuilder hexString = new StringBuilder();
	      byte[] arrayOfByte1;
	      int j = (arrayOfByte1 = messageDigest).length;
	      for (int i = 0; i < j; i++)
	      {
	        byte b = arrayOfByte1[i];
	        hexString.append(String.format("%02X", new Object[] { Integer.valueOf(0xFF & b) }));
	      }
	      senha = hexString.toString();
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	      e.printStackTrace();
	    }
	    catch (UnsupportedEncodingException e)
	    {
	      e.printStackTrace();
	    }
	    System.out.println("Senha normal: " + original + " - Senha criptografada: " + senha);
	    return senha;
	  }
	
	
	public List<Usuario> getUsuarioFiltrados() {
		return usuarioFiltrados;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getUsuarioSelecionada() {
		return usuarioSelecionada;
	}

	public void setUsuarioSelecionada(Usuario usuarioSelecionada) {
		this.usuarioSelecionada = usuarioSelecionada;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}


	


}