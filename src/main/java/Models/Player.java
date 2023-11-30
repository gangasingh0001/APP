package Models;

import Constants.ApplicationConstants;
import Orders.Deploy;
import Orders.IOrders;
import Strategy.PlayerStrategy;

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
    private final String d_name;

    private PlayerStrategy orderStrategy;
    private Boolean humanPlayer;

    public List<String> getD_diplomacyWith()
    {
        return d_diplomacyWith;
    }

    public void setD_diplomacyWith(List<String> d_diplomacyWith)
    {
        this.d_diplomacyWith = d_diplomacyWith;
    }

    private List<String> d_diplomacyWith;
    /**
     * a list of orders that played by players
     */
    Queue<IOrders> d_orderList = null;

    public List<Country> getCountryAcquired()
    {
        return countryAcquired;
    }

    public void setCountryAcquired(List<Country> countryAcquired)
    {
        this.countryAcquired = countryAcquired;
    }

    private List<Country> countryAcquired;

    /**
     * getter method to get number of armies
     * @return number of armies
     */
    public int getD_numberOfArmies() {
        return ApplicationConstants.DEFAULTARMIES;
    }

    /**
     * add method to add orders to a order list
     * @param p_object a player's input order
     */
    public void add_deployInOrderList(Deploy p_object) {
        this.d_orderList.add(p_object);
    }//this.d_orderList.add(strategy.deploy());

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
     * a list of cards for the player
     */
    private List<Card> d_PlayerCards;

    /**
     * player constructor
     * @param p_playerName player name
     */
    public Player(String p_playerName, PlayerStrategy p_strategy) {
        this.d_name = p_playerName;
        d_orderList = new LinkedList<>();
        d_diplomacyWith=new ArrayList<>();
        this.d_PlayerCards = new ArrayList<>();
        this.countryAcquired = new ArrayList<>();
        this.orderStrategy = p_strategy;
    }

    public Player(String p_playerName, Boolean humanPlayer) {
        this.d_name = p_playerName;
        d_orderList = new LinkedList<>();
        d_diplomacyWith=new ArrayList<>();
        this.d_PlayerCards = new ArrayList<>();
        this.countryAcquired = new ArrayList<>();
        this.humanPlayer = humanPlayer;
    }

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
     *
     * @param p_CardType card type
     */
    public void removeCard(CardType p_CardType){
        d_PlayerCards.remove(new Card(p_CardType));
    }

    /**
     * add the card to the player's card list
     * @param p_card card to be added
     */
    public void addPlayerCard(Card p_card){
        d_PlayerCards.add(p_card);
    }

    public void addAcquiredCountry(Country country) {
        if(this.countryAcquired==null) countryAcquired = new ArrayList<>();
        this.countryAcquired.add(country);
    }

    public boolean ifAcquiredCountryInThisTurn() {
         if(this.countryAcquired != null) {
             return !this.countryAcquired.isEmpty();
         }
         return false;
    }

    public void removeAcquiredCountry(Country country) {
        boolean b = this.countryAcquired != null && countryAcquired.remove(country);
    }

    public void clearAcquiredCountriesList() {
        if(this.countryAcquired != null ){
            countryAcquired.clear();
        }
    }

    public void setOrderStrategy(PlayerStrategy strategy) {
        this.orderStrategy = strategy;
    }

    public void issueOrder() {
        if(orderStrategy != null) {
//            orderStrategy.issueOrder(this);
        }
    }

    public Boolean getIsHumanPlayer() {
        return humanPlayer;
    }
}
