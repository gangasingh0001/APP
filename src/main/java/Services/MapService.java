package Services;

import Models.Continent;
import Models.Country;
import Models.IWorldMap;
import Models.WorldMap;
import Utils.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapService implements IMapService{
    IWorldMap worldMap;
    public MapService(IWorldMap _worldMap) {
        worldMap = _worldMap;
    }
    public void loadData(Commands commands) {
        String[] params = commands.getL_parameters();
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/gangasingh/Desktop/Concordia/COMP6441/APP/src/main/java/Data/Maps/"+params[1]))) {
            String line;
            String currentSection = "";
            int continentIndex = 0;
            int countryIndex = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                if (line.startsWith("[")) {
                    currentSection = line.substring(1, line.indexOf("]"));
                } else {
                    String[] parts = line.split(" ");
                    if (parts.length >= 3) {

                        if ("continents".equals(currentSection)) {
                            // Create and add a continent
                            String name = parts[0];
                            Continent continent = new Continent(countryIndex++, name,Integer.parseInt(parts[1]), parts[2]);
                            worldMap.addContinent(continent);
                        } else if ("countries".equals(currentSection)) {
                            // Create and add a country
                            int id =Integer.parseInt(parts[0]);
                            String name = parts[1];
                            int continentId = Integer.parseInt(parts[2]);
                            Country country = new Country(id, name, continentId);
                            worldMap.addCountry(country);
                        } else if ("borders".equals(currentSection)) {
                            // Add borders (neighbors) to a country
                            int id =Integer.parseInt(parts[0]);
                            Country country = worldMap.getCountries().get(id - 1); // Assuming ids start from 1
                            List<Country> neighbors = new ArrayList<>();
                            for (int i = 1; i < parts.length; i++) {
                                int neighborId = Integer.parseInt(parts[i]);
                                neighbors.add(worldMap.getCountries().get(neighborId - 1)); // Assuming ids start from 1
                            }
                            worldMap.addBorder(country, neighbors);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
