package com.mapreduce.jobtracker;

import java.util.HashMap;

import com.mapreduce.misc.MapReduce.JobStatusResponse;
import com.mapreduce.misc.MapReduce.JobSubmitRequest;

/**
 * 
 * @author master
 *	Implementation details
 *	1. HashMap for storing jobs (jobId(int), jobSubmitReq)
 *	2. HashMap for job submit response (jobId(int), jobSubmitRes) 
 */




public class JTrackerDriver implements IJobTracker {

	public static HashMap<Integer, JobSubmitRequest> jobs;
	public static HashMap<Integer, JobStatusResponse> jobStatus;
	public static HashMap<Integer, MapInfo> jobMapInfo;
	public static HashMap<Integer, ReduceInfo> jobReduceInfo;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] jobSubmit(byte[] jobSubmitRequest) {
		// TODO Auto-generated method stub
		System.out.println("Job Submit got called");
		return null;
	}

	@Override
	public byte[] getJobStatus(byte[] jobStatusRequest) {
		// TODO Auto-generated method stub
		System.out.println("getJobStatus got called");
		return null;
	}

	@Override
	public byte[] heartBeat(byte[] heartBeatRequest) {
		// TODO Auto-generated method stub
		System.out.println("HeartBeat got called");
		return null;
	}

}
