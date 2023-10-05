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
        int p_continentId = p_country.getContinentId();
        d_continentCountries.computeIfAbsent(p_continentId, k -> new ArrayList<>()).add(p_country);
        d_borders.computeIfAbsent(p_country, k -> new ArrayList<>());
    }

    public void removeCountry(Country p_country) {
        d_countries.remove(p_country);
        d_borders.remove(p_country);
    }

    public void removeContinent(Continent p_continent) {
        d_continents.remove(p_continent);
    }

    public void removeAllCountriesWithContinentID(int p_continentId){
        List<Country> c = d_countries.stream().filter(e -> e.getContinentId() == p_continentId).toList();
        for(Country country: c){
            if(country.getContinentId() == p_continentId){
                d_borders.remove(country);
            }
        }
        d_countries.removeIf(e -> e.getContinentId() == p_continentId);
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

   public List<String> formatWorldMap(){
    List<String> worldMap = new ArrayList<>();
    worldMap.add("[continents]\n");
    d_continents.forEach(
        e -> worldMap.add(e.toString()+"\n")
        );
    worldMap.add("[countries]\n");  
    d_countries.forEach(
        e -> worldMap.add(e.toString()+"\n")
        );
    worldMap.add("[borders]\n");  
    d_borders.forEach((Country country, List<Country> list)->{
        // List<String> countryIdList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        for(Country c: list) {
            // countryIdList.add();
            stringBuilder.append(" " + c.getId());
        }
        worldMap.add(country.getId() + stringBuilder.toString() + "\n");
    });
    return worldMap;
   }

}
