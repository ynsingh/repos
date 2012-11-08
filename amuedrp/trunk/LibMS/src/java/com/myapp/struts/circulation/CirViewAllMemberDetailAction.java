/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirDAO.CirculationDAO;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

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
public class CirViewAllMemberDetailAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id=null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationDAO cirdao=new CirculationDAO();
        CirViewAll1ActionForm  cir =  (CirViewAll1ActionForm)form;
       String sub_lib =cir.getCMBSUBLib();
      String memid =cir.getTXTTITLE();
      
     String year1 =cir.getRegistrationdate();
     String year2=cir.getRegistrationdatefrom();
     String expirydate=cir.getExpirydate();
     String status=cir.getStatus();
     String fac=cir.getTXTFACULTY();
     String dept=cir.getTXTDEPT();
     String course=cir.getTXTCOURSE();
     String title=cir.getCMBSORT();
   //  String year3=cir.getRequestdate();
//     String year4=cir.getRequestdate1();


        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");

        String button=cir.getButton();

        
        session.removeAttribute("cirmemdetaillist");
        session.removeAttribute("searchviewall");
      
        if(button.equalsIgnoreCase("Find") && cir.getCheckbox()==null)
      {
     List searchviewall  = (List)cirdao.ViewAllSearchReport(library_id, sub_lib, year1, year2, memid, status, fac, dept, course, title,(String)session.getAttribute("login_id"));
       
     System.out.println(searchviewall.size());
          session.setAttribute("searchviewall",searchviewall);
       
       return mapping.findForward("success");

      }
      //condition check
      if(button.equalsIgnoreCase("Print")){
        
         List list=null;
             
        String path = servlet.getServletContext().getRealPath("/");
        

path=path+"/JasperReport";

  try
        {
             CirViewAll1ActionForm ccra   =(CirViewAll1ActionForm)form;
        

        List circheckInlist1=(List)cirdao.ViewAllSearchReport(library_id, sub_lib, year1, year2, memid,status,  fac, dept, course, title,(String)session.getAttribute("login_id"));

      System.out.println("@@@@@@@@@@@@+///////////////////////////////////////////////////////////////////////////////////////"+circheckInlist1.size());





    System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(path + "/CirViewAll.jrxml");
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource;

// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
 dataSource = new JRBeanCollectionDataSource(circheckInlist1);
 

          JasperFillManager.fillReportToFile(path+"/CirViewAll.jasper",map, dataSource);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" + "CirViewAll.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path + "/" + "CirViewAll.pdf");
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




      }
 if(button.equalsIgnoreCase("Find") && cir.getCheckbox().equalsIgnoreCase("on"))
      {
     System.out.println("In Find");
  
        List circheckInlist2=(List)cirdao.ViewAllThoughOpacReq((String)session.getAttribute("library_id"), sub_lib, year1, year2, memid,  fac, dept, course, title,(String)session.getAttribute("login_id"));

        
        if(circheckInlist2.size()>0 && circheckInlist2!=null)
          session.setAttribute("RequestFromOPAC", circheckInlist2);

            System.out.println("In Find1");
          return mapping.findForward("success1");
      }
if(button.equalsIgnoreCase("Print") && cir.getCheckbox().equalsIgnoreCase("on")){

   

         List list=null;

        String path = servlet.getServletContext().getRealPath("/");
         
path=path+"/JasperReport";

  try
        {
             CirViewAll1ActionForm ccra   =(CirViewAll1ActionForm)form;

        List circheckInlist2=(List)cirdao.ViewAllThoughOpacReq((String)session.getAttribute("library_id"), sub_lib, year1, year2, memid,  fac, dept, course, title,(String)session.getAttribute("login_id"));




    System.out.println("Compiling report1111...");
          JasperCompileManager.compileReportToFile(path + "/CirViewAll1.jrxml");
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource;

 System.out.println("iqballlllllllllllllllllllllllllllll");
 HashMap map = new HashMap();

 dataSource = new JRBeanCollectionDataSource(circheckInlist2);

          JasperFillManager.fillReportToFile(path+"/CirViewAll1.jasper",map, dataSource);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" + "CirViewAll1.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path + "/" + "CirViewAll1.pdf");
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




      }

        return null;






        
    }
}
