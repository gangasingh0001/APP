package Command;

import Constants.ApplicationConstants;
import Services.CountryService;
import Middleware.Middleware;

public class EditCountryCommand implements Command {
    private final CountryService countryService;
    private final Middleware command;

    public EditCountryCommand(CountryService countryService, Middleware command) {
        this.countryService = countryService;
        this.command = command;
    }

    @Override
    public void execute() {
        if (command.getL_firstParameter().equals("-" + ApplicationConstants.ADD)) {
            if (countryService.addCountry(command)) {
                System.out.println("Added Successfully");
            } else {
                System.out.println("Invalid Input");
            }
        } else if (command.getL_firstParameter().equals("-" + ApplicationConstants.REMOVE)) {
            if (countryService.isCountryRemoved(command)) {
                System.out.println("Removed Successfully");
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}