package Orders;

import Constants.ApplicationConstants;
import Models.Country;
import Models.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The command class of deploy order
 */
public class Deploy implements IOrders{
    /**
     * Number of Armies to deploy
     */
    private int d_numberOfArmiesToDeploy;
    /**
     * The name of country the armies deployed to
     */
    private String d_targetCountryName ;

    /**
     * get logging records
     */
    private Logger d_logger;

    /**
     * The ID of country the armies deployed to
     */
    private String d_targetCountryID ;
    private Player d_sourcePlayer;
    private HashMap<Country,Player> d_countryOwnerMap;
    private Country d_targetCountry;
    /**
     *Parameterized Constructor for Deploy
     * @param p_numberOfArmiesToDeploy  The number of armies ued to deploy
     * @param p_targetCountryID         The ID of country the armies deployed to
     * @param p_targetCountryName      The name of country the armies deployed to
     */
    public Deploy(int p_numberOfArmiesToDeploy,String p_targetCountryID, String p_targetCountryName, Player p_sourcePlayer, HashMap<Country,Player> p_countryOwnerMap) {
        this.d_numberOfArmiesToDeploy = p_numberOfArmiesToDeploy;
        this.d_targetCountryName = p_targetCountryName;
        this.d_targetCountryID = p_targetCountryID;
        this.d_sourcePlayer = p_sourcePlayer;
        this.d_countryOwnerMap = p_countryOwnerMap;
    }

    /**
     * Parameterized Constructor for Deploy and logging records
     * @param p_numberOfArmiesToDeploy The number of armies ued to deploy
     * @param p_targetCountryID The ID of country the armies deployed to
     * @param p_targetCountryName The name of country the armies deployed to
     * @param p_sourcePlayer source player
     * @param p_countryOwnerMap country owned map
     * @param p_logger logging records
     */
    public Deploy(int p_numberOfArmiesToDeploy,String p_targetCountryID, String p_targetCountryName, Player p_sourcePlayer, HashMap<Country,Player> p_countryOwnerMap, Logger p_logger) {
        this.d_numberOfArmiesToDeploy = p_numberOfArmiesToDeploy;
        this.d_targetCountryName = p_targetCountryName;
        this.d_targetCountryID = p_targetCountryID;
        this.d_sourcePlayer = p_sourcePlayer;
        this.d_countryOwnerMap = p_countryOwnerMap;
        d_logger = p_logger;
    }

    /**
     *  Execute this Deploy order
     */
    @Override
    public void execute() {
        d_logger.severe("Deploy order is executing...");
        IOrders deploy = d_sourcePlayer.getD_orderList().poll();
        Country country = null;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet()) {
            Country countryKey = entry.getKey();
            assert deploy != null;
            if(countryKey.getName().equals(deploy.getTargetCountryName())) {
                country = countryKey;
            }
        }
        assert country != null;
        country.setD_Armies(country.getD_Armies()+deploy.getNumberOfArmies());
    }

    /**
     * valid the current game state
     * @return boolean
     */
    @Override
    public boolean valid() {
        d_logger.severe("Check if deploy order is valid...");
        Boolean targetCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet())
        {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_targetCountryName)){
                d_targetCountry=temp;
                targetCountryFind=true;
                break;
            }
        }
        if (targetCountryFind==false){
            d_logger.severe("target country is not exist");
            System.out.println("target country is not exist");
            return false;}
        if(!d_countryOwnerMap.get(d_targetCountry).equals(d_sourcePlayer))
        {
            d_logger.severe("the source country is not belong to source player");
            System.out.println("the source country is not belong to source player");
            return false;
        }
        return true;
    }

    /**
     * Print out the current order
     */
    @Override
    public void printOrder() {
        d_logger.severe("Deploy order validating");
        if (valid()) {
            d_logger.severe("player "+d_sourcePlayer+" will deploy "+d_numberOfArmiesToDeploy+" to country "+d_targetCountry);
            System.out.println("player "+d_sourcePlayer+" will deploy "+d_numberOfArmiesToDeploy+" to country "+d_targetCountry);
            }
        else {
            d_logger.severe("the current order is invalid");
            System.out.println("the current order is invalid");
            }
    }

    /**
     * return the type of this order
     * @return type of current order
     */
    @Override
    public String getOrderName() {
        return "Deploy";
    }

    /**
     * the country name deploy to
     * @return the country name deploy to
     */

    public String getTargetCountryName() {
        return d_targetCountryName;
    }

    /**
     * get the country ID deploy to
     * @return the country ID deploy to
     */

    public String getTargetCountryID() {
        return d_targetCountryID;
    }

    /**
     * get the number of armies we need to deploy
     * @return the number of armies we need to deploy
     */

    public int getNumberOfArmies() {
        return d_numberOfArmiesToDeploy;
    }
}
