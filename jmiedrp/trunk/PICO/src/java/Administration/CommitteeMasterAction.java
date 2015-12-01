/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;


//import org.apache.commons.validator.EmailValidator;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author sknaqvi
 */
public class CommitteeMasterAction extends DevelopmentSupport {

    private Committeemaster cm = new Committeemaster();
    private List<Committeemaster> cmList = new ArrayList<Committeemaster>();
    private CommitteemasterDAO cmDAO = new CommitteemasterDAO();

    private List<ErpmGenMaster> committeeTypesList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao erpmGenMasterDao = new ErpmGenMasterDao();

    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();

    private List<Subinstitutionmaster> simImIdList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();

    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();

    private String committeeLevel;
    private String message;
    private Integer committeeId;

   
    public void setMesssge(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setcm(Committeemaster cm) {
            this.cm = cm;
    }

    public Committeemaster getcm() {
        return cm;
    }

    public void setcommitteeTypesList(List<ErpmGenMaster> committeeTypesList) {
        this.committeeTypesList = committeeTypesList;
    }

    public List<ErpmGenMaster> getcommitteeTypesList() {
        return this.committeeTypesList;
    }
    
    public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

    public void setsimImIdList(List<Subinstitutionmaster> simImIdList) {
        this.simImIdList = simImIdList;
    }

    public List<Subinstitutionmaster> getsimImIdList() {
        return this.simImIdList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }
    
    public void setcmList(List<Committeemaster> cmList) {
        this.cmList = cmList;
    }

    public List<Committeemaster> getcmList() {
        return this.cmList;
    }

    public void setcommitteeId(Integer committeeId) {
        this.committeeId = committeeId;
    }

    public Integer getcommitteeId() {
        return this.committeeId;
    }

    public void setcommitteeLevel(String committeeLevel) {
        this.committeeLevel = committeeLevel;
    }

    public String getcommitteeLevel() {
        return this.committeeLevel;
    }

            
    @Override
     public String execute() throws Exception {
        try {
        //Initialize LOV,s
        InitializeitemsLOVs();
        return SUCCESS;
        }
        catch (Exception e)
        {
        message = message + e.getCause() + "  " + e.getMessage();
        return ERROR;
        }
        }


 public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update

            if(cm.getCommitteeId()== null) {
                if(cm.getSubinstitutionmaster().getSimId() == 0)
                  cm.setSubinstitutionmaster(null);
                if(cm.getDepartmentmaster().getDmId()==0)
                   cm.setDepartmentmaster(null);
               cmDAO.save(cm);
               cm = null;
               message = "Committee/Authority Record created successfully.";
            } else {

                Committeemaster cm2 = new Committeemaster();
                cm2 = cmDAO.findCommitteeById(cm.getCommitteeId());
                cm2 = cm;
                if(cm.getSubinstitutionmaster().getSimId() == 0)
                   cm2.setSubinstitutionmaster(null);
                if(cm.getDepartmentmaster().getDmId()==0)
                   cm2.setDepartmentmaster(null);

                cmDAO.update(cm2);

                message = "Committee/Authority Record updated successfully.";
           }

        //Initialize LOV,s
        InitializeitemsLOVs();
        return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                message = "The entry you are trying to make already exists.";
            else
                message = "Exception in Save method -> CommitteeMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

 @SkipValidation
public String FetchCommitteeMaster() throws Exception {
    try{
        /*if(cm.getSubinstitutionmaster().getSimId() == null )
            cmList = cmDAO.findCommittees(cm.getInstitutionmaster().getImId());
        else if (cm.getDepartmentmaster().getDmId()==0)
            cmList = cmDAO.findCommittees(cm.getInstitutionmaster().getImId(),cm.getSubinstitutionmaster().getSimId());
        else
            cmList = cmDAO.findCommittees(cm.getInstitutionmaster().getImId(), cm.getSubinstitutionmaster().getSimId(), cm.getDepartmentmaster().getDmId());
*/
	cmList = cmDAO.findCommittees(Short.valueOf(getSession().getAttribute("imId").toString()));
	if(cmList.size()== 0){
		message= "Committee  not created till now";
	}
        //Initialize LOV,s
        InitializeitemsLOVs();

        return SUCCESS;
    }
    catch (Exception e) {
            message = "Exception in Fetch method -> CommitteeMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String Edit() throws Exception {
     try {

           cm = cmDAO.findCommitteeById(getcommitteeId());

            InitializeitemsLOVs();
            return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in Edit method -> CommitteeMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String Delete() throws Exception {
     try {
          cm = cmDAO.findCommitteeById(getcommitteeId());

          cmDAO.delete(cm);

          //Initialize  LOV'S
          InitializeitemsLOVs();
          return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in Delete method -> CommitteeMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

@SkipValidation
public String Clear() throws Exception {
     try {
          message = "CLEARING ITEMS";
          cm = null;
        //Initialize LOV,s
        InitializeitemsLOVs();
        return SUCCESS;
         }
    catch (Exception e) {
         message = "Exception in Clear method -> CommitteeMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
         return ERROR;
        }
}

public void InitializeitemsLOVs() {
     //Prepare List of Institution under User's perview
            imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Prepare LOV containing the SubInstitutions forthe selected Institution
            //If Logged in user role is Administrator                            
                    if(getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0)
                        simImIdList = simDao.findSubInstForAdmin(Short.valueOf(getSession().getAttribute("imId").toString()));                
                    else
                        simImIdList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));

            //Prepare LOV containing the Department for the selected Selected Institution
                //If Logged in user role is Administrator                            
                    if(getSession().getAttribute("isAdministrator").toString().compareTo("Administrator") == 0)
                        dmList = dmDao.findBydmSimId(Integer.valueOf(Integer.valueOf(getSession().getAttribute("simId").toString())));
                    else
                        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(Integer.valueOf(getSession().getAttribute("simId").toString())));

            //Prepare List of Committee Types
            committeeTypesList = erpmGenMasterDao.findByErpmGmType(Short.parseShort("14"));

    }


public void  validate () {
    try {
        if(cm.getCommitteeId()==null) {
            if(cm.getInstitutionmaster().getImId()==null)
                addFieldError("cm.institutionmaster.imId","Please select Institute");
           // if(cm.getSubinstitutionmaster().getSimId()==null)
           //     addFieldError("cm.subinstitutionmaster.simId","Please  click here to select Subinstitute");
           // if(cm.getDepartmentmaster().getDmId()==null)
           //     addFieldError("cm.departmentmaster.dmId" ,"Please  click here to select Department");
            if(cm.getErpmGenMaster().getErpmgmEgmId()==null)
                addFieldError("cm.erpmGenMaster.erpmgmEgmId" ,"Please  click here to select Commitee Type");
            if(cm.getCommitteeLevel()==null)
                addFieldError("cm.committeeLevel" ,"Please click here to select Commitee Level");
            if(cm.getCommitteeName().toString().isEmpty())
                addFieldError("cm.committeeName" ,"Please enter Commitee/Authority  Name");
            if(cm.getCommitteeConvener().toString().isEmpty())
                addFieldError("cm.committeeConvener" ,"Please  enter Commitee convenor/Authority Email");
            if(cm.getCommitteeConvener().toString().indexOf("@") == -1)
                addFieldError("cm.committeeConvener" ,"Please  Type a valid   Commitee convenor/Authority Email");
            InitializeitemsLOVs();
        }
    }
 catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
}
}
}
