/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
//import org.smvdu.payroll.beans.db.DepartmentDB;
import org.smvdu.payroll.beans.db.SalaryGradeDB;
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
*  Modified Date: 03 Nov 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
public class SalaryGrade {

    private int code;
    
    public int getCode() {
        return code;
    }

    public void setCode(int i) {
        code = i;
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String string) {
        if(string!=null)
        name = string.toUpperCase();
    }
    
    public int minValue;

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    
    private int maxValue;
    
    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
    
    private int gradePay;
    
    public int getGradePay() {
        return gradePay;
    }

    public void setGradePay(int gradePay) {
        this.gradePay = gradePay;
    }
    
    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private ArrayList<SalaryGrade> allGrades;

    public ArrayList<SalaryGrade> getAllGrades() {
        return new SalaryGradeDB().load();
    }

    public void setAllGrades(ArrayList<SalaryGrade> allGrades) {
        this.allGrades = allGrades;
    }
    
    @Override
    public String toString()
    {
        return name + "("+maxValue+"-"+minValue+") - "+gradePay;
    }
    
    private  SelectItem[] grades;

    public SelectItem[] getGrades() {
        ArrayList<SalaryGrade> grds= new SalaryGradeDB().load();
        grades = new SelectItem[grds.size()];
        SalaryGrade sg = null;
        for(int i=0;i<grds.size();i++)
        {
            sg = grds.get(i);
            SelectItem si = new SelectItem(sg.code, sg.toString());
            grades[i] = si;
            //System.out.println("\nsiin grades===="+si+"\ngrades====="+grades+"\nsg====="+sg+"\ngrades[i]===="+grades[i]);
        }
        return grades;
    }
    
    public void setGrades(int[] ints)
    {
        
    }

    public void save()
    {
         FacesContext fc = FacesContext.getCurrentInstance();
         if (this.getMaxValue()< this.getMinValue())
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Maximum and Minimum Values.");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        if(this.getName().matches("^[a-zA-Z0-9\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Pay Band Name. No Special Characters are Allowed");
                fc.addMessage("", message);
                return;
       }
           // System.out.println("Code : "+sg.getCode()+"Name : "+sg.getName()+", Max : "+s 
      Exception e = new SalaryGradeDB().save(this);
      if(e==null)
      {
       FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pay scale saved "+" "+this.getName(), ""));
      }
      else{
      FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pay scale already exist "+" "+this.getName(), ""));    
      }
       name=null;
       maxValue=0;
       minValue=0;
   }    
    
   private UploadFile files=null ;
   //private int uploadsAvailable = 5;
   public void listener(UploadEvent event) throws Exception{
        UploadItem item = event.getUploadItem();
        UploadFile file= new UploadFile();
        file.setLength(item.getData().length);
        file.setName(item.getFileName());
        file.setData(item.getData());
        //System.err.println("File Size : "+item.getData().length);
        this.files=file;
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/tmp");
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
                      
           Exception e = new SalaryGradeDB().saveFile(files);
           if(e==null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pay Band saved", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pay Band already Exist : "+getName(), ""));
            }
                   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
   private Set defaultSalary = new HashSet();

    public Set getDefaultSalary() {
        return defaultSalary;
    }

    public void setDefaultSalary(Set defaultSalary) {
        this.defaultSalary = defaultSalary;
    }
   

}
