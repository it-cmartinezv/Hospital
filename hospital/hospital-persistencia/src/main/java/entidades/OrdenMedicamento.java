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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Orden_Medicamento")
@NamedQueries({ 
	@NamedQuery(name = OrdenMedicamento.LISTARORDENES, query = "SELECT o FROM OrdenMedicamento o"),
	@NamedQuery(name = OrdenMedicamento.BUSCARPORID, query = "SELECT OM FROM OrdenMedicamento OM WHERE OM.id=?1")
})
public class OrdenMedicamento implements Serializable{
	
	public static final String LISTARORDENES = "OrdenMedicamento.listar";
	public static final String BUSCARPORID= "OrdenMedicamento.buscar";
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ORDEN_MEDICAMENTO") 
	@SequenceGenerator(name="SEQ_ORDEN_MEDICAMENTO", sequenceName="SEQ_ORDEN_MEDICAMENTO",allocationSize=1) 
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
		OrdenMedicamento other = (OrdenMedicamento) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
