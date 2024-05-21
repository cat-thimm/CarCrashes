package org.cthimm;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CC_Reducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder jsonArray = new StringBuilder("[");
        for (Text value : values) {
            jsonArray.append(value.toString()).append(",");
        }
        // Remove the last comma
        if (jsonArray.length() > 1) {
            jsonArray.setLength(jsonArray.length() - 1);
        }
        jsonArray.append("]");
        context.write(key, new Text(jsonArray.toString()));
    }
}
