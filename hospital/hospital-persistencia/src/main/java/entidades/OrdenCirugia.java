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
@Table(name = "Orden_Cirugia")
@NamedQueries({
	@NamedQuery(name=OrdenCirugia.listarCirugia, query="SELECT oC FROM OrdenCirugia oC WHERE oC.cirugia =?1"),
	@NamedQuery(name=OrdenCirugia.LISTAR, query="SELECT oC FROM OrdenCirugia oC")
})
public class OrdenCirugia implements Serializable{
	
	public static final String listarCirugia = "Cirugia.listarOrdenCirugiaCirugia";
	public static final String LISTAR = "OrdenCirugia.listar";
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ORDEN_CIRUGIA") 
	@SequenceGenerator(name="SEQ_ORDEN_CIRUGIA", sequenceName="SEQ_ORDEN_CIRUGIA",allocationSize=1)
	private int id;
	
	@JoinColumn(name="Citas_Medicas")
	@ManyToOne(cascade={})
	private CitaMedica citaMedica;
	
	@JoinColumn(name="Cirugia")
	@ManyToOne(cascade={})
	private Cirugia cirugia;
	
	@JoinColumn(name="Medico")
	@ManyToOne(cascade={})
	private Medico medico;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Cirugia",nullable = false)
	private Date fecha;
	
	@Column(name="Descripcion",nullable = false,length=2000)
	private String Descripcion;
	
	public OrdenCirugia() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public OrdenCirugia(CitaMedica citaMedica, Cirugia cirugia, Medico medico, Date fecha, String descripcion) {
		super();
		this.citaMedica = citaMedica;
		this.cirugia = cirugia;
		this.medico = medico;
		this.fecha = fecha;
		Descripcion = descripcion;
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



	public Cirugia getCirugia() {
		return cirugia;
	}



	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}



	public Medico getMedico() {
		return medico;
	}



	public void setMedico(Medico medico) {
		this.medico = medico;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getDescripcion() {
		return Descripcion;
	}



	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
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
		OrdenCirugia other = (OrdenCirugia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
