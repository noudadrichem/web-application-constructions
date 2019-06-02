package noud.app.webservices;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {

	protected final Connection getConnection() {
		
        Connection result = null;

		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			
			result = ds.getConnection();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	// public static final String URL = "jdbc:postgresql://localhost:5432/p7";
    // public static final String USERNAME = "noud";
    // public static final String PASSWORD = "test1234";

    // protected Connection getConnection() throws SQLException, ClassNotFoundException {
    //     Connection con = null;
    //     Class.forName("org.postgresql.Driver");
    //     con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    //     return con;
    // }
	
}
