<%-- 
    Document   : MonthlySalarySingle
    Created on : Jul 21, 2010, 6:38:58 PM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*  Modified Date: 17 Sep 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
--%>

<%--@page import="org.smvdu.payroll.api.pf.ReportGen.JasperToXml"--%>
<%@page import="org.smvdu.payroll.api.annualreport.AnnualPFReport"%>
<%--  @page import="org.smvdu.payroll.api.pf.ReportGen.ButtonText" --%>
<%@page import="org.smvdu.payroll.api.pf.ReportGen.ReportClass"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JExcelApiExporter"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFSheet"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporterParameter"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JRAbstractExporter"%>
<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="com.lowagie.text.Document"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            //System.out.println("Type : "+type);
            Connection conn = new CommonDB().getConnection();
            SessionController session_co = new SessionController();
            //response.setContentType("bin");



            int ssCode = 0;
            ResultSet rst;
            PreparedStatement pst = conn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if (rst.next()) {
                ssCode = rst.getInt(1);
            }





            //System.out.println("Connection Established");
            String path = application.getRealPath("/");
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Annual PF Report ");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            
            //new PFOpeningBalanceDB().refreshReport();
            map.put("org_logo", img);
            map.put("fin_session", ssCode);


            System.out.println("Enter String Here : " + path);
            //JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "AnnualPFReport.jasper", map, conn);
            //System.out.println("Report Created...");
            OutputStream ouputStream = response.getOutputStream();
            try
            {
               JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "JasperFile/AnnualPFReport.jasper", map, conn);
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
            finally
            {
                try
                {
                    ouputStream.flush();
                    ouputStream.close();
                    conn.close();
                }
                catch (Exception ex)
                {
                    throw new ServletException(ex);
                }
            }


%>
