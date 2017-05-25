package seguridad;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Carlos Martinez
 * Roles Definidos:
 * 1 Administrador
 * 2 Medico
 * 3 Farmaceutico
 * 4 Paciente
 */

@Entity
@Table(name="Rol")
@NamedQueries({
	@NamedQuery(name=Rol.LISTA,query="SELECT r FROM Rol r")
})
public class Rol implements Serializable{

	public static final String LISTA = "Rol.lista";

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ROL") 
	@SequenceGenerator(name="SEQ_ROL", sequenceName="SEQ_ROL",allocationSize=1) 
	private int id;
	
	@Column(name="nombre",nullable = false,length=50)
	private String nombre;
	
	public Rol(){
		
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
		Rol other = (Rol) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}