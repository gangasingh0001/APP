package org.app.Services;

import java.util.List;


import org.app.Models.Continent;
import org.app.Utils.Commands;

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
    public void addContinent(Commands p_continent);

    /**
     * first check if the continent need to be removed is exist,if it is exist the removed
     * this continent and the country inside the current continent and return true,
     * else return false
     * @param p_commands the name of continent need to be removed
     * @return if removed successful return true else return false
     */
    public boolean isContinentRemoved(Commands p_commands);
}
