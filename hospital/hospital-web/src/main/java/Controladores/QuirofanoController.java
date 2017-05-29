package Controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.QuirofanoEJB;
import entidades.Quirofano;

@Named("quirofanoController")
@ViewScoped
public class QuirofanoController implements Serializable{
	
	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	private String nombre;
	
	private String descripcion;

	private List<Quirofano> quirofanos;
	
	@PostConstruct
	public void inicializar(){
		try{
			listarQuirofanos();
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Crear
	 */
	public void crear(){
		try{
			Quirofano q = quirofanoEJB.buscarByNombre(nombre);
			if(q == null){
				q = new Quirofano();
				q.setEstado(false); // Desocupado
				q.setDescripcion(descripcion);
				q.setNombre(nombre);
				quirofanoEJB.crear(q);
				Messages.addFlashGlobalInfo("El quirofano "+nombre+" se ha creado exitosamente");
				listarQuirofanos();
				limpiar();
			}else{
				Messages.addFlashGlobalInfo("Ya existe un quirofano con el nombre "+nombre);
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * limpiar campos
	 */
	public void limpiar(){
		nombre = "";
		descripcion = "";
	}
	
	/**
	 * Buscar
	 */
	public void buscar(){
		try{
			Quirofano q = quirofanoEJB.buscarByNombre(nombre);
			if(q != null){
				nombre = q.getNombre();
				descripcion = q.getDescripcion();
			}else{
				Messages.addFlashGlobalInfo("No existe ningun quirofano con el nombre "+nombre);
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Crear
	 */
	public void editar(){
		try{
			Quirofano q = quirofanoEJB.buscarByNombre(nombre);
			if(q != null){
				q.setDescripcion(descripcion);
				quirofanoEJB.editar(q);
				Messages.addFlashGlobalInfo("El quirofano "+nombre+" se ha editado exitosamente");
				listarQuirofanos();
				limpiar();
			}else{
				Messages.addFlashGlobalInfo("No existe un quirofano con el nombre "+nombre);
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Crear
	 */
	public void eliminar(){
		try{
			Quirofano q = quirofanoEJB.buscarByNombre(nombre);
			if(q != null){
				quirofanoEJB.eliminar(q);
				listarQuirofanos();
				limpiar();
			}else{
				Messages.addFlashGlobalInfo("No existe un quirofano con el nombre "+nombre);
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void listarQuirofanos(){
		quirofanos = quirofanoEJB.listarQuirofano();
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

	public List<Quirofano> getQuirofanos() {
		return quirofanos;
	}

	public void setQuirofanos(List<Quirofano> quirofanos) {
		this.quirofanos = quirofanos;
	}
}
