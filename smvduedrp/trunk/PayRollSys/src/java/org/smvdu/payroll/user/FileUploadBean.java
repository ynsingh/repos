/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.user;

/**
 *
 * @author Algox
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

/**
 * @author Ilya Shaikovsky
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
        System.out.println(item.getFileName()+","+item.getFileSize());
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
        UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select org_logo from org_profile where org_id=?");
            ps.setInt(1, ui.getUserOrgCode());
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
