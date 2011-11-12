/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.sms;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;

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
public class SMSProfile {

    private PreparedStatement ps;
    private ResultSet rs;
    private static SMSProfile currentProfile;

    public static SMSProfile getCurrentProfile() {
        return currentProfile;
    }

    public static void setCurrentProfile(SMSProfile currentProfile) {
        SMSProfile.currentProfile = currentProfile;
    }
    


    @Override
    public String toString()
    {
        return name;
    }

    public int save(SMSProfile p)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into sms_profile(smp_name,smp_user,smp_password,smp_sender) values(?,?,?,?)",1);
            ps.setString(1, p.name);
            ps.setString(2, p.userName);
            ps.setString(3, p.password);
            ps.setString(4, p.senderName);
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;

        }
        catch(Exception e)
        {
            //GlobalErrorManager.add(e);
            return -1;
        }
    }
    public ArrayList<SMSProfile> loadProfiles()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from sms_profile");
            rs = ps.executeQuery();
            ArrayList<SMSProfile> profiles = new ArrayList<SMSProfile>();
            while(rs.next())
            {
                SMSProfile sp = new  SMSProfile();
                sp.id = rs.getInt(1);
                sp.name = rs.getString(2);
                sp.userName = rs.getString(3);
                sp.password = rs.getString(4);
                sp.senderName = rs.getString(5);
                boolean b = rs.getBoolean(6);
                if(b)
                {
                    currentProfile = sp;
                }
                profiles.add(sp);
            }
            rs.close();
            ps.close();
            c.close();
            return profiles;
        }
        catch(Exception e)
        {
            //GlobalErrorManager.add(e);
            return null;
        }
    }
    public  String userName = "sushant001";
    public  String password = "146948891";
    public  String senderName;
    public int id;
    public String name;

}
