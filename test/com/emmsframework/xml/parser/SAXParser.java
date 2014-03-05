/**
 * @Project:activiti
 * @Package:com.emmsframework.xml.parser 
 * @FileName:SAXParser.java 
 * @Date:2013-9-18 下午5:29:41 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.xml.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/** 
 * @ClassName:SAXParser 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-18 下午5:29:41 
 * @Since:V 1.0 
 */
public class SAXParser
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(SAXParser.class);

	static String filePath = "F:\\workspace\\activiti\\test\\com\\emmsframework\\xml\\parser\\demo.xml";

	public static void main(String[] args) throws SAXException, IOException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		BookHandler handler = new BookHandler();
		
		reader.setContentHandler(handler);
		reader.parse(filePath);
		List<String> list = handler.getNameList();
		System.out.println("---sysout---");
		for (String txt : list) {
			System.out.println(txt);
		}
	}
}

class BookHandler extends DefaultHandler
{
	private List<String> nameList;

	private boolean title = false;

	public List<String> getNameList() {
		return nameList;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("start parsing document");
		nameList = new ArrayList<String>();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("end parsing document");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attr) throws SAXException {
		System.out.println("qName:" + qName);
		if (qName.equals("title")) {
			title = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (title) {
			title = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (title) {
			String bookTitle = new String(ch, start, length);
			System.out.println("Book title:" + bookTitle);
			nameList.add(bookTitle);
		}
	}


}