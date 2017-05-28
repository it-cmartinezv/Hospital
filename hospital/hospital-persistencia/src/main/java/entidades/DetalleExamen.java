/**
 * NO SE HA ESPECIFICADO UN ID EN LA TABLA DETALLE EXAMEN!!!
 */

package entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Detalle_Examen")
public class DetalleExamen implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DETALLE_EXAMEN")
	@SequenceGenerator(name = "SEQ_DETALLE_EXAMEN", sequenceName = "SEQ_DETALLE_EXAMEN", allocationSize = 1)
	@Column(name="Id_Detalle_Examen")
	private int id;
	
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
