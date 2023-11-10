package Cards;

import Models.CardType;
import Models.Country;
import Models.Player;
import Orders.IOrders;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is the Airlift class which implements IOrders interface
 */
public class Airlift implements IOrders
{
    /**
     * the number of armies used to attack
     */
    private int d_numberOfArmiesToAdvance;
    /**
     * the name of country attacked
     */
    private String d_targetCountryName ;
    /**
     * the name of country attacking
     */
    private String d_sourceConuntryName;
    /**
     * the player attacking
     */
    private Player d_SourcePlayer;
    /**
     * the country attacking
     */
    private Country d_sourceCountry;
    /**
     * the country attacked
     */
    private Country d_targetCountry;
    /**
     * the hashmap between Country and player who owned this country
     */
    private HashMap<Country,Player> d_countryOwnerMap;
    /**
     * constructor for Airlift order
     * @param p_numberOfArmiesToDeploy the number of armies used to attack
     * @param p_targetCountryName  the name of country attacked
     * @param p_sourceConuntryName  the name of country attacking
     * @param p_sourcePlayer the player attacking
     * @param p_countryOwnerMap  the hashmap between Country and player who owned this country
     */
    public Airlift(int p_numberOfArmiesToDeploy, String p_targetCountryName, String p_sourceConuntryName, Player p_sourcePlayer, HashMap<Country,Player> p_countryOwnerMap) {
        d_numberOfArmiesToAdvance=p_numberOfArmiesToDeploy;
        d_targetCountryName=p_targetCountryName;
        d_sourceConuntryName=p_sourceConuntryName;
        d_SourcePlayer=p_sourcePlayer;
        d_countryOwnerMap=p_countryOwnerMap;
        d_SourcePlayer.removeCard(CardType.AIRLIFT);
    }

    /**
     * execute Airlift order
     */
    @Override
    public void execute() {

        if (valid())
        {
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

    /**
     *  check if the game state is valid
     * @return if it's a valid game state
     */
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
        if (d_SourcePlayer.getD_diplomacyWith().contains(d_countryOwnerMap.get(d_targetCountry)))
        {
            System.out.println("the advance order can is invalid, because two player has diplomacy");
            return false;
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
     * print out order information
     */
    @Override
    public void printOrder() {
        if (!valid())System.out.println("the current order is invalid");
        else
        {
            System.out.println(d_SourcePlayer.getD_playerName()+" will Airlift to country "+d_targetCountry.getName()+" form country "+d_sourceCountry.getName()+" with the number of arimes "+d_numberOfArmiesToAdvance);
        }
    }

    /**
     * get the type name of current order
     * @return the name of this order
     */
    @Override
    public String getOrderName() {
        return "Airlift";
    }

    /**
     * get target Country Name
     * @return  target Country Name
     */
    @Override
    public String getTargetCountryName() {
        return d_targetCountryName;
    }

    /**
     * get Target Country ID
     * @return Target Country ID
     */
    @Override
    public String getTargetCountryID() {
        return d_targetCountryName;
    }

    /**
     * get the number Of Armies To Advance
     * @return number Of Armies To Advance
     */
    @Override
    public int getNumberOfArmies() {
        return d_numberOfArmiesToAdvance;
    }
}
