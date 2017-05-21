/**
 * 
 */
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

import beans.EPSEJB;
import entidades.Eps;
import entidades.Farmaceutico;
import entidades.TipoEps;

/**
 * @author AlejandroM
 *
 */
@ViewScoped
@Named("EPS")
public class ControladorEPS implements Serializable {

	@EJB
	private EPSEJB epsEJB;
	
	private int id;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String nombre;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Direccion No valida")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String direccion;
	
	@Pattern(regexp="[0-9]*",message="Solo numeros")
	@Length(min=5,max=20,message="longitud entre 5 y 20")
	private String telefono;
	
	private List<TipoEps> listaTiposEPS;
	private TipoEps tipoEPS;
	
	private List<Eps> listaEPS;
	
	@PostConstruct
	public void iniciar(){
		listaTiposEPS = epsEJB.listaTipos();
		listaEPS = epsEJB.listar();
		
	}

	/**
	 * Metodo para crear una EPS
	 * 
	 */
	public void crear() {
		
		Eps eps = new Eps(nombre, direccion, telefono, tipoEPS);
		epsEJB.crear(eps);
		limpiar();
		Messages.addFlashGlobalInfo("Se ha registrado exitosamente");

	}

	/**
	 * Metodo para buscar una eps
	 */
	public void buscar() {
		Eps eps = epsEJB.buscarPorNombre(nombre);
		if (eps != null) {
			direccion = eps.getDireccion();
			telefono = eps.getTelefono();
			nombre = eps.getNombre();
			tipoEPS = eps.getTipoEps();
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna eps con este nombre");
		}
	}

	/**
	 * Metodo para editar una eps
	 */
	public void editar() {
		Eps eps = epsEJB.buscarPorNombre(nombre);
		if (eps != null) {
			eps.setDireccion(direccion);
			eps.setTelefono(telefono);
			eps.setNombre(nombre);
			eps.setTipoEps(tipoEPS);
			epsEJB.editar(eps);
			limpiar();
			Messages.addFlashGlobalInfo("Se ha editado correctamente");
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna eps con este dato");
		}
	}

	/**
	 * Metodo para eliminar una EPS
	 */
	public void eliminar() {
		Eps eps = epsEJB.buscarPorNombre(nombre);
		if (eps != null) {
			epsEJB.eliminar(eps);
			Messages.addFlashGlobalInfo("Se ha eliminado correctamente la EPS");
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna EPS con estos datos");
		}
	}
	
	public void limpiar(){
		nombre = "";
		direccion = "";
		telefono = "";
		tipoEPS = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EPSEJB getEpsEJB() {
		return epsEJB;
	}

	public void setEpsEJB(EPSEJB epsEJB) {
		this.epsEJB = epsEJB;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<TipoEps> getListaTiposEPS() {
		return listaTiposEPS;
	}

	public void setListaTiposEPS(List<TipoEps> listaTiposEPS) {
		this.listaTiposEPS = listaTiposEPS;
	}

	public TipoEps getTipoEPS() {
		return tipoEPS;
	}

	public void setTipoEPS(TipoEps tipoEPS) {
		this.tipoEPS = tipoEPS;
	}

	public List<Eps> getListaEPS() {
		return listaEPS;
	}

	public void setListaEPS(List<Eps> listaEPS) {
		this.listaEPS = listaEPS;
	}
	
	
}
