package org.weblog;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WebHitDriver {
	public void run(String inputPath,String outputPath) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException{
		Configuration conf=new Configuration();
		
		
		Path pagerank = new Path(outputPath);
		FileSystem hdfs = FileSystem.get(conf);
		Path hitcount = new Path("/hitcount");

		// delete existing directory
		if (hdfs.exists(pagerank)) {
		    hdfs.delete(pagerank, true);
		}
		
		
		
		Job job1=new Job(conf,"Web Page Hit Counter Job");
		job1.setJarByClass(WebHitDriver.class);
		job1.setMapperClass(WebURLMapper.class);
		job1.setReducerClass(WebURLReducer.class);
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(NullWritable.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(IntWritable.class);
		
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job1,new Path(inputPath));
		FileOutputFormat.setOutputPath(job1, new Path("/hitcount"));

		int i = job1.waitForCompletion(true)?0:1;
		if(i==0)
		{
		Job job2=new Job(conf,"Web Page Hit Ranker Job");
		job2.setJarByClass(WebHitDriver.class);
		job2.setMapperClass(WebPageHitMapper.class);
		job2.setReducerClass(WebPageHitReducer.class);
		
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(NullWritable.class);

		job2.setMapOutputKeyClass(PageHitWritable.class);
		job2.setMapOutputValueClass(Text.class);
		
		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job2,new Path("/hitcount"));
		FileOutputFormat.setOutputPath(job2, new Path(outputPath));
		int state=job2.waitForCompletion(true)?0:1;
		
		
		
		// delete temporary directory
		if (hdfs.exists(hitcount)) {
		    hdfs.delete(hitcount, true);
		}
		
		}

//		JobControl jobcntrl=new JobControl("Web Page Ranking job controller");
//		ControlledJob pagehitcounter =new ControlledJob(conf);
//		ControlledJob pagehitranker =new ControlledJob(conf);
//		pagehitcounter.setJob(job1);
//		pagehitranker.setJob(job2);
//		
//		
//		jobcntrl.addJob(pagehitcounter);
//		jobcntrl.addJob(pagehitranker);
//		pagehitranker.addDependingJob(pagehitcounter);
//		
//		
//		
//		jobcntrl.run();
//		
		
	}
}
