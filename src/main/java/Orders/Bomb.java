package Orders;

import Models.Card;
import Models.CardType;
import Models.Player;
import Models.WorldMap;

/**
 * This is a bomb class which implements the bomb order card
 */
public class Bomb implements IOrders {


    /**
     * The name of country the armies deployed to
     */
    private String d_targetCountryName ;
    /**
     * The ID of country the armies deployed to
     */
    private String d_targetCountryID ;

    /**
     * Parameterized Constructor for bomb card
     * @param p_targetCountryID target country id
     * @param p_targetCountryName target country name
     */
    public Bomb(String p_targetCountryID, String p_targetCountryName){
        Card card = new Card();
        card.setCardType(CardType.BOMB);
        d_targetCountryID = p_targetCountryID;
        d_targetCountryName = p_targetCountryName;
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
     * @return if it's a valid game state
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
     * @return order name
     */
    @Override
    public String getOrderName() {
        return null;
    }

    /**
     * override method to get country name
     * @return country name
     */
    @Override
    public String getTargetCountryName() {
        return null;
    }

    /**
     * override method to get target country id
     * @return target country ID
     */
    @Override
    public String getTargetCountryID() {
        return null;
    }

    /**
     * override method to get number of armies for current country
     * @return number of armies
     */
    @Override
    public int getNumberOfArmies() {
        return 0;
    }
}
