package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.CamaEJB;
import entidades.Cama;
import entidades.Quirofano;

/**
 * 
 * @author Sebastian
 *
 */
@FacesConverter(value="camaConverter", forClass=Cama.class)
@Named("camaConverter")
public class CamaConverter implements Converter{

	@EJB
	private CamaEJB camaEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return camaEJB.buscar(Integer.parseInt(string));
	}
	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Cama) {
			Cama cama = (Cama) obj;
			return String.valueOf(cama.getId());
		}
		return null;
	}
}
