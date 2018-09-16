package org.weblog;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class PageHitWritable implements WritableComparable{

	private IntWritable val;
	
	PageHitWritable(){
		this.val= new IntWritable(-1);
	}
	
	PageHitWritable(IntWritable x){
		this.val=x;
	}
	
	PageHitWritable(int x){
		this.val=new IntWritable(x);
	}
	
	public void set(int x){
		this.val=new IntWritable(x);
	}
	public int get(){
		return val.get();
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		val.readFields(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		val.write(out);
		
	}

	@Override
	public int compareTo(Object o) {
		
		PageHitWritable hit=(PageHitWritable)o;
		if(this.get()<hit.get())
			return 1;
		else if(this.get()>hit.get())
			return -1;
		
		return 0;
	}

}
