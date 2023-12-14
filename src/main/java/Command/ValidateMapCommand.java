package Command;

import Services.MapService;

public class ValidateMapCommand implements Command {
    private final MapService mapService;

    public ValidateMapCommand(MapService mapService) {
        this.mapService = mapService;
    }

    @Override
    public void execute() {
        mapService.validateGraph();
    }
}
