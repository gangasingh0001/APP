package Orders;

import Constants.ApplicationConstants;
import Models.Country;
import Models.Player;

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
     * The ID of country the armies deployed to
     */
    private String d_targetCountryID ;
    private Player d_sourcePlayer;


    /**
     *Parameterized Constructor for Deploy
     * @param _numberOfArmiesToDeploy  The number of armies ued to deploy
     * @param _targetCountryID         The ID of country the armies deployed to
     * @param _targetCountryName      The name of country the armies deployed to
     */
    public Deploy(Player p_sourcePlayer,int _numberOfArmiesToDeploy,String _targetCountryID, String _targetCountryName) {
        this.d_numberOfArmiesToDeploy = _numberOfArmiesToDeploy;
        this.d_targetCountryName = _targetCountryName;
        this.d_targetCountryID = _targetCountryID;
        this.d_sourcePlayer=p_sourcePlayer;
    }

    /**
     *  Execute this Deploy order
     * @param player The player to execute current order
     */
    @Override
    public void execute(Player player) {
            IOrders deployObj = player.getD_orderList().poll();
            for(Country country: player.getD_coutriesOwned())
            {
                assert deployObj != null;
                if(country.getName().equals(deployObj.getTargetCountryName())) {
                    country.setD_Armies(country.getD_Armies()+deployObj.getNumberOfArmies());
                    break;
                }
            }

    }

    /**
     * valid the current game state
     * @return boolean
     */
    @Override
    public boolean valid() {
        for (Country i:)

    }

    /**
     * Print out the current order
     */
    @Override
    public void printOrder() {

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
