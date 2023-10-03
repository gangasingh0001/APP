package Orders;

import Models.Country;
import Models.Player;

public class Deploy implements IOrders{
    private int numberOfArmiesToDeploy;
    private String targetCountryName ;
    private String targetCountryID ;

    public Deploy(int _numberOfArmiesToDeploy,String _targetCountryID, String _targetCountryName) {
        this.numberOfArmiesToDeploy = _numberOfArmiesToDeploy;
        this.targetCountryName = _targetCountryName;
        this.targetCountryID = _targetCountryID;
    }
    @Override
    public void execute(Player player) {
        while (!player.getD_orderList().isEmpty()) {
            IOrders deployObj = player.getD_orderList().poll();
            for(Country country: player.getD_coutriesOwned()) {
                assert deployObj != null;
                if(country.getName().equals(deployObj.getTargetCountryName())) {
                    country.setD_Armies(country.getD_Armies()+deployObj.getNumberOfArmies());
                    break;
                }
            }
        }
    }

    @Override
    public boolean valid(int p_gameState) {
        return false;
    }

    @Override
    public void printOrder() {

    }

    @Override
    public String getOrderName() {
        return null;
    }

    public String getTargetCountryName() {
        return targetCountryName;
    }

    public String getTargetCountryID() {
        return targetCountryID;
    }

    public int getNumberOfArmies() {
        return numberOfArmiesToDeploy;
    }
}
