/* Attendance 
 * Created on 20 October 2014
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.AttendanceDB;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.upload.UploadFile;

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
*  IITK , Om Prakash(omprakashkgp@gmail.com), Manorama Pal (palseema30@gmail.com)
* */

public class Attendance implements Serializable{
    
    public Attendance(){}

    private int id;
    private String code;
    private String name;
    private int present;
    private int leave;
    private int month;
    private int year;
    private int monthMaxDays;
    private int orgcode;
    
      private Set matchin = new HashSet(0);

    public Set getMatchin() {
        return matchin;
    }

    public void setMatchin(Set matchin) {
        this.matchin = matchin;
    }
      
    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    public   boolean rememberme;
    
        
     public void save() {
              FacesContext fc = FacesContext.getCurrentInstance();
        
       /* String amc=Integer.toString(this.getPresent());
        if (amc.matches (".*[0-9].*") == false ) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Present Number, characters not allowed.");
            fc.addMessage("", message);
            return;
       } */
        int sum =this.getPresent()+this.getLeave();
        int mth= this.getMonth();
        int yar= this.getYear();
        int monthMaxDays = new AttendanceDB().NumberofDays(mth,yar);
        //System.out.println(sum+":"+mth+":"+yar+":condition====="+(sum <= monthMaxDays)+": max days"+monthMaxDays);
        if (sum > monthMaxDays) {
            
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Leave Number,and present number");
            fc.addMessage("", message);
            return;
        }
        Exception e = new AttendanceDB().save(this);
        if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Attendance saved of employee code"+" "+getCode(), ""));
        }
       else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attendance already Exist of Employee code : "+getCode(), ""));
        }
        
    }
     
     
     
     private int srNo;
     
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }
    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }
    
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
    
    public boolean isRemember() {
        return rememberme;
    }
    public void setRemember(boolean rememberme) {
      this.rememberme = rememberme;
    }
    
  

  //upload file --------------------//

   private UploadFile files=null ;
   public void listener(UploadEvent event) throws Exception{
        UploadItem item = event.getUploadItem();
        UploadFile file= new UploadFile();
        file.setLength(item.getData().length);
        file.setName(item.getFileName());
        file.setData(item.getData());
        this.files=file;
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/attend");
        File ff=new File(path);
        if(!ff.exists())
        ff.mkdirs();
        ff=new File(path+"/"+file.getName());
        FileOutputStream stream=new FileOutputStream(ff,true);
        stream.write(file.getData());
        stream.close();
        saveFile();
        ff.delete();
        
    }


    private void saveFile()   {
       
        try
        {
        
         Exception e = new AttendanceDB().saveFile(files);
           if(e==null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Attendance saved ", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attendance already Exist : "+getCode(), ""));
            }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
   
   public void callPage(){
           try
           {
                FacesContext.getCurrentInstance().getExternalContext().redirect("EmployeeAttendanceCheck.jsf");
                           
           }
           catch(Exception e)
           {
            e.printStackTrace();
            }       
   }
   public void backPage(){
           try
           {
                FacesContext.getCurrentInstance().getExternalContext().redirect("EmployeeAttendance.jsf");
                               
           }
           catch(Exception e)
           {
            e.printStackTrace();
            }       
   }

}

