/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NavigationLinkAction extends ActionSupport {

    private String caption, url;
    private String path = getText("");
    private SAXBuilder builder = new SAXBuilder();
    private ArrayList<String> CaptionList = new ArrayList();
    private ArrayList<String> UrlList = new ArrayList();
    private String msg, fileNotFound = getText("fileNotFound");
    private String filePath = getText("filePath.NavigationLinks");

    public NavigationLinkAction() {
    }

    public String AddOthersLink() throws Exception {
        try {
            File xmlFile = new File(getFilePath() + "/NavigationLinks.xml");
            if (xmlFile.exists()) {
                try {
                    Document doc = (Document) builder.build(xmlFile);
                    Element rootNode = doc.getRootElement();
                    List row = rootNode.getChildren("links");
                    String id = row.size() + 1 + "";
                    Element links = new Element("links");
                    links.setAttribute("Id", id);
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
                links.setAttribute("Id", "1");
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
        File xmlFile = new File(getFilePath() + "/NavigationLinks.xml");
        if (xmlFile.exists()) {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("links");
            for (int i = 0; i < list.size(); i++) {
                Element element = (Element) list.get(i);
                getCaptionList().add(element.getChildText("caption"));
                getUrlList().add(element.getChildText("url"));
            }
        } else {
            msg = fileNotFound;
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

}
