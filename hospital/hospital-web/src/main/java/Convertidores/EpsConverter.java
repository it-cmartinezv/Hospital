package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.EPSEJB;
import entidades.Eps;

@FacesConverter(value="epsConverter",forClass=Eps.class)
@Named("epsConverter")
public class EpsConverter implements Converter{
	
	@EJB
	private EPSEJB epsEJB;	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return epsEJB.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Eps) {
			Eps eps = (Eps)arg2;
			return String.valueOf(eps.getId());
		}
		return null;
	}

}