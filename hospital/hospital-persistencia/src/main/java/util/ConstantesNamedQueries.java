package util;

public class ConstantesNamedQueries {

	/**
	 * Consulta que retorna la lista de roles de un usuario.<br>
	 * ?1: usuario.
	 */
	public static final String CONSULTA_LISTARROLESUSUARIO = "rol.listarRolesPorUsuario";

	/**
	 * Consulta que retorna la lista de accesos de un rol.<br>
	 * ?1: rol.
	 */
	public static final String CONSULTA_LISTARACCESOSROL = "acceso.listarAccesosPorRol";
	
	private ConstantesNamedQueries() {
	}
}
