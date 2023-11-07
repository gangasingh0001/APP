package Orders;

import Models.Country;
import Models.Player;
import Services.CountryService;

import java.util.Random;

public class Airlift implements IOrders
{
    private int d_numberOfArmiesToAdvance;
    private String d_targetCountryName ;
    private String d_sourceConuntryName;
    private Player d_SourcePlayer;
    private CountryService d_myCountryService;
    private Country d_sourceCountry;
    private Country d_targetCountry;

    public Airlift(int p_numberOfArmiesToDeploy, String p_targetCountryName, String p_sourceConuntryName, Player p_sourcePlayer, CountryService p_temp) {
        d_numberOfArmiesToAdvance=p_numberOfArmiesToDeploy;
        d_targetCountryName=p_targetCountryName;
        d_sourceConuntryName=p_sourceConuntryName;
        d_SourcePlayer=p_sourcePlayer;
        d_myCountryService=p_temp;
    }
    @Override
    public void execute() {

        if (valid())
        {

            int l_temp=d_sourceCountry.getD_Armies();
            d_sourceCountry.setD_Armies(l_temp-this.d_numberOfArmiesToAdvance);
//            if (d_targetCountry.getD_ownedBy().equals(d_SourcePlayer))
//            {
//                l_temp=d_targetCountry.getD_Armies();
//                d_targetCountry.setD_Armies(l_temp+this.d_numberOfArmiesToAdvance);
//            } else
//            {
//                while (this.d_numberOfArmiesToAdvance != 0 && d_targetCountry.getD_Armies() != 0)
//                {
//                    Random random = new Random();
//                    int l_attcakRandom = random.nextInt(0, 10);
//                    int l_defenceRandom = random.nextInt(0, 10);
//                    if (l_attcakRandom <= 5) this.d_numberOfArmiesToAdvance--;
//                    l_temp = d_targetCountry.getD_Armies();
//                    if (l_defenceRandom <= 4) d_targetCountry.setD_Armies(l_temp-1);
//                }
//                if (d_targetCountry.getD_Armies() == 0&&this.d_numberOfArmiesToAdvance!=0)
//                {
//                    d_SourcePlayer.getD_coutriesOwned().add(d_targetCountry);
//                    d_targetCountry.getD_ownedBy().getD_coutriesOwned().remove(d_sourceCountry);
//                    d_targetCountry.setD_ownedBy(d_SourcePlayer);
//                    d_targetCountry.setD_Armies(this.d_numberOfArmiesToAdvance);
//                }
//            }
        }
    }
    @Override
    public boolean valid() {
        for(Country i:d_myCountryService.getCountryList())
        {
            if (d_sourceConuntryName==i.getName()) {d_sourceCountry=i;break;}
            System.out.println("Source Country Name is not exist");
            return  false;
        }
        for(Country i:d_myCountryService.getCountryList())
        {
            if (d_targetCountryName==i.getName()) {d_targetCountry=i;break;}
            System.out.println("Target Country Name is not exist");
            return false;
        }

//        if(d_sourceCountry.getD_ownedBy().equals(d_SourcePlayer)&&this.d_numberOfArmiesToAdvance<=d_sourceCountry.getD_Armies()-1)
//        {
//            return true;
//        }
//        else if (!d_sourceCountry.getD_ownedBy().equals(d_SourcePlayer))
//        {
//            System.out.println("Current Country is not belong to Source Player");
//            return false;
//        }
//        else
//        {
//            System.out.println("We Do not Have Enough Arimes yo Attack");
//            return false;
//        }
        return true;
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
