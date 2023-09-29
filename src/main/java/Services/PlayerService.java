package Services;

import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Models.WorldMap;
import Order.Advance;
import Order.Deploy;
import Order.IOrders;
import Utils.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerService implements IPlayerService{
    IMapService mapService;
    IWorldMap worldMap;
    private ArrayList<Player> players = new ArrayList<>();
    public PlayerService(IMapService _mapService,IWorldMap _worldMap) {
        mapService = _mapService;
        worldMap = _worldMap;
    }

    public ArrayList<Player> getPlayersList() {
        return players;
    }

    public void addPlayer(Commands p_command) {
        Player player = new Player(p_command.getL_thirdParameter());
        this.players.add(player);
    }

    public boolean isPlayerRemoved(Commands p_command) {
        Player playerToRemoveObj = null;
        for(Player player: this.players) {
            if(player.getD_playerName().equals(p_command.getL_thirdParameter())) {
                playerToRemoveObj = player;
                break;
            }
        }
        if(playerToRemoveObj!=null) {
            this.players.remove(playerToRemoveObj);
            return true;
        }
        return false;
    }

    public void assignCountries(Commands commands) {
        List<Country> l_countryList = worldMap.getCountries();
        Collections.shuffle(l_countryList);
        int currentItemIndex = 0;
        int n = this.players.size(); // Number of arrays to distribute items into
        int itemsPerArray = l_countryList.size() / n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < itemsPerArray; j++) {
                this.players.get(j).addCountriesOwned(l_countryList.get(currentItemIndex));
                currentItemIndex++;
            }
        }

        // Distribute any remaining items
        while (currentItemIndex < l_countryList.size()) {
            this.players.get(currentItemIndex % n).addCountriesOwned(l_countryList.get(currentItemIndex));
            currentItemIndex++;
        }
    }

    public void issue_order() {
        for(Player player : this.players) {
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
                if (l_command.validateCommand()) {
                    int countryID = Integer.parseInt(l_command.getL_firstParameter());
                    String countryName = worldMap.findCountryNameById(countryID);
                    for (Country country : player.getD_coutriesOwned()) {
                        if (country.getName().equals(countryName)) {
                            deployFlag = true;
                            int numOfArmiesToDeploy = Integer.parseInt(l_command.getL_secondParameter());
                            defaultNumberOfArmies = defaultNumberOfArmies - numOfArmiesToDeploy;
                            player.getD_orderList().add(new Deploy(numOfArmiesToDeploy, l_command.getL_firstParameter(), countryName));
                        }
                    }
                    if (!deployFlag) {
                        // Write exception that country is not owned by this player.
                    }
                }
            }
        }
    }

    public void next_order() {
        for(Player player: players) {
            for (IOrders obj : player.getD_orderList()) {
                if (obj instanceof Deploy) {
                    Deploy deployObj = (Deploy) obj;
                    deployObj.execute(player);
                }
            }
        }
    }
}
