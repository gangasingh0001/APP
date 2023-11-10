package Strategy;

import Models.Country;
import Models.Player;

public class AggressiveStrategy implements PlayerStrategy {
    @Override
    public void deploy(Player player) {
        Country strongestCountry = findStrongestCountry(player.getCountries());
        int armiesToDeploy = player.getAvailableArmies();
        player.deployArmies(strongestCountry, armiesToDeploy);
    }

    @Override
    public void attack(Player player) {
        Country strongestCountry = findStrongestCountry(player.getCountries());
        List<Country> neighbors = strongestCountry.getNeighbors();

        // Sort neighbors by the number of armies in descending order
        Collections.sort(neighbors, (c1, c2) -> Integer.compare(c2.getArmies(), c1.getArmies()));

        for (Country neighbor : neighbors) {
            if (strongestCountry.canAttack(neighbor)) {
                player.attack(strongestCountry, neighbor);
                // Assuming aggressive strategy attacks only once per turn
                break;
            }
        }
    }

    @Override
    public void moveArmies(Player player) {
        Country strongestCountry = findStrongestCountry(player.getCountries());
        List<Country> connectedCountries = findConnectedCountries(player, strongestCountry);

        // Move armies to the strongest country to maximize aggregation
        for (Country sourceCountry : connectedCountries) {
            if (!sourceCountry.equals(strongestCountry)) {
                int armiesToMove = sourceCountry.getArmies() - 1;  // Keep one army in the source country
                player.moveArmies(sourceCountry, strongestCountry, armiesToMove);
            }
        }
    }

    /**
     *
     */
    @Override
    public void execute() {

    }
}
