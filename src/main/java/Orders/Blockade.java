package Orders;

import Models.*;

public class Blockade implements IOrders{

    /**
     * worldmap variable
     */
    private WorldMap d_WorldMap;

    /**
     * target country
     */
    private Country d_TargetCountry;

    /**
     * Parameterized Constructor for blockade card
     * @param p_TargetCountry target country
     */
    public Blockade(Country p_TargetCountry){
        Card card = new Card();
        card.setCardType(CardType.BLOCKADE);
        d_TargetCountry = p_TargetCountry;
    }


    /**
     * method to apply blockade to a country
     * @param p_player parameter of player object
     */
    @Override
    public void execute(Player p_player) {
        while (!p_player.getD_PlayerCards().isEmpty()){
            if (validateCard(p_player,this.d_TargetCountry)){
                //if select country pass all the conditions
                //triple the armies
                d_TargetCountry.setD_Armies(d_TargetCountry.getD_Armies() * 3);
                d_TargetCountry.addNeutralCountry(d_TargetCountry);
                p_player.getD_coutriesOwned().remove(d_TargetCountry);
                p_player.removeCard(CardType.BLOCKADE);
                break;
            }

        }
    }

    /**
     * this is a method to validate correctness of blockade card
     * @param p_player variable of the current player
     * @param p_TargetCountry country that current want to blockade
     * @return true if pass all the conditions else return false
     */
    public boolean validateCard(Player p_player, Country p_TargetCountry) {

        //validate if the player is exist
        if (p_player == null) {
            System.err.println("This player is not valid.");
            return false;
        }

        //validate if this country is onwed by the plater
        for (Country l_country : p_player.getD_coutriesOwned()) {
            if (!l_country.getName().equals(d_TargetCountry.getName())) {
                System.err.println("Current country is not belong to you!");
                return false;
            }
        }

            //validate if the player's card is bomb type
        if (!p_player.checkIfCardExists(CardType.BLOCKADE)) {
            System.err.println("You do not have the Blockade card.");
            return false;
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
