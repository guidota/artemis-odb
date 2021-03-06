package com.artemis.generator.util;

/**
 * String utility methods.
 *
 * @author Daan van Yperen
 */
public class Strings {
    public static String decapitalizeString(String string) {
        return string == null || string.isEmpty() ? "" : Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

    public static String capitalizeString(String string) {
        return string == null || string.isEmpty() ? "" : Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }


    /**
     * Returns method name.
     * <p>
     * For alphanumerical or empty prefixes lowercase, otherwise uppercase.
     */
    public static String assembleMethodName(String prefix, String suffix) {
        if (prefix == null || prefix.length() == 0) {
            return decapitalizeString(suffix);
        }

        if (!StringUtils.isAlphanumeric(prefix)) {
            return prefix + decapitalizeString(suffix);
        }

        return prefix + capitalizeString(suffix);
    }
}
