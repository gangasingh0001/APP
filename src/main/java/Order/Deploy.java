package Order;

import Models.Country;
import Models.Player;

/**
 * The command class of deploy order
 */
public class Deploy implements IOrders{
    /**
     * Number of Armies to deploy
     */
    private int numberOfArmiesToDeploy;
    /**
     * The name of country the armies deployed to
     */
    private String targetCountryName ;
    /**
     * The ID of country the armies deployed to
     */
    private String targetCountryID ;

    /**
     *Parameterized Constructor for Deploy
     * @param _numberOfArmiesToDeploy  The number of armies ued to deploy
     * @param _targetCountryID         The ID of country the armies deployed to
     * @param _targetCountryName      The name of country the armies deployed to
     */
    public Deploy(int _numberOfArmiesToDeploy,String _targetCountryID, String _targetCountryName) {
        this.numberOfArmiesToDeploy = _numberOfArmiesToDeploy;
        this.targetCountryName = _targetCountryName;
        this.targetCountryID = _targetCountryID;
    }

    /**
     *  Execute this Deploy order
     * @param player The player to execute current order
     */
    @Override
    public void execute(Player player) {
        while (!player.getD_orderList().isEmpty()) {
            IOrders deployObj = player.getD_orderList().poll();
            for(Country country: player.getD_coutriesOwned()) {
                assert deployObj != null;
                if(country.getName().equals(deployObj.getTargetCountryName())) {
                    country.setD_Armies(country.getD_Armies()+deployObj.getNumberOfArmies());
                    break;
                }
            }
        }
    }

    /**
     * valid the current game state
     * @param p_gameState show the states of game
     * @return boolean
     */
    @Override
    public boolean valid(int p_gameState) {
        return false;
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

    public String getTargetCountryName() {
        return targetCountryName;
    }

    public String getTargetCountryID() {
        return targetCountryID;
    }

    public int getNumberOfArmies() {
        return numberOfArmiesToDeploy;
    }
}
