/**
 * @Project:activiti
 * @Package:com.emmsframework.xml.parser 
 * @FileName:DOMParser.java 
 * @Date:2013-9-18 下午5:13:46 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.xml.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @ClassName:DOMParser
 * @Desc:DOM 解析 XML
 * @Author:joe
 * @Date:2013-9-18 下午5:13:46
 * @Since:V 1.0
 */
public class DOMParser
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(DOMParser.class);

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	/**
	 * 加载和解析XML
	 */
	public Document parse(String filePath) {
		Document document = null;

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			File file = new File(filePath);
			document = builder.parse(file);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return document;
	}

	public static void main(String[] args) {
		DOMParser parser = new DOMParser();
		Document document = parser
				.parse("F:\\workspace\\activiti\\test\\com\\emmsframework\\xml\\parser\\demo.xml");
		Element rooteElement = document.getDocumentElement();

		NodeList nodes = rooteElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) node;
				System.out.println(child.getNodeName());
			}
		}

		NodeList nodeList = rooteElement.getElementsByTagName("book");
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				System.out.println("id=" + element.getAttribute("id"));
				Node node = element.getChildNodes().item(1);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// System.out.println(node.getNodeType());
					System.out.println(node.getNodeName() + "--"
							+ node.getTextContent());
				}
			}
		}
	}
}
