
/**
 *
 * @author Afreen
 */

package Administration;

import pojo.hibernate.Budgetheadmaster;
import pojo.hibernate.BudgetheadmasterDAO;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.PaginationHibDao;
import utils.DevelopmentSupport;
import java.util.ArrayList;
import pojo.hibernate.CheckDuplicateDAO;
import java.util.List;
import java.io.*;
import javax.servlet.http.HttpServletRequest;

public class ManageBudgetHeadAction extends DevelopmentSupport  {

    private Budgetheadmaster bhm = new Budgetheadmaster();
    private BudgetheadmasterDAO bhmDao = new BudgetheadmasterDAO();
    private List<Budgetheadmaster> bhmList = new ArrayList<Budgetheadmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
   
    private CheckDuplicateDAO cdDao = new CheckDuplicateDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private String message;
    private short BhmId;
    private short GenType;
    private String bhmName;
    private File Record;
    private String recordContentType;
    private String recordFileName;

   //---------------file upload--------------
    public File getRecord() {
      return Record;
     }

    public void setRecord(File Record) {
    this.Record = Record;
     }
    public String getRecordContentType() {
    return recordContentType;
    }
    public void setrecordContentType(String recordContentType) {
    this.recordContentType = recordContentType;
    }
    public String getRecordFileName() {
    return recordFileName;
    }
    public void setRecordFileName(String recordFileName) {
    this.recordFileName = recordFileName;
    }
   //end

    public void setbhm(Budgetheadmaster bhm) {
            this.bhm = bhm;
     }
    public Budgetheadmaster getbhm() {
            return bhm;
    }
    public void setBhmId(short BhmId) {
            this.BhmId = BhmId;
    }
    public short getBhmId() {
            return BhmId;
    }
    public void setGenType(short GenType) {
            this.GenType= GenType;
    }
    public short getGenType() {
            return GenType;
    }
    public List<Budgetheadmaster> getbhmList() {
       return bhmList;
    }
    public void setbhmList(List<Budgetheadmaster> bhmList) {
        this.bhmList = bhmList;
    }
   public List<Institutionmaster> getimList() {
        return this.imList;
    }
   public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
    public void setbhmName(String bhmName) {
        this.bhmName = bhmName;
    }

    public String getbhmName() {
        return this.bhmName;
    }

  /*public String insertupdate() throws Exception{
      int k=0;
      try{
      FileReader fr=null;
      BufferedReader br=null;
      String s = "Start here ";
      message="hii"  ;
      if(Record != null)
      {
      fr= new FileReader(Record.toString());
      br=new BufferedReader(fr);
      while ( s!=null)
       {
       s=br.readLine();
       if(s!=null)
       {
         int length  =s.length();
         int i=0;
          while(i<s.length())
             {
               String rec="";
               while (length > 0&& s.charAt(i) != ','   )
                    {
                    rec=rec+ s.charAt(i) ;
                    i++;
                    length--;
                    }
               message=rec;
              Institutionmaster im=imDao.findByImId(Short.parseShort(rec));
              k++;
              bhm.setInstitutionmaster(im);
              i++;
              length--;
              rec="";
              while (length > 0)
                    {
                    rec=rec+ s.charAt(i) ;
                    i++;
                    length--;
                    }
                 message=rec;
                 bhm.setBhmName(rec);
                 bhmDao.save(bhm);
                 }
                 }
                }
          }
      
        return SUCCESS;
     }
       catch (Exception e)
          {
              if (e.getCause().toString().contains("ConstraintViolationException"))
              {
             
                 // k++;
                  message = " institution does not exist at line no."+""+k;
              }
                  else
              message = "Exception in Save method -> ManageBudgetHeadAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
              return ERROR;
       }
}*/


public String insertupdate() throws Exception{
    Fetch();
    
int line=0;
 String rec="";
            String rec1="";
try{

      FileReader fr=null;

      BufferedReader br=null;
      String s = "Start here ";
      //message="hii"  ;

      if(Record != null)
  {
       //String res= checkDuplicate("Budgetheadmaster",Record.toString());
       String res= cdDao.checkDuplicate(Record.toString());
          if(!(res.equals("saveSuccessfully"))){
           message="duplicate record found at line"+res;
             return SUCCESS;
        }

          else
        {
           message="insert";
            fr = new FileReader(Record.toString());
            //din=new DataInputStream(iStreamRecord);
            br=new BufferedReader(fr);
            //int i = 0;
           // s=br.readLine();

            while ( s!=null)
               {
                
            s=br.readLine();

            if(s!=null)
            {
            int length  =s.length();
              int i=0;
               while(i<s.length())
                {
                
                    while (length > 0&& s.charAt(i) != ','   )
                    {
                    rec1=rec1+ s.charAt(i) ;
                    i++;
                    length--;
                    }
               //  message=rec;
           Institutionmaster im=imDao.findInstByIMFullName(rec1);
           line++;
           bhm.setInstitutionmaster(im);
           i++;
           length--;
          rec="";
           while (length > 0   )
                    {
                    rec=rec+ s.charAt(i) ;
                    i++;
                    length--;
                    }
                 //message=rec;

                 bhm.setBhmName(rec);
                 bhmDao.save(bhm);

                }

                 }

                   }

 }

       
          }
      return SUCCESS;
      }
catch (Exception e)
          {

              if (e.getCause().toString().contains("ConstraintViolationException"))
             
               message = " institution does not exist at line no."+""+line;
                 if(e.getCause().toString().contains("UNIQUE_BHM_IM_Id_BHM_Name"))
               message = "Record already exist in data base. for budget head"+" "+rec+" and institution"+" "+rec1 ;
                 else
               message = "Exception in Save method -> ManageBudgetHeadAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
               return ERROR;
       }
}


    public void InitializeLOVs() {
        //Prepare LOV containing User Institutions
       imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
       //Prepare List containing Budget Head
       //bhmList = bhmDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
       return;
    }

    @Override

     public String execute() throws Exception {
        try {
            InitializeLOVs();
          return SUCCESS;
            } catch (Exception e) {
            message = "Exception in -> ManageBudgetHeadAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //Modified by S.Aziz
 public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (bhm.getBhmId()== null)
            {
                if(bhm.getInstitutionmaster().getImId()==null)
                 message = "Please select institution";
                 else if(bhm.getBhmName().length()==0)
                 message = "Please enter Budget Head Name";
                 else
                {
                bhmDao.save(bhm);
                message = "Budget Head created successfully for the institution.";
                }
                InitializeLOVs();
                bhm=null;
            }
            else
            {
              Budgetheadmaster bhm1 =bhmDao.findBybhmId(bhm.getBhmId());
              bhm1=bhm;
              bhmDao.update(bhm1);
              InitializeLOVs();
              message = "Budget Head updated successfully.";
                       //bhmList=bhmDao.findAll();
            }
              return SUCCESS;
            }
       catch (Exception e)
          {
              InitializeLOVs();
              if (e.getCause().toString().contains("Duplicate entry"))
              message = "The budget head '" + bhm.getBhmName() + "' already exists for the selected institution";
              else
              message = "Exception in Save method -> ManageBudgetHeadAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
              return ERROR;
       }

}

 public String Fetch(){
        try {      
           InitializeLOVs();
            //Prepare LOV containing Budget Head for the selected institution
            if(bhm.getInstitutionmaster().getImId()==null)
            message = "Please select institution";
            bhmList=bhmDao.findByImId(bhm.getInstitutionmaster().getImId());
            //Prepare LOV containing User Institutions
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
            }
        catch (Exception e)
            {
            message = "Exception in Delete method -> ManageBudgetHeadAxn" + e.getMessage()+ " Reported Cause is: " + e.getCause();
            return ERROR;
            }

    }

 public String Export(){
        try {
            //Prepare LOV containing Budget Head for the selected institution
            //if(bhm.getInstitutionmaster().getImId()==null)
                if(getGenType()==0)
            message = "Please select institution";
            bhmList=bhmDao.findAll();
            //Prepare LOV containing User Institutions
            //imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
            }
        catch (Exception e)
            {
            message = "Exception in Delete method -> ManageBudgetHeadAxn" + e.getMessage()+ " Reported Cause is: " + e.getCause();
            return ERROR;
            }

    }

 public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted into bhm object
            bhm=bhmDao.findBybhmId(getBhmId());
            bhmDao.delete(bhm);
            //Prepare LOV containing User Institutions
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
             //Prepare LOV containing Budget Head for the selected institution
            bhmList=bhmDao.findByImId(bhm.getInstitutionmaster().getImId());
            message = "Record successfully deleted";
            bhm= null;
            return SUCCESS;
            } catch (Exception e) {
            message = "Exception in Delete method -> ManageBudgetHeadAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

  public String Edit() throws Exception {
              try {
                  //Retrieve the record to be updated into bhm object
                   bhm=bhmDao.findBybhmId(getBhmId());
                   //Prepare LOV containing User Institutions
                   imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
                   bhmList=bhmDao.findAll();
                   return SUCCESS;
                   } catch (Exception e) {
                   message = "Exception in EdiT method -> ManageBudgetHeadAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
                   return ERROR;
                  }
                  }




public String Clear() throws Exception {
        try {
            bhm = null;
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        }   catch (Exception e) {
            message = "Exception in Clear -> ManageBudgetHeadAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

// @SkipValidation
   
}


