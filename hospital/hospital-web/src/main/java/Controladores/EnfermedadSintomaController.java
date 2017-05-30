package Controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import beans.EnfermedadEJB;
import beans.SintomaEJB;
import beans.SintomaEnfermedadEJB;
import entidades.Enfermedad;
import entidades.Sintoma;
import entidades.SintomaEnfermedad;

/**
 * 
 * @author Sebastian
 *
 */
@ViewScoped
@Named("enfermedadSintomaController")
public class EnfermedadSintomaController implements Serializable {
		
	@EJB
	private SintomaEJB sintomaEJB;
	
	@EJB
	private EnfermedadEJB enfermedadEJB;
	
	@EJB
	private SintomaEnfermedadEJB sintomaEnfermedadEJB;
	
	private Sintoma sintoma;
	
	private Enfermedad enfermedad;
	
	private SintomaEnfermedad sintomaEnfermedad;
	
	private List<Sintoma> sintomas;
	
	private List<Enfermedad> enfermedades;

	private List<SintomaEnfermedad> sintomasEnfermedades;
	
	@PostConstruct
	public void inicializar(){
		try{
			sintomasEnfermedades = sintomaEnfermedadEJB.listar();
			enfermedades = enfermedadEJB.listarEnfermedad();
			sintomas = sintomaEJB.listarSintoma();
		}catch(excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void sintomasByEnfermedad(){
		sintomasEnfermedades = sintomaEnfermedadEJB.listarSintomaByEnfermedad(enfermedad);
	}
	
	/**
	 * Registrar
	 */
	public void registrar(){
		try{
			SintomaEnfermedad sintomaEnfermedad = new SintomaEnfermedad();
			sintomaEnfermedad.setEnfermedad(enfermedad);
			sintomaEnfermedad.setSintoma(sintoma);
			sintomaEnfermedadEJB.crear(sintomaEnfermedad);
			Messages.addFlashGlobalInfo("Sintoma de enfermedad registrado exitosamente");
			sintomasEnfermedades = sintomaEnfermedadEJB.listarSintomaByEnfermedad(enfermedad);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public List<Sintoma> getSintomas() {
		return sintomas;
	}

	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}

	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public List<SintomaEnfermedad> getSintomasEnfermedades() {
		return sintomasEnfermedades;
	}

	public void setSintomasEnfermedades(List<SintomaEnfermedad> sintomasEnfermedades) {
		this.sintomasEnfermedades = sintomasEnfermedades;
	}

	public SintomaEnfermedad getSintomaEnfermedad() {
		return sintomaEnfermedad;
	}

	public void setSintomaEnfermedad(SintomaEnfermedad sintomaEnfermedad) {
		this.sintomaEnfermedad = sintomaEnfermedad;
	}
	
	
	
}
