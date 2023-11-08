package Orders;

import Models.Country;
import Models.Player;
import Services.CountryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Airlift implements IOrders
{
    private int d_numberOfArmiesToAdvance;
    private String d_targetCountryName ;
    private String d_sourceConuntryName;
    private Player d_SourcePlayer;
    private Country d_sourceCountry;
    private Country d_targetCountry;
    private HashMap<Country,Player> d_countryOwnerMap;

    public Airlift(int p_numberOfArmiesToDeploy, String p_targetCountryName, String p_sourceConuntryName, Player p_sourcePlayer, HashMap<Country,Player> p_countryOwnerMap) {
        d_numberOfArmiesToAdvance=p_numberOfArmiesToDeploy;
        d_targetCountryName=p_targetCountryName;
        d_sourceConuntryName=p_sourceConuntryName;
        d_SourcePlayer=p_sourcePlayer;
        d_countryOwnerMap=p_countryOwnerMap;
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
        if (!sourceCountryFind){;System.out.println("source country is not exist");return false;}
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
        if (!targetCountryFind){System.out.println("target country is not exist");return false;}
        if(d_countryOwnerMap.get(d_sourceCountry).equals(d_SourcePlayer)&&this.d_numberOfArmiesToAdvance<=d_sourceCountry.getD_Armies()-1)
        {
            return true;
        }
        else if (!d_countryOwnerMap.get(d_sourceCountry).equals(d_SourcePlayer))
        {
            System.out.println("Current Country is not belong to Source Player");
            return false;
        }
        else
        {
            System.out.println("We Do not Have Enough Arimes yo Attack");
            return false;
        }

    }
    @Override
    public void printOrder() {
        if (!valid())System.out.println("the current order is invalid");
        else
        {
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
