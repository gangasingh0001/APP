package Services;

import Cards.Airlift;
import Cards.Blockade;
import Cards.Bomb;
import Cards.Diplomacy;
import Constants.ApplicationConstants;
import Models.*;
import Orders.*;
import Middleware.Middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Logger;

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

    /**
     * get logging records
     */
    private Logger logger;

    //TODO: Create hashmap for storing countries owned by player
    /**
     * hashmap for storing countries owned by player
     */
    HashMap<Country, Player> d_playerOwnedCountriesMap;

    /**
     * a list of players
     */
    private ArrayList<Player> d_players = new ArrayList<>();

    /**
     *  constructor for PlayerService with logging function
     * @param logger get logging records
     * @param p_mapService used to initial d_d_mapService
     * @param p_worldMap used to initial d_d_worldMap
     */
    public PlayerService(Logger logger,IMapService p_mapService, IWorldMap p_worldMap) {
        this.logger = logger;
        d_mapService = p_mapService;
        d_worldMap = p_worldMap;
        d_playerOwnedCountriesMap = new HashMap<Country, Player>();
    }

    /**
     * default constructor for PlayerService
     * @param p_mapService used to initial d_d_mapService
     * @param p_worldMap used to initial d_d_worldMap
     */
    public PlayerService(IMapService p_mapService, IWorldMap p_worldMap) {
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
    public void addPlayer(Middleware p_command) {
//        Player player = new Player(p_command.getL_secondParameter());
//        this.d_players.add(player);
    }

    /**
     * remove the player from rraylist<Player> in PlayerService, if it is not exist, then return false
     * @param p_command should include the name player removed
     * @return if remove successful then return true, else return false
     */
    public boolean isPlayerRemoved(Middleware p_command) {
        Player l_playerToRemoveObj = null;
        for(Player player: this.d_players) {
            if(player.getD_playerName().equals(p_command.getL_secondParameter())) {
                l_playerToRemoveObj = player;
                break;
            }
        }
        if(l_playerToRemoveObj!=null) {
            this.d_players.remove(l_playerToRemoveObj);
            logger.severe("Player " + l_playerToRemoveObj.getD_playerName() + " removed successfully.");
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
        logger.severe("The list of countries is shuffled...");
        int currentItemIndex = 0;
        int n = this.d_players.size(); // Number of arrays to distribute items into
        int itemsPerArray = l_countryList.size() / n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < itemsPerArray; j++) {
                d_playerOwnedCountriesMap.put(l_countryList.get(currentItemIndex),this.d_players.get(i));
                logger.severe(l_countryList.get(currentItemIndex).toString() + " is assigned to " + this.d_players.get(i).getD_playerName());
//                this.d_players.get(i).addCountriesOwned(l_countryList.get(currentItemIndex));
//                l_countryList.get(currentItemIndex).setD_ownedBy(this.d_players.get(i));
                currentItemIndex++;
            }
        }

        // Distribute any remaining items
        while (currentItemIndex < l_countryList.size()) {
            d_playerOwnedCountriesMap.put(l_countryList.get(currentItemIndex),this.d_players.get(currentItemIndex % n));
            logger.severe(l_countryList.get(currentItemIndex).toString() + " is assigned to " + this.d_players.get(currentItemIndex % n).getD_playerName());
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
            logger.severe("Set country " + country.getName() + " to not neutral...");
        }

        for(Player player : this.d_players) {
            int defaultNumberOfArmies = player.getD_numberOfArmies();
            while (defaultNumberOfArmies>0) { // Deploy command code
                BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
                String l_commandEntered = null;
                try {
                    System.out.println(player.getD_playerName().toUpperCase() + ": Please enter deploy order / type 'exit' to quit");
                    if(!player.getD_PlayerCards().isEmpty() && player.checkIfCardExists(CardType.REINFORCEMENT)) {
                        System.out.println(player.getD_playerName() + ": Please enter Yes to use "+ CardType.REINFORCEMENT + " card");
                        l_commandEntered = l_reader.readLine();
                        if(l_commandEntered.equalsIgnoreCase("yes")) {
                            defaultNumberOfArmies+=5;
                            logger.severe("Reinforcement card is used...Armies added by 5!");
                        }
                        player.removeCard(CardType.REINFORCEMENT);
                        logger.severe("Reinforcement card is removed！");
                    }
                    l_commandEntered = l_reader.readLine();
                    logger.severe(l_commandEntered);
                } catch (IOException l_ioException) {
                    l_ioException.printStackTrace();
                }
                Middleware l_command = new Middleware(l_commandEntered);
                boolean deployFlag = false;
                if (l_command.validateCommand() && !l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    int countryID = Integer.parseInt(l_command.getL_firstParameter());
                    String countryName = d_worldMap.findCountryNameById(countryID);
                    int numOfArmiesToDeploy = Integer.parseInt(l_command.getL_secondParameter());
                    defaultNumberOfArmies = defaultNumberOfArmies - numOfArmiesToDeploy;
                    player.getD_orderList().add(new Deploy(numOfArmiesToDeploy, l_command.getL_firstParameter(), countryName,player,d_playerOwnedCountriesMap));
                    logger.severe("Current command is: Deploy " + numOfArmiesToDeploy + " armies to country " + l_command.getL_firstParameter() + " " + countryName + " from player " + player.getD_playerName());
                } else if (l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    logger.severe("Exit current phase...");
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
                            logger.severe(player.getD_playerName() + " selects card: " + player.getD_PlayerCards().get(0).getCardType().name());
                            l_commandEntered = l_reader.readLine();
                            //TODO: validate the entered card command
                            Middleware l_command = new Middleware(l_commandEntered);
                            String sourceCountryID = l_command.getL_firstParameter();
                            String targetCountryID = l_command.getL_secondParameter();
                            String numOfArmies = l_command.getL_thirdParameter();
                            if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.AIRLIFT)) {
                                Airlift airlift = new Airlift(Integer.parseInt(numOfArmies),d_worldMap.findCountryNameById(Integer.parseInt(targetCountryID)),d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_playerOwnedCountriesMap);
                                logger.severe(player.getD_playerName() + " is using Airlift card!");
                                airlift.execute();
                            } else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.BLOCKADE)) {
                                //TODO: Add blockade functionality
                                Blockade blockade = new Blockade(d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_playerOwnedCountriesMap);
                                logger.severe(player.getD_playerName() + " is using Blockade card!");
                                blockade.execute();
                            } else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.BOMB)) {
                                //TODO: Add Bomb functionality

                                Bomb bomb = new Bomb(d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_worldMap,d_playerOwnedCountriesMap);
                                bomb.execute();
                            } else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.DIPLOMACY)) {
                                //TODO: Add Negotiate/Diplomacy functionality
                                Diplomacy diplomacy = new Diplomacy(l_command.getL_firstParameter(),player,d_players);
//                                Bomb bomb = new Bomb(d_worldMap.findCountryNameById(Integer.parseInt(sourceCountryID)),player,d_playerOwnedCountriesMap);
                                logger.severe(player.getD_playerName() + " is using Bomb card!");
                                diplomacy.execute();
                            }
//                            else if(player.getD_PlayerCards().get(0).getCardType().equals(CardType.DIPLOMACY)) {
//                                //TODO: Add Negotiate/Diplomacy functionality
//                                Diplomacy diplomacy = new Diplomacy(l_command.getL_firstParameter());
//                                logger.severe(player.getD_playerName() + " is using Diplomacy card!");
//                                diplomacy.execute();
//                            }
                        }
                    }
                    System.out.println(player.getD_playerName().toUpperCase() + ": Please enter Advance order or type 'exit' to quit");
                    l_commandEntered = l_reader.readLine();
                } catch (IOException l_ioException) {
                    l_ioException.printStackTrace();
                }
                Middleware l_command = new Middleware(l_commandEntered);
                if (l_command.validateCommand() && !l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    String countryNameFrom = l_command.getL_firstParameter();
                    String countryNameTo = l_command.getL_secondParameter();
                    int numOfArmiesToDeploy = Integer.parseInt(l_command.getL_thirdParameter());
                    defaultNumberOfArmies = defaultNumberOfArmies - numOfArmiesToDeploy;
                    player.getD_orderList().add(new Advance(countryNameFrom, countryNameTo, numOfArmiesToDeploy,player,d_worldMap,d_playerOwnedCountriesMap));
                    logger.severe("Current command is: Advance " + numOfArmiesToDeploy + " armies to country ID " + countryNameTo + " from player " + player.getD_playerName() + "'s country ID " + countryNameFrom);
                } else if (l_command.getL_rootCommand().equals(ApplicationConstants.EXIT)) {
                    logger.severe("Exit current phase...");
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

    private Country findStrongestCountry(List<Country> countries) {
        // Find the country with the maximum number of armies
        return Collections.max(countries, Comparator.comparing(Country::getD_Armies));
    }
}
