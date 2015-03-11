package com.Transpot.Fast.Converte;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.Transpot.Fast.Repositorio.RepositorioInstituicao;
import com.Transpot.Fast.model.Instituicao;
import com.Transpot.Fast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Instituicao.class)
public class InstituicaoConverter implements Converter {

	//@Inject
	private RepositorioInstituicao repositorioInstituicao;
	
	public InstituicaoConverter() {
		repositorioInstituicao = CDIServiceLocator.getBean(RepositorioInstituicao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Instituicao retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = repositorioInstituicao.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Instituicao instituicao = (Instituicao) value;
			if (instituicao.getId() == 0)
				return null;
			else
				return String.valueOf(instituicao.getId());
		}
		
		return "";
	}

}
