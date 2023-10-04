package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Utils {
    public static boolean isInteger(String str) {
        try {
            int intValue = Integer.parseInt(str);
//            System.out.println("Conversion successful. Integer value: " + intValue);
            return true;
            // You can now use intValue as an integer
        } catch (NumberFormatException e) {
            System.out.println("Conversion failed. The string is not a valid integer.");
        }
        return false;
    }
}
