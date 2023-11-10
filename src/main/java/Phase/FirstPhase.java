package Phase;

import Constants.ApplicationConstants;
import Services.MapService;
import Utils.Commands;

public class FirstPhase extends GamePhase {
    private final MapService mapService;
    public FirstPhase(MapService mapService) {
        this.mapService = mapService;
    }
    @Override
    public void processCommand(Commands p_command) {
        // Implementation for first phase command processing
        switch (p_command.getL_rootCommand()) {
            //read different commands then perform regarding methods
            case ApplicationConstants.EDITMAP: {
                String fileName = p_command.getL_firstParameter();
                mapService.mapEditor(fileName);
                break;
            }
            case ApplicationConstants.EDITCONTINENT: {
                if(p_command.getL_firstParameter().equals("-"+ ApplicationConstants.ADD)) {
                    mapService.addContinent(p_command);
                } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
                    if(mapService.isContinentRemoved(p_command.getL_secondParameter())) {
                        System.out.println("Removed Successfully");
                    }else{
                        System.out.println("Invalid Continent");
                    }
                }
                break;
            }
            case ApplicationConstants.SAVEMAP: {
                logger.severe(l_command.getL_rootCommand());
                saveMap(l_command);
                break;
            }
            case ApplicationConstants.LOADMAP: {
                logger.severe(l_command.getL_rootCommand());
                mapLoader(l_command);
                break;
            }
            case ApplicationConstants.VALIDATEMAP: {
                logger.severe(l_command.getL_rootCommand());
                d_mapService.validateGraph();
                break;
            }
            case ApplicationConstants.EDITCOUNTRY: {
                logger.severe(l_command.getL_rootCommand());
                countryEditor(l_command);
                break;
            }
            case ApplicationConstants.EDITNEIGHBOR: {
                logger.severe(l_command.getL_rootCommand());
                neighborEditor(l_command);
                break;
            }

            case ApplicationConstants.SHOWMAP: {
                logger.severe("Showing map...");
                showMap();
                break;
            }
            case ApplicationConstants.EXIT: {
                logger.severe("Exit phase 1...");
                exit = true;
                break;
            }
            default: {
                logger.severe("Invalid Command for Phase 1.");
                System.out.println("\nInvalid Command for Phase 1.");
                break;
            }
        }
    }
}

