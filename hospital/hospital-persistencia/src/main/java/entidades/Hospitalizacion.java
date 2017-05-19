package entidades;
import java.io.Serializable;
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
@Table(name = "Hospitalizacion")
public class Hospitalizacion implements Serializable{
	@Id
	@Column(name="Id_Hospitalizacion")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Entrada",nullable = false)
	private Date entrada;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Salida",nullable = false)
	private Date salida;
	
	@JoinColumn(name="Camas_Id_Cama")
	@ManyToOne(cascade={})
	private Cama cama;
	
	@JoinColumn(name="Medico_Numero_Identificacion")
	@ManyToOne(cascade={})
	private Medico medico;

	public Hospitalizacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hospitalizacion(Date entrada, Date salida, Cama cama, Medico medico) {
		super();
		this.entrada = entrada;
		this.salida = salida;
		this.cama = cama;
		this.medico = medico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSalida() {
		return salida;
	}

	public void setSalida(Date salida) {
		this.salida = salida;
	}

	public Cama getCama() {
		return cama;
	}

	public void setCama(Cama cama) {
		this.cama = cama;
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
		Hospitalizacion other = (Hospitalizacion) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
