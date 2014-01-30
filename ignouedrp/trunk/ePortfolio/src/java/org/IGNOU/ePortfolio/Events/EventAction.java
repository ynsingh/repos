package org.IGNOU.ePortfolio.Events;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.EventsDao;
import org.IGNOU.ePortfolio.Model.Events;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class EventAction extends ActionSupport implements Serializable, ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private EventsDao evntDao = new EventsDao();
    private Events evnt = new Events();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    @Override
    public String execute() throws Exception {
        evntDao.EventsSave(evnt);
        msg = infoSaved;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        evnt.setCreateDate(new Date());
        evnt.setPostponed(Boolean.FALSE);
        evnt.setCreator(user_id);
        return evnt;
    }

    /**
     * @return the evnt
     */
    public Events getEvnt() {
        return evnt;
    }

    /**
     * @param evnt the evnt to set
     */
    public void setEvnt(Events evnt) {
        this.evnt = evnt;
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
