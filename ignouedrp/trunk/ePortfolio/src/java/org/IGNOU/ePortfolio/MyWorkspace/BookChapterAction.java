/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.BookChapterDao;
import org.IGNOU.ePortfolio.Model.BookChapter;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 23-Feb-2012
 */
public class BookChapterAction extends ActionSupport implements ModelDriven<Object> {

    private String user_id = new UserSession().getUserInSession();
    private BookChapter bc = new BookChapter();
    private BookChapterDao dao = new BookChapterDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public BookChapterAction() {
    }

    @Override
    public String execute() throws Exception {
        dao.saveBC(getBc());
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        getBc().setUserId(getUser_id());
        return getBc();
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
     * @return the bc
     */
    public BookChapter getBc() {
        return bc;
    }

    /**
     * @param bc the bc to set
     */
    public void setBc(BookChapter bc) {
        this.bc = bc;
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
