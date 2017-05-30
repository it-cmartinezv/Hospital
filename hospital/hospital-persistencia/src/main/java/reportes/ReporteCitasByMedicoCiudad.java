package reportes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import oracle.jdbc.*;

public class ReporteCitasByMedicoCiudad {
//	
//		public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		public static final String usuario = "CARLOSABD";
//		public static final String password = "123";
//		/* Es la cantidad de columnas a mostrar en la tabla*/
//		public static final int cantidad = 11;
//		
//		public List<String[]> TestResultSetMartinez() throws SQLException{
//			    DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
//			    Connection conn = DriverManager.getConnection(url,usuario,password);
//			    CallableStatement stmt = conn.prepareCall("BEGIN reporte(?); END;"); // {call reporte()}
////			    stmt.setInt(1, 30); // SETEAMOS PARAMETROS EN CASO DE QUE EL PROCEDIMIENTO TENGA PARAMETROS
//			    stmt.registerOutParameter(1, OracleTypes.CURSOR); 
//			    stmt.execute();
//			    ResultSet rs = ((OracleCallableStatement)stmt).getCursor(1);
//			    List<String[]> lista = new ArrayList<String[]>();
//			    while (rs.next()) {
//			    	String[] objeto = new String[cantidad];
//				    for (int i = 0; i < cantidad; i++) {
//				    	String dato = rs.getString(i+1);
//						if(dato == null){
//							dato = "0";
//						}
//						objeto[i] = dato;
//					}
////			    	String[] objeto = {rs.getString("TRABAJO"),rs.getString("'Shipping'_CANTIDAD"),s1,rs.getString("'Sales'_CANTIDAD"),s2,rs.getString("'Executive'_CANTIDAD"),s3};
//			    	lista.add(objeto);
//			    }
//			    rs.close();
//			    rs = null;
//			    stmt.close();
//			    stmt = null;
//			    conn.close();
//			    conn = null;
//			    return lista;
//		}
//		
//	  public static void main (String[] args) {
//	    new ReporteCitasByMedicoCiudad();
//	  }
}
