package entidades;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Pais")
@NamedQuery(name=Pais.LISTAR, query="SELECT p FROM Pais p")
public class Pais implements Serializable{
	
	public static final String LISTAR = "Pais.listar";
	
	@Id
	@Column(name="Id_Pais")
	private int idPais;
	
	@Column(name="Nombre",nullable = false,length=30)
	private String nombre;

	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pais(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
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
		result = prime * result + idPais;
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
		Pais other = (Pais) obj;
		if (idPais != other.idPais)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
