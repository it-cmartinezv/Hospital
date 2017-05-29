package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.HospitalizacionEJB;
import entidades.Cama;
import entidades.Hospitalizacion;
/**
 * 
 * @author Sebastian
 *
 */
@FacesConverter(value="hospitalizacionConverter", forClass=Hospitalizacion.class)
@Named("hospitalizacionConverter")
public class HospitalizacionConverter implements Converter{

	@EJB
	private HospitalizacionEJB hospitalizacionEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return hospitalizacionEJB.buscar(Integer.parseInt(string));
	}
	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Hospitalizacion) {
			Hospitalizacion hospitalizacion = (Hospitalizacion) obj;
			return String.valueOf(hospitalizacion.getId());
		}
		return null;
	}
	
}
