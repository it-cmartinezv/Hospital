package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.QuirofanoEJB;
import entidades.Ciudad;
import entidades.Quirofano;

@FacesConverter(value="quirofanoConverter",forClass=Quirofano.class)
@Named("quirofanoConverter")
public class QuirofanoConverter implements Converter{

	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return quirofanoEJB.buscar(Integer.parseInt(string));
	}
	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Quirofano) {
			Quirofano quirofano = (Quirofano) obj;
			return String.valueOf(quirofano.getId());
		}
		return null;
	}
	
}
