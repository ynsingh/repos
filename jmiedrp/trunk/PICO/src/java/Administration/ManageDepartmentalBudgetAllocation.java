/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author afreen
 */

package Administration;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Budgetheadmaster;
import pojo.hibernate.BudgetheadmasterDAO;
import utils.DevelopmentSupport;
import pojo.hibernate.DepartmentalBudgetAllocation;
import pojo.hibernate.DepartmentalBudgetAllocationDAO;
import java.util.*;
import utils.DateUtilities;

public class ManageDepartmentalBudgetAllocation extends DevelopmentSupport   {

    private DepartmentalBudgetAllocation  dba;
    private List<DepartmentalBudgetAllocation > dbaList = new ArrayList<DepartmentalBudgetAllocation >();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private List<Subinstitutionmaster> simImList = new ArrayList<Subinstitutionmaster>();
    private List<Budgetheadmaster> bhmList = new ArrayList<Budgetheadmaster>();
    private BudgetheadmasterDAO bhmDao = new BudgetheadmasterDAO();
    private DepartmentalBudgetAllocationDAO dbaDao = new DepartmentalBudgetAllocationDAO();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private String message;
    private  Integer DBAID;
    private String fromdate;
    private String todate;



    public void setDBAID(Integer DBAID) {
        this.DBAID = DBAID;
    }
    public Integer getDBAID() {
        return this.DBAID;
    }
    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }
 
    public void setimList(List<Institutionmaster> imList) {
         this.imList = imList;
    }
    public List<Institutionmaster> getimList() {
        return this.imList;
    }
    public void setsimImList(List<Subinstitutionmaster> simImList) {
        this.simImList = simImList;
    }
    public List<Subinstitutionmaster> getsimImList() {
        return this.simImList;
    }
    public void setdba(DepartmentalBudgetAllocation dba) {
        this.dba = dba;
    }
    public DepartmentalBudgetAllocation getdba() {
        return this.dba;
    }
    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }
    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }
    public void setdbaList(List<DepartmentalBudgetAllocation> dbaList) {
        this. dbaList =  dbaList;
    }
    public List<DepartmentalBudgetAllocation> getdbaList() {
        return this. dbaList;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public void setbhmList(List<Budgetheadmaster>  bhmList) {
        this.bhmList =  bhmList;
    }
    public List<Budgetheadmaster> getbhmList() {
        return this.bhmList;
    }
    public void InitializeLOVs() {
        //Prepare LOV containing User Institutions
         imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
         //Prepare LOV containing Budget Head
         bhmList=bhmDao.findAll();
      }
      @Override
    public String execute() throws Exception {
        try {
           InitializeLOVs();
           return SUCCESS;
        } catch (Exception e) {
//           message = "Exception in -> DepartmentalBudgetAllocationAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
           return ERROR;
        }
    }

    
     public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (dba.getDbaId() == null) {
               if(dba.getInstitutionmaster().getImId()==null)
                message = "Please select institution";
                else if(dba.getSubinstitutionmaster().getSimId()==null)
                message = "Please select College/Faculty/School ";
                else if(dba.getDepartmentmaster().getDmId()==null)
                message = "Please select Department ";
                else if(dba.getBudgetheadmaster().getBhmId()==null)
                message = "Please select Budget Head ";
                else if(dba.getDbaAmount().toString()==null)
                message = "Please enter Amount ";
                else if(fromdate==null)
                message  = "Please enter From Date ";
                else if(todate==null)
                message = "Please enter To Date ";

             else{     
                      DateUtilities dt = new DateUtilities();
                       dba.setDbaFromDate(dt.convertStringToDate(getFromdate()));
                    dba.setDbaToDate(dt.convertStringToDate(getTodate()));
            //setInvoicerecvDate(d1.convertDateToString(dbafromdate, "dd-MM-yyyy"));
            //setSuplierinvoiceDate(d1.convertDateToString(dbatodate, "dd-MM-yyyy"));
               dbaDao.save(dba);
               message = "Department record saved successfully. Departmenyt Id is " + dba.getDbaId();
               dbaList = dbaDao.findAll();
               }
               InitializeLOVs();

               dba=null;

              } else {
                DepartmentalBudgetAllocation dba1 = dbaDao.findByDbaId(dba.getDbaId());
                DateUtilities dt = new DateUtilities();
                dba.setDbaFromDate(dt.convertStringToDate(getFromdate()));
                dba.setDbaToDate(dt.convertStringToDate(getTodate()));
                
                dba1 = dba;
                dbaDao.update(dba1);
                message = "Departmental Budget Allocation  record updated successfully.";
                InitializeLOVs();
                dba=null;
               }
               dbaList = dbaDao.findAll();
               return SUCCESS;
             }
        catch (NullPointerException e1) {
       // if (e.getCause().toString().contains("java.lang.NullPointerException"))
        //message = "The Po terms type ' " + epoterms.getErpmGenMaster().getErpmgmEgmDesc() + "' already exists for Your Purchase Order.";
       //else
        message = " Please Give Amount Sanctioned";
        return ERROR;
        }

        catch (Exception e) {
            InitializeLOVs();
              if (e.getCause().toString().contains("UNIQUE_DBA_DM_ID_DBA_From_Date_DBA_To_Date_DBA_BHM_ID"))
             // message = "The budget head '" +dba.getBudgetheadmaster().getBhmName() + "' already exists for the selected institution ";
              message = "The budget head already exists for the selected institution ";
              else
              message = "Exception in Save method -> DepartmentalBudgetAllocationAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
              return ERROR;

        }


    }

    public String Edit() throws Exception {
        try {
            InitializeLOVs();
             //Retrieve the record to be updated into dba object
            dba = dbaDao.findByDbaId(getDBAID());
            simImList=simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), dba.getInstitutionmaster().getImId());
            dmList=dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), dba.getSubinstitutionmaster().getSimId());
            
            DateUtilities dt = new DateUtilities();

            fromdate=dt.convertDateToString(dba.getDbaFromDate(),"dd-MM-yyyy");
            todate=dt.convertDateToString(dba.getDbaToDate(),"dd-MM-yyyy");

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> DepartmentalBudgetAllocationAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
             //Retrieve the record to be deleted into dba object
            dba = dbaDao.findByDbaId(getDBAID());
            dbaDao.delete(dba);
            dbaList = dbaDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {     
            message = "Exception in Delete method -> DepartmentalBudgetAllocationAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Clear() throws Exception {
        try {
            //Clear form field
            dba = null;
            InitializeLOVs();
//            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DepartmentalBudgetAllocationAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    public String Fetch() throws Exception {

         try {
            //Prepare LOV containing Budget Head for the selected institution
         //    message="hellooo"+dba.getInstitutionmaster().getImName();
             if(dba.getInstitutionmaster().getImId()!=null)
            //message = "Please select institution";
          // dbaList=dbaDao.findAll();
               if( dba.getSubinstitutionmaster().getSimId()!=null )
            //Prepare LOV containing User Institutions{
                   {
             if(dba.getDepartmentmaster().getDmId()!=null)
                dbaList=dbaDao.findByDMId(dba.getDepartmentmaster().getDmId());
                          else
             dbaList =dbaDao.findBySimId(dba.getSubinstitutionmaster().getSimId());
                   }

             else
            {
        if (dba.getDepartmentmaster().getDmId() != null)
              dbaList=dbaDao.findByDMId(dba.getDepartmentmaster().getDmId());
        else
        {
           // message="hellooo";
             dbaList =dbaDao.findByImId(dba.getInstitutionmaster().getImId());
        }
        }
                  //imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            else
             {
            //dbaList=dbaDao.findAll();}
                 //Changes by sajid Aziz on 7 july 2011
                 dbaList=dbaDao.findByDMId(dba.getDepartmentmaster().getDmId());}
            InitializeLOVs();
                return SUCCESS;
            }
        catch (Exception e)
            {
            message = "Exception in Delete method -> DepartmentalBudgetAllocationAxn" + e.getMessage()+ " Reported Cause is: " + e.getCause();
            return ERROR;
            }




    }
 public String Export(){
        try {
            
             //if(dba.getInstitutionmaster().getImId()!=null)
             message = "Please select institution";
             //dbaList =dbaDao.findByImId(dba.getInstitutionmaster().getImId());
             dbaList=dbaDao.findAll();
             return SUCCESS;
            }
             catch (Exception e)
            {
            message = "Exception in export method -> DepartmentalBudgetAllocationAxn" + e.getMessage()+ " Reported Cause is: " + e.getCause();
            return ERROR;
            }

    }


}









































