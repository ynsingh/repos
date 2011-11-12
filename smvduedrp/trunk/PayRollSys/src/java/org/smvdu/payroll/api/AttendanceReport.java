/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
public class AttendanceReport {

    private ArrayList<Attendance> attendances;

    public ArrayList<Attendance> getAttendances() {
        loadTeamReport(memberId);
        return attendances;
    }

    public void setAttendances(ArrayList<Attendance> attendances) {
        this.attendances = attendances;
    }
    private Attendance[][] data = new Attendance[35][17];

    public Attendance[][] getData() {
        return data;
    }

    public void setData(Attendance[][] data) {
        this.data = data;
    }
    private int totalAbscent = 0;

    public int getTotalAbscent() {
        return totalAbscent;
    }

    public void setTotalAbscent(int totalAbscent) {
        this.totalAbscent = totalAbscent;
    }
    private PreparedStatement ps;
    private ResultSet rs;
    private String empCode;
    private LoggedEmployee bean;
    private String memberId;

    public void loadAttReport() {
        System.out.println("Loading attendance report for " + memberId);
        loadTeamReport("emp001");
    }

    public String getMemberId() {
        System.out.println(" >>>>>>>Setting ID " + memberId);
        return memberId;
    }

    public void setMemberId(String memberId) {
        System.out.println(" >>>>>>>Setting ID " + memberId);
        this.memberId = memberId;
        //loadTeamReport(memberId);
    }

    public String getEmpCode() {
        bean = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if (bean != null) {
            empCode = bean.getProfile().getCode();
            loadReport(empCode);
        }
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public void loadTeamReport(String cEmpCode) {
        //System.out.println("Loading attendance report for "+cEmpCode);
//        new AttendanceInitAPI().init();
        attendances = new ArrayList<Attendance>();
        try {
            Connection c = new CommonDB().getConnection();
            String q = "select date_format(t_date,'%d-%M-%Y'),"
                    + "att_time_in,att_time_out from date_master left join "
                    + "attendance_master on att_date = t_date and att_emp_id =? "
                    + "where t_month=? and t_year=? ";
            ps = c.prepareStatement(q);
            ps.setString(1, "emp001");
            System.err.println("SQL : " + q);
            ps.setInt(2, new CommonDB().getMonth());
            ps.setInt(3, new CommonDB().getYear());
            rs = ps.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                att.setDate(rs.getString(1));
                att.setOginTime(rs.getString(2));
                att.setOgoutTime(rs.getString(3));
                attendances.add(att);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadReport(String cEmpCode) {
        System.out.println("Loading attendance report for " + memberId);
        new AttendanceInitAPI().init();
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select date_format(t_date,'%d-%M-%Y'),"
                    + "dayofweek(t_date),day(t_date),date_format(att_time_in,'%h-%m%p'),"
                    + "date_format(att_time_out,'%h-%m%p'),t_date from date_master left join "
                    + "attendance_master on att_date = t_date and att_emp_id =? where t_month=? and t_year=?");
            ps.setString(1, cEmpCode);
            ps.setInt(2, new CommonDB().getMonth());
            ps.setInt(3, new CommonDB().getYear());
            rs = ps.executeQuery();
            int arow = 0;
            while (rs.next()) {
                int day = rs.getInt(2);
                int col = day - 1;
                Attendance att = new Attendance();
                if (rs.getInt(3) > bean.getCurrentDay()) {
                    att.setForward(true);
                    att.setAbscent(false);
                }
                if (day == 1) {
                    att.setSunday(true);
                }
                att.setDate(rs.getString(1));
                att.setOginTime(rs.getString(4));
                att.setOgoutTime(rs.getString(5));
                if (att.getOginTime() == null || att.getOginTime().isEmpty()) {
                    att.setAbscent(true);
                    if (!att.isForward() && !att.isSunday()) {
                        totalAbscent++;
                    }
                }
                data[arow][col] = att;
                if (day == 7) {
                    arow++;
                }
            }
            rs.close();
            ps.close();
            c.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
