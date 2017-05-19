/**
 * NO SE HA ESPECIFICADO UN ID EN LA TABLA DETALLE EXAMEN!!!
 */
package entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
@Table(name = "Detalle_Cirugia")
public class DetalleCirugia implements Serializable{
	
	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Cirugia",nullable = false)
	private Date fechaCirugia;
	
	@Column(name="Descripcion",nullable = false,length=2000)
	private String descripcion;
	
	@JoinColumn(name="Orden_Cirugia_Id_Orden")
	@ManyToOne(cascade={})
	private OrdenCirugia ordenCirugia;

	public DetalleCirugia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleCirugia(Medico medico, Date fechaCirugia, String descripcion, OrdenCirugia ordenCirugia) {
		super();
		this.medico = medico;
		this.fechaCirugia = fechaCirugia;
		this.descripcion = descripcion;
		this.ordenCirugia = ordenCirugia;
	}
	
}
