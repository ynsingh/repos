/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

/**
 *
 * @author afreen
 */
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;
import pojo.hibernate.SupplierAddress;
import pojo.hibernate.SupplierAddressDAO;

import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class ManageSupplierAddress extends DevelopmentSupport {

    private SupplierAddress erpmsmad;
    private Suppliermaster erpmsm;
    private SuppliermasterDAO erpmsmDao = new SuppliermasterDAO();
    private StatemasterDAO smDao = new StatemasterDAO();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private SupplierAddressDAO adDao = new SupplierAddressDAO();
    private List<SupplierAddress> adList = new ArrayList<SupplierAddress>();
    private List<Suppliermaster> erpmsmList = new ArrayList<Suppliermaster>();
    private List<Statemaster> stList = new ArrayList<Statemaster>();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private Byte defaultCountry;
    private Integer ADID;
    private String message;
    private Integer SMID;

    public void seterpmsm(Suppliermaster erpmsm) {
        this.erpmsm = erpmsm;
    }

    public Suppliermaster geterpmsm() {
        return this.erpmsm;
    }

    public void setSMID(Integer SMID) {
        this.SMID = SMID;
    }

    public Integer getSMID() {
        return this.SMID;
    }

    public void setadList(List<SupplierAddress> adList) {
        this.adList = adList;
    }

    public List<SupplierAddress> getadList() {
        return this.adList;
    }

    public void seterpmsmList(List<Suppliermaster> erpmsmList) {
        this.erpmsmList = erpmsmList;
    }

    public List<Suppliermaster> geterpmsmList() {
        return this.erpmsmList;
    }

    public void setstList(List<Statemaster> stList) {
        this.stList = stList;
    }

    public List<Statemaster> getstList() {
        return this.stList;
    }

    public void setctList(List<Countrymaster> ctList) {
        this.ctList = ctList;
    }

    public List<Countrymaster> getctList() {
        return this.ctList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void seterpmsmad(SupplierAddress erpmsmad) {
        this.erpmsmad = erpmsmad;
    }

    public SupplierAddress geterpmsmad() {
        return this.erpmsmad;
    }

    public void setADID(Integer ADID) {
        this.ADID = ADID;
    }

    public Integer getADID() {
        return this.ADID;
    }

    public void setdefaultCountry(Byte defaultCountry) {
        this.defaultCountry = defaultCountry;
    }

    public Byte getdefaultCountry() {
        return this.defaultCountry;
    }

    @Override
    @SkipValidation
    //Provides an interface for add, edit, delete of supplier address enteries
    public String execute() throws Exception {
        try {

            //Prepare LOV containing Countries
            ctList = cmDao.findAll();
            //Set default Country (Need to be setup as same as that of Institution at some stage
            defaultCountry = cmDao.findCountry("India");
            //Prepare LOV containing States of India
            stList = smDao.findByCountryName("India");
            message = "Supplier Id : " + getSMID() + "  Supplier Name : " + erpmsmDao.findByErpmSMId(getSMID()).getSmName();
            //Prepare Supplier Address List
            adList = adDao.findBySupplierId(getSMID());
            //Store supplier if for keepingtrack of supplier addresses beeing added, deleted or edited
            SMID = getSMID();

            erpmsmad = null;
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ManageSupplierAddressAxn. Reported Cause is " + e.getCause();
            return ERROR;
        }
    }



    public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (getADID() == null) {
                erpmsm = erpmsmDao.findByErpmSMId(getSMID());
                erpmsmad.setSuppliermaster(erpmsm);
                adDao.save(erpmsmad);
                message = "Address record added successfully for the Supplier '" + erpmsmDao.findByErpmSMId(getSMID()).getSmName() + "'";
            } else {
                //Retrieve Address record and update it
                SupplierAddress erpmsmad1 = adDao.findByErpmADId(getADID());
                erpmsmad1 = erpmsmad;                
                adDao.update(erpmsmad1);
                message = "Address updated successfully for the Supplier '" + erpmsmDao.findByErpmSMId(getSMID()).getSmName() + "'";
                //Store Supplier ID
                SMID = erpmsmad1.getSuppliermaster().getSmId();
                ADID = null;
            }
                erpmsmad = null;
               ctList = cmDao.findAll();
               //Set default Country (Need to be setup as same as that of Institution at some stage
               defaultCountry = cmDao.findCountry("India");
               stList = smDao.findByCountryName("India");
               adList = adDao.findBySupplierId(getSMID());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> ManageSupplierAddressAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }

    public String Delete() throws Exception {
        try {

            erpmsmad = adDao.findByErpmADId(getADID());
            SMID = erpmsmad.getSuppliermaster().getSmId();
            adDao.delete(erpmsmad);            

            erpmsmad = null;
            ctList = cmDao.findAll();
            //Set default Country (Need to be setup as same as that of Institution at some stage
            defaultCountry = cmDao.findCountry("India");
            stList = smDao.findByCountryName("India");
            adList = adDao.findBySupplierId(getSMID());
            message = "Supplier Id : " + getSMID() + "  Supplier Name : " + erpmsmDao.findByErpmSMId(getSMID()).getSmName();
            setADID(null) ;
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> ManageSupplierAddressAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {
            //Prepare LOV containing Countries
            ctList = cmDao.findAll();
            //Retrieve Address to be edited
            erpmsmad = adDao.findByErpmADId(getADID());
            //Set country as that contained in the record (Need to be setup as same as that of Institution at some stage
            defaultCountry = erpmsmad.getCountrymaster().getCountryId();
            //Prepare LOV containing States for the country set in the record 
            stList = smDao.findByCountryId(defaultCountry);
            
            //Save Supplier ID and Address ID for Future reference
            SMID = erpmsmad.getSuppliermaster().getSmId();
            message = "Supplier Id : " + getSMID() + "  Supplier Name : " + erpmsmDao.findByErpmSMId(getSMID()).getSmName();
            //Prepare Supplier Address List
            adList = adDao.findBySupplierId(SMID);            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageSupplierAddressAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    public void validate() {
        try {

            //Validations on Address part
            if (erpmsmad.getAdLine1().length() == 0) {
                addFieldError("erpmsmad.adLine1", "Please enter primary contact address of supplier");
            }
            if (erpmsmad.getCountrymaster().getCountryId() == null) {
                addFieldError("erpmsmad.countrymaster.countryId", "Please select country from the list");
            }
            if (erpmsmad.getAdCity().length() == 0) {
                addFieldError("erpmsmad.adCity", "Please enter city");
            }
            if (erpmsmad.getStatemaster().getStateId() == null) {
                erpmsmad.setStatemaster(null);
            }
            message = erpmsmad.getAdEmail().getClass().getCanonicalName();

            //Initialize LOVs

            //Prepare LOV containing Countries
            ctList = cmDao.findAll();
            //Set default Country (Need to be setup as same as that of Institution at some stage
            defaultCountry = cmDao.findCountry("India");
            //Prepare LOV containing States
            stList = smDao.findByCountryName("India");

            //message = "In Validate";

        } catch (NullPointerException npe) {
        }
    }
}
