package Phase;

import Services.ContinentService;
import Services.CountryService;
import Services.MapService;
import Middleware.Middleware;

public class InitializationPhase extends GamePhase{

    private final MapService d_mapService;
    private final ContinentService d_continentService;
    private final CountryService d_countryService;
    public InitializationPhase(MapService mapService, ContinentService dContinentService, CountryService countryService) {
        this.d_mapService = mapService;
        this.d_continentService = dContinentService;
        this.d_countryService = countryService;
    }
    /**
     * @param p_commands
     */
    @Override
    public void processCommand(Middleware p_commands) {
        notifyPhaseComplete();
    }
}
