/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.TalkLectureDao;
import org.IGNOU.ePortfolio.Model.TalkLecture;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 17-02-2012
 */
public class TalkLectureAddAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private TalkLectureDao dao = new TalkLectureDao();
    private TalkLecture tlModel = new TalkLecture();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public TalkLectureAddAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.TalkLectureSave(getTlModel());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getTlModel().setUserId(getUser_id());
        return getTlModel();
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
     * @return the tlModel
     */
    public TalkLecture getTlModel() {
        return tlModel;
    }

    /**
     * @param tlModel the tlModel to set
     */
    public void setTlModel(TalkLecture tlModel) {
        this.tlModel = tlModel;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}
