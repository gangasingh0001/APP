package Command;

import Constants.ApplicationConstants;
import Services.ContinentService;
import Middleware.Middleware;

public class EditContinentCommand implements Command {
    private final ContinentService continentService;
    private final Middleware command;

    public EditContinentCommand(ContinentService continentService, Middleware command) {
        this.continentService = continentService;
        this.command = command;
    }

    @Override
    public void execute() {
        if (command.getL_firstParameter().equals("-" + ApplicationConstants.ADD)) {
            continentService.addContinent(command.getL_secondParameter(),Integer.parseInt(command.getL_thirdParameter()),"");
        } else if (command.getL_firstParameter().equals("-" + ApplicationConstants.REMOVE)) {
            if (continentService.isContinentRemoved(command.getL_secondParameter())) {
                System.out.println("Removed Successfully");
            } else {
                System.out.println("Invalid Continent");
            }
        }
    }
}
