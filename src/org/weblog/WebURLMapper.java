package org.weblog;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WebURLMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		// Pattern to extract quoted value from string
		Pattern pattern=Pattern.compile("\"([^\"]*)\"");
		Matcher match=pattern.matcher(value.toString());
		if(match.find())
			context.write(new Text(match.group(1)), new IntWritable(1));
				
		
	}
	

}
