package Services;

import Utils.Commands;

/**
 * interface of MapService
 */
public interface IMapService {
    /**
     * load map
     * @param commands the name of command and the name of map
     */
    public void loadData(Commands commands);

    /**
     * check if all the countries are connected
     * @return true if all the countries are connected
     */
    public boolean validateGraph();
}
