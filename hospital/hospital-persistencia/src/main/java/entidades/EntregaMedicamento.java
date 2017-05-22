package entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Entrega_Medicamento")
@NamedQueries({ 
	@NamedQuery(name = EntregaMedicamento.entregasListar, query = "SELECT e FROM EntregaMedicamento e")
	//Ahi depronto genera problema porque la entregaMedicamento le falta el raya piso entrega_medicamento
	//pero si lo pongo asi va a generar un error
	
})
public class EntregaMedicamento implements Serializable{
	
	public static final String entregasListar = "EntregaMedicamento.listar";
	
	@Id
	@Column(name="Id_Entrega")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENTREGA_MEDICAMENTO")
	@SequenceGenerator(name = "SEQ_ENTREGA_MEDICAMENTO", sequenceName = "SEQ_ENTREGA_MEDICAMENTO", allocationSize = 1)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha",nullable = false)
	private Date fecha;
	
	@Column(name="Cantidad")
	private int cantidad;

	public EntregaMedicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntregaMedicamento(Date fecha, int cantidad) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		EntregaMedicamento other = (EntregaMedicamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	
}
