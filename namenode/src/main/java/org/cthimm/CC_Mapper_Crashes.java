package org.cthimm;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

import java.text.ParseException;
import java.util.Objects;

public class CC_Mapper_Crashes extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private final ObjectMapper objectMapper = new ObjectMapper();


    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        String line = value.toString();
        String[] fields = FieldExtractionHelper.parseCSVLine(line);

        String crashDate = FieldExtractionHelper.extractField(fields, 0);
        String crashTime = FieldExtractionHelper.extractField(fields, 1);
        String collisionId = FieldExtractionHelper.extractField(fields, 23);

        if (crashDate.equals("CRASH DATE") || collisionId.isEmpty()) {
            // Is in header line or collisionId is missing
            return;
        }

        // Validate crashTime format
        if (!DateTimeFormat.isValidTime(crashTime)) {
            return;
        }

        // Format crashDate
        try {
            crashDate = DateTimeFormat.formatDate(crashDate);
        } catch (ParseException e) {
            return;
        }

        Crashes crashes = getCrashes(fields, collisionId, crashTime, crashDate);

        output.collect(new Text(collisionId), new Text(objectMapper.writeValueAsString(crashes)));

    }

    private static Crashes getCrashes(String[] fields, String collisionId, String crashTime, String crashDate) {
        String borough = FieldExtractionHelper.extractField(fields, 2);
        String zipCode = FieldExtractionHelper.extractField(fields, 3);
        String location = FieldExtractionHelper.extractField(fields, 6);
        String onStreetName = FieldExtractionHelper.extractField(fields, 7);
        String offStreetName = FieldExtractionHelper.extractField(fields, 9);
        String vehicleTypeCode1 = FieldExtractionHelper.extractField(fields, 24);
        String vehicleTypeCode2 = FieldExtractionHelper.extractField(fields, 25);
        String contributingFactorVehicle1 = Objects.equals(FieldExtractionHelper.extractField(fields, 18), "Unspecified") ? null : FieldExtractionHelper.extractField(fields, 18);
        String contributingFactorVehicle2 = Objects.equals(FieldExtractionHelper.extractField(fields, 19), "Unspecified") ? null : FieldExtractionHelper.extractField(fields, 18);
        String numberOfPersonsInjured = FieldExtractionHelper.extractField(fields, 10);
        String numberOfPersonsKilled = FieldExtractionHelper.extractField(fields, 11);

        return new Crashes(collisionId, crashDate, crashTime, borough, zipCode, location, onStreetName, offStreetName, vehicleTypeCode1, vehicleTypeCode2, contributingFactorVehicle1, contributingFactorVehicle2, numberOfPersonsInjured, numberOfPersonsKilled);
    }
}
