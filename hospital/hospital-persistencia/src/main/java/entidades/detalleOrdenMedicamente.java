package entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import seguridad.AccesoRolPK;

@Entity
@Table(name="detalle_ordenmedicamento")
@IdClass(detalleOrdenMedicamentePK.class)
@NamedQueries({ 
	@NamedQuery(name = detalleOrdenMedicamente.LISTA_DETALLES, query = "SELECT dm FROM detalleOrdenMedicamente dm")
})
public class detalleOrdenMedicamente implements Serializable{
	
	public static final String LISTA_DETALLES = "detalleOrdenMedicamente.listar";
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Orden_Medicamento")
	private OrdenMedicamento ordenMedicamento;
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Medicamento")
	private Medicamento medicamento;
	
	@Column(name="cantidad",nullable = false)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicamento == null) ? 0 : medicamento.hashCode());
		result = prime * result + ((ordenMedicamento == null) ? 0 : ordenMedicamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		detalleOrdenMedicamente other = (detalleOrdenMedicamente) obj;
		if (medicamento == null) {
			if (other.medicamento != null)
				return false;
		} else if (!medicamento.equals(other.medicamento))
			return false;
		if (ordenMedicamento == null) {
			if (other.ordenMedicamento != null)
				return false;
		} else if (!ordenMedicamento.equals(other.ordenMedicamento))
			return false;
		return true;
	}	
}
