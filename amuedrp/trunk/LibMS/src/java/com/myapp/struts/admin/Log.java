/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import java.io.*;
import java.util.*;
import com.myapp.struts.AdminDAO.*;
import com.myapp.struts.hbm.Logs;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author EdRP-05
 */
public class Log extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
      
    private HashMap SIMPLE_DATA;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JRBeanCollectionDataSource dataSource=null;
     JRDataSource simpleDS;
          String path = servlet.getServletContext().getRealPath("/");

             HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
      
        List<Logs> bb=LogsDAO.log();
        session.setAttribute("opacListb", bb);
          System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(path + "/log1.jrxml");
       
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 //JRBeanCollectionDataSource dataSource;

// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
 dataSource = new JRBeanCollectionDataSource(bb);

//JasperFillManager.fillReportToFile(path+"/log1.jasper", map, simpleDS);



         JasperFillManager.fillReportToFile(path+"/log1.jasper",map, dataSource);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" +
                                              "log1.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "log1.pdf");
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
 JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);


        return mapping.findForward(SUCCESS);
}
}
