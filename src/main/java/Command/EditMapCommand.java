package Command;

import Services.MapService;

public class EditMapCommand implements Command {
    private final MapService mapService;
    private final String fileName;

    public EditMapCommand(MapService mapService, String fileName) {
        this.mapService = mapService;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        mapService.mapEditor(fileName);
    }
}
