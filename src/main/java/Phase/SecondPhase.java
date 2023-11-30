package Phase;

import Command.*;
import Constants.ApplicationConstants;
import Services.*;
import Middleware.Middleware;
import Enum.GameMode;

public class SecondPhase extends GamePhase{
    private final CommandProcessor commandProcessor;
    private final MapService mapService;
    private final PlayerService d_playerService;
    private OutputService outputService;
    private InputService inputService;
    private int playerCount;
    public SecondPhase(MapService mapService, PlayerService p_playerService) {
        this.d_playerService = p_playerService;
        this.mapService = mapService;
        this.commandProcessor = new CommandProcessor(mapService,p_playerService);
        this.outputService = new ConsoleOutputService();
        this.inputService = new ConsoleInputService();
    }
    @Override
    public void processCommand(Middleware p_command) {
        // Implementation for Second phase command processing
        switch (p_command.getL_rootCommand()) {
            //read different commands then perform regarding methods
            case ApplicationConstants.SHOWMAP: {
                mapService.showMap();
                break;
            }
            case ApplicationConstants.VALIDATEMAP:
                commandProcessor.processCommand(new ValidateMapCommand(mapService));
                break;
            case ApplicationConstants.GAMEPLAYER: {
                if(playerCount<this.noOfPlayers){
                    commandProcessor.processCommand(new AddPlayerCommand(d_playerService,p_command));
                    playerCount++;
                }else{
                    outputService.print("no more players can be added");
                }
                break;
            }
            case ApplicationConstants.ASSIGNCOUNTRIES: {
                if(playerCount==this.noOfPlayers) {
                    commandProcessor.processCommand(new AssignCountryCommand(d_playerService, p_command));
                }else{
                    outputService.print("no of players not correct");
                }
                break;
            }
            case ApplicationConstants.EXIT: {
                if(playerCount == this.noOfPlayers){
                    notifyPhaseComplete();
                }else{
                    outputService.print("no of players does not match the player added");
                }
                break;
            }
            default: {
                System.out.println("\nInvalid Command for Phase 2.");
                break;
            }
        }
    }

    public void init(){
        outputService.print("Choose game mode (single or tournament)");
        String mode = inputService.readLine();
        if (mode.equals("single")){
            this.gameMode = GameMode.SINGLE_PLAYER;
        }else{
            this.gameMode = GameMode.TOURNAMENT;
        }
        switch (this.gameMode) {
            case TOURNAMENT:
                // Start tournament mode logic
                System.out.println("Tournament mode started");
                break;
            case SINGLE_PLAYER:
                // Start single-player mode logic
                outputService.print("Enter number of players");
                this.noOfPlayers = inputService.readInt();
                outputService.print("Single player mode started");
                break;
            default:
                System.out.println("Invalid game mode");
        }
    }
}
