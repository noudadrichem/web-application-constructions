package noud.app.webservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserPostgresDaoImpl extends PostgresBaseDao implements userDao {

	@Override
	public String findRoleForUser(String name, String pass) {
		
		System.out.println("name: " + name + " pass: " + pass);
		
		try {
			
			Connection con = super.getConnection();
			
			PreparedStatement prepStatement = con.prepareStatement("select role from useraccount where username = ? and password = ?");
			prepStatement.setString(1, name);
			prepStatement.setString(2, pass);
			ResultSet result = prepStatement.executeQuery();
			
			if(result.next()) {
				System.out.print("result:");
				System.out.print(result.toString());
				
				return result.getString("role");	
			} else {
				return null;
			}
			
		} catch(Exception e) {
			System.out.print(e);
			return null;
		}
	}
	
	public boolean authenticateUser(String username, String pass) {
		return false;
	}

}
