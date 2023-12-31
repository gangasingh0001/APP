package Core;

import Constants.ApplicationConstants;
import Models.IWorldMap;
import Models.Player;
import Models.WorldMap;
import Enum.GameMode;
import Phase.*;
import Services.*;
import java.util.List;

public class Game implements PhaseObserver{
    private boolean gameOver;
    private List<Player> players;
    private IWorldMap gameMap;
    private int currentPlayerIndex;
    private GamePhase currentPhase;
    private MapService mapService;
    private InputService inputService;
    private OutputService outputService;
    private PlayerService playerService;

    // Constructor
    public Game(List<Player> players, IWorldMap gameMap) {
        this.gameOver = false;
        this.players = players;
        this.gameMap = gameMap;
        this.currentPlayerIndex = 0;
        this.mapService = new MapService(gameMap);
        this.inputService = new ConsoleInputService();
        this.outputService = new ConsoleOutputService();
        this.currentPhase = new InitializationPhase(this.mapService, new ContinentService(mapService,gameMap), new CountryService(mapService,gameMap)); // or any initial phase
        this.currentPhase.setObserver(this);
    }

    public void play() {
        while (!gameOver) {
            outputService.print(currentPhase.getClass().getName()+":");
            outputService.print(ApplicationConstants.ENTER_COMMAND);
            currentPhase.processCommand(inputService.readCommand());
            gameOver = checkEndConditions();
        }
        displayResults();
    }

    private boolean checkEndConditions() {
        if(players.size()==1) return true;
        return false;
    }

    private void displayResults() {
        //TODO: Display the end-of-game results
    }

    @Override
    public void onPhaseComplete() {
        if (currentPhase instanceof InitializationPhase) {
            currentPhase = new FirstPhase(mapService,new ContinentService(mapService,gameMap),new CountryService(mapService,gameMap));
        } else if (currentPhase instanceof FirstPhase) {
            playerService = new PlayerService(mapService,gameMap);
            currentPhase = new SecondPhase(mapService,playerService);
            currentPhase.init();
        } else if (currentPhase instanceof SecondPhase) {
            currentPhase = new GamePlayPhase(playerService);
            //currentPhase = new GamePlayPhase(mapService,playerService, inputService, outputService, players);
        }
        currentPhase.setObserver(this);
    }
}