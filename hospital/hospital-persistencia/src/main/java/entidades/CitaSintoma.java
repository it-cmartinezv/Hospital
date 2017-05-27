package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Cita_Sintomas")
@IdClass(CitaSintomaPK.class)
@NamedQueries({
	@NamedQuery(name=CitaSintoma.BYCITA, query="SELECT c FROM CitaSintoma c WHERE c.cita=?1"),
})
public class CitaSintoma implements Serializable{
	
	public static final String BYCITA = "CitaSintoma.bycita";
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Cita")
	private CitaMedica cita;
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Sintoma")
	private Sintoma sintoma;

	public CitaSintoma() {
	}

	public CitaSintoma(CitaMedica cita, Sintoma sintoma) {
		super();
		this.cita = cita;
		this.sintoma = sintoma;
	}

	public CitaMedica getCita() {
		return cita;
	}

	public void setCita(CitaMedica cita) {
		this.cita = cita;
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cita == null) ? 0 : cita.hashCode());
		result = prime * result + ((sintoma == null) ? 0 : sintoma.hashCode());
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
		CitaSintoma other = (CitaSintoma) obj;
		if (cita == null) {
			if (other.cita != null)
				return false;
		} else if (!cita.equals(other.cita))
			return false;
		if (sintoma == null) {
			if (other.sintoma != null)
				return false;
		} else if (!sintoma.equals(other.sintoma))
			return false;
		return true;
	}
}
