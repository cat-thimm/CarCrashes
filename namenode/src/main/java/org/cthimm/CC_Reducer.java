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

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, NullWritable> output, Reporter reporter) throws IOException {
        StringBuilder combinedJson = new StringBuilder();

        while (values.hasNext()) {
            String value = values.next().toString();

            combinedJson.append(value);

            if (values.hasNext()) {
                combinedJson.append(",");
            }
        }

        output.collect(new Text(combinedJson.toString()), NullWritable.get());
    }
}
