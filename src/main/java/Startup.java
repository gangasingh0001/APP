import Constants.ApplicationConstants;
import Controllers.GameEngineController;
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
        //define and initiate a map instance to store map information
        IWorldMap l_worldMap = new WorldMap();
        //define and initiate commands and business logics to ingrate commands with the map
        IMapService l_mapService = new MapService(l_worldMap);
        //define and initiate a PlayerService instance to ingrate player's input commands from the console
        IPlayerService playerService = new PlayerService(l_mapService,l_worldMap);
        //define and initiate a controller which receives commands and redirect them to their respective services
        GameEngineController l_game = new GameEngineController(l_mapService,playerService,l_worldMap);
        logging = new Logging(ApplicationConstants.LOG);
        logger = logging.attachFileHandlerToLogger(logger);
        //to stark the game
        l_game.initGame(logger);
    }
}
