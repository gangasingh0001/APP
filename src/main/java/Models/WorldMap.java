package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldMap implements IWorldMap{
    private List<Country> countries;
    private List<Continent> continents;
    private HashMap<Integer, List<Country>> continentCountries;
    private HashMap<Country, List<Country>> borders;

    public WorldMap() {
        countries = new ArrayList<>();
        continents = new ArrayList<>();
        continentCountries = new HashMap<>();
        borders = new HashMap<>();
    }

    public void addCountry(Country country) {
        countries.add(country);
        int continentId = country.getContinentId();
        continentCountries.computeIfAbsent(continentId, k -> new ArrayList<>()).add(country);
    }

    public void addContinent(Continent continent) {
        continents.add(continent);
    }

    public void addBorder(Country country, List<Country> neighborCountries) {
        borders.put(country, neighborCountries);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<Continent> getContinents() {
        return continents;
    }

    public List<Country> getCountriesInContinent(int continentId) {
        return continentCountries.getOrDefault(continentId, new ArrayList<>());
    }

    public List<Country> getNeighborsOfCountry(Country country) {
        return borders.getOrDefault(country, new ArrayList<>());
    }

}
