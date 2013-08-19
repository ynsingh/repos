package org.smvdu.payroll.api.pf.ReportGen;   
 import net.sf.jasperreports.engine.JRException;
 import net.sf.jasperreports.engine.design.JasperDesign;
 import net.sf.jasperreports.engine.xml.JRXmlLoader;
 import net.sf.jasperreports.engine.xml.JRXmlWriter;
 import net.sf.jasperreports.engine.util.JRLoader;
 import net.sf.jasperreports.engine.JasperReport;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import javax.faces.context.FacesContext;
     
    public class JasperToXml {
    public static String sourcePath, destinationPath, xml;
    public static JasperDesign jd = new JasperDesign();
     
    public void jasperfileresult() {
       
    try
    {
         //String path1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/JasperFile"+"/salaryslip.jrxml");
         //String sourcePath = "/home/guest/18june2013/PayRollSys/web/JasperFile/salaryslip.jrxml";
         //String sourcePath = System.getProperty("user.home")+"/18june2013/PayRollSys/web/JasperFile/salaryslip.jrxml";
	 //System.out.println("----------> Checking .sourcePath.---"+sourcePath);
         //JasperCompileManager.compileReportToFile(sourcePath);
               
         //System.out.println("----------> Checking UserInfo..."+JasperCompileManager.compileReportToFile(sourcePath));
       //JasperCompileManager.compileReportToFile("destinationPath");
       // for converting a JasperReport object (compiled .jasper file) to a JasperDesign object
       //jd = JRXmlLoader.load(sourcePath);
    }
    catch(Exception e){
    e.printStackTrace();
    //System.out.println(System.getProperty("user.dir"));
    }
    //JRXmlWriter.writeReport(jd, destinationPath); //
    }
    }
