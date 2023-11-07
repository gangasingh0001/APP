package Views;

import Models.Country;
import Models.Player;
import Services.IPlayerService;

import java.util.HashMap;
import java.util.Map;

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
//        for(Player player: d_playerService.getPlayersList()) {
//            System.out.println("+-----------------------+");
//            System.out.println("| Player Name           |");
//            System.out.println("+-----------------------+");
//            System.out.printf("| %-21s |\n", player.getD_playerName());
//            System.out.println("+-----------------------+");
//
//            System.out.println("\nCountries Owned:");
//            System.out.println("+----------------------------------------------------------------------------+");
//            System.out.println("| Country ID            | Country Name          | Number of Armies           |");
//            System.out.println("+----------------------------------------------------------------------------+");
//            for (Country country : player.getD_coutriesOwned()) {
//                System.out.printf("| %-21s | %-21s | %-26s |\n",country.getId(), country.getName(),country.getD_Armies());
//            }
//            System.out.println("+----------------------------------------------------------------------------+");
//        }
        Map<Player, StringBuilder> playerCountryTable = new HashMap<>();

        for (Map.Entry<Country, Player> entry : d_playerService.getD_playerOwnedCountriesMap().entrySet()) {
            Country country = entry.getKey();
            Player player = entry.getValue();

            // Group countries by player
            playerCountryTable.computeIfAbsent(player, k -> new StringBuilder())
                    .append(String.format("| %-21s | %-21s | %-26s |\n", country.getId(), country.getName(), country.getD_Armies()));
        }

        // Print the result
        for (Map.Entry<Player, StringBuilder> entry : playerCountryTable.entrySet()) {
            Player player = entry.getKey();
            StringBuilder countries = entry.getValue();
            String countryList = countries.toString().replaceAll(", $", ""); // Remove trailing comma and space
            System.out.println("\n Player: " + player.getD_playerName().toUpperCase());
            System.out.println("\nCountries Owned:");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("| Country ID            | Country Name          | Number of Armies           |");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println(countryList);
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
