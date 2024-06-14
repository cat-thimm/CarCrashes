package org.cthimm;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.util.Progressable;

public class JsonOutputFormat extends FileOutputFormat<Text, NullWritable> {

    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(FileSystem ignored, JobConf job, String name, Progressable progress) throws IOException {
        Path outputPath = FileOutputFormat.getTaskOutputPath(job, name);
        FSDataOutputStream fileOut = outputPath.getFileSystem(job).create(outputPath, progress);
        return new JsonRecordWriter(fileOut);
    }

    public static class JsonRecordWriter implements RecordWriter<Text, NullWritable> {

        private DataOutputStream out;
        private ObjectMapper mapper;

        public JsonRecordWriter(DataOutputStream out) {
            this.out = out;
            this.mapper = new ObjectMapper();
        }

        @Override
        public void write(Text key, NullWritable value) throws IOException {
            String json = key.toString();
            out.writeBytes(json + "\n");
        }

        @Override
        public void close(Reporter reporter) throws IOException {
            out.close();
        }
    }
}
