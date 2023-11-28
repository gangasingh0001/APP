package Phase;

import Middleware.Middleware;

public abstract class GamePhase {
    private PhaseObserver observer;

    public void setObserver(PhaseObserver observer) {
        this.observer = observer;
    }
    protected void notifyPhaseComplete() {
        if(observer!=null) {
            observer.onPhaseComplete();
        }
    }
    public abstract void processCommand(Middleware p_command);

}

// Similar implementations for SecondPhase, ThirdPhase, etc.