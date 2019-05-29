package noud.app.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServlet.*;

@Path("/countries")
public class WorldResource {
	
	private WorldService resource = new WorldService();
	private CountryDao dao = new CountryPostgresDaoImpl();
	
	public JsonObjectBuilder buildCountryJsonObject(Country country) {
		
		JsonObjectBuilder singleCountry = Json.createObjectBuilder();
		singleCountry
			.add("code", country.getCode())
			.add("name", country.getName())
			.add("capital", country.getCapital())
			.add("surface", country.getSurface())
			.add("government", country.getGovernment())
			.add("lat", country.getLatitude())
			.add("iso3", country.getIso3())
			.add("continent", country.getContinent())
			.add("region", country.getRegion())
			.add("population", country.getPopulation())
			.add("lng", country.getLongitude());
		
		return singleCountry;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCountries() {
		JsonArrayBuilder countriesArray = Json.createArrayBuilder();
		
		for(Country country : dao.findAll()) {
			countriesArray.add(buildCountryJsonObject(country));
		}
		
		return countriesArray.build().toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{countryCode}")
	public String getSingleCountry(@PathParam("countryCode") String countryCode) {
		Country country = dao.findByCode(countryCode.toUpperCase());
		
		return buildCountryJsonObject(country).build().toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"user", "admin"})
	@Path("largestsurface")
	public String getLargestSurfaceCountries() {
		
		JsonArrayBuilder countriesArray = Json.createArrayBuilder();
			
		for(Country country : resource.get10LargestSurfaces()) {
			countriesArray.add(buildCountryJsonObject(country));
		}
		
		return countriesArray.build().toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"user", "admin"})
	@Path("largestpopulations")
	public String getLargestPopulationCountries() {
		
		JsonArrayBuilder countriesArray = Json.createArrayBuilder();
		
		for(Country country : resource.get10LargestPopulations()) {
			countriesArray.add(buildCountryJsonObject(country));
		}
		
		return countriesArray.build().toString();
	}
	
	@DELETE
	@RolesAllowed("admin")
	@Path("/delete/{code}")
	public String deleteCountryByCode(@PathParam("code") String code) {
		dao.delete(code);
		
		JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
		messageBuilder
			.add("message", "succesfully delete country: " + code);
		
		return messageBuilder.build().toString();
	}

	@POST
	@RolesAllowed("admin")
	@Path("/add")
	@Produces("application/json")
	public Response save(
		@FormParam("code") String code,
		@FormParam("name") String name,
		@FormParam("capital") String capital,
		@FormParam("continent") String continent,
		@FormParam("region") String region
	) {

		System.out.println("code: " + code);
		System.out.println("name: " + name);
		System.out.println("capital: " + capital);

		if(dao.save(code, name, capital, continent, region)) {
			return Response.ok().build();
		} else {
			return Response.status(400).build();
		}
	}
	
	@PUT
	@RolesAllowed("admin")
	@Path("/update/{code}")
	@Produces("application/json")
	public Response updateCountry(
		@PathParam("code") String code,
		@FormParam("land_in") String land,
		@FormParam("hoofdstad_in") String hoofdstad,
		@FormParam("regio_in") String regio,
		@FormParam("oppervlakte_in") int oppervlakte,
		@FormParam("inwoners_in") int inwoners) {

			System.out.println("update: " + code);
			System.out.println("land: " + land);
			System.out.println("hoofdstad: " + hoofdstad);
			System.out.println("regio: " + regio);
			System.out.println("oppervlakte: " + oppervlakte);
			System.out.println("inwoners: " + land);

		if(dao.update(code, land, hoofdstad, regio, oppervlakte, inwoners)) {
			System.out.println("update succesfull with code " + code);
			return Response.ok().build();
		} else {
			System.out.println("update failed with code " + code);
			return Response.status(400).build();
		}
	}
}














