package noud.app.webservices;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServlet.*;
 

@Path("/countries")
public class WorldResource extends CountryPostgresDaoImpl {
	
	private WorldService resource = new WorldService();
	
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
		
		for(Country country : super.findAll()) {
			countriesArray.add(buildCountryJsonObject(country));
		}
		
		return countriesArray.build().toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{countryCode}")
	public String getSingleCountry(@PathParam("countryCode") String countryCode) {
		Country country = super.findByCode(countryCode.toUpperCase());
		
		return buildCountryJsonObject(country).build().toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
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
	@Path("largestpopulations")
	public String getLargestPopulationCountries() {
		
		JsonArrayBuilder countriesArray = Json.createArrayBuilder();
		
		for(Country country : resource.get10LargestPopulations()) {
			countriesArray.add(buildCountryJsonObject(country));
		}
		
		return countriesArray.build().toString();
	}
	
	@DELETE
	@Path("/delete/{code}")
	public String deleteCountryByCode(@PathParam("code") String code) {
		super.delete(code);
		
		JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
		messageBuilder
			.add("message", "succesfully delete country: " + code);
		
		return messageBuilder.build().toString();
	}
	
	
	@PUT
	@Path("/update/{code}")
	@Produces("application/json")
	public Response updateCountry(
		@PathParam("code") String code,
		@FormParam("land_in") String land,
		@FormParam("hoofdstad_in") String hoofdstad,
		@FormParam("regio_in") String regio,
		@FormParam("oppervlakte_in") int oppervlakte,
		@FormParam("inwoners_in") int inwoners) {

		if(super.update(code, land, hoofdstad, regio, oppervlakte, inwoners)) {
			return Response.ok().build();
		} else {
			return Response.status(400).build();
		}
	}
}














