package main.java.org.example.Map;

import java.util.HashSet;
import java.util.Set;

public class Continent {
    private String d_ContinentName;
    private int d_ContinentID;
    private int d_Award;
    private Set<Country> d_CountryInside = new HashSet<>();
    boolean d_isActivedAward = false;

    void CheckisActivedAward() {
        Set<String> TempPlayerIDIncidecontinent = new HashSet<>();
        if (this.d_CountryInside.size() == 0) this.d_isActivedAward = false;
        else {
            for (Country i : this.d_CountryInside) {
                TempPlayerIDIncidecontinent.add(i.GetCountryName());
            }
            if (TempPlayerIDIncidecontinent.size() == 1) this.d_isActivedAward = true;
            else this.d_isActivedAward = false;
        }
    }


    int GetAwarededPlayerID() {
        this.CheckisActivedAward();
        if (this.d_isActivedAward == true && this.d_CountryInside.size() > 0) {
            for (Country i : this.d_CountryInside) return i.GetCountryID();
            return -1;
        } else return -1;
    }

    void SetContinentName(String p_ContinentName) {
        this.d_ContinentName = p_ContinentName;
    }

    String GetContinentName() {
        return this.d_ContinentName;
    }

    void SetContinentID(int p_ContinentID) {
        this.d_ContinentID = p_ContinentID;
    }

    int GetContinentID() {
        return this.d_ContinentID;
    }

    void AddCountrytoContinent(Country p_Country) {
        if (!this.d_CountryInside.contains(p_Country)) this.d_CountryInside.add(p_Country);
    }

    void RemoveCountryinContinent(Country p_Country) {
        if (this.d_CountryInside.contains(p_Country)) this.d_CountryInside.remove(p_Country);
    }

    void SetAward(int p_Award) {
        this.d_Award = p_Award;
    }

    int GetAward() {
        return this.d_Award;
    }
}
