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
*
--%>

<%@page import="org.smvdu.payroll.beans.SessionMaster"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="org.smvdu.payroll.api.security.LoginManager"%>
<%@page import="javax.faces.event.PhaseEvent"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="com.lowagie.text.Document"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="net.sf.jasperreports.engine.JRAbstractExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporterParameter"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRTextExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRTextExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRTextExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRTextExporter"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
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
            String type = request.getParameter("type");
            if (type == null) {
                type = "HTML";
            }
            //System.out.println("Type : "+type);
            Connection conn = new CommonDB().getConnection();
            response.setContentType("bin");
            SessionController session_co = new SessionController();
            SessionMaster sm = new SessionMaster();
            
            String path = application.getRealPath("/");

            int ssCode = 0;
            ResultSet rst;
            PreparedStatement pst = conn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if(rst.next())
                {
                    ssCode = rst.getInt(1);
                }

            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            //map.put("title", "Annual PF Report ");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("fin_session", ssCode);
            System.out.println("Session : "+ssCode);
            File jasperFile = new File((path + "/" + "JasperFile/pfReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile.getPath(), map, conn);
            //System.out.println("Report Created...");
            OutputStream ouputStream = response.getOutputStream();
            JRExporter exporter = null;


            System.out.println("Path : " + path);
            try {
                if (type.equals("HTML") == true) {
                    System.out.println("Director : " + application.getRealPath(" "));
                    exporter = new JRHtmlExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporter.exportReport();
                } 
                else if(type.equals("PDF") == true)
                {
                       //  Getting The Name Of The System Where Project Is Going On
                    com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
                    response.setContentType("application/pdf;charset=UTF-8");
                    response.setContentLength((path + "/" + "PFReport.jasper").length());


                    //Code For Creating Empty PDF Document For Genrating Report

                    OutputStream file = new FileOutputStream(new File("C:\\Users\\" + NTSystem.getName() + "\\Desktop\\Commulative.pdf"));
                    Document document = new Document();
                    PdfWriter.getInstance(document, file);
                    document.open();
                    document.add(new Paragraph(""));
                    document.add(new Paragraph("k"));
                    document.close();
                    file.close();

                    // Code For Printing Report In PDF Document

                    ServletOutputStream out1 = response.getOutputStream();
                    JRAbstractExporter exporter1 = new JRPdfExporter();
                    Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
                    parameterExport.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    parameterExport.put(JRPdfExporterParameter.OUTPUT_WRITER, out1);
                    parameterExport.put(JRPdfExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\" + NTSystem.getName() + "\\Desktop\\Commulative.pdf");
                    exporter1.setParameters(parameterExport);
                    exporter1.exportReport();
                    out1.flush();
                    out1.close();
                }


            } catch (Exception e) {
                throw new ServletException(e);
            } finally {
                try {
                    if (ouputStream != null) {
                        ouputStream.flush();
                        ouputStream.close();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

%>
