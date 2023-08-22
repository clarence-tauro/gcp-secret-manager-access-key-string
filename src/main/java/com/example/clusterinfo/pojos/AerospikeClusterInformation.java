package com.example.clusterinfo.pojos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ext.DOMDeserializer.NodeDeserializer;

public class AerospikeClusterInformation {

	private List<AerospikeNodeInformation> nodesDetails;

	public AerospikeClusterInformation(String filePath) {

		FileInputStream fileInputStream = null;
		Scanner scanner = null;
		
		nodesDetails = new ArrayList<>();

		try {
			fileInputStream = new FileInputStream(filePath);
			scanner = new Scanner(fileInputStream);

			scanner.nextLine(); // to skip header
			while (scanner.hasNextLine()) {
				String lineOfText = scanner.nextLine();

				AerospikeNodeInformation aerospikeNodeInformation = new AerospikeNodeInformation(lineOfText);
				nodesDetails.add(aerospikeNodeInformation);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<AerospikeNodeInformation> getNodesDetails() {
		return nodesDetails;
	}
}