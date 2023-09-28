import Controllers.GameEngineController;
import Models.IWorldMap;
import Models.WorldMap;
import Services.IMapService;
import Services.IPlayerService;
import Services.MapService;
import Services.PlayerService;

public class Startup {
    public static void main (String[] args) {
        IWorldMap worldMap = new WorldMap();
        IMapService mapService = new MapService(worldMap);
        IPlayerService playerService = new PlayerService(mapService);
        GameEngineController l_game = new GameEngineController(mapService,playerService,worldMap);
        l_game.initGame();
    }
}
