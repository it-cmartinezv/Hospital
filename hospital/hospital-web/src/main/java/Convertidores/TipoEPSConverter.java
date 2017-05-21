package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.EPSEJB;
import entidades.Eps;
import entidades.TipoEps;

@FacesConverter(value = "tipoEPSConverter", forClass = TipoEps.class)
@Named("tipoEPSConverter")
public class TipoEPSConverter implements Converter {

	@EJB
	EPSEJB epsejb;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		// TODO Auto-generated method stub
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return epsejb.buscarTipo(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 instanceof TipoEps) {
			TipoEps Teps = (TipoEps)arg2;
			return String.valueOf(Teps.getId());
		}
		return null;
	}

}
