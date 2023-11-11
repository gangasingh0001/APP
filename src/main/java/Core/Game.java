package Core;

import Constants.ApplicationConstants;
import Models.Player;
import Models.WorldMap;
import Phase.*;
import Services.*;

import java.util.List;

public class Game {
    private boolean gameOver;
    private List<Player> players;
    private WorldMap gameMap;
    private int currentPlayerIndex;
    private GamePhase currentPhase;
    private MapService mapService;
    private InputService inputService;
    private OutputService outputService;
    private Game game;

    // Constructor
    public Game(List<Player> players, WorldMap gameMap) {
        this.gameOver = false;
        this.players = players;
        this.gameMap = gameMap;
        this.currentPlayerIndex = 0;
        this.mapService = new MapService(gameMap);
        this.inputService = new ConsoleInputService();
        this.outputService = new ConsoleOutputService();
        this.currentPhase = new InitializationPhase(this.mapService, new ContinentService(mapService,gameMap), new CountryService(mapService,gameMap)); // or any initial phase
    }

    public void play() {
        while (!gameOver) {
            outputService.print(ApplicationConstants.ENTER_COMMAND);
            currentPhase.processCommand(inputService.readLine());
            //updatePhase();
            gameOver = checkEndConditions();
        }
        displayResults();
    }

    private boolean checkEndConditions() {
        // Check for game-over conditions
        return false; // Replace with actual game-over logic
    }

    private void displayResults() {
        // Display the end-of-game results
    }

    public void secondPhase() {
    }

    public void thirdPhase() {
    }

    public void endGame() {
    }
    // Additional methods such as adding players, setting up the map, etc.
    // Getters and Setters
}