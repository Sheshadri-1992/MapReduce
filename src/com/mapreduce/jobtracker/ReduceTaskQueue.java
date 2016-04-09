package com.mapreduce.jobtracker;

public class ReduceTaskQueue {

	public int jobID;
	public ReduceInfo.TaskInfo redtaskInfo;
	
	public ReduceTaskQueue(int jobID,ReduceInfo.TaskInfo taskInfo)
	{
		this.jobID = jobID;
		this.redtaskInfo = taskInfo;
	}
	
	public ReduceTaskQueue(){}
	
}
