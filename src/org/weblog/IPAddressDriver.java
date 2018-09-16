package org.weblog;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class IPAddressDriver {
	
	public void run(String inputPath,String outputPath) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException{
		
		
		Configuration conf=new Configuration();
		Job job=new Job(conf,"Web Log Processing Project");
		job.setJarByClass(IPAddressDriver.class);
		job.setMapperClass(IPAddressMapper.class);
		job.setReducerClass(IPAddressReducer.class);
		
		// set out key,value pair to stored file to hdfs
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// setting key,value output from mapper
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// set input,output format of mapper program
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		
		// set input output path for file to digest and store
		FileInputFormat.addInputPath(job,new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
