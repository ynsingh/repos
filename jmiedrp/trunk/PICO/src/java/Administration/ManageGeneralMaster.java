
/**
 *
 * @author kazim
 */

package Administration;

import pojo.hibernate.ErpmGenCtrl;
import pojo.hibernate.ErpmGenCtrlDao;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;


public class ManageGeneralMaster extends DevelopmentSupport {

    private ErpmGenCtrlDao erpmgctrlDao = new ErpmGenCtrlDao();
    private ErpmGenMasterDao erpmgmDao     = new ErpmGenMasterDao();

    private List<ErpmGenCtrl> erpmgctrlList = new ArrayList<ErpmGenCtrl>();

    private ErpmGenMaster erpmgm;
  
    private List<ErpmGenMaster> erpmgmList = new ArrayList<ErpmGenMaster>();

    private String message;
    int ErpmgmEgmId;
    

    public ErpmGenMaster geterpmgm() {
        return this.erpmgm;
    }

    public void seterpmgm(ErpmGenMaster erpmgm) {
        this.erpmgm = erpmgm;
    }      

   public List<ErpmGenCtrl> geterpmgctrlList() {
        return this.erpmgctrlList;
    }

   public void seterpmgctrlList(List<ErpmGenCtrl> erpmgctrlList) {
        this.erpmgctrlList = erpmgctrlList;
    }

   public List<ErpmGenMaster> geterpmgmList() {
        return this.erpmgmList;
    }

   public void seterpmgmList(List<ErpmGenMaster> erpmgmList) {
        this.erpmgmList = erpmgmList;
    }

    public int getErpmgmEgmId() {
        return this.ErpmgmEgmId;
    }

    public void setErpmgmEgmId(int ErpmgmEgmId) {
        this.ErpmgmEgmId = ErpmgmEgmId;
    }

   public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String execute() throws Exception {
        try {
            erpmgctrlList = erpmgctrlDao.findAll();            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageGeneralMasterAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


 public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if(erpmgm.getErpmgmEgmId() == null) {
                 if (erpmgm.getErpmGenCtrl().getErpmgcGenType() == null) {
                        message = "Please select Control Parameter";
                  erpmgctrlList = erpmgctrlDao.findAll();
                }
                 else if(erpmgm.getErpmgmEgmDesc().length() == 0)
                        message = "Please provide a value for General Master Entry";
                 else {
                        erpmgmDao.save(erpmgm);
                        message = "General Master Entry created successfully.";
                 }

                
            } else {
                ErpmGenMaster erpmgm2 = erpmgmDao.findByErpmGmId(erpmgm.getErpmgmEgmId());
                erpmgm2 = erpmgm;
                erpmgmDao.update(erpmgm2);
                message = "General Master updated successfully.";
           }
           // Prepare list for showing General Master Entries
           // erpmgmList = erpmgmDao.findByErpmGmType( erpmgm.getErpmGenCtrl().getErpmgcGenType());

            //Prepare LOV for showing Control Parameters
            erpmgctrlList = erpmgctrlDao.findAll();
            
            return SUCCESS;
            
        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                message = "The entry you are trying to make already exists.";
            else
                message = "Exception in Save method -> ManageGeneralMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String FetchGMEntries() throws Exception {
    try{                       
        erpmgmList = erpmgmDao.findByErpmGmType(erpmgm.getErpmGenCtrl().getErpmgcGenType());
                      
        erpmgctrlList = erpmgctrlDao.findAll();

        return SUCCESS;
    }
    catch (Exception e) {
            message = "Exception in FetchGM method -> ManageGeneralMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String Export() throws Exception {
    try{                       
        //erpmgmList = erpmgmDao.findByErpmGmType(erpmgm.getErpmGenCtrl().getErpmgcGenType());
        erpmgmList = erpmgmDao.findAll();              
        erpmgctrlList = erpmgctrlDao.findAll();

        return SUCCESS;
    }
    catch (Exception e) {
            message = "Exception in FetchGM method -> ManageGeneralMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}
public String Edit() throws Exception {
        try {

            //Prepare LOV for showing Control Parameters
            erpmgctrlList = erpmgctrlDao.findAll();

            //Retrieve the record to be edited into erpmgm object
            erpmgm = erpmgmDao.findByErpmGmId(getErpmgmEgmId());
            
            // Prepare list for showing General Master Entries
            erpmgmList = erpmgmDao.findByErpmGmType( erpmgm.getErpmGenCtrl().getErpmgcGenType());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageGeneralMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted into erpmgm object
            erpmgm = erpmgmDao.findByErpmGmId(getErpmgmEgmId());
            erpmgmDao.delete(erpmgm);
            message = "Record successfully deleted";
            erpmgctrlList = erpmgctrlDao.findAll();
            // Prepare list for showing General Master Entries
            //erpmgmList = erpmgmDao.findAll();//findByErpmGmType(erpmgm.getErpmGenCtrl().getErpmgcGenType());
             erpmgm = null;
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("FOREIGN KEY"))
                    message = "Cannot delete record as related record(s) exist(s). Reported cause is         :" + e.getCause();
            else
                    message = "Exception in Delete method -> ManageGeneralMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    public String Clear() throws Exception {
        try {
            erpmgm = null;

            //Prepare LOV for showing Control Parameters
            erpmgctrlList = erpmgctrlDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> ManageGeneralMasterAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void validate()  {
    try {
        //message="hi in validate";
        if(erpmgm.getErpmGenCtrl().getErpmgcGenType()==null) {
            addFieldError("erpmgm.erpmGenCtrl.erpmgcGenType","please select control patrameter!");
            erpmgctrlList = erpmgctrlDao.findAll();
        }
        if(erpmgm.getErpmgmEgmDesc().toString()==null) {
           addFieldError("erpmgm.erpmgmEgmDesc","please give GM Entry");
erpmgctrlList = erpmgctrlDao.findAll();
        }
        }
    catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
        }
    }


}
