package Services;

import Models.Continent;
import Models.IWorldMap;
import Utils.Commands;
import java.util.List;

public class ContinentService implements IContinentService{
    IMapService mapService;
    IWorldMap worldMap;
    public ContinentService(IMapService _mapService, IWorldMap _worldMap) {
        mapService = _mapService;
        worldMap = _worldMap;
    }

    public List<Continent> getContinentList(){
        return worldMap.getContinents();
    }
    public boolean isContinentRemoved(Commands p_commands){
        Continent continentToRemoveObj = null;
        for(Continent continent: worldMap.getContinents()) {
            if(continent.getName().equals(p_commands.getL_secondParameter())) {
                continentToRemoveObj = continent;
                break;
            }
        }
        if(continentToRemoveObj!=null) {
            worldMap.removeAllCountriesWithContinentID(continentToRemoveObj.getId());
            worldMap.removeContinent(continentToRemoveObj);
            return true;
        }
        return false;
    }
    public void addContinent(Commands p_continent){
        Continent continent = new Continent(worldMap.getContinents().size() + 1, p_continent.getL_secondParameter(), Integer.parseInt(p_continent.getL_thirdParameter()), "");
        worldMap.addContinent(continent);
    }
}
