package Services;


import Models.Country;
import Utils.Commands;
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
    public boolean addCountry(Commands p_commands);

    /**
     * check if the country exist and remove that country
     * @param p_commands the name of target country
     * @return true if the target country exist; else return false
     */
    public boolean isCountryRemoved(Commands p_commands);
    public boolean removeNeighbouringCountry(Commands p_commands);
    public boolean addNeighbouringCountry(Commands p_commands);
}
