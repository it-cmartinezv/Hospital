package entidades;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Cirugia")
@NamedQueries({
	@NamedQuery(name=Cirugia.listarCirugiaQuirofano, query="SELECT c FROM Cirugia c WHERE c.quirofano =?1"),
	@NamedQuery(name=Cirugia.BYNOMBRE, query="SELECT c FROM Cirugia c WHERE c.nombre=?1"),
	@NamedQuery(name=Cirugia.listarCirugia, query="SELECT c FROM Cirugia c")
})
public class Cirugia implements Serializable{
	
	public static final String listarCirugiaQuirofano = "Cirugia.listarCirugiaQuirofano";
	public static final String BYNOMBRE = "Cirugia.bynombre";
	public static final String listarCirugia = "Cirugia.listarCirugia";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_CIRUGIA") 
	@SequenceGenerator(name="SEQ_CIRUGIA", sequenceName="SEQ_CIRUGIA",allocationSize=1) 
	@Column(name="Id_Cirugia")
	private int id;
	
	@Column(name="Nombre",nullable = false,length=50)
	private String nombre;
	
	@Column(name="Descripcion",nullable = false,length=100)
	private String descripcion;
	
	@JoinColumn(name="Quirofano")
	@ManyToOne(cascade={})
	private Quirofano quirofano;

	public Cirugia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cirugia(String nombre, String descripcion, Quirofano quirofano) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.quirofano = quirofano;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
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
		Cirugia other = (Cirugia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
