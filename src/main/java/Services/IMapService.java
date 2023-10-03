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
    public boolean validateGraph();
}
