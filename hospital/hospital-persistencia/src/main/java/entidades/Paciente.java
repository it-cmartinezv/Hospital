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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Paciente")
public class Paciente extends Persona implements Serializable{

	@JoinColumn(name="EPS_Id_Eps")
	@ManyToOne(cascade={})
	private Eps eps;

}
