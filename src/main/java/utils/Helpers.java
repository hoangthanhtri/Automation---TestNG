package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Helpers {
    public static String randomAlphabets(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static int randomNumbers(int length) {
        return Integer.parseInt(RandomStringUtils.randomNumeric(length));
    }
}
