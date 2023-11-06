package Models;

import Constants.ApplicationConstants;
import Orders.Deploy;
import Orders.IOrders;

import java.util.*;

/**
 * This is a player class which manages data of added players
 */
public class Player {

    /**
     * continent color
     */
    private String d_color;

    /**
     * player name
     */
    private String d_name;

    /**
     * a list of negotiated players
     */
    List<Player> d_negotiateWith = new ArrayList<>();

    /**
     * player owned countries
     */
    List<Country> d_coutriesOwned;

    /**
     * a list of orders that played by players
     */
    Queue<IOrders> d_orderList;

    /**
     * number of armies assigned by players each turn
     */
    private int d_numberOfArmies = ApplicationConstants.DEFAULTARMIES;

    /**
     * a list of cards for the player
     */
    private List<Card> d_PlayerCards = new ArrayList<>();

    /**
     * getter method to get this player's cards
     * @return a list of cards for the player
     */
    public List<Card> getD_PlayerCards() {
        return d_PlayerCards;
    }

    /**
     * check if this card is available in the list
     * @param p_CardType card type
     * @return true if the card is exist otherwise false
     */
    public boolean checkIfCardExists(CardType p_CardType){
        return d_PlayerCards.stream().anyMatch(p_card -> p_card.getCardType().equals(p_CardType));
    }


    /**
     * remove the card after using it
     * @param p_CardType card type
     * @return true if the used card is removed otherwise false
     */
    public boolean removeCard(CardType p_CardType){
        return d_PlayerCards.remove(new Card(p_CardType));
    }

    /**
     * add the card to the player's card list
     * @param p_card card to be added
     */
    public void addPlayerCard(Card p_card){
        d_PlayerCards.add(p_card);
    }



    /**
     * player constructor
     * @param p_playerName player name
     */
    public Player(String p_playerName) {
        this.d_name = p_playerName;
        d_orderList = new LinkedList<>();
        d_coutriesOwned = new ArrayList<>();
    }

    /**
     * getter method to get number of armies
     * @return number of armies
     */
    public int getD_numberOfArmies() {
        return d_numberOfArmies;
    }

    /**
     * add method to add a country to a player owned countries
     * @param p_country country
     */
    public void addCountriesOwned(Country p_country) {
        this.d_coutriesOwned.add(p_country);
    }

    /**
     * getter method to get a list of player owned countries
     * @ a list of countries a player owned
     */
    public List<Country> getD_coutriesOwned() {
        return d_coutriesOwned;
    }

    /**
     * add method to add orders to a order list
     * @param p_object a player's input order
     */
    public void add_deployInOrderList(Deploy p_object) {
        this.d_orderList.add(p_object);
    }

    /**
     * getter method to get a list of player orders
     * @return a list of player orders
     */
    public Queue<IOrders> getD_orderList() {
        return d_orderList;
    }

    /**
     * getter method to get the player's name
     * @return player name
     */
    public String getD_playerName() {
        return d_name;
    }

    /**
     * add player to the negotiated player list
     * @param p_NegotiatedPlayer
     */
    public void addNegotiatedPlayer(Player p_NegotiatedPlayer){
        this.d_negotiateWith.add(p_NegotiatedPlayer);
    }

    /**
     * get the list of players that are negotiated
     * @return list of players
     */
    public List<Player> getNegotiatePlayers() {
        return d_negotiateWith;
    }

    /**
     * clear the negotiate player list
     */
    public void removeNegotiatePlayer(){
        d_negotiateWith.clear();
    }



}
