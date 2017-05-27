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

import beans.CitaMedicaEJB;
import entidades.CitaMedica;


/**
 * @author AlejandroMata
 *
 */
@FacesConverter(value="citaConverter", forClass = CitaMedica.class)
@Named("citaConverter")
public class CitaConverter implements Converter {

	@EJB
	private CitaMedicaEJB citaMedicaEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return citaMedicaEJB.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof CitaMedica) {
			CitaMedica cita = (CitaMedica)arg2;
			return String.valueOf(cita.getId());
		}
		return null;
	}

}
