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
    private List<Player> players;
    public GamePlayPhase(PlayerService p_playerService, List<Player> players) {
        this.d_playerService = p_playerService;

        this.players = players;
    }
    /**
     * @param p_command
     */
    @Override
    public void processCommand(Middleware p_command) {
        while (!players.isEmpty()) {
            executeRound(players);

            // Check and remove players based on a condition
            Iterator<Player> iterator = players.iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                if (shouldRemovePlayer(player)) {
                    iterator.remove();
                }
            }
        }
    }

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
        if(player.getCountryAcquired().size()<=0) return players.remove(player);
        return false;
    }

    @Override
    public void init() {

    }
}
