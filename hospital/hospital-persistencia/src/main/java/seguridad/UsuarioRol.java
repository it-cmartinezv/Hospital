package seguridad;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import entidades.Enfermedad;
import entidades.Persona;

@Entity
@Table(name="PersonaRol")
@IdClass(UsuarioRolPK.class)
@NamedQueries({
	@NamedQuery(name=UsuarioRol.ROLESBYUSER, query="SELECT r FROM UsuarioRol ur JOIN ur.rol r JOIN ur.usuario u WHERE u = ?1"),
	@NamedQuery(name=UsuarioRol.PERSONASBYROL, query="SELECT u FROM UsuarioRol ur JOIN ur.rol r JOIN ur.usuario u WHERE r.id = ?1")
})
public class UsuarioRol implements Serializable {

	public static final String ROLESBYUSER = "UsuarioRol.rolesbyuser";
	public static final String PERSONASBYROL = "UsuarioRol.personasbyrol";
	
	@Id
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="Persona")
	private Persona usuario;
	
	@Id
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="Rol")
	private Rol rol;

	public UsuarioRol() {
	}
	
	public UsuarioRol(Persona usuario, Rol rol) {
		super();
		this.usuario = usuario;
		this.rol = rol;
	}

	public Persona getPersona() {
		return usuario;
	}

	public void setPersona(Persona usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}