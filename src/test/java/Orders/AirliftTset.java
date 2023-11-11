package Orders;

import Cards.Airlift;
import Models.Country;
import Models.IWorldMap;
import Models.Player;
import Models.WorldMap;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirliftTset
{



        private PrintStream console = null;
        private ByteArrayOutputStream bytes = null;

        HashMap<Country, Player> d_countryOwnerMap;
        Player d_test1;
        Player d_test2;
        Country A;
        Country B;
        Country C;
        Country D;
        Country E;
        Country F;
        Country S;
        IWorldMap d_testmap;

        @BeforeEach
        public void setup()
        {
//            d_test1 = new Player("test1");
//            d_test2 = new Player("test2");
//            A = new Country(1, "A", 1);
//            B = new Country(2, "B", 1);
//            C = new Country(3, "C", 1);
//            D = new Country(4, "D", 1);
//            E = new Country(5, "E", 1);
//            F = new Country(6, "F", 1);
//            S = new Country(7, "S", 1);
//            d_countryOwnerMap = new HashMap<>();
//            d_countryOwnerMap.put(A, d_test1);
//            d_countryOwnerMap.put(B, d_test1);
//            d_countryOwnerMap.put(C, d_test1);
//            d_countryOwnerMap.put(D, d_test2);
//            d_countryOwnerMap.put(E, d_test2);
//            d_countryOwnerMap.put(F, d_test2);
//            d_countryOwnerMap.put(S, d_test1);
//            d_testmap = new WorldMap();
//            d_testmap.getCountries().add(A);
//            d_testmap.getCountries().add(B);
//            d_testmap.getCountries().add(C);
//            d_testmap.getCountries().add(D);
//            d_testmap.getCountries().add(E);
//            d_testmap.getCountries().add(F);
//            d_testmap.getCountries().add(S);
//
//            ArrayList<Country> borderA = new ArrayList<>();
//            borderA.add(S);
//            borderA.add(F);
//            d_testmap.addBorder(A, borderA);
//            ArrayList<Country> borderB = new ArrayList<>();
//            borderB.add(S);
//            d_testmap.addBorder(B, borderB);
//            ArrayList<Country> borderC = new ArrayList<>();
//            borderC.add(S);
//            d_testmap.addBorder(C, borderC);
//            ArrayList<Country> borderD = new ArrayList<>();
//            borderD.add(S);
//            borderD.add(E);
//            d_testmap.addBorder(D, borderD);
//            ArrayList<Country> borderE = new ArrayList<>();
//            borderE.add(D);
//            borderE.add(F);
//            d_testmap.addBorder(E, borderE);
//            ArrayList<Country> borderF = new ArrayList<>();
//            borderF.add(A);
//            borderF.add(E);
//            d_testmap.addBorder(F, borderF);
//            ArrayList<Country> borderS = new ArrayList<>();
//            borderS.add(A);
//            borderS.add(B);
//            borderS.add(C);
//            borderS.add(D);
//            d_testmap.addBorder(S, borderS);
//            bytes = new ByteArrayOutputStream();
//            console = System.out;
//            System.setOut(new PrintStream(bytes));

        }

        @After
        public void tearDown()
        {
            System.setOut(console);
        }

        /**
         * through check
         */
        @Test
        void isExistSourceCountry()
        {
            d_test1.getD_orderList().add(new Airlift(5, "H", "G", d_test1, d_countryOwnerMap));

            d_test1.getD_orderList().poll().execute();
            assertEquals("source country is not exist", bytes.toString().substring(0, "source country is not exist".length()));
            d_test1.getD_orderList().add(new Airlift(5, "H", "W", d_test1, d_countryOwnerMap));
            d_test1.getD_orderList().poll().execute();
            assertEquals("source country is not exist", bytes.toString().substring("source country is not exist".length() + 1, "source country is not exist".length() + "source country is not exist".length() + 1));
        }

        @Test
        void isSourceCountryBelongToSourcePlayer()
        {
            d_test1.getD_orderList().add(new Airlift(3, "D", "E", d_test1, d_countryOwnerMap));
            d_test1.getD_orderList().poll().execute();
            assertEquals("Current Country is not belong to Source Player", bytes.toString().substring(0, bytes.toString().length() - 1));
        }

        @Test
        void isSourceCountryhasEnoughArmies()
        {
            d_test1.getD_orderList().add(new Airlift(10, "D", "S", d_test1, d_countryOwnerMap));
            d_test1.getD_orderList().poll().execute();
            assertEquals("We Do not Have Enough Arimes yo Attack", bytes.toString().substring(0, bytes.toString().length() - 1));
        }
    @Test
    void isExistTargetCountry()
    {
        d_test1.getD_orderList().add(new Airlift( 5, "G", "A", d_test1, d_countryOwnerMap));
        d_test1.getD_orderList().poll().execute();
        assertEquals("target country is not exist",bytes.toString().substring(0,"source country is not exist".length()));
        d_test1.getD_orderList().add(new Airlift( 5, "H", "S", d_test1, d_countryOwnerMap));
        d_test1.getD_orderList().poll().execute();
        assertEquals("target country is not exist",bytes.toString().substring("source country is not exist".length()+1,"source country is not exist".length()+"source country is not exist".length()+1));
    }

}
