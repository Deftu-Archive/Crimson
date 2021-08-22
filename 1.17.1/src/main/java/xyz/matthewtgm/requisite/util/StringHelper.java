package xyz.matthewtgm.requisite.util;

import xyz.matthewtgm.requisite.core.util.IStringHelper;

import java.util.regex.Pattern;

public class StringHelper implements IStringHelper {

    private final Pattern formattingCodePattern = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public String removeFormattingCodes(String input) {
        return formattingCodePattern.matcher(input).replaceAll("");
    }

    public Pattern getFormattingCodePattern() {
        return formattingCodePattern;
    }

}