package Models;

import java.util.List;

/**
 * This is an interface class which manages information of
 * continents and countries on a map
 */
public interface IWorldMap {

    /**
     * getter method to get a list of countries by a given continent ID
     * @param p_continentId continent ID
     * @return a list of countries in this continent
     */
    public List<Country> getCountriesInContinent(int p_continentId);

    /**
     * getter method to get a list of neighbour countries by a given country class
     * @param p_country country class
     * @return a list of neighbour countries
     */
    public List<Country> getNeighborsOfCountry(Country p_country);

    /**
     * getter method to get all continents on the map
     * @return a list of continents
     */
    public List<Continent> getContinents();

    /**
     * getter method to get a list of countries
     * @return a list of countries
     */
    public List<Country> getCountries();

    /**
     * add method to add a new border with a list of countries
     * @param p_country country class
     * @param p_neighborCountries a list of countries
     */
    public void addBorder(Country p_country, List<Country> p_neighborCountries);

    /**
     * add method to add a new continent to the map
     * @param p_continent continent class
     */
    public void addContinent(Continent p_continent);

    /**
     * add method to add a new country
     * @param p_country country class
     */
    public void addCountry(Country p_country);

    /**
     * find method to find a country by its ID
     * @param p_countryId country ID
     * @return country name
     */
    public String findCountryNameById(int p_countryId);
}
