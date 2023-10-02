package Services;

import java.util.ArrayList;

import Models.Country;
import Utils.Commands;

public interface ICountryService {
    public ArrayList<Country> getCountryList();
    public void addCountry(Commands p_commands);
    public boolean isCountryRemoved(Commands p_commands);
    public void removeCountryNeighboursFromAll(Integer p_countryId);
}
