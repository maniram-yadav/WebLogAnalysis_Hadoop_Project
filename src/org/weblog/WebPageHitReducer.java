package org.weblog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class WebPageHitReducer extends Reducer<PageHitWritable, Text, Text,NullWritable>{
	
	// Web page hit ranker variable
	static int pageRank=0;
	@Override
	protected void reduce(PageHitWritable key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		for(Text value:values)
			{
			pageRank += 1;
			context.write(new Text("{"+value+","+key.get()+","+pageRank+"}"), null);
			}
		
	}}
