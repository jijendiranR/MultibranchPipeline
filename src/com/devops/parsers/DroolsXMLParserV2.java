package com.devops.parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DroolsXMLParserV2 {

	

	public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {
		
		File fXmlFile = new File("E:\\RequestDump\\Request09.05.18\\chickbv4-1solartisnet_1525785594454_76_request.xml");
		String key = "Object::EventName";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		int pointer = 0;
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("ATTRIBUTE_DETAIL");
		for (int temp = 0; temp < nList.getLength(); temp++) {
		
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if(eElement.getAttribute("KEY").equals(key)){
					pointer=temp;
				}	
			}
		}
		
		Node nNode = nList.item(pointer);
		Element eElement = (Element) nNode;
		System.out.println(eElement.getElementsByTagName("ATTRIBUTE_DETAIL").item(pointer).getTextContent());
		
		
	}
		
	
		
	}


