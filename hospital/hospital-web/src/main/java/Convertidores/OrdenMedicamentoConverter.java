/**
 * 
 */
package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.OrdenMedicamentoEJB;
import entidades.Eps;
import entidades.OrdenMedicamento;

/**
 * @author AlejandroM
 *
 */
@FacesConverter(value="OrdenMedicamentoConverter", forClass= entidades.OrdenMedicamento.class)
@Named("OrdenMedicamentoConverter")
public class OrdenMedicamentoConverter implements Converter {
	
	@EJB
	private OrdenMedicamentoEJB ordenMedicamentoEJB;
	

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return ordenMedicamentoEJB.buscar(Integer.parseInt(string)); // Aca debo de revisar ese buscar no esta muy logico
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof OrdenMedicamento) {
			OrdenMedicamento orden = (OrdenMedicamento)arg2;
			return String.valueOf(orden.getId());
		}
		return null;
	}

}
