package Services;

import Models.Continent;
import Models.IWorldMap;
import Utils.Commands;

import java.util.ArrayList;

public class ContinentService implements IContinentService{
    IMapService mapService;
    IWorldMap worldMap;
    private ArrayList<Continent> continents = new ArrayList<>();
    
    public ContinentService(IMapService _mapService,IWorldMap _worldMap) {
        mapService = _mapService;
        worldMap = _worldMap;
    }

    public ArrayList<Continent> getContinentList(){
        return continents;
    }
    public boolean isContinentRemoved(Commands p_commands){
        return true;
    }


    public void addContinent(Continent p_continent){

        // Country country = new Country(p_continent.getContinentId())
        // if (d_countries!=null){
        //     d_countries.add(p_continent);
        // }
        // else{
        //     d_countries=new ArrayList<Country>();
        //     d_countries.add(p_continent);
        // }
    }

   /**
    * removes Country from Continent.
    *
    * @param p_continent country to be removed
    */
   public void removeContinents(Continent p_continent){
    //    if(d_countries==null){
    //        System.out.println("No such Continent Exists");
    //    }else {
    //        d_countries.remove(p_continent);
    //    }
   }
//
//    /**
//     * Removes particular Continent ID from the neighbor list of all countries in continent.
//     *
//     * @param p_continentId ID of Continent to be removed
//     */
//    public void removeCountryNeighboursFromAll(Integer p_continentId){
//        if (null!=d_countries && !d_countries.isEmpty()) {
//            for (Country c: d_countries){
//                if (!CommonUtil.isNull(c.d_adjacentCountryIds)) {
//                    if (c.get_adjacentCountryIds().contains(p_continentId)){
//                        c.removeNeighbour(p_continentId);
//                    }
//                }
//            }
//        }
//    }
}
