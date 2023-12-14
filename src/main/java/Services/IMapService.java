package Services;

import Middleware.Middleware;

import java.io.FileNotFoundException;

/**
 * interface of MapService
 */
public interface IMapService {
    /**
     * load map
     * @param commands the name of command and the name of map
     */
    public void loadData(String[] params) throws FileNotFoundException;

    /**
     * save the map into txt file
     */

    public void saveMap(Middleware commands);


    /**
     * check if all the countries are connected
     * @return true if all the countries are connected
     */
    public boolean validateGraph();

    public void showMap();
}
