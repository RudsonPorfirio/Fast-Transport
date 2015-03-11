package com.Transpot.Fast.Converte;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.Transpot.Fast.Repositorio.RepositorioVeiculo;
import com.Transpot.Fast.model.Veiculo;
import com.Transpot.Fast.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Veiculo.class)
public class VeiculoConverter implements Converter {

	//@Inject
	private RepositorioVeiculo repositorioVeiculo;
	
	public VeiculoConverter() {
		repositorioVeiculo = CDIServiceLocator.getBean(RepositorioVeiculo.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Veiculo retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = repositorioVeiculo.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Veiculo veiculo = (Veiculo) value;
			//if (veiculo.getId() == 0)
			//	return "";
		//	else
				return String.valueOf(veiculo.getId());
		}
		
		return "";
	}

}
