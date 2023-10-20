package org.app.Services;

import org.app.Constants.ApplicationConstants;
import org.app.Models.Country;
import org.app.Models.IWorldMap;
import org.app.Models.Player;
import org.app.Orders.Deploy;
import org.app.Orders.IOrders;
import org.app.Utils.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * a class used to store game map and manipulate game map
 */
public class PlayerService implements IPlayerService{

    IMapService d_mapService;
    /**
     * world map pointer to the game map in d_mapService, we only have one map instance, and it is created in
     * d_mapService class, but in this class we will use map instance in d_mapService to initial the worldMap attribute in
     * PlayerService
     */
    IWorldMap d_worldMap;
    /**
     * Used to store all the players in the game
     */
    private ArrayList<Player> d_players = new ArrayList<>();

    /**
     * The constructor for PlayerService
     *
     * @param p_mapService used to initial d_d_mapService
     * @param p_worldMap used to initial d_d_worldMap
     */
    public PlayerService(IMapService p_mapService,IWorldMap p_worldMap) {
        d_mapService = p_mapService;
        d_worldMap = p_worldMap;
    }

    /**
     * Get all the players
     * @return all the players
     */

    public ArrayList<Player> getPlayersList() {
        return d_players;
    }

    /**
     * add players to the arraylist<Player> in PlayerService
     * @param p_command should provide playerID
     */
    public void addPlayer(Commands p_command) {
        Player player = new Player(p_command.getL_secondParameter());
        this.d_players.add(player);
    }

    /**
     * remove the player from rraylist<Player> in PlayerService, if it is not exist, then return false
     * @param p_command should include the name player removed
     * @return if remove successful then return true, else return false
     */
    public boolean isPlayerRemoved(Commands p_command) {
        Player l_playerToRemoveObj = null;
        for(Player player: this.d_players) {
            if(player.getD_playerName().equals(p_command.getL_thirdParameter())) {
                l_playerToRemoveObj = player;
                break;
            }
        }
        if(l_playerToRemoveObj!=null) {
            this.d_players.remove(l_playerToRemoveObj);
            return true;
        }
        return false;
    }

    /**
     * Assign all the countries to all players as far as fair
     *
     */

    public void assignCountries() {
        List<Country> l_countryList = d_worldMap.getCountries();
        Collections.shuffle(l_countryList);
        int currentItemIndex = 0;
        int n = this.d_players.size(); // Number of arrays to distribute items into
        int itemsPerArray = l_countryList.size() / n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < itemsPerArray; j++) {
                this.d_players.get(i).addCountriesOwned(l_countryList.get(currentItemIndex));
                currentItemIndex++;
            }
        }

        // Distribute any remaining items
        while (currentItemIndex < l_countryList.size()) {
            this.d_players.get(currentItemIndex % n).addCountriesOwned(l_countryList.get(currentItemIndex));
            currentItemIndex++;
        }
    }

    /**
     *Used to create deploy order for player
     */
    public void issue_order() {
        for(Player player : this.d_players) {
            int defaultNumberOfArmies = player.getD_numberOfArmies();
            while (defaultNumberOfArmies>0) {
                BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
                String l_commandEntered = null;
                try {
                    System.out.println(player.getD_playerName() + ": Please enter issue order / type 'exit' to quit");
                    l_commandEntered = l_reader.readLine();
                } catch (IOException l_ioException) {
                    l_ioException.printStackTrace();
                }
                Commands l_command = new Commands(l_commandEntered);
                boolean deployFlag = false;
                if (l_command.validateCommand() && !l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    int countryID = Integer.parseInt(l_command.getL_firstParameter());
                    String countryName = d_worldMap.findCountryNameById(countryID);
                    for (Country country : player.getD_coutriesOwned()) {
                        if (country.getName().equals(countryName)) {
                            deployFlag = true;
                            int numOfArmiesToDeploy = Integer.parseInt(l_command.getL_secondParameter());
                            defaultNumberOfArmies = defaultNumberOfArmies - numOfArmiesToDeploy;
                            player.getD_orderList().add(new Deploy(numOfArmiesToDeploy, l_command.getL_firstParameter(), countryName));
                            break;
                        }
                    }
                    if (!deployFlag) {
                        // Write exception that country is not owned by this player.
                    }
                } else if (l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    break;
                }
            }
        }
    }

    /**
     *used to execute deploy order
     */
    public void next_order() {
        for(Player player: d_players) {
            for (IOrders obj : player.getD_orderList()) {
                if (obj instanceof Deploy) {
                    Deploy deployObj = (Deploy) obj;
                    deployObj.execute(player);
                }
            }
        }
    }
}
