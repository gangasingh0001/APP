package Services;

import Models.Continent;
import Models.IWorldMap;
import Utils.Commands;
import java.util.List;

public class ContinentService implements IContinentService{
    IMapService d_mapService;
    IWorldMap d_worldMap;
    public ContinentService(IMapService _mapService, IWorldMap _worldMap) {
        d_mapService = _mapService;
        d_worldMap = _worldMap;
    }

    public List<Continent> getContinentList(){
        return d_worldMap.getContinents();
    }
    public boolean isContinentRemoved(Commands p_commands){
        Continent continentToRemoveObj = null;
        for(Continent continent: d_worldMap.getContinents()) {
            if(continent.getName().equals(p_commands.getL_secondParameter())) {
                continentToRemoveObj = continent;
                System.out.println(continentToRemoveObj.getId());
                break;
            }
        }
        if(continentToRemoveObj!=null) {
            d_worldMap.removeAllCountriesWithContinentID(continentToRemoveObj.getId());
            d_worldMap.removeContinent(continentToRemoveObj);
            return true;
        }
        return false;
    }
    public void addContinent(Commands p_continent){
        Continent continent = new Continent(d_worldMap.getContinents().size() + 1, p_continent.getL_secondParameter(), Integer.parseInt(p_continent.getL_thirdParameter()), "");
        d_worldMap.addContinent(continent);
    }
}
