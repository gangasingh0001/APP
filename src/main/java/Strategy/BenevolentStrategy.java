package Strategy;

import Models.Country;
import Models.Player;

import java.util.*;

/**
 * this is the class for benevolent strategy player
 * computer player strategy that focuses on protecting its weak countries
 * (deploys on its weakest country, never attacks,
 * then moves its armies in order to reinforce its weaker country).
 */
public class BenevolentStrategy implements PlayerStrategy {

    /**
     * @param p_player
     */
    @Override
    public void deploy(Player p_player) {
        Country l_weakestCountry = findweakestCountry(p_player);
        int l_armiesToDeploy = p_player.getD_numberOfArmies();
        //find the weakest country, deploy armies to the target country
        l_weakestCountry.setD_Armies(l_weakestCountry.getD_Armies() + l_armiesToDeploy);
        //p_player.deployArmies(strongestCountry, armiesToDeploy);
    }

    /**
     * method to find the weakest country
     * @param p_player player object
     * @return weakest country
     */
    public Country findweakestCountry(Player p_player){
        List<Country> l_countriesOwnedByPlayer = p_player.getCountryAcquired();
        Country l_weakestCountry = calculateWeakestCountry(l_countriesOwnedByPlayer);
        return l_weakestCountry;
    }

    /**
     * method to calculate the weakest countries by finding least armies in that country
     * @param p_countryList country list
     * @return weakest country if found, else return null
     */
    public Country calculateWeakestCountry(List<Country> p_countryList){
        LinkedHashMap<Country, Integer> l_countriesWithArmies = new LinkedHashMap<>();

        int l_leastNumOfArmies;

        for (Country l_country : p_countryList){
            l_countriesWithArmies.put(l_country, l_country.getD_Armies());
        }
        l_leastNumOfArmies = Collections.min(l_countriesWithArmies.values());
        //iterate through map to find any country with the least armies, then return it
        for (Map.Entry<Country,Integer> entry : l_countriesWithArmies.entrySet()){
            if (entry.getValue().equals(l_leastNumOfArmies)){
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * @param player
     */
    @Override
    public void attack(Player player) {

    }

    /**
     * under benevolent strategy
     * the player moves its armies in order to reinforce its weaker country
     * @param p_player player object
     */
    @Override
    public void moveArmies(Player p_player) {
        //        Country strongestCountry = findStrongestCountry(player.getCountries());
//        List<Country> connectedCountries = findConnectedCountries(player, strongestCountry);
//
//        // Move armies to the strongest country to maximize aggregation
//        for (Country sourceCountry : connectedCountries) {
//            if (!sourceCountry.equals(strongestCountry)) {
//                int armiesToMove = sourceCountry.getArmies() - 1;  // Keep one army in the source country
//                player.moveArmies(sourceCountry, strongestCountry, armiesToMove);
//            }
//        }
        Country l_weakestCountry = findweakestCountry(p_player);
        List<Country> l_connectedWeakerCountries = findConncectedWeakerCountries(p_player,l_weakestCountry);
    }

    public List<Country> findConncectedWeakerCountries(Player p_player, Country p_country){
        List<Country> l_WeakerNeighbours = new ArrayList<>();

        return l_WeakerNeighbours;
    }

    /**
     *
     */
    @Override
    public void execute(Player player) {

    }
}
