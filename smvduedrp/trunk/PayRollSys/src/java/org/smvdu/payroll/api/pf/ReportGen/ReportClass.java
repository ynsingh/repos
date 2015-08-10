/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.pf.ReportGen;

import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.smvdu.payroll.api.pf.PFOpeningBalanceDB;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.user.OrgLogoDB;

/**
 *
 * @author ERP
 */
public class ReportClass {

    /** Creates a new instance of ReportClass */
    Connection cn;
    private PreparedStatement pst;
    private ResultSet rst;
    public ReportClass() {
    }
    private HashMap map;

    public void AnnualPfReport() {
        try
        {
            int sessionID = 0;
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            ArrayList<Integer> sessionList = new ArrayList<Integer>();
            PreparedStatement pst = cn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if(rst.next())
                {
                    sessionID = rst.getInt(1);
                }
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            map.put("org_name", ub.getOrgName());
            map.put("title", "Annual PF Report ");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("fin_session", sessionID);
            new PFOpeningBalanceDB().refreshReport(sessionID);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/AnnualPFReport.jasper", map, cn);
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

 //servletOutputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void annualPFExcel()
    {
        try
        {
            int sessionID = 0;
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            PreparedStatement pst = cn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if(rst.next())
                {
                    sessionID = rst.getInt(1);
                }
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            map.put("org_name", ub.getOrgName());
            map.put("title", "Annual PF Report ");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("fin_session", sessionID);
            new PFOpeningBalanceDB().refreshReport(sessionID);
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            //Connection conn = new CommonDB().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(path+File.separator+"JasperFile/AnnualPFReport.jasper", map, cn);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
                response.setContentType("application/ms-excel");
                JRXlsExporter exporterXLS = new JRXlsExporter();
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporterXLS.exportReport();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public HashMap getMap() {
        return map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }
    
    /**
    * Render a report in PDF format using the supplied report data.
    */
    public void employeeSalarySlipAsPDF() {
        try
        {
            
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            //System.out.println("orgname===in report calss="+ub.getOrgName()+ub.getCurrentMonth()+ub.getCurrentYear()+ub.getProfile().getCode());
            map.put("org_title", "Salary Slip for the Month of " + ub.getCurrentMonthName());
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            if(img!=null){
                map.put("org_logo", img);
            }
            map.put("emp_code", ub.getProfile().getCode());
            map.put("org_id", ub.getUserOrgCode());
            //System.out.println("empcode===dibcheck=="+ub.getProfile().getCode()+"\nempname====="+ub.getProfile().getName());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/salaryslipone.jasper", map, cn);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            //System.out.println("datakks===="+ub.getOrgName()+":"+ub.getCurrentMonth()+":"+ub.getCurrentYear()+":"+path);
            JRAbstractExporter exporter1 = new JRPdfExporter();
            Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
            parameterExport.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
            parameterExport.put(JRPdfExporterParameter.OUTPUT_STREAM, servletOutputStream);
            response.setContentType("application/pdf");
            exporter1.setParameters(parameterExport);
            exporter1.exportReport();
            servletOutputStream.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
   /**
    * Render a report in HTML format using the supplied report data.
    */
    public void employeeSalarySlipAsHTML() {
        try
        {
            
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            //System.out.println("orgname===in report calss="+ub.getOrgName()+ub.getCurrentMonth()+ub.getCurrentYear()+ub.getProfile().getCode());
            map.put("org_title", "Salary Slip");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            if(img!=null){
                map.put("org_logo", img);
            }
            map.put("emp_code", ub.getProfile().getCode());
            map.put("org_id", ub.getUserOrgCode());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/salaryslipone.jasper", map, cn);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            JRHtmlExporter exporter=new JRHtmlExporter();
            Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
            parameterExport.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
            parameterExport.put(JRPdfExporterParameter.OUTPUT_STREAM, servletOutputStream);
           //response.setContentType("application/html");
            exporter.setParameters(parameterExport);
            exporter.exportReport();
            servletOutputStream.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void employeeSalarySlipAsExcel() {
        try
        {
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            //System.out.println("orgname===in report calss="+ub.getOrgName()+ub.getCurrentMonth()+ub.getCurrentYear()+ub.getProfile().getCode());
            map.put("org_title", "Salary Slip");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("emp_code", ub.getProfile().getCode());
            //System.out.println("empcode===dibcheck=="+ub.getProfile().getCode()+"\nempname====="+ub.getProfile().getName());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/salaryslipone.jasper", map, cn);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            //ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            facesContext.responseComplete();
           
           JRCsvExporter exporterCSV = new JRCsvExporter();
            Map<JRCsvExporter, Object> parameterExport = new HashMap<JRCsvExporter, Object>();
            exporterCSV.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterCSV.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
            exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
            exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
            exporterCSV.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, true);
            exporterCSV.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, true);
            response.setContentType("text/csv");
            exporterCSV.setParameters(parameterExport);
            exporterCSV.exportReport();
            response.setHeader("Content-Disposition", "attachment;filename=" +"example" + ".csv");
            response.getOutputStream().write(buffer.toString().getBytes());
            response.flushBuffer();
            
          /*  JRXlsExporter exporterXLS = new JRXlsExporter();
            Map<JRXlsExporter, Object> parameterExport = new HashMap<JRXlsExporter , Object>();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,servletOutputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.TRUE);

            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);

            exporterXLS.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED,Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.TRUE);

            exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS,Boolean.FALSE);
            //response.setContentType("application/vnd.ms-excel");
            exporterXLS.setParameters(parameterExport);
           
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename="+jasperPrint+" ");
            response.getOutputStream().write(os.toByteArray());
            response.flushBuffer();
            exporterXLS.exportReport();
            servletOutputStream.close();*/

    
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
     }   
}
