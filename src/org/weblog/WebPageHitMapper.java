package org.weblog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WebPageHitMapper extends Mapper<LongWritable,Text, PageHitWritable, Text>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String values[]=value.toString().split(",");
		context.write(
				new PageHitWritable(Integer.parseInt(values[values.length-1].trim())), // custom key for decreasing sorting order
				new Text(values[0]));
	}

	

}