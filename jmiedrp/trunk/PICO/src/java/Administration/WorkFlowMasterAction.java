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
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Workflowmaster;
import pojo.hibernate.WorkflowmasterDAO;
import pojo.hibernate.Workflowdetail;
import pojo.hibernate.WorkflowdetailDAO;
import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;



import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.Workflowactions;
import pojo.hibernate.WorkflowactionsDAO;

/**
 *
 * @author sknaqvi
 */
public class WorkFlowMasterAction extends DevelopmentSupport {

    private Workflowmaster              wfm             = new Workflowmaster();
    private List<Workflowmaster>        wfmList         = new ArrayList<Workflowmaster>();
    private WorkflowmasterDAO           wfmDAO          = new WorkflowmasterDAO();

    private Workflowdetail              wfdtl           = new Workflowdetail();
    private List<Workflowdetail>        wfdtlList        = new ArrayList<Workflowdetail>();
    private WorkflowdetailDAO           wfdtlDAO        = new WorkflowdetailDAO();

    private Workflowactions             wfactn          = new Workflowactions();
    private List<Workflowactions>       wfactnList      = new ArrayList<Workflowactions>();
    private WorkflowactionsDAO          wfactnDAO       = new WorkflowactionsDAO();

    private List<Committeemaster>       scommitteeList  = new ArrayList<Committeemaster>();
    private List<Committeemaster>       dcommitteeList  = new ArrayList<Committeemaster>();
    private CommitteemasterDAO          committeeDAO    = new CommitteemasterDAO();

    private List<Institutionmaster>     imIdList        = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO        imDao           = new InstitutionmasterDAO();

    private List<Subinstitutionmaster>  simImIdList     = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO     simDao          = new SubinstitutionmasterDAO();

    private List<Departmentmaster>      dmList          = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO         dmDao           = new DepartmentmasterDAO();

//    private ErpmGenMaster               egm           = new ErpmGenMaster();
    private List<ErpmGenMaster>         egmList         = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao            egmDAO          = new ErpmGenMasterDao();

    private List<ErpmGenMaster>         WfaActionsList  = new ArrayList<ErpmGenMaster>();

    private String message;
    private Integer wfmId;
    private Integer wfdId;
    private Integer wfaId;

    public void setMesssge(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setwfm(Workflowmaster wfm) {
            this.wfm = wfm;
    }

    public Workflowmaster getwfm() {
        return wfm;
    }

     public void setwfdtl(Workflowdetail wfdtl) {
            this.wfdtl = wfdtl;
    }

    public Workflowdetail getwfdtl() {
        return wfdtl;
    }

     public void setwfactn(Workflowactions wfactn) {
            this.wfactn = wfactn;
    }

    public Workflowactions getwfactn() {
        return wfactn;
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

    public void setegmList(List<ErpmGenMaster> egmList) {
        this.egmList = egmList;
    }

    public List<ErpmGenMaster> getegmList() {
        return this.egmList;
    }
    
    public void setwfmList(List<Workflowmaster> wfmList) {
        this.wfmList = wfmList;
    }

    public List<Workflowmaster> getwfmList() {
        return this.wfmList;
    }

    public void setwfdtlList(List<Workflowdetail> wfdtlList) {
        this.wfdtlList = wfdtlList;
    }

    public List<Workflowdetail> getwfdtlList() {
        return this.wfdtlList;
    }

    public void setwfactnList(List<Workflowactions> wfactnList) {
        this.wfactnList = wfactnList;
    }

    public List<Workflowactions> getwfactnList() {
        return this.wfactnList;
    }

    public List<Committeemaster> getscommitteeList() {
        return this.scommitteeList;
    }

    public void setscommitteeList(List<Committeemaster> scommitteeList) {
        this.scommitteeList = scommitteeList;
    }

    public List<Committeemaster> getdcommitteeList() {
        return this.dcommitteeList;
    }

    public void setdcommitteeList(List<Committeemaster> dcommitteeList) {
        this.dcommitteeList = dcommitteeList;
    }

    public Integer getwfmId() {
        return this.wfmId;
    }

    public void setwfmId(Integer wfmId) {
        this.wfmId = wfmId;
    }
              
    public Integer getwfdId() {
        return this.wfdId;
    }

    public void setwfdId(Integer wfdId) {
        this.wfdId = wfdId;
    }

    public Integer getwfaId() {
        return this.wfaId;
    }

    public void setwfaId(Integer wfaId) {
        this.wfaId = wfaId;
    }

    public void setWfaActionsList(List<ErpmGenMaster> WfaActionsList) {
        this.WfaActionsList = WfaActionsList;
    }

    public List<ErpmGenMaster> getWfaActionsList() {
        return this.WfaActionsList;
    }

    @Override
     public String execute() throws Exception {
        try {

        //Initialize LOVs
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
                     
            if(wfm.getWfmId() == null) {
                if(wfm.getSubinstitutionmaster().getSimId() == 0)
                   wfm.setSubinstitutionmaster(null);
                if(wfm.getDepartmentmaster().getDmId() == 0)
                   wfm.setDepartmentmaster(null);
                   wfmDAO.save(wfm);
                   wfm=null;

                   message = "Workflow Record created successfully.";
            }
            else
            {
                Workflowmaster wfm2 = new Workflowmaster();
                wfm2 = wfmDAO.findWorkFlowById(wfm.getWfmId());
                wfm2 = wfm;

                if(wfm2.getSubinstitutionmaster().getSimId() == 0)
                    wfm2.setSubinstitutionmaster(null);
                if(wfm2.getDepartmentmaster().getDmId()==0)
                    wfm2.setDepartmentmaster(null);
                wfmDAO.update(wfm2);

                message = "Workflow Record updated successfully.";
           }

//      Initialize LOV,s
        InitializeitemsLOVs();
        return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                message = "The entry you are trying to make already exists.";
            else
                message = "Exception in Save method -> WorkFlowMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String Edit() throws Exception {
     try {

            wfm = wfmDAO.findWorkFlowById(getwfmId());

            InitializeitemsLOVs();
            return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in Edit method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String Delete() throws Exception {
     try {
          wfm = wfmDAO.findWorkFlowById(getwfmId());

          wfmDAO.delete(wfm);
         
          message = "Record deleted successfully";

          wfm = null;
          //Initialize  LOV'S
          InitializeitemsLOVs();
          
          return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in Delete method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

@SkipValidation

public String Clear() throws Exception {
     try {
         
         wfm = null;         
        //Initialize LOV,s
        InitializeitemsLOVs();
        return SUCCESS;
         }
    catch (Exception e) {
         message = "Exception in Clear method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
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

           //Prepare LOV containing the Department for the selected Selected Institution
           egmList = egmDAO.findByErpmGmType(Short.parseShort("16"));//findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));

}


public void  validate () {
    try {
        if(wfm.getWfmId()==null) {
        //if(wfdtl.getWfdId()==null) {
          if(wfm.getInstitutionmaster().getImId()==null)
            addFieldError("wfm.institutionmaster.imId","Please select Institution");
            if(wfm.getErpmGenMaster().getErpmgmEgmId()==0)
            addFieldError("wfm.erpmGenMaster.erpmgmEgmId","Please select Workflow Type");
          InitializeitemsLOVs();
        }
        // if(wfdtl.getCommitteemasterByWfdSourceCommittee().toString()==null)
          //      addFieldError("wfdtl.committeemasterByWfdSourceCommittee","Please select Source Committee/Authority");
    //}
    }
 catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
}
}

    @SkipValidation

    public String FetchWorkFlowMaster() throws Exception {

            if(wfm.getSubinstitutionmaster().getSimId() == null )
                wfmList=wfmDAO.findWorkFlowRecords(wfm.getInstitutionmaster().getImId());
            else if (wfm.getDepartmentmaster().getDmId()==0)
                wfmList=wfmDAO.findWorkFlowRecords(wfm.getInstitutionmaster().getImId(),wfm.getSubinstitutionmaster().getSimId());
            else
                wfmList = wfmDAO.findWorkFlowRecords(wfm.getInstitutionmaster().getImId(), wfm.getSubinstitutionmaster().getSimId(), wfm.getDepartmentmaster().getDmId());      
        //Initialize LOV,s
        InitializeitemsLOVs();

        return SUCCESS;
    }

//**************************************************************************************************************************//
//                              Methods to deal with WorkFlow Details Start Here
//*************************************************************************************************************************//

//This method prepares user interface for Workflow Details management
public String AddWorkFlowDetail() throws Exception {
   
    //Retrieve the WorkflowMaster Object
    wfm = wfmDAO.findWorkFlowById(getwfmId());
    
   //Prepare LOVs for adding/updating/deleting enteries of Workflow detail Objects
    InitializeWFDLOVs();


    //wfdtl = new Workflowdetail();
    wfdtl.setWfdStage(wfdtlDAO.findWorkFlowDetailForWFM(getwfmId()));

    //Prepare Work Flow Records
    wfdtlList = wfdtlDAO.findWorkFlowDetailsForWFM(wfm.getWfmId());
    
    //Dispay the screen for Adding Workflow Details
    return SUCCESS;
}

//Initializiation of LOVs on the Screen
public void InitializeWFDLOVs() {
    if(wfm.getSubinstitutionmaster() == null)
        //Source Committe List (scommitteeId = 0 means User
        scommitteeList = committeeDAO.findCommitteeByInstitution(wfm.getInstitutionmaster().getImId());
    else if (wfm.getDepartmentmaster() == null)
        //Source Committe List (scommitteeId = 0 means User
        scommitteeList = committeeDAO.findCommitteeBySubInstitution(wfm.getSubinstitutionmaster().getSimId());
    else
        scommitteeList = committeeDAO.findCommitteeByDepartment(wfm.getDepartmentmaster().getDmId());

        dcommitteeList = scommitteeList;
    }

public String SaveWorkFlowDetail() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if(wfdtl.getWfdId() == null) {
                if(wfdtl.getCommitteemasterByWfdSourceCommittee().getCommitteeId() == 0)
                    wfdtl.setCommitteemasterByWfdSourceCommittee(null);
                else if(wfdtl.getCommitteemasterByWfdDestinationCommittee().getCommitteeId() == 0)
                    wfdtl.setCommitteemasterByWfdDestinationCommittee(null);
                wfdtl.setWorkflowmaster(wfm);
                wfdtlDAO.save(wfdtl);
                wfdtl=new Workflowdetail();;

                message = "Workflow Detail Record created successfully.";
                wfm = wfmDAO.findWorkFlowById(wfm.getWfmId());
                wfdtl.setWfdStage(wfdtlDAO.findWorkFlowDetailForWFM(wfm.getWfmId()));
            } 
            else {
                Workflowdetail wfdtl2 = new Workflowdetail();
                wfdtl2 = wfdtlDAO.findWorkFlowDetailById(wfdtl.getWfdId());
                wfdtl.setWorkflowmaster(wfm);
                wfdtl2 = wfdtl;
                if(wfdtl.getCommitteemasterByWfdSourceCommittee().getCommitteeId() == 0)
                    wfdtl2.setCommitteemasterByWfdSourceCommittee(null);
                else if(wfdtl.getCommitteemasterByWfdDestinationCommittee().getCommitteeId() == 0)
                    wfdtl.setCommitteemasterByWfdDestinationCommittee(null);

                wfdtlDAO.update(wfdtl2);
                wfdtl=new Workflowdetail();
                wfm = wfmDAO.findWorkFlowById(wfm.getWfmId());
                wfdtl.setWfdStage(wfdtlDAO.findWorkFlowDetailForWFM(wfm.getWfmId()));
                message = "Workflow Detail Record updated successfully.";
           }

        //Initialize LOVs
        InitializeWFDLOVs();
        //Prepare Workflow Records
        wfdtlList = wfdtlDAO.findWorkFlowDetailsForWFM(wfm.getWfmId());
        
        return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                message = "The entry you are trying to make already exists.";
            else
                message = "Exception in Save method -> WorkFlowMasterAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String ClearWorkFlowDetail() throws Exception {
     try {
         
        wfdtl = null;
        wfdtl=new Workflowdetail();

        //Retrieve the WorkflowMaster Object
        wfm = wfmDAO.findWorkFlowById(wfm.getWfmId());

        //Prepare LOVs for adding/updating/deleting enteries of Workflow detail Objects
        InitializeWFDLOVs();

        // Set stage
        wfdtl.setWfdStage(wfdtlDAO.findWorkFlowDetailForWFM(wfm.getWfmId()));

        //Prepare Work Flow Records
        wfdtlList = wfdtlDAO.findWorkFlowDetailsForWFM(wfm.getWfmId());

        return SUCCESS;
         }
    catch (Exception e) {
         message = "Exception in Clear method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
         return ERROR;
        }
}

public String EditWorkFlowDetail() throws Exception {
     try {
            wfdtl = wfdtlDAO.findWorkFlowDetailById(getwfdId());

            wfm = wfmDAO.findWorkFlowById(wfdtl.getWorkflowmaster().getWfmId());

            InitializeWFDLOVs();

            return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in EditWorkFlowDetail method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String DeleteWorkFlowDetail() throws Exception {
     try {
          wfdtl = wfdtlDAO.findWorkFlowDetailById(getwfdId());

          wfdtlDAO.delete(wfdtl);

          message = "Record deleted successfully";
          
          wfm = wfmDAO.findWorkFlowById(wfdtl.getWorkflowmaster().getWfmId());
          wfdtlList = wfdtlDAO.findWorkFlowDetailsForWFM(wfm.getWfmId());
          //Initialize  LOV'S
          InitializeWFDLOVs();

          return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in DeleteWorkFlowDetail method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String FetchWorkFlowDetail() {
    try {
       // wfdtl = wfdtlDAO.findWorkFlowDetailById(getwfdId());

        //For Getting the Name of the workflow in Add workflow details screen after fetch the record
       // wfm = wfmDAO.findWorkFlowById(wfdtl.getWorkflowmaster().getWfmId());
        wfm = wfmDAO.findWorkFlowById(wfm.getWfmId());
        //getting all the details record
        wfdtlList = wfdtlDAO.findWorkFlowDetailsForWFM(wfm.getWfmId());
        scommitteeList = committeeDAO.findCommitteeByInstitution(wfm.getInstitutionmaster().getImId());
        dcommitteeList = committeeDAO.findCommitteeByInstitution(wfm.getInstitutionmaster().getImId());
        
        return SUCCESS;
    }

        catch(Exception e) {
            message = "Exception in FetchWorkFlowDetail method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

//**************************************************************************************************************************//
//                              Methods to deal with WorkFlow Actions Start Here
//*************************************************************************************************************************//

//This method prepares user interface for Workflow Actions Management
public String AddWorkFlowActions() throws Exception {

    //Retrieve the WorkflowDetail Object
    wfdtl=wfdtlDAO.findWorkFlowDetailById(getwfdId());
    //Prepare LOV for Action List
    WfaActionsList = egmDAO.findByErpmGmType(Short.parseShort("17"));

    // Prepare Workflow Action Records List
    wfactnList = wfactnDAO.findWorkFlowActionsForWFD(getwfdId());

    //Dispay the screen for Adding Workflow Details
    return SUCCESS;
}

public String SaveWorkFlowActions() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if(wfactn.getWfaId() == null) {
                wfactn.setWorkflowdetail(wfdtl);
                wfactnDAO.save(wfactn);
                wfactn=new Workflowactions();

                message = "Workflow Action Record created successfully.";
                wfdtl = wfdtlDAO.findWorkFlowDetailById(wfdtl.getWfdId());
            }
            else {
                Workflowactions wfactn2 = new Workflowactions();
                wfactn2=wfactnDAO.findWorkflowactionsById(wfactn.getWfaId());
                wfactn.setWorkflowdetail(wfdtl);
                wfactn2 = wfactn;
                wfactnDAO.update(wfactn2);
                wfactn=new Workflowactions();
                wfdtl = wfdtlDAO.findWorkFlowDetailById(wfdtl.getWfdId());
                message = "Workflow Action Record updated successfully.";
            }
            
            //Prepare LOV for Action List
            WfaActionsList = egmDAO.findByErpmGmType(Short.parseShort("17"));

            // Prepare Workflow Action Records List
            wfactnList = wfactnDAO.findWorkFlowActionsForWFD(wfdtl.getWfdId());

        return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                message = "The entry you are trying to make already exists.";
            else
                message = "Exception in Save method -> WorkFlowActionAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}


public String ClearWorkFlowAction() throws Exception {
     try {
          wfactn = null;
        //Retrieve the WorkflowMaster Object
        wfactn = new Workflowactions();

        //Prepare LOV for Action List
        WfaActionsList = egmDAO.findByErpmGmType(Short.parseShort("17"));

        // Prepare Workflow Action Records List
        wfactnList = wfactnDAO.findWorkFlowActionsForWFD(wfdtl.getWfdId());
        return SUCCESS;
         }
    catch (Exception e) {
         message = "Exception in Clear Actions method -> WorkFlowActionAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
         return ERROR;
        }
}

public String EditWorkFlowAction() throws Exception {
     try {
            wfactn = wfactnDAO.findWorkflowactionsById(getwfaId());   //.findWorkFlowDetailById(getwfdId());

            wfdtl = wfdtlDAO.findWorkFlowDetailById(wfactn.getWorkflowdetail().getWfdId()); //findWorkFlowById(wfdtl.getWorkflowmaster().getWfmId());

            //Prepare LOV for Action List
            WfaActionsList = egmDAO.findByErpmGmType(Short.parseShort("17"));

            // Prepare Workflow Action Records List
            wfactnList = wfactnDAO.findWorkFlowActionsForWFD(wfdtl.getWfdId());

            return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in EditWorkFlowDetail method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}

public String DeleteWorkFlowAction() throws Exception {
     try {
          wfactn = wfactnDAO.findWorkflowactionsById(getwfaId());

          wfactnDAO.delete(wfactn);

          message = "Record deleted successfully";

          wfdtl = wfdtlDAO.findWorkFlowDetailById(wfactn.getWorkflowdetail().getWfdId()); //findWorkFlowById(wfdtl.getWorkflowmaster().getWfmId());

          //Prepare LOV for Action List
          WfaActionsList = egmDAO.findByErpmGmType(Short.parseShort("17"));

          // Prepare Workflow Action Records List
          wfactnList = wfactnDAO.findWorkFlowActionsForWFD(wfdtl.getWfdId());

          return SUCCESS;
      }
    catch (Exception e) {
            message = "Exception in DeleteWorkFlowAction method -> WorkFlowMasterAxn  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}
}
