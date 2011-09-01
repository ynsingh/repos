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


public class BudgetTypeMasterBrowseAction extends ActionSupport {

    private Budgettypemaster btm = new Budgettypemaster();
    private BudgettypemasterDAO btmDao = new BudgettypemasterDAO();
    private Byte btmId;
    private String btmName;

    private String message;
    private String editFlag;

    private List<Budgettypemaster> btmList = new ArrayList<Budgettypemaster>();

    public String getbtmName() {
            return btmName;
    }

    public void setbtmName(String btmName) {
            this.btmName = btmName;
    }

    public Byte getbtmId() {
            return btmId;
    }

    public void setbtmId(Byte btmId) {
            this.btmId = btmId;
    }

    public String getMessage() {
            return message;
    }

    public List<Budgettypemaster> getbtmList() {
        return btmList;
    }

    public void setbtmList(List<Budgettypemaster> btmList) {
        this.btmList = btmList;
    }

     public String geteditFlag() {
        return editFlag;
    }
    public void seteditFlag(String editFlag) {
        this.editFlag = editFlag;
    }


    @Override

      public String execute() throws Exception {
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

public String delete() {
   try {       
        Budgettypemaster btmForDelete = btmDao.findBybtmId(getbtmId());
        btmDao.delete(btmForDelete);
        btmList = btmDao.findAll();
        return SUCCESS;
    }
        catch (Exception e)
            {
                message = "Exception in Delete Budget Type Master" + e.getMessage();
                return ERROR;
            }
}

 public String edit() {
 //       btmList = btmDao.findAll();
        btm = btmDao.findBybtmId(getbtmId());
        //btmId = btm.getbtmId();

        btmId = btm.getBtmId();

        // btmName = btm.btmName();
         btmName = btm.getBtmName();
        return SUCCESS;
    }


}
