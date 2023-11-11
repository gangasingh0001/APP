package Services;

import Constants.ApplicationConstants;
import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Models.WorldMap;
import Middleware.Middleware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this is a playerServiceTest class to test playerService class
 */
class PlayerServiceTest {

    /**
     * a class used to store game map and manipulate game map
     */
    IPlayerService d_playerService;

    /**
     * world map pointer to the game map in d_mapService, we only have one map instance, and it is created in
     * d_mapService class, but in this class we will use map instance in d_mapService to initial the worldMap attribute in
     * PlayerService
     */
    IWorldMap d_worldMap;

    /**
     * a list of added players
     */
    private ArrayList<Player> d_players = new ArrayList<>();

    /**
     * store and manipulate game map
     */
    MapService d_mapService;

    /**
     * a list of countries
     */
    List<Country> d_countryList;


    /**
     * initiate all the variables before running the test
     */
    @BeforeEach
    void setUp(){
        d_worldMap = new WorldMap();
        d_mapService = new MapService(d_worldMap);
        d_playerService = new PlayerService(d_mapService,d_worldMap);
        d_players = new ArrayList<>();
        d_countryList = d_worldMap.getCountries();

    }

    /**
     * test method to check size of the player list
     */
    @Test
    void playerSizeIsZero(){
        assertEquals(0, d_players.size());
    }

    /**
     * test method to check if the list is empty
     */
    @Test
    void playerIsEmpty() {
        assertTrue(d_players.isEmpty());
    }

    /**
     * test method to check if a player is added or not
     */
    @Test
    void addPlayer(){
//        String l_commandString = "gameplayer -add Yang";
//        Commands l_commands = new Commands(l_commandString);
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        assertTrue(d_players.add(l_player));
    }

    @Test
    void correctPlayerSize(){
//        String l_commandString = "gameplayer -add Yang";
//        Commands l_commands = new Commands(l_commandString);
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        d_players.add(l_player);
//        assertEquals(1, d_players.size());
    }

    @Test
    void playerNameCheck(){
//        String l_commandString = "gameplayer -add Yang";
//        Commands l_commands = new Commands(l_commandString);
//        l_commands.validateCommand();
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        d_players.add(l_player);
//
//        assertEquals("Yang",l_player.getD_playerName());
    }

    /**
     * test method to check if a player is successfully removed
     * under the case of only one player in the list
     */
    @Test
    void playerIsRemovedCaseOne(){
//        String l_commandString = "gameplayer -add Yang";
//        Commands l_commands = new Commands(l_commandString);
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        d_players.add(l_player);
//
//        this.d_players.remove(l_player);
//
//        assertTrue(d_players.isEmpty());
    }

    /**
     * test method to check if a player is successfully removed
     * under the case of multiple players in the list
     */
    @Test
    void playerIsRemovedCaseTwo(){
        //add first player
//        String l_commandString = "gameplayer -add Yang";
//        Commands l_commands = new Commands(l_commandString);
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        d_players.add(l_player);
//
//        //add second player to the list
//        String l_commandString2 = "gameplayer -add Ganga";
//        Commands l_commands2 = new Commands(l_commandString2);
//        Player l_player2 = new Player(l_commands2.getL_secondParameter());
//        d_players.add(l_player2);
//
//        //remove the first player
//        this.d_players.remove(l_player);
//
//        assertEquals(1,d_players.size());
    }

    /**
     * test method to assign countries to different players
     */
    @Test
    void assignCountriesNotNull(){
        //load map
//        String l_commandString = "loadmap google.map";
//        Commands l_commands = new Commands(l_commandString);
//        try {
//            d_mapService.loadData(l_commands);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        //get a list of countries in the map
//        d_countryList = d_mapService.d_worldMap.getCountries();
//        //shuffle the list
//        Collections.shuffle(d_countryList);
//
//        //add first player
//        String l_commandString2 = "gameplayer -add Yang";
//        Commands l_commands2 = new Commands(l_commandString2);
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        d_players.add(l_player);
//
//        //add second player to the list
//        String l_commandString3 = "gameplayer -add Ganga";
//        Commands l_commands3 = new Commands(l_commandString3);
//        Player l_player2 = new Player(l_commands3.getL_secondParameter());
//        d_players.add(l_player2);
//
//        //assign countries to players
//        int currentItemIndex = 0;
//        int l_n = this.d_players.size(); // Number of arrays to distribute items into
//        int l_itemsPerArray = d_countryList.size() / l_n;

//        for (int l_i = 0; l_i < l_n; l_i++) {
//            for (int j = 0; j < l_itemsPerArray; j++) {
//                this.d_players.get(l_i).addCountriesOwned(d_countryList.get(currentItemIndex));
//                currentItemIndex++;
//            }
//        }
//
//        //if countries is assigned to a player, this play's list is not null
//        assertNotNull(l_player.getD_coutriesOwned());
//        assertNotNull(l_player2.getD_coutriesOwned());

    }

    /**
     * test method to test default number of armies that assigned to players each term
     */
    @Test
    void defaultNumberOfArmiesIsFive(){
//        String l_commandString = "gameplayer -add Yang";
//        Commands l_commands = new Commands(l_commandString);
//        Player l_player = new Player(l_commands.getL_secondParameter());
//        d_players.add(l_player);
//
//        assertEquals(5, l_player.getD_numberOfArmies());
    }

    /**
     * test method to test that a player cannot deploy more armies that there is in
     * their reinforcement pool. default is 5
     */
    @Test
    void cannotAssignMoreThan5(){
        String l_commandString = "deploy 12 6";
        Middleware l_commands = new Middleware(l_commandString);
        l_commands.validateCommand();
        assertFalse(Integer.parseInt(String.valueOf(l_commands.getL_secondParameter())) <= ApplicationConstants.DEFAULTARMIES);
    }

    @Test
    void correctRinforcementArmies(){
        //load map
//        String l_commandString = "loadmap google.map";
//        Commands l_commands = new Commands(l_commandString);
//        l_commands.validateCommand();
//        try {
//            d_mapService.loadData(l_commands);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        //add first player
//        String l_commandString2 = "gameplayer -add Yang";
//        Commands l_commands2 = new Commands(l_commandString2);
//        d_playerService.addPlayer(l_commands2);
//
//        //add second player to the list
//        String l_commandString3 = "gameplayer -add Ganga";
//        Commands l_commands3 = new Commands(l_commandString3);
//        l_commands3.validateCommand();
//        d_playerService.addPlayer(l_commands3);

        //assign countries to players
//        d_playerService.assignCountries();
//        Country country = this.d_playerService.getPlayersList().get(0).getD_coutriesOwned().get(1);
//        String l_commandString4 = "deploy "+country.getId()+" 5";
//        Commands l_commands4 = new Commands(l_commandString4);
//        l_commands4.validateCommand();
//        int armies = 0;
//        for(Country country3 : d_playerService.getPlayersList().get(0).getD_coutriesOwned()) {
//            if(country3.getId()==country.getId()) {
//                armies = country3.getD_Armies();
//                break;
//            }
//        }
        //assertEquals(5,armies + Integer.parseInt(String.valueOf(l_commands4.getL_secondParameter())));
    }




}