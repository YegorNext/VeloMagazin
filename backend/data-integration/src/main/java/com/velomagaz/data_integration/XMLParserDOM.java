package com.velomagaz.data_integration;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParserDOM {
	public Document read(String filePath){
		File fileXML = new File(filePath);
		
		if(!fileXML.exists()) {
			System.err.println("ERROR: cannot read xml file with path " + filePath);
			return null;
		}
		
		return parse(fileXML);
	}
	private Document parse(File fileXML) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder; Document document;

		try {
			 builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			document = builder.parse(fileXML);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return document;
	}
}
