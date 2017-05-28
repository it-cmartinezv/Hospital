package entidades;

import java.io.Serializable;

public class SintomaEnfermedadPK implements Serializable{
	
	private int enfermedad;
	private int sintoma;
	
	public SintomaEnfermedadPK() {
	}

	public SintomaEnfermedadPK(int enfermedad, int sintoma) {
		super();
		this.enfermedad = enfermedad;
		this.sintoma = sintoma;
	}

	public int getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(int enfermedad) {
		this.enfermedad = enfermedad;
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
		result = prime * result + enfermedad;
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
		SintomaEnfermedadPK other = (SintomaEnfermedadPK) obj;
		if (enfermedad != other.enfermedad)
			return false;
		if (sintoma != other.sintoma)
			return false;
		return true;
	}
}
