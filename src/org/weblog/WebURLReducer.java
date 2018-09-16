package org.weblog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WebURLReducer extends Reducer<Text, IntWritable,Text, NullWritable> {

	@Override
	protected void reduce(Text keys, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
		
		int sum=0;
		for(IntWritable value:values){
			sum +=1;
		}
		
		context.write(new Text(keys.toString()+","+sum),null);
	}
	
	

}
