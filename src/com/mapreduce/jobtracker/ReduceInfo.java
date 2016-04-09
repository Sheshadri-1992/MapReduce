package com.mapreduce.jobtracker;

import java.util.ArrayList;
import java.util.List;

import com.mapreduce.jobtracker.MapInfo.TaskInfo;

public class ReduceInfo {

	public class TaskInfo
	{
		List<String> mapOutFiles;
		String outputFile;
		boolean status;
		int taskID;
		
		public TaskInfo(List<String> mapOut,boolean st,String out,int task)
		{
			mapOutFiles = mapOut;
			status = st;
			outputFile = out;
			taskID = task;
		}
		
		
	}
	
	public List<TaskInfo> tasks; 
	
	public ReduceInfo()
	{
		tasks  = new ArrayList<>();
	}
	
	public void addTask(List<String> mapOut,String out)
	{
		int size = tasks.size();
		TaskInfo task = new  TaskInfo(mapOut, false, out, size);
		tasks.add(task);
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
