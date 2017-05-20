package Convertidores;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.LocalizacionEJB;
import entidades.Ciudad;
import entidades.Departamento;

@FacesConverter(value="departamentoConverter",forClass=Departamento.class)
@Named("departamentoConverter")
public class DepartamentoConverter implements Converter{
	
	@EJB
	private LocalizacionEJB licalizacionEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return licalizacionEJB.buscarDepartamento(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Departamento) {
			Departamento departamento = (Departamento) obj;
			return String.valueOf(departamento.getId());
		}
		return null;
	}
}
