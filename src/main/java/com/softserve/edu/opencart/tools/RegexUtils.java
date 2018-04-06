package com.softserve.edu.opencart.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {

    private static final String LIST_TO_STRING_REMOVER = "[\\[ \\]]";
    private static final String DOUBLE_UI_SEPARATOR = ",";
    private static final String PATTERN_UNSIGNED_NUMBER = "\\d+";
    private static final String PATTERN_UNSIGNED_DOUBLE = "\\d+(,\\d+)*\\.\\d+";
//    private static final String PATTERN_UNSIGNED_DOUBLE = "\\d+\\.\\d+";
    private static final String EXTRACT_NUMBER_MESSAGE = "NumberFormatException for pattern =  %s text =  %s";
    private static final String PATTERN_WORDS = "[a-zA-Z_,]+";
    private static final String PATTERN_SUCCESFULL_MESSAGE = "Success:.+!";

    private RegexUtils() {
    }


    public static boolean isWords(String text) {
        return isTextMatches(PATTERN_WORDS,listTrim(text));
    }

    public static boolean isTextMatches(String pattern, String text) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        return matcher.matches();
    }

    public static String extractFirstString(String pattern, String text) {
        String result = "";
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            result = text.substring(matcher.start(), matcher.end());
        }
        return result;
    }

    public static int extractFirstNumber(String text) {
        int result = -1;
        String extractText = extractFirstString(PATTERN_UNSIGNED_NUMBER, text);
        if (!extractText.isEmpty()) {
            try {
                result = Integer.parseUnsignedInt(extractText);

            } catch (NumberFormatException e) {
                // TODO Develop Custom Exception
                throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_NUMBER, text));
            }
        }
        return result;
    }



    public static double extractFirstDouble(String text) {
        double result = -1;
        String extractText = extractFirstString(PATTERN_UNSIGNED_DOUBLE, text);
        if (!extractText.isEmpty()) {
            try {
//                result = Double.parseDouble(extractText);
                result = Double.parseDouble(commaRemover(extractText));

            } catch (NumberFormatException e) {
                // TODO Develop Custom Exception
                throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_DOUBLE, text));
            }
        }
        return result;
    }

    public static boolean isDoubleMatches(String text) {
        return isTextMatches(PATTERN_UNSIGNED_DOUBLE, text);
    }

    public static String extractPathWithoutServer(String pattern, String text) {
        String result = text;
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            result = text.substring(matcher.end() - 1);
        }
        return result;
    }

    public static String extractSuccesfullMessage(String text) {
        String result = text;
        Matcher matcher = Pattern.compile(PATTERN_SUCCESFULL_MESSAGE).matcher(text);
        if (matcher.find()) {
            result = text.substring(matcher.start(), matcher.end());
        }
        return result;
    }

    private static String commaRemover(String text) {
        return text.replaceAll(DOUBLE_UI_SEPARATOR, "");
    }

    private static String listTrim(String text) {
        return text.replaceAll(LIST_TO_STRING_REMOVER, "");
    }
}
