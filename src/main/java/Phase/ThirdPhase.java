package Phase;

import Command.CommandProcessor;
import Command.ValidateMapCommand;
import Constants.ApplicationConstants;
import Factory.PlayerFactory;
import Models.Player;
import Services.InputService;
import Services.MapService;
import Services.OutputService;
import Services.PlayerService;
import Middleware.Middleware;

import java.util.List;

public class ThirdPhase extends GamePhase {
    private final CommandProcessor commandProcessor;
    private final MapService mapService;
    private final PlayerService d_playerService;
    private final InputService inputService;
    private final OutputService outputService;
    private List<Player> players;
    public ThirdPhase(MapService mapService, PlayerService p_playerService, InputService inputService, OutputService outputService, List<Player> players) {
        this.d_playerService = p_playerService;
        this.mapService = mapService;
        this.inputService = inputService;
        this.outputService = outputService;
        this.players = players;
        this.commandProcessor = new CommandProcessor(mapService,p_playerService);;
    }
    @Override
    public void processCommand(Middleware p_command) {
        // Implementation for Third phase command processing
        switch (p_command.getL_rootCommand()) {
            //read different commands then perform regarding methods
            case ApplicationConstants.VALIDATEMAP:
                commandProcessor.processCommand(new ValidateMapCommand(mapService));
                break;
            case ApplicationConstants.SHOWMAP: {
                mapService.showMap(); //TODO: show it through command and service
                break;
            }
            case ApplicationConstants.EXIT: {
                notifyPhaseComplete();
                break;
            }
//            case ApplicationConstants.SELECTION: {
//                //initializePlayers();
//                break;
//            }
            default: {
                System.out.println("\nInvalid Command for Phase 2.");
                break;
            }
        }
    }

    @Override
    public void init() {

    }

//    public void initializePlayers() {
//        outputService.print("Enter number of players:");
//        int numberOfPlayers = inputService.readInt();
//        for (int i = 0; i < numberOfPlayers; i++) {
//            System.out.println("Select strategy for player " + (i + 1) + " (1: Aggressive, 2: Benevolent, 3: Cheater, 4:Random, 5: Human):");
//            int choice = inputService.readInt();
//
//            switch (choice) {
//                case 1:
//                    players.add(new PlayerFactory().createPlayer(null,1)); //TODO: Craete random name
//                    break;
//                case 2:
//                    players.add(new PlayerFactory().createPlayer(null,2)); //TODO: Craete random name
//                    break;
//                case 3:
//                    players.add(new PlayerFactory().createPlayer(null,3)); //TODO: Craete random name
//                    break;
//                case 4:
//                    players.add(new PlayerFactory().createPlayer(null,4)); //TODO: Craete random name
//                    break;
//                case 5:
//                    outputService.print("Enter player name");
//                    players.add(new PlayerFactory().createPlayer(inputService.readLine(),5)); //TODO: Craete random name
//                    break;
//
//                // Add more cases for additional strategies
//                default:
//                    System.out.println("Invalid choice, defaulting to Aggressive.");
//                    players.add(new PlayerFactory().createPlayer(null,1)); //TODO: Craete random name
//                    break;
//            }
//        }
//    }
}
