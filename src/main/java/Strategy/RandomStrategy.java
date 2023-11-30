package Strategy;

import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Services.PlayerService;

import java.util.*;

public class RandomStrategy implements PlayerStrategy {
    /**
     * playerService used for find map and Hashmap<Country,Player>
     */
    PlayerService d_playerService;

    /**
     * constructor for RandomStrategy
     * @param l_playerService  playerService used for initial d_playerService
     */
    public RandomStrategy(PlayerService l_playerService)
    {
        this.d_playerService=l_playerService;
    }

    /**
     * where the armies move from
     */
    Country d_moveFrom;
    /**
     * where the armies move to
     */
    Country d_moveTo;
    /**
     * where the armies attack from
     */
    Country d_attackfrom;
    /**
     * where the armies attack to
     */
    Country d_attackTO;
    /**
     * where the armies deploy to
     */
    Country d_deployto;

    /**
     * select all target country
     * @param l_player the player who select target country
     */
    public void selectCountry(Player l_player)
    {
        IWorldMap mymap = d_playerService.getD_worldMap();
        ArrayList<Country> CountryBelongtoPlayer = new ArrayList<Country>();
        Iterator<Map.Entry<Country, Player>> myit = d_playerService.getD_playerOwnedCountriesMap().entrySet().iterator();
        // firstly we need get all the country belong to players
        while (myit.hasNext())
        {
            Map.Entry<Country, Player> tempEntry = myit.next();
            if (tempEntry.getValue().equals(l_player))
            {
                CountryBelongtoPlayer.add(tempEntry.getKey());
            }
        }
        if(!CountryBelongtoPlayer.isEmpty()) d_deployto=CountryBelongtoPlayer.get(0);
        for(Country i:CountryBelongtoPlayer)
        {
            List<Country> neighbors=mymap.getNeighborsOfCountry(i);
            for(Country j:neighbors)
            {
                boolean hasmove=false;
                if (CountryBelongtoPlayer.contains(j))
                {
                    hasmove=true;
                    d_moveFrom=i;
                    d_moveTo=j;
                }
                if (hasmove==false&&!CountryBelongtoPlayer.contains(j))
                {
                    d_attackfrom=i;
                    d_attackTO=j;
                }
            }
        }
    }

    /**
     * execute deploy action
     * @param l_player the player execute deploy action
     */
    @Override
    public void deploy(Player l_player) {
        if(d_deployto!=null)
        {
            d_deployto.setD_Armies(d_deployto.getD_Armies()+5);
        }
    }

    /**
     * execute attack action
     * @param l_player the player execute attack action
     */
    @Override
    public void attack(Player l_player) {
        if(d_attackfrom!=null&&d_attackTO!=null)
        {
            Country d_sourceCountry=d_attackfrom;
            int d_numberOfArmiesToAdvance=d_sourceCountry.getD_Armies();
            Country d_targetCountry=d_attackTO;
           Player d_SourcePlayer=l_player;

           HashMap<Country,Player> d_countryOwnerMap=d_playerService.getD_playerOwnedCountriesMap();

            d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies()-d_numberOfArmiesToAdvance);

                while (d_numberOfArmiesToAdvance != 0 && d_targetCountry.getD_Armies() != 0) {
                    Random random = new Random();
                    int l_attcakRandom = random.nextInt(0, 10);
                    int l_defenceRandom = random.nextInt(0, 10);
                    if (l_attcakRandom <= 5) d_numberOfArmiesToAdvance--;
                    if (l_defenceRandom <= 4) d_targetCountry.setD_Armies(d_targetCountry.getD_Armies()-1);
                }

                if (d_targetCountry.getD_Armies() == 0 && d_numberOfArmiesToAdvance!=0) {
                    if(!d_targetCountry.isD_NeutralCountry()) {
                        d_countryOwnerMap.put(d_targetCountry, d_SourcePlayer);
                        d_targetCountry.setD_Armies(d_numberOfArmiesToAdvance);
                        Player d_TargetPlayer = d_countryOwnerMap.get(d_targetCountry);
                        d_TargetPlayer.removeAcquiredCountry(d_targetCountry);
                        d_SourcePlayer.addAcquiredCountry(d_targetCountry);
                        d_countryOwnerMap.put(d_targetCountry, d_SourcePlayer);
                    } else {
                        d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies()+d_numberOfArmiesToAdvance);
                    }
                }
        }
    }

    /**
     * execute move armies
     * @param l_player the current player who execute move armies
     */
    @Override
    public void moveArmies(Player l_player) {
        if(d_moveTo!=null&&d_moveFrom!=null)
        {
            Random random=new Random();

            int movearmies=random.nextInt(0,d_moveFrom.getD_Armies()+1);
            d_moveFrom.setD_Armies(d_moveFrom.getD_Armies()-movearmies);
            d_moveTo.setD_Armies(d_moveTo.getD_Armies()+movearmies);
        }
    }

    /**
     * execute all actions
     * @param player the current player execute all actions
     */
    @Override
    public void execute(Player player)
    {
        selectCountry(player);
        deploy(player);
        moveArmies(player);
        attack(player);
    }
}
