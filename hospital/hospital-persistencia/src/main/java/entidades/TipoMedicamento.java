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
@Table(name = "Tipo_Medicamento")
@NamedQueries({
	@NamedQuery(name=TipoMedicamento.listar,query="SELECT t FROM TipoMedicamento t")
})
public class TipoMedicamento implements Serializable{
	
	public static final String listar= "TipoMedicamento.listarMedicamento";
	
	@Id
	@Column(name="Id_Tipo_Medicamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_MEDICAMENTO")
	@SequenceGenerator(name = "SEQ_TIPO_MEDICAMENTO", sequenceName = "SEQ_TIPO_MEDICAMENTO", allocationSize = 1)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=20)
	private String nombre;
	
	@Column(name="Descripcion",nullable = false,length=100)
	private String descripcion;

	public TipoMedicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoMedicamento(String nombre, String descripcion) {
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
		TipoMedicamento other = (TipoMedicamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	

}
