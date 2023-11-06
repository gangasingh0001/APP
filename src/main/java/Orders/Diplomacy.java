package Orders;

import Models.Card;
import Models.CardType;
import Models.Player;
import Models.WorldMap;
import Services.PlayerService;

public class Diplomacy implements IOrders{

    /**
     * worldmap variable
     */
    private WorldMap d_WorldMap;

    /**
     * players information
     */
    private PlayerService d_PlayerService;

    /**
     * target player's name
     */
    private String d_TargetPlayerToNegotiate;

    /**
     * Parameterized Constructor for negotiate card
     */
    public Diplomacy(String p_TargetPlayerToNegotiate){
        Card card = new Card();
        card.setCardType(CardType.NEGOTIATE);
        d_TargetPlayerToNegotiate = p_TargetPlayerToNegotiate;
    }

    @Override
    public void execute(Player p_player) {

    }

    public boolean validateCard(Player p_player){
        //check is negotiate card is exist
        if (!p_player.checkIfCardExists(CardType.NEGOTIATE)){
            System.err.println("You do not have the Negotiate card!");
            return false;
        }

        //check if the card is used on this player
        if (p_player.getD_playerName().equals(this.d_TargetPlayerToNegotiate)){
            System.err.println("You cannot play Negotiate card on yourself.");
            return false;
        }

        //check if target player is exist
        for(Player l_player: d_PlayerService.getPlayersList()) {
            if(!l_player.getD_playerName().equals(this.d_TargetPlayerToNegotiate)) {
                return false;
            }
        }

        return true;
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
