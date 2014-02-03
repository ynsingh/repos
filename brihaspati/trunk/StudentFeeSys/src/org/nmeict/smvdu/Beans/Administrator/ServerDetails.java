/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.db.CommonDB;

/**
 *
 * @author guest
 */
public class ServerDetails {
    /**
   * 
   * Save Server Details Ip Address, Port No, Client
   * @param org
   * @param code
   * @return 
   */

    public boolean saveServerDetail(OrgProfile org, String code)
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
             FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            org.setIpAddress(request.getRemoteAddr());
            org.setPort(String.valueOf(request.getServerPort()));
            cn.setAutoCommit(false);
            PreparedStatement pst = cn.prepareStatement("insert into client_details(ip_address,port,client_org_code,flag) values('"+org.getIpAddress()+"','"+org.getPort()+"','"+code+"','"+1+"')");
            pst.executeUpdate();
            cn.commit();
            cn.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    
}
