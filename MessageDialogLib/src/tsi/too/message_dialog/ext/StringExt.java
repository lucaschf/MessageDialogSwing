package tsi.too.message_dialog.ext;

import java.math.BigDecimal;

public abstract class StringExt {

    public static String removeLastChar (final String source) {
        return source.replaceFirst(".$", "");
    }

    /**
     * Parses the string as a [long] number and returns the result.
     * @param str the string to be parsed.
     * @return the long value represented by the string or zero if the string cannot be parsed.
     */
    public static long toLong(final String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Parses the string as a [Int] number and returns the result.
     * @param str the string to be parsed.
     * @return the int value represented by the string or zero if the string cannot be parsed.
     */
    public static int toInt(final String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Parses the string as a [Double] number and returns the result.
     * @param str the string to be parsed.
     * @return the double value represented by the string or zero if the string cannot be parsed.
     */
    public static double toDouble(final String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Parses the string as a [BigDecimal] number and returns the result.
     * @param str the string to be parsed.
     *
     * @return the double value represented by the string or zero if the string cannot be parsed.
     */
    public static BigDecimal toBigDecimal(final String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException ex) {
            return BigDecimal.ZERO;
        }
    }
}