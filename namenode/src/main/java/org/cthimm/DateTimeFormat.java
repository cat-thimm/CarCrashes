package org.cthimm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class DateTimeFormat {

    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");

    private static final SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd.MM.yyyy");


    public static boolean isValidTime(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }

    public static String formatDate(String date) throws ParseException {
        return outputDateFormat.format(inputDateFormat.parse(date));
    }
}
