package Command;

import Constants.ApplicationConstants;
import Services.PlayerService;
import Utils.Commands;

public class AssignCountryCommand implements Command{
    private final Commands d_command;
    private final PlayerService d_playerService;

    public AssignCountryCommand(PlayerService playerService, Commands command) {
        this.d_playerService = playerService;
        this.d_command = command;
    }

    @Override
    public void execute() {
        d_playerService.assignCountries();
    }
}
