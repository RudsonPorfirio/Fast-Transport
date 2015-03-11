package com.Transpot.Fast.Converte;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.Transpot.Fast.Repositorio.RepositorioEndereco;
import com.Transpot.Fast.model.Endereco;
import com.Transpot.Fast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter {

	//@Inject
	private RepositorioEndereco repositorioEndereco;
	
	public EnderecoConverter() {
		repositorioEndereco = CDIServiceLocator.getBean(RepositorioEndereco.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = repositorioEndereco.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Endereco endereco = (Endereco) value;
			if (endereco.getId() == 0)
				return null;
			else
				return String.valueOf(endereco.getId());
		}
		
		return "";
	}

}
