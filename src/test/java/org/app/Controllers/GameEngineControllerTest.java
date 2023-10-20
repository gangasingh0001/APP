package org.app.Controllers;

import org.app.Models.IWorldMap;
import org.app.Services.IContinentService;
import org.app.Services.ICountryService;
import org.app.Services.IMapService;
import org.app.Services.IPlayerService;
import org.app.Views.ShowMap;
import org.app.Views.ShowPlayerInfo;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

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