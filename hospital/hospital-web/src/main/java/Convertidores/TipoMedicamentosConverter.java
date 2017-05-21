package Convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import beans.MedicamentoEJB;
import entidades.TipoEps;
import entidades.TipoMedicamento;

@FacesConverter(value = "tipoMedicamentoConverter", forClass = TipoMedicamento.class)
@Named("tipoMedicamentoConverter")
public class TipoMedicamentosConverter implements Converter{
	
	@EJB
	MedicamentoEJB medicamentoEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		// TODO Auto-generated method stub
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return medicamentoEJB.buscarTipo(Integer.parseInt(string));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 instanceof TipoMedicamento) {
			TipoMedicamento TipoMe = (TipoMedicamento)arg2;
			return String.valueOf(TipoMe.getId());
		}
		return null;
	}
	

}
