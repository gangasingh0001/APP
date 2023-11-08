package Orders;

import Models.Card;
import Models.CardType;
import Models.Player;
import Models.WorldMap;
import Services.PlayerService;

import java.util.ArrayList;

public class Diplomacy implements IOrders{



    /**
     * players information
     */
    private ArrayList<Player> d_Players;

    /**
     * target player's name
     */
    private String d_TargetPlayerToNegotiate;
    private Player d_sourcePlayer;
    private  Player d_targetPlayer;

    /**
     * Parameterized Constructor for negotiate card
     */
    public Diplomacy(String p_TargetPlayerToNegotiate,Player p_sourcePlayer,ArrayList<Player> p_Players){
//        Card card = new Card();
//        card.setCardType(CardType.DIPLOMACY);
        d_TargetPlayerToNegotiate = p_TargetPlayerToNegotiate;
        d_sourcePlayer=p_sourcePlayer;
        d_Players=p_Players;
    }

    @Override
    public void execute() {
        if (valid())
        {
            d_sourcePlayer.getD_diplomacyWith().add(d_TargetPlayerToNegotiate);
            d_targetPlayer.getD_diplomacyWith().add(d_sourcePlayer.getD_playerName());
            d_sourcePlayer.removeCard(CardType.DIPLOMACY);
        }

    }

    @Override
    public boolean valid() {
        boolean l_targetPlayerExist=false;
        for(Player l_player:d_Players)
        {
            if(l_player.getD_playerName().equals(d_TargetPlayerToNegotiate))
            {
                d_targetPlayer=l_player;
                l_targetPlayerExist=true;
                break;
            }
        }
        if(l_targetPlayerExist==true) return true;
        else
        {
            System.out.println("the player "+d_TargetPlayerToNegotiate+" is not exist");
            return false;
        }


    }



    /**
     * boolean method to check the game state
     * @param p_gameState show the states of game
     * @return if it's a valid game state
     */
//    @Override
//    public boolean valid(int p_gameState) {
//        return false;
//    }

    /**
     * override method to print the order from players
     */
    @Override
    public void printOrder() {
        if (valid())
        {
            System.out.println("player "+d_sourcePlayer.getD_playerName()+" will diplomacy with player "+d_TargetPlayerToNegotiate);
        }
        else
        {
            System.out.println("the diplomacy card is invalid: "+"player "+d_sourcePlayer.getD_playerName()+" will diplomacy with player "+d_TargetPlayerToNegotiate);
        }

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
