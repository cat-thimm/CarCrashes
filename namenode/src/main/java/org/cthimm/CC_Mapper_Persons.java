package org.cthimm;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CC_Mapper_Persons extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final long HEADER_LINE_INDEX = 0L; // Index of the header line (assuming it's the first line)

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output,
                    Reporter reporter) throws IOException {
        // Skip the header line
        if (key.get() == HEADER_LINE_INDEX) {
            return;
        }

        String[] fields = FieldExtractionHelper.parseCSVLine(value.toString());

        // Extract fields with checks for null or empty values
        String collisionId = FieldExtractionHelper.extractField(fields, 1);
        String personType = FieldExtractionHelper.extractField(fields, 5);
        String personAge = FieldExtractionHelper.extractField(fields, 8);
        String emotionalStatus = FieldExtractionHelper.extractField(fields, 10);
        String bodilyInjury = FieldExtractionHelper.extractField(fields, 11);
        String safetyEquipment = FieldExtractionHelper.extractField(fields, 13);
        String personGender = FieldExtractionHelper.extractField(fields, 20);

        // Data Cleaning: Check person's age between 0 and 125
        if (isValidAge(personAge)) {
            // Create Person object with null checks
            Person person = new Person(
                    personType != null ? personType : "",
                    personAge != null ? personAge : "",
                    emotionalStatus != null ? emotionalStatus : "",
                    bodilyInjury != null ? bodilyInjury : "",
                    safetyEquipment != null ? safetyEquipment : "",
                    personGender != null ? personGender : ""
            );

            // Output collisionId as key and Person object as value
            output.collect(new Text(collisionId), new Text(objectMapper.writeValueAsString(person)));
        }
    }

    private boolean isValidAge(String personAge) {
        try {
            int age = Integer.parseInt(personAge);
            return (age >= 0 && age <= 114);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
