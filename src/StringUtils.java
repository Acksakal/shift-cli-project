import java.math.BigInteger;

class StringUtils {
    public static boolean isInteger(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        /*
         * This checks if the string has got a decimal point which will also filter out special values
         * like NaN and +-Infinity. It wasn't clearly specified by the task what to do with non-finite numbers,
         * so, I just made an arbitrary decision to remove them cuz those values are not real (R).
         * They are all part of IEEE-754 with Infinity being also part of extended real number line.
         * */
        if (s == null || !s.contains(".")) {
            return false;
        }
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String typeOf(String line) {
        if (isInteger(line)) return "integers";
        if (isDouble(line)) return "floats";
        return "text";
    }

}