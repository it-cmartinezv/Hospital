package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import beans.EJBUsuario;
import entidades.Farmaceutico;

@FacesConverter(value="fConverter", forClass=Farmaceutico.class)
@Named("fConverter")
public class FarmaceuticoConverter implements Converter {
		
	@EJB
	EJBUsuario usuarioEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		// TODO Auto-generated method stub
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return usuarioEJB.buscarFarmaceutico(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 instanceof Farmaceutico) {
			Farmaceutico farmaceutico = (Farmaceutico)arg2;
			return String.valueOf(farmaceutico.getId());
		}
		return null;
	}
}
