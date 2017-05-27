package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import beans.SintomaEJB;
import entidades.Sintoma;

@FacesConverter(value="sintomaConverter",forClass=Sintoma.class)
@Named("sintomaConverter")
public class SintomaConverter implements Converter{
	
	@EJB
	private SintomaEJB sintomaEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return sintomaEJB.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Sintoma) {
			Sintoma sintoma = (Sintoma) obj;
			return String.valueOf(sintoma.getId());
		}
		return null;
	}

}