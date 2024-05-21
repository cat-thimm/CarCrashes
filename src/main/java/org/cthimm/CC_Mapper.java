package org.cthimm;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CC_Mapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length >= 22) {
            String uniqueId = fields[0];
            String collisionId = fields[1];
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
            String complaint = fields[16];
            String pedRole = fields[17];
            String personSex = fields[20];

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

            context.write(new Text(collisionId), new Text(jsonObject));
        }
    }
}
