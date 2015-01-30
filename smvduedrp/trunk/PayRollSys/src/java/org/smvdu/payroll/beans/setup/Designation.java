/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.setup;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.smvdu.payroll.beans.db.DesignationDB;
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
 */
//public class Designation extends BaseBean implements Converter, Serializable {
public class Designation implements Serializable {

    public void save() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (this.getDcode().matches("[a-zA-Z0-9]*") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Designation Code.No speacial characters allowed.");
            fc.addMessage("", message);
            return;
        }
        if (this.getName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Designation Name.No speacial characters allowed.");
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
        Exception e = new DesignationDB().save(this);
        //Exception e = new DesignationDB().save(getName());
        if (e == null) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Designation saved "+" " + getName(), ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Designation already Exist : " + getName(), ""));
        }
    }
    private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {

        ArrayList<Designation> designations = new DesignationDB().loadDesignations();
        arrayAsItem = new SelectItem[designations.size()];
        Designation dp = null;
        for (int i = 0; i < designations.size(); i++) {
            dp = designations.get(i);
            SelectItem si = new SelectItem(dp.getCode(), dp.getName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

   /* @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return new DesignationDB().convert(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("Object class Desig : " + o.getClass().getSimpleName());
        return o.toString();
    }*/
    
    
    private String nickName;
    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickname) {
        this.nickName = nickname;
    }
    
    
    private String dcode;
    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
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
    
    private int orgcode;
    public int getOrgcode() {
        return orgcode;
    }
    
    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
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
        java.io.File ff=new java.io.File(path);
        if(!ff.exists())
        ff.mkdirs();
        ff=new java.io.File(path+"/"+file.getName());
        FileOutputStream stream=new FileOutputStream(ff,true);
        stream.write(file.getData());
        stream.close();
        saveFile();
        ff.delete();
        
    }
   
   private void saveFile()   {
        
        try
        {
            
            
           Exception e = new DesignationDB().saveFile(files);
           if(e==null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Designation saved", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Designation already Exist : "+getName(), ""));
            }
                   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
