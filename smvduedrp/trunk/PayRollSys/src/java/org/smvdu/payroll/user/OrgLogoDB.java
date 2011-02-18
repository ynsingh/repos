/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.user;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class OrgLogoDB {

    private ResultSet rs;
    private PreparedStatement ps;
    public Image loadLogoImage()     {
        try
        {
            UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select org_logo from org_profile where org_id=?");
            ps.setInt(1, ui.getUserOrgCode());
            rs=ps.executeQuery();
            rs.next();
            Image img = ImageIO.read(rs.getBinaryStream(1));
            rs.close();
            ps.close();
            c.close();
            return img;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
