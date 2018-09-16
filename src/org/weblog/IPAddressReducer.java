package org.weblog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IPAddressReducer extends Reducer<Text, IntWritable, Text, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Context context) throws IOException, InterruptedException {
		
		int sum=0;
		for(IntWritable a:value){
			sum += 1;
		}
		context.write(new Text("{"+key+","+new IntWritable(sum).get()+"}"),null);
		
	}

}
