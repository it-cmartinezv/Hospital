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
@Table(name = "Sintomas")
@NamedQueries({
	@NamedQuery(name=Sintoma.listarSintomas,query="SELECT s FROM Sintoma s")
})
public class Sintoma implements Serializable{
	
	public static final String listarSintomas = "Sintoma.listarSintomas";
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_SINTOMAS") 
	@SequenceGenerator(name="SEQ_SINTOMAS", sequenceName="SEQ_SINTOMAS",allocationSize=1)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=50)
	private String nombre;
	
	@Column(name="Descripcion",nullable = true,length=300)
	private String descripcion;

	public Sintoma() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sintoma(String nombre, String descripcion) {
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
		Sintoma other = (Sintoma) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
