package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * a world map class which manages information of countries
 * and continents on a map
 */
public class WorldMap implements IWorldMap{

    /**
     * list of countries
     */
    private List<Country> d_countries;

    /**
     * list of continents
     */
    private List<Continent> d_continents;

    /**
     * Hashmap with keys of continent ID, values as a list of countries
     * in that continent
     */
    private HashMap<Integer, List<Country>> d_continentCountries;

    /**
     * Hashmap with keys of country class, values as a list of
     * neighbour countries
     */
    private HashMap<Country, List<Country>> d_borders;

    /**
     * This is the constructor of the WorldMap class
     */
    public WorldMap() {
        d_countries = new ArrayList<>();
        d_continents = new ArrayList<>();
        d_continentCountries = new HashMap<>();
        d_borders = new HashMap<>();
    }

    /**
     * add method to add a new country
     * @param p_country country class
     */
    public void addCountry(Country p_country) {
        d_countries.add(p_country);
        int continentId = p_country.getContinentId();
        d_continentCountries.computeIfAbsent(continentId, k -> new ArrayList<>()).add(p_country);
    }

    /**
     * add method to add a new continent
     * @param p_continent continent class
     */
    public void addContinent(Continent p_continent) {
        d_continents.add(p_continent);
    }

    /**
     * add method to add a new border
     * @param p_country country class
     * @param p_neighborCountries a list of countries
     */
    public void addBorder(Country p_country, List<Country> p_neighborCountries) {
        d_borders.put(p_country, p_neighborCountries);
    }

    /**
     * getter method to get a list of countries
     * @return a list of countries
     */
    public List<Country> getCountries() {
        return d_countries;
    }

    /**
     * getter method to get a list of continents
     * @return a list of continents
     */
    public List<Continent> getContinents() {
        return d_continents;
    }

    /**
     * getter method to get a list of countries in a continent by a given
     * continent ID
     * @param p_continentId continent ID
     * @return a list of countries in that continent
     */
    public List<Country> getCountriesInContinent(int p_continentId) {
        return d_continentCountries.getOrDefault(p_continentId, new ArrayList<>());
    }

    /**
     * getter method to get neighbour countries
     * @param p_country country class
     * @return neighbour countries of the given country
     */
    public List<Country> getNeighborsOfCountry(Country p_country) {
        return d_borders.getOrDefault(p_country, new ArrayList<>());
    }

    /**
     * print method to find country name by its ID, return
     * not found if ID does not exist
     * @param p_countryId country ID
     * @return country name or not found if country ID does not exist
     */
    public String findCountryNameById(int p_countryId) {
        Country country = getCountries().stream()
                .filter(c -> c.getId() == p_countryId)
                .findFirst()
                .orElse(null);

        return (country != null) ? country.getName() : "Country not found";
    }

}
