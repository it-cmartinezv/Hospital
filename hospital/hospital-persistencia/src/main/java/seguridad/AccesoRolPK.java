package seguridad;
import java.io.Serializable;

public class AccesoRolPK implements Serializable {

	private int acceso;
	private int rol;

	public AccesoRolPK() {
	}
	
	/**
	 * constructor.
	 * @param acceso
	 * @param rol
	 */
	public AccesoRolPK(int acceso, int rol) {
		super();
		this.acceso = acceso;
		this.rol = rol;
	}
	
	public int getAcceso() {
		return acceso;
	}

	public void setAcceso(int acceso) {
		this.acceso = acceso;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acceso;
		result = prime * result + rol;
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
		AccesoRolPK other = (AccesoRolPK) obj;
		if (acceso != other.acceso)
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}
}