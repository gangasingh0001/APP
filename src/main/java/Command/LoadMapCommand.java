package Command;

import Services.MapService;

import java.util.List;

public class LoadMapCommand implements Command {
    private final MapService mapService;
    private final String[] parameters;

    public LoadMapCommand(MapService mapService, String[] parameters) {
        this.mapService = mapService;
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        mapService.loadData(parameters);
    }
}
