package org.cthimm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.*;

public class CC_Reducer extends MapReduceBase implements Reducer<Text, Text, Text, NullWritable> {
    private boolean start = true;
    private ObjectMapper mapper = new ObjectMapper();

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, NullWritable> output, Reporter reporter) throws IOException {
        boolean hasCrashTime = false;
        Map<String, Object> crashDetails = new HashMap<>();
        Set<Person> uniquePersons = new HashSet<>(); // Filter duplicates by not adding them to the map

        while (values.hasNext()) {
            String value = values.next().toString();
            Map<String, Object> valueMap = mapper.readValue(value, Map.class);

            if (valueMap.containsKey("crashTime")) {
                crashDetails.putAll(valueMap);
                hasCrashTime = true;
            } else if (valueMap.containsKey("age")) {
                Person person = mapper.convertValue(valueMap, Person.class);
                uniquePersons.add(person);  // Add to set, duplicates will be ignored
            }
        }

        if (!hasCrashTime) {
            crashDetails.putAll(mapper.convertValue(new Crashes(), Map.class));
        }

        crashDetails.put("persons", new ArrayList<>(uniquePersons));

        String combinedJson = mapper.writeValueAsString(crashDetails);

        if (start) {
            combinedJson = "[" + combinedJson;
            start = false;
        } else {
            combinedJson = "," + combinedJson;
        }

        output.collect(new Text(combinedJson), NullWritable.get());
    }
}
