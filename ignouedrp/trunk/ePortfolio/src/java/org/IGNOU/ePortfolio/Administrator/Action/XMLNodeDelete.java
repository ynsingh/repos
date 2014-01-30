/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author vinay
 */
public class XMLNodeDelete extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;

    public String NodeDelete(String fileURL, int Id, String node) {
        try {
            File file = new File(fileURL);
            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(file);
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer tFormer = tFactory.newTransformer();
                Element element = (Element) doc.getElementsByTagName(node).item(Id);

//        Remove the node
                element.getParentNode().removeChild(element);
//              Normalize the DOM tree to combine all adjacent nodes
                doc.normalize();
                Source source = new DOMSource(doc);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
                //System.out.println("Done");
            } else {
                System.out.println("File not found!");
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }
        return SUCCESS;
    }
}