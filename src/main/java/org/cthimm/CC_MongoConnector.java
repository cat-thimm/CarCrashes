package org.cthimm;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.io.BSONWritable;
import com.mongodb.hadoop.util.MapredMongoConfigUtil;
import com.mongodb.hadoop.util.MongoConfigUtil;
import com.mongodb.hadoop.util.MongoTool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ToolRunner;


public class CC_MongoConnector extends MongoTool {
        String CONNECTION_URL = "mongodb://thimmcaterina:WoRkm+7359@ac-wkbctcs-shard-00-00.znr9now.mongodb.net:27017,ac-wkbctcs-shard-00-01.znr9now.mongodb.net:27017,ac-wkbctcs-shard-00-02.znr9now.mongodb.net:27017/?ssl=true&replicaSet=atlas-oi9fbp-shard-0&authSource=admin&retryWrites=true&w=majority&appName=Cluster0";

        public CC_MongoConnector(Configuration entries) {
        }

        public void setupConfig(final Configuration conf) {
                setConf(conf);

                if (MongoTool.isMapRedV1()) {
                        MapredMongoConfigUtil.setInputFormat(conf, com.mongodb.hadoop.mapred.MongoInputFormat.class);
                        MapredMongoConfigUtil.setOutputFormat(conf, com.mongodb.hadoop.mapred.MongoOutputFormat.class);
                } else {
                        MongoConfigUtil.setInputFormat(conf, MongoInputFormat.class);
                        MongoConfigUtil.setOutputFormat(conf, MongoOutputFormat.class);
                }
                MongoConfigUtil.setMapper(conf, CC_Mapper.class);
                MongoConfigUtil.setMapperOutputKey(conf, Text.class);
                MongoConfigUtil.setMapperOutputValue(conf, Text.class);

                MongoConfigUtil.setReducer(conf, CC_Reducer.class);
                MongoConfigUtil.setOutputKey(conf, Text.class);
                MongoConfigUtil.setOutputValue(conf, BSONWritable.class);
        }
        public void main(String[] strings) throws Exception {
                Configuration conf = getConf();

                this.setupConfig(conf);

                conf.set("mongo.input.uri", CONNECTION_URL);
                conf.set("mongo.output.uri", "mongodb://thimmcaterina:WoRkm+7359/car_crashes.person");

                /*Job job = Job.getInstance(conf, "Car Crashes MongoDB Integration");
                job.setJarByClass(CC_MongoConnector.class);

                job.setMapperClass(CC_Mapper.class);
                job.setReducerClass(CC_Reducer.class);

                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(Text.class);

               //  job.setInputFormatClass(MongoInputFormat.class);
                job.setOutputFormatClass(MongoOutputFormat.class);

                return job.waitForCompletion(true) ? 0 : 1;*/
                System.exit(ToolRunner.run(new CC_MongoConnector(conf), strings));

        }
}
