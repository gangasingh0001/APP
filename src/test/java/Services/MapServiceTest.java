package Services;

import Models.IWorldMap;
import Models.WorldMap;
import Utils.Commands;
import org.junit.jupiter.api.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MapServiceTest
{
    IWorldMap d_worldMap;
    MapService d_mapService;


    @BeforeEach
    public void setup()
    {
        d_worldMap = new WorldMap();
        d_mapService = new MapService(d_worldMap);
    }

    @org.junit.jupiter.api.Test
    @Order(1)
    void loadData()
    {
        String commandString = "loadmap hello.map";
        Commands commands = new Commands(commandString);
        assertThrows(FileNotFoundException.class, () ->
        {
            d_mapService.loadData(commands);
        });
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void validateGraph() throws FileNotFoundException
    {
        String commandString = "loadmap google.map";
        Commands commands = new Commands(commandString);
        try
        {
            d_mapService.loadData(commands);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        assertEquals(5, d_mapService.d_worldMap.getContinents().size());
        assertTrue(d_mapService.validateGraph());

        commandString = "loadmap disconnected.map";
        Commands l_commandsDisconnected = new Commands(commandString);
        IWorldMap l_disconnectedWorldMap = new WorldMap();
        IMapService l_disconnectedMapService = new MapService(l_disconnectedWorldMap);

        try
        {
            l_disconnectedMapService.loadData(l_commandsDisconnected);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        assertEquals(false, l_disconnectedMapService.validateGraph());
    }
}

