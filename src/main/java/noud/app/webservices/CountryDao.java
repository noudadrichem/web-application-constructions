package noud.app.webservices;

import java.util.*;

public interface CountryDao {
	public boolean save(Country country);
	public List<Country> findAll();
	public Country findByCode(String code);
	public List<Country> find10LargestPopulations();
	public List<Country> fidn10LargestSurfaces();
	public boolean update(Country country);
	public boolean delete(Country country);
}
