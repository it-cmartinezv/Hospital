package entidades;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Citas_Medicas")
@NamedQueries({
	@NamedQuery(name=CitaMedica.LISTA_CITA, query="SELECT c FROM CitaMedica c"),
	@NamedQuery(name=CitaMedica.citasByPaciente, query="SELECT c FROM CitaMedica c WHERE c.paciente=?1"),
	@NamedQuery(name=CitaMedica.pacienteByEstado, query="SELECT c FROM CitaMedica c WHERE c.paciente=?1 AND c.estado=?2"),
	@NamedQuery(name=CitaMedica.citasByMedico, query="SELECT c FROM CitaMedica c WHERE c.medico=?1"),
	@NamedQuery(name=CitaMedica.medicoByEstado, query="SELECT c FROM CitaMedica c WHERE c.medico=?1 AND c.estado=?2")
})
public class CitaMedica implements Serializable{
	
	public static final String LISTA_CITA = "CitaMedica.listar";
	public static final String citasByPaciente = "CitaMedica.citasByPaciente";
	public static final String pacienteByEstado = "CitaMedica.pacienteByEstado";
	public static final String citasByMedico = "CitaMedica.citasByMedico";
	public static final String medicoByEstado = "CitaMedica.medicoByEstado";
	
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
	@Column(name="Fecha")
	private Date fecha;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="Hora")
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

	@OneToMany(mappedBy="cita",cascade={})
	private List<CitaSintoma> sintomas;
	
	public CitaMedica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CitaMedica(int caracter, String valoracion, String estado, Date fecha, Time hora, String descripcion,
			Medico medico, Paciente paciente) {
		super();
		this.caracter = caracter;
		this.valoracion = valoracion;
		this.estado = estado;
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

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	public List<CitaSintoma> getSintomas() {
		return sintomas;
	}

	public void setSintomas(List<CitaSintoma> sintomas) {
		this.sintomas = sintomas;
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
