package Services;

import java.util.List;
import Models.Continent;
import Models.Country;
import Models.IWorldMap;
import Utils.Commands;

public class CountryService implements ICountryService{
    IMapService mapService;
    IWorldMap worldMap;

    public CountryService(IMapService _mapService, IWorldMap _worldMap) {
        mapService = _mapService;
        worldMap = _worldMap;
    }
    public List<Country> getCountryList(){
        return worldMap.getCountries();
    }
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
    public boolean isCountryRemoved(Commands p_commands){
        System.out.println(p_commands.getL_firstParameter() + p_commands.getL_secondParameter());
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

    public void removeCountryNeighboursFromAll(Integer p_countryId){
    }
}
