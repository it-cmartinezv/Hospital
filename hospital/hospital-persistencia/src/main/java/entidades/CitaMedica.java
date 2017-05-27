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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Citas_Medicas")
@NamedQueries({
	@NamedQuery(name=CitaMedica.LISTA_CITA, query="SELECT c FROM CitaMedica c")
})
public class CitaMedica implements Serializable{
	
	public static final String LISTA_CITA = "CitaMedica.listar";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITAS_MEDICAS")
	@SequenceGenerator(name = "SEQ_CITAS_MEDICAS", sequenceName = "SEQ_CITAS_MEDICAS", allocationSize = 1)
	private int id;
	
	@Column(name="Caracter")
	private boolean caracter;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha",nullable = false)
	private Date fecha;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="Hora",nullable = false)
	private Time hora; // PREGUNTAR SI DE PUEDE USAR TIME PARA GUARDAR HORAS
	
	/**
	 * el tipo de la cita medica: odontologia, medico general, especialista
	 */
	@Column(name="tipo",nullable = false, length=50)
	private String tipo; 
	
	@JoinColumn(name="Paciente")
	@ManyToOne(cascade={})
	private Paciente paciente;
	
	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	@JoinColumn(name="Sintomas")
	@ManyToOne(cascade={})
	private Sintoma sintoma;

	public CitaMedica(boolean caracter, Date fecha, Time hora, String tipo, Paciente paciente, Medico medico,
			Sintoma sintoma) {
		super();
		this.caracter = caracter;
		this.fecha = fecha;
		this.hora = hora;
		this.tipo = tipo;
		this.paciente = paciente;
		this.medico = medico;
		this.sintoma = sintoma;
	}

	public CitaMedica() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCaracter() {
		return caracter;
	}

	public void setCaracter(boolean caracter) {
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public String toString() {
		return "CitaMedica [caracter=" + caracter + "]";
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
