package Controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
/**
 * 
 * @author Sebastian
 *
 */
import org.omnifaces.util.Messages;

import beans.CamaEJB;
import entidades.Cama;
import excepciones.ExcepcionFuncional;
import excepciones.ExcepcionNegocio;
@Named("camaController")
@ViewScoped
public class CamaController implements Serializable {

	@EJB
	private CamaEJB camaEJB;
	
	int id;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String descripcion;
	
	/**
	 * False = desocupado
	 * True = ocupado
	 */
	private String estado;
	
	private List<Cama> camas;
	
	@PostConstruct
	public void inicializar(){
		try{
			camas = camaEJB.listarCamas();
		}catch(excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Registrar cama
	 */
	public void registrar(){
		try{
			Cama cama = new Cama();
			cama.setDescripcion(descripcion);
			cama.setEstado(false);
			camaEJB.crear(cama);
			Messages.addFlashGlobalInfo("Cama registrada exitosamente");
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void limpiar(){
		descripcion = "";
	}
	
	public void buscar(){
		try{
			Cama cama = camaEJB.buscar(id);
			if(cama !=null){
				descripcion = cama.getDescripcion();
				if(cama.isEstado()){
					estado = "Ocupado";
				}else{
					estado = "Desocupado";
				}
			}else{
				Messages.addFlashGlobalError("No se ha encontrado ninguna cama");
			}
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void editar(){
		try{
			Cama cama = camaEJB.buscar(id);
			if(cama != null){
				cama.setDescripcion(descripcion);
				camaEJB.editar(cama);
				limpiar();
				Messages.addFlashGlobalInfo("La cama se ha actualizado exitomasamente");
			}else{
				Messages.addGlobalError("No se ha encontrado ninguna cama para actualizar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void eliminar(){
		try{
			camaEJB.eliminar(id);
			Messages.addFlashGlobalInfo("La cama se ha eliminado exitosamente");
			limpiar();
		}catch(ExcepcionFuncional ex){
			Messages.addFlashGlobalError(ex.getMessage());
			
		}catch(Exception e){
			Messages.addGlobalError(e.getMessage());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Cama> getCamas() {
		return camas;
	}

	public void setCamas(List<Cama> camas) {
		this.camas = camas;
	}
	
	
}
