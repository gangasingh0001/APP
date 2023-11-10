package Core;

import Models.Player;
import Models.WorldMap;
import Phase.GamePhase;
import Phase.FirstPhase;
import Phase.SecondPhase;
import Phase.ThirdPhase;
import Services.MapService;
import Utils.Commands;

import java.util.List;

public class Game {
    private boolean gameOver;
    private List<Player> players;
    private WorldMap gameMap;
    private int currentPlayerIndex;
    private GamePhase currentPhase;
    private MapService mapService;

    // Constructor
    public Game(List<Player> players, WorldMap gameMap) {
        this.gameOver = false;
        this.players = players;
        this.gameMap = gameMap;
        this.currentPlayerIndex = 0;
        this.mapService = new MapService(gameMap);
        this.currentPhase = new FirstPhase(this.mapService); // or any initial phase
    }

    public void processCommand(String command) {
        // Command processing logic
        // This can change the currentPhase based on certain commands or game state
    }

    public void play() {
        while (!gameOver) {
            currentPhase.processCommand(p_commands);
            updatePhase();
            gameOver = checkEndConditions();
        }
        displayResults();
    }

    public void initializeGame() {
        // Load map, initialize players, set strategies, etc.
    }

    private void updatePhase() {
        // Logic to update the current phase based on the game state
        // For example, move from planning phase to execution phase
    }

    private boolean checkEndConditions() {
        // Check for game-over conditions
        return false; // Replace with actual game-over logic
    }

    private void displayResults() {
        // Display the end-of-game results
    }

    // Additional methods such as adding players, setting up the map, etc.
    // Getters and Setters
}