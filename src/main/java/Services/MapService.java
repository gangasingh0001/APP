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
import java.util.Set;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

public class MapService implements IMapService{
    /**
     * Uesd to store game map
     */
    IWorldMap worldMap;

    /**
     *  the constructor for Mapservice
     * @param _worldMap
     */


    public MapService(IWorldMap _worldMap) {
        worldMap = _worldMap;
    }

    /**
     * used to get the map information from text file and store all the information into worldMap instance
     * @param commands including loadmap (the name of map)mapname
     */
    public void loadData(Commands commands) {
        String[] params = commands.getL_parameters();// split the command by " "
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/shangmo/Desktop/SOEN 6441/Project/SOEN6441/src/main/java/Data/Maps/"+params[1])))
        //laod the information from text file, params[1]is the name of map
        {
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
                    //uesd to distinct between country and continent
                } else {
                    String[] parts = line.split(" ");
                    if (parts.length >= 3) {

                        if ("continents".equals(currentSection)) {
                            // Create and add a continent
                            String name = parts[0];
                            Continent continent = new Continent(continentIndex++, name,Integer.parseInt(parts[1]), parts[2]);
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

    public boolean validateGraph() {
        // Step 1: Check if every country has at least one neighbor
        if (!checkEveryCountryHasNeighbors()) {
            System.out.println("Validation Error: Not every country has neighbors.");
            return false;
        }

        // Step 2: Check if the graph is connected
        if (!checkGraphConnected()) {
            System.out.println("Validation Error: The graph is not connected.");
            return false;
        }

        return true;
    }

    private boolean checkEveryCountryHasNeighbors() {
        for (Country country : worldMap.getCountries()) {
            List<Country> neighbors = worldMap.getNeighborsOfCountry(country);
            if (neighbors.isEmpty()) {
                return false; // Country has no neighbors
            }
        }
        return true; // All countries have neighbors
    }

    private boolean checkGraphConnected() {
        Set<Country> visitedCountries = new HashSet<>();
        Queue<Country> queue = new LinkedList<>();

        // Start the traversal from the first country (or any country)
        Country startingCountry = worldMap.getCountries().get(0);
        visitedCountries.add(startingCountry);
        queue.add(startingCountry);

        while (!queue.isEmpty()) {
            Country currentCountry = queue.poll();
            List<Country> neighbors = worldMap.getNeighborsOfCountry(currentCountry);
            for (Country neighbor : neighbors) {
                if (!visitedCountries.contains(neighbor)) {
                    visitedCountries.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // If all countries are visited, the graph is connected
        return visitedCountries.size() == worldMap.getCountries().size();
    }

}
