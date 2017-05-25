package entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import seguridad.Rol;

/**
 * Entidad que representa a todas las personas que usan el aplicativo
 * 
 */
@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name = "Persona")
@NamedQueries({
	@NamedQuery(name=Persona.BUSCAR,query="SELECT p FROM Persona p WHERE p.numeroIdentificacion=?1 AND p.tipoIdentificacion=?2"),
	@NamedQuery(name=Persona.BYTELEFONO,query="SELECT p FROM Persona p WHERE p.telefono=?1"),
	@NamedQuery(name=Persona.BYCORREO,query="SELECT p FROM Persona p WHERE p.correo=?1"),
})
public class Persona implements Serializable{
	
	public static final String BUSCAR = "Persona.buscar";
	public static final String BYTELEFONO = "Persona.bytelefono";
	public static final String BYCORREO = "Persona.bycorreo";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_PERSONA") 
	@SequenceGenerator(name="SEQ_PERSONA", sequenceName="SEQ_PERSONA",allocationSize=1) 
	private int id;
	
	@Column(name="Numero_Identificacion",nullable = false,length=15)
	private String numeroIdentificacion;
	
	@Column(name="Tipo_Identificacion",nullable = false,length=20)
	private String tipoIdentificacion;
	
	@Column(name="Nombre",nullable = false,length=20)
	private String nombre;
	
	@Column(name="Apellido",nullable = false,length=20)
	private String apellido;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Nacimiento",nullable = false)
	private Date fechaNacimiento;
	
	@Column(name="Genero",nullable = false,length=50)
	private String genero;
	
	@Column(name="Correo",nullable = false,length=50)
	private String correo;
	
	@Column(name="password",nullable = false,length=200)
	private String password;
	
	@Column(name="telefono",nullable = false,length=20)
	private String telefono;
	
	@JoinColumn(name="Ciudad")
	@ManyToOne(cascade={})
	private Ciudad ciudad;
	
	public Persona() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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
		Persona other = (Persona) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

