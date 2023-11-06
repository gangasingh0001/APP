package Orders;

import Models.Country;
import Models.Player;
import Models.WorldMap;
import Services.CountryService;

import java.util.Random;

/**
 * This is the advance class which implements IOrders interface
 */
public class Advance implements IOrders{

<<<<<<< HEAD
    private int d_numberOfArmiesToAdvance;
    private String d_targetCountryName ;
    private String d_sourceConuntryName;
    private Player d_SourcePlayer;
    private WorldMap d_myWordMap;
    private Country d_sourceCountry;
    private Country d_targetCountry;

    public Advance(int p_numberOfArmiesToDeploy, String p_targetCountryName, String p_sourceConuntryName, Player p_sourcePlayer, WorldMap p_temp)
    {
        d_numberOfArmiesToAdvance = p_numberOfArmiesToDeploy;
        d_targetCountryName = p_targetCountryName;
        d_sourceConuntryName = p_sourceConuntryName;
        d_SourcePlayer = p_sourcePlayer;
        d_myWordMap = p_temp;
    }
    /**
     * number of armies assigned each term
     */
    private int d_numberOfArmiesToDeploy;

    /**
     * target country name
     */
    private String d_targetCountryName ;

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
     * @param p_player parameter of player object
     */
    @Override
    public void execute(Player p_player) {

        if (valid())
        {
            int l_temp=d_sourceCountry.getD_Armies();
            d_sourceCountry.setD_Armies(l_temp-this.d_numberOfArmiesToAdvance);
            if (d_targetCountry.getD_ownedBy().equals(d_SourcePlayer))
            {
                 l_temp=d_targetCountry.getD_Armies();
                 d_targetCountry.setD_Armies(l_temp+this.d_numberOfArmiesToAdvance);
            } else
            {
                while (this.d_numberOfArmiesToAdvance != 0 && d_targetCountry.getD_Armies() != 0)
                {
                    Random random = new Random();
                    int l_attcakRandom = random.nextInt(0, 10);
                    int l_defenceRandom = random.nextInt(0, 10);
                    if (l_attcakRandom <= 5) this.d_numberOfArmiesToAdvance--;
                     l_temp = d_targetCountry.getD_Armies();
                    if (l_defenceRandom <= 4) d_targetCountry.setD_Armies(l_temp-1);
                }
                if (d_targetCountry.getD_Armies() == 0&&this.d_numberOfArmiesToAdvance!=0)
                {
                    d_SourcePlayer.getD_coutriesOwned().add(d_targetCountry);
                    d_targetCountry.getD_ownedBy().getD_coutriesOwned().remove(d_sourceCountry);
                    d_targetCountry.setD_ownedBy(d_SourcePlayer);
                    d_targetCountry.setD_Armies(this.d_numberOfArmiesToAdvance);
                }
            }
        }
    }
    /**
     * boolean method to check the game state
     * @param p_gameState show the states of game
     * @return
     */
    @Override
    public boolean valid() {
        for(Country i:d_myWordMap.getCountries())
        {
            if (d_sourceConuntryName==i.getName()) {d_sourceCountry=i;break;}
            System.out.println("Source Country Name is not exist");
            return  false;
        }
     for(Country i:d_myWordMap.getCountries())
     {
         if (d_targetCountryName==i.getName()) {d_targetCountry=i;break;}
         System.out.println("Target Country Name is not exist");
         return false;
     }
     if(!d_myWordMap.getNeighborsOfCountry(d_targetCountry).contains(d_sourceCountry))
     {
         System.out.println("Two countries is not connected");
     }
     if(d_sourceCountry.getD_ownedBy().equals(d_SourcePlayer)&&this.d_numberOfArmiesToAdvance<=d_sourceCountry.getD_Armies()-1)
     {
         return true;
     }
     else if (!d_sourceCountry.getD_ownedBy().equals(d_SourcePlayer))
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
