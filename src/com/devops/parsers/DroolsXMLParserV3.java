package com.devops.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DroolsXMLParserV3 {
	private static Scanner in = null;
	private static String key = "Object::EventName";
	private static String expectedLine = "";

	public static void main(String[] args) throws IOException {

		File requestDir = new File("D:\\DevOps\\JIJI\\OCI_update\\req");
		//File requestDir = new File("E:\\MGRE\\");
		File[] directoryListing = requestDir.listFiles();
		int i =1;
		for (File file : directoryListing) {
			
			String eventname = findEventName(file);
			File renamedDir = new File("D:\\DevOps\\JIJI\\OCI_update\\"+eventname+".xml");
			//File renamedDir = new File("E:\\MGUEVENT\\"+eventname+".xml");
			System.out.print(" Number "+i++);
			file.renameTo(renamedDir);
		}

	}

	static String findEventName(File file) throws FileNotFoundException {
		in = new Scanner(file);
		String eventName = "";
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.contains(key)) {
				expectedLine = line.trim();
				break;
			}
		}
		int startIndex = expectedLine.indexOf(">");
		int endIndex = expectedLine.indexOf("</");
		System.out.println(expectedLine.substring(startIndex + 1, endIndex));
		eventName = expectedLine.substring(startIndex + 1, endIndex);
		in.close();
		return eventName;

	}
}
