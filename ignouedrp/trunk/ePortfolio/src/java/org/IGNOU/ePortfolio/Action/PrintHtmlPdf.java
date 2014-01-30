/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;


/**
 *
 * @author Amit
 */
public class PrintHtmlPdf {
    final Logger logger = Logger.getLogger(this.getClass());
  
    public String HtmlToPdf(String pdfData,String FILE){
    try {
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            worker.parseXHtml(pdfWriter, document,new InputStreamReader(new ByteArrayInputStream(pdfData.getBytes("UTF-8"))));
            document.close();
           } catch (Exception e) {
             
               logger.warn("Error in PrintHtmlPdf with Data"+pdfData, e);
        }
        return FILE;
    
    }
    
}
