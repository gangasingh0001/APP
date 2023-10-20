package Orders;

import Models.Player;

/**
 * This is the advance class which implements IOrders interface
 */
public class Advance implements IOrders{

    /**
     * number of armies assigned each term
     */
    private int d_numberOfArmiesToDeploy;

    /**
     * target country name
     */
    private String d_targetCountryName ;

    /**
     * target country ID
     */
    private String d_targetCountryID ;

    /**
     * constructor class of Advance
     * @param p_numberOfArmiesToDeploy parameter of  number of armies to assign
     * @param p_targetCountryID  parameter of target country ID
     * @param p_targetCountryName parameter of target country name
     */
    public Advance(int p_numberOfArmiesToDeploy,String p_targetCountryID, String p_targetCountryName) {
        this.d_numberOfArmiesToDeploy = p_numberOfArmiesToDeploy;
        this.d_targetCountryName = p_targetCountryName;
        this.d_targetCountryID = p_targetCountryID;
    }

    /**
     * override method of execute orders from players
     * @param p_player parameter of player object
     */
    @Override
    public void execute(Player p_player) {

    }

    /**
     * boolean method to check the game state
     * @param p_gameState show the states of game
     * @return
     */
    @Override
    public boolean valid(int p_gameState) {
        return false;
    }

    /**
     * override method to print the order from players
     */
    @Override
    public void printOrder() {

    }

    /**
     * override method to get the order name
     * @return
     */
    @Override
    public String getOrderName() {
        return null;
    }

    @Override
    public String getTargetCountryName() {
        return null;
    }

    @Override
    public String getTargetCountryID() {
        return null;
    }

    @Override
    public int getNumberOfArmies() {
        return 0;
    }
}
