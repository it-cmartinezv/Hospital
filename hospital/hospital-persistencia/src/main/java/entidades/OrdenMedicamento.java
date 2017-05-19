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
@Table(name = "Orden_Medicamento")
public class OrdenMedicamento implements Serializable{
	@Id
	@Column(name="Id_Orden")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * POSOLOGIA = EL PACIENTE DEBE TOMAR 5 PASTAS DIARIAS DE ACETAMINOFEN
	 */
	@Column(name="posologia",nullable = false,length=300)
	private String posologia;
	
	@JoinColumn(name="Citas_Medicas_Id_Cita")
	@ManyToOne(cascade={})
	private CitaMedica citaMedica;
	
	@JoinColumn(name="Medicamento_Id_Medicamento")
	@ManyToOne(cascade={})
	private Medicamento medicamento;

	@JoinColumn(name="En")
	@ManyToOne(cascade={})
	private EntregaMedicamento entregaMedicamento;

	public OrdenMedicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenMedicamento(CitaMedica citaMedica, Medicamento medicamento, EntregaMedicamento entregaMedicamento) {
		super();
		this.citaMedica = citaMedica;
		this.medicamento = medicamento;
		this.entregaMedicamento = entregaMedicamento;
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

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public EntregaMedicamento getEntregaMedicamento() {
		return entregaMedicamento;
	}

	public void setEntregaMedicamento(EntregaMedicamento entregaMedicamento) {
		this.entregaMedicamento = entregaMedicamento;
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
		OrdenMedicamento other = (OrdenMedicamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}	
}
