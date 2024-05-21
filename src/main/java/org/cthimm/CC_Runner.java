package org.cthimm;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;


public class CC_Runner {
    public static void main(String[] args) throws Exception {
        /* JobConf conf = new JobConf(CC_Runner.class);

        conf.setJobName("CarCrashes");
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        conf.setMapperClass(CC_Mapper.class);
        conf.setCombinerClass(CC_Reducer.class);
        conf.setReducerClass(CC_Reducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf,new Path(args[1]));

        JobClient.runJob(conf); */

    }
}
