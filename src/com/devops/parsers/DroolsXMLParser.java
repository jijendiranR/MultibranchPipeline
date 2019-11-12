package com.devops.parsers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.solartis.data.QuestionHashMap;
import com.solartis.drools.detail.DroolsRequestJaxBDetail;

public class DroolsXMLParser {

	private static FileWriter fileWriter;

	public static void main(String[] args) throws JAXBException, IOException {
		fileWriter = new FileWriter(new File("E:\\RequestDump\\Request_Extraction\\EventNames.csv"));

		File requestDir = new File("E:\\RequestDump\\Request09.05.18");
		JAXBContext context = JAXBContext.newInstance(DroolsRequestJaxBDetail.class);
		Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
		File[] directoryListing = requestDir.listFiles();
		int i = 0;
		long starttime = System.currentTimeMillis();
		for (File file : directoryListing) {
				
			String requestId = file.getName().substring(0, file.getName().lastIndexOf('_'));
			DroolsRequestJaxBDetail requestDetail = (DroolsRequestJaxBDetail) jaxbUnmarshaller.unmarshal(file);
			QuestionHashMap qmap = new QuestionHashMap();
			requestDetail.deSerialize(qmap);
			String eventName = qmap.getString("Object::EventName");
			fileWriter.write(requestId + "," + eventName + "\n");
			System.out.println(
					"Number " + i + " RequestID " + requestId + " EventName " + qmap.getString("Object::EventName"));
			File renamedFile = new File("E:\\RequestDump\\RenamedFiles\\"+eventName+".xml");
			file.renameTo(renamedFile);
			i++;
		}
		long endtime = System.currentTimeMillis();
		System.out.println((endtime - starttime)/60*60);
		fileWriter.close();

	}

}
