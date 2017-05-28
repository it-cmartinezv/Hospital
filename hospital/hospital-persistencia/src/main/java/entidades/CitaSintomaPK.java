package entidades;

import java.io.Serializable;

public class CitaSintomaPK implements Serializable{
	
	private int cita;
	private int sintoma;
	
	public CitaSintomaPK() {
	}

	public CitaSintomaPK(int cita, int sintoma) {
		super();
		this.cita = cita;
		this.sintoma = sintoma;
	}

	public int getCita() {
		return cita;
	}

	public void setCita(int cita) {
		this.cita = cita;
	}

	public int getSintoma() {
		return sintoma;
	}

	public void setSintoma(int sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cita;
		result = prime * result + sintoma;
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
		CitaSintomaPK other = (CitaSintomaPK) obj;
		if (cita != other.cita)
			return false;
		if (sintoma != other.sintoma)
			return false;
		return true;
	}
}
