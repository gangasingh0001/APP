package Strategy;

import Models.Country;
import Models.Player;
import Orders.IOrders;
import Services.PlayerService;

public class AggressiveStrategy implements PlayerStrategy {

    //Player d_player;
    PlayerService d_playerService;
    public  AggressiveStrategy(PlayerService l_playerService){
        //this.d_player=l_player;
        this.d_playerService=l_playerService;

    }

    @Override
    public void deploy(Player player) {
//        Country strongestCountry = findStrongestCountry(player.getCountries());
//        int armiesToDeploy = player.getD_numberOfArmies();
//        player.deployArmies(strongestCountry, armiesToDeploy);
        //return null;
    }

    @Override
    public void attack(Player player) {
//        Country strongestCountry = findStrongestCountry(player.getCountries());
//        List<Country> neighbors = strongestCountry.getNeighbors();
//
//        // Sort neighbors by the number of armies in descending order
//        Collections.sort(neighbors, (c1, c2) -> Integer.compare(c2.getArmies(), c1.getArmies()));
//
//        for (Country neighbor : neighbors) {
//            if (strongestCountry.canAttack(neighbor)) {
//                player.attack(strongestCountry, neighbor);
//                // Assuming aggressive strategy attacks only once per turn
//                break;
//            }
//        }
        //return null;
    }

    @Override
    public void moveArmies(Player player) {
//        Country strongestCountry = findStrongestCountry(player.getCountries());
//        List<Country> connectedCountries = findConnectedCountries(player, strongestCountry);
//
//        // Move armies to the strongest country to maximize aggregation
//        for (Country sourceCountry : connectedCountries) {
//            if (!sourceCountry.equals(strongestCountry)) {
//                int armiesToMove = sourceCountry.getArmies() - 1;  // Keep one army in the source country
//                player.moveArmies(sourceCountry, strongestCountry, armiesToMove);
//            }
//        }
        //return null;
    }

    @Override
    public void execute(Player player)
    {

    }




}
