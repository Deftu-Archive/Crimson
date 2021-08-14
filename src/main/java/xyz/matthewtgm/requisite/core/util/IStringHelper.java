package xyz.matthewtgm.requisite.core.util;

public interface IStringHelper {
    default String getLongest(String[] strings) {
        String longestString = "";
        int longest = 0;
        for (Object o : strings) {
            String string = (String) o;
            if (string.length() > longest) {
                longestString = string;
                longest = string.length();
            }
        }
        return longestString;
    }
    default int getLongestWidth(String[] strings) {
        return getLongest(strings).length();
    }

    String removeFormattingCodes(String input);
}