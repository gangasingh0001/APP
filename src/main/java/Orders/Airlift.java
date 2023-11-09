package Orders;

import Models.CardType;
import Models.Country;
import Models.Player;
import Services.CountryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Airlift implements IOrders
{
    private int d_numberOfArmiesToAdvance;
    private String d_targetCountryName ;
    private String d_sourceConuntryName;
    private Player d_SourcePlayer;
    private Country d_sourceCountry;
    private Country d_targetCountry;
    private HashMap<Country,Player> d_countryOwnerMap;

    /**
     * get logging records
     */
    private Logger d_logger;

    public Airlift(int p_numberOfArmiesToDeploy, String p_targetCountryName, String p_sourceConuntryName, Player p_sourcePlayer, HashMap<Country,Player> p_countryOwnerMap) {
        d_numberOfArmiesToAdvance=p_numberOfArmiesToDeploy;
        d_targetCountryName=p_targetCountryName;
        d_sourceConuntryName=p_sourceConuntryName;
        d_SourcePlayer=p_sourcePlayer;
        d_countryOwnerMap=p_countryOwnerMap;
        d_SourcePlayer.removeCard(CardType.AIRLIFT);
    }

    /**
     * constructor for card airlift with logging reocrds
     * @param p_numberOfArmiesToDeploy number of armies to deploy
     * @param p_targetCountryName target country
     * @param p_sourceConuntryName source country name
     * @param p_sourcePlayer source player
     * @param p_countryOwnerMap country owned map
     * @param p_logger logging records
     */
    public Airlift(int p_numberOfArmiesToDeploy, String p_targetCountryName, String p_sourceConuntryName, Player p_sourcePlayer, HashMap<Country,Player> p_countryOwnerMap, Logger p_logger) {
        d_numberOfArmiesToAdvance=p_numberOfArmiesToDeploy;
        d_targetCountryName=p_targetCountryName;
        d_sourceConuntryName=p_sourceConuntryName;
        d_SourcePlayer=p_sourcePlayer;
        d_countryOwnerMap=p_countryOwnerMap;
        d_SourcePlayer.removeCard(CardType.AIRLIFT);
        d_logger = p_logger;
    }


    @Override
    public void execute() {

        if (valid())
        {
//            for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet()) {
//                Country country = entry.getKey();
//                if(country.getName().equals(d_sourceConuntryName)) {
//                    d_targetCountry = country;
//                }
//                if(country.getName().equals(d_sourceConuntryName)) {
//                    d_sourceCountry = country;
//                }
//            }
            d_logger.severe("Airlift card is playing!");
            d_sourceCountry.setD_Armies(d_sourceCountry.getD_Armies()-this.d_numberOfArmiesToAdvance);
            if(d_countryOwnerMap.get(d_targetCountry).equals(d_SourcePlayer))
            {
                d_targetCountry.setD_Armies(d_targetCountry.getD_Armies()+this.d_numberOfArmiesToAdvance);
            } else {
                while (this.d_numberOfArmiesToAdvance != 0 && d_targetCountry.getD_Armies() != 0) {
                    Random random = new Random();
                    int l_attcakRandom = random.nextInt(0, 10);
                    int l_defenceRandom = random.nextInt(0, 10);
                    if (l_attcakRandom <= 5) this.d_numberOfArmiesToAdvance--;
                    if (l_defenceRandom <= 4) d_targetCountry.setD_Armies(d_targetCountry.getD_Armies()-1);
                }

                if (d_targetCountry.getD_Armies() == 0&&this.d_numberOfArmiesToAdvance!=0)
                {
                    d_countryOwnerMap.put(d_targetCountry,d_SourcePlayer);
                    d_targetCountry.setD_Armies(this.d_numberOfArmiesToAdvance);
                    Player d_TargetPlayer = d_countryOwnerMap.get(d_targetCountry);

                    d_TargetPlayer.removeAcquiredCountry(d_targetCountry);
                    d_SourcePlayer.addAcquiredCountry(d_targetCountry);
                    d_countryOwnerMap.put(d_targetCountry,d_SourcePlayer);
                }
            }
        }
    }
    @Override
    public boolean valid() {
        d_logger.severe("Check if Airlift card is valid...");
        boolean sourceCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet())
        {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_sourceConuntryName)){
                d_sourceCountry=temp;
                sourceCountryFind=true;
                break;
            }
        }
        if (!sourceCountryFind){
            d_logger.severe("source country is not exist");
            System.out.println("source country is not exist");
            return false;}
        boolean targetCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet())
        {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_targetCountryName)){
                d_targetCountry=temp;
                targetCountryFind=true;
                break;
            }
        }
        if (!targetCountryFind){
            d_logger.severe("target country is not exist");
            System.out.println("target country is not exist");
            return false;}
        if (d_SourcePlayer.getD_diplomacyWith().contains(d_countryOwnerMap.get(d_targetCountry)))
        {
            d_logger.severe("The advance order is invalid, because two players have diplomacy");
            System.out.println("The advance order is invalid, because two players have diplomacy");
            return false;
        }
        if(d_countryOwnerMap.get(d_sourceCountry).equals(d_SourcePlayer)&&this.d_numberOfArmiesToAdvance<=d_sourceCountry.getD_Armies()-1)
        {
            return true;
        }
        else if (!d_countryOwnerMap.get(d_sourceCountry).equals(d_SourcePlayer))
        {
            d_logger.severe("Current Country is not belong to Source Player");
            System.out.println("Current Country is not belong to Source Player");
            return false;
        }
        else
        {
            d_logger.severe("We Do not Have Enough Armies yo Attack");
            System.out.println("We Do not Have Enough Armies yo Attack");
            return false;
        }

    }
    @Override
    public void printOrder() {
        d_logger.severe("Airlift card validating...");
        if (!valid()){
            d_logger.severe("the current order is invalid");
            System.out.println("the current order is invalid");
        }
        else
        {
            d_logger.severe(d_SourcePlayer.getD_playerName()+" will Airlift to country "+d_targetCountry.getName()+" form country "+d_sourceCountry.getName()+" with the number of arimes "+d_numberOfArmiesToAdvance);
            System.out.println(d_SourcePlayer.getD_playerName()+" will Airlift to country "+d_targetCountry.getName()+" form country "+d_sourceCountry.getName()+" with the number of arimes "+d_numberOfArmiesToAdvance);
        }
    }

    @Override
    public String getOrderName() {
        return "Airlift";
    }

    @Override
    public String getTargetCountryName() {
        return d_targetCountryName;
    }

    @Override
    public String getTargetCountryID() {
        return d_targetCountryName;
    }

    @Override
    public int getNumberOfArmies() {
        return d_numberOfArmiesToAdvance;
    }
}
