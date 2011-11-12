/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.module.attendance;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.user.ActiveProfile;
import org.smvdu.payroll.user.UserHistory;

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
public class LoggedEmployee implements Serializable {

    private String loginId;
    private String orgName;
    private int currentMonth;
    private int currentYear;
    private int currentDay;

    public int getCurrentDay() {
        return currentDay;
    }


    private String passOne;
    private String passTwo;

    public String getPassOne() {
        return passOne;
    }

    public void setPassOne(String passOne) {
        this.passOne = passOne;
    }

    public String getPassTwo() {
        return passTwo;
    }

    public void setPassTwo(String passTwo) {
        this.passTwo = passTwo;
    }
    

    

    

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }
    private String[] months = {"", "JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOW", "DEC"};
    private boolean admin = true;
    private String currentDate;
    private String currentMonthName;
    private int userOrgCode;
    private String password;
    private String date;
    private Employee profile;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public String getCurrentMonthName() {
        return currentMonthName;
    }

    public void setCurrentMonthName(String currentMonthName) {
        this.currentMonthName = currentMonthName;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void set() {
        String[] dd = currentDate.split("-");
        currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
        currentMonth = Integer.parseInt(dd[1]);
        currentYear = Integer.parseInt(dd[0]);
        currentDay = Integer.parseInt(dd[2]);
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getUserOrgCode() {
        return userOrgCode;
    }

    public void setUserOrgCode(int userOrgCode) {
        this.userOrgCode = userOrgCode;
        orgName = new OrgProfileDB().getProfileName(userOrgCode);
    }

    public String invalidate() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        new EmployeeLoginController().invalidate(profile.getCode(), new CommonDB().getDate());
        return "EmployeeLogin.jsf";
    }

    public String validate() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        String s = new EmployeeLoginController().validate(loginId, password,userOrgCode);
        if (s != null) {

            currentDate = new CommonDB().getDate();
            String[] dd = currentDate.split("-");
            currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
            currentMonth = Integer.parseInt(dd[1]);
            currentYear = Integer.parseInt(dd[0]);
            currentDay = Integer.parseInt(dd[2]);
            profile = new EmployeeDB().loadProfile(s,userOrgCode);
            ActiveProfile ap = new ActiveProfile();
            ap.setOrgId(userOrgCode);
            ap.setProfile(profile);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ActiveProfile", ap);
            UserHistory uh = new UserHistory();
            uh.setUserId(profile.getEmpId());
            uh.setAction(true);
            uh.save();
            System.out.println(profile.getName() + ", " + profile.getDesigName() + "," + profile.getDeptName() + currentMonthName);
            return "case1";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Login Id or Password", ""));
            return null;
        }

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public void changePassword()
    {
       
        if(!passOne.equals(passTwo))
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Password doesnot Matches",""));
             return;
        }
        boolean b = new EmployeeLoginController().changePassword(loginId, passOne);

        if(b)
        {
            password = passOne;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Password Changed",""));
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        UserHistory uh = new UserHistory();
        LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
//        uh.setUserId(le.getProfile().getEmpId());
//        uh.setAction(false);
//        uh.save();
        return "EmployeeLogin.jsf";
    }

    public Employee getProfile() {
       ActiveProfile ac = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");
       profile = ac.getProfile();
       System.err.println(profile.getEmail());
        return profile;
    }

    public void setProfile(Employee profile) {
        this.profile = profile;
    }
}
