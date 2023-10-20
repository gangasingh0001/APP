package org.app.Views;

import org.app.Models.Country;
import org.app.Models.Player;
import org.app.Services.IPlayerService;

/**
 * This is a show player info(view) class which prints
 * players' information to the console
 */
public class ShowPlayerInfo {

    /**
     * player information
     */
    IPlayerService d_playerService;

    /**
     * Constructor of the ShowPlayerInfo class
     * @param p_playerService player information
     */
    public ShowPlayerInfo(IPlayerService p_playerService) {
        d_playerService = p_playerService;
    }

    /**
     * show method to print a player's information to the console
     * information includes a player's name, countries owned, number of armies, and country names and IDs
     */
    public void displayPlayerInfo() {
        System.out.println("Player Information:");
        for(Player player: d_playerService.getPlayersList()) {
            System.out.println("+-----------------------+");
            System.out.println("| Player Name           |");
            System.out.println("+-----------------------+");
            System.out.printf("| %-21s |\n", player.getD_playerName());
            System.out.println("+-----------------------+");

            System.out.println("\nCountries Owned:");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("| Country ID            | Country Name          | Number of Armies           |");
            System.out.println("+----------------------------------------------------------------------------+");
            for (Country country : player.getD_coutriesOwned()) {
                System.out.printf("| %-21s | %-21s | %-26s |\n",country.getId(), country.getName(),country.getD_Armies());
            }
            System.out.println("+----------------------------------------------------------------------------+");
        }
    }

    /**
     * show method to print all the players in the game
     */
    public void displayPlayers() {
        System.out.println("Players List:");
        System.out.println("+-----------------------+");
        System.out.println("| Player Name           |");
        System.out.println("+-----------------------+");
        for(Player player: d_playerService.getPlayersList()) {
            System.out.printf("| %-21s |\n", player.getD_playerName());
            System.out.println("+-----------------------+");
        }
    }
}
