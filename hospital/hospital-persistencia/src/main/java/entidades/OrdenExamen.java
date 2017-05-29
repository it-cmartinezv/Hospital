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
@Table(name = "Orden_Examen")
@NamedQueries({
	@NamedQuery(name=OrdenExamen.listarExamen, query="SELECT oE FROM OrdenExamen oE WHERE oE.examen =?1"), 
	@NamedQuery(name= OrdenExamen.LISTAORDENESEXIS, query="SELECT E FROM OrdenExamen E")
})
public class OrdenExamen implements Serializable{
	
	public static final String listarExamen = "Examen.listarOrdenExamenExamen";
	public static final String LISTAORDENESEXIS = "OrdenExamen,listarOrdenes";
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ORDEN_EXAMEN") 
	@SequenceGenerator(name="SEQ_ORDEN_EXAMEN", sequenceName="SEQ_ORDEN_EXAMEN",allocationSize=1)
	private int id;
	
	@JoinColumn(name="Citas_Medicas")
	@ManyToOne(cascade={})
	private CitaMedica citaMedica;
	
	@JoinColumn(name="Examen")
	@ManyToOne(cascade={})
	private Examen examen;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Realizacion",nullable = false)
	private Date fechaRealizacion;
	
	@Column(name="Descripcion",nullable = false,length=2000)
	private String Descripcion;
	
	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	public OrdenExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public OrdenExamen(CitaMedica citaMedica, Examen examen, Date fechaRealizacion, String descripcion, Medico medico) {
		super();
		this.citaMedica = citaMedica;
		this.examen = examen;
		this.fechaRealizacion = fechaRealizacion;
		Descripcion = descripcion;
		this.medico = medico;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public CitaMedica getCitaMedica() {
		return citaMedica;
	}



	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}



	public Examen getExamen() {
		return examen;
	}



	public void setExamen(Examen examen) {
		this.examen = examen;
	}



	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}



	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}



	public String getDescripcion() {
		return Descripcion;
	}



	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}



	public Medico getMedico() {
		return medico;
	}



	public void setMedico(Medico medico) {
		this.medico = medico;
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
		OrdenExamen other = (OrdenExamen) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	
}
