/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */

import pojo.hibernate.Budgettypemaster;
import pojo.hibernate.BudgettypemasterDAO;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class bugettypemasterAction extends ActionSupport {

    private Budgettypemaster btm = new Budgettypemaster();
    private BudgettypemasterDAO btmDao = new BudgettypemasterDAO();
        private String message;
    //private String editFlag;

    private List<Budgettypemaster> btmList = new ArrayList<Budgettypemaster>();

  
public void setbtm(Budgettypemaster btm) {
            this.btm = btm;
    }

    public Budgettypemaster getbtm() {
            return btm;
    }
    public List<Budgettypemaster> getbtmList() {
        return btmList;
    }

    public void setbtmList(List<Budgettypemaster> btmList) {
        this.btmList = btmList;
    }
 
      public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

  
  
    /* public String geteditFlag() {
        return editFlag;
    }

     public void seteditFlag(String editFlag) {
        this.editFlag = editFlag;
    }*/
    
    @Override

      public String execute() throws Exception {
        try {
        return SUCCESS;
        }
       catch (Exception e)
       {
           return ERROR;
       }
}

    public String Save() throws Exception {
        try {
 message="helobtmid"+btm.getBtmId();
            if (btm.getBtmId()!= null)
            {

          btmDao.save(btm);

            }
            else
            {
            //Initialize the new object btm with the values on the form
                Budgettypemaster btm1 =btmDao.findBybtmId(btm.getBtmId());

                btm1=btm;

                btmDao.update(btm1);

               // message = "Here";
            }
        return SUCCESS;
        }
       catch (Exception e)
       {
if (e.getCause().toString().contains("UNIQUE_BTM_Name"))
                message = "Please enter unique value for Budget Type Name";
           else
             message = "Error in BTMA: " + e.getMessage(); // + e.getCause().toString();
           return ERROR;
       }
}

    public String Browse(){

        try {
        btmList = btmDao.findAll();
        return SUCCESS;
        }
        catch (Exception e)
            {
                message = "Exception in RetrieveBudgetTypeMaster" + e.getMessage();
                return ERROR;
            }
}
    

}
