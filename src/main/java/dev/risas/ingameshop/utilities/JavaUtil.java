package dev.risas.ingameshop.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JavaUtil {

    public Integer tryParseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
