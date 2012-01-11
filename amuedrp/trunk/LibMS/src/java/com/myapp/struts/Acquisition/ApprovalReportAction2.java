/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
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

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




/**
 *
 * @author edrp02
 */
public class ApprovalReportAction2 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    public static final String REPORT_DIRECTORY = "reports";
    Connection connection=null;
    Statement statement=null;
    ResultSet resultSet=null;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            List list=null;
             String library_id;
        String path = servlet.getServletContext().getRealPath("/");
System.out.println(path+"................");

        
  

path=path+"JasperReport";
System.out.println(path);

        try
        {

           HttpSession session=request.getSession();
             library_id=(String)session.getAttribute("library_id");
            String  sub_lib=(String)session.getAttribute("sublibrary_id");


        List<CirculationList_1_1> circheckInlist1=(List<CirculationList_1_1>)AcquisitionDao.approvalReport(library_id, sub_lib);
        // session.setAttribute("circheckInlist1", circheckInlist1);
        
        //return mapping.findForward(SUCCESS);
 System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+circheckInlist1.size());



    System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(path + "/approval1_1.jrxml");
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource;

// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
 dataSource = new JRBeanCollectionDataSource(circheckInlist1);
          JasperFillManager.fillReportToFile(path+"/approval1_1.jasper",map, dataSource);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" +
                                              "approval1_1.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "approval1_1.pdf");
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
 JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);

            //  System.out.println(dataSource.getData().getClass());


        }
        catch (JRException e)
        {
            System.out.println("asssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssss");
          e.printStackTrace();
        }
        return null;
    }
}
