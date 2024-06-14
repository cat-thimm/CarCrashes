package org.cthimm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class CC_Reducer extends MapReduceBase implements Reducer<Text, Text, Text, NullWritable> {
    private boolean start = true;
    private ObjectMapper mapper = new ObjectMapper();

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, NullWritable> output, Reporter reporter) throws IOException {
        StringBuilder combinedJson = new StringBuilder();

        if (this.start) {
            combinedJson.append("[");
            this.start = false;
        }
        combinedJson.append("{");

        while (values.hasNext()) {
            String value = values.next().toString().replace("{","").replace("}","");

            combinedJson.append(value);
            combinedJson.append(","); // todo comma checkne
       }

        if (combinedJson.indexOf("crashTime") == -1) {
            combinedJson.append(mapper.writeValueAsString(new Crashes()).replace("{","").replace("}",""));
        } else if (combinedJson.indexOf("age") == -1) {
            combinedJson.append(mapper.writeValueAsString(new Person()).replace("{","").replace("}",""));
        }

        combinedJson.append("}");
        combinedJson.append(",");

        output.collect(new Text(combinedJson.toString()), NullWritable.get());
    }
}
