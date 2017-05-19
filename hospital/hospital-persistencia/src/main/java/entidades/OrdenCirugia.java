package entidades;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orden_Cirugia")
public class OrdenCirugia implements Serializable{
	@Id
	@Column(name="Id_Orden")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name="Citas_Medicas_Id_Cita")
	@ManyToOne(cascade={})
	private CitaMedica citaMedica;
	
	@JoinColumn(name="Cirugia_Id_Cirugia")
	@ManyToOne(cascade={})
	private Cirugia cirugia;

	public OrdenCirugia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenCirugia(CitaMedica citaMedica, Cirugia cirugia) {
		super();
		this.citaMedica = citaMedica;
		this.cirugia = cirugia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}

	public Cirugia getCirugia() {
		return cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
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
		OrdenCirugia other = (OrdenCirugia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
