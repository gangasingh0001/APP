package Orders;

import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Models.WorldMap;
import Services.CountryService;
import Services.IPlayerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is the advance class which implements IOrders interface
 */
public class Advance implements IOrders{

    private int d_numberOfArmiesToAdvance;
    private final String d_targetCountryName ;
    private final String d_sourceConuntryName;
    private Player d_SourcePlayer;
    private final IWorldMap d_WorldMap;
    private Country d_sourceCountry;
    private Country d_targetCountry;
    private HashMap<Country,Player> d_countryOwnerMap;

    public Advance(String p_sourceConuntryName, String p_targetCountryName, int p_numberOfArmiesToDeploy, Player p_SourcePlayer, IWorldMap p_WorldMap, HashMap<Country,Player> p_countryOwnerMap)
    {
        d_numberOfArmiesToAdvance = p_numberOfArmiesToDeploy;
        d_targetCountryName = p_targetCountryName;
        d_sourceConuntryName = p_sourceConuntryName;
        d_SourcePlayer = p_SourcePlayer;
        d_WorldMap = p_WorldMap;
        d_countryOwnerMap = p_countryOwnerMap;
    }
    /**
     * number of armies assigned each term
     */
    private int d_numberOfArmiesToDeploy;

    /**
     * target country name
     */
    //private String d_targetCountryName ;

    /**
     * target country ID
     */
    private String d_targetCountryID ;

    /**
     * constructor class of Advance
     * @param p_numberOfArmiesToDeploy parameter of  number of armies to assign
     * @param p_targetCountryID  parameter of target country ID
     * @param p_targetCountryName parameter of target country name
     */
//    public Advance(int p_numberOfArmiesToDeploy,String p_targetCountryID, String p_targetCountryName) {
//        this.d_numberOfArmiesToDeploy = p_numberOfArmiesToDeploy;
//        this.d_targetCountryName = p_targetCountryName;
//        this.d_targetCountryID = p_targetCountryID;
//    }

    /**
     * override method of execute orders from players
     */
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
                }
            }
        }
    }

    /**
     * boolean method to check the game state
     * @return true if the current order can be executed, else return false
     */
    @Override
    public boolean valid() {
        Boolean sourceCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet())
        {
        Country temp=entry.getKey();
        if (temp.getName().equals(d_sourceConuntryName)){
            d_sourceCountry=temp;
            sourceCountryFind=true;
            break;
        }
        }
        if (sourceCountryFind==false){;System.out.println("source country is not exist");return false;}
        Boolean targetCountryFind=false;
        for (Map.Entry<Country, Player> entry : d_countryOwnerMap.entrySet())
        {
            Country temp=entry.getKey();
            if (temp.getName().equals(d_targetCountryName)){
                d_targetCountry=temp;
                targetCountryFind=true;
                break;
            }
        }
        if (targetCountryFind==false){System.out.println("target country is not exist");return false;}


     if(!d_WorldMap.getNeighborsOfCountry(d_targetCountry).contains(d_sourceCountry))
     {
         System.out.println("Two countries is not connected");
     }

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
    /**
     * override method to print the order from players
     */
    @Override
    public void printOrder() {
        if (!valid())System.out.println("the current order is invalid");
        else
        {
            System.out.println(d_SourcePlayer.getD_playerName()+" will attack country "+d_targetCountry.getName()+" form country "+d_sourceCountry.getName()+" with the number of arimes "+d_numberOfArmiesToAdvance);
        }
    }

    /**
     * override method to get the order name
     * @return
     */
    @Override
    public String getOrderName() {
        return "Advance";
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
