package Services;

import java.util.List;


import Models.Continent;
import Utils.Commands;

public interface IContinentService {
    public List<Continent> getContinentList();
    public void addContinent(Commands p_continent);
    public boolean isContinentRemoved(Commands p_commands);
}
