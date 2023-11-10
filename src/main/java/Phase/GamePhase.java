package Phase;

import Services.ContinentService;
import Services.CountryService;
import Services.MapService;
import Services.PlayerService;
import Utils.Commands;

import java.util.ArrayList;
import java.util.List;

public abstract class GamePhase {
    public abstract void processCommand(Commands p_command);

}

// Similar implementations for SecondPhase, ThirdPhase, etc.