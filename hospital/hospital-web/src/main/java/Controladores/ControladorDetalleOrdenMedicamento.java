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

import beans.DetalleOrdenMediEJB;
import entidades.Medicamento;
import entidades.OrdenMedicamento;
import entidades.detalleOrdenMedicamente;

/**
 * @author AlejandroM
 *
 */
@ViewScoped
@Named("detalleOrden")
public class ControladorDetalleOrdenMedicamento implements Serializable {
	
	
	@EJB
	private DetalleOrdenMediEJB ordenMediEJB;
	
	
	private OrdenMedicamento ordenMedicamento;
	
	private Medicamento medicamento;
	
	
	private int cantidad;
	
	
	private boolean estado;
	
	
	private List<Medicamento> listaMEdicamenos;
	
	
	private List<OrdenMedicamento> listaOrdenes;
	
	private List<detalleOrdenMedicamente> listaDetalle;
	
	
	/**
	 * PostrConstruc
	 */
	@PostConstruct
	public void iniciar(){
		listaMEdicamenos = ordenMediEJB.listaMedicamento();
		listaOrdenes = ordenMediEJB.listarOrden();
		listaDetalle = ordenMediEJB.listaDetalle();
	}

	
	public void crearDetalle(){

		
		detalleOrdenMedicamente detalle = new detalleOrdenMedicamente(ordenMedicamento, medicamento, cantidad, true);
		
		System.out.println( medicamento );
		
		System.out.println( ordenMedicamento );
		ordenMediEJB.crearDetalleOrden(detalle);
		Messages.addFlashGlobalInfo("La orden se ha creado exitosamente");
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public List<detalleOrdenMedicamente> getListaDetalle() {
		return listaDetalle;
	}


	public void setListaDetalle(List<detalleOrdenMedicamente> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}


	public DetalleOrdenMediEJB getOrdenMediEJB() {
		return ordenMediEJB;
	}


	public void setOrdenMediEJB(DetalleOrdenMediEJB ordenMediEJB) {
		this.ordenMediEJB = ordenMediEJB;
	}


	public OrdenMedicamento getOrdenMedicamento() {
		return ordenMedicamento;
	}


	public void setOrdenMedicamento(OrdenMedicamento ordenMedicamento) {
		this.ordenMedicamento = ordenMedicamento;
	}


	public Medicamento getMedicamento() {
		return medicamento;
	}


	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public List<Medicamento> getListaMEdicamenos() {
		return listaMEdicamenos;
	}


	public void setListaMEdicamenos(List<Medicamento> listaMEdicamenos) {
		this.listaMEdicamenos = listaMEdicamenos;
	}


	public List<OrdenMedicamento> getListaOrdenes() {
		return listaOrdenes;
	}


	public void setListaOrdenes(List<OrdenMedicamento> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

}
