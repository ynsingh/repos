/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Group.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.PeerGroupDao;
import org.IGNOU.ePortfolio.Model.UserList;

/**
 *
 * @author Vinay
 */
public class MyPeerGroupAction extends ActionSupport /*implements SessionAware*/ {

    private String user_id = new UserSession().getUserInSession();
    private PeerGroupDao dao = new PeerGroupDao();
    private UserList userModel = new UserList();
    private List<UserList> PeerGroupListList;
    private String title = getText("title.myPeergroup");

    public MyPeerGroupAction() {
    }

    public String PeerGroupList() throws Exception {
        PeerGroupListList = dao.PeerGroupList(user_id);
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
     * @return the PeerGroupListList
     */
    public List<UserList> getPeerGroupListList() {
        return PeerGroupListList;
    }

    /**
     * @param PeerGroupListList the PeerGroupListList to set
     */
    public void setPeerGroupListList(List<UserList> PeerGroupListList) {
        this.PeerGroupListList = PeerGroupListList;
    }

    /**
     * @return the userModel
     */
    public UserList getUserModel() {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(UserList userModel) {
        this.userModel = userModel;
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

}
