package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.LocalizacionEJB;
import entidades.Ciudad;

@FacesConverter(value="ciudadConverter",forClass=Ciudad.class)
@Named("ciudadConverter")
public class CiudadConverter implements Converter{
	
	@EJB
	private LocalizacionEJB licalizacionEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return licalizacionEJB.buscarCiudad(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Ciudad) {
			Ciudad ciudad = (Ciudad) obj;
			return String.valueOf(ciudad.getId());
		}
		return null;
	}

}
