/* AttendanceController
 * Created on 21 October 2014
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletException;
import org.smvdu.payroll.api.email.MassageProperties;
import org.smvdu.payroll.api.pf.ReportGen.AnnualAttendanceReport;
import org.smvdu.payroll.api.pf.ReportGen.AttendancePDF;
import org.smvdu.payroll.beans.setup.Attendance;
import org.smvdu.payroll.beans.db.AttendanceDB;
import org.smvdu.payroll.user.UserRegistration;

/**
 *
 *    Copyright (c) 2010 - 2011 SMVDU, Katra.
 *    Copyright (c) 2014 - 2016 ETRG, IITK.
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
* Author: Om Prakash<omprakashkgp@gmail.com>, Manorama Pal<palseema30@gmail.com> IITK
* modified on 29 January 2015 by Om Prakash <omprakashkgp@gmail.com> 
 */
public class AttendanceController {
    private String data;
    public int listofyears;
    private ArrayList<Attendance> checkattendances;
    private UIData dataGrid11;
    private ArrayList<Attendance> attendances;
    private UIData dataGrid;
    public String loadAtt;
    private ArrayList<Attendance> attendancepdf;
    private ArrayList<Attendance> attendancehtml;
    private ArrayList<Attendance> individualattendancepdf;
    private String code;
    private ArrayList<Attendance> individualattendancehtml;
    private ArrayList<Attendance> annualattendancepdf;
    private ArrayList<Attendance> annualattendancehtml;

   
   /* load selected attendance  */
   public void populate()
    {
        getAllAttendanceData();
    }
   
   public void checkAtts()
   {
       getAllCheckAttendanceData();
   } 
   
    public UIData getDataGrid() {
       return dataGrid;
    }
    
    public void setDataGrid(UIData dataGrid) {
         this.dataGrid = dataGrid;
    }
    
    public UIData getDataGrid11() {
       return dataGrid11;
    }
    
    public void setDataGrid11(UIData dataGrid11) {
         this.dataGrid11 = dataGrid11;
    }
       
    public int month;
    public int year;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    private SelectItem[] items;

    public SelectItem[] getItemsYear() {
        ArrayList<Attendance> attendance=new AttendanceDB().ListofYears();
        
        items = new SelectItem[attendance.size()];
        //System.out.print();
        for(int i=0;i<attendance.size();i++)
        {
            Attendance at =attendance.get(i);
            SelectItem si =new SelectItem(at.getYear());
            items[i] = si;
        }
        return items;
    }
    public void setItemsYear(SelectItem[] items) {
        this.items = items;
    }
    
    public ArrayList<Attendance> getAllAttendanceData() {
        attendances = new AttendanceDB().loadAttendances(month,year);
        dataGrid.setValue(attendances);
        return attendances;
    }
   
    public void setAllAttendanceData(ArrayList<Attendance> attendances) {
        this.attendances =attendances;
    }
    
    
    public ArrayList<Attendance> getAllCheckAttendanceData() {
        checkattendances = new AttendanceDB().checkAttendances(month, year);
        dataGrid.setValue(checkattendances);
        return checkattendances;
    }
   
    public void setAllCheckAttendanceData(ArrayList<Attendance> checkattendances) {
        this.checkattendances =checkattendances;
    }
    
     
    /* update attendance  of employee */
    public void update()
    {
        ArrayList<Attendance> data = (ArrayList<Attendance>) dataGrid.getValue();
        FacesContext fc = FacesContext.getCurrentInstance();
        for(Attendance d: data)
        {
        int sum =d.getPresent()+d.getAbsent()+d.getLeave();
        int mth= d.getMonth();
        int yar= d.getYear();
        int monthMaxDays = new AttendanceDB().NumberofDays(mth,yar);
       
        if (sum > monthMaxDays) {
            
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Leave Number,absent number and present number");
            fc.addMessage("", message);
            return;
           }
                 
            System.out.println("Session Present "+d.getPresent()+"<===>"+d.getAbsent()+"<====>"+d.getLeave());
        }
        new AttendanceDB().update(data);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Attendance Updated", ""));
        
    }
    
    public void saveCheckAt()
    {
        ArrayList<Attendance> checkboxdata = new ArrayList<Attendance>();
        ArrayList<Attendance> chdata = (ArrayList<Attendance>) dataGrid.getValue();    
        FacesContext fc = FacesContext.getCurrentInstance();
           
        for(Attendance chbd: chdata)
        {
                                 
            if(chbd.isRemember())
            {
                 
              //  checkboxdata.add(chbd);
                System.out.println("datacopy:==== "+chdata.size());
            //} 
            int sum =chbd.getPresent()+chbd.getAbsent()+chbd.getLeave();
            int mth= chbd.getMonth();
            int yar= chbd.getYear();
            int monthMaxDays = new AttendanceDB().NumberofDays(mth,yar);
        
            if (sum > monthMaxDays) 
            {           
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Leave Number,absent number and present number");
            fc.addMessage("", message);
            return;
            }
            if (chbd.getMonth() > 11) 
            {           
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Month Number");
                fc.addMessage("", message);
                return;
            }
            if (chbd.getYear() < 2004) 
            {           
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Year Number");
            fc.addMessage("", message);
            return;
            }
            checkboxdata.add(chbd);
            }
        }
        Exception e = new AttendanceDB().saveCheckAt(checkboxdata);
           
            if(e==null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Attendance saved :", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attendance already Exist : ", ""));
            }
      }
      
    
     /* load selected attendance report in PDF Formate */
   public void attendanceReportPDF()
    {
        //getAllAttendanceData();
        attendancepdf = new AttendancePDF().Attendance(month,year);
        
    }
    
     public ArrayList<Attendance> getAllAttendancePDF() {
        attendancepdf = new AttendancePDF().Attendance(month,year);
        return attendancepdf;
    }
   
    public void setAllAttendancePDF(ArrayList<Attendance> attendancepdf) {
        this.attendancepdf =attendancepdf;
    }
  
        /* load selected attendance report in HTML Formate */
   public void attendanceReportHTML() throws ServletException
    {
        //getAllAttendanceData();
        attendancehtml = new AttendancePDF().AttendanceHTML(month,year);
        
    }
    
     public ArrayList<Attendance> getAllAttendanceHTML() throws ServletException {
        attendancehtml = new AttendancePDF().AttendanceHTML(month,year);
        return attendancehtml;
    }
   
    public void setAllAttendanceHTML(ArrayList<Attendance> attendancehtml) {
        this.attendancehtml =attendancehtml;
    }
    
         /* load selected Individual attendance report in PDF Formate */
   public void individualAttendanceReportPDF()
    {
       // getIndividualAttendancePDF();
        individualattendancepdf = new AnnualAttendanceReport().individualAttendancePDF(code, month, year);
        
    }
    
     public ArrayList<Attendance> getIndividualAttendancePDF() {
        individualattendancepdf = new AnnualAttendanceReport().individualAttendancePDF(code, month, year);
        return individualattendancepdf;
    }
   
    public void setIndividualAttendancePDF(ArrayList<Attendance> individualattendancepdf) {
        this.individualattendancepdf =individualattendancepdf;
    }

       
         /* load selected Individual attendance report in HTML Formate */
   public void individualAttendanceReportHTML()
    {
       // getIndividualAttendancePDF();
        individualattendancehtml = new AnnualAttendanceReport().individualAttendanceHTML(code, month, year);
        
    }
    
     public ArrayList<Attendance> getIndividualAttendanceHTML() {
        individualattendancehtml = new AnnualAttendanceReport().individualAttendanceHTML(code, month, year);
        return individualattendancehtml;
    }
   
    public void setIndividualAttendanceHTML(ArrayList<Attendance> individualattendancehtml) {
        this.individualattendancehtml =individualattendancehtml;
    }

    //       load selected Annual attendance report in PDF Formate 
   public void annualAttendanceReportPDF()
    {
       // getIndividualAttendancePDF();
        annualattendancepdf = new AnnualAttendanceReport().annualAttendancePDF(code, year);
        
    }
    
     public ArrayList<Attendance> getAnnualAttendancePDF() {
        annualattendancepdf = new AnnualAttendanceReport().annualAttendancePDF(code, year);
        return annualattendancepdf;
    }
   
    public void setAnnualAttendancePDF(ArrayList<Attendance> annualattendancepdf) {
        this.annualattendancepdf =annualattendancepdf;
    }

    
    // Load selected annual attendance report in HTML Formate 
    public void annualAttendanceReportHTML(){
        annualattendancehtml= new AnnualAttendanceReport().annualAttendanceHTML(code, year);
    }
    
    public ArrayList<Attendance> getAnnualAttendanceHTML(){
        annualattendancehtml = new AnnualAttendanceReport().annualAttendanceHTML(code, year);
        return annualattendancehtml;
    }
    public void setAnnualAttendanceHTML(ArrayList<Attendance> annualattendancehtml){
       this.annualattendancehtml = annualattendancehtml; 
    }
    
}
