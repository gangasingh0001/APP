package Command;

import Services.ContinentService;
import Services.CountryService;
import Services.MapService;
import Services.PlayerService;

public class CommandProcessor {
    private MapService mapService;
    private ContinentService continentService;
    private CountryService countryService;
    private PlayerService playerService;

    public CommandProcessor(MapService mapService, ContinentService continentService, CountryService countryService) {
        this.mapService = mapService;
        this.continentService = continentService;
        this.countryService = countryService;
    }

    public CommandProcessor(MapService mapService, PlayerService playerService) {
        this.mapService = mapService;
        this.playerService = playerService;
    }

    public void processCommand(Command command) {
        command.execute();
    }
}