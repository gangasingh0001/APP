package Services;

import Models.Country;

import java.util.ArrayList;

public class ContinentService implements IContinentService{
    @Override
    public void addCountry(Country p_country) {

    }
//    public void addCountry(Country p_country){
//        if (d_countries!=null){
//            d_countries.add(p_country);
//        }
//        else{
//            d_countries=new ArrayList<Country>();
//            d_countries.add(p_country);
//        }
//    }
//
//    /**
//     * removes Country from Continent.
//     *
//     * @param p_country country to be removed
//     */
//    public void removeCountry(Country p_country){
//        if(d_countries==null){
//            System.out.println("No such Country Exists");
//        }else {
//            d_countries.remove(p_country);
//        }
//    }
//
//    /**
//     * Removes particular country ID from the neighbor list of all countries in continent.
//     *
//     * @param p_countryId ID of country to be removed
//     */
//    public void removeCountryNeighboursFromAll(Integer p_countryId){
//        if (null!=d_countries && !d_countries.isEmpty()) {
//            for (Country c: d_countries){
//                if (!CommonUtil.isNull(c.d_adjacentCountryIds)) {
//                    if (c.get_adjacentCountryIds().contains(p_countryId)){
//                        c.removeNeighbour(p_countryId);
//                    }
//                }
//            }
//        }
//    }
}
