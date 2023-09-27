package Models;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    /**
     * continent ID.
     */
    Integer d_continentID;

    /**
     * continent name.
     */
    String d_continentName;

    /**
     * continent value.
     */
    Integer d_continentValue;

    /**
     * List of countries.
     */
    List<Country> d_countries;

    /**
     * constructor to this class.
     *
     * @param p_continentID continent ID
     * @param p_continentName continent name
     * @param p_continentValue continent value
     */
    public Continent(Integer p_continentID, String p_continentName, int p_continentValue) {
        this.d_continentID=p_continentID;
        this.d_continentName=p_continentName;
        this.d_continentValue=p_continentValue;
    }

    /**
     * default contructor to the class.
     */
    public Continent(){

    }

    /**
     * constructor to this class.
     *
     * @param p_continentName continent name
     */
    public Continent(String p_continentName) {
        this.d_continentName = p_continentName;
    }

    /**
     * getter method to get the continent ID.
     *
     * @return continent ID
     */
    public Integer get_continentID() {
        return d_continentID;
    }

    /**
     * setter method to set the continenet ID.
     *
     * @param p_continentID continent ID
     */
    public void set_continentID(Integer p_continentID) {
        this.d_continentID = p_continentID;
    }

    /**
     * getter method to get the continent name.
     *
     * @return continent name
     */
    public String get_continentName() {
        return d_continentName;
    }

    /**
     * setter method to set the continent name.
     *
     * @param p_continentName name of the continent
     */
    public void set_continentName(String p_continentName) {
        this.d_continentName = p_continentName;
    }

    /**
     * getter method to get the continent value.
     *
     * @return continent value
     */
    public Integer get_continentValue() {
        return d_continentValue;
    }

    /**
     * setter method to set the continent value.
     *
     * @param p_continentValue the continent value
     */
    public void set_continentValue(Integer p_continentValue) {
        this.d_continentValue = p_continentValue;
    }

    /**
     * getter method to get the countries.
     *
     * @return list of countries
     */
    public List<Country> get_countries() {
        return d_countries;
    }

    /**
     * setter method to set the countries.
     *
     * @param p_countries list of countries
     */
    public void set_countries(List<Country> p_countries) {
        this.d_countries = p_countries;
    }

    /**
     * adds the specified country.
     *
     * @param p_country the country to be added
     */

}
