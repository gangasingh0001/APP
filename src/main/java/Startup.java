import Constants.ApplicationConstants;
import Core.Game;
import Logger.ILogging;
import Logger.Logging;
import Models.IWorldMap;
import Models.WorldMap;
import Services.IMapService;
import Services.IPlayerService;
import Services.MapService;
import Services.PlayerService;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import Controllers.GameController;
/**
 *This is the Startup(main) class to start the game
 */
public class Startup {
    /**
     * main class to start the game
     * @param args arguments
     */
    public static void main (String[] args) throws FileNotFoundException {
        ILogging logging;
        Logger logger = Logger.getLogger(ApplicationConstants.LOG);
        logging = new Logging(ApplicationConstants.LOG);
        logger = logging.attachFileHandlerToLogger(logger);
        // define and initiate a map instance to store map information
        IWorldMap l_worldMap = new WorldMap();
        // define and initiate commands and business logics to ingrate commands with the map
        IMapService l_mapService = new MapService(l_worldMap);
        // define and initiate a PlayerService instance to ingrate player's input commands from the console
        IPlayerService playerService = new PlayerService(logger,l_mapService,l_worldMap);
        // define and initiate a controller which receives commands and redirect them to their respective services
        GameController l_game = new GameController(new Game(playerService.getPlayersList(), l_worldMap));
        //to start the game
        l_game.start();
    }
}
