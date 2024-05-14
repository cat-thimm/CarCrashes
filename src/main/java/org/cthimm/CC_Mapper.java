package org.cthimm;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CC_Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output,
                    Reporter reporter) throws IOException {

        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length >= 22) { // Ensure all necessary fields are present
            String uniqueId = fields[0];
            String collisionId = fields[1];
            String crashDate = fields[2];
            String crashTime = fields[3];
            String personId = fields[4];
            String personType = fields[5];
            String personInjury = fields[6];
            String vehicleId = fields[7];
            String personAge = fields[8];
            String ejection = fields[9];
            String emotionalStatus = fields[10];
            String bodilyInjury = fields[11];
            String positionInVehicle = fields[12];
            String safetyEquipment = fields[13];
            String pedLocation = fields[14];
            String pedAction = fields[15];
            String complaint = fields[16];
            String pedRole = fields[17];
            String contributingFactor1 = fields[18];
            String contributingFactor2 = fields[19];
            String personSex = fields[20];

            // Construct JSON object
            String jsonObject = "{"
                    + "\"unique_id\":\"" + uniqueId + "\","
                    + "\"collision_id\":\"" + collisionId + "\","
                    + "\"crash_date\":\"" + crashDate + "\","
                    + "\"crash_time\":\"" + crashTime + "\","
                    + "\"person_id\":\"" + personId + "\","
                    + "\"person_type\":\"" + personType + "\","
                    + "\"person_injury\":\"" + personInjury + "\","
                    + "\"vehicle_id\":\"" + vehicleId + "\","
                    + "\"person_age\":\"" + personAge + "\","
                    + "\"ejection\":\"" + ejection + "\","
                    + "\"emotional_status\":\"" + emotionalStatus + "\","
                    + "\"bodily_injury\":\"" + bodilyInjury + "\","
                    + "\"position_in_vehicle\":\"" + positionInVehicle + "\","
                    + "\"safety_equipment\":\"" + safetyEquipment + "\","
                    + "\"ped_location\":\"" + pedLocation + "\","
                    + "\"ped_action\":\"" + pedAction + "\","
                    + "\"complaint\":\"" + complaint + "\","
                    + "\"ped_role\":\"" + pedRole + "\","
                    + "\"contributing_factor_1\":\"" + contributingFactor1 + "\","
                    + "\"contributing_factor_2\":\"" + contributingFactor2 + "\","
                    + "\"person_sex\":\"" + personSex + "\""
                    + "}";

            output.collect(new Text(collisionId), new Text(jsonObject));
        }
    }
}
