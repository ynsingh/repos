/* AttendancePDF
 * Created on 29 January 2015   
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.pf.ReportGen;
  

import java.awt.Image;
import java.io.File; 
//import java.io.OutputStream;
import java.sql.Connection;
import java.text.DateFormatSymbols;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.setup.Attendance;
import org.smvdu.payroll.user.OrgLogoDB;
/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
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
*AttendancePDF Class has been created for the report generation in monthly basis of 
* Attendance of employee in PDF and HTML formate.
*
* @author Om Prakash <omprakashkgp@gmail.com>, IITK
*/

public class AttendancePDF {
    

    /** Creates a new instance of AttendancePDF */
    public AttendancePDF() {
    }
   
    /* 
       this mathed is for attendance report generation, monthly basis in PDF formate
    */
    public ArrayList<Attendance> Attendance(int month, int year) {
        
        try
        {
                           
           // JasperToXml abc= new JasperToXml();   
           // abc.jasperfileresult();
            Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("org_name", ub.getOrgName());
            map.put("title", "Attendance Report for the Month of " +getMonthName(month)+", "+year);
            //map.put("title", "Attendance Report for the Month of " +ub.getCurrentMonthName());
            //int mth= (ub.getCurrentMonth()-1);
            map.put("month", month);
            map.put("year", year);
            map.put("org_code",ub.getUserOrgCode());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/attendanceReport.jasper", map, cn);
            //System.out.print("=>Om Prakash--->Report of Attendance of Employee Created successful on monthly basis in pdf formate...");
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            JRAbstractExporter exporter1 = new JRPdfExporter();
            Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
            parameterExport.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
            parameterExport.put(JRPdfExporterParameter.OUTPUT_STREAM, servletOutputStream);
            response.setContentType("application/pdf");
            exporter1.setParameters(parameterExport);
            exporter1.exportReport();
            servletOutputStream.close();
        } catch (Exception ex) {
           // throw new ServletException(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return null;
    }
    
    /* 
        this mathed is for attendance report generation, monthly basis in HTML formate
    */
    public ArrayList<Attendance> AttendanceHTML(int month, int year) throws ServletException{
        try{
                Connection conn = new CommonDB().getConnection();
                //String path = application.getRealPath("/");
                UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                HashMap map = new HashMap();
                Image img = new OrgLogoDB().loadLogoImage();
                map.put("org_logo", img);
                map.put("org_name", ub.getOrgName());
                map.put("title", "Attendance report for the Month of " +getMonthName(month)+", "+year);
                map.put("month", month);
                map.put("year", year);
                map.put("org_code",ub.getUserOrgCode());
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                JasperPrint jasperPrint = JasperFillManager.fillReport(path + "/" + "JasperFile/attendanceReport.jasper", map, conn);
                //System.out.println("=>Om Prakash--->Report of Attendance of Employee Created successful on monthly basis in HTML formate...");
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                ServletOutputStream servletOutputStream = response.getOutputStream();
                facesContext.responseComplete();
                //OutputStream ouputStream = response.getOutputStream();
                JRExporter exporter = new JRHtmlExporter();
                Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                exporter.exportReport();
                servletOutputStream.close();
           } 
           catch(Exception ex){
                
          }
          return null;
    }
    
    /*
      @this mathed is call for display the name of selected month. 
    */
    public static String getMonthName(int month) {
    String monthName = "invalid";
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    if (month >= 0 && month <= 11 ) {
        monthName = months[month];
    }
    return monthName;
   }
} 
    
