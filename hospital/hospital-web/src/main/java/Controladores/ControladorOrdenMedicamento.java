/**
 * 
 */
package Controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.OrdenMedicamentoEJB;
import entidades.CitaMedica;
import entidades.Medicamento;
import entidades.OrdenMedicamento;

/**
 * @author AlejandroM
 *
 */

@ViewScoped
@Named("ordenMedicamento")
public class ControladorOrdenMedicamento implements Serializable {

	@EJB
	private OrdenMedicamentoEJB ordenMedicamentoEJB;

	private int id;

	private Date fecha;

	@Pattern(regexp = "[a-zA-Z ]*", message = "posologia No valida")
	@Length(min = 0, max = 300, message = "longitud entre 4 y 50")
	private String posologia;

	private boolean estado;

	private CitaMedica citaMedica;

	private List<CitaMedica> listaCitas;
	
	private List<OrdenMedicamento> listaOrdenes;

	@PostConstruct
	public void inciar() {

		listaCitas = ordenMedicamentoEJB.listaCitas();
		listaOrdenes = ordenMedicamentoEJB.listaOrden();
	}

	/**
	 * Metodo para crear una entrega a un medicamento
	 */
	public void crearOrden() {

		OrdenMedicamento ordenMedi = new OrdenMedicamento(fecha, posologia, true, citaMedica);
		ordenMedicamentoEJB.registrarOrden(ordenMedi);
		Messages.addFlashGlobalInfo("La orden se ha creado exitosamente");
	}

	/**
	 * Metodo para editar una orden del medicamento
	 */
	public void editarOrden() {
		System.out.println("Entre al boton");
		OrdenMedicamento orden = ordenMedicamentoEJB.buscarId(id);
		if (orden != null) {
			orden.setCitaMedica(citaMedica);
			orden.setEstado(true);
			orden.setFecha(fecha);
			orden.setPosologia(posologia);
			ordenMedicamentoEJB.editar(orden);
			Messages.addFlashGlobalInfo("La orden se ha editado exitosamente");

		} else {
			Messages.addFlashGlobalInfo("No existe ninguna orde con este numero");
		}
	}

	/**
	 * Metodo para eliminar una orden de un medicamento
	 */
	public void eliminarOrden() {
		OrdenMedicamento orden = ordenMedicamentoEJB.buscarId(id);
		if (orden != null) {
			ordenMedicamentoEJB.eliminar(orden);
			Messages.addFlashGlobalInfo("La orden se ha eliminado exitosamente");
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna orden con este dato");
		}

	}

	/**
	 * Metodo para buscar una orden medica
	 */
	public void buscarOrden() {
		OrdenMedicamento orden = ordenMedicamentoEJB.buscarId(id);
		System.out.println("Entre al boton");
		if (orden != null) {
			citaMedica = orden.getCitaMedica();
			fecha = orden.getFecha();
			posologia = orden.getPosologia();
		} else {
			Messages.addFlashGlobalInfo("No hay ninguna orden con este dato");
		}
	}

	public List<CitaMedica> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(List<CitaMedica> listaCitas) {
		this.listaCitas = listaCitas;
	}

	public OrdenMedicamentoEJB getOrdenMedicamentoEJB() {
		return ordenMedicamentoEJB;
	}

	public void setOrdenMedicamentoEJB(OrdenMedicamentoEJB ordenMedicamentoEJB) {
		this.ordenMedicamentoEJB = ordenMedicamentoEJB;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrdenMedicamento> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<OrdenMedicamento> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

}
