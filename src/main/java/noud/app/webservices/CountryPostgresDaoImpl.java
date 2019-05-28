package noud.app.webservices;

import java.sql.*;
import java.util.*;
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

//	@PUT
//	@Path("{code}")
//	public Response update(@PathParam("code") String code, @FormParam("name") String naam, @FormParam("capital") String hoofdstad,
//			@FormParam("surface") int oppervlakte, @FormParam("population") int mensen) {
//
//		CountryPostgresDaoImpl db = new CountryPostgresDaoImpl();
//		Country c = new Country(); 
//		c.setCode(code);
//		c.setName(naam);
//		c.setCapital(hoofdstad);
//		c.setPopulation(mensen);
//		c.setSurface(oppervlakte);
//		boolean r = db.update(c);
//		
//		if (!r) {
//			return Response.status(404).build();
//		}
//		
//		return Response.ok().build();
//	}

	@Override
	public boolean update(Country country) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
