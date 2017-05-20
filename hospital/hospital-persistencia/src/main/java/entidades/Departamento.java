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
@Table(name = "Departamento")
@NamedQueries({
	@NamedQuery(name=Departamento.LISTAR, query="SELECT d FROM Departamento d"),
	@NamedQuery(name=Departamento.BYPAIS, query="SELECT d FROM Departamento d WHERE d.pais = ?1")
})
public class Departamento implements Serializable{
	
	public static final String LISTAR = "Departamento.listar";
	public static final String BYPAIS = "Departamento.bypais";
	
	@Id
	@Column(name="Id_Departamento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=30)
	private String nombre;
	
	@JoinColumn(name="Pais_Id_Pais")
	@ManyToOne(cascade={})
	private Pais pais;

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departamento(String nombre, Pais pais) {
		super();
		this.nombre = nombre;
		this.pais = pais;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		Departamento other = (Departamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
