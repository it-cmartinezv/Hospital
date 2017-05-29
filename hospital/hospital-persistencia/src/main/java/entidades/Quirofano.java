package entidades;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Quirofano")
@NamedQueries({
	@NamedQuery(name=Quirofano.listarQuirofano, query="SELECT q FROM Quirofano q"),
	@NamedQuery(name=Quirofano.BYNOMBRE, query="SELECT q FROM Quirofano q WHERE q.nombre=?1")
})
public class Quirofano implements Serializable{
	
	public static final String listarQuirofano = "Quirofano.listarQuirofano";
	public static final String BYNOMBRE = "Quirofano.BYNOMBRE";
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QUIROFANO")
	@SequenceGenerator(name = "SEQ_QUIROFANO", sequenceName = "SEQ_QUIROFANO", allocationSize = 1)
	private int id;
	
	@Column(name="nombre",nullable = true,length=100)
	private String nombre;
	
	@Column(name="Descripcion",nullable = true,length=300)
	private String descripcion;
	
	@Column(name="Estado")
	private boolean estado;

	public Quirofano() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quirofano(String descripcion, boolean estado) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		Quirofano other = (Quirofano) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
