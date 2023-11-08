package Utils;

import Models.Country;
import Models.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * a class used to check if the input String is integer
 */
public class Utils {
    /**
     * check if the inout type is integer
     * @param str input string
     * @return true if it is integer,else return false
     */
    public static boolean isInteger(String str) {
        try {
            int intValue = Integer.parseInt(str);
            // System.out.println("Conversion successful. Integer value: " + intValue);
            return true;
            // You can now use intValue as an integer
        } catch (NumberFormatException e) {
            System.out.println("Conversion failed. The string is not a valid integer.");
        }
        return false;
    }

//    public static String readCommand(Player player) {
//        BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
//        String l_commandEntered = null;
//        try {
//            System.out.println(player.getD_playerName() + ": Please enter Deploy order or type 'exit' to quit");
//            return l_commandEntered = l_reader.readLine();
//        } catch (IOException l_ioException) {
//            l_ioException.printStackTrace();
//        }
//        return null;
//    }
}
