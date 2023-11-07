package Services;

import java.util.ArrayList;
import java.util.List;
import Models.Continent;
import Models.Country;
import Models.IWorldMap;
import Utils.Commands;

/**
 * a class used to manipulate countries in game map
 */
public class CountryService implements ICountryService{
    /**
     * used to manipulate map
     */
    IMapService mapService;
    /**
     * used to store map
     */
    IWorldMap worldMap;

    /**
     * used to initial CountryService
     * @param _mapService used to manipulate map
     * @param _worldMap used to store map
     */
    public CountryService(IMapService _mapService, IWorldMap _worldMap) {
        mapService = _mapService;
        worldMap = _worldMap;
    }

    /**
     * get all the countries in the map
     * @return all the countries in the map
     */
    public List<Country> getCountryList(){
        return worldMap.getCountries();
    }

    /**
     * check if the continent of that country is exist, if exist then add that country
     *
     * @param p_commands the name of country needed to added
     * @return true  if the continent of that country exist then add that country, else return false
     */
    public boolean addCountry(Commands p_commands){
        Country country = new Country(worldMap.getCountries().size() + 1, p_commands.getL_secondParameter(), Integer.parseInt(p_commands.getL_thirdParameter()));
        boolean isValidContinent = false;
        for(Continent continent: worldMap.getContinents()) {
            if(continent.getId()==country.getContinentId()){
                isValidContinent = true;
            }
        }
        if(isValidContinent) {
            worldMap.addCountry(country);
        }
        return isValidContinent;
    }
    /**
     * check if that country is exist, if exist then remove
     *
     * @param p_commands the name of country needed to remove
     * @return true  if that country exist and remove that country, else return false
     */
    public boolean isCountryRemoved(Commands p_commands){
        Country countryToRemoveObj = null;
        for(Country country: worldMap.getCountries()) {
            if(country.getName().equals(p_commands.getL_secondParameter())) {
                countryToRemoveObj = country;
                break;
            }
        }
        if(countryToRemoveObj!=null) {
            worldMap.removeCountry(countryToRemoveObj);
            return true;
        }
        return false;
    }

    /**
     * remove all the country connected with current country
     * @param p_countryId the name of country needed to be removed
     */
    public boolean removeNeighbouringCountry(Commands p_commands){
        Country countryFromRemoveObj = null;
        List <Country> countryToRemoveObj = new ArrayList<>();
        for(Country country: worldMap.getCountries()) {
            if(country.getId()==Integer.parseInt(p_commands.getL_secondParameter())) {
                countryFromRemoveObj = country;
                break;
            }
        }
        for(Country country: worldMap.getCountries()) {
            if(country.getId()==Integer.parseInt(p_commands.getL_thirdParameter())) {
                countryToRemoveObj.add(country);
                break;
            }
        }
        if(countryToRemoveObj!=null && countryFromRemoveObj!=null) {
            worldMap.removeBorder(countryFromRemoveObj, countryToRemoveObj);;
            return true;
        }
        return false;
    }

    public boolean addNeighbouringCountry(Commands p_commands){
        Country countryFromRemoveObj = null;
        List <Country> countryToRemoveObj = new ArrayList<>();
        for(Country country: worldMap.getCountries()) {
            if(country.getId()==Integer.parseInt(p_commands.getL_secondParameter())) {
                countryFromRemoveObj = country;
                countryToRemoveObj = worldMap.getNeighborsOfCountry(countryFromRemoveObj);
                break;
            }
        }
        for(Country country: worldMap.getCountries()) {
            if(country.getId()==Integer.parseInt(p_commands.getL_thirdParameter())) {
                countryToRemoveObj.add(country);
                break;
            }
        }
        if(countryToRemoveObj!=null && countryFromRemoveObj!=null) {
            worldMap.addBorder(countryFromRemoveObj, countryToRemoveObj);;
            return true;
        }
        return false;
    }
}
