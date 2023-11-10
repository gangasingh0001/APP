package Services;

import Constants.ApplicationConstants;
import Models.*;
import Utils.Commands;
import Views.ShowMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * a class used to store game class and manipulate game class
 */
public class MapService implements IMapService{
    /**
     * Used to store game map
     */
    IWorldMap d_worldMap;

    /**
     *  the constructor for Mapservice
     * @param p_worldMap
     */

    ShowMap d_mapView;

    private boolean mapEditingAllowed = true;

    private Logger d_logger;
    public MapService(Logger p_logger,IWorldMap p_worldMap) {
        d_logger = p_logger;
        d_worldMap = p_worldMap;
        d_mapView  = new ShowMap(d_worldMap);
    }
    public MapService(IWorldMap p_worldMap) {
        d_worldMap = p_worldMap;
    }

    /**
     * used to get the map information from text file and store all the information into worldMap instance
     * @param d_commands including loadmap (the name of map)mapname
     */
    public void loadData(String[] params) {
        if (!mapEditingAllowed) {
            System.out.println("Map editing is not allowed.");
            return;
        }
        //String[] params = d_commands.getL_parameters();// split the command by " "
        String currentDirectory = System.getProperty("user.dir");
        File file = new File(currentDirectory);
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()+"/src/main/java/Data/Maps/"+params[1])))

        //laod the information from text file, params[1]is the name of map
        {
            String line;
            String currentSection = "";
            int continentIndex = 1;

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
                            d_worldMap.addContinent(continent);
                        } else if ("countries".equals(currentSection)) {
                            // Create and add a country
                            int id =Integer.parseInt(parts[0]);
                            String name = parts[1];
                            int continentId = Integer.parseInt(parts[2]);
                            Country country = new Country(id, name, continentId);
                            d_worldMap.addCountry(country);
                        } else if ("borders".equals(currentSection)) {
                            // Add borders (neighbors) to a country
                            int id =Integer.parseInt(parts[0]);
                            Country country = d_worldMap.getCountries().get(id - 1); // Assuming ids start from 1
                            List<Country> neighbors = new ArrayList<>();
                            for (int i = 1; i < parts.length; i++) {
                                int neighborId = Integer.parseInt(parts[i]);
                                neighbors.add(d_worldMap.getCountries().get(neighborId - 1)); // Assuming ids start from 1
                            }
                            d_worldMap.addBorder(country, neighbors);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
             new FileNotFoundException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            throw new NullPointerException("Null");
        }
    }

    public void mapEditor(String fileName){
        if (!mapEditingAllowed) {
            System.out.println("Map editing is not allowed.");
            return;
        }
        String filePath = "./src/main/java/Data/Maps/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * save map in to a txt file
     */
    public void saveMap(Commands commands){
        if (!mapEditingAllowed) {
            System.out.println("Map editing is not allowed.");
            return;
        }
        String filePath = "./src/main/java/Data/Maps/" + commands.getL_firstParameter();
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write the text to the file
            for(String str : d_worldMap.formatWorldMap()){
                writer.write(str);
            }   
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    /**
     * check if the countries all connected
     *
     * @return true if all the country connected together, else return false
     */
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

    /**
     * check if all the countries are connected
     * @return if every country has neighbor then return true else return false
     */
    private boolean checkEveryCountryHasNeighbors() {
        for (Country l_country : d_worldMap.getCountries()) {
            List<Country> neighbors = d_worldMap.getNeighborsOfCountry(l_country);
            if (neighbors.isEmpty()) {
                return false; // Country has no neighbors
            }
        }
        return true; // All countries have neighbors
    }

    /**
     * use DFS to travel all the country, if we can visit all the country, the return true, else return false
     * @return true if all the countries are connected
     */
    private boolean checkGraphConnected() {
        Set<Country> visitedCountries = new HashSet<>();
        Queue<Country> queue = new LinkedList<>();

        // Start the traversal from the first country (or any country)
        Country startingCountry = d_worldMap.getCountries().get(0);
        visitedCountries.add(startingCountry);
        queue.add(startingCountry);

        while (!queue.isEmpty()) {
            Country currentCountry = queue.poll();
            List<Country> neighbors = d_worldMap.getNeighborsOfCountry(currentCountry);
            for (Country neighbor : neighbors) {
                if (!visitedCountries.contains(neighbor)) {
                    visitedCountries.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // If all countries are visited, the graph is connected
        return visitedCountries.size() == d_worldMap.getCountries().size();
    }

    public void showMap() {
        d_mapView.show();
    }

    public void disableMapEditing() {
        mapEditingAllowed = false;
    }

}
