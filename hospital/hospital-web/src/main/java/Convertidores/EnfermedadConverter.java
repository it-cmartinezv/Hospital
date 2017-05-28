package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import beans.EnfermedadEJB;
import entidades.Enfermedad;

@FacesConverter(value="enfermedadConverter", forClass=Enfermedad.class)
@Named("enfermedadConverter")
public class EnfermedadConverter implements Converter {
		
	@EJB
	EnfermedadEJB enfermedadEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		// TODO Auto-generated method stub
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return enfermedadEJB.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 instanceof Enfermedad) {
			Enfermedad enfermedad = (Enfermedad)arg2;
			return String.valueOf(enfermedad.getId());
		}
		return null;
	}
}