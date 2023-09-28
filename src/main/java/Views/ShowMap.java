package Views;

import Models.Continent;
import Models.Country;
import Models.IWorldMap;

import java.util.List;

public class ShowMap {
    IWorldMap worldMap;
    public ShowMap(IWorldMap _worldMap) {
        worldMap = _worldMap;
    }

    public void show() {
        List<Country> allCountries = worldMap.getCountries();
        List<Continent> allContinents = worldMap.getContinents();

        System.out.println("Continents:");
        for (Continent continent : allContinents) {
            System.out.println(continent.getName() + " (ID: " + continent.getId() + ", Color: " + continent.getColor() + ", Value: " + continent.getValue() + ")");
        }

        System.out.println("\nCountries:");
        for (Country country : allCountries) {
            System.out.println(country.getName() + " (ID: " + country.getId() + ", Continent ID: " + country.getContinentId() + ")");
            List<Country> neighbors = worldMap.getNeighborsOfCountry(country);
            if (!neighbors.isEmpty()) {
                System.out.print("   Neighbors: ");
                for (Country neighbor : neighbors) {
                    System.out.print(neighbor.getName() + " ");
                }
                System.out.println();
            }
        }
    }
}
