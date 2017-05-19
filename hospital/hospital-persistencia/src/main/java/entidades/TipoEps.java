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
@Table(name = "Tipo_Eps")
@NamedQuery(name=TipoEps.listaTipos, query="SELECT t FROM TipoEps t")
public class TipoEps implements Serializable{
	
	public static final String listaTipos="TipoEps.listar";
	
	@Id
	@Column(name="Id_Tipo_Eps")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Tipo_Eps",nullable = false,length=20)
	private String tipo;

	public TipoEps() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoEps(String tipo) {
		super();
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		TipoEps other = (TipoEps) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tipo;
	}
	
	
}
