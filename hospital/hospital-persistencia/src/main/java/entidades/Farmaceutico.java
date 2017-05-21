package entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Farmaceutico")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name=Farmaceutico.BYTARJETA,query="SELECT f FROM Farmaceutico f WHERE f.tarjetaProfesional=?1"),
	@NamedQuery(name=Farmaceutico.listarFarmaceuticos, query="SELECT f FROM Farmaceutico f")
})
public class Farmaceutico extends Persona implements Serializable{
	
	public static final String listarFarmaceuticos = "Farmaceutico.listar";
	public static final String BYTARJETA = "Farmaceutico.bytarjeta";
	
	@Column(name="Tarjeta_Profesional",nullable = false,length=30)
	private String tarjetaProfesional;

	
	public Farmaceutico(String tarjetaProfesional) {
		super();
		this.tarjetaProfesional = tarjetaProfesional;
	}

	public Farmaceutico() {
		super();
	}

	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}

	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}

	public static String getListarfarmaceuticos() {
		return listarFarmaceuticos;
	}
}
