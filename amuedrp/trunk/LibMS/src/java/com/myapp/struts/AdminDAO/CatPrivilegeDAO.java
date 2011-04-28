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
public class CatPrivilegeDAO {


   static  Integer maxNewRegId;
   static Query query;
   public static String getValue(CatPrivilege cat,int i) {
      Session session = HibernateUtil.getSessionFactory().openSession();



        try
        {
         session.beginTransaction();
           if(i==201)
                return cat.getCat201();
            if(i==202)
                return cat.getCat202();
         if(i==203)
                return cat.getCat203();
         if(i==204)
                return cat.getCat204();
         if(i==205)
                return cat.getCat205();
         if(i==206)
                return cat.getCat206();
         if(i==207)
                return cat.getCat207();
         if(i==208)
                return cat.getCat208();
         if(i==209)
                return cat.getCat209();
         if(i==210)
                return cat.getCat210();
         if(i==211)
                return cat.getCat211();
         if(i==212)
                return cat.getCat212();
         if(i==213)
                return cat.getCat213();
         if(i==214)
                return cat.getCat214();
         if(i==215)
                return cat.getCat215();
         if(i==216)
                return cat.getCat216();
         if(i==217)
                return cat.getCat217();
         if(i==218)
                return cat.getCat218();
         if(i==219)
                return cat.getCat219();
         if(i==220)
                return cat.getCat220();
         if(i==221)
                return cat.getCat221();
         if(i==222)
                return cat.getCat222();
         if(i==223)
                return cat.getCat223();
         if(i==224)
                return cat.getCat224();
         if(i==225)
                return cat.getCat225();
         if(i==226)
                return cat.getCat226();
         if(i==227)
                return cat.getCat227();
         if(i==228)
                return cat.getCat228();
         if(i==229)
                return cat.getCat229();
         if(i==230)
                return cat.getCat230();
         if(i==231)
                return cat.getCat231();
         if(i==232)
                return cat.getCat232();
         if(i==233)
                return cat.getCat233();
         if(i==234)
                return cat.getCat234();
         if(i==235)
                return cat.getCat235();
         if(i==236)
                return cat.getCat236();
         if(i==237)
                return cat.getCat237();
         if(i==238)
                return cat.getCat238();
         if(i==239)
                return cat.getCat239();
         if(i==240)
                return cat.getCat240();
         if(i==241)
                return cat.getCat241();
         if(i==242)
                return cat.getCat242();
         if(i==243)
                return cat.getCat243();
         if(i==244)
                return cat.getCat244();
         if(i==245)
                return cat.getCat245();
         if(i==246)
                return cat.getCat246();
         if(i==247)
                return cat.getCat247();
         if(i==248)
                return cat.getCat248();
         if(i==249)
                return cat.getCat249();
         if(i==250)
                return cat.getCat250();
         if(i==251)
                return cat.getCat251();
         if(i==252)
                return cat.getCat252();
         if(i==253)
                return cat.getCat253();
         if(i==254)
                return cat.getCat254();
         if(i==255)
                return cat.getCat255();
         if(i==256)
                return cat.getCat256();
         if(i==257)
                return cat.getCat257();
         if(i==258)
                return cat.getCat258();
         if(i==259)
                return cat.getCat259();
         if(i==260)
                return cat.getCat260();
         if(i==261)
                return cat.getCat261();
         if(i==262)
                return cat.getCat262();
         if(i==263)
                return cat.getCat263();
         if(i==264)
                return cat.getCat264();
         if(i==265)
                return cat.getCat265();
         if(i==266)
                return cat.getCat266();
         if(i==267)
                return cat.getCat267();
         if(i==268)
                return cat.getCat268();
         if(i==269)
                return cat.getCat269();
         if(i==270)
                return cat.getCat270();
         if(i==271)
                return cat.getCat271();
         if(i==272)
                return cat.getCat272();
         if(i==273)
                return cat.getCat273();
         if(i==274)
                return cat.getCat274();
         if(i==275)
                return cat.getCat275();
         if(i==276)
                return cat.getCat276();
         if(i==277)
                return cat.getCat277();
         if(i==278)
                return cat.getCat278();
         if(i==279)
                return cat.getCat279();
         if(i==280)
                return cat.getCat280();
         if(i==281)
                return cat.getCat281();
         if(i==282)
                return cat.getCat282();
         if(i==283)
                return cat.getCat283();
         if(i==284)
                return cat.getCat284();
         if(i==285)
                return cat.getCat285();
         if(i==286)
                return cat.getCat286();
         if(i==287)
                return cat.getCat287();
         if(i==288)
                return cat.getCat288();
         if(i==289)
                return cat.getCat289();
         if(i==290)
                return cat.getCat290();
         if(i==291)
                return cat.getCat291();
         if(i==292)
                return cat.getCat292();
         if(i==293)
                return cat.getCat293();
         if(i==294)
                return cat.getCat294();
         if(i==295)
                return cat.getCat295();
         if(i==296)
                return cat.getCat296();
         if(i==297)
                return cat.getCat297();
         if(i==298)
                return cat.getCat298();
         if(i==299)
                return cat.getCat299();








        }
        catch (Exception ex)
        {


       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }

return "";

}
public static boolean DeleteLogin(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            query = session.createQuery("Delete From  CatPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

              query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;


}
public static  boolean update(CatPrivilege obj)
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

   return true;

}

  

public static CatPrivilege searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CatPrivilege WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            return ( CatPrivilege) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}
  

public static  boolean insert(CatPrivilege obj)
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
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;

}
 public static  CatPrivilege getPrivilege(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  CatPrivilege  WHERE id.libraryId =:library_id and sublibraryId =:sublibrary_id and id.staffId =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);

        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
          //  session.close();
        }
        return ( CatPrivilege) query.uniqueResult();

}
public static boolean DeleteStaff(String staff_id,String library_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  CatPrivilege where id.libraryId =:library_id and id.staffId =:staff_id ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);


            query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            System.out.println(ex);
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
        // session.close();
        }
   return true;


}
public static  List getPrivilege1(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createSQLQuery("select * from cat_privilege  WHERE library_id =:library_id and sublibrary_id =:sublibrary_id and staff_id =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setResultTransformer(Transformers.TO_LIST);
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
          //  session.close();
        }
        return ( List) query.list();

}
}
