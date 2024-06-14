package org.cthimm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CC_Mapper_Crashes extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private static final int CRASH_DATE_INDEX = 0;
    private static final int CRASH_TIME_INDEX = 1;
    private static final int BOROUGH_INDEX = 2;
    private static final int ZIP_CODE_INDEX = 3;
    private static final int LATITUDE_INDEX = 4;
    private static final int LONGITUDE_INDEX = 5;
    private static final int LOCATION_INDEX = 6;
    private static final int ON_STREET_NAME_INDEX = 7;
    private static final int OFF_STREET_NAME_INDEX = 9;
    private static final int VEHICLE_TYPE_CODE_1_INDEX = 24;
    private static final int VEHICLE_TYPE_CODE_2_INDEX = 25;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final int HEADER_LINE_INDEX = 0; // Index of the header line (assuming it's the first line)

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        if (key.get() == HEADER_LINE_INDEX) {
            return;
        }
        String line = value.toString();
        String[] fields = parseCSVLine(line);

        String crashDate = FieldExtractionHelper.extractField(fields, CRASH_DATE_INDEX);
        String crashTime = FieldExtractionHelper.extractField(fields, CRASH_TIME_INDEX);
        String borough = FieldExtractionHelper.extractField(fields, BOROUGH_INDEX);
        String zipCode = FieldExtractionHelper.extractField(fields, ZIP_CODE_INDEX);
        String location = FieldExtractionHelper.extractField(fields, LOCATION_INDEX);
        String onStreetName = FieldExtractionHelper.extractField(fields, ON_STREET_NAME_INDEX).trim();
        String offStreetName = FieldExtractionHelper.extractField(fields, OFF_STREET_NAME_INDEX).trim();
        String vehicleTypeCode1 = FieldExtractionHelper.extractField(fields, VEHICLE_TYPE_CODE_1_INDEX);
        String vehicleTypeCode2 = FieldExtractionHelper.extractField(fields, VEHICLE_TYPE_CODE_2_INDEX);
        String collisionId = FieldExtractionHelper.extractField(fields, 23);


        Crashes crashes = new Crashes(crashDate, crashTime, borough, zipCode, location, onStreetName, offStreetName, vehicleTypeCode1, vehicleTypeCode2);
        output.collect(new Text(collisionId), new Text(objectMapper.writeValueAsString(crashes)));
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
