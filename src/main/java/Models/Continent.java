package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * The continent class manages data of all continents in the map
 */
public class Continent {

    /**
     * continent ID
     */
    private int d_id;

    /**
     * continent name
     */
    private String d_name;

    /**
     * continent value
     */
    private int d_value;

    /**
     * color represented by a continent
     */
    private String d_color;

    /**
     * list of countries existed in the continent
     */
    private List<Country> d_countries;

    /**
     * This is a constructor of the continent class
     *
     * @param p_id continent ID
     * @param p_name continent name
     * @param p_value continent value
     * @param p_color continent color
     */
    public Continent(int p_id, String p_name,int p_value, String p_color) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_value = p_value;
        this.d_color = p_color;
        this.d_countries = new ArrayList<>();
    }

    /**
     * getter method to get the continent ID
     * @return continent ID
     */
    public int getId() {
        return d_id;
    }

    /**
     * getter method to get the continent name
     * @return continent name
     */
    public String getName() {
        return d_name;
    }

    /**
     * getter method to get continent color
     * @return continent color
     */
    public String getColor() {
        return d_color;
    }

    /**
     * getter method to get continent value
     * @return continent value
     */
    public int getValue() {
        return d_value;
    }

    /**
     * getter method to get a list of countries in a continent
     * @return list of countries
     */
    public List<Country> getCountries() {
        return d_countries;
    }

    /**
     * add method to add a specified country
     * @param p_country add a country
     */
    public void addCountry(Country p_country) {
        d_countries.add(country);
    }

    /**
     * print method to print the country name
     * @return country name
     */
    @Override
    public String toString() {
        return d_name;
    }

}
