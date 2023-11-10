package Phase;

import Utils.Commands;

public abstract class GamePhase {
    public abstract void processCommand(Commands p_commands);
}

// Similar implementations for SecondPhase, ThirdPhase, etc.