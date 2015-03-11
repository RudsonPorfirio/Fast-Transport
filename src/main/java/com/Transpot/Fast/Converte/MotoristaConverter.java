package com.Transpot.Fast.Converte;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.Transpot.Fast.Repositorio.RepositorioMotorista;
import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Motorista.class)
public class MotoristaConverter implements Converter {

	//@Inject
	private RepositorioMotorista repositorioMotorista;
	
	public MotoristaConverter() {
		repositorioMotorista = CDIServiceLocator.getBean(RepositorioMotorista.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Motorista retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = repositorioMotorista.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Motorista motorista = (Motorista) value;
			if (motorista.getId() == 0)
				return null;
			else
				return String.valueOf(motorista.getId());
		}
		
		return "";
	}

}
