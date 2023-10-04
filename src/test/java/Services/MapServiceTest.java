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

class MapServiceTest {
    IWorldMap  worldMap;
    MapService mapService;


    @BeforeEach
    public void setup() {
        worldMap = new WorldMap();
        mapService = new MapService(worldMap);
    }

    @Test
    @Order(1)
    void loadDataIfFileNotFound() {
        String commandString = "loadmap hello.map";
        Commands commands = new Commands(commandString);
        assertThrows(FileNotFoundException.class,()->{
            mapService.loadData(commands);
        });
    }

    @Test
    @Order(2)
    void validateDataAndGraph() throws FileNotFoundException {
        String commandString = "loadmap google.map";
        Commands commands = new Commands(commandString);
        try {
             mapService.loadData(commands);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        mapService.loadData(commands);
        assertEquals(5,mapService.d_worldMap.getContinents().size());
        assertTrue(mapService.validateGraph());
    }
}