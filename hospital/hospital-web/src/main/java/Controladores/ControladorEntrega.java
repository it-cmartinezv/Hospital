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

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.EntregaMedicamentoEJB;
import beans.MedicamentoEJB;
import entidades.CitaMedica;
//import entidades.EntregaMedicamento;
import entidades.Medicamento;

/**
 * @author AlejandroM
 *
 */
@ViewScoped
@Named("entrega")
public class ControladorEntrega implements Serializable {

	@EJB
	EntregaMedicamentoEJB ejb;
	
	@EJB
	MedicamentoEJB medicamentoEJB;

	private CitaMedica citaMedica;
	private Medicamento  medicamento;

	private int cantidad;
	private Date fechaReclamacion;
//	private List<EntregaMedicamento> listaEntregas;

	@PostConstruct
//	public void iniciar() {
//		listaEntregas = ejb.listaEntregas();
//
//	}

	/**
	 * Metodo para entregar un medicamento a un paciente validando que la fecha
	 * de la entrega sea igual a la fecha de la cita
	 */
	public void entregar() {

		if (fechaReclamacion.equals(citaMedica.getFecha())) { // aca comparo que la fecha de la entrega 
			//sea la misma que la de la cita
			if (cantidad> medicamento.getCantidad()) {// aca se compara que la cantidad solicitada no sea 
				// mayor a la que hay en bodega
				Messages.addFlashGlobalInfo("No tenemos esta cantidad disponible en la bodega");
			} else {
				
	//int cantidadTotal =  medicamento.getCantidad()- cantidad ; //Aca resto la cantidad solicitada de la cantidad de la bodega
				medicamento.setCantidad(medicamento.getCantidad()- cantidad );

				// Guardo La entrega del medicamento y edito el objeto medicamento con la nueva cantidad
//				EntregaMedicamento entregaMedicamento = new EntregaMedicamento(fechaReclamacion, cantidad);
//				ejb.entregarMedicamento(entregaMedicamento);
				medicamentoEJB.editar(medicamento);
				
				Messages.addFlashGlobalInfo("Se ha entregado el medicamento exitosamente");
				
			}
			
		} else {
			Messages.addFlashGlobalInfo("Se ha generado un error al entregar el medicamento");
		}

	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaReclamacion() {
		return fechaReclamacion;
	}

	public void setFechaReclamacion(Date fechaReclamacion) {
		this.fechaReclamacion = fechaReclamacion;
	}

//	public List<EntregaMedicamento> getListaEntregas() {
//		return listaEntregas;
//	}
//
//	public void setListaEntregas(List<EntregaMedicamento> listaEntregas) {
//		this.listaEntregas = listaEntregas;
//	}

}
