package org.app.Models;

/**
 * This is a country class which manages information of all the countries on the map
 */
public class Country {

    /**
     * country ID
     */
    private int d_id;

    /**
     * country name
     */
    private String d_name;

    /**
     * the continent id which this country belongs to
     */
    private int d_continentId;

    /**
     * armies that deployed each term to this country
     */
    private int d_armies;

    /**
     * This is a constructor of the country class
     *
     * @param p_id country ID
     * @param p_name country name
     * @param p_continentId continent ID this country belongs to
     */
    public Country(int p_id, String p_name, int p_continentId) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_continentId = p_continentId;
        this.d_armies = 0;
    }

    /**
     * getter method to get country ID
     * @return country ID
     */
    public int getId() {
        return d_id;
    }

    /**
     * getter method to get country name
     * @return country name
     */
    public String getName() {
        return d_name;
    }

    /**
     * getter method to get continent ID of this country
     * @return continent ID
     */
    public int getContinentId() {
        return d_continentId;
    }

    /**
     * print method to print country name
     * @return country name
     */
    @Override
    public String toString() {
        return d_id + " " + d_name + " " + d_continentId;
    }

    /**
     * setter method to set number of armies to this country
     * @param p_numberOfArmies number of armies assigned to this country
     */
    public void setD_Armies(int p_numberOfArmies) {
        this.d_armies = p_numberOfArmies;
    }

    /**
     * getter method to get number of armies
     * @return number of armies in this country
     */
    public int getD_Armies() {
        return d_armies;
    }
}
