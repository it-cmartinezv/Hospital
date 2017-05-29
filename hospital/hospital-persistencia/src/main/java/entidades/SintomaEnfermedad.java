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
@Table(name="Sintomas_Enfermedad")
@IdClass(SintomaEnfermedadPK.class)
@NamedQueries({
	@NamedQuery(name=SintomaEnfermedad.BYENFERMEDAD, query="SELECT se FROM SintomaEnfermedad se WHERE se.enfermedad=?1")
})
public class SintomaEnfermedad implements Serializable{
	
	public static final String BYENFERMEDAD = "SintomaEnfermedad.byenfermedad";
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Enfermedad")
	private Enfermedad enfermedad;
	
	@Id
	@ManyToOne(cascade={})
	@JoinColumn(name="Sintoma")
	private Sintoma sintoma;

	public SintomaEnfermedad() {
	}

	public SintomaEnfermedad(Enfermedad enfermedad, Sintoma sintoma) {
		super();
		this.enfermedad = enfermedad;
		this.sintoma = sintoma;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
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
		result = prime * result + ((enfermedad == null) ? 0 : enfermedad.hashCode());
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
		SintomaEnfermedad other = (SintomaEnfermedad) obj;
		if (enfermedad == null) {
			if (other.enfermedad != null)
				return false;
		} else if (!enfermedad.equals(other.enfermedad))
			return false;
		if (sintoma == null) {
			if (other.sintoma != null)
				return false;
		} else if (!sintoma.equals(other.sintoma))
			return false;
		return true;
	}
}
