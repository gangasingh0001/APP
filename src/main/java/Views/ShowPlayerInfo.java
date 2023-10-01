package Views;

import Models.Country;
import Models.Player;
import Services.IPlayerService;

public class ShowPlayerInfo {
    IPlayerService playerService;
    public ShowPlayerInfo(IPlayerService _playerService) {
        playerService = _playerService;
    }

    public void displayPlayerInfo() {
        System.out.println("Player Information:");
        for(Player player: playerService.getPlayersList()) {
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

    public void displayPlayers() {
        System.out.println("Players List:");
        System.out.println("+-----------------------+");
        System.out.println("| Player Name           |");
        System.out.println("+-----------------------+");
        for(Player player: playerService.getPlayersList()) {
            System.out.printf("| %-21s |\n", player.getD_playerName());
            System.out.println("+-----------------------+");
        }
    }
}
