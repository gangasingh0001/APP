package Command;

import Services.PlayerService;
import Middleware.Middleware;

public class AssignCountryCommand implements Command{
    private final Middleware d_command;
    private final PlayerService d_playerService;

    public AssignCountryCommand(PlayerService playerService, Middleware command) {
        this.d_playerService = playerService;
        this.d_command = command;
    }

    @Override
    public void execute() {
        d_playerService.assignCountries();
    }
}
