/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Group.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.FacultyGroupDao;
import org.IGNOU.ePortfolio.Model.UserList;

/**
 *
 * @author Vinay
 */
public class MyFacultyAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private String user_id = new UserSession().getUserInSession();
    private FacultyGroupDao dao = new FacultyGroupDao();
    private List<UserList> MyFacultyList;
    private String title = getText("title.myFaculty");
    private int listSize;

    public MyFacultyAction() {
    }

    public String MyFacultyList() throws Exception {
        MyFacultyList = dao.FacultyListByEmailId(user_id);
        listSize = MyFacultyList.size();
        return SUCCESS;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    /**
     * @return the FacultyListByEmailId
     */
    public List<UserList> getMyFacultyList() {
        return MyFacultyList;
    }

    /**
     * @param FacultyListByEmailId the FacultyListByEmailId to set
     */
    public void setMyFacultyList(List<UserList> MyFacultyList) {
        this.MyFacultyList = MyFacultyList;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the listSize
     */
    public int getListSize() {
        return listSize;
    }

    /**
     * @param listSize the listSize to set
     */
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
}
