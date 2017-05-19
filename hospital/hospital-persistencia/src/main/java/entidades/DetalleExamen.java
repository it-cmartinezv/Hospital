/**
 * NO SE HA ESPECIFICADO UN ID EN LA TABLA DETALLE EXAMEN!!!
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

//@Entity
@Table(name = "Detalle_Examen")
public class DetalleExamen implements Serializable{
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Realizacion",nullable = false)
	private Date fechaRealizacion;
	
	@Column(name="Descripcion",nullable = false,length=2000)
	private String descripcion;
	
	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	@JoinColumn(name="Orden_Examen_Id_Orden")
	@ManyToOne(cascade={})
	private OrdenExamen ordenExamen;

	public DetalleExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleExamen(Date fechaRealizacion, String descripcion, Medico medico, OrdenExamen ordenExamen) {
		super();
		this.fechaRealizacion = fechaRealizacion;
		this.descripcion = descripcion;
		this.medico = medico;
		this.ordenExamen = ordenExamen;
	}
	
	
}
