package org.weblog;

import java.io.IOException;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		
		
		
		
		if(args.length!=3)
		{
			System.out.println("Command Argument missing");
			System.out.println("Argument format : <task_name>  <input_dir>  <output_dir>");
			System.out.println("Possible task name : mapipaddress,mapweburl");
			System.exit(0);
		}
		
		if(args[0].equalsIgnoreCase("mapipaddress")){
			new IPAddressDriver().run(args[1],args[2]);
		}
		else if(args[0].equalsIgnoreCase("mapweburl")){
			new WebHitDriver().run(args[1],args[2]);
		}
		
		
		
	}

}
