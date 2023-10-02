package Controllers;

import Constants.ApplicationConstants;
import Models.IWorldMap;
import Services.IContinentService;
import Services.ICountryService;
import Services.IMapService;
import Services.IPlayerService;
import Utils.Commands;
import Views.ShowMap;
import Views.ShowPlayerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameEngineController {
    IMapService mapService;
    IWorldMap worldMap;
    IPlayerService playerService;
    ICountryService continentService;
    ICountryService countryService;
    ShowMap mapView;
    ShowPlayerInfo showPlayerInfo;
    Scanner scanner;
    public GameEngineController(IMapService _mapService, IPlayerService _playerService, IWorldMap _worldMap) {
        mapService = _mapService;
        playerService = _playerService;
        worldMap = _worldMap;
        mapView = new ShowMap(worldMap);
        showPlayerInfo = new ShowPlayerInfo(playerService);
        new Scanner(System.in);
    }

    public void initGame() {
        firstPhase();
        secondPhase();
        thirdPhase();
    }

    public void firstPhase() {
        boolean exit = false;
        while (!exit) {
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game phase 1 Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            System.out.println("l_commandEntered" + l_commandEntered);
            Commands l_command = new Commands(l_commandEntered);
            System.out.println("l_command" + l_command);

            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    case ApplicationConstants.EDITMAP: {
                        mapEditor(l_command);
                        break;
                    }
                    case ApplicationConstants.EDITCONTINENT: {
                        continentEditor(l_command);
                        break;
                    }
                    case ApplicationConstants.SAVEMAP: {
                    }
                    case ApplicationConstants.LOADMAP: {
                        mapLoader(l_command);
                        break;
                    }
                    case ApplicationConstants.VALIDATEMAP: {
                        mapService.validateGraph();
                        break;
                    }
                    case ApplicationConstants.EDITCOUNTRY: {
                        countryEditor(l_command);
                    }
                    case ApplicationConstants.EDITNEIGHBOR: {
                    }

                    case ApplicationConstants.SHOWMAP: {
                        showMap();
                        break;
                    }
                    case ApplicationConstants.EXIT: {
                        exit = true;
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

    public void secondPhase() {
        boolean exit = false;
        while (!exit) {
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game phase 2 Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            Commands l_command = new Commands(l_commandEntered);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    case ApplicationConstants.VALIDATEMAP: {
                        mapService.validateGraph();
                        break;
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
                        exit = true;
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

    public void thirdPhase() {
        while (true) {

            issue_order();
            next_order();

            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game phase 3 Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            Commands l_command = new Commands(l_commandEntered);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    case ApplicationConstants.VALIDATEMAP: {
                        mapService.validateGraph();
                        break;
                    }

                    case ApplicationConstants.SHOWMAP: {
                        showMap();
                        break;
                    }

                    case ApplicationConstants.EXIT: {
                    }

                    case ApplicationConstants.DEPLOY: {

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


    public void continentEditor(Commands p_command){
        System.out.println("continentEditor");
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            continentService.addCountry(p_command);
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(continentService.isCountryRemoved(p_command)) System.out.println("Removed Successfully");
            // Remove country
        }
    }

    public void mapEditor(Commands p_command){
        System.out.println("MApEditor");
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            countryService.addCountry(p_command);
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(countryService.isCountryRemoved(p_command)) System.out.println("Removed Successfully");
            // Remove country
        }
    }

    public void mapLoader(Commands p_command) {
        mapService.loadData(p_command);
    }

    public void countryEditor(Commands p_command){
        System.out.println("continentEditor");
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            countryService.addCountry(p_command);
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(countryService.isCountryRemoved(p_command)) System.out.println("Removed Successfully");
            // Remove country
        }
    }

    public void showMap() {
        mapView.show();
    }

    public void addRemovePlayer(Commands p_command) {
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            playerService.addPlayer(p_command);
            showPlayerInfo.displayPlayers();
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(playerService.isPlayerRemoved(p_command)) System.out.println("Removed Successfully");
            showPlayerInfo.displayPlayers();
        }
    }

    public void assignCountries(Commands p_command) {
        playerService.assignCountries(p_command);
        showPlayerInfo.displayPlayerInfo();
    }

    public void issue_order() {
        playerService.issue_order();
    }
    public void next_order() {
        playerService.next_order();
        showPlayerInfo.displayPlayerInfo();
    }
}
