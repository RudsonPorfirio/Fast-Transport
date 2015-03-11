package com.Transpot.Fast.Converte;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.Transpot.Fast.Repositorio.RepositorioUsuario;
import com.Transpot.Fast.model.Usuario;
import com.Transpot.Fast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	//@Inject
	private RepositorioUsuario repositorioUsuarios;
	
	public UsuarioConverter() {
		repositorioUsuarios = CDIServiceLocator.getBean(RepositorioUsuario.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = repositorioUsuarios.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario produto = (Usuario) value;
			if (produto.getId() == 0)
				return null;
			else
				return String.valueOf(produto.getId());
		}
		
		return "";
	}

}
