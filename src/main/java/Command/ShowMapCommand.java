package Command;

import Constants.ApplicationConstants;
import Middleware.Middleware;
import Services.MapService;
import Services.PlayerService;

public class ShowMapCommand implements Command{
    private final MapService mapService;

    public ShowMapCommand(MapService mapService) {
        this.mapService = mapService;
    }

    @Override
    public void execute() {
        mapService.showMap();
    }
}
