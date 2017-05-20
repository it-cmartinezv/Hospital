/**
 * 
 */
package Controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

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
	private String nombre;
	private String direccion;
	private String telefono;
	private List<TipoEps> tiposEPS;
	private TipoEps tipo;
	
	@PostConstruct
	public void iniciar(){
	tiposEPS = epsEJB.listaTipos();
	}

	/**
	 * Metodo para crear una EPS
	 * 
	 */
	public void crear() {
		System.out.println("OEEEEE!!!!!!!mkon jajajaja no funciona :p !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Eps eps = new Eps(nombre, direccion, telefono, tipo);
		epsEJB.crear(eps);
		Messages.addFlashGlobalInfo("Se ha registrado exitosamente");

	}

	/**
	 * Metodo para buscar una eps
	 */
	public void buscar() {
		Eps eps = epsEJB.buscar(id);
		if (eps != null) {
			direccion = eps.getDireccion();
			telefono = eps.getTelefono();
			nombre = eps.getNombre();
			// tiposEPS = eps.getTipoEps();
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna eps con este dato");
		}
	}

	/**
	 * Metodo para editar una eps
	 */
	public void editar() {
		Eps eps = epsEJB.buscar(id);
		if (eps != null) {
			eps.setDireccion(direccion);
			eps.setTelefono(telefono);
			eps.setNombre(nombre);
			eps.setTipoEps(tipo);
			epsEJB.editar(eps);
			Messages.addFlashGlobalInfo("Se ha editado correctamente");
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna eps con este dato");
		}
	}

	/**
	 * Metodo para eliminar una EPS
	 */
	public void eliminar() {
		Eps eps = epsEJB.buscar(id);
		if (eps != null) {
			epsEJB.eliminar(eps);
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna EPS con estos datos");
		}
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
	
	public TipoEps getTipo() {
		return tipo;
	}

	public void setTipo(TipoEps tipo) {
		this.tipo = tipo;
	}

	public List<TipoEps> getTiposEPS() {
		return tiposEPS;
	}

	public void setTiposEPS(List<TipoEps> tiposEPS) {
		this.tiposEPS = tiposEPS;
	}

}
