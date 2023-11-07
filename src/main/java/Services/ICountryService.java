package Services;


import Models.Country;
import Utils.Commands;
import java.util.List;

public interface ICountryService {

    public List<Country> getCountryList();
    public boolean addCountry(Commands p_commands);
    public boolean isCountryRemoved(Commands p_commands);
    public boolean removeNeighbouringCountry(Commands p_commands);
    public boolean addNeighbouringCountry(Commands p_commands);
}
