package Models;

import Constants.ApplicationConstants;
import Orders.Deploy;
import Orders.IOrders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Player {
    private String d_color;

    private String d_name;

    List<Country> d_coutriesOwned;

    Queue<IOrders> d_orderList;
    private int d_numberOfArmies = ApplicationConstants.DEFAULtARMIES;

    public Player(String p_playerName) {
        this.d_name = p_playerName;
        d_orderList = new LinkedList<>();
        d_coutriesOwned = new ArrayList<>();
    }

    public int getD_numberOfArmies() {
        return d_numberOfArmies;
    }

    public void addCountriesOwned(Country p_country) {
        this.d_coutriesOwned.add(p_country);
    }

    public List<Country> getD_coutriesOwned() {
        return d_coutriesOwned;
    }

    public void add_deployInOrderList(Deploy object) {
        this.d_orderList.add(object);
    }

    public Queue<IOrders> getD_orderList() {
        return d_orderList;
    }

    public String getD_playerName() {
        return d_name;
    }
}
