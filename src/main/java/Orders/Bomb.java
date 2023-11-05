package Orders;

import Models.*;

import java.util.List;

/**
 * This is a bomb class which implements the bomb order card
 */
public class Bomb implements IOrders {

    /**
     * worldmap variable
     */
    private WorldMap d_worldMap;

    /**
     * target country
     */
    private Country d_TargetCountry;

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
     * @param p_TargetCountry target country to bomb
     */
    public Bomb(Country p_TargetCountry){
        Card card = new Card();
        card.setCardType(CardType.BOMB);
        d_TargetCountry = p_TargetCountry;
    }

    /**
     * override method of execute orders from players
     * bomb method to check conditions, if all met
     * destroy half of the armies located on an opponent’s territory
     * @param p_player parameter of player object
     */
    @Override
    public void execute(Player p_player) {

        while (!p_player.getD_PlayerCards().isEmpty()){
            if (validateCard(p_player,this.d_TargetCountry)){
                int l_newArmies = d_TargetCountry.getD_Armies() / 2;
                d_TargetCountry.setD_Armies(l_newArmies);
                p_player.removeCard(CardType.BOMB);
                break;
            }

        }
    }


    public boolean validateCard(Player p_player, Country p_TargetCountry){
        //validate if the player is exist
        if (p_player == null){
            System.err.println("This player is not valid.");
            return false;
        }

        //validate if the player's card is bomb type
        if (!p_player.checkIfCardExists(CardType.BOMB)){
            System.err.println("You do not have the Bomb card.");
            return false;
        }

        //validate if the target country is belong to the current player
        if (p_player.getD_coutriesOwned().contains(p_TargetCountry)){
            System.err.println("This country is under your control, you cannot Bomb it!");
            return false;
        }

        //validate if the target country is adjacent country of this player's owned countries
        boolean l_adjacent = false;
        for (Country l_PlayerCountry : p_player.getD_coutriesOwned()){
            for (Country l_NeighbourCountry : d_worldMap.getNeighborsOfCountry(l_PlayerCountry)){
                if (l_NeighbourCountry.getName().equals(p_TargetCountry.getName())){
                    l_adjacent = true;
                    break;
                }
            }
        }
        if (!l_adjacent){
            System.err.println("Target country is not adjacent to one of countries that belongs to you.");
            return false;
        }

        //check is negotiate card is played


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
