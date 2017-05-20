package entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Medico")
public class Medico extends Persona implements Serializable{

	@Column(name="Estado")
	private boolean estado;
	
	@Column(name="Tarjeta_Profesional",nullable = false,length=100)
	private String tarjetaProfesional;
}
