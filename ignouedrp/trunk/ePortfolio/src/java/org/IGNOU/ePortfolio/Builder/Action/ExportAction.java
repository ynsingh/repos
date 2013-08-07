/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.IGNOU.ePortfolio.Action.PrintHtmlPdf;
import org.IGNOU.ePortfolio.Action.UserSession;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author Amit
 */
public class ExportAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private String report;
    private String CFile = ReadPropertyFile("Filepath");
    private InputStream fps;
    private String FileToCreate;
    private PrintHtmlPdf phpdf = new PrintHtmlPdf();
    private String msg, fileNotFound = getText("fileNotFound"), failedtoCreate = getText("msg.failed");

    public String ResumeExport() throws FileNotFoundException {
        // File UserFolder = new File(CFile + "/" + user_id);
        try {
            File folder = new File(CFile + "/" + user_id);
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Directory is created!");
                    CFile = CFile + "/" + user_id + "/" + "Portfolio.pdf";
                    report = report.replace("<br>", "<br/>");
                    report = report.replaceAll("\"", "\\\"");
                    FileToCreate = phpdf.HtmlToPdf(report, CFile);
                    fps = new FileInputStream(new File(FileToCreate));
                } else {
                    msg = failedtoCreate;
                }
            } else {
                CFile = CFile + "/" + user_id + "/" + "Portfolio.pdf";
                report = report.replace("<br>", "<br/>");
                report = report.replaceAll("\"", "\\\"");
                FileToCreate = phpdf.HtmlToPdf(report, CFile);
                fps = new FileInputStream(new File(FileToCreate));
                // System.out.println("Failed to create directory!");
            }
        } catch (FileNotFoundException e) {
            msg = fileNotFound + " " + e.toString();
        }

        return SUCCESS;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the CFile
     */
    public String getCFile() {
        return CFile;
    }

    /**
     * @param CFile the CFile to set
     */
    public void setCFile(String CFile) {
        this.CFile = CFile;
    }

    /**
     * @return the fps
     */
    public InputStream getFps() {
        return fps;
    }

    /**
     * @return the FileToCreate
     */
    public String getFileToCreate() {
        return FileToCreate;
    }

    /**
     * @param FileToCreate the FileToCreate to set
     */
    public void setFileToCreate(String FileToCreate) {
        this.FileToCreate = FileToCreate;
    }

    /**
     * @return the report
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(String report) {
        this.report = report;
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
     * @return the failedtoCreate
     */
    public String getFailedtoCreate() {
        return failedtoCreate;
    }

    /**
     * @param failedtoCreate the failedtoCreate to set
     */
    public void setFailedtoCreate(String failedtoCreate) {
        this.failedtoCreate = failedtoCreate;
    }
}
