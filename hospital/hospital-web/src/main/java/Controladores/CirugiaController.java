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

import beans.CirugiaEJB;
import beans.QuirofanoEJB;
import entidades.Cirugia;
import entidades.Quirofano;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@Named("cirugiaController")
@ViewScoped
public class CirugiaController implements Serializable{

	@EJB
	private CirugiaEJB cirugiaEJB;
	
	@EJB
	private QuirofanoEJB quirofanoEJB;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String nombre;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String descripcion;
	
	private Quirofano quirofano;
	
	private List<Quirofano> quirofanos;
	
	private List<Cirugia> cirugias;
	
	@PostConstruct
	public void inicializar(){
		try{
			quirofanos = quirofanoEJB.listarQuirofano();
			cirugias = cirugiaEJB.listarCirugia();
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Registrar
	 */
	public void registrar(){
		try{
			Cirugia cirugia = new Cirugia();
			cirugia.setNombre(nombre);
			cirugia.setDescripcion(descripcion);
			cirugia.setQuirofano(quirofano);
			cirugiaEJB.crear(cirugia);
			limpiar();
			Messages.addFlashGlobalInfo("Cirugia "+nombre+" registrado exitosamente");
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Buscar
	 */
	public void buscar(){
		try{
			Cirugia cirugia = cirugiaEJB.cirugiaByNombre(nombre);
			if(cirugia != null){
				nombre = cirugia.getNombre();
				descripcion = cirugia.getDescripcion();
				quirofano = cirugia.getQuirofano();
			}else{
				Messages.addFlashGlobalError("No se ha encontrado ninguna cirugia con este nombre");
			}
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
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
			Cirugia cirugia = cirugiaEJB.cirugiaByNombre(nombre);
			if(cirugia != null){
				 cirugia.setNombre(nombre);
				 cirugia.setDescripcion(descripcion);
				 cirugia.setQuirofano(quirofano);
				 cirugiaEJB.editar(cirugia);
				limpiar();
				Messages.addFlashGlobalInfo("La cirugia "+nombre+"  se ha actualizado exitosamente");
			}else{
				Messages.addGlobalError("No se ha encontrado ninguna cirugia para actualizar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void eliminar(){
		try{
			cirugiaEJB.eliminar(nombre);
			Messages.addFlashGlobalInfo("La cirugia "+nombre+"  se ha eliminado exitosamente");
			limpiar();
		}catch(ExcepcionNegocio ex){
			Messages.addFlashGlobalError(ex.getMessage());
		}catch(Exception e){
			Messages.addGlobalError(e.getMessage());
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

	public List<Quirofano> getQuirofanos() {
		return quirofanos;
	}

	public void setQuirofanos(List<Quirofano> quirofanos) {
		this.quirofanos = quirofanos;
	}

	public List<Cirugia> getCirugias() {
		return cirugias;
	}

	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}
	
	
	
}
