package com.mapreduce.tasktracker;

import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mapreduce.hdfsutils.PutFile;
import com.mapreduce.misc.MapReduce.BlockLocations;
import com.mapreduce.misc.MapReduce.DataNodeLocation;
import com.mapreduce.misc.MapReduce.MapTaskInfo;
import com.mapreduce.misc.MyFileWriter;

class MapThread implements Runnable{

	MapTaskInfo info;
	
	public MapThread(MapTaskInfo info) {
		// TODO Auto-generated constructor stub
		this.info = info;
		
	
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		 * Get the input block list
		 * read the block from by making call to name node
		 * now read the block line by line and send it to the map method
		 * if map method returns true add it to local map output file
		 * once all the lines are read from the block , upload the temporary map file to hdfs
		 * update the status of the task
		 * remove the task which is completed after heart beat req;
		 */
		List<BlockLocations> list = info.getInputBlocksList();
		
		try {
			String input = getBlockContents(list.get(0));
			String word = "Hello";
			String lines[] = input.split("\n");
			
			StringBuilder sb = new StringBuilder();
			
			for(String line: lines)
			{
				String output = callMapper(info.getMapName(),line,word);
				if(output!=null)
				{
					sb.append(output);
					sb.append("\n");
				}
			}
			
			
			
			
			MyFileWriter writer = new MyFileWriter(getMapOutFileName(info.getJobId(),info.getTaskId()));
			writer.createFile();
			writer.writeonly(sb.toString());
			writer.closeFile();
			
			
			PutFile putFile = new PutFile(getMapOutFileName(info.getJobId(),info.getTaskId()), getMapOutFileName(info.getJobId(),info.getTaskId()));
			Thread thread1 = new Thread(putFile);
			thread1.start();
			try {
				thread1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TTrackerDriver.updateMapStatus(info);
			
			/*u
			 * 
			 * update the status
			 */
			
			
			
			
			
		} catch (RemoteException | InvalidProtocolBufferException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private String callMapper(String mapClass,String line, String word) {
		// TODO Auto-generated method stub
		
		/*replace this by dynamic jar */
		
		if(line.contains(word))
			return line;
		return null;
		
		
	}
	
	
	static String getMapOutFileName(Integer jobID,Integer taskID)
	 {
		 return "job_"+jobID.toString()+"_map_"+taskID.toString();
	 }

	private String getBlockContents(BlockLocations blockLocations) throws NotBoundException, RemoteException, InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		
		int blockNumber = blockLocations.getBlockNumber();
		
		com.mapreduce.hdfsutils.Hdfs.BlockLocations.Builder blockLocationObj  = com.mapreduce.hdfsutils.Hdfs.BlockLocations.newBuilder();
		blockLocationObj.setBlockNumber(blockLocations.getBlockNumber());//blocklocation => (blockNumber + datanodelocations)
		
		List<DataNodeLocation> dataNodeLocations = blockLocations.getLocationsList();
		
		for(int k=0;k<dataNodeLocations.size();k++)
		{
			com.mapreduce.hdfsutils.Hdfs.DataNodeLocation.Builder miscDataNodeLocation = com.mapreduce.hdfsutils.Hdfs.DataNodeLocation.newBuilder();
			miscDataNodeLocation.setIp(dataNodeLocations.get(k).getIp());
			miscDataNodeLocation.setPort(dataNodeLocations.get(k).getPort());
			
			blockLocationObj.addLocations(miscDataNodeLocation);
		}
		
		
		
		
		List<com.mapreduce.hdfsutils.Hdfs.DataNodeLocation> dataNodes = blockLocationObj.getLocationsList();
		
		int dataNodeCounter=0;
		
		com.hdfs.datanode.IDataNode dataStub=null;
		
		boolean gotDataNodeFlag=false;
		
		
		/* get data node registry */
		do
		{
			try
			{
				com.mapreduce.hdfsutils.Hdfs.DataNodeLocation thisDataNode = dataNodes.get(dataNodeCounter);
				String ip = thisDataNode.getIp();
				int port = thisDataNode.getPort();
											
				Registry registry2=LocateRegistry.getRegistry(ip,port);					
				
				dataStub = (com.hdfs.datanode.IDataNode) registry2.lookup(com.mapreduce.misc.Constants.DATA_NODE_ID);
				gotDataNodeFlag=true;
			}
			catch (RemoteException e) {
				gotDataNodeFlag=false;
				dataNodeCounter++;
			} 
		}					
		while(gotDataNodeFlag==false && dataNodeCounter<dataNodes.size());
		
	    

		com.mapreduce.hdfsutils.Hdfs.ReadBlockRequest.Builder readBlockReqObj = com.mapreduce.hdfsutils.Hdfs.ReadBlockRequest.newBuilder();
		readBlockReqObj.setBlockNumber(blockNumber);
		
		/**Read block request call **/
							
		byte[] responseArray = dataStub.readBlock(readBlockReqObj.build().toByteArray());
		com.mapreduce.hdfsutils.Hdfs.ReadBlockResponse readBlockResObj = com.mapreduce.hdfsutils.Hdfs.ReadBlockResponse.parseFrom(responseArray);
		
		if(readBlockResObj.getStatus()==com.mapreduce.misc.Constants.STATUS_FAILED)
		{
			System.out.println("In method openFileGet(), readError");
			System.exit(0);
		}
		
		responseArray = readBlockResObj.getData(0).toByteArray();						
		String str = new String(responseArray, StandardCharsets.UTF_8);						
		return str;						
		
			
	}
	
}