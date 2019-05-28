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
	
}
