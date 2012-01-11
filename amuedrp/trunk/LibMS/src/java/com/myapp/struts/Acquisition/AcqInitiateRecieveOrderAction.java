/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import java.io.*;
import java.util.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
 import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 * @author maqbool
 */
public class AcqInitiateRecieveOrderAction extends org.apache.struts.action.Action {
  AcquisitionDao ado= new AcquisitionDao();
  AcqBibliographyDetails acqdao=new AcqBibliographyDetails();
  AcqOrderDao acqodao=new AcqOrderDao();
  AcqOrderHeader acqordrhr=new AcqOrderHeader();
  AcqOrderHeaderId acqorderhid =new AcqOrderHeaderId();
  List<ApprovalList> acqorder1= new ArrayList<ApprovalList>();
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    public static final String REPORT_DIRECTORY = "reports";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           AcqOrderManagementActionForm acqorder=(AcqOrderManagementActionForm)form;
 

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String vendor_id=acqorder.getVendor();
        String order_no=acqorder.getOrder_no();
        String button=acqorder.getButton();
         String  recieving_no=acqorder.getReceiving_no();
      
         request.setAttribute("button", button);
        acqordrhr=acqodao.searchOrderHeaderByVendor(order_no, vendor_id, library_id, sub_library_id);
        acqorder1=acqodao.searchOrderByVendor(order_no,library_id, sub_library_id);
        // System.out.println(vendor+" "+button);

          if(button.equals("New Recieve Order"))
            {
            if(acqordrhr ==null)
             {
         String msg1="This Order No does'nt found";
         request.setAttribute("msg1", msg1);
         return mapping.findForward("duplicateorderno");
             }
         else
             {
           System.out.println("List of "+vendor_id+" for order no "+order_no+" size="+acqorder1.size());
           if(acqorder1.size()==0){
         String msg1="Given Order No Has no Pending List to Receive";
         request.setAttribute("msg1", msg1);
         return mapping.findForward("duplicateorderno");


           }
              session.setAttribute("opacList1",acqorder1);
             return mapping.findForward("delete");
            }
  }

        
   if(button.equals("View Recieve Order")){

          AcqRecievingHeader aa1=ado.getRecieveOrder(library_id,  sub_library_id, recieving_no,order_no,vendor_id);
          if(aa1==null){
                    String msg1="This Recieving No Not Found";
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("duplicateorderno");
            }
acqorder.setReceiving_no(recieving_no);
acqorder.setRecieved_date(aa1.getRecievedDate());
acqorder.setRecieved_by(aa1.getRecievedDate());
acqorder.setOrder_no(aa1.getOrderNo());
acqorder.setVendor(aa1.getVendorId());

      acqordrhr=acqodao.searchOrderHeaderByVendor(order_no, vendor_id, library_id, sub_library_id);
        acqorder1=acqodao.searchOrderByVendor1(order_no,library_id, sub_library_id,recieving_no);
       
         session.setAttribute("opacList2",acqorder1);
         return mapping.findForward("duplicateapprovalno");
        }

        JRBeanCollectionDataSource dataSource=null;
JRBeanCollectionDataSource dataSource1=null;
JRBeanCollectionDataSource dataSource2=null;
JRBeanCollectionDataSource dataSource3=null;
JRBeanCollectionDataSource dataSource5=null;
JRBeanCollectionDataSource dataSource8=null;
JRBeanCollectionDataSource dataSource10=null;
JRBeanCollectionDataSource dataSource11=null;
JRBeanCollectionDataSource dataSource12=null;
JRDataSource simpleDS;
String path = servlet.getServletContext().getRealPath("/");
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
path=path+"/JasperReport";

HashMap SIMPLE_DATA;

if(button.equals("Print"))
{
   System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

               
try
{
     acqordrhr=acqodao.searchOrderHeaderByVendor(order_no, vendor_id, library_id, sub_library_id);
        acqorder1=acqodao.searchOrderByVendor1(order_no,library_id, sub_library_id,recieving_no);
      
          System.out.println("Compiling report..."+acqorder1.size());
          JasperCompileManager.compileReportToFile(path + "/recieveReport.jrxml");
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource20;

// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
 dataSource20 = new JRBeanCollectionDataSource(acqorder1);
          JasperFillManager.fillReportToFile(path+"/recieveReport.jasper",map, dataSource20);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" +
                                              "recieveReport.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "recieveReport.pdf");
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
 JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);


        return mapping.findForward(SUCCESS);






 


                  

}
catch(Exception e)
{
    e.printStackTrace();
}

 }

 

 return null;
  }
}
