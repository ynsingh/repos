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
public class SerPrivilegeDAO {


   static  Integer maxNewRegId;
   static Query query;

public static String getValue(SerPrivilege ser,int i) {
      Session session = HibernateUtil.getSessionFactory().openSession();



        try
        {
         session.beginTransaction();
           if(i==401)
                return ser.getSer401();
            if(i==402)
                return ser.getSer402();
         if(i==403)
                return ser.getSer403();
         if(i==404)
                return ser.getSer404();
         if(i==405)
                return ser.getSer405();
         if(i==406)
                return ser.getSer406();
         if(i==407)
                return ser.getSer407();
         if(i==408)
                return ser.getSer408();
         if(i==409)
                return ser.getSer409();
         if(i==410)
                return ser.getSer410();
         if(i==411)
                return ser.getSer411();
         if(i==412)
                return ser.getSer412();
         if(i==413)
                return ser.getSer413();
         if(i==414)
                return ser.getSer414();
         if(i==415)
                return ser.getSer415();
         if(i==416)
                return ser.getSer416();
         if(i==417)
                return ser.getSer417();
         if(i==418)
                return ser.getSer418();
         if(i==419)
                return ser.getSer419();
         if(i==420)
                return ser.getSer420();
         if(i==421)
                return ser.getSer421();
         if(i==422)
                return ser.getSer422();
         if(i==423)
                return ser.getSer423();
         if(i==424)
                return ser.getSer424();
         if(i==425)
                return ser.getSer425();
         if(i==426)
                return ser.getSer426();
         if(i==427)
                return ser.getSer427();
         if(i==428)
                return ser.getSer428();
         if(i==429)
                return ser.getSer429();
         if(i==430)
                return ser.getSer430();
         if(i==431)
                return ser.getSer431();
         if(i==432)
                return ser.getSer432();
         if(i==433)
                return ser.getSer433();
         if(i==434)
                return ser.getSer434();
         if(i==435)
                return ser.getSer435();
         if(i==436)
                return ser.getSer436();
         if(i==437)
                return ser.getSer437();
         if(i==438)
                return ser.getSer438();
         if(i==439)
                return ser.getSer439();
         if(i==440)
                return ser.getSer440();
         if(i==441)
                return ser.getSer441();
         if(i==442)
                return ser.getSer442();
         if(i==443)
                return ser.getSer443();
         if(i==444)
                return ser.getSer444();
         if(i==445)
                return ser.getSer445();
         if(i==446)
                return ser.getSer446();
         if(i==447)
                return ser.getSer447();
         if(i==448)
                return ser.getSer448();
         if(i==449)
                return ser.getSer449();
         if(i==450)
                return ser.getSer450();
         if(i==451)
                return ser.getSer451();
         if(i==452)
                return ser.getSer452();
         if(i==453)
                return ser.getSer453();
         if(i==454)
                return ser.getSer454();
         if(i==455)
                return ser.getSer455();
         if(i==456)
                return ser.getSer456();
         if(i==457)
                return ser.getSer457();
         if(i==458)
                return ser.getSer458();
         if(i==459)
                return ser.getSer459();
         if(i==460)
                return ser.getSer460();
         if(i==461)
                return ser.getSer461();
         if(i==462)
                return ser.getSer462();
         if(i==463)
                return ser.getSer463();
         if(i==464)
                return ser.getSer464();
         if(i==465)
                return ser.getSer465();
         if(i==466)
                return ser.getSer466();
         if(i==467)
                return ser.getSer467();
         if(i==468)
                return ser.getSer468();
         if(i==469)
                return ser.getSer469();
         if(i==470)
                return ser.getSer470();
         if(i==471)
                return ser.getSer471();
         if(i==472)
                return ser.getSer472();
         if(i==473)
                return ser.getSer473();
         if(i==474)
                return ser.getSer474();
         if(i==475)
                return ser.getSer475();
         if(i==476)
                return ser.getSer476();
         if(i==477)
                return ser.getSer477();
         if(i==478)
                return ser.getSer478();
         if(i==479)
                return ser.getSer479();
         if(i==480)
                return ser.getSer480();
         if(i==481)
                return ser.getSer481();
         if(i==482)
                return ser.getSer482();
         if(i==483)
                return ser.getSer483();
         if(i==484)
                return ser.getSer484();
         if(i==485)
                return ser.getSer485();
         if(i==486)
                return ser.getSer486();
         if(i==487)
                return ser.getSer487();
         if(i==488)
                return ser.getSer488();
         if(i==489)
                return ser.getSer489();
         if(i==490)
                return ser.getSer490();
         if(i==491)
                return ser.getSer491();
         if(i==492)
                return ser.getSer492();
         if(i==493)
                return ser.getSer493();
         if(i==494)
                return ser.getSer494();
         if(i==495)
                return ser.getSer495();
         if(i==496)
                return ser.getSer496();
         if(i==497)
                return ser.getSer497();
         if(i==498)
                return ser.getSer498();
         if(i==499)
                return ser.getSer499();








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
  public static  boolean update(SerPrivilege obj)
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

public static SerPrivilege searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
         query = session.createQuery("FROM  SerPrivilege WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            return ( SerPrivilege) query.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

  

public static  boolean insert(SerPrivilege obj)
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
 public static  SerPrivilege getPrivilege(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  SerPrivilege  WHERE id.libraryId =:library_id and sublibraryId =:sublibrary_id and id.staffId =:staff_id");
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
        return ( SerPrivilege) query.uniqueResult();

}
public static boolean DeleteStaff(String staff_id,String library_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  SerPrivilege where id.libraryId =:library_id and id.staffId =:staff_id ");
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
public static boolean DeleteLogin(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
        query = session.createQuery("Delete From  SerPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
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

public static  List getPrivilege1(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

        try
        {
            session.beginTransaction();
            query = session.createSQLQuery("select * from ser_privilege  WHERE library_id =:library_id and sublibrary_id =:sublibrary_id and staff_id =:staff_id");
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
