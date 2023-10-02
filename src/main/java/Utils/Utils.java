package Utils;

public class Utils {
    /**
     * check if the inout type is integer
     * @param str input string
     * @return true if it is integer,else return false
     */
    public static boolean isInteger(String str) {
        try {
            int intValue = Integer.parseInt(str);
            System.out.println("Conversion successful. Integer value: " + intValue);
            return true;
            // You can now use intValue as an integer
        } catch (NumberFormatException e) {
            System.out.println("Conversion failed. The string is not a valid integer.");
        }
        return false;
    }
}
