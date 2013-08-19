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
import org.smvdu.payroll.beans.SessionMaster;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.user.ActiveProfile;

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
public class SessionDB {


    private PreparedStatement ps;
    private ResultSet rs;
    private ActiveProfile info;
    private final UserInfo userBean;

    public SessionDB() {
        info = (ActiveProfile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");


    }

    
    private ArrayList<SessionMaster> currentSessionReport;
    public void setCurrentSession(int sessId)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update session_master set ss_current=0 where ss_org_id='"+userBean.getUserOrgCode()+"'");
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("update session_master set ss_current=1 where ss_id=? and ss_org_id='"+userBean.getUserOrgCode()+"' ");
            ps.setInt(1, sessId);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }





    public int save(SessionMaster session) {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into session_master(ss_name,"
                    + "ss_start_from,ss_end_to,ss_current,ss_org_id) values(?,?,?,?,?)");
            ps.setString(1, session.getName());
            ps.setString(2, session.getStartDate());
            ps.setString(3, session.getEndDate());
            ps.setBoolean(4, session.isCurrent());
            ps.setInt(5, userBean.getUserOrgCode());
            ps.executeUpdate();
       //     rs = ps.getGeneratedKeys();
            rs.next();
            int code =rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public SessionMaster getCurrentSession()
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from session_master where ss_current=1 and ss_org_id='"+userBean.getUserOrgCode()+"'");
            rs = ps.executeQuery();
            SessionMaster sess = new SessionMaster();
            if(rs.next())
            {
                sess.setCode(rs.getInt(1));
                sess.setName(rs.getString(2));
            }
            rs.close();
            ps.close();
            c.close();
            return sess;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<SessionMaster> getCurrentSessionForReport()
    {
        try
        {
            currentSessionReport = new ArrayList<SessionMaster>();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from session_master where ss_current=1 and ss_org_id='"+userBean.getUserOrgCode()+"'");
            rs = ps.executeQuery();
            while(rs.next())
            {
                SessionMaster sess = new SessionMaster();
                sess.setCode(rs.getInt(1));
                sess.setName(rs.getString(2));
                currentSessionReport.add(sess); 
            }
            rs.close();
            ps.close();
            c.close();
            return currentSessionReport;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }



    public boolean  update(ArrayList<SessionMaster> sesss)
    {
        try
        {
            boolean flag = false;
            int currentStatus = 0;
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update session_master set ss_name=?,ss_current=? where ss_id=? and ss_org_id='"+userBean.getUserOrgCode()+"'");
            for(SessionMaster sm : sesss)
            {
                if(sm.isCurrent() == true)
                {
                    currentStatus++;
                }
                if(currentStatus>1)
                {
                    flag = false;
                    break;
                }
                else
                {
                    flag = true;
                }
                ps.setString(1, sm.getName());
                ps.setBoolean(2, sm.isCurrent());
                ps.setInt(3, sm.getCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
            return flag;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }



    public ArrayList<SessionMaster> load()  {
        ArrayList<SessionMaster> data = new ArrayList<SessionMaster>();
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from session_master where ss_org_id='"+userBean.getUserOrgCode()+"'");
            rs = ps.executeQuery();

            while(rs.next())
            {
                SessionMaster sm = new SessionMaster();
                sm.setCode(rs.getInt(1));
                sm.setName(rs.getString(2));
                sm.setStartDate(rs.getString(3));
                sm.setEndDate(rs.getString(4));
                sm.setCurrent(rs.getBoolean(5));
                data.add(sm);
            }
            rs.close();
            ps.close();
            c.close();
            //return data;

        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return data;
    }

}
