package Command;

import Services.MapService;
import Utils.Commands;

import java.io.File;

public class SaveMapCommand implements Command {
    private final MapService mapService;
    private final Commands command;

    public SaveMapCommand(MapService mapService, Commands command) {
        this.mapService = mapService;
        this.command = command;
    }

    @Override
    public void execute() {
        if (command.getL_firstParameter() != null && !command.getL_firstParameter().isEmpty()) {
            String filePath = "./src/main/java/Data/Maps/" + command.getL_firstParameter();
            File file = new File(filePath);
            if (file.exists()) {
                System.out.println("File exists");
                mapService.saveMap(command);
            } else {
                System.out.println("File not found");
            }
        } else {
            System.out.println("File name not found");
        }
    }
}
