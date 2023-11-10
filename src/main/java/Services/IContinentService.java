package Services;

import java.util.List;


import Models.Continent;
import Utils.Commands;

public interface IContinentService {
    /**
     *  get all the continents in the current continent
     * @return all the continents in the current continent
     */
    public List<Continent> getContinentList();

    /**
     * add new continent
     * @param p_continent the name of new continent
     */
    public void addContinent(String continentName, int continentValue, String color);

    /**
     * first check if the continent need to be removed is exist,if it is exist the removed
     * this continent and the country inside the current continent and return true,
     * else return false
     * @param continentName the name of continent need to be removed
     * @return if removed successful return true else return false
     */
    public boolean isContinentRemoved(String continentName);
}
