/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserGroup;
import org.smvdu.payroll.beans.UserInfo;

/**
 *
 * @author Algox
 */
public class UserDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo userBean;

    public UserDB() {
        userBean = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    public ArrayList<UserInfo> loadAll()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select user_id,user_name,grp_id,grp_name "
                    + "from user_master left join user_group_master on "
                    + " user_grp_id = grp_id where user_org_id=?");
            ps.setInt(1, userBean.getUserOrgCode());
            rs=ps.executeQuery();
            ArrayList<UserInfo> data = new ArrayList<UserInfo>();
            while(rs.next())
            {
                UserInfo ui = new UserInfo();
                ui.setUserId(rs.getInt(1));
                ui.setUserName(rs.getString(2));
                UserGroup ug = new UserGroup();
                ug.setId(rs.getInt(3));
                ug.setName(rs.getString(4));
                ui.setUserGroup(ug);
                data.add(ui);
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void save(UserInfo ui)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into user_master(user_name,user_pass,"
                    + "user_org_id,user_group_id) values(?,?,?,?)");
            ps.setString(1, ui.getUserName());
            ps.setString(2, ui.getPassword());
            ps.setInt(3, ui.getUserOrgCode());
            ps.setInt(4, ui.getGroupCode());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean editPass(String pass,int x)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update user_master set user_pass=? where user_id=?");
            ps.setString(1, pass);
            ps.setInt(2, x);
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public int validate(String user,String pass)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select user_id from user_master where user_name=?"
                    + " and user_pass=? and user_org_id=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, userBean.getUserOrgCode());
            rs=ps.executeQuery();
            rs.next();
            int k = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return k;
        }
        catch(Exception e)
        {
            return -1;
        }
    }

}
