package Phase;

import Command.*;
import Constants.ApplicationConstants;
import Core.Game;
import Services.ContinentService;
import Services.CountryService;
import Services.MapService;
import Utils.Commands;

import java.io.File;

public class FirstPhase extends GamePhase {
    private final CommandProcessor commandProcessor;
    private final MapService mapService;
    private final ContinentService d_continentService;
    private final CountryService d_countryService;
    private final Game d_game;
    public FirstPhase(Game p_game, MapService mapService, ContinentService dContinentService, CountryService countryService) {
        this.d_game = p_game;
        this.commandProcessor = new CommandProcessor(mapService, dContinentService, countryService);;
        this.mapService = mapService;
        d_continentService = dContinentService;
        this.d_countryService = countryService;
    }
    @Override
    public void processCommand(Commands p_command) {
        // Implementation for first phase command processing
        switch (p_command.getL_rootCommand()) {
            //read different commands then perform regarding methods
            case ApplicationConstants.EDITMAP:
                String fileName = p_command.getL_firstParameter();
                commandProcessor.processCommand(new EditMapCommand(mapService,fileName));
                break;
            case ApplicationConstants.EDITCONTINENT:
                commandProcessor.processCommand(new EditContinentCommand(d_continentService,p_command));
                break;
            case ApplicationConstants.SAVEMAP:
                commandProcessor.processCommand(new SaveMapCommand(mapService, p_command));
                break;
            case ApplicationConstants.LOADMAP:
                commandProcessor.processCommand(new LoadMapCommand(mapService, p_command.getL_parameters()));
                break;
            case ApplicationConstants.VALIDATEMAP:
                commandProcessor.processCommand(new ValidateMapCommand(mapService));
                break;
            case ApplicationConstants.EDITCOUNTRY:
                commandProcessor.processCommand(new EditCountryCommand(d_countryService, p_command));
                break;
            case ApplicationConstants.EDITNEIGHBOR:
                commandProcessor.processCommand(new EditNeighbourCommand(d_countryService, p_command));
                break;

            case ApplicationConstants.SHOWMAP: {
                mapService.showMap(); //TODO:
                break;
            }
            case ApplicationConstants.EXIT: {
                d_game.secondPhase();
                break;
            }
            default: {
                System.out.println("\nInvalid Command for Phase 1.");
                break;
            }
        }
    }
}
