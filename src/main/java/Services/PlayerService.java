package Services;

import Constants.ApplicationConstants;
import Models.*;
import Orders.*;
import Utils.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * a class used to store players and manipulate players
 */
public class PlayerService implements IPlayerService{
    /**
     * a class used to store game map and manipulate game map
     */
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
    //TODO: Create hashmap for storing countries owned by player
    HashMap<Country, Player> d_playerOwnedCountriesMap;

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
        d_playerOwnedCountriesMap = new HashMap<Country, Player>();
    }

    /**
     * Get all the players
     * @return all the players
     */

    public ArrayList<Player> getPlayersList() {
        return d_players;
    }

    public HashMap<Country,Player> getD_playerOwnedCountriesMap() {
        return d_playerOwnedCountriesMap;
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
            if(player.getD_playerName().equals(p_command.getL_secondParameter())) {
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
                d_playerOwnedCountriesMap.put(l_countryList.get(currentItemIndex),this.d_players.get(i));
//                this.d_players.get(i).addCountriesOwned(l_countryList.get(currentItemIndex));
//                l_countryList.get(currentItemIndex).setD_ownedBy(this.d_players.get(i));
                currentItemIndex++;
            }
        }

        // Distribute any remaining items
        while (currentItemIndex < l_countryList.size()) {
            d_playerOwnedCountriesMap.put(l_countryList.get(currentItemIndex),this.d_players.get(currentItemIndex % n));
//            this.d_players.get(currentItemIndex % n).addCountriesOwned(l_countryList.get(currentItemIndex));
//            l_countryList.get(currentItemIndex).setD_ownedBy(this.d_players.get(currentItemIndex % n));
            currentItemIndex++;
        }
    }

    /**
     *Used to create deploy order for player
     */
    public void issue_order() {
        //set every country neutral to false
        for (Map.Entry<Country, Player> entry : d_playerOwnedCountriesMap.entrySet()) {
            Country country=entry.getKey();
            country.setD_NeutralCountry(false);
        }

        for(Player player : this.d_players) {
            int defaultNumberOfArmies = player.getD_numberOfArmies();
            while (defaultNumberOfArmies>0) { // Deploy command code
                BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
                String l_commandEntered = null;
                try {
                    System.out.println(player.getD_playerName().toUpperCase() + ": Please enter issue order / type 'exit' to quit");
                    if(!player.getD_PlayerCards().isEmpty() && player.checkIfCardExists(CardType.REINFORCEMENT)) {
                        System.out.println(player.getD_playerName() + ": Please enter Yes to use "+ CardType.REINFORCEMENT + " card");
                        l_commandEntered = l_reader.readLine();
                        if(l_commandEntered.equalsIgnoreCase("yes")) defaultNumberOfArmies+=5;
                        player.removeCard(CardType.REINFORCEMENT);
                    }
                    System.out.println(player.getD_playerName() + ": Please enter Deploy order or type 'exit' to quit");
                    l_commandEntered = l_reader.readLine();
                } catch (IOException l_ioException) {
                    l_ioException.printStackTrace();
                }
                Commands l_command = new Commands(l_commandEntered);
                boolean deployFlag = false;
                if (l_command.validateCommand() && !l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    int countryID = Integer.parseInt(l_command.getL_firstParameter());
                    String countryName = d_worldMap.findCountryNameById(countryID);
                    int numOfArmiesToDeploy = Integer.parseInt(l_command.getL_secondParameter());
                    defaultNumberOfArmies = defaultNumberOfArmies - numOfArmiesToDeploy;
                    player.getD_orderList().add(new Deploy(numOfArmiesToDeploy, l_command.getL_firstParameter(), countryName,player,d_playerOwnedCountriesMap));
                } else if (l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    break;
                }
            } // Creation of deploy commands completed


            // Start for advance commands
            while(true) {
                BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
                String l_commandEntered = null;
                try {
                    if(!player.getD_PlayerCards().isEmpty()) {
//                        List<Card> playerCards = player.getD_PlayerCards();
//                        Card firstCard = playerCards.getFirst();
//                        String cardType = firstCard.getCardType().name();
                        System.out.println(player.getD_playerName() + ": Please enter Yes to use "+ player.getD_PlayerCards().get(0).getCardType().name() + " card");
                        l_commandEntered = l_reader.readLine();
                        if(l_commandEntered.equalsIgnoreCase("yes")) {
                            System.out.println(player.getD_playerName() + ": Please enter command for "+ player.getD_PlayerCards().get(0).getCardType().name() + " card");
                            l_commandEntered = l_reader.readLine();
                            //TODO: validate the entered card command
                            Commands l_command = new Commands(l_commandEntered);
                            String sourceCountryID = l_command.getL_firstParameter();
                            String targetCountryID = l_command.getL_secondParameter();
                            String numOfArmies = l_command.getL_thirdParameter();
                            if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.AIRLIFT)) {
                                Airlift airlift = new Airlift(Integer.parseInt(numOfArmies),d_worldMap.findCountryNameById(Integer.parseInt(targetCountryID)),d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_playerOwnedCountriesMap);
                                airlift.execute();
                            } else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.BLOCKADE)) {
                                //TODO: Add blockade functionality
                                Blockade blockade = new Blockade(d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_playerOwnedCountriesMap);
                                blockade.execute();
                            } else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.BOMB)) {
                                //TODO: Add Bomb functionality
                                Bomb bomb = new Bomb(d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_playerOwnedCountriesMap);
                                bomb.execute();
                            } else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.DIPLOMACY)) {
                                //TODO: Add Negotiate/Diplomacy functionality
                                Diplomacy diplomacy = new Diplomacy(l_command.getL_firstParameter());
                                diplomacy.execute();
                            }
                        }
                    }
                    System.out.println(player.getD_playerName().toUpperCase() + ": Please enter Advance order or type 'exit' to quit");
                    l_commandEntered = l_reader.readLine();
                } catch (IOException l_ioException) {
                    l_ioException.printStackTrace();
                }
                Commands l_command = new Commands(l_commandEntered);
                if (l_command.validateCommand() && !l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    String countryNameFrom = l_command.getL_firstParameter();
                    String countryNameTo = l_command.getL_secondParameter();
                    int numOfArmiesToDeploy = Integer.parseInt(l_command.getL_thirdParameter());
                    defaultNumberOfArmies = defaultNumberOfArmies - numOfArmiesToDeploy;
                    player.getD_orderList().add(new Advance(countryNameFrom, countryNameTo, numOfArmiesToDeploy,player,d_worldMap,d_playerOwnedCountriesMap));
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

        for(Player player: d_players) { //Execute all deploy orders of all players
            for (IOrders obj : player.getD_orderList()) {
                if (obj instanceof Deploy deploy) {
                    deploy.execute();
                }
            }
        }

        int allPlayersOrderCompleted = 0;
        int currentPlayerIndex = 0;
        while(allPlayersOrderCompleted!=d_players.size()) {
            Player currentPlayer = d_players.get(currentPlayerIndex);
            if(!currentPlayer.getD_orderList().isEmpty()) {
                IOrders obj = currentPlayer.getD_orderList().poll();
                if (obj instanceof Advance advance) {
                    advance.execute();
                    if (currentPlayer.getD_orderList().isEmpty()) {
                        allPlayersOrderCompleted++;
                    }
                }
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % d_players.size();
        }

        for(Player player: d_players) {
            if(player.ifAcquiredCountryInThisTurn()) {
                player.addPlayerCard(new Card(CardType.getRandomCard()));
                player.clearAcquiredCountriesList();
            }
        }
    }
}
