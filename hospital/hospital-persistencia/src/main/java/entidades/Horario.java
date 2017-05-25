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
@Table(name = "Horario")
public class Horario implements Serializable{
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="Dia",nullable = false,length=15)
	private String dia;
		
	//@Temporal(TemporalType.DATE)
	@Column(name="Hora_Inicio",nullable = false)
	private Time horaInicio;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="Hora_Final",nullable = false)
	private Time horaFinal;	 // OJO PREGUNTAR SI SE PUEDE USAR LA VARIABLE DE TIPO TIME

	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
