package org.cthimm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FieldExtractionHelper {

    public static String extractField(String[] fields, int index) {
        return fields.length > index ? fields[index] : "";
    }

    public static String[] parseCSVLine(String line) {
        // This method assumes that fields are separated by commas and fields may be enclosed in double quotes
        // Handle quoted fields and commas within quotes properly
        boolean insideQuote = false;
        StringBuilder field = new StringBuilder();
        List<String> fields = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                insideQuote = !insideQuote; // Toggle insideQuote flag
            } else if (c == ',' && !insideQuote) {
                // Comma outside quotes indicates end of a field
                fields.add(field.toString().trim());
                field.setLength(0); // Reset the field buffer
            } else {
                field.append(c); // Add character to current field
            }
        }
        // Add the last field
        fields.add(field.toString().trim());

        return fields.toArray(new String[0]);
    }

    public static Integer parseToInteger(String field) {
        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Date parseDate(String field) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(field, formatter);
            return java.sql.Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
