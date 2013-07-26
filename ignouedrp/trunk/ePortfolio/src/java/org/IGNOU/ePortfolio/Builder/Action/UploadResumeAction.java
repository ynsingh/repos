/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ResumeDao;
import org.IGNOU.ePortfolio.Model.Resume;

/**
 *
 * @author IGNOU Team
 */
public class UploadResumeAction extends ActionSupport implements ModelDriven<Resume>{
    private Resume res = new Resume();
    private ResumeDao rsdao=new ResumeDao();
    private String user_id=new UserSession().getUserInSession();
      
       
    public String UploadResume() throws Exception {
         
         rsdao.ResumeSave(res);
         return SUCCESS;
     }    
    
    
    
    @Override
    public Resume getModel() {
       res.setUserId(user_id);
         return res;
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
    
    
    
    
}
