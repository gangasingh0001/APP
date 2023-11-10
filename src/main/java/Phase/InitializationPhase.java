package Phase;

import Services.ContinentService;
import Services.CountryService;
import Services.MapService;
import Utils.Commands;

public class InitializationPhase extends GamePhase implements PhaseObserver{

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
    public void processCommand(Commands p_commands) {

    }
}