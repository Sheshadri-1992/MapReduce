package com.mapreduce.misc;

public final class Constants {


	public static final String  NAME_NODE = "NameNode";  //added by shweta
	public static final String  NAME_NODE_IP = "10.0.0.2";  //added by shweta
	public static final String DATA_NODE_ID = "DataNode";//constant by sheshadri
	
	
	public static final int STATUS_SUCCESS = 1;//constant by shweta  
	public static final int STATUS_NOT_FOUND = -1;//only for get openfile - file not found
	public static final int STATUS_FAILED = 0;//constant by shweta
	
	public static final String NAME_NODE_CONF = "NNConf";//constant by sheshadri
	public static final String BLOCK_NUM_FILE = "BlockNum";//constant by sheshadri
	public static final String DATA_NODE_CONF = "DNConf";//constant by sheshadri
	public static final String OUTPUT_FILE = "out_"; //constant by sheshadri
	
	
	public static final int BLOCK_SIZE=1*1000*1024;// 32 MB
	public static final int DATA_NODE_PORT=10000;
	public static final long BLOCK_REPORT_FREQ = 5000;
	public static final long HEART_BEAT_FREQ = 5000;

	public static final String CONNECTIVITY = "eth0"; //eth0 if you use a cable
//	public static final String CONNECTIVITY = "wlan0"; //eth0 if you use a cable
	
	
	/***ADDED BY SHESH DFS **/
	public static final String JOB_TRACKER_IP = "10.3.9.118";
	public static final String REDUCE_FILE = "_reduceFile_";
	
}
