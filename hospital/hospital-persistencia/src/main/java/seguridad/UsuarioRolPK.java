package seguridad;
import java.io.Serializable;

/**
 * Clase responsable de.mpodelar la llave compuesta de UsuarioRol <br>
 * 
 */
public class UsuarioRolPK implements Serializable {

	private int usuario;
	private int rol;

	public UsuarioRolPK() {
	}

	public UsuarioRolPK(int usuario, int rol) {
		super();
		this.usuario = usuario;
		this.rol = rol;
	}


	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
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
		result = prime * result + rol;
		result = prime * result + usuario;
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
		UsuarioRolPK other = (UsuarioRolPK) obj;
		if (rol != other.rol)
			return false;
		if (usuario != other.usuario)
			return false;
		return true;
	}
}
