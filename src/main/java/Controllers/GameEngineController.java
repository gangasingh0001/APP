package Controllers;



import Constants.ApplicationConstants;
import Models.IWorldMap;
import Services.ContinentService;
import Services.CountryService;
import Services.IContinentService;
import Services.ICountryService;
import Services.IMapService;
import Services.IPlayerService;
import Utils.Commands;
import Views.ShowMap;
import Views.ShowPlayerInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This is the controller class which contains console commands
 * and three phases during the game
 * first phase: game start up
 * second phase: add player, assign countries, map info
 * third phase: main game loop and map info
 */
public class GameEngineController {

    /**
     * define mapservice variable
     */
    IMapService d_mapService;

    /**
     * define worldMap variable
     */
    IWorldMap d_worldMap;

    /**
     *  define playerService variable
     */
    IPlayerService d_playerService;

    /**
     *  define mapview variable
     */
    ShowMap d_mapView;

    /**
     * define showplayerinfo variable
     */
    ShowPlayerInfo d_showPlayerInfo;

    /**
     * system input
     */
    Scanner d_scanner;

    /**
     * define countryService variable
     */
    ICountryService d_countryService;

    /**
     * define continentService variable
     */
    IContinentService d_continentService;

    /**
     * this is a GameEngineController constructor
     * @param p_mapService mapService object
     * @param p_playerService playerService object
     * @param p_worldMap worldMap object
     */
    public GameEngineController(IMapService p_mapService, IPlayerService p_playerService, IWorldMap p_worldMap) {
        d_mapService = p_mapService;
        d_playerService = p_playerService;
        d_worldMap = p_worldMap;
        d_mapView = new ShowMap(p_worldMap);
        d_showPlayerInfo = new ShowPlayerInfo(p_playerService);
        d_countryService = new CountryService(d_mapService,d_worldMap);
        d_continentService = new ContinentService(d_mapService,d_worldMap);
        new Scanner(System.in);
    }

    /**
     * initiate three game phases by order, type exit to leave current phase
     */
    public void initGame() throws FileNotFoundException {
        firstPhase();
        secondPhase();
        thirdPhase();
    }

    /**
     * first phase of the game, read commands from the console
     * @throws FileNotFoundException throws exception if file not found
     */
    public void firstPhase() throws FileNotFoundException {
        boolean exit = false;
        while (!exit) {
            //load map
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game phase 1 Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            String[] list = l_commandEntered.split("-");
            for(int i = 0; i<list.length; i++){
                String str;
                if(list.length>1)
                {
                    if(i>0)
                    {
                        str = list[0] + "-" + list[i];
                    }else{
                        continue;
                    }
                }else{
                    str = list[0];
                }
                Commands l_command = new Commands(str);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    //read different commands then perform regarding methods
                    case ApplicationConstants.EDITMAP: {
                        mapEditor(l_command);
                        break;
                    }
                    case ApplicationConstants.EDITCONTINENT: {
                        continentEditor(l_command);
                        break;
                    }
                    case ApplicationConstants.SAVEMAP: {
                        saveMap(l_command);
                        break;
                    }
                    case ApplicationConstants.LOADMAP: {
                        mapLoader(l_command);
                        break;
                    }
                    case ApplicationConstants.VALIDATEMAP: {
                        d_mapService.validateGraph();
                        break;
                    }
                    case ApplicationConstants.EDITCOUNTRY: {
                        countryEditor(l_command);
                        break;
                    }
                    case ApplicationConstants.EDITNEIGHBOR: {
                        neighborEditor(l_command);
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
                        System.out.println("\nInvalid Command for Phase 1.");
                        break;
                    }
                }
            }
            }
            
        }
    }

    /**
     * second phase of the game, read commands from the console
     */
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
            String[] list = l_commandEntered.split("-");
            for(int i = 0; i<list.length; i++){
                String str;
                if(list.length>1)
                {
                    if(i>0)
                    {
                        str = list[0] + "-" + list[i];
                    }else{
                        continue;
                    }
                }else{
                    str = list[0];
                }
                Commands l_command = new Commands(str);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    //read different commands then perform regarding methods
                    case ApplicationConstants.VALIDATEMAP: {
                        d_mapService.validateGraph();
                        break;
                    }
                    case ApplicationConstants.GAMEPLAYER: {
                        addRemovePlayer(l_command);
                        break;
                    }
                    case ApplicationConstants.ASSIGNCOUNTRIES: {
                        assignCountries();
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
                        System.out.println("\nInvalid Command for Phase 2.");
                        break;
                    }
                }
            }
        }
        }
    }

    /**
     * third phase of the game, read commands from the console
     */
    public void thirdPhase() {
        while (true) {
            //a phase to collect orders from all the players in round-robin fashion
            issue_order();
            //execute orders from issue_order phase
            next_order();

            BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
            String l_commandEntered = null;
            try {
                System.out.println("Enter Game phase 3 Commands / type 'exit' to quit");
                l_commandEntered = l_reader.readLine();
            } catch (IOException l_ioException) {
                l_ioException.printStackTrace();
            }
            String[] list = l_commandEntered.split("-");
            for(int i = 0; i<list.length; i++){
                String str;
                if(list.length>1)
                {
                    if(i>0)
                    {
                        str = list[0] + "-" + list[i];
                    }else{
                        continue;
                    }
                }else{
                    str = list[0];
                }
                Commands l_command = new Commands(str);
            if (l_command.validateCommand()) {
                switch (l_command.getL_rootCommand()) {
                    //read different commands then perform regarding methods
                    case ApplicationConstants.VALIDATEMAP: {
                        d_mapService.validateGraph();
                        break;
                    }

                    case ApplicationConstants.SHOWMAP: {
                        showMap();
                        break;
                    }

                    case ApplicationConstants.EXIT: {
                        break;
                    }

                    case ApplicationConstants.DEPLOY: {
                        break;
                    }

                    default: {
                        System.out.println("\nInvalid Command for Phase 3.");
                        break;
                    }
                }
            }
        }
        }
    }

    /**
     * read player input command to edit countries(add or remove) on a map
     * @param p_command command parameters from players
     */
    public void countryEditor(Commands p_command){
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            if(d_countryService.addCountry(p_command)) {
                System.out.println("Added Successfully");
            }else {
                System.out.println("Invalid Input");
            }
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(d_countryService.isCountryRemoved(p_command)) {
                System.out.println("Removed Successfully");
            }else {
                System.out.println("Invalid Input");
            }
        }
    }

    /**
     * read player input command to edit continents(add or remove) on a map
     * @param p_command command parameters from players
     */
    public void continentEditor(Commands p_command){
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            d_continentService.addContinent(p_command);
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(d_continentService.isContinentRemoved(p_command)) {
                System.out.println("Removed Successfully");
            }else{
                System.out.println("Invalid Continent");
            }
        }
    }

    /**
     * read player input command to edit neighbour countries(add or remove) on a map
     * @param p_command command parameters from players
     */
    public void neighborEditor(Commands p_command){
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            if(d_countryService.addCountry(p_command)) {
                System.out.println("Added Successfully");
            }else {
                System.out.println("Invalid Input");
            }
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(d_countryService.isCountryRemoved(p_command)) {
                System.out.println("Removed Successfully");
            }else {
                System.out.println("Invalid Input");
            }
        }
    }

    /**
     * read player input command to edit a map, if map not exist,
     * create a new one from scratch
     * @param p_command command parameters from players
     */
    public void mapEditor(Commands p_command){
        if(p_command.getL_firstParameter()!=null && !p_command.getL_firstParameter().isEmpty()){
            String filePath = "./src/main/java/Data/Maps/" + p_command.getL_firstParameter();
            File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }else{
            System.out.println("File name not found");
        }
    }

    /**
     * method to save the map
     * @param p_command command parameters from players
     */
    public void saveMap(Commands p_command){
        if(p_command.getL_firstParameter()!=null && !p_command.getL_firstParameter().isEmpty()){
            String filePath = "./src/main/java/Data/Maps/" + p_command.getL_firstParameter();
            File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File exists");
            d_mapService.saveMap(p_command);
        }else{
            System.out.println("File not found");
        }
        }else{
            System.out.println("File name not found");
        }
    }

    /**
     * load map
     * @param p_command read player's input command from the console
     */
    public void mapLoader(Commands p_command) throws FileNotFoundException {
        d_mapService.loadData(p_command);
    }

    /**
     * show loaded map to the console
     */
    public void showMap() {
        d_mapView.show();
    }

    /**
     * a method to add/remove players from the game
     * @param p_command read player's input command from the console
     */
    public void addRemovePlayer(Commands p_command) {
        if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            d_playerService.addPlayer(p_command);
            d_showPlayerInfo.displayPlayers();
        } else if(p_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(d_playerService.isPlayerRemoved(p_command)) System.out.println("Removed Successfully");
            d_showPlayerInfo.displayPlayers();
        }
    }

    /**
     * a method to assign countries to the player
     */
    public void assignCountries() {
        d_playerService.assignCountries();
        d_showPlayerInfo.displayPlayerInfo();
    }

    /**
     *  wait for orders from all the players to assign their armies
     *  in round-robin fashion
     */
    public void issue_order() {
        d_playerService.issue_order();
    }

    /**
     * execute orders from issue_order phase
     */
    public void next_order() {
        d_playerService.next_order();
        d_showPlayerInfo.displayPlayerInfo();
    }
}
