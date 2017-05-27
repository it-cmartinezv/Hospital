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
import javax.persistence.Table;

@Entity
@Table(name = "Enfermedad")
@NamedQueries({
	@NamedQuery(name=Enfermedad.listarEnfermedadTratamiento, query="SELECT e FROM Enfermedad e WHERE e.tratamiento = ?1")
})
public class Enfermedad implements Serializable{
	
	public static final String listarEnfermedadTratamiento = "Enfermedad.listarEnfermedadTratamiento";
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=50)
	private String nombre;
	
	@Column(name="Descripcion",nullable = false,length=300)
	private String descripcion;
	
	@JoinColumn(name="Tratamiento")
	@ManyToOne(cascade={})
	private Tratamiento tratamiento;

	public Enfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enfermedad(String nombre, String descripcion, Tratamiento tratamiento) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tratamiento = tratamiento;
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

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
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
		Enfermedad other = (Enfermedad) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
