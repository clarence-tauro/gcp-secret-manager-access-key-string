package com.example.clusterinfo.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

	public static void stringToFile (String content, String filePath) {

	        File file = new File(filePath);

	        try {
	            FileWriter writer = new FileWriter(file);

	            writer.write(content);
	            writer.close();

	        } catch (IOException e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        }
	    }
}
