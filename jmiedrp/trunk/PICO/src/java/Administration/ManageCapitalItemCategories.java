/**
 *
 * @author kazim
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.ErpmCapitalCategory;
import pojo.hibernate.ErpmCapitalCategoryDao;


import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author sknaqvi
 */
public class ManageCapitalItemCategories extends DevelopmentSupport {

    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();

    private ErpmCapitalCategory erpmcc;// = new ErpmCapitalCategory();
    private ErpmCapitalCategoryDao erpmccDao = new ErpmCapitalCategoryDao();
    private List<ErpmCapitalCategory> erpmccList = new ArrayList<ErpmCapitalCategory>();

    private String message;
    Integer ErpmccId;
private short IMID;
    public ErpmCapitalCategory geterpmcc() {
        return this.erpmcc;
    }

    public void seterpmcc(ErpmCapitalCategory erpmcc) {
        this.erpmcc = erpmcc;
    }

   public Integer getErpmccId() {
        return this.ErpmccId;
    }

    public void setErpmccId(Integer ErpmccId) {
        this.ErpmccId = ErpmccId;
    }

   public List<Institutionmaster> getimList() {
        return this.imList;
    }

   public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

   public List<ErpmCapitalCategory> geterpmccList() {
        return this.erpmccList;
    }

   public void seterpmccList(List<ErpmCapitalCategory> erpmccList) {
        this.erpmccList = erpmccList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
public void setIMID(short IMID) {
        this.IMID = IMID;
    }

    public short getIMID() {
        return this.IMID;
    }
    @Override
    public String execute() throws Exception {
        try {

            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageCapitalItemCategoriesAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


 public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (erpmcc.getErpmccId() == null) {
                    if (erpmcc.getInstitutionmaster().getImId() == null) {
                        message = "Please select institution";
                     imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
                }
                                
                    else if(erpmcc.getErmccDesc().length() == 0)
                        message = "Please enter Capital Item Category Description";
                    else {
                        erpmccDao.save(erpmcc);
                        erpmcc=erpmccDao.findByErpmccId(erpmcc.getErpmccId());//Modified by sajid 0n 30 june
                        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
                        erpmccList = erpmccDao.findByImId(erpmcc.getInstitutionmaster().getImId());
                        erpmcc=null;
                        message = "Capital Category created successfully for the institution.";
                    }
            } else {
                    ErpmCapitalCategory erpmcc2 = erpmccDao.findByErpmccId(erpmcc.getErpmccId());
                    erpmcc2 = erpmcc;
                    erpmccDao.update(erpmcc2);
                    
                    message = "Capital Category updated successfully.";
                    //Prepare LOV for User Institutions and List of records from ERPM_Capital_Category Table
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            erpmccList = erpmccDao.findByImId(erpmcc.getInstitutionmaster().getImId());
            erpmcc=null;
            }
            return SUCCESS;
     }

          /*  catch (NullPointerException e1) {
            if (e1.getCause().toString().contains("Duplicate entry"))
                    message = "The capital item category ' " + erpmcc.getErmccDesc() + "' already exists for the institution.";
            else
                message = "Exception in Save method -> ManageCapitalItemCategoriesAxn" + e1.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }*/
         catch (Exception e) {
            if (e.getCause().toString().contains("Duplicate entry"))
                    message = "The capital item category ' " + erpmcc.getErmccDesc() + "' already exists for the institution.";
            else
                message = "Exception in Save method -> ManageCapitalItemCategoriesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {

            //Prepare LOV for User Institutions and List of records from ERPM_Capital_Category Table
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Retrieve the record to be edited into erpmcc object
            erpmcc = erpmccDao.findByErpmccId(getErpmccId());

            //Prepare list of Capital Items fot the selected institution
            erpmccList = erpmccDao.findByImId(erpmcc.getInstitutionmaster().getImId());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageCapitalItemCategoriesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {

            //Retrieve the record to be deleted into erpmcc object
            erpmcc = erpmccDao.findByErpmccId(getErpmccId());
            erpmccDao.delete(erpmcc);
            message = "Record successfully deleted";

            //Prepare LOV for User Institutions and List of records from ERPM_Capital_Category Table
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Prepare list of Capital Items fot the selected institution
            erpmccList = erpmccDao.findByImId(erpmcc.getInstitutionmaster().getImId());

            erpmcc = null;

            return SUCCESS;
        } catch (Exception e) {
           if (e.getCause().toString().contains("FK_ERPMIM_Capital_Cat_ERPMCC_ID"))
                   message = "Cannot delete category. Found relared items classified as '" + erpmcc.getErmccDesc() + "'";
           else
                message = "Exception in Delete method -> ManageCapitalItemCategoriesAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String FetchCCEntries() throws Exception {
    try{
        erpmccList = erpmccDao.findByImId(erpmcc.getInstitutionmaster().getImId());
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        return SUCCESS;
    }
    catch (Exception e) {
            message = "Exception in FetchCCEntries method -> ManageCapitalItemCategoriesAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}
public String Export() throws Exception {
    try{
        if(getIMID()==0)
        erpmccList = erpmccDao.findAll();
        imList = imDao.findAll();
        return SUCCESS;
    }
    catch (Exception e) {
            message = "Exception in FetchCCEntries method -> ManageCapitalItemCategoriesAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
}
public String Clear() throws Exception {
        try {
            erpmcc = null;
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear -> ManageCapitalItemCategoriesAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

//added by sajid Aziz on 18 aug
 public void validate()  {
    try {
        //message="hi in validate";
        if(erpmcc.getInstitutionmaster().getImId()==null) {
            addFieldError("erpmcc.institutionmaster.imId","please select Insitute!");
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        }
        if(erpmcc.getErmccDesc().toString()==null) {
           addFieldError("erpmcc.ermccDesc","please item category name");
imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        }
        }
    catch (NullPointerException npe) {
          // message="hi" +npe.getCause();
        }
    }

}
