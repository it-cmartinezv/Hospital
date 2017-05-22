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
@Table(name = "Examen")
@NamedQueries({
	@NamedQuery(name=Examen.listarExamen, query="SELECT e FROM Examen e"),
	@NamedQuery(name=Examen.BYNOMBRE, query="SELECT e FROM Examen e WHERE e.nombre=?1")
})
public class Examen implements Serializable{
	
	public static final String listarExamen = "Examen.listarExamen";
	public static final String BYNOMBRE = "Examen.bynombre";
	
	@Id
	@Column(name="Id_Examen")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_EXAMEN") 
	@SequenceGenerator(name="SEQ_EXAMEN", sequenceName="SEQ_EXAMEN",allocationSize=1) 
	private int id;
	
	@Column(name="Nombre",nullable = false,length=30)
	private String nombre;
	
	@Column(name="Descripcion",nullable = false,length=200)
	private String descripcion;
	

	public Examen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Examen(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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
		Examen other = (Examen) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
