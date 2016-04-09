package com.mapreduce.jobtracker;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import com.mapreduce.misc.Constants;
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

		/**initialize data structures **/
		initializeDataStructures();
		
        /**call bind to registry **/
        bindToRegistry();
        
		
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

	/**bind to registry method **/
	static void bindToRegistry()
	{
		System.setProperty("java.rmi.server.hostname",Constants.JOB_TRACKER_IP);
		JTrackerDriver obj = new JTrackerDriver();
		try {
			
			Registry register=LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			IJobTracker stub = (IJobTracker) UnicastRemoteObject.exportObject(obj,Registry.REGISTRY_PORT);
			try {
				register.bind(Constants.JOB_TRACKER_IP, stub);
				
				System.out.println("JOB TRACKER started succesfully");
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("JOB TRACKER failed to bind");
				e.printStackTrace();
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	static void initializeDataStructures()
	{
		jobs = new HashMap<>();
		jobStatus = new HashMap<Integer, JobStatusResponse>();
		jobMapInfo = new HashMap<>();
		jobReduceInfo = new HashMap<>();
	}
	
}
