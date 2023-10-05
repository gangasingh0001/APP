package Controllers;

import Models.IWorldMap;
import Services.IContinentService;
import Services.ICountryService;
import Services.IMapService;
import Services.IPlayerService;
import Views.ShowMap;
import Views.ShowPlayerInfo;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineControllerTest {

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

    ICountryService d_countryService;
    IContinentService d_continentService;

    @Test
    void mapLoader() {
    }

    @Test
    void addRemovePlayer() {
    }

    @Test
    void assignCountries() {
    }
}