package com.example.gcpsecret.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.AuthMode;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.TlsPolicy;
import com.example.clusterinfo.connection.GCPSecret;
import com.example.clusterinfo.helper.FileOperations;
import com.example.clusterinfo.helper.TlsParser;


public class Test {

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {

		GCPSecret gcpSecretAerospikePassword = new GCPSecret("aerospike-se-clarence", "my-aerospike-database-password", "latest");
		
		GCPSecret gcpSecretKey = new GCPSecret("aerospike-se-clarence", "key", "latest");
		GCPSecret gcpSecretCert = new GCPSecret("aerospike-se-clarence", "cert", "latest");
		GCPSecret gcpSecretCACert = new GCPSecret("aerospike-se-clarence", "cacert", "latest");
		
		TlsParser tlsParser = new TlsParser();
		
		Host[] hosts = new Host[] { 
				new Host("13.214.191.221" ,"tls1", 4333), 
				new Host("13.251.26.223", "tls1", 4333),
				new Host("18.141.218.104", "tls1", 4333) 
		};

		TlsPolicy tlsPolicy = new TlsPolicy();
		
		SSLContext sslContext = tlsParser.parseStringTlsContext(gcpSecretCert.getSecretString(),
				gcpSecretCACert.getSecretString(), 
				gcpSecretKey.getSecretString(), 
				gcpSecretAerospikePassword.getSecretString()
			).getSslContext();
		
		tlsPolicy.context = sslContext;
				
		ClientPolicy clientPolicy = new ClientPolicy();
		clientPolicy.authMode = AuthMode.INTERNAL;

		clientPolicy.user = "clarence";
		clientPolicy.password = gcpSecretAerospikePassword.getSecretString();
		clientPolicy.useServicesAlternate = true;

		clientPolicy.tlsPolicy = tlsPolicy;

		AerospikeClient client = new AerospikeClient(clientPolicy, hosts);
		
		//----- write -----
		String recordKey = UUID.randomUUID().toString();
		Key key = new Key("cla_ns", "EmpoyeeSet", recordKey);
		
		Bin id = new Bin("id", "101");
		Bin name = new Bin("name", "Clarence");
		Bin salary = new Bin("salary", 1000);
		
		client.put(null, key, id, name, salary);
		
		//----- read -----
		Record record = client.get(null, key);
		String myId = record.getString("id");
		String myName = record.getString("name");
		int mySalary = record.getInt("salary");
		
		System.out.println("[" + myId + ", " + myName + ", " + mySalary + "]");
		
		client.close();
	}
}
