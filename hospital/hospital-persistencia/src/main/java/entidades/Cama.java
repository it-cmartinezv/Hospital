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

/**
 * Yo estuve aqui haciendo commit
 * @author Sebastian
 *
 */
@Entity
@Table(name = "Camas")
@NamedQueries({
	@NamedQuery(name=Cama.listarCama, query = "SELECT c FROM Cama c")
})
public class Cama implements Serializable{
	
	public static final String listarCama = "Cama.listarCama";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_CAMA_HOSPITALIZACION") 
	@SequenceGenerator(name="SEQ_CAMA_HOSPITALIZACION", sequenceName="SEQ_CAMA_HOSPITALIZACION",allocationSize=1)
	@Column(name="Id_Cama")
	private int id;
	
	@Column(name="Descripcion",nullable = false,length=50)
	private String descripcion;
	
	/**
	 * True = Desocupada 
	 * False = Ocupada
	 */
	@Column(name="Estado")
	private boolean estado;

	public Cama() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cama(String descripcion, boolean estado) {
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
		Cama other = (Cama) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
