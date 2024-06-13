package org.cthimm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class CC_Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        List<String> file1Values = new ArrayList<>();
        List<String> file2Values = new ArrayList<>();

        // Separate values into file1 and file2 lists
        while (values.hasNext()) {
            String value = values.next().toString();
            String[] keyParts = key.toString().split("#");

            if (keyParts[1].equals("file1")) {
                file1Values.add(value);
            } else if (keyParts[1].equals("file2")) {
                file2Values.add(value);
            }
        }

        // Process each pair of values (assuming there's exactly one from each file for a given key)
        for (String file1Value : file1Values) {
            for (String file2Value : file2Values) {
                // Combine file1Value and file2Value as JSON objects
                String combinedJson = combineJsonObjects(file1Value, file2Value);

                // Output the combined JSON object with the original key (collision_id)
                output.collect(new Text(key.toString().split("#")[0]), new Text(combinedJson));
            }
        }
    }

    private String combineJsonObjects(String json1, String json2) {
        // Combine json1 and json2 into a single JSON object
        // Remove trailing comma from json1 if present
        if (json1.endsWith(",")) {
            json1 = json1.substring(0, json1.length() - 1);
        }

        // Remove leading comma from json2 if present
        if (json2.startsWith(",")) {
            json2 = json2.substring(1);
        }

        // Combine json1 and json2 into a single JSON object
        return "{" + json1 + "," + json2 + "}";
    }
}
