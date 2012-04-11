/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;
/**
 * @author SKNaqvi
 */

import pojo.hibernate.ErpmGeneralTerms;
import pojo.hibernate.ErpmGeneralTermsDAO;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;

public class ManageGeneralTermsAction extends DevelopmentSupport{

private ErpmGeneralTerms GTerms = new ErpmGeneralTerms();

private List<ErpmGeneralTerms> GTermsList = new ArrayList<ErpmGeneralTerms>();
private ErpmGeneralTermsDAO GTermsDao = new ErpmGeneralTermsDAO ();
private List<Institutionmaster> termsImIdList = new ArrayList<Institutionmaster>();
private List<ErpmGenMaster> termsTypeList = new ArrayList<ErpmGenMaster>();
private InstitutionmasterDAO imDao= new InstitutionmasterDAO();
private ErpmGenMasterDao termsTypeDao = new ErpmGenMasterDao();
private String message;
private Integer GTGTID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setGTerms(ErpmGeneralTerms GTerms) {
        this.GTerms = GTerms;
    }

    public ErpmGeneralTerms getGTerms() {
        return this.GTerms;
    }

    public List<Institutionmaster> gettermsImIdList() {
        return termsImIdList;
    }
    public void settermsImIdList(List<Institutionmaster> termsImIdList) {
        this.termsImIdList = termsImIdList;
    }

    public List<ErpmGenMaster> gettermsTypeList() {
        return termsTypeList;
    }
    public void settermsTypeList(List<ErpmGenMaster> termsTypeList) {
        this.termsTypeList = termsTypeList;
    }

    public List<ErpmGeneralTerms> getGTermsList() {
        return GTermsList;
    }

    public void setGTermsList(List<ErpmGeneralTerms> GTermsList) {
        this.GTermsList = GTermsList;
    }

    public Integer getGTGTID() {
        return GTGTID;
    }

    public void setGTGTID(Integer GTGTID) {
        this.GTGTID = GTGTID;
    }


    @Override


    public String execute() throws Exception {

        try {
            termsImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
            return SUCCESS;
            } catch (Exception e) {
                message = "Exception in Execute method -> ManageGeneralTermsAxn"  + e.getMessage() + e.getCause();
                return ERROR;
       }
    }

 public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (GTerms.getGtGtid() == null) {
                GTermsDao.save(GTerms);
                message = "General Terms record saved successfully. General Terms Id is " + GTerms.getGtGtid();
            } else {
                ErpmGeneralTerms gterms1 =  GTermsDao.findBygtGtid(GTerms.getGtGtid());
                gterms1 = GTerms;
                GTermsDao.update(gterms1);
                message = "General Terms record update successfully.";
            }
            
            GTerms = null;
            termsImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            termsTypeList = termsTypeDao.findByErpmGmType(Short.parseShort("12"));

            return SUCCESS;
        } catch (Exception e) {
//            if (e.getCause().toString().contains("GT_IMT_ID_UNIQUE"))
            if (e.getCause().toString().contains("GT_IMT_ID_UNIQUE"))
                message = "A General Terms Description '" + GTerms.getErpmGenMaster().getErpmgmEgmId() + "' already exists for the institution.";
            else
                message = "Exception in Save method -> GeneralTermsAxn '" + e.getMessage() + "' Reported Cause is: '" + e.getCause() +"'";
            return ERROR;
        }
    }

public String Browse() throws Exception {
    try {
//        if(GTerms.getErpmGenMaster().getErpmgmEgmDesc()==null)
//            message="Please select ";
        GTermsList = GTermsDao.findAll();
        return SUCCESS;
        } catch (Exception e) {
             message = "Exception in Browse method -> GeneralTermsAxn" + e.getMessage();
             return ERROR;
            }
        }

public String Edit() throws Exception {
        try {
            GTerms = GTermsDao.findBygtGtid(GTGTID); //findBygtGtid(GTerms.getGtGtid());
              termsImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> GeneralTermsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


public String Delete() {
        GTerms = GTermsDao.findBygtGtid(GTGTID);
        GTermsDao.delete(GTerms);
        GTermsList = GTermsDao.findAll();
        return SUCCESS;
        }

public String Clear() throws Exception {
    try {
            GTerms = null;
            termsImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
//            termsImIdList=imDao.findAll();
            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
            return SUCCESS;
            } catch (Exception e) {
                message = "Exception in Clear method -> GeneralTermsAxn " + e.getMessage() + e.getCause();
                return ERROR;
       }

    }

//Added by sajid Aziz
public void validate() {
        try {
            //validation of POMASTER Screen NO-1
             if(GTerms.getGtGtid() == null) {
            if (GTerms.getInstitutionmaster().getImId() == null) {
                addFieldError("GTerms.institutionmaster.imId", "Please select institution from the list");

             termsImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
                 }
             else   if (GTerms.getErpmGenMaster().getErpmgmEgmId()==null){
             addFieldError("GTerms.erpmGenMaster.erpmgmEgmId","Please  select T&C Types");
            
             termsImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            termsTypeList =termsTypeDao.findByErpmGmType(Short.parseShort("12"));
            }
            }
    }
     
     //}
         catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
        }
    }

}