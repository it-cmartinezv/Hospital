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
@Table(name = "Orden_Hospitalizacion")
public class OrdenHospitalizacion implements Serializable{
	@Id
	@Column(name="Id_Orden")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name="Citas_Medicas_Id_Cita")
	@ManyToOne(cascade={})
	private CitaMedica citaMedica;
	
	@JoinColumn(name="Hospitalizacion_Id_Hospitalizacion")
	@ManyToOne(cascade={})
	private Hospitalizacion hospitalizacion;

	public OrdenHospitalizacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenHospitalizacion(CitaMedica citaMedica, Hospitalizacion hospitalizacion) {
		super();
		this.citaMedica = citaMedica;
		this.hospitalizacion = hospitalizacion;
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

	public Hospitalizacion getHospitalizacion() {
		return hospitalizacion;
	}

	public void setHospitalizacion(Hospitalizacion hospitalizacion) {
		this.hospitalizacion = hospitalizacion;
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
		OrdenHospitalizacion other = (OrdenHospitalizacion) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
