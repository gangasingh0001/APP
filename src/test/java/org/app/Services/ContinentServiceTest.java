package org.app.Services;

import org.app.Models.Continent;
import org.app.Models.IWorldMap;
import org.app.Models.WorldMap;
import org.app.Utils.Commands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContinentServiceTest
{
    IMapService d_mapService;
    IWorldMap d_worldMap;
    IContinentService d_continentService;
    Commands d_commands;

    @BeforeEach
    public void setup()
    {
        String command="loadmap google.map";
        d_commands=new Commands(command);
        d_worldMap = new WorldMap();
        d_mapService = new MapService(d_worldMap);
        try
        {
            d_mapService.loadData(d_commands);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        d_continentService=new ContinentService(d_mapService,d_worldMap);

    }
    @Test
    void getContinentList()
    {
        List<Continent>  l_continents = new ArrayList<>();
        String l_storeAllContinent="Western_States 4 red\n" +
                "    Central_States 6 yellow\n" +
                "    Skandinavia 5 green\n" +
                "    Eastern_States 5 blue\n" +
                "    Balkan 5 black";
        String []l_ArrStoreAllContinent=l_storeAllContinent.split("\n");
        for(int i=0; i<l_ArrStoreAllContinent.length;i++)
        {
            String l_test=l_ArrStoreAllContinent[i].trim();
            String [] l_arrForOneContinent=l_test.split(" ");
            l_continents.add(new Continent(i+1,l_arrForOneContinent[0],Integer.parseInt(String.valueOf(l_arrForOneContinent[1])),l_arrForOneContinent[2]));
        }
        assertEquals(l_continents.toString(),d_continentService.getContinentList().toString());

    }

    @Test
    void isContinentRemoved()
    {
        Commands l_testCommand_1=new Commands("editcontinent -remove UA");
        l_testCommand_1.validateCommand();
        Commands l_testCommand_2=new Commands("editcontinent -remove Western_States");
        l_testCommand_2.validateCommand();

        assertEquals(false,d_continentService.isContinentRemoved(l_testCommand_1));
        assertEquals(true,d_continentService.isContinentRemoved(l_testCommand_2));
        assertEquals(4,d_continentService.getContinentList().size());

    }

    @Test
    void addContinent()
    {
        Commands l_testCommand_1=new Commands("editcontinent -add UA 6");
        l_testCommand_1.validateCommand();
        d_continentService.addContinent(l_testCommand_1);
        assertEquals(6,d_continentService.getContinentList().size());
        Commands l_testCommand_2=new Commands("editcontinent -add Cs 6");
        l_testCommand_2.validateCommand();
        d_continentService.addContinent(l_testCommand_2);
        assertEquals(7,d_continentService.getContinentList().size());
    }
}