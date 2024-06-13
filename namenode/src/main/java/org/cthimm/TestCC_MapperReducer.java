package org.cthimm;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestCC_MapperReducer {
    public static void main(String[] args) throws IOException {
        // Mapper Test
        CC_Mapper_Persons mapper = new CC_Mapper_Persons();
        Text value = new Text("UNIQUE_ID,COLLISION_ID,CRASH_DATE,CRASH_TIME,PERSON_ID,PERSON_TYPE,PERSON_INJURY,VEHICLE_ID,PERSON_AGE,EJECTION,EMOTIONAL_STATUS,BODILY_INJURY,POSITION_IN_VEHICLE,SAFETY_EQUIPMENT,PED_LOCATION,PED_ACTION,COMPLAINT,PED_ROLE,CONTRIBUTING_FACTOR_1,CONTRIBUTING_FACTOR_2,PERSON_SEX\n" +
                "10249006,4229554,10/26/2019,9:43,31aa2bc0-f545-444f-8cdb-f1cb5cf00b89,Occupant,Unspecified,19141108,,,,,,,,,,Registrant,,,U\n" +
                "10255054,4230587,10/25/2019,15:15,4629e500-a73e-48dc-b8fb-53124d124b80,Occupant,Unspecified,19144075,33,Not Ejected,Does Not Apply,Does Not Apply,\"Front passenger, if two or more persons, including the driver, are in the front seat\",Lap Belt & Harness,,,Does Not Apply,Passenger,,,F\n" +
                "10253177,4230550,10/26/2019,17:55,ae48c136-1383-45db-83f4-2a5eecfb7cff,Occupant,Unspecified,19143133,55,,,,,,,,,Registrant,,,M\n" +
                "6650180,3565527,11/21/2016,13:05,2782525,Occupant,Unspecified,,,,,,,,,,,Notified Person,,,"); // Example CSV line with at least 22 fields
        OutputCollector<Text, Text> mapOutput = new OutputCollector<Text, Text>() {
            @Override
            public void collect(Text key, Text value) throws IOException {
                System.out.println("Mapper Output -> Key: " + key + ", Value: " + value);
            }
        };
        mapper.map(new LongWritable(1), value, mapOutput, null);

        // Reducer Test
        CC_Reducer reducer = new CC_Reducer();
        List<Text> valueList = Arrays.asList(
                new Text("{\"unique_id\":\"5821571\",\"collision_id\":\"3427222\",\"person_type\":\"Occupant\",\"age\":\"53\",\"emotional_status\":\"\",\"bodily_injury\":\"\",\"safety_equipment\":\"\",\"gender\":\"F\"}"),
                new Text("{\"unique_id\":\"5821570\",\"collision_id\":\"3427222\",\"person_type\":\"Occupant\",\"age\":\"52\",\"emotional_status\":\"\",\"bodily_injury\":\"\",\"safety_equipment\":\"\",\"gender\":\"M\"}")
        );
        Iterator<Text> values = valueList.iterator();
        OutputCollector<Text, Text> reduceOutput = new OutputCollector<Text, Text>() {
            @Override
            public void collect(Text key, Text value) throws IOException {
                System.out.println("Reducer Output -> Key: " + key + ", Value: " + value);
            }
        };
        reducer.reduce(new Text("3427222"), values, reduceOutput, null);
    }
}
