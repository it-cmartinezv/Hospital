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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Citas_Medicas")
public class CitaMedica implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
}
