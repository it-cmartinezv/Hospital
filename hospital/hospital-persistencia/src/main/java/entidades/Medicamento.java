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
@Table(name = "Medicamento")
public class Medicamento implements Serializable{
	@Id
	@Column(name="Id_Medicamento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	public Medicamento(int id, String nombre, String descripcion, int cantidad, TipoMedicamento tipoMedicamento,
			Date fechaVencimiento, Farmacia farmacia) {
		super();
		this.id = id;
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
	
	
}
