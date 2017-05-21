package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.FarmaciaEJB;
import entidades.Farmacia;


@FacesConverter(value = "farmaciaConverter", forClass=Farmacia.class)
@Named("farmaciaConverter")
public class FarmaciaConverter implements Converter{
	
	@EJB
	FarmaciaEJB farmaciaEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		// TODO Auto-generated method stub
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return farmaciaEJB.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 instanceof Farmacia) {
			Farmacia farma = (Farmacia)arg2;
			return String.valueOf(farma.getId());
		}
		return null;
	}

}
