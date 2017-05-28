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

import beans.MedicamentoEJB;
import entidades.Medicamento;

/**
 * @author AlejandroM
 *
 */
@FacesConverter(value="medicamentoConverte", forClass=Medicamento.class)
@Named("medicamentoConverte")
public class MedicamentoConverter implements Converter{
	
	@EJB
	private MedicamentoEJB medicamentoEJB;
	

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return medicamentoEJB.buscar(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Medicamento) {
			Medicamento medi = (Medicamento)arg2;
			return String.valueOf(medi.getId());
		}
		return null;
	}

}
