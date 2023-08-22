package com.example.clusterinfo.connection;

import java.io.IOException;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretPayload;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import com.google.protobuf.ByteString;

public class GCPSecret {

	private static SecretManagerServiceClient client;
	private static SecretVersionName secretVersionName;
	
    private String projectId;
    private String secretId;
    private String versionId;
	
	public GCPSecret(String projectId, String secretId, String versionId) {
		this.projectId = projectId;
		this.secretId = secretId;
		this.versionId = versionId;
	}

	static {
        // Initialize the client
        try {
			client = SecretManagerServiceClient.create();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ByteString getSecretByte() { 
        
        // Build the secret version name
        secretVersionName = SecretVersionName.of(projectId, secretId, versionId);

        // Access the secret version
        AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);
        
     // Get the secret data
        SecretPayload payload = response.getPayload();
        ByteString secretData = payload.getData();
        
        return secretData;
	}
	
	public String getSecretString() { 
        
        // Build the secret version name
        secretVersionName = SecretVersionName.of(projectId, secretId, versionId);

        // Access the secret version
        AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);
        
     // Get the secret data
        SecretPayload payload = response.getPayload();
        ByteString secretData = payload.getData();

        // Convert the secret data to a string
        String secretValue = secretData.toStringUtf8();
        
        return secretValue;
	}

}