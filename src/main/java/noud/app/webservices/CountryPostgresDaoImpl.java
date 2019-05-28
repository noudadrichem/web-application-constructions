package noud.app.webservices;

import java.sql.*;
import java.util.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
		
		try(Connection con = super.getConnection()) {
			PreparedStatement query = con.prepareStatement("select * from country where code = ?");
			query.setString(1, code);
			ResultSet r = query.executeQuery();
			
			Country found = null;
			
			System.out.println(r);

			while(r.next()) {
				found = new Country(
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
				);
			}
			
			return found;
			
		} catch(Exception e) {
			System.out.print(e);
			return null;
		}	
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
	public boolean delete(String code) {
		try {
			Connection con = super.getConnection();
			
			PreparedStatement prepState = con.prepareStatement("delete from country where code = ?");
			prepState.setString(1, code);
			prepState.executeQuery();

			return true;
		} catch(Exception e) {
			System.out.print(e);

			return false;
		}
	}

	@Override
	public boolean update(String code, String land, String hoofdstad, String regio, int oppervlakte, int inwoners) {
		try (Connection con = super.getConnection()){
			String query = "UPDATE country SET name='" + land + "', capital='"+hoofdstad+"', region='"+regio+"', surfacearea='"+oppervlakte+"', population='"+inwoners+"' ";
			query += "WHERE code='"+ code + "'";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();

			return true;			
		} catch (Exception e) {
			System.out.print(e);

			return false;
		}
	}
}
