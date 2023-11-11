package Services;


import Models.Country;
import Middleware.Middleware;
import java.util.List;

public interface ICountryService {
    /**
     * get all the country in the map
     * @return all the country in the map
     */
    public List<Country> getCountryList();

    /**
     *
     * @param p_commands the name of target country needed to be added
     * @return true if the continent of target country is exist,else return false
     */
    public boolean addCountry(Middleware p_commands);

    /**
     * check if the country exist and remove that country
     * @param p_commands the name of target country
     * @return true if the target country exist; else return false
     */
    public boolean isCountryRemoved(Middleware p_commands);
    public boolean removeNeighbouringCountry(Middleware p_commands);
    public boolean addNeighbouringCountry(Middleware p_commands);
}
