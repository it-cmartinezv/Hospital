package entidades;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad que representa a todas las personas que usan el aplicativo
 * 
 */
@Entity
@Table(name = "Persona")
public class Persona implements Serializable{
	
	@Id
	@Column(name="id")
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
	
	@Column(name="Genero")
	private boolean genero;
	
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

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
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
}

