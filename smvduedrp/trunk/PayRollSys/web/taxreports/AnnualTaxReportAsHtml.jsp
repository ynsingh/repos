<%-- 
    Document   : AnnualTaxReportAsHtml
    Created on : 12 Apr, 2012, 11:19:58 AM
    Author     : smvdu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.smvdu.payroll.api.annualreport.AnnualPFReport"%>
<%@page import="org.smvdu.payroll.api.pf.ReportGen.ReportClass"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JExcelApiExporter"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporterParameter"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JRAbstractExporter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.smvdu.payroll.beans.composite.SessionController"%>
<%@page import="org.smvdu.payroll.api.pf.PFOpeningBalanceDB"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.smvdu.payroll.user.OrgLogoDB"%>
<%@page import="java.awt.Image"%>
<%@page import="org.smvdu.payroll.beans.composite.OrgController"%>
<%@page import="org.smvdu.payroll.beans.UserInfo"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="org.smvdu.payroll.user.FileUploadBean"%>
<%@page import="net.sf.jasperreports.engine.export.JRXmlExporter"%>
<%@page import="org.smvdu.payroll.beans.db.OrgProfileDB"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.smvdu.payroll.beans.db.CommonDB"%>
<%@page import="java.io.IOException"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Connection cn = new CommonDB().getConnection();
            int ssCode = 0;
            ResultSet rst;
            PreparedStatement pst = cn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if (rst.next()) {
                ssCode = rst.getInt(1);
            }

            HashMap map = new HashMap();
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            //HashMap map = new HashMap();
            //System.out.println("In JSP : "+new TaxController().getEffectiveInv());
            map.put("org_name", ub.getOrgName());
            map.put("org_title", "Annual Tax Report");
            map.put("year", ""+ub.getCurrentYear());
            
            Image img = new OrgLogoDB().loadLogoImage();
            String path = application.getRealPath("/");
            OutputStream ouputStream = response.getOutputStream();
            try
            {
                JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "JasperFile/AnnualIT.jasper", map, cn);
                JRExporter exporter = new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.exportReport();
            }
            catch (JRException e)
            {
                e.printStackTrace();
                throw new ServletException(e);
            }
        %>
    </body>
</html>
