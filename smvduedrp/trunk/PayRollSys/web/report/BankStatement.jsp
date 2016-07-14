<%-- 
    Document   : MonthlySalarySingle
    Created on : Jul 21, 2010, 6:38:58 PM
    Author     : 

*  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  Copyright (c) 2014 - 2016 ETRG, IITK.
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
** Modification date 14 July 2016,  Om Prakash<omprakashkgp@gmail.com> , IITK  
--%>

<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.lowagie.text.Document"%>
<%@page import="java.io.File"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporterParameter"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JRAbstractExporter"%>
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
            map.put("org_id", ub.getUserOrgCode());
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "JasperFile/bankReport.jasper", map, conn);
            //System.out.println("Report Created...");
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
