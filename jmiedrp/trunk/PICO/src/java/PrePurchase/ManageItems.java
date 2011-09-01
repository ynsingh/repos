/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;
import com.opensymphony.xwork2.validator.ValidationException;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import pojo.hibernate.ErpmCapitalCategory;
import pojo.hibernate.ErpmCapitalCategoryDao;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kazim
 */
public class ManageItems extends DevelopmentSupport {

    private ErpmItemMaster erpmim;
    private ErpmItemMasterDAO erpmimDao = new ErpmItemMasterDAO();
    private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
    private ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
    private ErpmCapitalCategoryDao erpmccDao = new ErpmCapitalCategoryDao();
    private List<ErpmItemMaster> erpmimList = new ArrayList<ErpmItemMaster>();
    private List<ErpmGenMaster> erpmGmIdList = new ArrayList<ErpmGenMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmCapitalCategory> erpmCapitalCategoryList = new ArrayList<ErpmCapitalCategory>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private String message;
    private Short i = 1;
    private Integer ErpmimId;


    //public ManageItems() {
   // }
    
    
    public void seterpmim(ErpmItemMaster erpmim) {
        this.erpmim = erpmim;
    }

    public ErpmItemMaster geterpmim() {
        return this.erpmim;
    }

    public List<ErpmGenMaster> geterpmGmIdList() {
        return this.erpmGmIdList;
    }

    public void seterpmGmIdList(List<ErpmGenMaster> erpmGmIdList) {
        this.erpmGmIdList = erpmGmIdList;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList1() {
        return this.erpmIcmList1;
    }

    public void seterpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList2() {
        return this.erpmIcmList2;
    }

    public void seterpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList3() {
        return this.erpmIcmList3;
    }

    public void seterpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    public List<ErpmCapitalCategory> geterpmCapitalCategoryList() {
        return this.erpmCapitalCategoryList;
    }

    public void seterpmCapitalCategoryList(List<ErpmCapitalCategory> erpmCapitalCategoryList) {
        this.erpmCapitalCategoryList = erpmCapitalCategoryList;
    }

    public List<ErpmItemMaster> geterpmimList() {
        return this.erpmimList;
    }

    public void seterpmimList(List<ErpmItemMaster> erpmimList) {
        this.erpmimList = erpmimList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public Integer getErpmimId() {
        return this.ErpmimId;
    }

    public void setErpmimId(Integer ErpmimId) {
        this.ErpmimId = ErpmimId;
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

            //Initialize LOVs
            InitializeLOVs();

            //Set the object as erpmim as Null
            erpmim = null;

            return SUCCESS;
        } catch (Exception e) {
           message = "Exception in -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Browse() throws Exception {
        try {
            //Retrieve Items which belong to the institutes in the perview of user
            erpmimList = erpmimDao.findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
            //If part saves new record else parts is for record update

            if (erpmim.getErpmimId() == null) {
              
                //Check if dupliacte item exist(s)
                if (erpmimDao.hasDuplicate(erpmim.getErpmimItemBriefDesc(), erpmim.getInstitutionmaster().getImId())) {
                    message = "An item with the given brief description already exist(s)";
                    InitializeLOVs();
                    return INPUT;
                } else {
                    erpmimDao.save(erpmim);
                    message = "Item record created. Item id is : " + erpmim.getErpmimId();
                }

            } else {
                ErpmItemMaster erpmim2 = erpmimDao.findByErpmimId(erpmim.getErpmimId());
                erpmim2 = erpmim;
                erpmimDao.update(erpmim2);
                message = "Item Record updated successfully.";
            }
            erpmimList = erpmimDao.findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {

            erpmim = erpmimDao.findByErpmimId(getErpmimId());
            
            //Populate LOV for Unit of Purchase
            erpmGmIdList = erpmGmDao.findByErpmGmType(Short.parseShort("3".toString()));

            //Prepare LOV for Ite Type
            erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

            //Prepare LOV for Capital categories
            erpmCapitalCategoryList = erpmccDao.findByImId(erpmim.getInstitutionmaster().getImId());

            //Prepare Item Category List
            erpmIcmList2 = erpmIcmDao.findByerpmItemCategoryMaster(erpmim.getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId());

            //Prepare Item Sub Category List
            erpmIcmList3 = erpmIcmDao.findByerpmItemCategoryMaster(erpmim.getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId());
            
            //Prepare User Institution LOV
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted
            erpmim = erpmimDao.findByErpmimId(getErpmimId());
            erpmimDao.delete(erpmim);

            //Retrieve Items which belong to the institutes in the perview of user
            erpmimList = erpmimDao.findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
            message = "Item Record deleted successfully.";
            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    private void InitializeLOVs() {
        //Prepare LOV for Unit of Purchase
        erpmGmIdList = erpmGmDao.findByErpmGmType(Short.parseShort("3".toString()));
        //Prepare LOV for Item Types
        erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);
        //Prepare LOV for User's institutions
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //Prepare LOV for the user institution
        erpmCapitalCategoryList = erpmccDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        
        return;
    }

public void validate() {
          try{


                if (erpmim.getInstitutionmaster().getImId() == null)
                    addFieldError("erpmim.institutionmaster.imId", "Please select institution from the list");
                if (erpmim.getErpmimItemBriefDesc().length() == 0)
                  addFieldError("erpmim.erpmimItemBriefDesc", "Please enter item's brief description");
                if(erpmim.getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId() == null)
                    addFieldError("erpmim.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId", "Please select Item Type from the list");
                if(erpmim.getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId() == null)
                    addFieldError("erpmim.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId", "Please select Item category from the list");
                if(erpmim.getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId() == null)
                    addFieldError("erpmim.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId", "Please select Item sub category from the list");
                if(erpmim.getErpmGenMaster().getErpmgmEgmId() == null)
                    addFieldError("erpmim.erpmGenMaster.erpmgmEgmId", "Please select Unit of purchase from the list");
                if (erpmim.getErpmimDetailedDesc().length() == 0)
                  addFieldError("erpmim.erpmimDetailedDesc", "Please enter item's detailed description");

                if (erpmim.getErpmCapitalCategory().getErpmccId() == null)
                    erpmim.setErpmCapitalCategory(null);
                //Initialize LOVs
                InitializeLOVs();

            
    }
    catch(NullPointerException npe){};
}
  
}
