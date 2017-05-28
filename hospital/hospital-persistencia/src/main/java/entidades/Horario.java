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
@Table(name = "Horario")
@NamedQueries({
	@NamedQuery(name=Horario.BYMEDICO,query="SELECT h FROM Horario h WHERE H.medico=?1")
})
public class Horario implements Serializable{
	
	public static final String BYMEDICO = "Horario.bymedico";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_HORARIO") 
	@SequenceGenerator(name="SEQ_HORARIO", sequenceName="SEQ_HORARIO",allocationSize=1) 
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

	public Horario(String dia, Time horaInicio, Time horaFinal, Medico medico) {
		super();
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.medico = medico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Time horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
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
		Horario other = (Horario) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		return true;
	}
}
