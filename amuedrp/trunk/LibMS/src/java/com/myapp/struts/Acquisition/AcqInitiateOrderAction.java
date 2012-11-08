/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.VendorDAO;
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
public class AcqInitiateOrderAction extends org.apache.struts.action.Action {
  AcquisitionDao ado= new AcquisitionDao();
  AcqBibliographyDetails acqdao=new AcqBibliographyDetails();
  AcqOrderDao acqodao=new AcqOrderDao();
  AcqOrderHeader acqordrhr=new AcqOrderHeader();
  AcqOrderHeaderId acqorderhid =new AcqOrderHeaderId();
  VendorDAO vendao=new VendorDAO();
  AcquisitionDao  acqudao=new AcquisitionDao();
    /* forward name="success" path="" */
    
     public static final String REPORT_DIRECTORY = "reports";

    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           AcqOrderManagementActionForm acqorder=(AcqOrderManagementActionForm)form;
 

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String vendor=acqorder.getVendor();
        if(vendor==null)
        {  vendor=(String)request.getAttribute("selectedvendor");
     
        }

        String order_no=acqorder.getOrder_no();


       

        if(order_no==null)
        {order_no=(String)request.getAttribute("order_no");
      
        }


        String button=acqorder.getButton();
        if(button==null)
            button=(String)request.getAttribute("button1");


        String discount=acqorder.getDiscount();
        String order_date=acqorder.getOrder_date();
        String due_date=acqorder.getDue_date();
        String order_status=acqorder.getOrder_status();
        String cancel_reason=acqorder.getCancel_reason();
        List<AcqVendor> acqvendor=vendao.searchDoc5(library_id, sub_library_id);
System.out.println(vendor);

        List<ApprovalList> aa=ado.getApprovalList(library_id, sub_library_id,vendor);
     

        
            

             

               
         session.setAttribute("vendor1", acqvendor);
         session.setAttribute("opacList", aa);
         request.setAttribute("button", button);


        
      

          acqordrhr=acqodao.search1Orderno(order_no, library_id, sub_library_id);
          System.out.println(vendor+" "+button);




          if(button.equals("New Order"))
           {
                
                 

                   if(acqordrhr !=null)
     {
         String msg1="This Order No is Already Exist";
         request.setAttribute("msg1", msg1);
         return mapping.findForward("duplicateorderno");
     }
                   if(aa.isEmpty())
                   {
                    String msg1="We Have no List to Ordered";
                    request.setAttribute("msg1", msg1);
                    return mapping.findForward("duplicateorderno");

                   }



                 return mapping.findForward("new");
            }


          if(button.equals("Cancel Order") || button.equals("View Order") )
         {
                acqordrhr=acqodao.search1Orderno1(order_no, library_id, sub_library_id);
               
                if(acqordrhr==null){
            request.setAttribute("msg1", "Order No does not exists or Cancelled");
            return mapping.findForward("duplicate");
                }
                else
                {
                  AcqVendor acqVendor=acqudao.searchVendor(library_id,acqordrhr.getVendorId()) ;
                  List<ApprovalList> acq=null;
                  if(acqVendor!=null)
                  {
                  AcqCurrency demo=acqudao.searchVendorCurrency(library_id,acqVendor.getVendorCurrency());

if(demo!=null)
{
    acq=(List<ApprovalList>)acqudao.getViewApprovalList(library_id, sub_library_id, order_no);
}
else{
    acq=(List<ApprovalList>)acqudao.getViewApprovalList1(library_id, sub_library_id, order_no);
}
        
                  }
            session.setAttribute("orderhead", acqordrhr);
              session.setAttribute("orderlist",acq);

            return mapping.findForward("delete");

        }

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
path=path+"/JasperReport";

HashMap SIMPLE_DATA;

if(button.equals("Print Order"))
{
                acqordrhr=acqodao.search1Orderno1(order_no, library_id, sub_library_id);

                if(acqordrhr==null){
            request.setAttribute("msg1", "Order No does not exists or Cancelled");
            return mapping.findForward("duplicate");
                }
                else
                {
try
{
 AcqVendor acqVendor=acqudao.searchVendor(library_id,acqordrhr.getVendorId()) ;
                  List<ApprovalList> acq=null;
                  if(acqVendor!=null)
                  {
                  AcqCurrency demo=acqudao.searchVendorCurrency(library_id,acqVendor.getVendorCurrency());

if(demo!=null)
{
  

JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport1.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport3.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport6.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport8.jrxml");
JasperCompileManager.compileReportToFile(path + "/purchaseordersubreport.jrxml");
OutputStream ouputStream = response.getOutputStream();
response.setContentType("application/pdf");
HashMap map = new HashMap();
List<Library> newlib=new ArrayList<Library>();
List<AcqOrder1> newlib1=new ArrayList<AcqOrder1>();
List<AcqOrderHeader> newlib2=new ArrayList<AcqOrderHeader>();
List<AcqVendor> newlib3=new ArrayList<AcqVendor>();

List<AcqFinalDemandList> newlib4=new ArrayList<AcqFinalDemandList>();
List<AcqOrderHeader> newlib5=new ArrayList<AcqOrderHeader>();
List<AcqOrderHeader> newlib7=new ArrayList<AcqOrderHeader>();
List<AcqOrderHeader> newlib8=new ArrayList<AcqOrderHeader>();
List<ApprovalList> newlib9=new ArrayList<ApprovalList>();
 
newlib1=ReportDao.getList1();
newlib2=ReportDao.getList2(order_no);
newlib3=ReportDao.getList3(library_id,sub_library_id,newlib2.get(0).getVendorId());
System.out.print("Acq vendor List="+newlib3.size());
newlib5=ReportDao.getList2(order_no);
newlib7=ReportDao.getList2(order_no);
newlib8=ReportDao.getList2(order_no);


newlib9=(List<ApprovalList>)acqudao.getViewApprovalList(library_id, sub_library_id, order_no);


HashMap parameterMap = new HashMap();
parameterMap.put("sum", "10");
dataSource = new JRBeanCollectionDataSource(newlib1);
dataSource3 = new JRBeanCollectionDataSource(newlib2);
dataSource1 = new JRBeanCollectionDataSource(newlib3);
dataSource5 = new JRBeanCollectionDataSource(newlib4);
dataSource8=new JRBeanCollectionDataSource(newlib5);
dataSource10=new JRBeanCollectionDataSource(newlib7);
dataSource11=new JRBeanCollectionDataSource(newlib8);
dataSource12=new JRBeanCollectionDataSource(newlib9);
SIMPLE_DATA = new HashMap();
SIMPLE_DATA.put("datasource", dataSource);
SIMPLE_DATA.put("datasource1", dataSource1);
SIMPLE_DATA.put("datasource3", dataSource3);
SIMPLE_DATA.put("datasource5", dataSource5);
SIMPLE_DATA.put("datasource8", dataSource8);
SIMPLE_DATA.put("datasource10", dataSource10);
SIMPLE_DATA.put("datasource11", dataSource11);
SIMPLE_DATA.put("datasource12", dataSource12);
SIMPLE_DATA.put("sum", "10");
Map simpleMasterMap = new HashMap();
simpleMasterMap.put("master", "This is the Master JRMapCollectionDataSource");
simpleMasterMap.put("id", "datasource");
simpleMasterMap.put("id2", "datasource1");
simpleMasterMap.put("id3", "datasource3");
simpleMasterMap.put("id5", "datasource5");
simpleMasterMap.put("id7", "datasource8");
simpleMasterMap.put("id8", "datasource10");
simpleMasterMap.put("id9", "datasource11");
simpleMasterMap.put("id10", "datasource11");
simpleMasterMap.put("id12", "datasource12");
simpleMasterMap.put("sum", "10");
List simpleMasterList = new ArrayList();
simpleMasterList.add(simpleMasterMap);
simpleDS = new JRBeanCollectionDataSource(simpleMasterList);
map.put("SIMPLE_DATA", SIMPLE_DATA);
JasperFillManager.fillReportToFile(path+"/AircraftStateReport_1.jasper", map, simpleDS);
File file1 = new File(path + "/" +"AircraftStateReport_1.jrprint");
JasperPrint jasperPrint1 = (JasperPrint)JRLoader.loadObject(file1);
JRPdfExporter pdfExporter = new JRPdfExporter();
pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);
pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,path + "/" + "AircraftStateReport_1.pdf");
pdfExporter.exportReport();
JRExporter exporter = null;
exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint1, ouputStream);
}
else{
   

    JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport1.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_1.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport3.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport6.jrxml");
JasperCompileManager.compileReportToFile(path + "/AircraftStateReport_1_subreport8.jrxml");
JasperCompileManager.compileReportToFile(path + "/purchaseordersubreport_1.jrxml");
OutputStream ouputStream = response.getOutputStream();
response.setContentType("application/pdf");
HashMap map = new HashMap();
List<Library> newlib=new ArrayList<Library>();
List<AcqOrder1> newlib1=new ArrayList<AcqOrder1>();
List<AcqOrderHeader> newlib2=new ArrayList<AcqOrderHeader>();
List<AcqVendor> newlib3=new ArrayList<AcqVendor>();

List<AcqFinalDemandList> newlib4=new ArrayList<AcqFinalDemandList>();
List<AcqOrderHeader> newlib5=new ArrayList<AcqOrderHeader>();
List<AcqOrderHeader> newlib7=new ArrayList<AcqOrderHeader>();
List<AcqOrderHeader> newlib8=new ArrayList<AcqOrderHeader>();
List<ApprovalList> newlib9=new ArrayList<ApprovalList>();

newlib1=ReportDao.getList1();
newlib2=ReportDao.getList2(order_no);
newlib3=ReportDao.getList3(library_id,sub_library_id,newlib2.get(0).getVendorId());
System.out.print("Acq vendor List="+newlib3.size());
newlib5=ReportDao.getList2(order_no);
newlib7=ReportDao.getList2(order_no);
newlib8=ReportDao.getList2(order_no);


newlib9=(List<ApprovalList>)acqudao.getViewApprovalList1(library_id, sub_library_id, order_no);


HashMap parameterMap = new HashMap();
parameterMap.put("sum", "10");
dataSource = new JRBeanCollectionDataSource(newlib1);
dataSource3 = new JRBeanCollectionDataSource(newlib2);
dataSource1 = new JRBeanCollectionDataSource(newlib3);
dataSource5 = new JRBeanCollectionDataSource(newlib4);
dataSource8=new JRBeanCollectionDataSource(newlib5);
dataSource10=new JRBeanCollectionDataSource(newlib7);
dataSource11=new JRBeanCollectionDataSource(newlib8);
dataSource12=new JRBeanCollectionDataSource(newlib9);
SIMPLE_DATA = new HashMap();
SIMPLE_DATA.put("datasource", dataSource);
SIMPLE_DATA.put("datasource1", dataSource1);
SIMPLE_DATA.put("datasource3", dataSource3);
SIMPLE_DATA.put("datasource5", dataSource5);
SIMPLE_DATA.put("datasource8", dataSource8);
SIMPLE_DATA.put("datasource10", dataSource10);
SIMPLE_DATA.put("datasource11", dataSource11);
SIMPLE_DATA.put("datasource12", dataSource12);
SIMPLE_DATA.put("sum", "10");
Map simpleMasterMap = new HashMap();
simpleMasterMap.put("master", "This is the Master JRMapCollectionDataSource");
simpleMasterMap.put("id", "datasource");
simpleMasterMap.put("id2", "datasource1");
simpleMasterMap.put("id3", "datasource3");
simpleMasterMap.put("id5", "datasource5");
simpleMasterMap.put("id7", "datasource8");
simpleMasterMap.put("id8", "datasource10");
simpleMasterMap.put("id9", "datasource11");
simpleMasterMap.put("id10", "datasource11");
simpleMasterMap.put("id12", "datasource12");
simpleMasterMap.put("sum", "10");
List simpleMasterList = new ArrayList();
simpleMasterList.add(simpleMasterMap);
simpleDS = new JRBeanCollectionDataSource(simpleMasterList);
map.put("SIMPLE_DATA", SIMPLE_DATA);
JasperFillManager.fillReportToFile(path+"/AircraftStateReport_1_1.jasper", map, simpleDS);
File file1 = new File(path + "/" +"AircraftStateReport_1_1.jrprint");
JasperPrint jasperPrint1 = (JasperPrint)JRLoader.loadObject(file1);
JRPdfExporter pdfExporter = new JRPdfExporter();
pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);
pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,path + "/" + "AircraftStateReport_1_1.pdf");
pdfExporter.exportReport();
JRExporter exporter = null;
exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint1, ouputStream);




}

                  }

}
catch(Exception e)
{
    e.printStackTrace();
}




                }

           }
          
             
return null;
    }
}
