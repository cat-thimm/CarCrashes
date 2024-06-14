package org.cthimm;

public class FieldExtractionHelper {

    public static String extractField(String[] fields, int index) {
        return fields.length > index ? fields[index] : "";
    }
}
