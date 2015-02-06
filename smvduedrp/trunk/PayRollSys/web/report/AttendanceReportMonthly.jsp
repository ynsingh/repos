<%-- 
    Document   : AttendanceReportMonthly.jsp
    Created on : Dec 29, 2014, 4:41:30 PM
    Author     : guest
--%>
<%--
<%@page import="org.smvdu.payroll.user.OrgLogoDB"%>
<%@page import="java.awt.Image"%>
<%@page import="java.io.IOException"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.smvdu.payroll.beans.UserInfo"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.smvdu.payroll.beans.db.CommonDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

</html>
<%--
<%
            String type = request.getParameter("type");
            if(type==null)
                {
                  type="PDF";
                }
            //System.out.println("Type : "+type);
            Connection conn = new CommonDB().getConnection();
            //String path = application.getRealPath("/");
            UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("org_name", ub.getOrgName());
            map.put("title", "Attendance report for the Month of " +ub.getCurrentMonthName());
            map.put("month", ub.getCurrentMonth()-1);
            map.put("year", ub.getCurrentYear());
            map.put("org_code",ub.getUserOrgCode());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "JasperFile/attendanceReport.jasper", map, conn);
            System.out.println("=>Om Prakash--->Report of Attendance of Employee Created successful on monthly basis in HTML formate...");
            OutputStream ouputStream = response.getOutputStream();
            JRExporter exporter = null;
            try {
                exporter = new JRHtmlExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
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

--%>