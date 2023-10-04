package Views;

import Models.Continent;
import Models.Country;
import Models.IWorldMap;

import java.util.List;

/**
 * This is the show map(view) class which is a console command to show
 * all the data include in a map
 */
public class ShowMap {

    /**
     * map information
     */
    IWorldMap d_worldMap;

    /**
     * constructor of the showmap class
     * @param p_worldMap map information
     */
    public ShowMap(IWorldMap p_worldMap) {
        d_worldMap = p_worldMap;
    }

    /**
     * show method to print map information to the console
     */
    public void show() {

        //local variables to store a list of countries
        List<Country> l_allCountries = d_worldMap.getCountries();
        //local variables to store a list of continents
        List<Continent> l_allContinents = d_worldMap.getContinents();

        //print data of continents to the console
        System.out.println("Continents:");
        for (Continent continent : l_allContinents) {
            System.out.println(continent.getName() + " (ID: " + continent.getId() + ", Color: " + continent.getColor() + ", Value: " + continent.getValue() + ")");
        }

        //print data of countries to the console
        System.out.println("\nCountries:");
        for (Country country : l_allCountries) {
            System.out.println(country.getName() + " (ID: " + country.getId() + ", Continent ID: " + country.getContinentId() + ")");
            //local variables to store neighbour countries
            List<Country> l_neighbors = d_worldMap.getNeighborsOfCountry(country);
            //print neighbour countries if list is not empty
            if (!l_neighbors.isEmpty()) {
                System.out.print("   Neighbors: ");
                for (Country neighbor : l_neighbors) {
                    System.out.print(neighbor.getName() + " ");
                }
                System.out.println();
            }
        }
    }
}
