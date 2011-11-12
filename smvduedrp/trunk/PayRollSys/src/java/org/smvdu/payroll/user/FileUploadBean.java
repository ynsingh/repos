/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.user;

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

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

/**
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
 *
 */
public class FileUploadBean{


    

    public FileUploadBean()
    {
        load();
        try
        {
            logoImage =ImageIO.read(new ByteArrayInputStream(file.getData()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public Image getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(Image logoImage) {
        this.logoImage = logoImage;
    }


    
    private Image logoImage;
    private PreparedStatement ps;
    private ResultSet rs;
    private MyFile file = null;
    public int getSize() {
      if(file==null)
      {
          return 0;
      }
      return 1;
    }

   

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(file.getData());
    }
    public void listener(UploadEvent event) throws Exception{
        UploadItem item = event.getUploadItem();
        System.out.println(item.getFileName()+",");
        MyFile mf =new MyFile();
        mf.setName(item.getFileName());
        mf.setData(item.getData());
        mf.setLength(item.getData().length);
        this.file = mf;
        save();
    }

    public String clearUploadData() {
        
        return null;
    }

   
    public MyFile getFile() {
        return file;
    }

    public void setFile(MyFile file) {
        this.file = file;
    }

    private void load()     {
        int orgCode=0;
        UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        if(ui==null)
        {
            LoggedEmployee le =(LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            orgCode = le.getUserOrgCode();
        }
 else
        {
            orgCode = ui.getUserOrgCode();
 }
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select org_logo from org_profile where org_id=?");
            ps.setInt(1, orgCode);
            rs=ps.executeQuery();
            rs.next();
            file = new MyFile();
            file.setData(rs.getBytes(1));
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void save()   {
        UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update org_profile set org_logo=? where org_id=?");            
            ps.setBytes(1, file.getData());
            ps.setInt(2, ui.getUserOrgCode());
            ps.executeUpdate();
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    

}
