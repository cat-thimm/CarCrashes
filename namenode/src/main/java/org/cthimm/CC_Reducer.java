package org.cthimm;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CC_Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Map<String, String> combinedValues = new HashMap<>();

        StringBuilder combinedJson = new StringBuilder("{");


        while (values.hasNext()) {

            String value = values.next().toString();
            // Append value to combinedJson (assuming each value is a JSON object)
            combinedJson.append(value.substring(1, value.length() - 1));
            if (values.hasNext()) {
                combinedJson.append(",");
            }
        }

        combinedJson.append("}");

        // Output the combined JSON object with the original key (collision_id)
        output.collect(key, new Text(combinedJson.toString()));
    }
}
