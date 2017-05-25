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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "id")
public class OrdenMedicamento implements Serializable{
	@Id
	@Column(name="Id_Orden")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha",nullable = false)
	private Date fecha;
	
	/**
	 * POSOLOGIA = EL PACIENTE DEBE TOMAR 5 PASTAS DIARIAS DE ACETAMINOFEN
	 */
	@Column(name="posologia",nullable = false,length=300)
	private String posologia;
	
	@Column(name="estado")
	private boolean estado;
	
	@JoinColumn(name="Citas_Medicas")
	@ManyToOne(cascade={})
	private CitaMedica citaMedica;
	
	public OrdenMedicamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor con parametros
	 * 
	 */
	public OrdenMedicamento(Date fecha, String posologia, boolean estado, CitaMedica citaMedica) {
		super();
		this.fecha = fecha;
		this.posologia = posologia;
		this.estado = estado;
		this.citaMedica = citaMedica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}

	@Override
	public String toString() {
		return id+"";
	}	
}
