package Models;

import java.util.List;

public class Player {
    /**
     * color to show details with on map.
     */
    private String d_color;

    /**
     * Name of the player.
     */
    private String d_name;

    /**
     * List of countries owned by player.
     */
    List<Country> d_coutriesOwned;

    /**
     * List of Continents owned by player.
     */
    List<Continent> d_continentsOwned;

    /**
     * List of orders of player.
     */
    //List<Order> d_orderList;

    /**
     * Number of armies allocated to player.
     */
    Integer d_noOfUnallocatedArmies;
}
