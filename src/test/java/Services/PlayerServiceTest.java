package Services;

import Models.IWorldMap;
import Models.Player;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
     * constructor for plauerService test
     */
    public PlayerServiceTest(IPlayerService p_playerService, IWorldMap p_worldMap, ArrayList<Player> p_players) {
        this.d_playerService = p_playerService;
        this.d_worldMap = p_worldMap;
        this.d_players = p_players;
    }

    @Before
    void setUp(){
        d_players.add(new Player("Yang"));

    }


    @Test
    void addPlayer() {
        assertEquals(1,d_players.size());
    }

    @Test
    void assignCountries() {
    }
}