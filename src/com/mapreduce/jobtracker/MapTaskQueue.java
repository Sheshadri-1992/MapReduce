package com.mapreduce.jobtracker;

public class MapTaskQueue {

	public int jobID;
	public MapInfo.TaskInfo maptaskInfo;
	
	public MapTaskQueue(int jobID,MapInfo.TaskInfo taskInfo)
	{
		this.jobID = jobID;
		this.maptaskInfo = taskInfo;
	}
	
	public MapTaskQueue(){}
	
}
