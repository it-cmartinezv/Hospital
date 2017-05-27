package beans;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beansSeguridad.UsuarioRolEJB;
import entidades.Ciudad;
import entidades.Farmaceutico;
import entidades.Farmacia;
import entidades.Medico;
import entidades.Paciente;
import entidades.Pais;
import entidades.Persona;
import remote.ILocalizacionRemote;
import remote.IUsuarioRemote;
import seguridad.Rol;
import seguridad.UsuarioRol;

/**
 * 
 * @author carlos martinez
 * clase encargada de las operaciones de usuario
 */
@LocalBean
@Stateless
@Remote(IUsuarioRemote.class)
public class EJBUsuario implements Serializable{
	
	@EJB
	private UsuarioRolEJB usuarioRolEJB;
	
	@EJB
	private RolEJB rolEJB;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Edita una persona en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarPersona(Persona persona){
		Persona telefono = usuarioByTelefono(persona.getTelefono());
		if(telefono != null){
			if(!telefono.getCorreo().equalsIgnoreCase(persona.getCorreo())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}
		Persona correo = usuarioByCorreo(persona.getCorreo());
		if(correo != null){
			if(!correo.getTelefono().equalsIgnoreCase(persona.getTelefono())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
			}
		}
		em.merge(persona);
	}
	
	/**
	 * Eliminar una persona
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersona(String tipoId, String numeroId){
		Persona persona = buscarUsuario(tipoId, numeroId);
		if(persona != null){
			em.remove(persona);
		}else{
			throw new excepciones.ExcepcionNegocio("No se ha encontrado ningun usuario con estas credenciales");
		}
	}
	
	
	/**
	 * Buscar persona por tipo identificacion y numero identificacion
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Persona buscarUsuario(String tipoId, String numeroId){
		Query q = em.createNamedQuery(Persona.BUSCAR);
		q.setParameter(1, numeroId);
		q.setParameter(2, tipoId);
		List<Persona> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Buscar usuario por telefono
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Persona usuarioByTelefono(String telefono){
		Query q = em.createNamedQuery(Persona.BYTELEFONO);
		q.setParameter(1, telefono);
		List<Persona> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Buscar usuario por correo electronico
	 * @param correo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Persona usuarioByCorreo(String correo){
		Query q = em.createNamedQuery(Persona.BYCORREO);
		q.setParameter(1, correo);
		List<Persona> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Registra una persona en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarPersona(Persona laPersona, int elRol){
		Persona persona = buscarUsuario(laPersona.getTipoIdentificacion(), laPersona.getNumeroIdentificacion());
		if(persona == null){
			Persona telefono = usuarioByTelefono(laPersona.getTelefono());
			if(telefono == null){
				Persona correo = usuarioByCorreo(laPersona.getCorreo());
				if(correo == null){
					Rol rol = rolEJB.buscar(elRol);
					if(rol != null){
						em.persist(laPersona);
						Persona usuario = usuarioByCorreo(laPersona.getCorreo());
						if(usuario != null){
							UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
							usuarioRolEJB.crear(usuarioRol);
						}
					}else{
						throw new excepciones.ExcepcionNegocio("No existe el rol "+elRol+", registrelo antes de registrar");
					}
				}else{
					throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
				}
			}else{
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}else{
			throw new excepciones.ExcepcionNegocio("Ya existen estas credenciales");
		}
	}
	
	/**
	 * Registra un paciente en la bd
	 * @param paciente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarPaciente(Paciente paciente){
		Persona persona = buscarUsuario(paciente.getTipoIdentificacion(), paciente.getNumeroIdentificacion());
		if(persona == null){
			Persona telefono = usuarioByTelefono(paciente.getTelefono());
			if(telefono == null){
				Persona correo = usuarioByCorreo(paciente.getCorreo());
				if(correo == null){
					Rol rol = rolEJB.buscar(4);
					if(rol != null){
						em.persist(paciente);
						Persona usuario = usuarioByCorreo(paciente.getCorreo());
						if(usuario != null){
							UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
							usuarioRolEJB.crear(usuarioRol);
						}
					}else{
						throw new excepciones.ExcepcionNegocio("No existe el rol paciente, registrelo antes de registrar un paciente");
					}
				}else{
					throw new excepciones.ExcepcionNegocio("Ya existe un cliente registrado con este correo");
				}
			}else{
				throw new excepciones.ExcepcionNegocio("Ya existe un cliente registrado con este telefono");
			}
		}else{
			throw new excepciones.ExcepcionNegocio("Ya existe este Cliente");
		}
	}
	
	/**
	 * Buscar un paciente por tipo identificacion y numero de identificacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Paciente buscarPaciente(String tipoid, String numid){
		Query q = em.createNamedQuery(Paciente.PACIENTE);
		q.setParameter(1, numid);
		q.setParameter(2, tipoid);
		List<Paciente> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Listar Pacientes
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Paciente> listarPacientes(){
		return em.createNamedQuery(Paciente.LISTAR).getResultList();
	}
	
	/**
	 * Edita un paciente en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarPaciente(Paciente paciente){
		Persona telefono = usuarioByTelefono(paciente.getTelefono());
		if(telefono != null){
			if(!telefono.getCorreo().equalsIgnoreCase(paciente.getCorreo())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}
		Persona correo = usuarioByCorreo(paciente.getCorreo());
		if(correo != null){
			if(!correo.getTelefono().equalsIgnoreCase(paciente.getTelefono())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
			}
		}
		em.merge(paciente);
	}
	
	/**
	 * Eliminar paciente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPaciente(String tipoid, String numid){
		Paciente paciente = buscarPaciente(tipoid, numid);
		if(paciente != null){
			em.remove(paciente);
		}else{
			throw new excepciones.ExcepcionNegocio("No se ha encontrado ningun paciente para eliminar");
		}
	}
	
	/**
	 * Registra un medico en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarMedico(Medico medico){
		Persona persona = buscarUsuario(medico.getTipoIdentificacion(), medico.getNumeroIdentificacion());
		if(persona == null){
			Persona telefono = usuarioByTelefono(medico.getTelefono());
			if(telefono == null){
				Persona correo = usuarioByCorreo(medico.getCorreo());
				if(correo == null){
					Rol rol = rolEJB.buscar(2);
					if(rol != null){
						em.persist(medico);
						Persona usuario = usuarioByCorreo(medico.getCorreo());
						if(usuario != null){
							UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
							usuarioRolEJB.crear(usuarioRol);
						}
					}else{
						throw new excepciones.ExcepcionNegocio("No existe el rol medico, registrelo antes de registrar un medico");
					}
				}else{
					throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
				}
			}else{
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}else{
			throw new excepciones.ExcepcionNegocio("Ya existen estas credenciales");
		}
	}
	
	/**
	 * Edita un medico en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarMedico(Medico medico){
		Persona telefono = usuarioByTelefono(medico.getTelefono());
		if(telefono != null){
			if(!telefono.getCorreo().equalsIgnoreCase(medico.getCorreo())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}
		Persona correo = usuarioByCorreo(medico.getCorreo());
		if(correo != null){
			if(!correo.getTelefono().equalsIgnoreCase(medico.getTelefono())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
			}
		}
		em.merge(medico);
	}
	
	/**
	 * Buscar medico por id
	 */
	public Medico buscarMedicoID(int id){
		return em.find(Medico.class, id);
	}
	
	/**
	 * Buscar un medico en la base de datos
	 * por tipo y numero de identificacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Medico buscarMedico(String tipoId, String numId){
		Query q = em.createNamedQuery(Medico.BUSCAR);
		q.setParameter(1, tipoId);
		q.setParameter(2, numId);
		List<Medico> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Buscar un medico en la base de datos por numero de tarjeta profesional
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Medico buscarMedicoByTarjeta(String tarjetaProfesional){
		Query q = em.createNamedQuery(Medico.BYTARJETA);
		q.setParameter(1, tarjetaProfesional);
		List<Medico> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Listar Medicos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Medico> listarMedicos(){
		return em.createNamedQuery(Medico.LISTAR).getResultList();
	}
	
	/**
	 * Eliminar Medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarMedico(String tarjetaProfesional){
		Medico medico = buscarMedicoByTarjeta(tarjetaProfesional);
		if(medico != null){
			em.remove(medico);
		}else{
			throw new excepciones.ExcepcionNegocio("No se ha encontrado ningun medico");
		}
	}
	
	/**
	 * Registra un farmaceutico en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarFarmaceutico(Farmaceutico farmaceutico){
		Persona persona = buscarUsuario(farmaceutico.getTipoIdentificacion(), farmaceutico.getNumeroIdentificacion());
		if(persona == null){
			Persona telefono = usuarioByTelefono(farmaceutico.getTelefono());
			if(telefono == null){
				Persona correo = usuarioByCorreo(farmaceutico.getCorreo());
				if(correo == null){
					Rol rol = rolEJB.buscar(3);
					if(rol != null){
						em.persist(farmaceutico);
						Persona usuario = usuarioByCorreo(farmaceutico.getCorreo());
						if(usuario != null){
							UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
							usuarioRolEJB.crear(usuarioRol);
						}
					}else{
						throw new excepciones.ExcepcionNegocio("No existe el rol farmaceutico, registrelo antes de registrar un farmaceutico");
					}
				}else{
					throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
				}
			}else{
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}else{
			throw new excepciones.ExcepcionNegocio("Ya existen estas credenciales");
		}
	}
	
	/**
	 * Listar Farmaceuticos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Farmaceutico> listarFarmaceuticos(){
		return em.createNamedQuery(Farmaceutico.listarFarmaceuticos).getResultList();
	}
	
	/**
	 * Edita un farmaceutico en la base de datos
	 * @param medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarFarmaceutico(Farmaceutico farmaceutico){
		Persona telefono = usuarioByTelefono(farmaceutico.getTelefono());
		if(telefono != null){
			if(!telefono.getCorreo().equalsIgnoreCase(farmaceutico.getCorreo())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este telefono");
			}
		}
		Persona correo = usuarioByCorreo(farmaceutico.getCorreo());
		if(correo != null){
			if(!correo.getTelefono().equalsIgnoreCase(farmaceutico.getTelefono())){
				throw new excepciones.ExcepcionNegocio("Ya existe un usuario registrado con este correo");
			}
		}
		em.merge(farmaceutico);
	}
	
	/**
	 * Buscar un farmaceutico en la base de datos por numero de tarjeta profesional
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Farmaceutico buscarFarmaceuticoByTarjeta(String tarjetaProfesional){
		Query q = em.createNamedQuery(Farmaceutico.BYTARJETA);
		q.setParameter(1, tarjetaProfesional);
		List<Farmaceutico> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Eliminar Medico
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarFarmaceutico(String tarjetaProfesional){
		Farmaceutico farmaceutico = buscarFarmaceuticoByTarjeta(tarjetaProfesional);
		if(farmaceutico != null){
			em.remove(farmaceutico);
		}else{
			throw new excepciones.ExcepcionNegocio("No se ha encontrado ningun farmaceutico");
		}
	}
	
	/**
	 * Buscar Farmaceutico por ID
	 */
	public Farmaceutico buscarFarmaceutico(int id){
		return em.find(Farmaceutico.class, id);
	}
}
