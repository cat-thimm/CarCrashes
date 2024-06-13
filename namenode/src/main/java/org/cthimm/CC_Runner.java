package org.cthimm;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.MultipleInputs;

import java.io.IOException;

public class CC_Runner {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: CC_Runner <input1> <input2> <output>");
            System.exit(2);
        }

        JobConf conf = new JobConf(CC_Runner.class);
        conf.setJobName("CarCrashes");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        MultipleInputs.addInputPath(conf, new Path(args[0]), TextInputFormat.class, CC_Mapper_Crashes.class);
        MultipleInputs.addInputPath(conf, new Path(args[1]), TextInputFormat.class, CC_Mapper_Persons.class);

        conf.setReducerClass(CC_Reducer.class);
        conf.setOutputFormat(TextOutputFormat.class);

        TextOutputFormat.setOutputPath(conf, new Path(args[2]));

        JobClient.runJob(conf);
    }
}