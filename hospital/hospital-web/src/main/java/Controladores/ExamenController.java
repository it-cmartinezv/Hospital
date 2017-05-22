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

import beans.ExamenEJB;
import entidades.Cirugia;
import entidades.Examen;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@Named("examenController")
@ViewScoped
public class ExamenController implements Serializable{

	@EJB
	private ExamenEJB examenEJB;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String nombre;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 100")
	String descripcion;
	
	private List<Examen> examenes;
	
	@PostConstruct
	public void inicializar(){
		try{
			examenes = examenEJB.listarExamen(); 
		}catch(excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Registrar
	 */
	public void registrar(){
		try{
			Examen examen = new Examen();
			examen.setNombre(nombre);
			examen.setDescripcion(descripcion);
			examenEJB.crear(examen);
			limpiar();
			Messages.addFlashGlobalInfo("Examen "+nombre+" registrado exitosamente");
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void limpiar(){
		nombre = "";
		descripcion = "";
	}
	
	/**
	 * Metodo para editar cirugia
	 */
	public void editar(){
		try{
			Examen examen = examenEJB.examenByNombre(nombre);
			if(examen != null){
				 examen.setNombre(nombre);
				 examen.setDescripcion(descripcion);
				 examenEJB.editar(examen);
				limpiar();
				Messages.addFlashGlobalInfo("El examen "+nombre+"  se ha actualizado exitosamente");
			}else{
				Messages.addGlobalError("No se ha encontrado ningun examen para actualizar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Metodo para eliminar examen
	 */
	public void eliminar(){
		try{
			examenEJB.eliminar(nombre);
			Messages.addFlashGlobalInfo("El examen "+nombre+"  se ha eliminado exitosamente");
			limpiar();
		}catch(ExcepcionNegocio ex){
			Messages.addFlashGlobalError(ex.getMessage());
		}catch(Exception e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Buscar
	 */
	public void buscar(){
		try{
			Examen examen = examenEJB.examenByNombre(nombre);
			if(examen != null){
				nombre = examen.getNombre();
				descripcion = examen.getDescripcion();
			}else{
				Messages.addFlashGlobalError("No se ha encontrado ningun examen con este nombre");
			}
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	
	
	
}
