/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import java.sql.*;
import java.io.*;
import java.util.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.data.ListOfArrayDataSource;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRHibernateListDataSource;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.DocumentDetails;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
/**
 *
 * @author EdRP-05
 */
public class PrintCardItemListAction6 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
      BibliographicEntryDAO dao=new BibliographicEntryDAO();
      AcquisitionDao dao1=new AcquisitionDao();
    private HashMap SIMPLE_DATA;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JRBeanCollectionDataSource dataSource=null;
     JRDataSource simpleDS;
          String path = servlet.getServletContext().getRealPath("/");

path=path+"/JasperReport";

             HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
     
        List<DocumentDetails> bb=dao1.searchDoc5( library_id, sub_library_id);
        session.setAttribute("opacListb", bb);
          System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(path + "/approval_1.jrxml");
       
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");



 HashMap map = new HashMap();
 dataSource = new JRBeanCollectionDataSource(bb);




 
         JasperFillManager.fillReportToFile(path+"/approval_1.jasper",map, dataSource);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" +
                                              "approval_1.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "approval_1.pdf");
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
 JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);


        return mapping.findForward(SUCCESS);
}
}
