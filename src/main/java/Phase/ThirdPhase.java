package Phase;

import Command.CommandProcessor;
import Command.ValidateMapCommand;
import Constants.ApplicationConstants;
import Core.Game;
import Services.MapService;
import Services.PlayerService;
import Utils.Commands;

public class ThirdPhase extends GamePhase {
    private final CommandProcessor commandProcessor;
    private final MapService mapService;
    private final PlayerService d_playerService;
    private final Game d_game;
    public ThirdPhase(Game p_game,MapService mapService, PlayerService p_playerService) {
        this.d_game = p_game;
        this.d_playerService = p_playerService;
        this.mapService = mapService;
        this.commandProcessor = new CommandProcessor(mapService,p_playerService);;
    }
    @Override
    public void processCommand(Commands p_command) {
        // Implementation for Third phase command processing
        switch (p_command.getL_rootCommand()) {
            //read different commands then perform regarding methods
            case ApplicationConstants.VALIDATEMAP:
                commandProcessor.processCommand(new ValidateMapCommand(mapService));
                break;
            case ApplicationConstants.SHOWMAP: {
                mapService.showMap(); //TODO:
                break;
            }
            case ApplicationConstants.EXIT: {
                d_game.endGame();
                break;
            }
            default: {
                System.out.println("\nInvalid Command for Phase 2.");
                break;
            }
        }
    }
}
