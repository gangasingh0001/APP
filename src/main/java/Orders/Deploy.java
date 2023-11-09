package Orders;

import Models.Country;
import Models.Player;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the Deploy class which implements IOrders interface
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
     * teh id of target country
     */
    private String d_targetCountryID ;
    /**
     * teh player who create this order
     */
    private Player d_sourcePlayer;
    /**
     * the hashmap between Country and player who owned this country
     */
    private HashMap<Country,Player> d_countryOwnerMap;
    /**
     * the country the armies deploy to
     */
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
     *  Execute this Deploy order
     */
    @Override
    public void execute() {
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
        if (targetCountryFind==false){System.out.println("target country is not exist");return false;}
        if(!d_countryOwnerMap.get(d_targetCountry).equals(d_sourcePlayer))
        {
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
     if (valid())
     {
         System.out.println("player "+d_sourcePlayer+" will deploy "+d_numberOfArmiesToDeploy+" to country "+d_targetCountry);
     }
     else
     {
         System.out.println("the current deploy order is invalid");
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
