package com.mapreduce.jobtracker;

import java.util.ArrayList;
import java.util.List;

import com.mapreduce.misc.MapReduce.BlockLocations;

public class MapInfo {

	
	public class TaskInfo
	{
		boolean status;
		int taskID;
		BlockLocations blockLocations;
		
		public TaskInfo(BlockLocations blk,boolean st,int task)
		{
			status = st;
			taskID = task;
			blockLocations = blk;
		}
	}
	
	List<TaskInfo> tasks;  // index is taskID
	
	public MapInfo(ArrayList<BlockLocations> blocks)
	{
		tasks  = new ArrayList<>();
		for(int i=0;i<blocks.size();i++)
		{
			TaskInfo task  = new TaskInfo(blocks.get(i),false,i);
			tasks.add(task);
		}
	}
	
	/*** implementation left */
	
	public boolean isAllTaskCompleted()
	{
		return false;
	}
	
	public void updateStatus(int taskID)
	{
		
	}
}
