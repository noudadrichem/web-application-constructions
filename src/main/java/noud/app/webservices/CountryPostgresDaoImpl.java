package noud.app.webservices;

import java.sql.*;
import java.util.List;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDao {

	@Override
	public boolean save(Country country) {
		try(Connection con = super.getConnection()) {
			String query = "";
			ResultSet result = con.prepareStatement(query).executeQuery();
			
			
			
			return true;
		} catch(Exception e) {
			System.out.print(e);
			return false;
		}
	}

	@Override
	public List<Country> findAll() {
		return null;
	}

	@Override
	public Country findByCode(String code) {
		return null;
	}

	@Override
	public List<Country> find10LargestPopulations() {
		return null;
	}

	@Override
	public List<Country> fidn10LargestSurfaces() {
		return null;
	}

	@Override
	public boolean update(Country country) {
		return false;
	}

	@Override
	public boolean delete(Country country) {
		return false;
	}
	
	

}
