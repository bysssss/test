package com.leebs.test.hc;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Folders {
	public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<folder name=\"c\">" +
                    "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                    "</folder>" +
                    "<folder name=\"users\" />" +
                "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
    
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
    	DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try{
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(new InputSource(new StringReader(xml)));
        } catch(Exception ex){
            throw ex;
        }       

        List<String> nameList = new ArrayList<String>();        
        String name = doc.getDocumentElement().getAttribute("name");
        addList(name, startingLetter, nameList);
        explore(doc.getDocumentElement().getChildNodes(), startingLetter, nameList);
        
    	return nameList;
    }
    
    public static List<String> explore(NodeList nodeList, char startingLetter, List<String> nameList) throws Exception {
    	if( nodeList==null) {
    		return nameList;
    	}
    	
    	for(int i=0; i<nodeList.getLength(); ++i) {
        	Node node = nodeList.item(i);
        	addList(node.getAttributes().getNamedItem("name").getNodeValue(), startingLetter, nameList);
        	explore(node.getChildNodes(), startingLetter, nameList);
        }
    	
    	return nameList;
    }
    
    public static void addList(String name,  char startingLetter, List<String> nameList) throws Exception {
    	if( name!=null && name.charAt(0)==startingLetter) {
    		nameList.add(name);
    	}
    }
}
