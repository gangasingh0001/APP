package Phase;

import Command.CommandProcessor;
import Middleware.Middleware;
import Models.Player;
import Services.InputService;
import Services.MapService;
import Services.OutputService;
import Services.PlayerService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePlayPhase extends GamePhase{
    private final PlayerService d_playerService;

    public GamePlayPhase(PlayerService p_playerService) {
        this.d_playerService = p_playerService;
    }
    /**
     * @param p_command
     */
    @Override
    public void processCommand(Middleware p_command) {
        while (!d_playerService.getPlayersList().isEmpty()) {
            executeRound(d_playerService.getPlayersList());

            // Check and remove players based on a condition
            Iterator<Player> iterator = d_playerService.getPlayersList().iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                if (shouldRemovePlayer(player)) {
                    iterator = d_playerService.getPlayersList().iterator();
                }
            }
        }
    }

//    public void removePlayersBasedOnCondition() {
//        d_players.removeIf(this::shouldRemovePlayer);
//    }

    private void executeRound(List<Player> players) {
        System.out.println("New Round:");

        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);

            // Execute logic for the player
            System.out.println("Player " + currentPlayer + " is playing.");

            currentPlayer.issueOrder();

            // Print the remaining players
            System.out.println("Remaining players: " + players);
        }
    }

    // Replace this method with your specific condition for removing players
    private boolean shouldRemovePlayer(Player player) {
        // Example condition: remove player if the player contains the letter 'a'
        if(player.getCountryAcquired().size()<=0) return d_playerService.removePlayer(player);
        return false;
    }

    @Override
    public void init() {

    }
}
