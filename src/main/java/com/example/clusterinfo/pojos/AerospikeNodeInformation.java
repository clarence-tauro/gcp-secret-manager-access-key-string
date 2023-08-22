package com.example.clusterinfo.pojos;

public class AerospikeNodeInformation {
	private String nodeIpAddress;
	private String serverVersion;
	private int clusterSize;
	private long nodeUniqueRecords;
	private long clusterUniqueRecords;
	private long clusterUniqueData;
	private String clusterFeatures;
	
	public AerospikeNodeInformation(String nodeInfo) {
		String []info = nodeInfo.split(",");
		
		nodeIpAddress = info[0];
		serverVersion = info[1];
		clusterSize = Integer.parseInt(info[2]);
		nodeUniqueRecords = Long.parseLong(info[3]);
		clusterUniqueRecords = Long.parseLong(info[4]);
		clusterUniqueData = Long.parseLong(info[5]);
		clusterFeatures = info[6];		
	}

	public String getNodeIpAddress() {
		return nodeIpAddress;
	}

	void setNodeIpAddress(String nodeIpAddress) {
		this.nodeIpAddress = nodeIpAddress;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public int getClusterSize() {
		return clusterSize;
	}

	void setClusterSize(int clusterSize) {
		this.clusterSize = clusterSize;
	}

	public long getNodeUniqueRecords() {
		return nodeUniqueRecords;
	}

	void setNodeUniqueRecords(long nodeUniqueRecords) {
		this.nodeUniqueRecords = nodeUniqueRecords;
	}

	public long getClusterUniqueRecords() {
		return clusterUniqueRecords;
	}

	void setClusterUniqueRecords(long clusterUniqueRecords) {
		this.clusterUniqueRecords = clusterUniqueRecords;
	}

	public long getClusterUniqueData() {
		return clusterUniqueData;
	}

	void setClusterUniqueData(long clusterUniqueData) {
		this.clusterUniqueData = clusterUniqueData;
	}

	public String getClusterFeatures() {
		return clusterFeatures;
	}

	void setClusterFeatures(String clusterFeatures) {
		this.clusterFeatures = clusterFeatures;
	}
	
	@Override
	public String toString() {
		return "[" 
				+ nodeIpAddress + ", "
				+ serverVersion + ", "
				+ clusterSize + ", "
				+ nodeUniqueRecords + ", "
				+ clusterUniqueRecords + ", "
				+ clusterUniqueData + ", "
				+ clusterFeatures 
				+ "]";				
	}
}
