package Command;

import Constants.ApplicationConstants;
import Services.ContinentService;
import Services.PlayerService;
import Utils.Commands;

public class AddPlayerCommand implements Command{
    private final Commands d_command;
    private final PlayerService d_playerService;

    public AddPlayerCommand(PlayerService playerService, Commands command) {
        this.d_playerService = playerService;
        this.d_command = command;
    }

    @Override
    public void execute() {
        if(d_command.getL_firstParameter().equals("-"+ApplicationConstants.ADD)) {
            d_playerService.addPlayer(d_command);
            //d_showPlayerInfo.displayPlayers(); TODO: Show player info
        } else if(d_command.getL_firstParameter().equals("-"+ApplicationConstants.REMOVE)) {
            if(d_playerService.isPlayerRemoved(d_command))
                System.out.println("Removed Successfully");
            //d_showPlayerInfo.displayPlayers(); TODO: Show player info
        }
    }
}
