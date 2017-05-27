package entidades;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Citas_Medicas")
public class CitaMedica implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITAS_MEDICAS")
	@SequenceGenerator(name = "SEQ_CITAS_MEDICAS", sequenceName = "SEQ_CITAS_MEDICAS", allocationSize = 1)
	private int id;
	
	@Column(name="Caracter")
	private int caracter;
	
	@Column(name="valoracion")
	private String valoracion;
	
	@Column(name="estado")
	private String estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha",nullable = false)
	private Date fecha;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="Hora",nullable = false)
	private Time hora; // PREGUNTAR SI DE PUEDE USAR TIME PARA GUARDAR HORAS
	
	/**
	 * el tipo de la cita medica: odontologia, medico general, especialista
	 */
	@Column(name="descripcion",nullable = false, length=300)
	private String descripcion; 
	
	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	@JoinColumn(name="Paciente")
	@ManyToOne(cascade={})
	private Paciente paciente;

	public CitaMedica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CitaMedica(int id, int caracter, Date fecha, Time hora, String descripcion, Medico medico,
			Paciente paciente) {
		super();
		this.id = id;
		this.caracter = caracter;
		this.fecha = fecha;
		this.hora = hora;
		this.descripcion = descripcion;
		this.medico = medico;
		this.paciente = paciente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaracter() {
		return caracter;
	}

	public void setCaracter(int caracter) {
		this.caracter = caracter;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		CitaMedica other = (CitaMedica) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
