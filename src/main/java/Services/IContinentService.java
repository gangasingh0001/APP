package Services;

import Models.Country;

/**
 * interface for ContinentService
 */
public interface IContinentService {
    /**
     *  add country to current continent
     * @param p_country the country is added to this continent
     */
    public void addCountry(Country p_country);
}
