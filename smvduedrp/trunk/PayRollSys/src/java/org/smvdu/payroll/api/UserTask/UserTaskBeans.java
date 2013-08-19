/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.UserTask;

import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.module.attendance.LoggedEmployee;


/**
 *
 * @author ERP
 */
public class UserTaskBeans {

    /** Creates a new instance of UserTaskBeans */
    public UserTaskBeans() {
        try {
            LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            if (le == null) {
                UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                userName = uf.getUserName();
                System.out.println("DAta Should Be Write Here : 3214 : --" +userName);
            } else {
                //orgCode = le.getUserOrgCode();
                System.out.println("DAta Should Be Write Here : 32142323 : " +userName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    private UIData dataGrid;
    private String taskId;
    private String taskName;
    private String userName;
    private String menuName;
    private String menuItemName;
    private boolean menuStatus;;
    private String taskValue;

    public String getTaskValue() {
        return taskValue;
    }

    public void setTaskValue(String taskValue) {
        this.taskValue = taskValue;
    }
    
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public boolean isMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(boolean menuStatus) {
        this.menuStatus = menuStatus;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    private boolean check;
    private ArrayList<UserTaskBeans> taskList;

    public ArrayList<UserTaskBeans> getTaskList() {
           taskList = new UserTaskDB().taskList();
           dataGrid.setValue(taskList);
           return taskList;
    }

    public void setTaskList(ArrayList<UserTaskBeans> taskList) {
        this.taskList = taskList;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        System.out.println("Flag Value : "+check);
        this.check = check;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    public void saveTaskList()
    {
        try
        {
            ArrayList<UserTaskBeans> utb = (ArrayList<UserTaskBeans>) dataGrid.getValue();
            ArrayList<UserTaskBeans> copyUtb = new ArrayList<UserTaskBeans>();
            for(UserTaskBeans ub : utb)
            {
                if(ub.isCheck() == true)
                {
                    copyUtb.add(ub);
                    System.out.println("Task ID :"+ub.getTaskId());
                }
            }
            Exception ex = new UserTaskDB().saveUserTaskList(copyUtb);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
