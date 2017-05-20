package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.LocalizacionEJB;
import entidades.Ciudad;
import entidades.Pais;

@FacesConverter(value="paisConverter",forClass=Pais.class)
@Named("paisConverter")
public class PaisConverter implements Converter{
	
	@EJB
	private LocalizacionEJB licalizacionEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return licalizacionEJB.buscarPais(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Pais) {
			Pais pais = (Pais)arg2;
			return String.valueOf(pais.getIdPais());
		}
		return null;
	}

}
