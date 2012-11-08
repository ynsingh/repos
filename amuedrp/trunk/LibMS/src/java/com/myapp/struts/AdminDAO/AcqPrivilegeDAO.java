/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
/**
 * Developed By : Kedar Kumar
 * Modified By  : 21-Feb-2011
 * Use to Check the Multiple occurance of Library_Id in Library POJO
 */
public class AcqPrivilegeDAO {


      Integer maxNewRegId;
     Query query;
public   boolean DeleteLogin(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            query = session.createQuery("Delete From  AcqPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

              query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
             
        tx.rollback();
         System.out.println(ex.toString());
         return false;

        }
        finally
        {
     session.close();
        }
   return true;


}
public   String getValue(AcqPrivilege acq,int i) {
      Session session = HibernateUtil.getSessionFactory().openSession();



        try
        {
         session.beginTransaction();

           if(i==101)
                return acq.getAcq101();
            if(i==102)
                return acq.getAcq102();
         if(i==103)
                return acq.getAcq103();
         if(i==104)
                return acq.getAcq104();
         if(i==105)
                return acq.getAcq105();
         if(i==106)
                return acq.getAcq106();
         if(i==107)
                return acq.getAcq107();
         if(i==108)
                return acq.getAcq108();
         if(i==109)
                return acq.getAcq109();
         if(i==110)
                return acq.getAcq110();
         if(i==111)
                return acq.getAcq111();
         if(i==112)
                return acq.getAcq112();
         if(i==113)
                return acq.getAcq113();
         if(i==114)
                return acq.getAcq114();
         if(i==115)
                return acq.getAcq115();
         if(i==116)
                return acq.getAcq116();
         if(i==117)
                return acq.getAcq117();
         if(i==118)
                return acq.getAcq118();
         if(i==119)
                return acq.getAcq119();
         if(i==120)
                return acq.getAcq120();
         if(i==121)
                return acq.getAcq121();
         if(i==122)
                return acq.getAcq122();
         if(i==123)
                return acq.getAcq123();
         if(i==124)
                return acq.getAcq124();
         if(i==125)
                return acq.getAcq125();
         if(i==126)
                return acq.getAcq126();
         if(i==127)
                return acq.getAcq127();
         if(i==128)
                return acq.getAcq128();
         if(i==129)
                return acq.getAcq129();
         if(i==130)
                return acq.getAcq130();
         if(i==131)
                return acq.getAcq131();
         if(i==132)
                return acq.getAcq132();
         if(i==133)
                return acq.getAcq133();
         if(i==134)
                return acq.getAcq134();
         if(i==135)
                return acq.getAcq135();
         if(i==136)
                return acq.getAcq136();
         if(i==137)
                return acq.getAcq137();
         if(i==138)
                return acq.getAcq138();
         if(i==139)
                return acq.getAcq139();
         if(i==140)
                return acq.getAcq140();
         if(i==141)
                return acq.getAcq141();
         if(i==142)
                return acq.getAcq142();
         if(i==143)
                return acq.getAcq143();
         if(i==144)
                return acq.getAcq144();
         if(i==145)
                return acq.getAcq145();
         if(i==146)
                return acq.getAcq146();
         if(i==147)
                return acq.getAcq147();
         if(i==148)
                return acq.getAcq148();
         if(i==149)
                return acq.getAcq149();
         if(i==150)
                return acq.getAcq150();
         if(i==151)
                return acq.getAcq151();
         if(i==152)
                return acq.getAcq152();
         if(i==153)
                return acq.getAcq153();
         if(i==154)
                return acq.getAcq154();
         if(i==155)
                return acq.getAcq155();
         if(i==156)
                return acq.getAcq156();
         if(i==157)
                return acq.getAcq157();
         if(i==158)
                return acq.getAcq158();
         if(i==159)
                return acq.getAcq159();
         if(i==160)
                return acq.getAcq160();
         if(i==161)
                return acq.getAcq161();
         if(i==162)
                return acq.getAcq162();
         if(i==163)
                return acq.getAcq163();
         if(i==164)
                return acq.getAcq164();
         if(i==165)
                return acq.getAcq165();
         if(i==166)
                return acq.getAcq166();
         if(i==167)
                return acq.getAcq167();
         if(i==168)
                return acq.getAcq168();
         if(i==169)
                return acq.getAcq169();
         if(i==170)
                return acq.getAcq170();
         if(i==171)
                return acq.getAcq171();
         if(i==172)
                return acq.getAcq172();
         if(i==173)
                return acq.getAcq173();
         if(i==174)
                return acq.getAcq174();
         if(i==175)
                return acq.getAcq175();
         if(i==176)
                return acq.getAcq176();
         if(i==177)
                return acq.getAcq177();
         if(i==178)
                return acq.getAcq178();
         if(i==179)
                return acq.getAcq179();
         if(i==180)
                return acq.getAcq180();
         if(i==181)
                return acq.getAcq181();
         if(i==182)
                return acq.getAcq182();
         if(i==183)
                return acq.getAcq183();
         if(i==184)
                return acq.getAcq184();
         if(i==185)
                return acq.getAcq185();
         if(i==186)
                return acq.getAcq186();
         if(i==187)
                return acq.getAcq187();
         if(i==188)
                return acq.getAcq188();
         if(i==189)
                return acq.getAcq189();
         if(i==190)
                return acq.getAcq190();
         if(i==191)
                return acq.getAcq191();
         if(i==192)
                return acq.getAcq192();
         if(i==193)
                return acq.getAcq193();
         if(i==194)
                return acq.getAcq194();
         if(i==195)
                return acq.getAcq195();
         if(i==196)
                return acq.getAcq196();
         if(i==197)
                return acq.getAcq197();
         if(i==198)
                return acq.getAcq198();
         if(i==199)
                return acq.getAcq199();



session.getTransaction().commit();




        }
        catch (Exception ex)
        {


         System.out.println(ex.toString());

        }
        finally
        {
         session.close();

        }

return null;

}
public    boolean update(AcqPrivilege obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (RuntimeException e) {

                tx.rollback();
                return false;

        }
        finally{
          session.close();
        }

   return true;

}

  
public   AcqPrivilege searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqPrivilege acqobj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  AcqPrivilege WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           acqobj=( AcqPrivilege) query.uniqueResult();
           session.getTransaction().commit();
        }
         catch (Exception ex)
        {

             System.out.println(ex.toString());
        }
        finally {
       session.close();
        }
 return acqobj;
}

  

public    boolean insert(AcqPrivilege obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
        catch (Exception ex)
        {
            tx.rollback();
             System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
          session.close();

        }
   return true;

}
public    AcqPrivilege getPrivilege(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqPrivilege acqobj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  AcqPrivilege  WHERE id.libraryId =:library_id and sublibraryId =:sublibrary_id and id.staffId =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
acqobj=( AcqPrivilege) query.uniqueResult();
session.getTransaction().commit();
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
         session.close();
        }
        return acqobj;

}
public   boolean DeleteStaff(String staff_id,String library_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            query = session.createQuery("Delete From  AcqPrivilege where id.libraryId =:library_id and id.staffId =:staff_id ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);


            query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            System.out.println(ex);
            tx.rollback();
             return false;

       

        }
        finally
        {
            session.close();
        }
   return true;


}

public    List getPrivilege1(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try
        {
            session.beginTransaction();
            query = session.createSQLQuery("select * from acq_privilege  WHERE library_id =:library_id and sublibrary_id =:sublibrary_id and staff_id =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setResultTransformer(Transformers.TO_LIST);
            obj= ( List) query.list();
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
            session.close();
        }
        return obj;

}

}
