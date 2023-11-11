package Phase;

import Command.*;
import Constants.ApplicationConstants;
import Services.MapService;
import Services.PlayerService;
import Middleware.Middleware;

public class SecondPhase extends GamePhase{
    private final CommandProcessor commandProcessor;
    private final MapService mapService;
    private final PlayerService d_playerService;
    public SecondPhase(MapService mapService, PlayerService p_playerService) {
        this.d_playerService = p_playerService;
        this.mapService = mapService;
        this.commandProcessor = new CommandProcessor(mapService,p_playerService);;
    }
    @Override
    public void processCommand(Middleware p_command) {
        // Implementation for Second phase command processing
        switch (p_command.getL_rootCommand()) {
            //read different commands then perform regarding methods
            case ApplicationConstants.VALIDATEMAP:
                commandProcessor.processCommand(new ValidateMapCommand(mapService));
                break;
            case ApplicationConstants.SHOWMAP: {
                mapService.showMap(); //TODO:
                break;
            }
            case ApplicationConstants.GAMEPLAYER: {
                commandProcessor.processCommand(new AddPlayerCommand(d_playerService,p_command));
                break;
            }
            case ApplicationConstants.ASSIGNCOUNTRIES: {
                commandProcessor.processCommand(new AssignCountryCommand(d_playerService,p_command));
                break;
            }
            case ApplicationConstants.EXIT: {
                notifyPhaseComplete();
                break;
            }
            default: {
                System.out.println("\nInvalid Command for Phase 2.");
                break;
            }
        }
    }
}
