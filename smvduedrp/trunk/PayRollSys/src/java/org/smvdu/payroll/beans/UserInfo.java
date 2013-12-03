package org.smvdu.payroll.beans;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.smvdu.payroll.api.UserTask.ReadUserTaskList;
import org.smvdu.payroll.api.UserTask.UserTaskDB;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;
import org.smvdu.payroll.beans.db.UserDB;
import org.smvdu.payroll.beans.db.UserGroupDB;
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
* Modified Date: 02 Dec 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
 */
public class UserInfo implements Serializable {

    private String bankName = new String();

    public String getBankName() {
        
        return bankName;
    }

    public void setBankName(String bankName) {
        
        this.bankName = bankName;
    }
    private int userId = 0;
    private int currentMonth;
    private int currentYear;
    private String[] months = {"", "JAN", "FEB", "MARCH", "APRIL", "MAY",
        "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC"};
    private boolean admin = true;
    private String userName = "System";
    private String password;
    private String pass3;
    private String currentDate;
    private String currentMonthName;
    private int userOrgCode;
    private int groupId;
    private boolean isAuthority;
    private String memberId;
    private Employee profile;

    public Employee getProfile() {
        return profile;
    }

    public void setProfile(Employee profile) {
        this.profile = profile;
    }
    private boolean profileActive = false;

    public boolean isProfileActive() {
        return profileActive;
    }

    public void setProfileActive(boolean profileActive) {
        this.profileActive = profileActive;
    }
    private String memberName;

    public String getMemberName() {
        memberName = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("teamName");
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        memberId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("teamId");
        System.out.println("Member ID : " + memberId);
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String taskPage() {
        return "TaskResponse.jsf";
    }

    public String teamLogin() {
        return "TeamTask.jsf";
    }

    public String adminLogin() {
        return "../Login.jsf";
    }

    public String portalLogin() {
        return "portal/EmployeeLogin.jsf";
    }

    public String attControl() {
        return "MemberAttendance.jsf";
    }

    public String register() {
        return "admin/Login.jsf";
    }

    public boolean isIsAuthority() {
        if (groupCode == 2) {
            return true;
        } else {
            return false;
        }

    }

    public void setIsAuthority(boolean isAuthority) {
        this.isAuthority = isAuthority;
    }
    private int groupCode;
    private String groupName;

    public int getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    private UserGroup userGroup;

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getUserOrgCode() {
        return userOrgCode;
    }

    public void setUserOrgCode(int userOrgCode) {
        this.userOrgCode = userOrgCode;
        orgName = new OrgProfileDB().getProfileName(userOrgCode);
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
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

    public void setCurrentMonthName(String currentMonth) {
        this.currentMonthName = currentMonth;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getPass3() {
        return pass3;
    }

    public void setPass3(String pass3) {
        this.pass3 = pass3;
    }
    private String pass1;

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }
    private String orgName;
    private ArrayList<UserInfo> allUser;
    private SelectItem[] groups;

    public SelectItem[] getGroups() {
        ArrayList<UserGroup> gs = new UserGroupDB().load();
        groups = new SelectItem[gs.size()];
        UserGroup ug = null;
        for (int i = 0; i < gs.size(); i++) {
            ug = gs.get(i);
            SelectItem si = new SelectItem(ug.getId(), ug.getName());
            groups[i] = si;
        }
        return groups;
    }

    public void setGroups(SelectItem[] groups) {
        this.groups = groups;
    }

    public ArrayList<UserInfo> getAllUser() {
        return new UserDB().loadAll();
    }
    private int profileId;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
    private String timeStamp;
    private String pass2;

    public String getPass2() {
        return pass2;
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void save() {
        if (!password.equals(pass2)) {
            message = "<html><font color='red'>Password do not match </font>";
            return;
        } else {
            message = "";
        }

        new UserDB().save(this);
        message = "User Created Successfully";
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void editPass() {


        if (!pass1.equals(pass2)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords Doesnot Matches", "Passwords Doesnot Matches");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            return;
        }
        boolean b = new UserDB().editPass(pass1, userId);
        if (b) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwords Changed", "Passwords Updated");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Passwords Not Changed", "Passwords Not Updated");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            message = "<html><font color='red'>Password Change failed ! </font></html>";
        }
    }

    public void setPass2(String pass2) {

        this.pass2 = pass2;
    }

    public String getTimeStamp() {

        return CommonDB.getTimeStamp();
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    private String error = "";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    private String userTaskId;

    public String getUserTaskId() {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst = cn.prepareStatement("");
            return userTaskId;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public void setUserTaskId(String userTaskId) {
        this.userTaskId = userTaskId;
    }

     public String nameP()
    {
        return "em";
    }
    public String logout() {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        ectx.invalidateSession();
        return "Login.jsf";
    }

    public void set() {
        String[] dd = currentDate.split("-");
        currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
        currentMonth = Integer.parseInt(dd[1]);
        currentYear = Integer.parseInt(dd[0]);
    }


    public String validate() {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
         System.out.println("Login : "+request.getParameter("user1")+" :: Login1 : "+request.getParameter("user"));
        int x = new UserDB().validate(userName, password,userOrgCode,this);
        System.err.println("Login status : "+x); 
        if(x == 2)
        {
            return "AdminLogin.jsf";
        }
        if (x == 3) {
            new UserTaskDB().insertNewTaskList();
            ActiveProfile ap = new ActiveProfile();
            ap.setOrgId(userOrgCode);
            if (profile != null) {
                ap.setProfile(profile);
            }
            
            currentDate = new CommonDB().getDate();
            String[] dd = currentDate.split("-");
            currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
            currentMonth = Integer.parseInt(dd[1]);
            currentYear = Integer.parseInt(dd[0]);
            ap.setMonthName(months[Integer.parseInt(dd[1])] + "," + dd[0]);
            ap.setMonth(currentMonth);
            ap.setYear(currentYear);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ActiveProfile", ap);
            return "MainPage.jsf";
        } 
        if (x == 1) {
          //  new UserTaskDB().insertNewTaskList();
            ActiveProfile ap = new ActiveProfile();
            ap.setOrgId(userOrgCode);
            if (profile != null) {
                ap.setProfile(profile);
            }
            
            currentDate = new CommonDB().getDate();
            String[] dd = currentDate.split("-");
            currentMonthName = months[Integer.parseInt(dd[1])] + "," + dd[0];
            currentMonth = Integer.parseInt(dd[1]);
            currentYear = Integer.parseInt(dd[0]);
            ap.setMonthName(months[Integer.parseInt(dd[1])] + "," + dd[0]);
            ap.setMonth(currentMonth);
            ap.setYear(currentYear);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ActiveProfile", ap);
            return "MainPage.jsf";
        } else {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong User Name or Password", ""));
            return "Login.jsf";
        }

        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "Main.jsf");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
