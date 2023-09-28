package Controllers;

import Constants.ApplicationConstants;
import Exceptions.InvalidCommand;
import Exceptions.InvalidMap;
import Models.IWorldMap;
import Services.IMapService;
import Services.IPlayerService;
import Utils.Commands;
import Views.ShowMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameEngineController {
    IMapService mapService;
    IWorldMap worldMap;
    IPlayerService playerService;
    public GameEngineController(IMapService _mapService, IPlayerService _playerService, IWorldMap _worldMap) {
        mapService = _mapService;
        playerService = _playerService;
        worldMap = _worldMap;
    }

    public void initGame() {
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println("Enter Game Commands / type 'exit' to quit");
                String l_commandEntered = l_reader.readLine();

                handleCommand(l_commandEntered);
            }
            catch (InvalidCommand | InvalidMap l_exception) {
                System.out.println(l_exception.getMessage());
            }
            catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
        }
    }

    public void editMap(Commands l_command) {

    }

    public void handleCommand(String p_enteredCommand) throws InvalidMap, InvalidCommand, IOException {
        Commands l_command = new Commands(p_enteredCommand);
        if(l_command.validateCommand()) {
            switch (l_command.getL_rootCommand()) {
                case ApplicationConstants.EDITMAP: {
                    editMap(l_command);
                    break;
                }
                case ApplicationConstants.EDITCONTINENT: {
//                    if (!l_isMapLoaded) {
//                        System.out.println("Can not Edit Continent, please perform `editmap` first");
//                        break;
//                    }
//                    performEditContinent(l_command);
                    break;
                }
                case ApplicationConstants.SAVEMAP: {
//                    if (!l_isMapLoaded) {
//                        System.out.println("No map found to save, Please `editmap` first");
//                        break;
//                    }
//
//                    performSaveMap(l_command);
//                    break;
                }
                case ApplicationConstants.LOADMAP: {
                    mapLoader(l_command);
                    break;
                }
                case ApplicationConstants.VALIDATEMAP: {
//                    if (!l_isMapLoaded) {
//                        System.out.println("No map found to validate, Please `loadmap` & `editmap` first");
//                        break;
//                    }
//                    performValidateMap(l_command);
//                    break;
                }
                case ApplicationConstants.EDITCOUNTRY: {
//                    if (!l_isMapLoaded) {
//                        System.out.println("Can not Edit Country, please perform `editmap` first");
//                        break;
//                    }
//                    performEditCountry(l_command);
//                    break;
                }
                case ApplicationConstants.EDITNEIGHBOR: {
//                    if (!l_isMapLoaded) {
//                        System.out.println("Can not Edit Neighbors, please perform `editmap` first");
//                        break;
//                    }
//                    performEditNeighbour(l_command);
//                    break;
                }
                case ApplicationConstants.GAMEPLAYER: {
//                    if (!l_isMapLoaded) {
//                        System.out.println("No map found, Please `loadmap` before adding game players");
//                        break;
//                    }
//                    createPlayers(l_command);
//                    break;
                }
                case ApplicationConstants.ASSIGNCOUNTRIES: {
//                    assignCountries(l_command);
//                    break;
                }
                case ApplicationConstants.SHOWMAP: {
//                    MapView l_mapView = new MapView(d_gameState);
//                    l_mapView.showMap();
//                    break;
                }
                case ApplicationConstants.EXIT: {
//                    System.out.println("Exit Command Entered");
//                    System.exit(0);
//                    break;
                }
                default: {
                    System.out.println("Invalid Command");
                    break;
                }
            }
        }
    }

    public void mapLoader(Commands p_command) {
        mapService.loadData(p_command);
        ShowMap mapView = new ShowMap(worldMap);
        mapView.show();
    }
}
