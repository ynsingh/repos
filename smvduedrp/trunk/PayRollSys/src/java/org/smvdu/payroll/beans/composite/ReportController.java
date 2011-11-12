/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Rotation;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.smvdu.payroll.beans.ReportBuilder;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;
import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
 */
public class ReportController {
    
    public JasperPrint getIndidivualSlip(String s,HashMap map)
    {
        LoggedEmployee emp = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        
        try
        {
            map = new HashMap();
            System.err.println("Employee Code "+s);
            map.put("emp_id", emp.getProfile().getCode());
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            System.err.println("path :"+path);
            //map.put("org_name", ui.getOrgName());
            map.put("month", 4 );
            map.put("year", 2011);
            JasperPrint print = JasperFillManager.fillReport(path+File.separator+"salaryslipone.jasper", map, new CommonDB().getConnection());
            System.err.println("Salary Slip generated");
            return print;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public JasperPrint  getMonthlyPyroll()
    {
        System.out.println("Enter String Here.............in method");
        UserInfo user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        //FileUploadBean file = (FileUploadBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fileUploadBean");
        new ReportBuilder().refreshReport();
         try
        {
                Style header = new Style();
                header.setRotation(Rotation.LEFT);
                header.setBorder(Border.THIN);
                header.setFont(Font.ARIAL_MEDIUM_BOLD);
                header.setVerticalAlign(VerticalAlign.MIDDLE);
                header.setBackgroundColor(Color.LIGHT_GRAY);
                header.setHorizontalAlign(HorizontalAlign.RIGHT);
              
                FastReportBuilder drb = new FastReportBuilder();
                HashMap<String,Object> map = new HashMap<String, Object>();
                map.put("month",user.getCurrentMonth());
                map.put("year",user.getCurrentYear());
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                map.put("instName",user.getOrgName());
                drb.setTitle(user.getOrgName());
                
                Style title = new Style();
                title.setFont(new Font(20, "arial", true));
                title.setTextColor(Color.ORANGE);
                title.setHorizontalAlign(HorizontalAlign.CENTER);
                drb.setTitleStyle(title);
                
                map.put("head", "");
                map.put("PayMonth", "");
                //map.put("org_logo", file.getLogoImage());
               
                Style subTitle = new Style();
                subTitle.setFont(Font.ARIAL_BIG);
                subTitle.setHorizontalAlign(HorizontalAlign.CENTER);
                drb.setSubtitle("Monthly payroll for the Month of "+user.getCurrentMonthName());
                drb.setSubtitleStyle(subTitle);
                
                drb.setTemplateFile(path+File.separator+"MonthlyAcq.jrxml", false, false, false, false);
                
                Style ps = new Style();
                ps.setBorder(Border.THIN);
                Style num = new Style();
                num.setBorder(Border.PEN_1_POINT);
                Style total = new Style();
                total.setBorder(Border.THIN);
                total.setFont(Font.ARIAL_BIG_BOLD);
                total.setHorizontalAlign(HorizontalAlign.LEFT);

              
                num.setHorizontalAlign(HorizontalAlign.RIGHT);
                DynamicReportBuilder builder = drb.addColumn("Emp Code", "emp_code", String.class.getName(),80,ps);
                builder = drb.addColumn("Title", "emp_title", String.class.getName(),50,ps);
                builder = drb.addColumn("Emp Name", "emp_name", String.class.getName(),150,ps);
                builder = drb.addColumn("Emp Desg", "desig_name", String.class.getName(),150,ps);
                builder = drb.addColumn("Basic", "emp_basic", Integer.class.getName(),60,ps);
                drb.setGrandTotalLegend("Net Total");
                int k=2;
                for(SalaryHead sh : new SalaryHeadDB().loadAllHeads())
                {
                    k++;
                    builder = drb.addColumn(sh.getName(), sh.getAlias(), Float.class.getName(),60,num,header);
                    drb.addGlobalFooterVariable(k, DJCalculation.SUM,num);
                }
                builder = drb.addColumn("Total Income", "es_total_income", Float.class.getName(),100,num);
                k++;
                drb.addGlobalFooterVariable(k, DJCalculation.SUM,num);
                k++;
                builder = drb.addColumn("Total Deduction", "es_total_deduct", Float.class.getName(),100,num);
                drb.addGlobalFooterVariable(k, DJCalculation.SUM,num);
                builder = drb.addColumn("Net Salary", "es_gross", Float.class.getName(),100,num);
                k++;
                drb.addGlobalFooterVariable(k, DJCalculation.SUM,num);
                drb.setQuery("select * from temp_sal_data left join employee_master on emp_code = temp_code "
                        + "left join employee_salary_summery on es_code = emp_code left join salary_grade_master on grd_code = emp_salary_grade left join designation_master on desig_code = emp_desig_code    where es_month= "+user.getCurrentMonth()
                        + " and es_year= "+user.getCurrentYear() + " ORDER BY gp desc, emp_code", "SQL");
                
               
                drb.setPrintBackgroundOnOddRows(false);
                drb.setGrandTotalLegendStyle(total);
                drb.setUseFullPageWidth(false);
                drb.setPageSizeAndOrientation(new Page(1000, 2000, false));                
                JasperPrint jp = DynamicJasperHelper.generateJasperPrint(builder.build(), new ClassicLayoutManager(),new CommonDB().getConnection(),map);
//                JRExporter exporter = new JRPdfExporter();
//                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
//                exporter.exportReport();
                return jp;
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
         
         
    }

}
