package noud.app.webservices;

import java.sql.*;
import java.util.*;

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
			
		ArrayList<Country> tempCountries = new ArrayList<Country>();
		
		try(Connection con = super.getConnection()) {
			String query = "select * from country";
			ResultSet r = con.prepareStatement(query).executeQuery();

			while(r.next()) {
				System.out.println();
				tempCountries.add(new Country(
					r.getString("code"),
					r.getString("iso3"),
					r.getString("name"),
					r.getString("capital"),
					r.getString("continent"),
					r.getString("region"),
					r.getDouble("surfacearea"),
					r.getInt("population"),
					r.getString("governmentform"),
					r.getDouble("latitude"),
					r.getDouble("longitude")
				));
			}
			
			return tempCountries;
			
		} catch(Exception e) {
			System.out.print(e);
			return tempCountries;
		}		
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
