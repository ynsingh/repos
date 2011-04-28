/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
//import  com.myapp.struts.hbm.*;

import  com.myapp.struts.hbm.Privilege;
import  com.myapp.struts.hbm.PrivilegeId;
import  com.myapp.struts.hbm.Library;
import  com.myapp.struts.hbm.SerPrivilege;
import  com.myapp.struts.hbm.CirPrivilegeId;
import  com.myapp.struts.hbm.CirPrivilege;
import  com.myapp.struts.hbm.CatPrivilege;
import  com.myapp.struts.hbm.CatPrivilegeId;
import  com.myapp.struts.hbm.AcqPrivilege;
import  com.myapp.struts.hbm.AcqPrivilegeId;
import  com.myapp.struts.hbm.Login;
import  com.myapp.struts.hbm.LoginId;
import  com.myapp.struts.hbm.StaffDetail;
import  com.myapp.struts.hbm.StaffDetailId;
import  com.myapp.struts.hbm.SubLibrary;
import  com.myapp.struts.hbm.SubLibraryId;
import  com.myapp.struts.hbm.Library;
import  com.myapp.struts.hbm.AdminRegistration;
import  com.myapp.struts.hbm.SerPrivilegeId;
import  com.myapp.struts.AdminDAO.*;

import java.sql.*;

/**
 *
 * @author Dushyant
 */
public class CreatePrivilege {
    static public boolean result=true;
    static Connection con;
  static  String  sql;
   static PreparedStatement stmt;
  
  public static boolean   assignAdminPrivilege(String staff_id,String library_id,String sublibrary_id)
  {

       StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);

   /* Use to Insert New Entry in Privilege related to Staff Detail Table */
           PrivilegeId privid=new PrivilegeId(staff_id, library_id);
           Privilege priv=new Privilege(privid, staffobj);
           priv.setSublibraryId(sublibrary_id);
           priv.setAcquisition("false");
           priv.setAdministrator("false");
           priv.setCataloguing("false");
           priv.setOpac("false");
           priv.setSerial("false");
           priv.setSystemSetup("false");
           priv.setUtilities("false");
           priv.setCirculation("false");

           result=PrivilegeDAO.insert(priv);

            if(result==false)
                {
                  return result;

                }
/* Use to Insert New Entry in AcqPrivilege related to Staff Detail Table */
                AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                AcqPrivilege acq=new AcqPrivilege(acqid, staffobj, sublibrary_id);

                acq.setAcq101("false");
                acq.setAcq102("false");
                acq.setAcq103("false");
                acq.setAcq104("false");
                acq.setAcq105("false");
                acq.setAcq106("false");
                acq.setAcq107("false");
                acq.setAcq108("false");
                acq.setAcq109("false");
                acq.setAcq110("false");
                acq.setAcq111("false");
                acq.setAcq112("false");
                acq.setAcq113("false");
                acq.setAcq114("false");
                acq.setAcq115("false");
                acq.setAcq116("false");
                acq.setAcq117("false");
                acq.setAcq118("false");
                acq.setAcq119("false");
                acq.setAcq120("false");
                acq.setAcq121("false");
                acq.setAcq122("false");
                acq.setAcq123("false");
                acq.setAcq124("false");
                acq.setAcq125("false");
                acq.setAcq126("false");
                acq.setAcq127("false");
                acq.setAcq128("false");
                acq.setAcq129("false");
                acq.setAcq130("false");
                acq.setAcq131("false");
                acq.setAcq132("false");
                acq.setAcq133("false");
                acq.setAcq134("false");
                acq.setAcq135("false");
                acq.setAcq136("false");
                acq.setAcq137("false");
                acq.setAcq138("false");
                acq.setAcq139("false");
                acq.setAcq140("false");
                acq.setAcq141("false");
                acq.setAcq142("false");
                acq.setAcq143("false");
                acq.setAcq144("false");
                acq.setAcq145("false");
                acq.setAcq146("false");
                acq.setAcq147("false");
                acq.setAcq148("false");
                acq.setAcq149("false");
                acq.setAcq150("false");
                acq.setAcq151("false");
                acq.setAcq152("false");
                acq.setAcq153("false");
                acq.setAcq154("false");
                acq.setAcq155("false");
                acq.setAcq156("false");
                acq.setAcq157("false");
                acq.setAcq158("false");
                acq.setAcq159("false");
                acq.setAcq160("false");
                acq.setAcq161("false");
                acq.setAcq162("false");
                acq.setAcq163("false");
                acq.setAcq164("false");
                acq.setAcq165("false");
                acq.setAcq166("false");
                acq.setAcq167("false");
                acq.setAcq168("false");
                acq.setAcq169("false");
                acq.setAcq170("false");
                acq.setAcq171("false");
                acq.setAcq172("false");
                acq.setAcq173("false");
                acq.setAcq174("false");
                acq.setAcq175("false");
                acq.setAcq176("false");
                acq.setAcq177("false");
                acq.setAcq178("false");
                acq.setAcq179("false");
                acq.setAcq180("false");
                acq.setAcq181("false");
                acq.setAcq182("false");
                acq.setAcq183("false");
                acq.setAcq184("false");
                acq.setAcq185("false");
                acq.setAcq186("false");
                acq.setAcq187("false");
                acq.setAcq188("false");
                acq.setAcq189("false");
                acq.setAcq190("false");
                acq.setAcq191("false");
                acq.setAcq192("false");
                acq.setAcq193("false");
                acq.setAcq194("false");
                acq.setAcq195("false");
                acq.setAcq196("false");
                acq.setAcq197("false");
                acq.setAcq198("false");
                acq.setAcq199("false");

                result=AcqPrivilegeDAO.insert(acq);

            if(result==false)
                {
                     return false;

                }
/* Use to Insert New Entry in CatPrivilege related to Staff Detail Table */

             CatPrivilegeId catpriId=new CatPrivilegeId(staff_id, library_id);
             CatPrivilege Cat=new CatPrivilege(catpriId, staffobj, sublibrary_id);

            Cat.setCat201("false");
            Cat.setCat202("false");
            Cat.setCat203("false");
            Cat.setCat204("false");
            Cat.setCat205("false");
            Cat.setCat206("false");
            Cat.setCat207("false");
            Cat.setCat208("false");
            Cat.setCat209("false");
            Cat.setCat210("false");
            Cat.setCat211("false");
            Cat.setCat212("false");
            Cat.setCat213("false");
            Cat.setCat214("false");
            Cat.setCat215("false");
            Cat.setCat216("false");
            Cat.setCat217("false");
            Cat.setCat218("false");
            Cat.setCat219("false");
            Cat.setCat220("false");
            Cat.setCat221("false");
            Cat.setCat222("false");
            Cat.setCat223("false");
            Cat.setCat224("false");
            Cat.setCat225("false");
            Cat.setCat226("false");
            Cat.setCat227("false");
            Cat.setCat228("false");
            Cat.setCat229("false");
            Cat.setCat230("false");
            Cat.setCat231("false");
            Cat.setCat232("false");
            Cat.setCat233("false");
            Cat.setCat234("false");
            Cat.setCat235("false");
            Cat.setCat236("false");
            Cat.setCat237("false");
            Cat.setCat238("false");
            Cat.setCat239("false");
            Cat.setCat240("false");
            Cat.setCat241("false");
            Cat.setCat242("false");
            Cat.setCat243("false");
            Cat.setCat244("false");
            Cat.setCat245("false");
            Cat.setCat246("false");
            Cat.setCat247("false");
            Cat.setCat248("false");
            Cat.setCat249("false");
            Cat.setCat250("false");
            Cat.setCat251("false");
            Cat.setCat252("false");
            Cat.setCat253("false");
            Cat.setCat254("false");
            Cat.setCat255("false");
            Cat.setCat256("false");
            Cat.setCat257("false");
            Cat.setCat258("false");
            Cat.setCat259("false");
            Cat.setCat260("false");
            Cat.setCat261("false");
            Cat.setCat262("false");
            Cat.setCat263("false");
            Cat.setCat264("false");
            Cat.setCat265("false");
            Cat.setCat266("false");
            Cat.setCat267("false");
            Cat.setCat268("false");
            Cat.setCat269("false");
            Cat.setCat270("false");
            Cat.setCat271("false");
            Cat.setCat272("false");
            Cat.setCat273("false");
            Cat.setCat274("false");
            Cat.setCat275("false");
            Cat.setCat276("false");
            Cat.setCat277("false");
            Cat.setCat278("false");
            Cat.setCat279("false");
            Cat.setCat280("false");
            Cat.setCat281("false");
            Cat.setCat282("false");
            Cat.setCat283("false");
            Cat.setCat284("false");
            Cat.setCat285("false");
            Cat.setCat286("false");
            Cat.setCat287("false");
            Cat.setCat288("false");
            Cat.setCat289("false");
            Cat.setCat290("false");
            Cat.setCat291("false");
            Cat.setCat292("false");
            Cat.setCat293("false");
            Cat.setCat294("false");
            Cat.setCat295("false");
            Cat.setCat296("false");
            Cat.setCat297("false");
            Cat.setCat298("false");
            Cat.setCat299("false");


            result=CatPrivilegeDAO.insert(Cat);

            if(result==false)
                {
                     return false;

                }


/* Use to Insert New Entry in SerPrivilege related to Staff Detail Table */

        SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
        SerPrivilege Ser=new SerPrivilege(serid, staffobj, sublibrary_id);
        Ser.setSer401("false");
        Ser.setSer402("false");
        Ser.setSer403("false");
        Ser.setSer404("false");
        Ser.setSer405("false");
        Ser.setSer406("false");
        Ser.setSer407("false");
        Ser.setSer408("false");
        Ser.setSer409("false");
        Ser.setSer410("false");
        Ser.setSer411("false");
        Ser.setSer412("false");
        Ser.setSer413("false");
        Ser.setSer414("false");
        Ser.setSer415("false");
        Ser.setSer416("false");
        Ser.setSer417("false");
        Ser.setSer418("false");
        Ser.setSer419("false");
        Ser.setSer420("false");
        Ser.setSer421("false");
        Ser.setSer422("false");
        Ser.setSer423("false");
        Ser.setSer424("false");
        Ser.setSer425("false");
        Ser.setSer426("false");
        Ser.setSer427("false");
        Ser.setSer428("false");
        Ser.setSer429("false");
        Ser.setSer430("false");
        Ser.setSer431("false");
        Ser.setSer432("false");
        Ser.setSer433("false");
        Ser.setSer434("false");
        Ser.setSer435("false");
        Ser.setSer436("false");
        Ser.setSer437("false");
        Ser.setSer438("false");
        Ser.setSer439("false");
        Ser.setSer440("false");
        Ser.setSer441("false");
        Ser.setSer442("false");
        Ser.setSer443("false");
        Ser.setSer444("false");
        Ser.setSer445("false");
        Ser.setSer446("false");
        Ser.setSer447("false");
        Ser.setSer448("false");
        Ser.setSer449("false");
        Ser.setSer450("false");
        Ser.setSer451("false");
        Ser.setSer452("false");
        Ser.setSer453("false");
        Ser.setSer454("false");
        Ser.setSer455("false");
        Ser.setSer456("false");
        Ser.setSer457("false");
        Ser.setSer458("false");
        Ser.setSer459("false");
        Ser.setSer460("false");
        Ser.setSer461("false");
        Ser.setSer462("false");
        Ser.setSer463("false");
        Ser.setSer464("false");
        Ser.setSer465("false");
        Ser.setSer466("false");
        Ser.setSer467("false");
        Ser.setSer468("false");
        Ser.setSer469("false");
        Ser.setSer470("false");
        Ser.setSer471("false");
        Ser.setSer472("false");
        Ser.setSer473("false");
        Ser.setSer474("false");
        Ser.setSer475("false");
        Ser.setSer476("false");
        Ser.setSer477("false");
        Ser.setSer478("false");
        Ser.setSer479("false");
        Ser.setSer480("false");
        Ser.setSer481("false");
        Ser.setSer482("false");
        Ser.setSer483("false");
        Ser.setSer484("false");
        Ser.setSer485("false");
        Ser.setSer486("false");
        Ser.setSer487("false");
        Ser.setSer488("false");
        Ser.setSer489("false");
        Ser.setSer490("false");
        Ser.setSer491("false");
        Ser.setSer492("false");
        Ser.setSer493("false");
        Ser.setSer494("false");
        Ser.setSer495("false");
        Ser.setSer496("false");
        Ser.setSer497("false");
        Ser.setSer498("false");
        Ser.setSer499("false");


            result=SerPrivilegeDAO.insert(Ser);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in CirPrivilege related to Staff Detail Table */

        CirPrivilegeId cirprivid=new CirPrivilegeId(staff_id, library_id);
        CirPrivilege Cir=new CirPrivilege(cirprivid, staffobj, sublibrary_id);
        Cir.setCir301("false");
        Cir.setCir302("false");
        Cir.setCir303("false");
        Cir.setCir304("false");
        Cir.setCir305("false");
        Cir.setCir306("false");
        Cir.setCir307("false");
        Cir.setCir308("false");
        Cir.setCir309("false");
        Cir.setCir310("false");
        Cir.setCir311("false");
        Cir.setCir312("false");
        Cir.setCir313("false");
        Cir.setCir314("false");
        Cir.setCir315("false");
        Cir.setCir316("false");
        Cir.setCir317("false");
        Cir.setCir318("false");
        Cir.setCir319("false");
        Cir.setCir320("false");
        Cir.setCir321("false");
        Cir.setCir322("false");
        Cir.setCir323("false");
        Cir.setCir324("false");
        Cir.setCir325("false");
        Cir.setCir326("false");
        Cir.setCir327("false");
        Cir.setCir328("false");
        Cir.setCir329("false");
        Cir.setCir330("false");
        Cir.setCir331("false");
        Cir.setCir332("false");
        Cir.setCir333("false");
        Cir.setCir334("false");
        Cir.setCir335("false");
        Cir.setCir336("false");
        Cir.setCir337("false");
        Cir.setCir338("false");
        Cir.setCir339("false");
        Cir.setCir340("false");
        Cir.setCir341("false");
        Cir.setCir342("false");
        Cir.setCir343("false");
        Cir.setCir344("false");
        Cir.setCir345("false");
        Cir.setCir346("false");
        Cir.setCir347("false");
        Cir.setCir348("false");
        Cir.setCir349("false");
        Cir.setCir350("false");
        Cir.setCir351("false");
        Cir.setCir352("false");
        Cir.setCir353("false");
        Cir.setCir354("false");
        Cir.setCir355("false");
        Cir.setCir356("false");
        Cir.setCir357("false");
        Cir.setCir358("false");
        Cir.setCir359("false");
        Cir.setCir360("false");
        Cir.setCir361("false");
        Cir.setCir362("false");
        Cir.setCir363("false");
        Cir.setCir364("false");
        Cir.setCir365("false");
        Cir.setCir366("false");
        Cir.setCir367("false");
        Cir.setCir368("false");
        Cir.setCir369("false");
        Cir.setCir370("false");
        Cir.setCir371("false");
        Cir.setCir372("false");
        Cir.setCir373("false");
        Cir.setCir374("false");
        Cir.setCir375("false");
        Cir.setCir376("false");
        Cir.setCir377("false");
        Cir.setCir378("false");
        Cir.setCir379("false");
        Cir.setCir380("false");
        Cir.setCir381("false");
        Cir.setCir382("false");
        Cir.setCir383("false");
        Cir.setCir384("false");
        Cir.setCir385("false");
        Cir.setCir386("false");
        Cir.setCir387("false");
        Cir.setCir388("false");
        Cir.setCir389("false");
        Cir.setCir390("false");
        Cir.setCir391("false");
        Cir.setCir392("false");
        Cir.setCir393("false");
        Cir.setCir394("false");
        Cir.setCir395("false");
        Cir.setCir396("false");
        Cir.setCir397("false");
        Cir.setCir398("false");
        Cir.setCir399("false");


            result=CirPrivilegeDAO.insert(Cir);

            if(result==false)
                {
                   return false;

                }

            return true;


  }
   public static boolean   updateSublibraryId(String staff_id,String library_id,String sublibrary_id)
  {

      

   /* Use to Update Entry in Privilege related to Staff Detail Table */
        
           Privilege priv=PrivilegeDAO.searchStaffLogin(staff_id, library_id);
           priv.setSublibraryId(sublibrary_id);
           result=PrivilegeDAO.update(priv);

            if(result==false)
                {
                  return result;

                }
/* Use to Update Entry in AcqPrivilege related to Staff Detail Table */
            
                AcqPrivilege acq=AcqPrivilegeDAO.searchStaffLogin(staff_id, library_id);

               acq.setSublibraryId(sublibrary_id);
               result=AcqPrivilegeDAO.update(acq);

            if(result==false)
                {
                     return false;

                }
/* Use to Update Entry in CatPrivilege related to Staff Detail Table */

            
             CatPrivilege Cat=CatPrivilegeDAO.searchStaffLogin(staff_id,library_id);
            Cat.setSublibraryId(sublibrary_id);
            result=CatPrivilegeDAO.update(Cat);
            
            if(result==false)
                {
                     return false;

                }


/* Use to Update Entry in SerPrivilege related to Staff Detail Table */

      
        SerPrivilege Ser=SerPrivilegeDAO.searchStaffLogin(staff_id,library_id);
        Ser.setSublibraryId(sublibrary_id);
        result=SerPrivilegeDAO.update(Ser);

            if(result==false)
                {
                    return false;

                }
/* Use to Update Entry in CirPrivilege related to Staff Detail Table */

        CirPrivilege Cir=CirPrivilegeDAO.searchStaffLogin(staff_id, library_id);
        Cir.setSublibraryId(sublibrary_id);
        result=CirPrivilegeDAO.update(Cir);
        
            if(result==false)
                {
                   return false;

                }

            return true;


  }
  static public boolean assignStaffPrivilege(String staff_id,String library_id,String sublibrary_id)
  {

      StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);

       /* Use to Insert New Entry in Privilege related to Staff Detail Table */
           PrivilegeId privid=new PrivilegeId(staff_id, library_id);
           Privilege priv=new Privilege(privid, staffobj);
           priv.setSublibraryId(sublibrary_id);
           priv.setAcquisition("true");
           priv.setAdministrator("true");
           priv.setCataloguing("true");
           priv.setSerial("true");
           priv.setSystemSetup("true");
           priv.setUtilities("true");
           priv.setCirculation("true");
            priv.setOpac("false");

           result=PrivilegeDAO.insert(priv);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in AcqPrivilege related to Staff Detail Table */
                AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                AcqPrivilege acq=new AcqPrivilege(acqid, staffobj, sublibrary_id);

                acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                result=AcqPrivilegeDAO.insert(acq);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in CatPrivilege related to Staff Detail Table */

             CatPrivilegeId catpriId=new CatPrivilegeId(staff_id, library_id);
             CatPrivilege Cat=new CatPrivilege(catpriId, staffobj, sublibrary_id);

            Cat.setCat201("true");
            Cat.setCat202("true");
            Cat.setCat203("true");
            Cat.setCat204("true");
            Cat.setCat205("true");
            Cat.setCat206("true");
            Cat.setCat207("true");
            Cat.setCat208("true");
            Cat.setCat209("true");
            Cat.setCat210("true");
            Cat.setCat211("true");
            Cat.setCat212("true");
            Cat.setCat213("true");
            Cat.setCat214("true");
            Cat.setCat215("true");
            Cat.setCat216("true");
            Cat.setCat217("true");
            Cat.setCat218("true");
            Cat.setCat219("true");
            Cat.setCat220("true");
            Cat.setCat221("true");
            Cat.setCat222("true");
            Cat.setCat223("true");
            Cat.setCat224("true");
            Cat.setCat225("true");
            Cat.setCat226("true");
            Cat.setCat227("true");
            Cat.setCat228("true");
            Cat.setCat229("true");
            Cat.setCat230("true");
            Cat.setCat231("true");
            Cat.setCat232("true");
            Cat.setCat233("true");
            Cat.setCat234("true");
            Cat.setCat235("true");
            Cat.setCat236("true");
            Cat.setCat237("true");
            Cat.setCat238("true");
            Cat.setCat239("true");
            Cat.setCat240("true");
            Cat.setCat241("true");
            Cat.setCat242("true");
            Cat.setCat243("true");
            Cat.setCat244("true");
            Cat.setCat245("true");
            Cat.setCat246("true");
            Cat.setCat247("true");
            Cat.setCat248("true");
            Cat.setCat249("true");
            Cat.setCat250("true");
            Cat.setCat251("true");
            Cat.setCat252("true");
            Cat.setCat253("true");
            Cat.setCat254("true");
            Cat.setCat255("true");
            Cat.setCat256("true");
            Cat.setCat257("true");
            Cat.setCat258("true");
            Cat.setCat259("true");
            Cat.setCat260("true");
            Cat.setCat261("true");
            Cat.setCat262("true");
            Cat.setCat263("true");
            Cat.setCat264("true");
            Cat.setCat265("true");
            Cat.setCat266("true");
            Cat.setCat267("true");
            Cat.setCat268("true");
            Cat.setCat269("true");
            Cat.setCat270("true");
            Cat.setCat271("true");
            Cat.setCat272("true");
            Cat.setCat273("true");
            Cat.setCat274("true");
            Cat.setCat275("true");
            Cat.setCat276("true");
            Cat.setCat277("true");
            Cat.setCat278("true");
            Cat.setCat279("true");
            Cat.setCat280("true");
            Cat.setCat281("true");
            Cat.setCat282("true");
            Cat.setCat283("true");
            Cat.setCat284("true");
            Cat.setCat285("true");
            Cat.setCat286("true");
            Cat.setCat287("true");
            Cat.setCat288("true");
            Cat.setCat289("true");
            Cat.setCat290("true");
            Cat.setCat291("true");
            Cat.setCat292("true");
            Cat.setCat293("true");
            Cat.setCat294("true");
            Cat.setCat295("true");
            Cat.setCat296("true");
            Cat.setCat297("true");
            Cat.setCat298("true");
            Cat.setCat299("true");


            result=CatPrivilegeDAO.insert(Cat);

            if(result==false)
                {
                    return false;

                }


/* Use to Insert New Entry in SerPrivilege related to Staff Detail Table */

        SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
        SerPrivilege Ser=new SerPrivilege(serid, staffobj, sublibrary_id);
        Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


            result=SerPrivilegeDAO.insert(Ser);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in CirPrivilege related to Staff Detail Table */

        CirPrivilegeId cirprivid=new CirPrivilegeId(staff_id, library_id);
        CirPrivilege Cir=new CirPrivilege(cirprivid, staffobj, sublibrary_id);
        Cir.setCir301("true");
        Cir.setCir302("true");
        Cir.setCir303("true");
        Cir.setCir304("true");
        Cir.setCir305("true");
        Cir.setCir306("true");
        Cir.setCir307("true");
        Cir.setCir308("true");
        Cir.setCir309("true");
        Cir.setCir310("true");
        Cir.setCir311("true");
        Cir.setCir312("true");
        Cir.setCir313("true");
        Cir.setCir314("true");
        Cir.setCir315("true");
        Cir.setCir316("true");
        Cir.setCir317("true");
        Cir.setCir318("true");
        Cir.setCir319("true");
        Cir.setCir320("true");
        Cir.setCir321("true");
        Cir.setCir322("true");
        Cir.setCir323("true");
        Cir.setCir324("true");
        Cir.setCir325("true");
        Cir.setCir326("true");
        Cir.setCir327("true");
        Cir.setCir328("true");
        Cir.setCir329("true");
        Cir.setCir330("true");
        Cir.setCir331("true");
        Cir.setCir332("true");
        Cir.setCir333("true");
        Cir.setCir334("true");
        Cir.setCir335("true");
        Cir.setCir336("true");
        Cir.setCir337("true");
        Cir.setCir338("true");
        Cir.setCir339("true");
        Cir.setCir340("true");
        Cir.setCir341("true");
        Cir.setCir342("true");
        Cir.setCir343("true");
        Cir.setCir344("true");
        Cir.setCir345("true");
        Cir.setCir346("true");
        Cir.setCir347("true");
        Cir.setCir348("true");
        Cir.setCir349("true");
        Cir.setCir350("true");
        Cir.setCir351("true");
        Cir.setCir352("true");
        Cir.setCir353("true");
        Cir.setCir354("true");
        Cir.setCir355("true");
        Cir.setCir356("true");
        Cir.setCir357("true");
        Cir.setCir358("true");
        Cir.setCir359("true");
        Cir.setCir360("true");
        Cir.setCir361("true");
        Cir.setCir362("true");
        Cir.setCir363("true");
        Cir.setCir364("true");
        Cir.setCir365("true");
        Cir.setCir366("true");
        Cir.setCir367("true");
        Cir.setCir368("true");
        Cir.setCir369("true");
        Cir.setCir370("true");
        Cir.setCir371("true");
        Cir.setCir372("true");
        Cir.setCir373("true");
        Cir.setCir374("true");
        Cir.setCir375("true");
        Cir.setCir376("true");
        Cir.setCir377("true");
        Cir.setCir378("true");
        Cir.setCir379("true");
        Cir.setCir380("true");
        Cir.setCir381("true");
        Cir.setCir382("true");
        Cir.setCir383("true");
        Cir.setCir384("true");
        Cir.setCir385("true");
        Cir.setCir386("true");
        Cir.setCir387("true");
        Cir.setCir388("true");
        Cir.setCir389("true");
        Cir.setCir390("true");
        Cir.setCir391("true");
        Cir.setCir392("true");
        Cir.setCir393("true");
        Cir.setCir394("true");
        Cir.setCir395("true");
        Cir.setCir396("true");
        Cir.setCir397("true");
        Cir.setCir398("true");
        Cir.setCir399("true");

            result=CirPrivilegeDAO.insert(Cir);

            if(result==false)
                {
                    return false;

                }



return true;

   
  }
 static public boolean assignDepartmentalStaffPrivilege(String staff_id,String library_id,String sublibrary_id)
  {

      StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);

       /* Use to Insert New Entry in Privilege related to Staff Detail Table */
           PrivilegeId privid=new PrivilegeId(staff_id, library_id);
           Privilege priv=new Privilege(privid, staffobj);
           priv.setSublibraryId(sublibrary_id);
           priv.setAcquisition("true");
           priv.setAdministrator("true");
           priv.setCataloguing("true");
           priv.setSerial("true");
           priv.setSystemSetup("true");
           priv.setUtilities("true");
           priv.setCirculation("true");
            priv.setOpac("false");

           result=PrivilegeDAO.insert(priv);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in AcqPrivilege related to Staff Detail Table */
                AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                AcqPrivilege acq=new AcqPrivilege(acqid, staffobj, sublibrary_id);

                acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                result=AcqPrivilegeDAO.insert(acq);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in CatPrivilege related to Staff Detail Table */

             CatPrivilegeId catpriId=new CatPrivilegeId(staff_id, library_id);
             CatPrivilege Cat=new CatPrivilege(catpriId, staffobj, sublibrary_id);

            Cat.setCat201("true");
            Cat.setCat202("true");
            Cat.setCat203("true");
            Cat.setCat204("true");
            Cat.setCat205("true");
            Cat.setCat206("true");
            Cat.setCat207("true");
            Cat.setCat208("true");
            Cat.setCat209("true");
            Cat.setCat210("true");
            Cat.setCat211("true");
            Cat.setCat212("true");
            Cat.setCat213("true");
            Cat.setCat214("true");
            Cat.setCat215("true");
            Cat.setCat216("true");
            Cat.setCat217("true");
            Cat.setCat218("true");
            Cat.setCat219("true");
            Cat.setCat220("true");
            Cat.setCat221("true");
            Cat.setCat222("true");
            Cat.setCat223("true");
            Cat.setCat224("true");
            Cat.setCat225("true");
            Cat.setCat226("true");
            Cat.setCat227("true");
            Cat.setCat228("true");
            Cat.setCat229("true");
            Cat.setCat230("true");
            Cat.setCat231("true");
            Cat.setCat232("true");
            Cat.setCat233("true");
            Cat.setCat234("true");
            Cat.setCat235("true");
            Cat.setCat236("true");
            Cat.setCat237("true");
            Cat.setCat238("true");
            Cat.setCat239("true");
            Cat.setCat240("true");
            Cat.setCat241("true");
            Cat.setCat242("true");
            Cat.setCat243("true");
            Cat.setCat244("true");
            Cat.setCat245("true");
            Cat.setCat246("true");
            Cat.setCat247("true");
            Cat.setCat248("true");
            Cat.setCat249("true");
            Cat.setCat250("true");
            Cat.setCat251("true");
            Cat.setCat252("true");
            Cat.setCat253("true");
            Cat.setCat254("true");
            Cat.setCat255("true");
            Cat.setCat256("true");
            Cat.setCat257("true");
            Cat.setCat258("true");
            Cat.setCat259("true");
            Cat.setCat260("true");
            Cat.setCat261("true");
            Cat.setCat262("true");
            Cat.setCat263("true");
            Cat.setCat264("true");
            Cat.setCat265("true");
            Cat.setCat266("true");
            Cat.setCat267("true");
            Cat.setCat268("true");
            Cat.setCat269("true");
            Cat.setCat270("true");
            Cat.setCat271("true");
            Cat.setCat272("true");
            Cat.setCat273("true");
            Cat.setCat274("true");
            Cat.setCat275("true");
            Cat.setCat276("true");
            Cat.setCat277("true");
            Cat.setCat278("true");
            Cat.setCat279("true");
            Cat.setCat280("true");
            Cat.setCat281("true");
            Cat.setCat282("true");
            Cat.setCat283("true");
            Cat.setCat284("true");
            Cat.setCat285("true");
            Cat.setCat286("true");
            Cat.setCat287("true");
            Cat.setCat288("true");
            Cat.setCat289("true");
            Cat.setCat290("true");
            Cat.setCat291("true");
            Cat.setCat292("true");
            Cat.setCat293("true");
            Cat.setCat294("true");
            Cat.setCat295("true");
            Cat.setCat296("true");
            Cat.setCat297("true");
            Cat.setCat298("true");
            Cat.setCat299("true");


            result=CatPrivilegeDAO.insert(Cat);

            if(result==false)
                {
                    return false;

                }


/* Use to Insert New Entry in SerPrivilege related to Staff Detail Table */

        SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
        SerPrivilege Ser=new SerPrivilege(serid, staffobj, sublibrary_id);
        Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


            result=SerPrivilegeDAO.insert(Ser);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in CirPrivilege related to Staff Detail Table */

        CirPrivilegeId cirprivid=new CirPrivilegeId(staff_id, library_id);
        CirPrivilege Cir=new CirPrivilege(cirprivid, staffobj, sublibrary_id);
        Cir.setCir301("true");
        Cir.setCir302("true");
        Cir.setCir303("true");
        Cir.setCir304("true");
        Cir.setCir305("true");
        Cir.setCir306("true");
        Cir.setCir307("true");
        Cir.setCir308("true");
        Cir.setCir309("true");
        Cir.setCir310("true");
        Cir.setCir311("true");
        Cir.setCir312("true");
        Cir.setCir313("true");
        Cir.setCir314("true");
        Cir.setCir315("true");
        Cir.setCir316("true");
        Cir.setCir317("true");
        Cir.setCir318("true");
        Cir.setCir319("true");
        Cir.setCir320("true");
        Cir.setCir321("true");
        Cir.setCir322("true");
        Cir.setCir323("true");
        Cir.setCir324("true");
        Cir.setCir325("true");
        Cir.setCir326("true");
        Cir.setCir327("true");
        Cir.setCir328("true");
        Cir.setCir329("true");
        Cir.setCir330("true");
        Cir.setCir331("true");
        Cir.setCir332("true");
        Cir.setCir333("true");
        Cir.setCir334("true");
        Cir.setCir335("true");
        Cir.setCir336("true");
        Cir.setCir337("true");
        Cir.setCir338("true");
        Cir.setCir339("true");
        Cir.setCir340("true");
        Cir.setCir341("true");
        Cir.setCir342("true");
        Cir.setCir343("true");
        Cir.setCir344("true");
        Cir.setCir345("true");
        Cir.setCir346("true");
        Cir.setCir347("true");
        Cir.setCir348("true");
        Cir.setCir349("true");
        Cir.setCir350("true");
        Cir.setCir351("true");
        Cir.setCir352("true");
        Cir.setCir353("true");
        Cir.setCir354("true");
        Cir.setCir355("true");
        Cir.setCir356("true");
        Cir.setCir357("true");
        Cir.setCir358("true");
        Cir.setCir359("true");
        Cir.setCir360("true");
        Cir.setCir361("true");
        Cir.setCir362("true");
        Cir.setCir363("true");
        Cir.setCir364("true");
        Cir.setCir365("true");
        Cir.setCir366("true");
        Cir.setCir367("true");
        Cir.setCir368("true");
        Cir.setCir369("true");
        Cir.setCir370("true");
        Cir.setCir371("true");
        Cir.setCir372("true");
        Cir.setCir373("true");
        Cir.setCir374("true");
        Cir.setCir375("true");
        Cir.setCir376("true");
        Cir.setCir377("true");
        Cir.setCir378("true");
        Cir.setCir379("true");
        Cir.setCir380("true");
        Cir.setCir381("true");
        Cir.setCir382("true");
        Cir.setCir383("true");
        Cir.setCir384("true");
        Cir.setCir385("true");
        Cir.setCir386("true");
        Cir.setCir387("true");
        Cir.setCir388("true");
        Cir.setCir389("true");
        Cir.setCir390("true");
        Cir.setCir391("true");
        Cir.setCir392("true");
        Cir.setCir393("true");
        Cir.setCir394("true");
        Cir.setCir395("true");
        Cir.setCir396("true");
        Cir.setCir397("true");
        Cir.setCir398("true");
        Cir.setCir399("true");

            result=CirPrivilegeDAO.insert(Cir);

            if(result==false)
                {
                    return false;

                }



return true;


  }

  static public boolean assignDepartmentalAdminPrivilege(String staff_id,String library_id,String sublibrary_id)
  {
        StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);

   /* Use to Insert New Entry in Privilege related to Staff Detail Table */
           PrivilegeId privid=new PrivilegeId(staff_id, library_id);
           Privilege priv=new Privilege(privid, staffobj);
           priv.setSublibraryId(sublibrary_id);
           priv.setAcquisition("true");
           priv.setAdministrator("false");
           priv.setCataloguing("false");
           priv.setOpac("false");
           priv.setSerial("true");
           priv.setSystemSetup("false");
           priv.setUtilities("false");
           priv.setCirculation("false");

           result=PrivilegeDAO.insert(priv);

            if(result==false)
                {
                  return result;

                }
/* Use to Insert New Entry in AcqPrivilege related to Staff Detail Table */
                AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                AcqPrivilege acq=new AcqPrivilege(acqid, staffobj, sublibrary_id);

               acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");


                result=AcqPrivilegeDAO.insert(acq);

            if(result==false)
                {
                     return false;

                }
/* Use to Insert New Entry in CatPrivilege related to Staff Detail Table */

             CatPrivilegeId catpriId=new CatPrivilegeId(staff_id, library_id);
             CatPrivilege Cat=new CatPrivilege(catpriId, staffobj, sublibrary_id);

            Cat.setCat201("false");
            Cat.setCat202("false");
            Cat.setCat203("false");
            Cat.setCat204("false");
            Cat.setCat205("false");
            Cat.setCat206("false");
            Cat.setCat207("false");
            Cat.setCat208("false");
            Cat.setCat209("false");
            Cat.setCat210("false");
            Cat.setCat211("false");
            Cat.setCat212("false");
            Cat.setCat213("false");
            Cat.setCat214("false");
            Cat.setCat215("false");
            Cat.setCat216("false");
            Cat.setCat217("false");
            Cat.setCat218("false");
            Cat.setCat219("false");
            Cat.setCat220("false");
            Cat.setCat221("false");
            Cat.setCat222("false");
            Cat.setCat223("false");
            Cat.setCat224("false");
            Cat.setCat225("false");
            Cat.setCat226("false");
            Cat.setCat227("false");
            Cat.setCat228("false");
            Cat.setCat229("false");
            Cat.setCat230("false");
            Cat.setCat231("false");
            Cat.setCat232("false");
            Cat.setCat233("false");
            Cat.setCat234("false");
            Cat.setCat235("false");
            Cat.setCat236("false");
            Cat.setCat237("false");
            Cat.setCat238("false");
            Cat.setCat239("false");
            Cat.setCat240("false");
            Cat.setCat241("false");
            Cat.setCat242("false");
            Cat.setCat243("false");
            Cat.setCat244("false");
            Cat.setCat245("false");
            Cat.setCat246("false");
            Cat.setCat247("false");
            Cat.setCat248("false");
            Cat.setCat249("false");
            Cat.setCat250("false");
            Cat.setCat251("false");
            Cat.setCat252("false");
            Cat.setCat253("false");
            Cat.setCat254("false");
            Cat.setCat255("false");
            Cat.setCat256("false");
            Cat.setCat257("false");
            Cat.setCat258("false");
            Cat.setCat259("false");
            Cat.setCat260("false");
            Cat.setCat261("false");
            Cat.setCat262("false");
            Cat.setCat263("false");
            Cat.setCat264("false");
            Cat.setCat265("false");
            Cat.setCat266("false");
            Cat.setCat267("false");
            Cat.setCat268("false");
            Cat.setCat269("false");
            Cat.setCat270("false");
            Cat.setCat271("false");
            Cat.setCat272("false");
            Cat.setCat273("false");
            Cat.setCat274("false");
            Cat.setCat275("false");
            Cat.setCat276("false");
            Cat.setCat277("false");
            Cat.setCat278("false");
            Cat.setCat279("false");
            Cat.setCat280("false");
            Cat.setCat281("false");
            Cat.setCat282("false");
            Cat.setCat283("false");
            Cat.setCat284("false");
            Cat.setCat285("false");
            Cat.setCat286("false");
            Cat.setCat287("false");
            Cat.setCat288("false");
            Cat.setCat289("false");
            Cat.setCat290("false");
            Cat.setCat291("false");
            Cat.setCat292("false");
            Cat.setCat293("false");
            Cat.setCat294("false");
            Cat.setCat295("false");
            Cat.setCat296("false");
            Cat.setCat297("false");
            Cat.setCat298("false");
            Cat.setCat299("false");



            result=CatPrivilegeDAO.insert(Cat);

            if(result==false)
                {
                     return false;

                }


/* Use to Insert New Entry in SerPrivilege related to Staff Detail Table */

        SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
        SerPrivilege Ser=new SerPrivilege(serid, staffobj, sublibrary_id);
         Ser.setSer401("true");
         Ser.setSer402("true");
         Ser.setSer403("true");
         Ser.setSer404("true");
         Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");



            result=SerPrivilegeDAO.insert(Ser);

            if(result==false)
                {
                    return false;

                }
/* Use to Insert New Entry in CirPrivilege related to Staff Detail Table */

        CirPrivilegeId cirprivid=new CirPrivilegeId(staff_id, library_id);
        CirPrivilege Cir=new CirPrivilege(cirprivid, staffobj, sublibrary_id);
        Cir.setCir301("false");
        Cir.setCir302("false");
        Cir.setCir303("false");
        Cir.setCir304("false");
        Cir.setCir305("false");
        Cir.setCir306("false");
        Cir.setCir307("false");
        Cir.setCir308("false");
        Cir.setCir309("false");
        Cir.setCir310("false");
        Cir.setCir311("false");
        Cir.setCir312("false");
        Cir.setCir313("false");
        Cir.setCir314("false");
        Cir.setCir315("false");
        Cir.setCir316("false");
        Cir.setCir317("false");
        Cir.setCir318("false");
        Cir.setCir319("false");
        Cir.setCir320("false");
        Cir.setCir321("false");
        Cir.setCir322("false");
        Cir.setCir323("false");
        Cir.setCir324("false");
        Cir.setCir325("false");
        Cir.setCir326("false");
        Cir.setCir327("false");
        Cir.setCir328("false");
        Cir.setCir329("false");
        Cir.setCir330("false");
        Cir.setCir331("false");
        Cir.setCir332("false");
        Cir.setCir333("false");
        Cir.setCir334("false");
        Cir.setCir335("false");
        Cir.setCir336("false");
        Cir.setCir337("false");
        Cir.setCir338("false");
        Cir.setCir339("false");
        Cir.setCir340("false");
        Cir.setCir341("false");
        Cir.setCir342("false");
        Cir.setCir343("false");
        Cir.setCir344("false");
        Cir.setCir345("false");
        Cir.setCir346("false");
        Cir.setCir347("false");
        Cir.setCir348("false");
        Cir.setCir349("false");
        Cir.setCir350("false");
        Cir.setCir351("false");
        Cir.setCir352("false");
        Cir.setCir353("false");
        Cir.setCir354("false");
        Cir.setCir355("false");
        Cir.setCir356("false");
        Cir.setCir357("false");
        Cir.setCir358("false");
        Cir.setCir359("false");
        Cir.setCir360("false");
        Cir.setCir361("false");
        Cir.setCir362("false");
        Cir.setCir363("false");
        Cir.setCir364("false");
        Cir.setCir365("false");
        Cir.setCir366("false");
        Cir.setCir367("false");
        Cir.setCir368("false");
        Cir.setCir369("false");
        Cir.setCir370("false");
        Cir.setCir371("false");
        Cir.setCir372("false");
        Cir.setCir373("false");
        Cir.setCir374("false");
        Cir.setCir375("false");
        Cir.setCir376("false");
        Cir.setCir377("false");
        Cir.setCir378("false");
        Cir.setCir379("false");
        Cir.setCir380("false");
        Cir.setCir381("false");
        Cir.setCir382("false");
        Cir.setCir383("false");
        Cir.setCir384("false");
        Cir.setCir385("false");
        Cir.setCir386("false");
        Cir.setCir387("false");
        Cir.setCir388("false");
        Cir.setCir389("false");
        Cir.setCir390("false");
        Cir.setCir391("false");
        Cir.setCir392("false");
        Cir.setCir393("false");
        Cir.setCir394("false");
        Cir.setCir395("false");
        Cir.setCir396("false");
        Cir.setCir397("false");
        Cir.setCir398("false");
        Cir.setCir399("false");


            result=CirPrivilegeDAO.insert(Cir);

            if(result==false)
                {
                   return false;

                }

            return true;


  }

  //privilege

static   String[] substring;
static String sql1,sql2,sql3,sql4,sql5;
static  int [] privilege_index;
/* delimiter */
static String delimiter = ",";
static String [] privilege={"true","true","true","true"};
static String[] acq_privilege=new String[100];
static String[] cat_privilege=new String[100];
static String[] cir_privilege=new String[100];
static String[] ser_privilege=new String[100];



public  static boolean AssignPrivilege(String string,String staff_id,String library_id,String sublibrary_id)
{

for( int k=0;k<100;k++)
    {
       acq_privilege[k]="true";
       cat_privilege[k]="true";
        cir_privilege[k]="true";
        ser_privilege[k]="true";
    }
    substring = string.split(delimiter);
    
boolean admincheck=false;
    privilege_index=new int[substring.length];
    

for(int i =0; i < substring.length; i++)
    {
    privilege_index[i]=Integer.parseInt(substring[i]);
    
    if(privilege_index[i]<5)
    {
    if(privilege_index[i]==1) admincheck=true;
    privilege[privilege_index[i]-1]="false";
    }

    if(privilege_index[i]>=100&&privilege_index[i]<200)
    {
    acq_privilege[privilege_index[i]-100]="false";
    }

    if(privilege_index[i]>=200&&privilege_index[i]<300)
    {
    cat_privilege[privilege_index[i]-200]="false";
    }

    if(privilege_index[i]>=300&&privilege_index[i]<400)
    {
    cir_privilege[privilege_index[i]-300]="false";
    }

    if(privilege_index[i]>=400&&privilege_index[i]<500)
    {
    ser_privilege[privilege_index[i]-400]="false";
    }
    }
    result=PrivilegeDAO.DeleteStaff(staff_id, library_id, sublibrary_id);
   
    result=AcqPrivilegeDAO.DeleteLogin(staff_id, library_id, sublibrary_id);
    result=SerPrivilegeDAO.DeleteLogin(staff_id, library_id, sublibrary_id);
    result=CatPrivilegeDAO.DeleteLogin(staff_id, library_id, sublibrary_id);
    result=CirPrivilegeDAO.DeleteLogin(staff_id, library_id, sublibrary_id);

    if(result==true){


        Login login=LoginDAO.searchStaffLogin(staff_id, library_id, sublibrary_id);

        if(!admincheck)
        {
            if(login!=null && (login.getRole().equals("admin")||login.getRole().equals("insti-admin")))
            {
                login.setRole("staff");
            }else if(login!=null && login.getRole().equals("dept-admin"))
            {
                login.setRole("dept-staff");
            }
            LoginDAO.update1(login);
        }

        StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
    PrivilegeId privid=new PrivilegeId(staff_id, library_id);
    Privilege priv=new Privilege(privid, staffobj);
    priv.setSublibraryId(sublibrary_id);
    priv.setAcquisition(acq_privilege[0]);
    priv.setCataloguing(cat_privilege[0]);
    priv.setCirculation(cir_privilege[0]);
    priv.setSerial(ser_privilege[0]);
     
    if(!login.getRole().contains("admin")){
    priv.setAdministrator("true");
    priv.setSystemSetup("true");
    priv.setUtilities("true");
    priv.setOpac("true");
    }else{
        priv.setAdministrator("false");
    priv.setSystemSetup("false");
    priv.setUtilities("false");
    priv.setOpac("true");
    }
    result=PrivilegeDAO.insert(priv);

    if(result==true){
        System.out.println(".......................");
        update_acq_priv(staff_id,library_id,sublibrary_id,acq_privilege);
        update_cat_priv(staff_id,library_id,sublibrary_id,cat_privilege);
        update_cir_priv(staff_id,library_id,sublibrary_id,cir_privilege);
        update_ser_priv(staff_id,library_id,sublibrary_id,ser_privilege);
    }


    }


      /*sql1="update privilege set acquisition='"+acq_privilege[0]+"',cataloguing='"+cat_privilege[0]+
         "',circulation='"+cir_privilege[0]+"',serial='"+ser_privilege[0]+"',administrator='"+privilege[0]+
         "',system_setup='"+privilege[1]+"',utilities='"+privilege[2]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    sql2="update acq_privilege set acq_101='"+acq_privilege[1]+"',acq_102='"+acq_privilege[2]+
         "',acq_103='"+acq_privilege[3]+"',acq_104='"+acq_privilege[4]+"',acq_105='"+acq_privilege[5]+
         "',acq_106='"+acq_privilege[6]+"',acq_107='"+acq_privilege[7]+"',acq_108='"+acq_privilege[8]+
         "',acq_109='"+acq_privilege[9]+"',acq_110='"+acq_privilege[10]+"',acq_111='"+acq_privilege[11]+
         "',acq_112='"+acq_privilege[12]+"',acq_113='"+acq_privilege[13]+"',acq_114='"+acq_privilege[14]+
         "',acq_115='"+acq_privilege[15]+"',acq_116='"+acq_privilege[16]+"',acq_117='"+acq_privilege[17]+
         "',acq_118='"+acq_privilege[18]+"',acq_119='"+acq_privilege[19]+"',acq_120='"+acq_privilege[20]+
         "',acq_121='"+acq_privilege[21]+"',acq_122='"+acq_privilege[22]+"',acq_123='"+acq_privilege[23]+
         "',acq_124='"+acq_privilege[24]+"',acq_125='"+acq_privilege[25]+"',acq_126='"+acq_privilege[26]+
         "',acq_127='"+acq_privilege[27]+"',acq_128='"+acq_privilege[28]+"',acq_129='"+acq_privilege[29]+
         "',acq_130='"+acq_privilege[30]+"',acq_131='"+acq_privilege[31]+"',acq_132='"+acq_privilege[32]+
         "',acq_133='"+acq_privilege[33]+"',acq_134='"+acq_privilege[34]+"',acq_135='"+acq_privilege[35]+
         "',acq_136='"+acq_privilege[36]+"',acq_137='"+acq_privilege[37]+"',acq_138='"+acq_privilege[38]+
         "',acq_139='"+acq_privilege[39]+"',acq_140='"+acq_privilege[40]+"',acq_141='"+acq_privilege[41]+
         "',acq_142='"+acq_privilege[42]+"',acq_143='"+acq_privilege[43]+"',acq_144='"+acq_privilege[44]+
         "',acq_145='"+acq_privilege[45]+"',acq_146='"+acq_privilege[46]+"',acq_147='"+acq_privilege[47]+
         "',acq_148='"+acq_privilege[48]+"',acq_149='"+acq_privilege[49]+"',acq_150='"+acq_privilege[50]+
         "',acq_151='"+acq_privilege[51]+"',acq_152='"+acq_privilege[52]+"',acq_153='"+acq_privilege[53]+
         "',acq_154='"+acq_privilege[54]+"',acq_155='"+acq_privilege[55]+"',acq_156='"+acq_privilege[56]+
         "',acq_157='"+acq_privilege[57]+"',acq_158='"+acq_privilege[58]+"',acq_159='"+acq_privilege[59]+
         "',acq_160='"+acq_privilege[60]+"',acq_161='"+acq_privilege[61]+"',acq_162='"+acq_privilege[62]+
         "',acq_163='"+acq_privilege[63]+"',acq_164='"+acq_privilege[64]+"',acq_165='"+acq_privilege[65]+
         "',acq_166='"+acq_privilege[66]+"',acq_167='"+acq_privilege[67]+"',acq_168='"+acq_privilege[68]+
         "',acq_169='"+acq_privilege[69]+"',acq_170='"+acq_privilege[70]+"',acq_171='"+acq_privilege[71]+
         "',acq_172='"+acq_privilege[72]+"',acq_173='"+acq_privilege[73]+"',acq_174='"+acq_privilege[74]+
         "',acq_175='"+acq_privilege[75]+"',acq_176='"+acq_privilege[76]+"',acq_177='"+acq_privilege[77]+
         "',acq_178='"+acq_privilege[78]+"',acq_179='"+acq_privilege[79]+"',acq_180='"+acq_privilege[80]+
         "',acq_181='"+acq_privilege[81]+"',acq_182='"+acq_privilege[82]+"',acq_183='"+acq_privilege[83]+
         "',acq_184='"+acq_privilege[84]+"',acq_185='"+acq_privilege[85]+"',acq_186='"+acq_privilege[86]+
         "',acq_187='"+acq_privilege[87]+"',acq_188='"+acq_privilege[88]+
         "',acq_189='"+acq_privilege[89]+"',acq_190='"+acq_privilege[90]+"',acq_191='"+acq_privilege[91]+
         "',acq_192='"+acq_privilege[92]+"',acq_193='"+acq_privilege[93]+"',acq_194='"+acq_privilege[94]+
         "',acq_195='"+acq_privilege[95]+"',acq_196='"+acq_privilege[96]+"',acq_197='"+acq_privilege[97]+
         "',acq_198='"+acq_privilege[98]+"',acq_199='"+acq_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

           sql3="update cat_privilege set cat_201='"+cat_privilege[1]+"',cat_202='"+cat_privilege[2]+
         "',cat_203='"+cat_privilege[3]+"',cat_204='"+cat_privilege[4]+"',cat_205='"+cat_privilege[5]+
         "',cat_206='"+cat_privilege[6]+"',cat_207='"+cat_privilege[7]+"',cat_208='"+cat_privilege[8]+
         "',cat_209='"+cat_privilege[9]+"',cat_210='"+cat_privilege[10]+"',cat_211='"+cat_privilege[11]+
         "',cat_212='"+cat_privilege[12]+"',cat_213='"+cat_privilege[13]+"',cat_214='"+cat_privilege[14]+
         "',cat_215='"+cat_privilege[15]+"',cat_216='"+cat_privilege[16]+"',cat_217='"+cat_privilege[17]+
         "',cat_218='"+cat_privilege[18]+"',cat_219='"+cat_privilege[19]+"',cat_220='"+cat_privilege[20]+
         "',cat_221='"+cat_privilege[21]+"',cat_222='"+cat_privilege[22]+"',cat_223='"+cat_privilege[23]+
         "',cat_224='"+cat_privilege[24]+"',cat_225='"+cat_privilege[25]+"',cat_226='"+cat_privilege[26]+
         "',cat_227='"+cat_privilege[27]+"',cat_228='"+cat_privilege[28]+"',cat_229='"+cat_privilege[29]+
         "',cat_230='"+cat_privilege[30]+"',cat_231='"+cat_privilege[31]+"',cat_232='"+cat_privilege[32]+
         "',cat_233='"+cat_privilege[33]+"',cat_234='"+cat_privilege[34]+"',cat_235='"+cat_privilege[35]+
         "',cat_236='"+cat_privilege[36]+"',cat_237='"+cat_privilege[37]+"',cat_238='"+cat_privilege[38]+
         "',cat_239='"+cat_privilege[39]+"',cat_240='"+cat_privilege[40]+"',cat_241='"+cat_privilege[41]+
         "',cat_242='"+cat_privilege[42]+"',cat_243='"+cat_privilege[43]+"',cat_244='"+cat_privilege[44]+
         "',cat_245='"+cat_privilege[45]+"',cat_246='"+cat_privilege[46]+"',cat_247='"+cat_privilege[47]+
         "',cat_248='"+cat_privilege[48]+"',cat_249='"+cat_privilege[49]+"',cat_250='"+cat_privilege[50]+
         "',cat_251='"+cat_privilege[51]+"',cat_252='"+cat_privilege[52]+"',cat_253='"+cat_privilege[53]+
         "',cat_254='"+cat_privilege[54]+"',cat_255='"+cat_privilege[55]+"',cat_256='"+cat_privilege[56]+
         "',cat_257='"+cat_privilege[57]+"',cat_258='"+cat_privilege[58]+"',cat_259='"+cat_privilege[59]+
         "',cat_260='"+cat_privilege[60]+"',cat_261='"+cat_privilege[61]+"',cat_262='"+cat_privilege[62]+
         "',cat_263='"+cat_privilege[63]+"',cat_264='"+cat_privilege[64]+"',cat_265='"+cat_privilege[65]+
         "',cat_266='"+cat_privilege[66]+"',cat_267='"+cat_privilege[67]+"',cat_268='"+cat_privilege[68]+
         "',cat_269='"+cat_privilege[69]+"',cat_270='"+cat_privilege[70]+"',cat_271='"+cat_privilege[71]+
         "',cat_272='"+cat_privilege[72]+"',cat_273='"+cat_privilege[73]+"',cat_274='"+cat_privilege[74]+
         "',cat_275='"+cat_privilege[75]+"',cat_276='"+cat_privilege[76]+"',cat_277='"+cat_privilege[77]+
         "',cat_278='"+cat_privilege[78]+"',cat_279='"+cat_privilege[79]+"',cat_280='"+cat_privilege[80]+
         "',cat_281='"+cat_privilege[81]+"',cat_282='"+cat_privilege[82]+"',cat_283='"+cat_privilege[83]+
         "',cat_284='"+cat_privilege[84]+"',cat_285='"+cat_privilege[85]+"',cat_286='"+cat_privilege[86]+
         "',cat_287='"+cat_privilege[87]+"',cat_288='"+cat_privilege[88]+"',cat_289='"+cat_privilege[89]+
         "',cat_290='"+cat_privilege[90]+"',cat_291='"+cat_privilege[91]+
         "',cat_292='"+cat_privilege[92]+"',cat_293='"+cat_privilege[93]+"',cat_294='"+cat_privilege[94]+
         "',cat_295='"+cat_privilege[95]+"',cat_296='"+cat_privilege[96]+"',cat_297='"+cat_privilege[97]+
         "',cat_298='"+cat_privilege[98]+"',cat_299='"+cat_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    sql4="update  cir_privilege set cir_301='"+cir_privilege[1]+"',cir_302='"+cir_privilege[2]+
         "',cir_303='"+cir_privilege[3]+"',cir_304='"+cir_privilege[4]+"',cir_305='"+cir_privilege[5]+
         "',cir_306='"+cir_privilege[6]+"',cir_307='"+cir_privilege[7]+"',cir_308='"+cir_privilege[8]+
         "',cir_309='"+cir_privilege[9]+"',cir_310='"+cir_privilege[10]+"',cir_311='"+cir_privilege[11]+
         "',cir_312='"+cir_privilege[12]+"',cir_313='"+cir_privilege[13]+"',cir_314='"+cir_privilege[14]+
         "',cir_315='"+cir_privilege[15]+"',cir_316='"+cir_privilege[16]+"',cir_317='"+cir_privilege[17]+
         "',cir_318='"+cir_privilege[18]+"',cir_319='"+cir_privilege[19]+"',cir_320='"+cir_privilege[20]+
         "',cir_321='"+cir_privilege[21]+"',cir_322='"+cir_privilege[22]+"',cir_323='"+cir_privilege[23]+
         "',cir_324='"+cir_privilege[24]+"',cir_325='"+cir_privilege[25]+"',cir_326='"+cir_privilege[26]+
         "',cir_327='"+cir_privilege[27]+"',cir_328='"+cir_privilege[28]+"',cir_329='"+cir_privilege[29]+
         "',cir_330='"+cir_privilege[30]+"',cir_331='"+cir_privilege[31]+"',cir_332='"+cir_privilege[32]+
         "',cir_333='"+cir_privilege[33]+"',cir_334='"+cir_privilege[34]+"',cir_335='"+cir_privilege[35]+
         "',cir_336='"+cir_privilege[36]+"',cir_337='"+cir_privilege[37]+"',cir_338='"+cir_privilege[38]+
         "',cir_339='"+cir_privilege[39]+"',cir_340='"+cir_privilege[40]+"',cir_341='"+cir_privilege[41]+
         "',cir_342='"+cir_privilege[42]+"',cir_343='"+cir_privilege[43]+"',cir_344='"+cir_privilege[44]+
         "',cir_345='"+cir_privilege[45]+"',cir_346='"+cir_privilege[46]+"',cir_347='"+cir_privilege[47]+
         "',cir_348='"+cir_privilege[48]+"',cir_349='"+cir_privilege[49]+"',cir_350='"+cir_privilege[50]+
         "',cir_351='"+cir_privilege[51]+"',cir_352='"+cir_privilege[52]+"',cir_353='"+cir_privilege[53]+
         "',cir_354='"+cir_privilege[54]+"',cir_355='"+cir_privilege[55]+"',cir_356='"+cir_privilege[56]+
         "',cir_357='"+cir_privilege[57]+"',cir_358='"+cir_privilege[58]+"',cir_359='"+cir_privilege[59]+
         "',cir_360='"+cir_privilege[60]+"',cir_361='"+cir_privilege[61]+"',cir_362='"+cir_privilege[62]+
         "',cir_363='"+cir_privilege[63]+"',cir_364='"+cir_privilege[64]+"',cir_365='"+cir_privilege[65]+
         "',cir_366='"+cir_privilege[66]+"',cir_367='"+cir_privilege[67]+"',cir_368='"+cir_privilege[68]+
         "',cir_369='"+cir_privilege[69]+"',cir_370='"+cir_privilege[70]+"',cir_371='"+cir_privilege[71]+
         "',cir_372='"+cir_privilege[72]+"',cir_373='"+cir_privilege[73]+"',cir_374='"+cir_privilege[74]+
         "',cir_375='"+cir_privilege[75]+"',cir_376='"+cir_privilege[76]+"',cir_377='"+cir_privilege[77]+
         "',cir_378='"+cir_privilege[78]+"',cir_379='"+cir_privilege[79]+"',cir_380='"+cir_privilege[80]+
         "',cir_381='"+cir_privilege[81]+"',cir_382='"+cir_privilege[82]+"',cir_383='"+cir_privilege[83]+
         "',cir_384='"+cir_privilege[84]+"',cir_385='"+cir_privilege[85]+"',cir_386='"+cir_privilege[86]+
         "',cir_387='"+cir_privilege[87]+"',cir_388='"+cir_privilege[88]+
         "',cir_389='"+cir_privilege[89]+"',cir_390='"+cir_privilege[90]+"',cir_391='"+cir_privilege[91]+
         "',cir_392='"+cir_privilege[92]+"',cir_393='"+cir_privilege[93]+"',cir_394='"+cir_privilege[94]+
         "',cir_395='"+cir_privilege[95]+"',cir_396='"+cir_privilege[96]+"',cir_397='"+cir_privilege[97]+
         "',cir_398='"+cir_privilege[98]+"',cir_399='"+cir_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    sql5="update ser_privilege set ser_401='"+ser_privilege[1]+"',ser_402='"+ser_privilege[2]+
         "',ser_403='"+ser_privilege[3]+"',ser_404='"+ser_privilege[4]+"',ser_405='"+ser_privilege[5]+
         "',ser_406='"+ser_privilege[6]+"',ser_407='"+ser_privilege[7]+"',ser_408='"+ser_privilege[8]+
         "',ser_409='"+ser_privilege[9]+"',ser_410='"+ser_privilege[10]+"',ser_411='"+ser_privilege[11]+
         "',ser_412='"+ser_privilege[12]+"',ser_413='"+ser_privilege[13]+"',ser_414='"+ser_privilege[14]+
         "',ser_415='"+ser_privilege[15]+"',ser_416='"+ser_privilege[16]+"',ser_417='"+ser_privilege[17]+
         "',ser_418='"+ser_privilege[18]+"',ser_419='"+ser_privilege[19]+"',ser_420='"+ser_privilege[20]+
         "',ser_421='"+ser_privilege[21]+"',ser_422='"+ser_privilege[22]+"',ser_423='"+ser_privilege[23]+
         "',ser_424='"+ser_privilege[24]+"',ser_425='"+ser_privilege[25]+"',ser_426='"+ser_privilege[26]+
         "',ser_427='"+ser_privilege[27]+"',ser_428='"+ser_privilege[28]+"',ser_429='"+ser_privilege[29]+
         "',ser_430='"+ser_privilege[30]+"',ser_431='"+ser_privilege[31]+"',ser_432='"+ser_privilege[32]+
         "',ser_433='"+ser_privilege[33]+"',ser_434='"+ser_privilege[34]+"',ser_435='"+ser_privilege[35]+
         "',ser_436='"+ser_privilege[36]+"',ser_437='"+ser_privilege[37]+"',ser_438='"+ser_privilege[38]+
         "',ser_439='"+ser_privilege[39]+"',ser_440='"+ser_privilege[40]+"',ser_441='"+ser_privilege[41]+
         "',ser_442='"+ser_privilege[42]+"',ser_443='"+ser_privilege[43]+"',ser_444='"+ser_privilege[44]+
         "',ser_445='"+ser_privilege[45]+"',ser_446='"+ser_privilege[46]+"',ser_447='"+ser_privilege[47]+
         "',ser_448='"+ser_privilege[48]+"',ser_449='"+ser_privilege[49]+"',ser_450='"+ser_privilege[50]+
         "',ser_451='"+ser_privilege[51]+"',ser_452='"+ser_privilege[52]+"',ser_453='"+ser_privilege[53]+
         "',ser_454='"+ser_privilege[54]+"',ser_455='"+ser_privilege[55]+"',ser_456='"+ser_privilege[56]+
         "',ser_457='"+ser_privilege[57]+"',ser_458='"+ser_privilege[58]+"',ser_459='"+ser_privilege[59]+
         "',ser_460='"+ser_privilege[61]+"',ser_461='"+ser_privilege[61]+"',ser_462='"+ser_privilege[62]+
         "',ser_463='"+ser_privilege[63]+"',ser_464='"+ser_privilege[64]+"',ser_465='"+ser_privilege[65]+
         "',ser_466='"+ser_privilege[66]+"',ser_467='"+ser_privilege[67]+"',ser_468='"+ser_privilege[68]+
         "',ser_469='"+ser_privilege[69]+"',ser_470='"+ser_privilege[70]+"',ser_471='"+ser_privilege[71]+
         "',ser_472='"+ser_privilege[72]+"',ser_473='"+ser_privilege[73]+"',ser_474='"+ser_privilege[74]+
         "',ser_475='"+ser_privilege[75]+"',ser_476='"+ser_privilege[76]+"',ser_477='"+ser_privilege[77]+
         "',ser_478='"+ser_privilege[78]+"',ser_479='"+ser_privilege[79]+"',ser_480='"+ser_privilege[80]+
         "',ser_481='"+ser_privilege[81]+"',ser_482='"+ser_privilege[82]+"',ser_483='"+ser_privilege[83]+
         "',ser_484='"+ser_privilege[84]+"',ser_485='"+ser_privilege[85]+"',ser_486='"+ser_privilege[86]+
         "',ser_487='"+ser_privilege[87]+"',ser_488='"+ser_privilege[88]+"',ser_489='"+ser_privilege[89]+
         "',ser_490='"+ser_privilege[90]+"',ser_491='"+ser_privilege[91]+
         "',ser_492='"+ser_privilege[92]+"',ser_493='"+ser_privilege[93]+"',ser_494='"+ser_privilege[94]+
         "',ser_495='"+ser_privilege[95]+"',ser_496='"+ser_privilege[96]+"',ser_497='"+ser_privilege[97]+
         "',ser_498='"+ser_privilege[98]+"',ser_499='"+ser_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";
//System.out.println(sql1);
//System.out.println(sql2);
//System.out.println(sql3);
//System.out.println(sql4);
//System.out.println(sql5);
   MyQueryResult.getMyExecuteUpdate(sql1);
   MyQueryResult.getMyExecuteUpdate(sql2);
   MyQueryResult.getMyExecuteUpdate(sql3);
   MyQueryResult.getMyExecuteUpdate(sql4);
   MyQueryResult.getMyExecuteUpdate(sql5);
       *
       */
    return true;
   }

public static boolean update_acq_priv(String staff_id,String library_id,String sublibrary_id,String acq_arr[])
{
            StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
            AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                AcqPrivilege acq=new AcqPrivilege(acqid, staffobj, sublibrary_id);

               	acq.setAcq101(acq_arr[1]);
                acq.setAcq102(acq_arr[2]);
                acq.setAcq103(acq_arr[3]);
                acq.setAcq104(acq_arr[4]);
                acq.setAcq105(acq_arr[5]);
                acq.setAcq106(acq_arr[6]);
                acq.setAcq107(acq_arr[7]);
                acq.setAcq108(acq_arr[8]);
                acq.setAcq109(acq_arr[9]);
                acq.setAcq110(acq_arr[10]);
                acq.setAcq111(acq_arr[11]);
                acq.setAcq112(acq_arr[12]);
                acq.setAcq113(acq_arr[13]);
                acq.setAcq114(acq_arr[14]);
                acq.setAcq115(acq_arr[15]);
                acq.setAcq116(acq_arr[16]);
                acq.setAcq117(acq_arr[17]);
                acq.setAcq118(acq_arr[18]);
                acq.setAcq119(acq_arr[19]);
                acq.setAcq120(acq_arr[20]);
                acq.setAcq121(acq_arr[21]);
                acq.setAcq122(acq_arr[22]);
                acq.setAcq123(acq_arr[23]);
                acq.setAcq124(acq_arr[24]);
                acq.setAcq125(acq_arr[25]);
                acq.setAcq126(acq_arr[26]);
                acq.setAcq127(acq_arr[27]);
                acq.setAcq128(acq_arr[28]);
                acq.setAcq129(acq_arr[29]);
                acq.setAcq130(acq_arr[30]);
                acq.setAcq131(acq_arr[31]);
                acq.setAcq132(acq_arr[32]);
                acq.setAcq133(acq_arr[33]);
                acq.setAcq134(acq_arr[34]);
                acq.setAcq135(acq_arr[35]);
                acq.setAcq136(acq_arr[36]);
                acq.setAcq137(acq_arr[37]);
                acq.setAcq138(acq_arr[38]);
                acq.setAcq139(acq_arr[39]);
                acq.setAcq140(acq_arr[40]);
                acq.setAcq141(acq_arr[41]);
                acq.setAcq142(acq_arr[42]);
                acq.setAcq143(acq_arr[43]);
                acq.setAcq144(acq_arr[44]);
                acq.setAcq145(acq_arr[45]);
                acq.setAcq146(acq_arr[46]);
                acq.setAcq147(acq_arr[47]);
                acq.setAcq148(acq_arr[48]);
                acq.setAcq149(acq_arr[49]);
                acq.setAcq150(acq_arr[50]);
                acq.setAcq151(acq_arr[51]);
                acq.setAcq152(acq_arr[52]);
                acq.setAcq153(acq_arr[53]);
                acq.setAcq154(acq_arr[54]);
                acq.setAcq155(acq_arr[55]);
                acq.setAcq156(acq_arr[56]);
                acq.setAcq157(acq_arr[57]);
                acq.setAcq158(acq_arr[58]);
                acq.setAcq159(acq_arr[59]);
                acq.setAcq160(acq_arr[60]);
                acq.setAcq161(acq_arr[61]);
                acq.setAcq162(acq_arr[62]);
                acq.setAcq163(acq_arr[63]);
                acq.setAcq164(acq_arr[64]);
                acq.setAcq165(acq_arr[65]);
                acq.setAcq166(acq_arr[66]);
                acq.setAcq167(acq_arr[67]);
                acq.setAcq168(acq_arr[68]);
                acq.setAcq169(acq_arr[69]);
                acq.setAcq170(acq_arr[70]);
                acq.setAcq171(acq_arr[71]);
                acq.setAcq172(acq_arr[72]);
                acq.setAcq173(acq_arr[73]);
                acq.setAcq174(acq_arr[74]);
                acq.setAcq175(acq_arr[75]);
                acq.setAcq176(acq_arr[76]);
                acq.setAcq177(acq_arr[77]);
                acq.setAcq178(acq_arr[78]);
                acq.setAcq179(acq_arr[79]);
                acq.setAcq180(acq_arr[80]);
                acq.setAcq181(acq_arr[81]);
                acq.setAcq182(acq_arr[82]);
                acq.setAcq183(acq_arr[83]);
                acq.setAcq184(acq_arr[84]);
                acq.setAcq185(acq_arr[85]);
                acq.setAcq186(acq_arr[86]);
                acq.setAcq187(acq_arr[87]);
                acq.setAcq188(acq_arr[88]);
                acq.setAcq189(acq_arr[89]);
                acq.setAcq190(acq_arr[90]);
                acq.setAcq191(acq_arr[91]);
                acq.setAcq192(acq_arr[92]);
                acq.setAcq193(acq_arr[93]);
                acq.setAcq194(acq_arr[94]);
                acq.setAcq195(acq_arr[95]);
                acq.setAcq196(acq_arr[96]);
                acq.setAcq197(acq_arr[97]);
                acq.setAcq198(acq_arr[98]);
                acq.setAcq199(acq_arr[99]);
            result=AcqPrivilegeDAO.insert(acq);

            if(result==false)
                {
                   return false;

                }

            return true;




}
public static boolean update_cat_priv(String staff_id,String library_id,String sublibrary_id,String cat_arr[])
{

   StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
            CatPrivilegeId catid=new CatPrivilegeId(staff_id, library_id);
                CatPrivilege cat=new CatPrivilege(catid , staffobj, sublibrary_id);
		cat.setCat201(cat_arr[1]);
                cat.setCat202(cat_arr[2]);
                cat.setCat203(cat_arr[3]);
                cat.setCat204(cat_arr[4]);
                cat.setCat205(cat_arr[5]);
                cat.setCat206(cat_arr[6]);
                cat.setCat207(cat_arr[7]);
                cat.setCat208(cat_arr[8]);
                cat.setCat209(cat_arr[9]);
                cat.setCat210(cat_arr[10]);
                cat.setCat211(cat_arr[11]);
                cat.setCat212(cat_arr[12]);
                cat.setCat213(cat_arr[13]);
                cat.setCat214(cat_arr[14]);
                cat.setCat215(cat_arr[15]);
                cat.setCat216(cat_arr[16]);
                cat.setCat217(cat_arr[17]);
                cat.setCat218(cat_arr[18]);
                cat.setCat219(cat_arr[19]);
                cat.setCat220(cat_arr[20]);
                cat.setCat221(cat_arr[21]);
                cat.setCat222(cat_arr[22]);
                cat.setCat223(cat_arr[23]);
                cat.setCat224(cat_arr[24]);
                cat.setCat225(cat_arr[25]);
                cat.setCat226(cat_arr[26]);
                cat.setCat227(cat_arr[27]);
                cat.setCat228(cat_arr[28]);
                cat.setCat229(cat_arr[29]);
                cat.setCat230(cat_arr[30]);
                cat.setCat231(cat_arr[31]);
                cat.setCat232(cat_arr[32]);
                cat.setCat233(cat_arr[33]);
                cat.setCat234(cat_arr[34]);
                cat.setCat235(cat_arr[35]);
                cat.setCat236(cat_arr[36]);
                cat.setCat237(cat_arr[37]);
                cat.setCat238(cat_arr[38]);
                cat.setCat239(cat_arr[39]);
                cat.setCat240(cat_arr[40]);
                cat.setCat241(cat_arr[41]);
                cat.setCat242(cat_arr[42]);
                cat.setCat243(cat_arr[43]);
                cat.setCat244(cat_arr[44]);
                cat.setCat245(cat_arr[45]);
                cat.setCat246(cat_arr[46]);
                cat.setCat247(cat_arr[47]);
                cat.setCat248(cat_arr[48]);
                cat.setCat249(cat_arr[49]);
                cat.setCat250(cat_arr[50]);
                cat.setCat251(cat_arr[51]);
                cat.setCat252(cat_arr[52]);
                cat.setCat253(cat_arr[53]);
                cat.setCat254(cat_arr[54]);
                cat.setCat255(cat_arr[55]);
                cat.setCat256(cat_arr[56]);
                cat.setCat257(cat_arr[57]);
                cat.setCat258(cat_arr[58]);
                cat.setCat259(cat_arr[59]);
                cat.setCat260(cat_arr[60]);
                cat.setCat261(cat_arr[61]);
                cat.setCat262(cat_arr[62]);
                cat.setCat263(cat_arr[63]);
                cat.setCat264(cat_arr[64]);
                cat.setCat265(cat_arr[65]);
                cat.setCat266(cat_arr[66]);
                cat.setCat267(cat_arr[67]);
                cat.setCat268(cat_arr[68]);
                cat.setCat269(cat_arr[69]);
                cat.setCat270(cat_arr[70]);
                cat.setCat271(cat_arr[71]);
                cat.setCat272(cat_arr[72]);
                cat.setCat273(cat_arr[73]);
                cat.setCat274(cat_arr[74]);
                cat.setCat275(cat_arr[75]);
                cat.setCat276(cat_arr[76]);
                cat.setCat277(cat_arr[77]);
                cat.setCat278(cat_arr[78]);
                cat.setCat279(cat_arr[79]);
                cat.setCat280(cat_arr[80]);
                cat.setCat281(cat_arr[81]);
                cat.setCat282(cat_arr[82]);
                cat.setCat283(cat_arr[83]);
                cat.setCat284(cat_arr[84]);
                cat.setCat285(cat_arr[85]);
                cat.setCat286(cat_arr[86]);
                cat.setCat287(cat_arr[87]);
                cat.setCat288(cat_arr[88]);
                cat.setCat289(cat_arr[89]);
                cat.setCat290(cat_arr[90]);
                cat.setCat291(cat_arr[91]);
                cat.setCat292(cat_arr[92]);
                cat.setCat293(cat_arr[93]);
                cat.setCat294(cat_arr[94]);
                cat.setCat295(cat_arr[95]);
                cat.setCat296(cat_arr[96]);
                cat.setCat297(cat_arr[97]);
                cat.setCat298(cat_arr[98]);
                cat.setCat299(cat_arr[99]);
                result=CatPrivilegeDAO.insert(cat);

            if(result==false)
                {
                   return false;

                }

            return true;


}
public static boolean update_cir_priv(String staff_id,String library_id,String sublibrary_id,String cir_arr[])
{

   StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
            CirPrivilegeId cirid=new CirPrivilegeId(staff_id, library_id);
                CirPrivilege cir=new CirPrivilege(cirid, staffobj, sublibrary_id);
		cir.setCir301(cir_arr[1]);
                cir.setCir302(cir_arr[2]);
                cir.setCir303(cir_arr[3]);
                cir.setCir304(cir_arr[4]);
                cir.setCir305(cir_arr[5]);
                cir.setCir306(cir_arr[6]);
                cir.setCir307(cir_arr[7]);
                cir.setCir308(cir_arr[8]);
                cir.setCir309(cir_arr[9]);
                cir.setCir310(cir_arr[10]);
                cir.setCir311(cir_arr[11]);
                cir.setCir312(cir_arr[12]);
                cir.setCir313(cir_arr[13]);
                cir.setCir314(cir_arr[14]);
                cir.setCir315(cir_arr[15]);
                cir.setCir316(cir_arr[16]);
                cir.setCir317(cir_arr[17]);
                cir.setCir318(cir_arr[18]);
                cir.setCir319(cir_arr[19]);
                cir.setCir320(cir_arr[20]);
                cir.setCir321(cir_arr[21]);
                cir.setCir322(cir_arr[22]);
                cir.setCir323(cir_arr[23]);
                cir.setCir324(cir_arr[24]);
                cir.setCir325(cir_arr[25]);
                cir.setCir326(cir_arr[26]);
                cir.setCir327(cir_arr[27]);
                cir.setCir328(cir_arr[28]);
                cir.setCir329(cir_arr[29]);
                cir.setCir330(cir_arr[30]);
                cir.setCir331(cir_arr[31]);
                cir.setCir332(cir_arr[32]);
                cir.setCir333(cir_arr[33]);
                cir.setCir334(cir_arr[34]);
                cir.setCir335(cir_arr[35]);
                cir.setCir336(cir_arr[36]);
                cir.setCir337(cir_arr[37]);
                cir.setCir338(cir_arr[38]);
                cir.setCir339(cir_arr[39]);
                cir.setCir340(cir_arr[40]);
                cir.setCir341(cir_arr[41]);
                cir.setCir342(cir_arr[42]);
                cir.setCir343(cir_arr[43]);
                cir.setCir344(cir_arr[44]);
                cir.setCir345(cir_arr[45]);
                cir.setCir346(cir_arr[46]);
                cir.setCir347(cir_arr[47]);
                cir.setCir348(cir_arr[48]);
                cir.setCir349(cir_arr[49]);
                cir.setCir350(cir_arr[50]);
                cir.setCir351(cir_arr[51]);
                cir.setCir352(cir_arr[52]);
                cir.setCir353(cir_arr[53]);
                cir.setCir354(cir_arr[54]);
                cir.setCir355(cir_arr[55]);
                cir.setCir356(cir_arr[56]);
                cir.setCir357(cir_arr[57]);
                cir.setCir358(cir_arr[58]);
                cir.setCir359(cir_arr[59]);
                cir.setCir360(cir_arr[60]);
                cir.setCir361(cir_arr[61]);
                cir.setCir362(cir_arr[62]);
                cir.setCir363(cir_arr[63]);
                cir.setCir364(cir_arr[64]);
                cir.setCir365(cir_arr[65]);
                cir.setCir366(cir_arr[66]);
                cir.setCir367(cir_arr[67]);
                cir.setCir368(cir_arr[68]);
                cir.setCir369(cir_arr[69]);
                cir.setCir370(cir_arr[70]);
                cir.setCir371(cir_arr[71]);
                cir.setCir372(cir_arr[72]);
                cir.setCir373(cir_arr[73]);
                cir.setCir374(cir_arr[74]);
                cir.setCir375(cir_arr[75]);
                cir.setCir376(cir_arr[76]);
                cir.setCir377(cir_arr[77]);
                cir.setCir378(cir_arr[78]);
                cir.setCir379(cir_arr[79]);
                cir.setCir380(cir_arr[80]);
                cir.setCir381(cir_arr[81]);
                cir.setCir382(cir_arr[82]);
                cir.setCir383(cir_arr[83]);
                cir.setCir384(cir_arr[84]);
                cir.setCir385(cir_arr[85]);
                cir.setCir386(cir_arr[86]);
                cir.setCir387(cir_arr[87]);
                cir.setCir388(cir_arr[88]);
                cir.setCir389(cir_arr[89]);
                cir.setCir390(cir_arr[90]);
                cir.setCir391(cir_arr[91]);
                cir.setCir392(cir_arr[92]);
                cir.setCir393(cir_arr[93]);
                cir.setCir394(cir_arr[94]);
                cir.setCir395(cir_arr[95]);
                cir.setCir396(cir_arr[96]);
                cir.setCir397(cir_arr[97]);
                cir.setCir398(cir_arr[98]);
                cir.setCir399(cir_arr[99]);

             
             result=CirPrivilegeDAO.insert(cir);

            if(result==false)
                {
                   return false;

                }

            return true;


}
public static boolean update_ser_priv(String staff_id,String library_id,String sublibrary_id,String ser_arr[])
{

   StaffDetail staffobj=StaffDetailDAO.searchStaffId(staff_id, library_id);
            SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                SerPrivilege ser=new SerPrivilege(serid, staffobj, sublibrary_id);

		ser.setSer401(ser_arr[1]);
                ser.setSer402(ser_arr[2]);
                ser.setSer403(ser_arr[3]);
                ser.setSer404(ser_arr[4]);
                ser.setSer405(ser_arr[5]);
                ser.setSer406(ser_arr[6]);
                ser.setSer407(ser_arr[7]);
                ser.setSer408(ser_arr[8]);
                ser.setSer409(ser_arr[9]);
                ser.setSer410(ser_arr[10]);
                ser.setSer411(ser_arr[11]);
                ser.setSer412(ser_arr[12]);
                ser.setSer413(ser_arr[13]);
                ser.setSer414(ser_arr[14]);
                ser.setSer415(ser_arr[15]);
                ser.setSer416(ser_arr[16]);
                ser.setSer417(ser_arr[17]);
                ser.setSer418(ser_arr[18]);
                ser.setSer419(ser_arr[19]);
                ser.setSer420(ser_arr[20]);
                ser.setSer421(ser_arr[21]);
                ser.setSer422(ser_arr[22]);
                ser.setSer423(ser_arr[23]);
                ser.setSer424(ser_arr[24]);
                ser.setSer425(ser_arr[25]);
                ser.setSer426(ser_arr[26]);
                ser.setSer427(ser_arr[27]);
                ser.setSer428(ser_arr[28]);
                ser.setSer429(ser_arr[29]);
                ser.setSer430(ser_arr[30]);
                ser.setSer431(ser_arr[31]);
                ser.setSer432(ser_arr[32]);
                ser.setSer433(ser_arr[33]);
                ser.setSer434(ser_arr[34]);
                ser.setSer435(ser_arr[35]);
                ser.setSer436(ser_arr[36]);
                ser.setSer437(ser_arr[37]);
                ser.setSer438(ser_arr[38]);
                ser.setSer439(ser_arr[39]);
                ser.setSer440(ser_arr[40]);
                ser.setSer441(ser_arr[41]);
                ser.setSer442(ser_arr[42]);
                ser.setSer443(ser_arr[43]);
                ser.setSer444(ser_arr[44]);
                ser.setSer445(ser_arr[45]);
                ser.setSer446(ser_arr[46]);
                ser.setSer447(ser_arr[47]);
                ser.setSer448(ser_arr[48]);
                ser.setSer449(ser_arr[49]);
                ser.setSer450(ser_arr[50]);
                ser.setSer451(ser_arr[51]);
                ser.setSer452(ser_arr[52]);
                ser.setSer453(ser_arr[53]);
                ser.setSer454(ser_arr[54]);
                ser.setSer455(ser_arr[55]);
                ser.setSer456(ser_arr[56]);
                ser.setSer457(ser_arr[57]);
                ser.setSer458(ser_arr[58]);
                ser.setSer459(ser_arr[59]);
                ser.setSer460(ser_arr[60]);
                ser.setSer461(ser_arr[61]);
                ser.setSer462(ser_arr[62]);
                ser.setSer463(ser_arr[63]);
                ser.setSer464(ser_arr[64]);
                ser.setSer465(ser_arr[65]);
                ser.setSer466(ser_arr[66]);
                ser.setSer467(ser_arr[67]);
                ser.setSer468(ser_arr[68]);
                ser.setSer469(ser_arr[69]);
                ser.setSer470(ser_arr[70]);
                ser.setSer471(ser_arr[71]);
                ser.setSer472(ser_arr[72]);
                ser.setSer473(ser_arr[73]);
                ser.setSer474(ser_arr[74]);
                ser.setSer475(ser_arr[75]);
                ser.setSer476(ser_arr[76]);
                ser.setSer477(ser_arr[77]);
                ser.setSer478(ser_arr[78]);
                ser.setSer479(ser_arr[79]);
                ser.setSer480(ser_arr[80]);
                ser.setSer481(ser_arr[81]);
                ser.setSer482(ser_arr[82]);
                ser.setSer483(ser_arr[83]);
                ser.setSer484(ser_arr[84]);
                ser.setSer485(ser_arr[85]);
                ser.setSer486(ser_arr[86]);
                ser.setSer487(ser_arr[87]);
                ser.setSer488(ser_arr[88]);
                ser.setSer489(ser_arr[89]);
                ser.setSer490(ser_arr[90]);
                ser.setSer491(ser_arr[91]);
                ser.setSer492(ser_arr[92]);
                ser.setSer493(ser_arr[93]);
                ser.setSer494(ser_arr[94]);
                ser.setSer495(ser_arr[95]);
                ser.setSer496(ser_arr[96]);
                ser.setSer497(ser_arr[97]);
                ser.setSer498(ser_arr[98]);
                ser.setSer499(ser_arr[99]);
                result=SerPrivilegeDAO.insert(ser);

            if(result==false)
                {
                   return false;

                }

            return true;


}


 /* public ResultSet[] backupPrivilege(String staff_id,String library_id)
  {
 String priv_sql1,priv_sql2,priv_sql3,priv_sql4,priv_sql5;
 ResultSet pre_rst1,pre_rst2,pre_rst3,pre_rst4,pre_rst5;
 ResultSet resultset[]=new ResultSet[5];
priv_sql1="Select *  from privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql2="Select *  from acq_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql3="Select *  from cat_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql4="Select *  from cir_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql5="Select *  from ser_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";

pre_rst1=MyQueryResult.getMyExecuteQuery(priv_sql1);
resultset[0]=pre_rst1;
pre_rst2=MyQueryResult.getMyExecuteQuery(priv_sql2);
resultset[1]=pre_rst2;
pre_rst3=MyQueryResult.getMyExecuteQuery(priv_sql3);
resultset[2]=pre_rst3;
pre_rst4=MyQueryResult.getMyExecuteQuery(priv_sql4);
resultset[3]=pre_rst4;
pre_rst5=MyQueryResult.getMyExecuteQuery(priv_sql5);
resultset[4]=pre_rst5;
try
{
pre_rst1.next();
pre_rst1.beforeFirst();
}catch(Exception e)
{
System.out.println("Error in Privilege.backupPrivilege:"+e);
}
return resultset;

  }



//public void rollbackPrivilege(ResultSet resultset[], String staff_id, String library_id)
{

ResultSet pre_rst1=resultset[0],pre_rst2=resultset[1],pre_rst3=resultset[2],pre_rst4=resultset[3],pre_rst5=resultset[4];
String pre_sql1,pre_sql2,pre_sql3,pre_sql4,pre_sql5;


 int pre_start=0;
 String pre_privilege[]=new String[10];
 String pre_acq_privilege[]=new String[100];
 String pre_cat_privilege[]=new String[100];
 String pre_cir_privilege[]=new String[100];
 String pre_ser_privilege[]=new String[100];
try{
 pre_rst1.next();
while(pre_start<8)
{
  System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"+resultset[0].getString(8)+staff_id+library_id);
  pre_privilege[pre_start]= pre_rst1.getString(pre_start+3);
  pre_start++;
}

pre_start=1;
pre_rst2.next();
pre_rst3.next();
pre_rst4.next();
pre_rst5.next();
while(pre_start<100)
{
  pre_acq_privilege[pre_start]= pre_rst2.getString(pre_start+2);
  pre_cat_privilege[pre_start]= pre_rst3.getString(pre_start+2);
  pre_cir_privilege[pre_start]= pre_rst4.getString(pre_start+2);
  pre_ser_privilege[pre_start]= pre_rst5.getString(pre_start+2);
  pre_start++;
}
}catch(Exception e)
{
System.out.println("Error in Privilege.rollbackPrivilege"+e);
}

pre_sql1="update privilege set acquisition='"+pre_privilege[0]+"',cataloguing='"+pre_privilege[1]+
         "',circulation='"+pre_privilege[2]+"',serial='"+pre_privilege[3]+"',administrator='"+pre_privilege[4]+
         "',system_setup='"+pre_privilege[5]+"',utilities='"+pre_privilege[6]+"',opac='"+pre_privilege[7]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    pre_sql2="update acq_privilege set acq_101='"+pre_acq_privilege[1]+"',acq_102='"+pre_acq_privilege[2]+
         "',acq_103='"+pre_acq_privilege[3]+"',acq_104='"+pre_acq_privilege[4]+"',acq_105='"+pre_acq_privilege[5]+
         "',acq_106='"+pre_acq_privilege[6]+"',acq_107='"+pre_acq_privilege[7]+"',acq_108='"+pre_acq_privilege[8]+
         "',acq_109='"+pre_acq_privilege[9]+"',acq_110='"+pre_acq_privilege[10]+"',acq_111='"+pre_acq_privilege[11]+
         "',acq_112='"+pre_acq_privilege[12]+"',acq_113='"+pre_acq_privilege[13]+"',acq_114='"+pre_acq_privilege[14]+
         "',acq_115='"+pre_acq_privilege[15]+"',acq_116='"+pre_acq_privilege[16]+"',acq_117='"+pre_acq_privilege[17]+
         "',acq_118='"+pre_acq_privilege[18]+"',acq_119='"+pre_acq_privilege[19]+"',acq_120='"+pre_acq_privilege[20]+
         "',acq_121='"+pre_acq_privilege[21]+"',acq_122='"+pre_acq_privilege[22]+"',acq_123='"+pre_acq_privilege[23]+
         "',acq_124='"+pre_acq_privilege[24]+"',acq_125='"+pre_acq_privilege[25]+"',acq_126='"+pre_acq_privilege[26]+
         "',acq_127='"+pre_acq_privilege[27]+"',acq_128='"+pre_acq_privilege[28]+"',acq_129='"+pre_acq_privilege[29]+
         "',acq_130='"+pre_acq_privilege[30]+"',acq_131='"+pre_acq_privilege[31]+"',acq_132='"+pre_acq_privilege[32]+
         "',acq_133='"+pre_acq_privilege[33]+"',acq_134='"+pre_acq_privilege[34]+"',acq_135='"+pre_acq_privilege[35]+
         "',acq_136='"+pre_acq_privilege[36]+"',acq_137='"+pre_acq_privilege[37]+"',acq_138='"+pre_acq_privilege[38]+
         "',acq_139='"+pre_acq_privilege[39]+"',acq_140='"+pre_acq_privilege[40]+"',acq_141='"+pre_acq_privilege[41]+
         "',acq_142='"+pre_acq_privilege[42]+"',acq_143='"+pre_acq_privilege[43]+"',acq_144='"+pre_acq_privilege[44]+
         "',acq_145='"+pre_acq_privilege[45]+"',acq_146='"+pre_acq_privilege[46]+"',acq_147='"+pre_acq_privilege[47]+
         "',acq_148='"+pre_acq_privilege[48]+"',acq_149='"+pre_acq_privilege[49]+"',acq_150='"+pre_acq_privilege[50]+
         "',acq_151='"+pre_acq_privilege[51]+"',acq_152='"+pre_acq_privilege[52]+"',acq_153='"+pre_acq_privilege[53]+
         "',acq_154='"+pre_acq_privilege[54]+"',acq_155='"+pre_acq_privilege[55]+"',acq_156='"+pre_acq_privilege[56]+
         "',acq_157='"+pre_acq_privilege[57]+"',acq_158='"+pre_acq_privilege[58]+"',acq_159='"+pre_acq_privilege[59]+
         "',acq_160='"+pre_acq_privilege[60]+"',acq_161='"+pre_acq_privilege[61]+"',acq_162='"+pre_acq_privilege[62]+
         "',acq_163='"+pre_acq_privilege[63]+"',acq_164='"+pre_acq_privilege[64]+"',acq_165='"+pre_acq_privilege[65]+
         "',acq_166='"+pre_acq_privilege[66]+"',acq_167='"+pre_acq_privilege[67]+"',acq_168='"+pre_acq_privilege[68]+
         "',acq_169='"+pre_acq_privilege[69]+"',acq_170='"+pre_acq_privilege[70]+"',acq_171='"+pre_acq_privilege[71]+
         "',acq_172='"+pre_acq_privilege[72]+"',acq_173='"+pre_acq_privilege[73]+"',acq_174='"+pre_acq_privilege[74]+
         "',acq_175='"+pre_acq_privilege[75]+"',acq_176='"+pre_acq_privilege[76]+"',acq_177='"+pre_acq_privilege[77]+
         "',acq_178='"+pre_acq_privilege[78]+"',acq_179='"+pre_acq_privilege[79]+"',acq_180='"+pre_acq_privilege[80]+
         "',acq_181='"+pre_acq_privilege[81]+"',acq_182='"+pre_acq_privilege[82]+"',acq_183='"+pre_acq_privilege[83]+
         "',acq_184='"+pre_acq_privilege[84]+"',acq_185='"+pre_acq_privilege[85]+"',acq_186='"+pre_acq_privilege[86]+
         "',acq_187='"+pre_acq_privilege[87]+"',acq_188='"+pre_acq_privilege[88]+
         "',acq_189='"+pre_acq_privilege[89]+"',acq_190='"+pre_acq_privilege[90]+"',acq_191='"+pre_acq_privilege[91]+
         "',acq_192='"+pre_acq_privilege[92]+"',acq_193='"+pre_acq_privilege[93]+"',acq_194='"+pre_acq_privilege[94]+
         "',acq_195='"+pre_acq_privilege[95]+"',acq_196='"+pre_acq_privilege[96]+"',acq_197='"+pre_acq_privilege[97]+
         "',acq_198='"+pre_acq_privilege[98]+"',acq_199='"+pre_acq_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

           pre_sql3="update cat_privilege set cat_201='"+pre_cat_privilege[1]+"',cat_202='"+pre_cat_privilege[2]+
         "',cat_203='"+pre_cat_privilege[3]+"',cat_204='"+pre_cat_privilege[4]+"',cat_205='"+pre_cat_privilege[5]+
         "',cat_206='"+pre_cat_privilege[6]+"',cat_207='"+pre_cat_privilege[7]+"',cat_208='"+pre_cat_privilege[8]+
         "',cat_209='"+pre_cat_privilege[9]+"',cat_210='"+pre_cat_privilege[10]+"',cat_211='"+pre_cat_privilege[11]+
         "',cat_212='"+pre_cat_privilege[12]+"',cat_213='"+pre_cat_privilege[13]+"',cat_214='"+pre_cat_privilege[14]+
         "',cat_215='"+pre_cat_privilege[15]+"',cat_216='"+pre_cat_privilege[16]+"',cat_217='"+pre_cat_privilege[17]+
         "',cat_218='"+pre_cat_privilege[18]+"',cat_219='"+pre_cat_privilege[19]+"',cat_220='"+pre_cat_privilege[20]+
         "',cat_221='"+pre_cat_privilege[21]+"',cat_222='"+pre_cat_privilege[22]+"',cat_223='"+pre_cat_privilege[23]+
         "',cat_224='"+pre_cat_privilege[24]+"',cat_225='"+pre_cat_privilege[25]+"',cat_226='"+pre_cat_privilege[26]+
         "',cat_227='"+pre_cat_privilege[27]+"',cat_228='"+pre_cat_privilege[28]+"',cat_229='"+pre_cat_privilege[29]+
         "',cat_230='"+pre_cat_privilege[30]+"',cat_231='"+pre_cat_privilege[31]+"',cat_232='"+pre_cat_privilege[32]+
         "',cat_233='"+pre_cat_privilege[33]+"',cat_234='"+pre_cat_privilege[34]+"',cat_235='"+pre_cat_privilege[35]+
         "',cat_236='"+pre_cat_privilege[36]+"',cat_237='"+pre_cat_privilege[37]+"',cat_238='"+pre_cat_privilege[38]+
         "',cat_239='"+pre_cat_privilege[39]+"',cat_240='"+pre_cat_privilege[40]+"',cat_241='"+pre_cat_privilege[41]+
         "',cat_242='"+pre_cat_privilege[42]+"',cat_243='"+pre_cat_privilege[43]+"',cat_244='"+pre_cat_privilege[44]+
         "',cat_245='"+pre_cat_privilege[45]+"',cat_246='"+pre_cat_privilege[46]+"',cat_247='"+pre_cat_privilege[47]+
         "',cat_248='"+pre_cat_privilege[48]+"',cat_249='"+pre_cat_privilege[49]+"',cat_250='"+pre_cat_privilege[50]+
         "',cat_251='"+pre_cat_privilege[51]+"',cat_252='"+pre_cat_privilege[52]+"',cat_253='"+pre_cat_privilege[53]+
         "',cat_254='"+pre_cat_privilege[54]+"',cat_255='"+pre_cat_privilege[55]+"',cat_256='"+pre_cat_privilege[56]+
         "',cat_257='"+pre_cat_privilege[57]+"',cat_258='"+pre_cat_privilege[58]+"',cat_259='"+pre_cat_privilege[59]+
         "',cat_260='"+pre_cat_privilege[61]+"',cat_261='"+pre_cat_privilege[61]+"',cat_262='"+pre_cat_privilege[62]+
         "',cat_263='"+pre_cat_privilege[63]+"',cat_264='"+pre_cat_privilege[64]+"',cat_265='"+pre_cat_privilege[65]+
         "',cat_266='"+pre_cat_privilege[66]+"',cat_267='"+pre_cat_privilege[67]+"',cat_268='"+pre_cat_privilege[68]+
         "',cat_269='"+pre_cat_privilege[69]+"',cat_270='"+pre_cat_privilege[70]+"',cat_271='"+pre_cat_privilege[71]+
         "',cat_272='"+pre_cat_privilege[72]+"',cat_273='"+pre_cat_privilege[73]+"',cat_274='"+pre_cat_privilege[74]+
         "',cat_275='"+pre_cat_privilege[75]+"',cat_276='"+pre_cat_privilege[76]+"',cat_277='"+pre_cat_privilege[77]+
         "',cat_278='"+pre_cat_privilege[78]+"',cat_279='"+pre_cat_privilege[79]+"',cat_280='"+pre_cat_privilege[80]+
         "',cat_281='"+pre_cat_privilege[81]+"',cat_282='"+pre_cat_privilege[82]+"',cat_283='"+pre_cat_privilege[83]+
         "',cat_284='"+pre_cat_privilege[84]+"',cat_285='"+pre_cat_privilege[85]+"',cat_286='"+pre_cat_privilege[87]+
         "',cat_287='"+pre_cat_privilege[87]+"',cat_288='"+pre_cat_privilege[88]+"',cat_289='"+pre_cat_privilege[89]+
         "',cat_290='"+pre_cat_privilege[90]+"',cat_291='"+pre_cat_privilege[91]+
         "',cat_292='"+pre_cat_privilege[92]+"',cat_293='"+pre_cat_privilege[93]+"',cat_294='"+pre_cat_privilege[94]+
         "',cat_295='"+pre_cat_privilege[95]+"',cat_296='"+pre_cat_privilege[96]+"',cat_297='"+pre_cat_privilege[97]+
         "',cat_298='"+pre_cat_privilege[98]+"',cat_299='"+pre_cat_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    pre_sql4="update  cir_privilege set cir_301='"+pre_cir_privilege[1]+"',cir_302='"+pre_cir_privilege[2]+
         "',cir_303='"+pre_cir_privilege[3]+"',cir_304='"+pre_cir_privilege[4]+"',cir_305='"+pre_cir_privilege[5]+
         "',cir_306='"+pre_cir_privilege[6]+"',cir_307='"+pre_cir_privilege[7]+"',cir_308='"+pre_cir_privilege[8]+
         "',cir_309='"+pre_cir_privilege[9]+"',cir_310='"+pre_cir_privilege[10]+"',cir_311='"+pre_cir_privilege[11]+
         "',cir_312='"+pre_cir_privilege[12]+"',cir_313='"+pre_cir_privilege[13]+"',cir_314='"+pre_cir_privilege[14]+
         "',cir_315='"+pre_cir_privilege[15]+"',cir_316='"+pre_cir_privilege[16]+"',cir_317='"+pre_cir_privilege[17]+
         "',cir_318='"+pre_cir_privilege[18]+"',cir_319='"+pre_cir_privilege[19]+"',cir_320='"+pre_cir_privilege[20]+
         "',cir_321='"+pre_cir_privilege[21]+"',cir_322='"+pre_cir_privilege[22]+"',cir_323='"+pre_cir_privilege[23]+
         "',cir_324='"+pre_cir_privilege[24]+"',cir_325='"+pre_cir_privilege[25]+"',cir_326='"+pre_cir_privilege[26]+
         "',cir_327='"+pre_cir_privilege[27]+"',cir_328='"+pre_cir_privilege[28]+"',cir_329='"+pre_cir_privilege[29]+
         "',cir_330='"+pre_cir_privilege[30]+"',cir_331='"+pre_cir_privilege[31]+"',cir_332='"+pre_cir_privilege[32]+
         "',cir_333='"+pre_cir_privilege[33]+"',cir_334='"+pre_cir_privilege[34]+"',cir_335='"+pre_cir_privilege[35]+
         "',cir_336='"+pre_cir_privilege[36]+"',cir_337='"+pre_cir_privilege[37]+"',cir_338='"+pre_cir_privilege[38]+
         "',cir_339='"+pre_cir_privilege[39]+"',cir_340='"+pre_cir_privilege[40]+"',cir_341='"+pre_cir_privilege[41]+
         "',cir_342='"+pre_cir_privilege[42]+"',cir_343='"+pre_cir_privilege[43]+"',cir_344='"+pre_cir_privilege[44]+
         "',cir_345='"+pre_cir_privilege[45]+"',cir_346='"+pre_cir_privilege[46]+"',cir_347='"+pre_cir_privilege[47]+
         "',cir_348='"+pre_cir_privilege[48]+"',cir_349='"+pre_cir_privilege[49]+"',cir_350='"+pre_cir_privilege[50]+
         "',cir_351='"+pre_cir_privilege[51]+"',cir_352='"+pre_cir_privilege[52]+"',cir_353='"+pre_cir_privilege[53]+
         "',cir_354='"+pre_cir_privilege[54]+"',cir_355='"+pre_cir_privilege[55]+"',cir_356='"+pre_cir_privilege[56]+
         "',cir_357='"+pre_cir_privilege[57]+"',cir_358='"+pre_cir_privilege[58]+"',cir_359='"+pre_cir_privilege[59]+
         "',cir_360='"+pre_cir_privilege[60]+"',cir_361='"+pre_cir_privilege[61]+"',cir_362='"+pre_cir_privilege[62]+
         "',cir_363='"+pre_cir_privilege[63]+"',cir_364='"+pre_cir_privilege[64]+"',cir_365='"+pre_cir_privilege[65]+
         "',cir_366='"+pre_cir_privilege[66]+"',cir_367='"+pre_cir_privilege[67]+"',cir_368='"+pre_cir_privilege[68]+
         "',cir_369='"+pre_cir_privilege[69]+"',cir_370='"+pre_cir_privilege[70]+"',cir_371='"+pre_cir_privilege[71]+
         "',cir_372='"+pre_cir_privilege[72]+"',cir_373='"+pre_cir_privilege[73]+"',cir_374='"+pre_cir_privilege[74]+
         "',cir_375='"+pre_cir_privilege[75]+"',cir_376='"+pre_cir_privilege[76]+"',cir_377='"+pre_cir_privilege[77]+
         "',cir_378='"+pre_cir_privilege[78]+"',cir_379='"+pre_cir_privilege[79]+"',cir_380='"+pre_cir_privilege[80]+
         "',cir_381='"+pre_cir_privilege[81]+"',cir_382='"+pre_cir_privilege[82]+"',cir_383='"+pre_cir_privilege[83]+
         "',cir_384='"+pre_cir_privilege[84]+"',cir_385='"+pre_cir_privilege[85]+"',cir_386='"+pre_cir_privilege[86]+
         "',cir_387='"+pre_cir_privilege[87]+"',cir_388='"+pre_cir_privilege[88]+
         "',cir_389='"+pre_cir_privilege[89]+"',cir_390='"+pre_cir_privilege[90]+"',cir_391='"+pre_cir_privilege[91]+
         "',cir_392='"+pre_cir_privilege[92]+"',cir_393='"+pre_cir_privilege[93]+"',cir_394='"+pre_cir_privilege[94]+
         "',cir_395='"+pre_cir_privilege[95]+"',cir_396='"+pre_cir_privilege[96]+"',cir_397='"+pre_cir_privilege[97]+
         "',cir_398='"+pre_cir_privilege[98]+"',cir_399='"+pre_cir_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    pre_sql5="update ser_privilege set ser_401='"+pre_ser_privilege[1]+"',ser_402='"+pre_ser_privilege[2]+
         "',ser_403='"+pre_ser_privilege[3]+"',ser_404='"+pre_ser_privilege[4]+"',ser_405='"+pre_ser_privilege[5]+
         "',ser_406='"+pre_ser_privilege[6]+"',ser_407='"+pre_ser_privilege[7]+"',ser_408='"+pre_ser_privilege[8]+
         "',ser_409='"+pre_ser_privilege[9]+"',ser_410='"+pre_ser_privilege[10]+"',ser_411='"+pre_ser_privilege[11]+
         "',ser_412='"+pre_ser_privilege[12]+"',ser_413='"+pre_ser_privilege[13]+"',ser_414='"+pre_ser_privilege[14]+
         "',ser_415='"+pre_ser_privilege[15]+"',ser_416='"+pre_ser_privilege[16]+"',ser_417='"+pre_ser_privilege[17]+
         "',ser_418='"+pre_ser_privilege[18]+"',ser_419='"+pre_ser_privilege[19]+"',ser_420='"+pre_ser_privilege[20]+
         "',ser_421='"+pre_ser_privilege[21]+"',ser_422='"+pre_ser_privilege[22]+"',ser_423='"+pre_ser_privilege[23]+
         "',ser_424='"+pre_ser_privilege[24]+"',ser_425='"+pre_ser_privilege[25]+"',ser_426='"+pre_ser_privilege[26]+
         "',ser_427='"+pre_ser_privilege[27]+"',ser_428='"+pre_ser_privilege[28]+"',ser_429='"+pre_ser_privilege[29]+
         "',ser_430='"+pre_ser_privilege[30]+"',ser_431='"+pre_ser_privilege[31]+"',ser_432='"+pre_ser_privilege[32]+
         "',ser_433='"+pre_ser_privilege[33]+"',ser_434='"+pre_ser_privilege[34]+"',ser_435='"+pre_ser_privilege[35]+
         "',ser_436='"+pre_ser_privilege[36]+"',ser_437='"+pre_ser_privilege[37]+"',ser_438='"+pre_ser_privilege[38]+
         "',ser_439='"+pre_ser_privilege[39]+"',ser_440='"+pre_ser_privilege[40]+"',ser_441='"+pre_ser_privilege[41]+
         "',ser_442='"+pre_ser_privilege[42]+"',ser_443='"+pre_ser_privilege[43]+"',ser_444='"+pre_ser_privilege[44]+
         "',ser_445='"+pre_ser_privilege[45]+"',ser_446='"+pre_ser_privilege[46]+"',ser_447='"+pre_ser_privilege[47]+
         "',ser_448='"+pre_ser_privilege[48]+"',ser_449='"+pre_ser_privilege[49]+"',ser_450='"+pre_ser_privilege[50]+
         "',ser_451='"+pre_ser_privilege[51]+"',ser_452='"+pre_ser_privilege[52]+"',ser_453='"+pre_ser_privilege[53]+
         "',ser_454='"+pre_ser_privilege[54]+"',ser_455='"+pre_ser_privilege[55]+"',ser_456='"+pre_ser_privilege[56]+
         "',ser_457='"+pre_ser_privilege[57]+"',ser_458='"+pre_ser_privilege[58]+"',ser_459='"+pre_ser_privilege[59]+
         "',ser_460='"+pre_ser_privilege[60]+"',ser_461='"+pre_ser_privilege[61]+"',ser_462='"+pre_ser_privilege[62]+
         "',ser_463='"+pre_ser_privilege[63]+"',ser_464='"+pre_ser_privilege[64]+"',ser_465='"+pre_ser_privilege[65]+
         "',ser_466='"+pre_ser_privilege[66]+"',ser_467='"+pre_ser_privilege[67]+"',ser_468='"+pre_ser_privilege[68]+
         "',ser_469='"+pre_ser_privilege[69]+"',ser_470='"+pre_ser_privilege[70]+"',ser_471='"+pre_ser_privilege[71]+
         "',ser_472='"+pre_ser_privilege[72]+"',ser_473='"+pre_ser_privilege[73]+"',ser_474='"+pre_ser_privilege[74]+
         "',ser_475='"+pre_ser_privilege[75]+"',ser_476='"+pre_ser_privilege[76]+"',ser_477='"+pre_ser_privilege[77]+
         "',ser_478='"+pre_ser_privilege[78]+"',ser_479='"+pre_ser_privilege[79]+"',ser_480='"+pre_ser_privilege[80]+
         "',ser_481='"+pre_ser_privilege[81]+"',ser_482='"+pre_ser_privilege[82]+"',ser_483='"+pre_ser_privilege[83]+
         "',ser_484='"+pre_ser_privilege[84]+"',ser_485='"+pre_ser_privilege[85]+"',ser_486='"+pre_ser_privilege[86]+
         "',ser_487='"+pre_ser_privilege[87]+"',ser_488='"+pre_ser_privilege[88]+
         "',ser_489='"+pre_ser_privilege[89]+"',ser_490='"+pre_ser_privilege[90]+"',ser_491='"+pre_ser_privilege[91]+
         "',ser_492='"+pre_ser_privilege[92]+"',ser_493='"+pre_ser_privilege[93]+"',ser_494='"+pre_ser_privilege[94]+
         "',ser_495='"+pre_ser_privilege[95]+"',ser_496='"+pre_ser_privilege[96]+"',ser_497='"+pre_ser_privilege[97]+
         "',ser_498='"+pre_ser_privilege[98]+"',ser_499='"+pre_ser_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";
//System.out.println(sql1);
//System.out.println(sql2);
//System.out.println(sql3);
//System.out.println(sql4);
//System.out.println(sql5);
   MyQueryResult.getMyExecuteUpdate(pre_sql1);
   MyQueryResult.getMyExecuteUpdate(pre_sql2);
   MyQueryResult.getMyExecuteUpdate(pre_sql3);
   MyQueryResult.getMyExecuteUpdate(pre_sql4);
   MyQueryResult.getMyExecuteUpdate(pre_sql5);

   }
*/
}
