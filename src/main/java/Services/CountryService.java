package Services;

import java.util.ArrayList;
import Models.Country;
import Utils.Commands;

public class CountryService implements ICountryService{
    private ArrayList<Country> countries = new ArrayList<>();

    public ArrayList<Country> getCountryList(){
        return countries;
    }
    public void addCountry(Commands p_commands){

    }
    public boolean isCountryRemoved(Commands p_commands){
            return true;
    }

    public void removeCountryNeighboursFromAll(Integer p_countryId){
    }
}
