package com.tools.format.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tools.format.CMN_MetaDto;
import com.tools.format.Main;


/**
 * PackageXML作�?�クラス
 * Date --- Author ---- Subject
 * 20160503 M.Kawaguchi プログラ�?リリース
 **/
public class CMN_PackageXMLWriter {

	private static final Logger logger = LoggerFactory.getLogger(CMN_PackageXMLWriter.class);
	
    public CMN_PackageXMLWriter() {
    }
 
    public List<CMN_MetaDto> read(String path) {

        DocumentBuilder documentBuilder = null;
        try {
             documentBuilder = DocumentBuilderFactory.newInstance()
                       .newDocumentBuilder();
        } catch (ParserConfigurationException e) {
             e.printStackTrace();
        }

        Document document = null;
        logger.debug(path);
		try {
			document = documentBuilder.parse(path);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<CMN_MetaDto> mlist = new ArrayList<CMN_MetaDto>();
		
		Element root = document.getDocumentElement();
		NodeList  types = root.getChildNodes();
		for (int i = 0; i < types.getLength(); i++) {
			if (types.item(i).getNodeName().equals("version") || types.item(i).getNodeName().equals("#text")) continue;
			NodeList typeval = types.item(i).getChildNodes();
			CMN_MetaDto m = new CMN_MetaDto();
			logger.debug("types :: " + types.item(i).getNodeName());
			for (int j = 0; j < typeval.getLength(); j++) {
				if (typeval.item(j).getNodeName().equals("#text")) continue;
				logger.debug("members :: " + typeval.item(j).getNodeName());
				logger.debug("nodevalue :: " + typeval.item(j).getTextContent());
				if (typeval.item(j).getNodeName().equals("name")) {
					m.setName(typeval.item(j).getTextContent());
					continue;
				}
				m.addMember(typeval.item(j).getTextContent());
			}
			mlist.add(m);
		}
		return mlist;
   }

    public void create(String path, List<CMN_MetaDto> mlist) {

        DocumentBuilder documentBuilder = null;
        try {
             documentBuilder = DocumentBuilderFactory.newInstance()
                       .newDocumentBuilder();
        } catch (ParserConfigurationException e) {
             e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();

        // XML�?書の作�??
        Element p = document.createElement("Package");
        p.setAttribute("xmlns", "http://soap.sforce.com/2006/04/metadata");
        document.appendChild(p);
        
        for (CMN_MetaDto m : mlist) {
        	Element t = document.createElement("types");
        	logger.debug("Type Name :: " + m.getName());
        	List<String> sl = new ArrayList<>(m.getMembers());
    	    Collections.sort(sl);
        	for (String str : sl) {
        		Element mem = document.createElement("members");
                mem.appendChild(document.createTextNode(str));
                t.appendChild(mem);
        	}
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(m.getName()));
            t.appendChild(name);
            p.appendChild(t);
        }
        
        Element v = document.createElement("version");
        v.appendChild(document.createTextNode(CMN_Const.VERSION_API));
        p.appendChild(v);
        

        logger.debug("Output file :: " + path);
        File file = new File(path);
        write(file, document);
   }

   private boolean write(File file, Document document) {

        // Transformerインスタンスの生�??
        Transformer transformer = null;
        try {
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
             e.printStackTrace();
             return false;
        }

        transformer.setOutputProperty("indent", "yes"); //改行指�?
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");

        try {
             transformer.transform(new DOMSource(document), new StreamResult(file));
        } catch (TransformerException e) {
             e.printStackTrace();
             return false;
        }

        return true;
   }
}
