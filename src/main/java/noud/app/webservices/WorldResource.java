package noud.app.webservices;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 

@Path("/countries")
public class WorldResource {
	
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
		
		for(Country country : resource.getAllCountries()) {
			countriesArray.add(buildCountryJsonObject(country));
		}
		
		return countriesArray.build().toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{countryCode}")
	public String getSingleCountry(@PathParam("countryCode") String countryCode) {
		Country country = resource.getCountryByCode(countryCode);
		
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

}














