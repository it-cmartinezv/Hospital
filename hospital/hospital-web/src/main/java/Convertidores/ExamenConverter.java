/**
 * 
 */
package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.ExamenEJB;

import entidades.Examen;

/**
 * @author AlejandroM
 *
 */
@FacesConverter(value="ExamenConverter", forClass= Examen.class)
@Named("ExamenConverter")
public class ExamenConverter implements Converter {
	
	@EJB
	ExamenEJB ejb;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return ejb.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Examen) {
			Examen ex = (Examen)arg2;
			return String.valueOf(ex.getId());
		}
		return null;
	}

}
