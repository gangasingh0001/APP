package Models;

import java.util.List;

public interface IWorldMap {
    public List<Country> getCountriesInContinent(int continentId);
    public List<Country> getNeighborsOfCountry(Country country);
    public List<Continent> getContinents();
    public List<Country> getCountries();
    public void addBorder(Country country, List<Country> neighborCountries);
    public void addContinent(Continent continent);
    public void addCountry(Country country);
    public void removeCountry(Country country);
    public void removeContinent(Continent country);
    public void removeAllCountriesWithContinentID(int continrntId);
    public String findCountryNameById(int countryId);
}
