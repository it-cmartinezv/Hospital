package Controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import beans.ReporteEJB;

@ViewScoped
@Named("reporteController")
public class ReporteController implements Serializable{
	
	@EJB
	private ReporteEJB reporteEJB;
	
	private List<String> bymedico;
	
	@PostConstruct
	public void inicializar(){
		
	}

	public List<String> getBymedico() {
		return bymedico;
	}

	public void setBymedico(List<String> bymedico) {
		this.bymedico = bymedico;
	}
}
