package Models;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private int d_id;
    private String d_name;
    private int d_continentId;
    private int armies;

    public Country(int p_id, String p_name, int p_continentId) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_continentId = p_continentId;
        this.armies = 0;
    }

    public int getId() {
        return d_id;
    }

    public String getName() {
        return d_name;
    }

    public int getContinentId() {
        return d_continentId;
    }

    @Override
    public String toString() {
        return d_name;
    }

    public void setD_Armies(int numberOfArmies) {
        this.armies = numberOfArmies;
    }

    public int getD_Armies() {
        return armies;
    }
}
