package entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import seguridad.AccesoRolPK;

@Entity
@Table(name="detalle_ordenmedicamente")
@IdClass(detalleOrdenMedicamentePK.class)
public class detalleOrdenMedicamente implements Serializable{
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Orden_Medicamento")
	private OrdenMedicamento ordenMedicamento;
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Medicamento")
	private Medicamento medicamento;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="estado")
	private boolean entregado;

	public detalleOrdenMedicamente() {
	}

	public detalleOrdenMedicamente(OrdenMedicamento ordenMedicamento, Medicamento medicamento, int cantidad,
			boolean entregado) {
		super();
		this.ordenMedicamento = ordenMedicamento;
		this.medicamento = medicamento;
		this.cantidad = cantidad;
		this.entregado = entregado;
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

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}	
}
