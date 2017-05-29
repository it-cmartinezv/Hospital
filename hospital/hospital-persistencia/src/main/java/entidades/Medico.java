package entidades;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name = "Medico")
@NamedQueries({
	@NamedQuery(name=Medico.MEDICO, query="SELECT m FROM Medico m WHERE m.numeroIdentificacion=?1 AND m.tipoIdentificacion=?2"),
	@NamedQuery(name=Medico.BYTARJETA,query="SELECT m FROM Medico m WHERE m.tarjetaProfesional=?1"),
	@NamedQuery(name=Medico.LISTAR, query="SELECT m FROM Medico m")	
})
public class Medico extends Persona implements Serializable{
	
	public static final String MEDICO = "Medico.medico";
	public static final String BYTARJETA = "Medico.bytarjeta";
	public static final String LISTAR = "Medico.listar";
	
	@Column(name="Estado")
	private boolean estado;
	
	@Column(name="Tarjeta_Profesional",nullable = false,length=100)
	private String tarjetaProfesional;

	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}

	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}
}
