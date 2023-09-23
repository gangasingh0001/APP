package main.java.org.example.Map;

import java.util.HashSet;
import java.util.Set;

public class Country {
    private int d_ConutryID;
    private String d_CountryName;
    private int d_ContientId;
    private Player d_Player;
    private int d_Armies;
    Set<Country> d_NeighborCountry = new HashSet<>();
    Set<String> d_NeighborCountryName = new HashSet<>();


    void SetCountryID(int p_CountryID) {
        this.d_ConutryID = p_CountryID;
    }

    int GetCountryID() {
        return this.d_ConutryID;
    }

    void SetCountryName(String p_CountryName) {
        this.d_CountryName = p_CountryName;
    }

    String GetCountryName() {
        return d_CountryName;
    }

    void SetPlayer(Player p_Player) {
        this.d_Player = p_Player;
    }

    Player GetPlayer() {
        return this.d_Player;
    }

    void SetContientID(int p_ContientID) {
        this.d_ContientId = p_ContientID;
    }

    int GetContinentID() {
        return this.d_ContientId;
    }

    void SetArmies(int p_Armies) {
        p_Armies = Math.max(p_Armies, 0);//Avoid p_Armies less than 0
        this.d_Armies = p_Armies;
    }

    int GetArmies() {
        return this.d_Armies;
    }

    void AddNeighborCountry(Country p_Country) {
        if (!this.d_NeighborCountry.contains(p_Country)) {
            this.d_NeighborCountry.add(p_Country);
            this.d_NeighborCountryName.add(p_Country.GetCountryName());
        }
    }

    void RemoveNeighborCountry(Country p_Country) {
        if (this.d_NeighborCountry.contains(p_Country)) {
            this.d_NeighborCountry.remove(p_Country);
            this.d_NeighborCountryName.remove(p_Country.GetCountryName());
        }
    }

    Set<Country> GetNeighborCountryID() {
        return this.d_NeighborCountry;
    }

    Set<String> GetNeighborCountryName() {
        return this.d_NeighborCountryName;
    }


    void attackOtherCountry(int p_AttackingArmiesNumber, Country p_AttackedCountry) {
        p_AttackingArmiesNumber = Math.min(this.d_Armies, p_AttackingArmiesNumber);
        this.d_Armies = this.d_Armies - p_AttackingArmiesNumber;
        if (p_AttackedCountry.GetArmies() >= p_AttackingArmiesNumber) {
            p_AttackedCountry.SetArmies(p_AttackedCountry.GetArmies() - p_AttackingArmiesNumber);
        } else {
            p_AttackedCountry.SetPlayer(this.d_Player);
            p_AttackedCountry.SetArmies(p_AttackingArmiesNumber - p_AttackedCountry.GetArmies());
        }
    }

    boolean isNeighbor(Country p_Country) {
        if (this.d_NeighborCountry.contains(p_Country)) return true;
        else return false;
    }

}
