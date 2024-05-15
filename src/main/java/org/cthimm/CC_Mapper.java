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
            //String crashDate = fields[2]; take that info from the other table
            // String crashTime = fields[3]; take that info from the other table
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
            // String pedLocation = fields[14]; usually empty - only for pedestrians
            // String pedAction = fields[15]; usually empty - only for pedestrians
            String complaint = fields[16];
            String pedRole = fields[17];
            // String contributingFactor1 = fields[18]; empty or unspecified
            // String contributingFactor2 = fields[19]; empty or unspecified
            String personSex = fields[20];



            // Construct JSON object
            String jsonObject = "{"
                    + "\"unique_id\":\"" + uniqueId + "\","
                    + "\"collision_id\":\"" + collisionId + "\","
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
                    + "\"complaint\":\"" + complaint + "\","
                    + "\"ped_role\":\"" + pedRole + "\","
                    + "\"person_sex\":\"" + personSex + "\""
                    + "}";

            output.collect(new Text(collisionId), new Text(jsonObject));
        }
    }
}
