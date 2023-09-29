package Order;

import Models.Player;

public class Advance implements IOrders{

    private int numberOfArmiesToDeploy;
    private String targetCountryName ;
    private String targetCountryID ;

    public Advance(int _numberOfArmiesToDeploy,String _targetCountryID, String _targetCountryName) {
        this.numberOfArmiesToDeploy = _numberOfArmiesToDeploy;
        this.targetCountryName = _targetCountryName;
        this.targetCountryID = _targetCountryID;
    }
    @Override
    public void execute(Player player) {

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
}
