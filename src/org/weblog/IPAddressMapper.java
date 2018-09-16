package org.weblog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IPAddressMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		// extract IP Address from string
		String ip=value.toString().split(" ")[0];
		context.write(new Text(ip), new IntWritable(1));
	}

}
