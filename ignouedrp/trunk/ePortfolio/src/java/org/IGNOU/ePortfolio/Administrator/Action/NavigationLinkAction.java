/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author IGNOUERP
 */
public class NavigationLinkAction extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private String caption, url, Id;
    private String path = getText("");
    private SAXBuilder builder = new SAXBuilder();
    private ArrayList<String> CaptionList = new ArrayList();
    private ArrayList<String> UrlList = new ArrayList();
    private ArrayList<String> ListsId = new ArrayList();
    private String msg, fileNotFound = getText("fileNotFound");
    private String filePath = ReadPropertyFile("Filepath");
    private File xmlFile = new File(getFilePath() + "/NavigationLinks.xml");

    public NavigationLinkAction() {
    }

    public String AddOthersLink() throws Exception {
        try {
            if (xmlFile.exists()) {
                try {
                    Document doc = (Document) builder.build(xmlFile);
                    Element rootNode = doc.getRootElement();
                    List row = rootNode.getChildren("links");
                    String id = row.size() + 1 + "";
                    Element links = new Element("links");
                    // links.setAttribute("Id", id);
                    links.addContent(new Element("id").setText(id));
                    links.addContent(new Element("caption").setText(caption));
                    links.addContent(new Element("url").setText(url));
                    doc.getRootElement().addContent(links);
                    XMLOutputter xmlOutput = new XMLOutputter();
                    xmlOutput.setFormat(Format.getPrettyFormat());
                    xmlOutput.output(doc, new FileWriter(getFilePath() + "/NavigationLinks.xml"));
                    //System.out.println("File Updated with New Child Node.");
                } catch (JDOMException ex) {
                    Logger.getLogger(NavigationLinkAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Element navigations = new Element("navigations");
                Document doc = new Document(navigations);
                //doc.setRootElement(navigations);
                Element links = new Element("links");
                //  links.setAttribute("Id", "1");
                links.addContent(new Element("id").setText("1"));
                links.addContent(new Element("caption").setText(caption));
                links.addContent(new Element("url").setText(url));
                doc.getRootElement().addContent(links);
                // new XMLOutputter().output(doc, System.out);
                XMLOutputter xmlOutput = new XMLOutputter();
                // display nice nice
                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(doc, new FileWriter(getFilePath() + "/NavigationLinks.xml"));
                // System.out.println("File Saved!");
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        return SUCCESS;
    }

    public String ShowOthersLink() throws Exception {
        if (xmlFile.exists()) {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("links");
            for (int i = 0; i < list.size(); i++) {
                Element element = (Element) list.get(i);
                // ListsId.add(element.getAttributeValue("Id"));
                getCaptionList().add(element.getChildText("caption"));
                getUrlList().add(element.getChildText("url"));
            }
        } else {
            msg = fileNotFound;
        }
        return SUCCESS;
    }

    public String DeleteXMLNode() throws Exception {
        try {
            if (xmlFile.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dbuilder = factory.newDocumentBuilder();
                org.w3c.dom.Document doc = dbuilder.parse(xmlFile);
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer tFormer = tFactory.newTransformer();
                org.w3c.dom.Element element = (org.w3c.dom.Element) doc.getElementsByTagName("links").item(Integer.parseInt(Id));
                //  Remove the node
                element.getParentNode().removeChild(element);
                //  Normalize the DOM tree to combine all adjacent nodes
                doc.normalize();
                Source source = new DOMSource(doc);
                //   write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);
            } else {
                System.out.println("File not found!");
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }
        return SUCCESS;
    }

    public String WriteXMLFile() {
        return SUCCESS;
    }

    /**
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the CaptionList
     */
    public ArrayList<String> getCaptionList() {
        return CaptionList;
    }

    /**
     * @param CaptionList the CaptionList to set
     */
    public void setCaptionList(ArrayList<String> CaptionList) {
        this.CaptionList = CaptionList;
    }

    /**
     * @return the UrlList
     */
    public ArrayList<String> getUrlList() {
        return UrlList;
    }

    /**
     * @param UrlList the UrlList to set
     */
    public void setUrlList(ArrayList<String> UrlList) {
        this.UrlList = UrlList;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the fileNotFound
     */
    public String getFileNotFound() {
        return fileNotFound;
    }

    /**
     * @param fileNotFound the fileNotFound to set
     */
    public void setFileNotFound(String fileNotFound) {
        this.fileNotFound = fileNotFound;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the ListsId
     */
    public ArrayList<String> getListsId() {
        return ListsId;
    }

    /**
     * @param ListsId the ListsId to set
     */
    public void setListsId(ArrayList<String> ListsId) {
        this.ListsId = ListsId;
    }

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }
}
