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
@Table(name = "Medicamento")
@NamedQueries({ 
	@NamedQuery(name = Medicamento.LISTARMEDICAMENTOS, query = "SELECT m FROM Medicamento m"),
	@NamedQuery(name = Medicamento.BUSCARMEDICAMENTONOM, query = "SELECT M FROM Medicamento m where m.nombre=?1")
	
})
public class Medicamento implements Serializable{
	
	public static final String LISTARMEDICAMENTOS= "Medicamento.listar";
	public static final String BUSCARMEDICAMENTONOM = "Medicamento.buscarNom";
	
	@Id
	@Column(name="Id_Medicamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEDICAMENTO")
	@SequenceGenerator(name = "SEQ_MEDICAMENTO", sequenceName = "SEQ_MEDICAMENTO", allocationSize = 1)
	private int id;
	
	@Column(name="Nombre",nullable = false,length=50)
	private String nombre;
	
	@Column(name="Descripcion",nullable = false,length=100)
	private String descripcion;
		
	@Column(name="Cantidad")
	private int cantidad;
	
	@JoinColumn(name="Tipo_Medicamento")
	@ManyToOne(cascade={})
	private TipoMedicamento tipoMedicamento;
	
	/* DUDA CON ESTOS CAMPOS */
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Vencimiento",nullable = false)
	private Date fechaVencimiento;
	
	@JoinColumn(name="Farmacia_Id_Farmacia")
	@ManyToOne(cascade={})
	private Farmacia farmacia;

	/**
	 * Constructor
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param cantidad
	 * @param tipoMedicamento
	 * @param fechaVencimiento
	 * @param farmacia
	 */
	public Medicamento(String nombre, String descripcion, int cantidad, TipoMedicamento tipoMedicamento,
			Date fechaVencimiento, Farmacia farmacia) {
		super();
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.tipoMedicamento = tipoMedicamento;
		this.fechaVencimiento = fechaVencimiento;
		this.farmacia = farmacia;
	}

	public Medicamento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
	
	

	@Override
	public String toString() {
		return  nombre ;
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
		Medicamento other = (Medicamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
