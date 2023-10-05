package Services;

import Models.Continent;
import Models.IWorldMap;
import Utils.Commands;
import java.util.List;

/**
 * a class used to manipulate continent
 */
public class ContinentService implements IContinentService{
    /**
     * used to manipulate map
     */
    IMapService d_mapService;
    /**
     * used to store map
     */
    IWorldMap d_worldMap;

    /**
     * used to initial ContinentService
     * @param _mapService Used to manipulate map
     * @param _worldMap Used to store map
     */
    public ContinentService(IMapService _mapService, IWorldMap _worldMap) {
        d_mapService = _mapService;
        d_worldMap = _worldMap;
    }

    /**
     * get all the continents in the map
     * @return all the continents
     */
    public List<Continent> getContinentList(){
        return d_worldMap.getContinents();
    }

    /**
     * remove continent from map
     * @param p_commands the name of continent need to be removed
     * @return true if the target continent is exist and remove target continent; else return false
     */
    public boolean isContinentRemoved(Commands p_commands){
        Continent continentToRemoveObj = null;
        for(Continent continent: d_worldMap.getContinents()) {
            if(continent.getName().equals(p_commands.getL_secondParameter())) {
                continentToRemoveObj = continent;
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

    /**
     * add new continent to the map
     * @param p_continent the name of new continent
     */
    public void addContinent(Commands p_continent){
        Continent continent = new Continent(d_worldMap.getContinents().size() + 1, p_continent.getL_secondParameter(), Integer.parseInt(p_continent.getL_thirdParameter()), "");
        d_worldMap.addContinent(continent);
    }
}
