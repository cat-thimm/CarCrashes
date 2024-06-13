package org.cthimm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CC_Mapper_Persons extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private static final int HEADER_LINE_INDEX = 0; // Index of the header line (assuming it's the first line)

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output,
                    Reporter reporter) throws IOException {
        String line = value.toString();

        // Skip the header line
        if (key.get() == HEADER_LINE_INDEX) {
            return;
        }

        String[] fields = parseCSVLine(line);

        if (fields.length >= 20) {
            String collisionId = fields[1];
            String personType = fields[5];
            String personAge = fields[8];
            String emotionalStatus = fields[10];
            String bodilyInjury = fields[11];
            String safetyEquipment = fields[13];
            String personGender = fields[20]; // equals person_sex

            // Data Cleaning: Check person's age between 0 and 125
            if (isValidAge(personAge)) {
                // Construct JSON object
                String jsonObject = "{" +
                        "\"type\":\"" + personType + "\"," +
                        "\"age\":\"" + personAge + "\"," +
                        "\"emotional_status\":\"" + emotionalStatus + "\"," +
                        "\"bodily_injury\":\"" + bodilyInjury + "\"," +
                        "\"safety_equipment\":\"" + safetyEquipment + "\"," +
                        "\"gender\":\"" + personGender + "\"" +
                        "}";

                // Output uniqueId as key and JSON object as value
                output.collect(new Text(collisionId), new Text(jsonObject));
            }
        }
    }

    private boolean isValidAge(String personAge) {
        try {
            int age = Integer.parseInt(personAge);
            return (age >= 0 && age <= 125);
        } catch (NumberFormatException e) {
            return false; // Handle cases where age is not a valid integer
        }
    }

    private String[] parseCSVLine(String line) {
        // This method assumes that fields are separated by commas and fields may be enclosed in double quotes
        // Handle quoted fields and commas within quotes properly
        boolean insideQuote = false;
        StringBuilder field = new StringBuilder();
        List<String> fields = new ArrayList<>();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                insideQuote = !insideQuote; // Toggle insideQuote flag
            } else if (c == ',' && !insideQuote) {
                // Comma outside quotes indicates end of a field
                fields.add(field.toString());
                field.setLength(0); // Reset the field buffer
            } else {
                field.append(c); // Add character to current field
            }
        }
        // Add the last field
        fields.add(field.toString());

        return fields.toArray(new String[0]);
    }
}

