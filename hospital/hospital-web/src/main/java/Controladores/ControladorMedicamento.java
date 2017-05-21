package Controladores;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.FarmaciaEJB;
import beans.MedicamentoEJB;
import entidades.Farmacia;
import entidades.Medicamento;
import entidades.TipoMedicamento;

@ViewScoped
@Named("Medicamento")
public class ControladorMedicamento implements Serializable {

	@EJB
	private MedicamentoEJB medicamentoEJB;
	
	private int id;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String nombre;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Descripcion No valida")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String descripcion;
	
	//@Length(min=4,max=200,message="longitud entre 4 y 200")
	private int cantidad;
	
	private TipoMedicamento tipoMedicamento;
	private Date fechaMedicamento;
	private Farmacia farmacia;
	private List<Medicamento> listarMedicamento;
	

	/**
	 * Lista de los tipos de medicamentos
	 */
	private List<TipoMedicamento> listaTipos;

	/**
	 * Lista de farmacias en las que puede estar el medicamento
	 */
	private List<Farmacia> listaFarmacias;

	@PostConstruct
	public void iniciar() {
		listaFarmacias = medicamentoEJB.listaFarmacia();
		listaTipos = medicamentoEJB.listarTipoMedicamentos();
		listarMedicamento = medicamentoEJB.listaMedicamentos();
	}

	/**
	 * Metodo para crear un medicamento
	 */
	public void crear() {
		
		Medicamento medicamento = new Medicamento(nombre, descripcion, cantidad, tipoMedicamento, fechaMedicamento, farmacia);
//		
		//medicamento.setNombre(nombre);
//		medicamento.setCantidad(10);
//		medicamento.setDescripcion("Sirve para todo");
//		Date date = new Date();
//		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String convertido = fechaHora.format(date);
//		medicamento.setFechaVencimiento(date);
//		medicamento.setTipoMedicamento(tipoMedicamento);
//		medicamento.setFarmacia(farmacia);
		
		medicamentoEJB.crear(medicamento);
		limpiar();
		Messages.addFlashGlobalInfo("El medicamento se ha creado exitosamente");

	}
	
	/**
	 * Metodo para buscar un medicamento
	 */
	public void buscar(){
		Medicamento medicamento = medicamentoEJB.buscarNombre(nombre);
		if (medicamento!=null) {
			nombre = medicamento.getNombre();
			descripcion = medicamento.getDescripcion();
			cantidad = medicamento.getCantidad();
			farmacia = medicamento.getFarmacia();
			tipoMedicamento = medicamento.getTipoMedicamento();
			fechaMedicamento = medicamento.getFechaVencimiento();
			
		} else {
			Messages.addFlashGlobalInfo("No hay medicamentos con este dato");
		}
	}
	
	/**
	 * MEtodo para editar un medicamento
	 */
	public void editar(){
		Medicamento medi = medicamentoEJB.buscarNombre(nombre);
		if (medi!=null) {
			medi.setCantidad(cantidad);
			medi.setDescripcion(descripcion);
			medi.setFarmacia(farmacia);
			medi.setFechaVencimiento(fechaMedicamento);
			medi.setNombre(nombre);
			medi.setTipoMedicamento(tipoMedicamento);
			medicamentoEJB.editar(medi);
			limpiar();
			Messages.addFlashGlobalInfo("Se ha editado correctamente");
		}else {
			Messages.addFlashGlobalInfo("No hay medicamentos con este dato");
		}
		
	}
	
	/**
	 * Metodo para eliminar medicamentos
	 */
	public void eliminar(){
		Medicamento medicamento = medicamentoEJB.buscarNombre(nombre);
		if (medicamento!=null) {
			medicamentoEJB.eliminar(medicamento);
		} else {
			Messages.addFlashGlobalInfo("No hay medicamentos con este dato");
		}
	}
	
	public void limpiar(){
		nombre = "";
		descripcion = "";
		cantidad = 0;
		fechaMedicamento = null;
		tipoMedicamento = null;
		farmacia = null;
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

	public Date getFechaMedicamento() {
		return fechaMedicamento;
	}

	public void setFechaMedicamento(Date fechaMedicamento) {
		this.fechaMedicamento = fechaMedicamento;
	}

	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public MedicamentoEJB getMedicamentoEJB() {
		return medicamentoEJB;
	}

	public void setMedicamentoEJB(MedicamentoEJB medicamentoEJB) {
		this.medicamentoEJB = medicamentoEJB;
	}

	public List<TipoMedicamento> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<TipoMedicamento> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public List<Farmacia> getListaFarmacias() {
		return listaFarmacias;
	}

	public void setListaFarmacias(List<Farmacia> listaFarmacias) {
		this.listaFarmacias = listaFarmacias;
	}

	public List<Medicamento> getListarMedicamento() {
		return listarMedicamento;
	}

	public void setListarMedicamento(List<Medicamento> listarMedicamento) {
		this.listarMedicamento = listarMedicamento;
	}

	
	
}
