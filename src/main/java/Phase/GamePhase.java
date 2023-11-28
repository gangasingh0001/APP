package Phase;

import Middleware.Middleware;
import Enum.GameMode;
public abstract class GamePhase {
    private PhaseObserver observer;
    protected GameMode gameMode;
    public void setObserver(PhaseObserver observer) {
        this.observer = observer;
    }
    protected void notifyPhaseComplete() {
        if(observer!=null) {
            observer.onPhaseComplete();
        }
    }
    public abstract void processCommand(Middleware p_command);

    public abstract void init();
}

// Similar implementations for SecondPhase, ThirdPhase, etc.