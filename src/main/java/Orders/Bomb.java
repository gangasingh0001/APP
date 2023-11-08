package Orders;

import Models.*;

import java.util.HashMap;
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
     * country ID
     */
    private int d_TargetID;

    /**
     * target country
     */
    private Country d_TargetCountry;

    /**
     * The name of country the armies deployed to
     */
    private String d_sourceCountryName ;

    private HashMap<Country,Player> d_countryOwnerMap;
    private Player d_player;
    /**
     * Parameterized Constructor for bomb card
     * @param p_TargetID target country to bomb
     */
    public Bomb(String p_sourceConuntryName, Player p_player, HashMap<Country,Player> p_countryOwnerMap){
        Card card = new Card();
        card.setCardType(CardType.BOMB);
        d_sourceCountryName = p_sourceConuntryName;
        d_player = p_player;
        d_countryOwnerMap = p_countryOwnerMap;
    }

    /**
     * override method of execute orders from players
     * bomb method to check conditions, if all met
     * destroy half of the armies located on an opponent’s territory
     */
    @Override
    public void execute() {

        while (!d_player.getD_PlayerCards().isEmpty()){
            if (validateCard(d_player,this.d_TargetCountry)){
                int l_newArmies = d_TargetCountry.getD_Armies() / 2;
                d_TargetCountry.setD_Armies(l_newArmies);
                d_player.removeCard(CardType.BOMB);
                break;
            }

        }
    }

    @Override
    public boolean valid() {
        return false;
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
//        if (p_player.getD_coutriesOwned().contains(p_TargetCountry)){
//            System.err.println("This country is under your control, you cannot Bomb it!");
//            return false;
//        }

        //validate if the target country is adjacent country of this player's owned countries
        boolean l_adjacent = false;
//        for (Country l_PlayerCountry : p_player.getD_coutriesOwned()){
//            for (Country l_NeighbourCountry : d_worldMap.getNeighborsOfCountry(l_PlayerCountry)){
//                if (l_NeighbourCountry.getName().equals(p_TargetCountry.getName())){
//                    l_adjacent = true;
//                    break;
//                }
//            }
//        }
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

//    public boolean valid(int p_gameState) {
//        return false;
//    }

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
