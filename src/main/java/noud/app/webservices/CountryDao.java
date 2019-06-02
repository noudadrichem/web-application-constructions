package noud.app.webservices;

import java.sql.*;
import java.util.*;

public interface CountryDao {
	public boolean save(String code, String name, String capital, String continent, String region);
	public List<Country> findAll();
	public Country findByCode(String code);
	public List<Country> find10LargestPopulations();
	public List<Country> fidn10LargestSurfaces();
	public boolean update(String code, String land, String hoofdstad, String regio, int oppervlakte, int inwoners);
	public boolean delete(String code);
}
