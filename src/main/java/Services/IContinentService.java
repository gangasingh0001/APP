package Services;

import java.util.ArrayList;


import Models.Continent;
import Utils.Commands;

public interface IContinentService {
    public ArrayList<Continent> getContinentList();
    public void addContinent(Continent p_continent);
    public boolean isContinentRemoved(Commands p_commands);
}
