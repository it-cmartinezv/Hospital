/**
 *  OJO NUMERO DE IDENTIFICACION Y TIPO DE IDENTIFICACION SON LA LLAVE PRIMARIA Y ES COMPUESTA
 *  
 */

package entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Paciente")
@Inheritance(strategy=InheritanceType.JOINED)
public class Paciente extends Persona implements Serializable{

	@JoinColumn(name="EPS_Id_Eps")
	@ManyToOne(cascade={})
	private Eps eps;

	public Paciente() {
		super();
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	@Override
	public String toString() {
		return "INFORMACION PACIENTE Paciente [eps=" + eps + ", getId()=" + getId() + ", getNumeroIdentificacion()="
				+ getNumeroIdentificacion() + ", getTipoIdentificacion()=" + getTipoIdentificacion() + ", getNombre()="
				+ getNombre() + ", getApellido()=" + getApellido() + ", getFechaNacimiento()=" + getFechaNacimiento()
				+ ", getGenero()=" + getGenero() + ", getCorreo()=" + getCorreo() + ", getPassword()=" + getPassword()
				+ ", getTelefono()=" + getTelefono() + ", getCiudad()=" + getCiudad()+ "]";
	}
	
	
	
}
