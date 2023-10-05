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
public
class MapServiceTest {
    IWorldMap  worldMap;
    MapService mapService;


    @BeforeEach
    void setup() {
        worldMap = new WorldMap();
        mapService = new MapService(worldMap);
    }

    @org.junit.jupiter.api.Test
    @Order(1)
    void loadData() {
        String commandString = "loadmap hello.map";
        Commands commands = new Commands(commandString);
        assertThrows(FileNotFoundException.class,()->{
            mapService.loadData(commands);
        });
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void validateGraph() throws FileNotFoundException {
        String commandString = "loadmap google.map";
        Commands commands = new Commands(commandString);
        try {
            mapService.loadData(commands);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(5,mapService.worldMap.getContinents().size());
        assertTrue(mapService.validateGraph());
    }
}