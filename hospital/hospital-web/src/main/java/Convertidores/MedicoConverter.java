package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import beans.EJBUsuario;
import entidades.Medico;

@FacesConverter(value="medicoConverter",forClass=Medico.class)
@Named("medicoConverter")
public class MedicoConverter implements Converter{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return usuarioEJB.buscarMedicoID(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Medico) {
			Medico medico = (Medico) obj;
			return String.valueOf(medico.getId());
		}
		return null;
	}

}

