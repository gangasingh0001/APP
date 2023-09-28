package Models;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    private int d_id;
    private String d_name;
    private int d_value;
    private String d_color;
    private List<Country> d_countries;

    public Continent(int p_id, String p_name,int p_value, String p_color) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_value = p_value;
        this.d_color = p_color;
        this.d_countries = new ArrayList<>();
    }

    public int getId() {
        return d_id;
    }

    public String getName() {
        return d_name;
    }

    public String getColor() {
        return d_color;
    }

    public int getValue() {
        return d_value;
    }

    public List<Country> getCountries() {
        return d_countries;
    }

    public void addCountry(Country country) {
        d_countries.add(country);
    }

    @Override
    public String toString() {
        return d_name;
    }

}
