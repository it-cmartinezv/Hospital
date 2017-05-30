package Controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.CitaMedicaEJB;
import beans.HospitalizacionEJB;
import beans.OrdenHospitalizacionEJB;
import entidades.CitaMedica;
import entidades.Hospitalizacion;
import entidades.OrdenHospitalizacion;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@Named("ordenHospitalizacionController")
@ViewScoped
public class OrdenHospitalizacionController implements Serializable{

	@EJB
	private OrdenHospitalizacionEJB ordenHospitalizacionEJB;
	
	@EJB
	private CitaMedicaEJB citaMedicaEJB;
	
	@EJB
	private HospitalizacionEJB hospitalizacionEJB;
	
	private String descripcion;
	
	private Date fecha;
	
	private CitaMedica citaMedica;
	
	private Hospitalizacion hospitalizacion;
	
	private List<Hospitalizacion> listarHospitalizaciones;
	
	private List<CitaMedica> listarCitasMedicas;
	
	private List<OrdenHospitalizacion> listarOrdenesHospitalizaciones;
	
	@PostConstruct
	public void inicializar(){
		try{
			listarHospitalizaciones = hospitalizacionEJB.listarHospitalizaciones();
			listarCitasMedicas = citaMedicaEJB.listarCitasMedicas();
			listarOrdenesHospitalizaciones = ordenHospitalizacionEJB.listarOrdenHospitalizacion();
		}catch(excepciones.ExcepcionFuncional e){
			Messages.addFlashGlobalError(e.getMessage());
		}
		
	}
	
	/**
	 * Registrar
	 */
	public void registrar(){
		try{
			OrdenHospitalizacion ordenHospitalizacion = new OrdenHospitalizacion();
			ordenHospitalizacion.setCitaMedica(citaMedica);
			ordenHospitalizacion.setHospitalizacion(hospitalizacion);
			ordenHospitalizacionEJB.crear(ordenHospitalizacion);
			Messages.addFlashGlobalInfo("La orden de la hospitalizacion resgistrada exitosamente");
			
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}

	public Hospitalizacion getHospitalizacion() {
		return hospitalizacion;
	}

	public void setHospitalizacion(Hospitalizacion hospitalizacion) {
		this.hospitalizacion = hospitalizacion;
	}

	public List<Hospitalizacion> getListarHospitalizaciones() {
		return listarHospitalizaciones;
	}

	public void setListarHospitalizaciones(List<Hospitalizacion> listarHospitalizaciones) {
		this.listarHospitalizaciones = listarHospitalizaciones;
	}

	public List<CitaMedica> getListarCitasMedicas() {
		return listarCitasMedicas;
	}

	public void setListarCitasMedicas(List<CitaMedica> listarCitasMedicas) {
		this.listarCitasMedicas = listarCitasMedicas;
	}

	public List<OrdenHospitalizacion> getListarOrdenesHospitalizaciones() {
		return listarOrdenesHospitalizaciones;
	}

	public void setListarOrdenesHospitalizaciones(List<OrdenHospitalizacion> listarOrdenesHospitalizaciones) {
		this.listarOrdenesHospitalizaciones = listarOrdenesHospitalizaciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
