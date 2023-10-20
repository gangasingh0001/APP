package org.app.Models;

import org.app.Constants.ApplicationConstants;
import org.app.Orders.Deploy;
import org.app.Orders.IOrders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
