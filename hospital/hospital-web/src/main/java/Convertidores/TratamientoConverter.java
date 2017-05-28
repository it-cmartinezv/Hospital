package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.TratamientoEJB;
import entidades.Sintoma;
import entidades.Tratamiento;

@FacesConverter(value="tratamientoConverter", forClass=Tratamiento.class)
@Named("tratamientoConverter")
public class TratamientoConverter implements Converter{

	@EJB
	private TratamientoEJB tratamientoEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return tratamientoEJB.buscar(Integer.parseInt(string));
	}
	
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Tratamiento) {
			Tratamiento tratamiento = (Tratamiento) obj;
			return String.valueOf(tratamiento.getId());
		}
		return null;
	}


	
	
}
