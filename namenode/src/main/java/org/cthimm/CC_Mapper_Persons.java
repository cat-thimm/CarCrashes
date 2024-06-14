package org.cthimm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CC_Mapper_Persons extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private static final int HEADER_LINE_INDEX = 0; // Index of the header line (assuming it's the first line)
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output,
                    Reporter reporter) throws IOException {
        String line = value.toString();

        // Skip the header line
        if (key.get() == HEADER_LINE_INDEX) {
            return;
        }

        String[] fields = parseCSVLine(line);
        String collisionId = FieldExtractionHelper.extractField(fields, 1);
        String personType =   FieldExtractionHelper.extractField(fields, 5);
        if (collisionId == null || personType == null) {
            throw new RuntimeException(new IllegalArgumentException(Arrays.toString(fields)));
        }
        String personAge =   FieldExtractionHelper.extractField(fields, 8);
        String emotionalStatus =   FieldExtractionHelper.extractField(fields, 10);
        String bodilyInjury =   FieldExtractionHelper.extractField(fields, 11);
        String safetyEquipment =   FieldExtractionHelper.extractField(fields, 13);
        String personGender =   FieldExtractionHelper.extractField(fields, 20); // equals person_sex

        // Data Cleaning: Check person's age between 0 and 125
        if (isValidAge(personAge)) {

            // Output uniqueId as key and Person obj as value
            Person v = new Person(personType, personAge, emotionalStatus, bodilyInjury, safetyEquipment, personGender);
            output.collect(new Text(collisionId), new Text(objectMapper.writeValueAsString(v)));
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

