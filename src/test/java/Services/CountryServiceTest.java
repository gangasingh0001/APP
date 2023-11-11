package Services;

import Models.*;
import Utils.Commands;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest
{
    IMapService d_mapService;
    IWorldMap d_worldMap;
    ICountryService d_countryService;
    Commands d_command;
    @BeforeEach
    public void setup()
    {
        d_command=new Commands("loadmap google.map");
        d_worldMap=new WorldMap();
        d_mapService=new MapService(d_worldMap);
//        try
//        {
//           // d_mapService.loadData(d_command);
//        } catch (FileNotFoundException e)
//        {
//            throw new RuntimeException(e);
//        }
        d_countryService=new CountryService(d_mapService,d_worldMap);
    }
    @Test
    void getCountryList()
    {

        List<Country> l_countries = new ArrayList<>();
        String l_storeAllCountries="1 Portugal 1 57 305\n" +
                "        2 Spanien 1 114 312\n" +
                "        3 Frankreich 1 193 206\n" +
                "        4 Gross_Britanien 1 179 104\n" +
                "        5 Irland 1 141 131\n" +
                "        6 Deutschland 2 276 162\n" +
                "        7 Niederlande 2 246 142\n" +
                "        8 Belgien 2 229 176\n" +
                "        9 Luxemburg 2 239 186\n" +
                "        10 Schweiz 2 255 216\n" +
                "        11 Oesterreich 2 320 216\n" +
                "        12 Italien 2 307 285\n" +
                "        13 Daenemark 3 280 110\n" +
                "        14 Schweden 3 333 74\n" +
                "        15 Norwegen 3 273 63\n" +
                "        16 Finnland 3 397 51\n" +
                "        17 Estland 3 402 84\n" +
                "        18 Lettland 3 379 99\n" +
                "        19 Litauen 3 406 114\n" +
                "        20 Kaliningrad 3 370 119\n" +
                "        21 Polen 4 362 151\n" +
                "        22 Weissrussland 4 442 126\n" +
                "        23 Ukraine 4 504 181\n" +
                "        24 Tschechien 4 328 177\n" +
                "        25 Slowakei 4 372 189\n" +
                "        26 Ungarn 4 369 214\n" +
                "        27 Rumaenien 4 434 227\n" +
                "        28 Moldawien 4 467 208\n" +
                "        29 Slowenien 5 324 226\n" +
                "        30 Kroatien 5 332 254\n" +
                "        31 Bosnien_Herzegowina 5 359 252\n" +
                "        32 Serbien 5 396 256\n" +
                "        33 Montenegro 5 373 276\n" +
                "        34 Bulgarien 5 464 274\n" +
                "        35 Mazedonien 5 409 292\n" +
                "        36 Albanien 5 389 317\n" +
                "        37 Griechenland 5 432 347";
        String []l_ArrStoreAllCountries=l_storeAllCountries.split("\n");
        for(int i=0; i<l_ArrStoreAllCountries.length;i++)
        {
            String l_test=l_ArrStoreAllCountries[i].trim();
            String [] l_arrForOneCountry=l_test.split(" ");
            l_countries.add(new Country(Integer.parseInt(String.valueOf(l_arrForOneCountry[0])),l_arrForOneCountry[1],Integer.parseInt(String.valueOf(l_arrForOneCountry[2]))));
        }

      assertEquals(d_countryService.getCountryList().toString(),l_countries.toString());
    }

    @Test
    void addCountry()
    {
        Commands l_addCountry_1=new Commands("editcountry -add England 1");
        l_addCountry_1.validateCommand();
        d_countryService.addCountry(l_addCountry_1);
        assertEquals(38,d_countryService.getCountryList().size());
        Commands l_addCountry_2=new Commands("editcountry -add French 2");
        l_addCountry_2.validateCommand();
        d_countryService.addCountry(l_addCountry_2);
        assertEquals(39,d_countryService.getCountryList().size());
    }

    @Test
    void isCountryRemoved()
    {
        Commands l_removeCountry=new Commands("editcountry -remove England");
        l_removeCountry.validateCommand();
        assertEquals(false,d_countryService.isCountryRemoved(l_removeCountry));
        Commands l_removeCountry_2=new Commands("editcountry -remove Schweiz");
        l_removeCountry_2.validateCommand();
        assertEquals(true,d_countryService.isCountryRemoved(l_removeCountry_2));
        assertEquals(36,d_countryService.getCountryList().size());
    }

}