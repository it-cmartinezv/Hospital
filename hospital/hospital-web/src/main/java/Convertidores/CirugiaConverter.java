package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import beans.CamaEJB;
import beans.CirugiaEJB;
import entidades.Cama;
import entidades.Cirugia;
import entidades.Quirofano;

/**
 * 
 * @author Carlos Martinez
 *
 */
@FacesConverter(value="cirugiaConverter", forClass=Cama.class)
@Named("cirugiaConverter")
public class CirugiaConverter implements Converter{

	@EJB
	private CirugiaEJB cirugiaEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return cirugiaEJB.buscar(Integer.parseInt(string));
	}
	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Cirugia) {
			Cirugia cirugia = (Cirugia) obj;
			return String.valueOf(cirugia.getId());
		}
		return null;
	}
}