package Services;

import Models.Continent;
import Models.IWorldMap;

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
     * @param continentName
     */
    @Override
    public void addContinent(String continentName, int continentValue, String continentColor) {
        Continent continent = new Continent(d_worldMap.getContinents().size() + 1, continentName, continentValue, continentColor);
        d_worldMap.addContinent(continent);
    }

    /**
     * @param continentName the name of continent need to be removed
     * @return
     */
    @Override
    public boolean isContinentRemoved(String continentName) {
        Continent continentToRemoveObj = null;
        for(Continent continent: d_worldMap.getContinents()) {
            if(continent.getName().equals(continentName)) {
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
}

