<%-- 
    Document   : MonthlySalarySingle
    Created on : Jul 21, 2010, 6:38:58 PM
    Author     : Algox
--%>

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
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%


    out.println("PDF");
                
            Connection conn = new CommonDB().getConnection();
            System.out.println("Connection Established");
            String path = application.getRealPath("/");
            HashMap map = new HashMap();
            map.put("org_name", new OrgProfileDB().getProfile());
            map.put("report_title", "Salary Slip for the Month of" +new CommonDB().getDescriptiveDate());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "report1.jasper", map, conn);
            System.out.println("Report Created...");

            OutputStream ouputStream = response.getOutputStream();

            JRExporter exporter = null;

             
                exporter = new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            
            try {
                exporter.exportReport();
            } catch (JRException e) {
                throw new ServletException(e);
            } finally {
                if (ouputStream != null) {
                    try {
                        ouputStream.close();
                    } catch (IOException ex) {
                    }
                }
            }

%>