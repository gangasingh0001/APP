package Order;

import Models.Country;
import Models.Player;

public class OrdreInformation
{
    /**
     * Command typed by player
     */
    private String d_Command;
    /**
     * The player operating this order
     */
    private Player d_palyer;
    /**
     * The country we will assign armies to
     */
    private Country d_TargetContry;
    /**
     * The number of armies will be ued
     */
    private int d_NumberArmies;

    /**
     * Setter for d_Command
     *
     * @param p_Command the command entered by player
     */
    public void setD_Command(String p_Command)
    {
        this.d_Command = p_Command;
    }


}
