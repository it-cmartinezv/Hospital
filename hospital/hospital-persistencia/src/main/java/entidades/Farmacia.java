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
@Table(name = "Farmacia")
@NamedQueries({
	@NamedQuery(name=Farmacia.listaFarmacias,query="SELECT f FROM Farmacia f"),
	@NamedQuery(name=Farmacia.busquedaNombre, query="SELECT f FROM Farmacia f where f.nombre=?1")
})
public class Farmacia implements Serializable{
	
	public static final String listaFarmacias = "Farmacia.listarFarmacia";
	public static final String busquedaNombre = "Farmacia.farmaciaNom";
	
	
	@Id
	@Column(name="Id_Farmacia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FARMACIA")
	@SequenceGenerator(name = "SEQ_FARMACIA", sequenceName = "SEQ_FARMACIA", allocationSize = 1)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=30)
	private String nombre;
	
	@Column(name="Direccion",nullable = false,length=30)
	private String direccion;
	
	@Column(name="Telefono",nullable = false,length=15)
	private String telefono;
	
	@JoinColumn(name="Ciudad_Id_Ciudad")
	@ManyToOne(cascade={})
	private Ciudad ciudad;
	
	@JoinColumn(name="Farmaceutico")
	@ManyToOne(cascade={})
	private Farmaceutico farmaceutico;

	public Farmacia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Farmacia(String nombre, String direccion, String telefono, Ciudad ciudad,
			Farmaceutico farmaceutico) {
		super();
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.farmaceutico = farmaceutico;
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

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Farmaceutico getFarmaceutico() {
		return farmaceutico;
	}



	public void setFarmaceutico(Farmaceutico farmaceutico) {
		this.farmaceutico = farmaceutico;
	}



	@Override
	public String toString() {
		return  nombre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Farmacia other = (Farmacia) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	

}
