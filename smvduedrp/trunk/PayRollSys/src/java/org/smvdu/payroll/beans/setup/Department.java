/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.DepartmentDB;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
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
*  Modified Date: 07 OCT 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
//public class Department extends BaseBean implements Converter,Serializable{
public class Department implements Serializable{
    public void save() {
        FacesContext fc = FacesContext.getCurrentInstance();
        /*if (this.getDCode()matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Department Name.No speacial characters allowed.");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }*/
        if (this.getName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Department Name.No speacial characters allowed.");
            fc.addMessage("", message);
            return;
        }
        if (this.getNickName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Nick Name No speacial characters allowed");
            fc.addMessage("", message);
            return;
        }
        //Exception e = new DepartmentDB().save(getName());
        Exception e = new DepartmentDB().save(this);
         if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Department saved "+" "+getName(), ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department already Exist : "+getName(), ""));
        }
        
    }

    private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {

        ArrayList<Department> departments = new DepartmentDB().loadDepartments();
        arrayAsItem = new SelectItem[departments.size()];
        //Department dp = null;
        for(int i=0;i<departments.size();i++)
        {
           Department dp = departments.get(i);
            SelectItem si = new SelectItem(dp.getCode(), dp.getName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    public void setArrayAsItem(SelectItem[] arrayAsItem) {
        this.arrayAsItem = arrayAsItem;
    }
        
    private int empCount;

    public int getEmpCount() {
        return empCount;
    }

    public void setEmpCount(int empCount) {
        this.empCount = empCount;
    }
    
    

    @Override
    public String toString()
    {
        return getName();
    }

    
    /*@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        //System.err.println("Got String "+string);
        Department dept =  new DepartmentDB().convert(string);
        //System.err.println("Got Object Name "+dept.getName()+",Code "+dept.getCode());
        return dept;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        //System.out.println("Object class Dept: "+o.getClass().getSimpleName());
        BaseBean bb = (BaseBean)o;
        return String.valueOf(bb.getName());
    }*/
    
    private String nickname;
    
    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }
    
    
    private String dcode;
    public String getDCode() {
        return dcode;
    }

    public void setDCode(String dcode) {
        this.dcode = dcode;
    }
    private int code;
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    //upload file --------------------//
    
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
        //System.out.println("path===="+path);
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
            
            
           Exception e = new DepartmentDB().saveFile(files);
           if(e==null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Department saved", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department already Exist : "+getName(), ""));
            }
                   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
}
