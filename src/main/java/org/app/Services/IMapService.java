package org.app.Services;

import org.app.Utils.Commands;

import java.io.FileNotFoundException;

/**
 * interface of MapService
 */
public interface IMapService {
    /**
     * load map
     * @param commands the name of command and the name of map
     */
    public void loadData(Commands commands) throws FileNotFoundException;

    /**
     * save the map into txt file
     */

    public void saveMap(Commands commands);


    /**
     * check if all the countries are connected
     * @return true if all the countries are connected
     */
    public boolean validateGraph();

}
