package Controllers;

import Constants.ApplicationConstants;
import Exceptions.InvalidCommand;
import Exceptions.InvalidMap;
import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Services.IMapService;
import Services.IPlayerService;
import Utils.Commands;
import Views.ShowMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

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
        firstPhase();
        secondPhase();
        thirdPhase();
    }

    public void firstPhase() {
        while (true) {
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            Commands l_command = new Commands(l_commandEntered);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    case ApplicationConstants.EDITMAP: {
                        break;
                    }
                    case ApplicationConstants.EDITCONTINENT: {
                        break;
                    }
                    case ApplicationConstants.SAVEMAP: {
                    }
                    case ApplicationConstants.LOADMAP: {
                        mapLoader(l_command);
                        break;
                    }
                    case ApplicationConstants.VALIDATEMAP: {
                    }
                    case ApplicationConstants.EDITCOUNTRY: {
                    }
                    case ApplicationConstants.EDITNEIGHBOR: {
                    }

                    case ApplicationConstants.SHOWMAP: {
                        showMap();
                        break;
                    }
                    case ApplicationConstants.EXIT: {
                    }
                    default: {
                        System.out.println("Invalid Command");
                        break;
                    }
                }
            }
        }
    }

    public void secondPhase() {
        while (true) {
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            Commands l_command = new Commands(l_commandEntered);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    case ApplicationConstants.VALIDATEMAP: {
                    }
                    case ApplicationConstants.GAMEPLAYER: {
                        addRemovePlayer(l_command);
                        break;
                    }
                    case ApplicationConstants.ASSIGNCOUNTRIES: {
                        assignCountries(l_command);
                        break;
                    }
                    case ApplicationConstants.SHOWMAP: {
                        showMap();
                        break;
                    }
                    case ApplicationConstants.EXIT: {
                    }
                    default: {
                        System.out.println("Invalid Command");
                        break;
                    }
                }
            }
        }
    }

    public void thirdPhase() {
        while (true) {
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            Commands l_command = new Commands(l_commandEntered);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    case ApplicationConstants.VALIDATEMAP: {
                    }

                    case ApplicationConstants.SHOWMAP: {
                        showMap();
                        break;
                    }
                    case ApplicationConstants.EXIT: {
                    }
                    case ApplicationConstants.ISSUEORDER: {
                        issue_order();
                        next_order();
                        break;
                    }
                    default: {
                        System.out.println("Invalid Command");
                        break;
                    }
                }
            }
        }
    }

    public void mapLoader(Commands p_command) {
        mapService.loadData(p_command);
    }

    public void showMap() {
        ShowMap mapView = new ShowMap(worldMap);
        mapView.show();
    }

    public void addRemovePlayer(Commands p_command) {
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            playerService.addPlayer(p_command);
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(playerService.isPlayerRemoved(p_command)) System.out.println("Removed Successfully");
        }
    }

    public void assignCountries(Commands p_command) {
        playerService.assignCountries(p_command);
    }

    public void issue_order() {
        playerService.issue_order();
    }
    public void next_order() {
        playerService.next_order();
    }
}
