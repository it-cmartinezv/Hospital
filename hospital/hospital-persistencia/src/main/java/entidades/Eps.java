package entidades;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "EPS")
@NamedQuery(name=Eps.LISTAR, query="SELECT e FROM Eps e")
public class Eps implements Serializable{
	
	public static final String LISTAR = "Eps.listar";
	
	@Id
	@Column(name="Id_Eps")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=30)
	private String nombre;
	
	@Column(name="Direccion",nullable = false,length=30)
	private String direccion;
	
	@Column(name="Telefono",nullable = false,length=15)
	private String telefono;
	
	@JoinColumn(name="Id_Tipo_Eps")
	@ManyToOne(cascade={})
	private TipoEps tipoEps;

	public Eps() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Eps(String nombre, String direccion, String telefono, TipoEps tipoEps) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipoEps = tipoEps;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoEps getTipoEps() {
		return tipoEps;
	}

	public void setTipoEps(TipoEps tipoEps) {
		this.tipoEps = tipoEps;
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
		Eps other = (Eps) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
