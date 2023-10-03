package Services;

import Models.IWorldMap;
import Models.WorldMap;
import Utils.Commands;
import org.junit.jupiter.api.*;

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
    void validateDataAndGraph() {
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