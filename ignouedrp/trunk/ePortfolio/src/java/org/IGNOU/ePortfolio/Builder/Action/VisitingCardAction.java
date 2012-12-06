/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.VisitingCardDao;
import org.IGNOU.ePortfolio.Model.Vistingcard;

/**
 *
 * @author Amit
 */
public class VisitingCardAction extends ActionSupport implements ModelDriven<Vistingcard> {

    private String user_id = new UserSession().getUserInSession();
    private VisitingCardDao vcDao = new VisitingCardDao();
    private Vistingcard vcMod = new Vistingcard();
    

    public String VisitingCardAdd() {
        vcDao.AddVisitingCardDetail(vcMod);
        return SUCCESS;
    }

   

    @Override
    public Vistingcard getModel() {
        vcMod.setUserId(user_id);
        return vcMod;
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
     * @return the vcDao
     */
    public VisitingCardDao getVcDao() {
        return vcDao;
    }

    /**
     * @param vcDao the vcDao to set
     */
    public void setVcDao(VisitingCardDao vcDao) {
        this.vcDao = vcDao;
    }

    /**
     * @return the vcMod
     */
    public Vistingcard getVcMod() {
        return vcMod;
    }

    /**
     * @param vcMod the vcMod to set
     */
    public void setVcMod(Vistingcard vcMod) {
        this.vcMod = vcMod;
    }

    /**
     * @return the vcList
     */
   
}
