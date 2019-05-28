package noud.app.webservices;

public class UserPostgresDaoImpl extends PostgresBaseDao implements userDao {

	@Override
	public String findRoleForUser(String name, String pass) {

		return null;
	}
	
	public boolean authenticateUser(String username, String pass) {
		return false;
	}

}
