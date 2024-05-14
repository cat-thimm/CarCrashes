package org.cthimm;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class CC_Reducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output,
                       Reporter reporter) throws IOException {
        StringBuilder jsonArray = new StringBuilder("[");
        while (values.hasNext()) {
            String jsonValue = values.next().toString();
            jsonArray.append(jsonValue);
            if (values.hasNext()) {
                jsonArray.append(",");
            }
        }
        jsonArray.append("]");
        output.collect(key, new Text(jsonArray.toString()));
    }
}
