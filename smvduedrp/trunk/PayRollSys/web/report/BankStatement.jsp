<%-- 
    Document   : MonthlySalarySingle
    Created on : Jul 21, 2010, 6:38:58 PM
    Author     : Algox
--%>

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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String type = request.getParameter("type");
            if(type==null)
                {
                  type="PDF";
                }
            //System.out.println("Type : "+type);
            Connection conn = new CommonDB().getConnection();
            //System.out.println("Connection Established");
            String path = application.getRealPath("/");
            UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Bank Statement for the Month of " +ub.getCurrentMonthName());
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "bankReport.jasper", map, conn);
            //System.out.println("Report Created...");
            OutputStream ouputStream = response.getOutputStream();
            JRExporter exporter = null;
            if(type.equalsIgnoreCase("HTML"))
                {
            exporter = new JRHtmlExporter();
            }
            else if(type.equalsIgnoreCase("PDF"))
                {
                   exporter = new JRPdfExporter();
                   response.setContentType("application/pdf");
                }
            else if(type.equalsIgnoreCase("Excel"))
                {
                   exporter = new JRXlsExporter();
                }
            else if(type.equalsIgnoreCase("XML"))
                {
                   exporter = new JRXmlExporter();
                }
           
            
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