package Strategy;

import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Services.PlayerService;

import java.util.*;

public class CheaterStrategy implements PlayerStrategy {
    /**
     * playerService used for find map and Hashmap<Country,Player>
     */
    PlayerService d_playerService;

    /**
     * constructor for CheaterStrategy
     * @param l_playerService  playerService used for initial d_playerService
     */
    public CheaterStrategy(PlayerService l_playerService)
    {
        this.d_playerService=l_playerService;
    }
    /**
     * execute deploy action
     * @param l_player the player execute deploy action
     */
    @Override
    public void deploy(Player l_player) {

        //return null;
    }

    /**
     * execute attack action
     * @param l_player the player execute attack action
     */
    @Override
    public void attack(Player l_player) {

        //return null;
    }
    /**
     * execute move armies
     * @param l_player the current player who execute move armies
     */

    @Override
    public void moveArmies(Player l_player) {
        //return null;

    }

    /**
     * for cheater player, we do not need creating any order,
     * we just need to execute the action for cheater player
     * @param l_player the player who execute cheater strategy
     */
    @Override
    public void execute(Player l_player)
    {
        IWorldMap mymap=d_playerService.getD_worldMap();

        ArrayList<Country> CountryBelongtoPlayer=new ArrayList<Country>();
        Iterator<Map.Entry<Country,Player>> myit=d_playerService.getD_playerOwnedCountriesMap().entrySet().iterator();

       // firstly we need get all the country belong to players
        while (myit.hasNext())
        {
            Map.Entry<Country,Player> tempEntry=myit.next();
            if(tempEntry.getValue().equals(l_player))
            {
                CountryBelongtoPlayer.add(tempEntry.getKey());
            }
        }
        //check if the country belong to player has a neighbor country with enemy
        //if it is, ew need double armies for that country
        for(Country i:CountryBelongtoPlayer)
        {
            List<Country> nighborsOfi=mymap.getNeighborsOfCountry(i);
            for (Country j:nighborsOfi)
            {
                if(!CountryBelongtoPlayer.contains(j))
                {
                    i.setD_Armies(i.getD_Armies()*2);
                    break;
                }
            }
        }

//then we should assign all the playe's neighbor country with enemy to player
        for(Country i:CountryBelongtoPlayer)
        {
            List<Country> nighborsOfi=mymap.getNeighborsOfCountry(i);
            for (Country j:nighborsOfi)
            {
                if(!CountryBelongtoPlayer.contains(j))
                {
                    d_playerService.getD_playerOwnedCountriesMap().put(j,l_player);
                }
            }

        }
    }


}
