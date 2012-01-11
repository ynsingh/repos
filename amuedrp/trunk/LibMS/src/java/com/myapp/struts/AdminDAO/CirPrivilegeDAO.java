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
public class CirPrivilegeDAO {


   static  Integer maxNewRegId;
   static Query query;
   public static String getValue(CirPrivilege cir,int i) {
      Session session = HibernateUtil.getSessionFactory().openSession();



        try
        {
         session.beginTransaction();
           if(i==301)
                return cir.getCir301();
            if(i==302)
                return cir.getCir302();
         if(i==303)
                return cir.getCir303();
         if(i==304)
                return cir.getCir304();
         if(i==305)
                return cir.getCir305();
         if(i==306)
                return cir.getCir306();
         if(i==307)
                return cir.getCir307();
         if(i==308)
                return cir.getCir308();
         if(i==309)
                return cir.getCir309();
         if(i==310)
                return cir.getCir310();
         if(i==311)
                return cir.getCir311();
         if(i==312)
                return cir.getCir312();
         if(i==313)
                return cir.getCir313();
         if(i==314)
                return cir.getCir314();
         if(i==315)
                return cir.getCir315();
         if(i==316)
                return cir.getCir316();
         if(i==317)
                return cir.getCir317();
         if(i==318)
                return cir.getCir318();
         if(i==319)
                return cir.getCir319();
         if(i==320)
                return cir.getCir320();
         if(i==321)
                return cir.getCir321();
         if(i==322)
                return cir.getCir322();
         if(i==323)
                return cir.getCir323();
         if(i==324)
                return cir.getCir324();
         if(i==325)
                return cir.getCir325();
         if(i==326)
                return cir.getCir326();
         if(i==327)
                return cir.getCir327();
         if(i==328)
                return cir.getCir328();
         if(i==329)
                return cir.getCir329();
         if(i==330)
                return cir.getCir330();
         if(i==331)
                return cir.getCir331();
         if(i==332)
                return cir.getCir332();
         if(i==333)
                return cir.getCir333();
         if(i==334)
                return cir.getCir334();
         if(i==335)
                return cir.getCir335();
         if(i==336)
                return cir.getCir336();
         if(i==337)
                return cir.getCir337();
         if(i==338)
                return cir.getCir338();
         if(i==339)
                return cir.getCir339();
         if(i==340)
                return cir.getCir340();
         if(i==341)
                return cir.getCir341();
         if(i==342)
                return cir.getCir342();
         if(i==343)
                return cir.getCir343();
         if(i==344)
                return cir.getCir344();
         if(i==345)
                return cir.getCir345();
         if(i==346)
                return cir.getCir346();
         if(i==347)
                return cir.getCir347();
         if(i==348)
                return cir.getCir348();
         if(i==349)
                return cir.getCir349();
         if(i==350)
                return cir.getCir350();
         if(i==351)
                return cir.getCir351();
         if(i==352)
                return cir.getCir352();
         if(i==353)
                return cir.getCir353();
         if(i==354)
                return cir.getCir354();
         if(i==355)
                return cir.getCir355();
         if(i==356)
                return cir.getCir356();
         if(i==357)
                return cir.getCir357();
         if(i==358)
                return cir.getCir358();
         if(i==359)
                return cir.getCir359();
         if(i==360)
                return cir.getCir360();
         if(i==361)
                return cir.getCir361();
         if(i==362)
                return cir.getCir362();
         if(i==363)
                return cir.getCir363();
         if(i==364)
                return cir.getCir364();
         if(i==365)
                return cir.getCir365();
         if(i==366)
                return cir.getCir366();
         if(i==367)
                return cir.getCir367();
         if(i==368)
                return cir.getCir368();
         if(i==369)
                return cir.getCir369();
         if(i==370)
                return cir.getCir370();
         if(i==371)
                return cir.getCir371();
         if(i==372)
                return cir.getCir372();
         if(i==373)
                return cir.getCir373();
         if(i==374)
                return cir.getCir374();
         if(i==375)
                return cir.getCir375();
         if(i==376)
                return cir.getCir376();
         if(i==377)
                return cir.getCir377();
         if(i==378)
                return cir.getCir378();
         if(i==379)
                return cir.getCir379();
         if(i==380)
                return cir.getCir380();
         if(i==381)
                return cir.getCir381();
         if(i==382)
                return cir.getCir382();
         if(i==383)
                return cir.getCir383();
         if(i==384)
                return cir.getCir384();
         if(i==385)
                return cir.getCir385();
         if(i==386)
                return cir.getCir386();
         if(i==387)
                return cir.getCir387();
         if(i==388)
                return cir.getCir388();
         if(i==389)
                return cir.getCir389();
         if(i==390)
                return cir.getCir390();
         if(i==391)
                return cir.getCir391();
         if(i==392)
                return cir.getCir392();
         if(i==393)
                return cir.getCir393();
         if(i==394)
                return cir.getCir394();
         if(i==395)
                return cir.getCir395();
         if(i==396)
                return cir.getCir396();
         if(i==397)
                return cir.getCir397();
         if(i==398)
                return cir.getCir398();
         if(i==399)
                return cir.getCir399();








        }
        catch (Exception ex)
        {


        System.out.println(ex.toString());

        }
        finally
        {
          session.close();
         
        }

return "";

}
public static boolean DeleteLogin(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
             query = session.createQuery("Delete From  CirPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

              query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
              System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
        session.close();
        }
   return true;


}
   public static  boolean update(CirPrivilege obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (Exception e) {

                tx.rollback();
                System.out.println(e);
                return false;

        }
        finally{
        session.close();
        }

   return true;

}


public static CirPrivilege searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
CirPrivilege cirobj=null;
        try {
            session.beginTransaction();
        query = session.createQuery("FROM  CirPrivilege WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            cirobj=( CirPrivilege) query.uniqueResult();
        }
         catch (Exception e) {

               
                System.out.println(e);

                

        }
        finally {
        session.close();
        }
return cirobj;
}
  


  

public static  boolean insert(CirPrivilege obj)
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
              System.out.println(ex.toString());
             return false;

       

        }
        finally
        {
        HibernateUtil.getSessionFactory().close();
        }
   return true;

}
 public static  CirPrivilege getPrivilege(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirPrivilege cirobj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  CirPrivilege  WHERE id.libraryId =:library_id and sublibraryId =:sublibrary_id and id.staffId =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
cirobj=( CirPrivilege) query.uniqueResult();
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
        session.close();
        }
        return cirobj;

}
public static boolean DeleteStaff(String staff_id,String library_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  CirPrivilege where id.libraryId =:library_id and id.staffId =:staff_id ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);


            query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            tx.rollback();
            System.out.println(ex);
             return false;

       

        }
        finally
        {
       session.close();
        }
   return true;


}
public static  List getPrivilege1(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List obj=null;
        try
        {
            session.beginTransaction();
            query = session.createSQLQuery("select * from cir_privilege  WHERE library_id =:library_id and sublibrary_id =:sublibrary_id and staff_id =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setResultTransformer(Transformers.TO_LIST);
            obj=( List) query.list();
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
