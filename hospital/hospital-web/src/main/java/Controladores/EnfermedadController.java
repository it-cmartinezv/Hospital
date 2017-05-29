package Controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.EnfermedadEJB;
import beans.SintomaEJB;
import beans.TratamientoEJB;
import entidades.Enfermedad;
import entidades.Sintoma;
import entidades.SintomaEnfermedad;
import entidades.Tratamiento;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@ViewScoped
@Named("enfermedadController")
public class EnfermedadController implements Serializable {
	
	@EJB
	private SintomaEJB sintomaEJB;
	
	@EJB
	private TratamientoEJB tratamientoEJB;
	
	@EJB
	private EnfermedadEJB enfermedadEJB;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String nombre;

	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=100,message="longitud entre 4 y 100")
	String descripcion;
	
	private Tratamiento tratamiento;
	
	private Sintoma sintoma;
	
	private Enfermedad enfermedad;
	
	private List<Tratamiento> tratamientos;
	
	private List<Enfermedad> enfermedades;
	
	private List<SintomaEnfermedad> sintomasEnfermadades;
	
	private List<Sintoma> sintomas;
	
	@PostConstruct
	public void inicializar(){
		try{
			enfermedades = enfermedadEJB.listarEnfermedad();
			sintomas = sintomaEJB.listarSintoma();
			tratamientos = tratamientoEJB.listarTratamiento();
			
		}catch(excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Registrar
	 */
	public void registrar(){
		try{
			Enfermedad enfermedad = new Enfermedad();
			enfermedad.setNombre(nombre);
			enfermedad.setDescripcion(descripcion);
			enfermedad.setTratamiento(tratamiento);
			enfermedadEJB.crear(enfermedad);
			limpiar();
			Messages.addFlashGlobalInfo("Enfermedad "+nombre+" registrado exitosamente");
			
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void limpiar(){
		nombre = "";
		descripcion = "";
	}
	
	public void buscar(){
		try{
			Enfermedad enfermedad = enfermedadEJB.enfermedadByNombre(nombre);
			if(enfermedad != null ){
				nombre = enfermedad.getNombre();
				tratamiento = enfermedad.getTratamiento();
				descripcion = enfermedad.getDescripcion();
			}else{
				Messages.addFlashGlobalError("No se ha encontrado ninguna enfermedad con este nombre");
			}
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Metodo para editar un enfermedad
	 */
	public void editar(){
		try{
			Enfermedad enfermedad = enfermedadEJB.enfermedadByNombre(nombre);
			if(enfermedad != null){
				enfermedad.setNombre(nombre);
				enfermedad.setTratamiento(tratamiento);
				enfermedad.setDescripcion(descripcion);
				enfermedadEJB.editar(enfermedad);
				limpiar();
				Messages.addFlashGlobalInfo("La enfermedad "+nombre+" se ha actualizado exitosamente");
			}else{
				Messages.addGlobalError("No se ha encontrado ninguna enfermedad para actualizar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
		
		public void eliminar(){
			try{
				enfermedadEJB.eliminar(nombre);
				Messages.addFlashGlobalInfo("La enfermedad "+nombre+" se ha eliminado exitosamente");
				limpiar();
			}catch(ExcepcionNegocio ex){
				Messages.addFlashGlobalError(ex.getMessage());
			}catch(Exception e){
				Messages.addFlashGlobalError(e.getMessage());
			}
		}
		
		
	// Eliminar sintoma de una enfermedad
	public void eliminarSintomaEnfermedad(){
		
	}
		
	// Registrar Sintoma enfermedad
	public void registrarSintomaEnfermedad(){
		
	}
		
		
		

		public TratamientoEJB getTratamientoEJB() {
			return tratamientoEJB;
		}

		public void setTratamientoEJB(TratamientoEJB tratamientoEJB) {
			this.tratamientoEJB = tratamientoEJB;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Tratamiento getTratamiento() {
			return tratamiento;
		}

		public void setTratamiento(Tratamiento tratamiento) {
			this.tratamiento = tratamiento;
		}

		public List<Sintoma> getSintomas() {
			return sintomas;
		}

		public void setSintomas(List<Sintoma> sintomas) {
			this.sintomas = sintomas;
		}

		public List<Tratamiento> getTratamientos() {
			return tratamientos;
		}

		public void setTratamientos(List<Tratamiento> tratamientos) {
			this.tratamientos = tratamientos;
		}

		public List<Enfermedad> getEnfermedades() {
			return enfermedades;
		}

		public void setEnfermedades(List<Enfermedad> enfermedades) {
			this.enfermedades = enfermedades;
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

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
}
