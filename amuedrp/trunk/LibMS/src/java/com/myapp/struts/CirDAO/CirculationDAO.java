/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.CirDAO;
import com.myapp.struts.circulation.*;
import com.myapp.struts.circulation.CirculationList;
import com.myapp.struts.circulation.CirculationList_1;

import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.opac.MemberFineWithCheckoutDetails;
import com.myapp.struts.utility.DateCalculation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.exception.SQLGrammarException;
/**
 *
 * @author edrp01
 */
public class CirculationDAO
{
   static  Integer maxNewRegId;
   static Query query;
 public static  Courses LoadCourseName(String library_id,String course_id,String dept_id,String faculty_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
Courses course=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Courses.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.courseId", course_id))
                    .add(Restrictions.eq("id.deptId", dept_id))
                    .add(Restrictions.eq("id.facultyId", faculty_id))


                    );
      course= (Courses) criteria.uniqueResult();
      session.getTransaction().commit();


        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return course;
}

  public static  List<CirMemberAccount> searchCirMemAccount2(String library_id,String sublibrary_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirMemberAccount> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("status", "Blocked"))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id)));
            obj=(List<CirMemberAccount>) criteria.list();
            session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}



    public static  List<CirMemberAccount> searchCirMemCancel(String library_id,String sublibrary_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirMemberAccount> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("status", "Cancel"))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id)));
           obj=  (List<CirMemberAccount>) criteria.list();
            session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
    public static  CirMemberAccount searchCirMemAccountDetails2(String library_id,String sublibrary_id,String mem_id,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirMemberAccount obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.memid", mem_id))
                    .add(Restrictions.eq("status",status)));
        obj= (CirMemberAccount) criteria.uniqueResult();


        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
        public static  List<DeliquencyReason> searchDelReason(String library_id,String sublibrary_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<DeliquencyReason> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DeliquencyReason.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
    );
            obj= (List<DeliquencyReason>) criteria.list();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

         public static  List<CancellationReason> searchCancelReason(String library_id,String sublibrary_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CancellationReason> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CancellationReason.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
    );
            obj= (List<CancellationReason>) criteria.list();
session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

          public static  CancellationReason searchCancelReason(String library_id,String sublibrary_id,String id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CancellationReason  obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CancellationReason.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.id", id))
    );
            obj= (CancellationReason) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

    public static  DeliquencyReason searchDeliReason(String library_id,String sublibrary_id,String id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
DeliquencyReason obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DeliquencyReason.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.id", id))
    );
            obj= (DeliquencyReason) criteria.uniqueResult();
session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static List  CheckInReport1(String library_id,String sub_lib,String year1,String year2,String memid)
    {
       List obj=null;
    Session session=null;
    
        
        try
        {
        
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "(select  a.member_id,concat(ca.title,' ',ca.main_entry) title,a.returning_date,b.fine_amt,concat(d.fname,' ',d.mname,' ',d.lname) fname,acc.req_date,acc.expiry_date,(select CONVERT(f.faculty_name using utf8) from faculty f where f.faculty_id=acc.faculty_id and acc.library_id=f.library_id) faculty_name,(select CONVERT(da.dept_name using utf8) from department da where acc.faculty_id=da.faculty_id and acc.library_id=da.library_id and acc.dept_id=da.dept_id) dept_name,(select CONVERT(c.course_name using utf8) from courses c where c.faculty_id=acc.faculty_id and c.library_id=acc.library_id and c.dept_id=acc.dept_id and c.course_id=acc.course_id) course_name from cir_checkin a,cir_transaction_history b,document_details ca,cir_member_detail d,cir_member_account acc where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.checkin_id=b.checkin_id and a.member_id=b.memid and b.library_id=ca.library_id and b.document_id=ca.document_id and a.library_id=d.library_id and a.member_id=d.`memId`and acc.library_id=d.library_id and acc.memid=d.memId ";
    
 if(!memid.equals("") && (!year1.equals(""))&& (!year2.equals("")))
{
     System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
              sql += "and a.member_id='"+memid+"' and a.returning_date>='"+year1+"' and a.returning_date<='"+year2+"'";

 }
  if(!memid.equals("") && (!year1.equals("")))
{
     System.out.println("ggggyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
              sql += " and a.member_id='"+memid+"' and a.returning_date>='"+year1+"'";

 }
 if(!memid.equals("") && (!year2.equals("")))
{
     System.out.println("gffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
              sql += " and a.member_id='"+memid+"' and  a.returning_date<='"+year2+"'";

 }
 if(!year1.equals("")&& (!year2.equals("")))
{
     System.out.println("ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
              sql += " and  a.returning_date>='"+year1+"' and a.returning_date<='"+year2+"'";

 }
 if(!year1.equals(""))
{
                 System.out.println("llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

              sql += " and a.returning_date >='"+year1+"'";

 }
 if(!year2.equals(""))
{
     System.out.println("gkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

              sql += " and a.returning_date <='"+year2+"'";

 }
 if(!memid.equals(""))
{
     System.out.println("gpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");

              sql += " and a.member_id ='"+memid+"'";

 }

sql+=" order by a.member_id)";

          Query query =  session.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(CirculationList_1.class));

          System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            obj= (List<CirculationList_1>)query.list();
session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}





   public static List  CheckoutReport1(String library_id,String sub_lib,String year1,String year2,String memid)
    {
    List obj=null;
     Session session=null;
     
     
        try
        {
        
          session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
      
String sql = "(select  cma.*,a.*,b.*,concat(ca.title,'/',ca.main_entry) title,ca.*,(select f.faculty_name from faculty f where f.faculty_id=cma.faculty_id and cma.library_id=f.library_id) faculty_name,(select da.dept_name from department da where cma.faculty_id=da.faculty_id and cma.library_id=da.library_id and cma.dept_id=da.dept_id) dept_name,(select c.course_name from courses c where c.faculty_id=cma.faculty_id and c.library_id=cma.library_id and c.dept_id=cma.dept_id and c.course_id=cma.course_id) course_name from document_details ca,cir_checkout a,cir_member_detail b,cir_member_account cma where" +
        " a.library_id=ca.library_id and a.sublibrary_id=ca.sublibrary_id and a.document_id=ca.document_id and " +
        "a.library_id=b.library_id and a.memid=b.memId and b.memId=cma.memid and b.library_id=cma.library_id ";
if(memid!=null)
               if(!memid.equals(""))
        {

                   sql+=" and a.memid='"+memid+"'";
               }

       if(year1!=null){
           if(!year1.equals(""))
         sql+=" and a.issue_date>='"+year1+"'";
         }

 if(year2!=null){
           if(!year2.equals(""))
         sql+=" and a.issue_date<='"+year2+"'";
         }

            sql+=" and a.status='issued' order by b.memId)";

          Query query =  session.createSQLQuery(sql)
                  .addEntity(CirCheckout.class)
                  .addEntity(CirMemberDetail.class)
                  .addEntity(CirMemberAccount.class)
                  .addEntity(DocumentDetails.class)
                   .addEntity(Faculty.class)
                  .addEntity(Department.class)
                  .addEntity(Courses.class)
                  ;

                  query.setResultTransformer(Transformers.aliasToBean(CirculationList.class));



          
          obj= (List<CirculationList>)query.list();
session.getTransaction().commit();




        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}


public static List  ViewAllSearchReport(String library_id,String sub_lib,String year1,String year2,String memid,String status,String fac,String dept,String course,String title,String login_id)
    {
        List obj=null;
    
    
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
    
         hsession.beginTransaction();

         String sql="";



            if(memid==null)
                 memid = "";
            
            if(fac==null || fac.equals("Select"))
                fac="";
            if(dept==null || dept.equals("Select"))
                dept="";
            if(course==null || course.equals("Select"))
                course="";
            if(status==null)
                status="";
            if(sub_lib==null)
                sub_lib = "";
            if(year1==null)
                year1="";
            if(year2==null)
                year2="";
            if(title==null)
                title="";
System.out.println("its working2");
//status = status.toLowerCase();
            sql = "select lb.library_name,l.user_name,a.memId,concat(a.fname,' ',a.mname,' ',a.lname) fname,a.email,b.req_date,b.expiry_date,b.sublibrary_id,b.library_id,sb.sublib_name,"+
" (select CONVERT(f.faculty_name using utf8) from faculty f where f.faculty_id=b.faculty_id and f.library_id=b.library_id) faculty_name,"+
 " (select CONVERT(d.dept_name using utf8) from department d where d.faculty_id=b.faculty_id and d.library_id=b.library_id and d.dept_id=b.dept_id) dept_name,"+
 " (select CONVERT(c.course_name using utf8) from courses c where c.faculty_id=b.faculty_id and c.library_id=b.library_id and c.dept_id=b.dept_id"+
" and c.course_id=b.course_id) course_name"+
  " from cir_member_detail a,cir_member_account b,login l,library lb,sub_library sb   where a.memId=b.memid and a.library_id=b.library_id"+
 " and l.library_id='"+library_id+"' and l.login_id='"+login_id+"' and b.library_id=lb.library_id and b.library_id=sb.library_id and b.sublibrary_id=sb.sublibrary_id";

            if(memid.equals("") && fac.equals("") && dept.equals("") && course.equals("") && year1.equals("") && year2.equals("") && sub_lib.equals(""))
            {

            }
            else{
            if(!memid.equals(""))
                 sql += " and b.memid>='"+ memid +"'";
           
            if(!fac.equals(""))
                sql += " and b.faculty_id='"+ fac +"'";
            if(!dept.equals(""))
                sql += " and b.dept_id='"+ dept +"'";
            if(!course.equals(""))
                sql += " and b.course_id='"+ course +"'";
            if(!status.equals("All"))
                sql += " and b.status='"+ status +"'";
            if(!sub_lib.equals("All"))
                sql += " and b.sublibrary_id='"+ sub_lib +"'";
            if(!year1.equals(""))
                sql += " and b.req_date>='"+ year1 +"'" ;
            if(!year2.equals(""))
                sql += " and b.req_date<='"+ year2 +"'";

            if(!title.equals(""))
                if(title.equalsIgnoreCase("memid"))
                    sql += " order By memId asc";
                else if(title.equalsIgnoreCase("firstname"))
                    sql += " order By fname asc";
                else if(title.equalsIgnoreCase("registerdate"))
                    sql+=" order By req_date asc";
            else if(title.equalsIgnoreCase("expirydate"))
                    sql+=" order By expiry_date asc";
             else
                    sql += " order By "+ title +" asc";
            }
          

          Query query =  hsession.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(MixCirMemberDetail.class));

         
            obj= (List<MixCirMemberDetail>)query.list();
hsession.getTransaction().commit();
        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           hsession.close();
        }
        return obj;
}


 public static  List<Faculty> LoadFaculty(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<Faculty> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Faculty.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                 

                    );
obj= (List<Faculty>) criteria.list();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
 public static  List<Department> LoadDepartment(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<Department> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Department.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                 


                    );
            obj= (List<Department>) criteria.list();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}


 public static  List<Courses> LoadCourses(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<Courses> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Courses.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                  


                    );
            obj= (List<Courses>) criteria.list();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

 public static  List<SubLibrary> LoadSublibrary(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SubLibrary.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                


                    );
            obj= (List<SubLibrary>) criteria.list();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}



   public static  boolean insertCirCheckout(CirCheckout obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch(Exception e){
        e.printStackTrace();
        return false;

        }
        finally
        {
           session.close();
        }
        return true;
}
   public static  boolean insertDelinquencyout(DeliquencyReason obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
                e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}

    public static  boolean insertCancellation(CancellationReason obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
             e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}

public static  boolean insertCirTransHistory(CirTransactionHistory obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
             e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}



   public static  CirCheckin searchCheckinMemDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirCheckin obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckin.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    .add(Restrictions.eq("memberId", mem_id)));
            obj= (CirCheckin) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

   public static  CirCheckin searchCheckinMemDetails(String library_id,String sublibrary_id,String mem_id,String checkIn)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirCheckin obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckin.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    .add(Restrictions.eq("memberId", mem_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.checkinId", checkIn))


                    );
            obj=(CirCheckin) criteria.uniqueResult();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static List  ViewAllThoughOpacReq(String library_id,String sub_lib,String year3,String year4,String memid,String fac,String dept,String course,String title,String login_id)
    {
        List obj=new ArrayList<MixCirMemberDetail>();
    Session session=null;
    
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
        

         String sql="";



            if(memid==null)
                 memid = "";
            if(fac==null || fac.equals("Select"))
                fac="";
            if(dept==null || dept.equals("Select"))
                dept="";
            if(course==null || course.equals("Select"))
                course="";
          
            if(sub_lib==null)
                sub_lib = "";
            if(year3==null)
                year3="";
            if(year4==null)
                year4="";
            if(title==null)
                title="";


            sql = "select (select CONVERT(l.library_name using utf8) from library l where l.library_id='"+library_id+"') library_name,a.memId,concat(a.fname,' ',a.mname,' ',a.lname) fname,a.library_id,a.email,a.requestdate,a.status,a.sublibrary_id,(select CONVERT(l.user_name using utf8) from login l where l.library_id='"+library_id+"' and l.login_id='"+login_id+"') user_name,(select CONVERT(f.faculty_name using utf8) from faculty f where f.faculty_id=a.faculty_id and a.library_id=f.library_id) faculty_name,(select CONVERT(d.dept_name using utf8) from department d where d.faculty_id=a.faculty_id and d.library_id=a.library_id and d.dept_id=a.dept_id) dept_name,(select CONVERT(cb.course_name using utf8) from courses cb where cb.faculty_id=a.faculty_id and cb.library_id=a.library_id and cb.dept_id=a.dept_id and cb.course_id=a.course) course_name,(select CONVERT(b.sublib_name using utf8) from sub_library b where a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id) sublib_name from cir_requestfrom_opac a where a.library_id='"+library_id+"'";


            if(!memid.equals(""))
                 sql += " and a.memid='"+ memid +"'";
            if(!fac.equals(""))
                sql += " and a.faculty_id='"+ fac +"'";
            if(!dept.equals(""))
                sql += " and a.dept_id='"+ dept +"'";
            if(!course.equals(""))
                sql += " and a.course_id='"+ course +"'";
            
            if(!sub_lib.equals("All"))
                sql += " and a.sublibrary_id='"+ sub_lib +"'";
            if(!year3.equals(""))
                sql += " and a.requestdate>='"+ year3 +"'" ;
            if(!year4.equals(""))
                sql += " and a.requestdate<='"+ year4 +"'";

            sql+=" and a.status='UnApproved' ";

            if(!title.equals(""))
                if(title.equalsIgnoreCase("memid"))
                    sql += " order By a.memId asc";
                else if(title.equalsIgnoreCase("firstname"))
                    sql += " order By a.fname asc";
                else if(title.equalsIgnoreCase("registerdate"))
                    sql+="  order By a.requestdate asc";


            


           query =  hsession.createSQLQuery(sql)
                    .setResultTransformer(Transformers.aliasToBean(MixCirMemberDetail.class));


            obj= (ArrayList<MixCirMemberDetail>)query.list();
hsession.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
       
       if(obj!=null)
        return obj;
       else
           return null;
       
}


    public static  List<CirMemberAccount> searchCirMemAccount1(String library_id,String sublibrary_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
 List<CirMemberAccount> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("status", "Active"))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id)));
            obj= (List<CirMemberAccount>) criteria.list();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}


public static boolean insert(CirMemberAccount cma)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cma);

            tx.commit();
        }
        catch (RuntimeException e) {
               e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

   public static  DocumentDetails searchDocumentID(String library_id,String sub_library_id,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
DocumentDetails obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("accessionNo", accession_no)));
            obj= (DocumentDetails) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

      public static  CirCheckout searchCheckOutDetails(String library_id,String sub_library_id,String document_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirCheckout obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("documentId", document_id)));
            obj= (CirCheckout) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
       public static  CirCheckout searchCheckOutDetails1(String library_id,String sub_library_id,String document_id,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirCheckout obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("documentId", document_id))
            .add(Restrictions.eq("status", status)));
           obj= (CirCheckout) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
           public static  CirCheckout searchCheckOutDetailsByStatus(String library_id,String sub_library_id,String document_id,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirCheckout obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("documentId", document_id))
                    .add(Restrictions.eq("status", status)));
            obj= (CirCheckout) criteria.uniqueResult();
            session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

            public static  List<CirCheckout> searchCheckOutListByStatus(String library_id,String sub_library_id,String memId,String status)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirCheckout> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("memid", memId))
                    .add(Restrictions.eq("status", status)));
            obj= (List<CirCheckout>) criteria.list();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
 public static  CirMemberDetail searchCirMemDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirMemberDetail obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberDetail.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   
                    .add(Restrictions.eq("id.memId", mem_id)));
            obj= (CirMemberDetail) criteria.uniqueResult();
session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

           
      public static  CirOpacRequest searchCirOpacRequest(String library_id,String sublibrary_id,String document_id,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirOpacRequest obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                     .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                     .add(Restrictions.eq("accessionNo", accession_no))
                    .add(Restrictions.eq("documentId",Integer.parseInt(document_id))));
            obj= (CirOpacRequest) criteria.uniqueResult();
            session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  CirOpacRequest searchCirOpacRequest1(String library_id,String sublibrary_id,String document_id,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirOpacRequest obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                     .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                     .add(Restrictions.eq("accessionNo", accession_no))
                     .add(Restrictions.eq("status", "Pending"))
                    .add(Restrictions.eq("documentId",Integer.parseInt(document_id))));
            obj=(CirOpacRequest) criteria.uniqueResult();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
      public static  CirOpacRequest searchCirOpacRequestByMemId(String library_id,String sublibrary_id,String memId,String accession_no)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirOpacRequest obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirOpacRequest.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                     .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                     .add(Restrictions.eq("accessionNo", accession_no))
                     .add(Restrictions.eq("status", "Pending"))
                    .add(Restrictions.eq("memid",memId)));
            obj=(CirOpacRequest) criteria.uniqueResult();
session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
  public static  List<CirMemberAccount> searchCirMemAccountDetailsBySubMember(String library_id,String emptype_id,String sub_emptype_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirMemberAccount> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("memType", emptype_id))
                    .add(Restrictions.eq("subMemberType", sub_emptype_id))

                    );
            obj=(List<CirMemberAccount>) criteria.list();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
        public static  List<CirMemberAccount> searchCirMemAccountDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirMemberAccount> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    
                    .add(Restrictions.eq("id.memid", mem_id)));
            obj= (List<CirMemberAccount>) criteria.list();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  CirMemberAccount searchCirMemAccountDetails(String library_id,String sublibrary_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirMemberAccount obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))

                    .add(Restrictions.eq("id.memid", mem_id)));
            obj= (CirMemberAccount) criteria.uniqueResult();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  List<CirMemberAccount> searchCirMemAccountDetailsLst(String library_id,String sublibrary_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
 List<CirMemberAccount>  obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.not(Restrictions.eq("id.sublibraryId", sublibrary_id)))
                    .add(Restrictions.eq("id.memid", mem_id)));
            obj= (List<CirMemberAccount>) criteria.list();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

       public static  CirTransactionHistory searchCirTransactionHistory(String library_id,String sub_library_id,Integer checkoutId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirTransactionHistory obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirTransactionHistory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("checkoutId", checkoutId)));
            obj= (CirTransactionHistory) criteria.uniqueResult();
session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  BookCategory searchfineDetails(String library_id,String book_type,String memtype,String submemtype)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
BookCategory obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bookType",book_type ))
                    .add(Restrictions.eq("id.emptypeId",memtype ))
                    .add(Restrictions.eq("id.subEmptypeId",submemtype ))
                    );
           obj= (BookCategory) criteria.uniqueResult();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

        public static  BookCategory searchfineDetails(String library_id,String book_type)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
BookCategory obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bookType",book_type )));
           obj= (BookCategory) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

        public static  BookCategory searchfineDetailsByType(String library_id,String sublibraryId,String docId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
BookCategory obj=null;
        try
        {
            session.beginTransaction();
            Query query = session.createSQLQuery("select a.* from book_category a,document_details d,cir_checkout cc,cir_member_account cma where a.library_id=d.library_id and a.book_type=d.book_type and a.emptype_id=cma.mem_type and a.sub_emptype_id=cma.sub_member_type and a.library_id=cc.library_id and d.sublibrary_id = cc.sublibrary_id and cc.memid=cma.memid and cc.library_id=cma.library_id and cc.sublibrary_id=cma.sublibrary_id  and cc.document_id=d.document_id  and cc.library_id=:library_id and d.status='issued' and cc.document_id=:documentId and d.sublibrary_id=:sublibraryId")
                    .addEntity(BookCategory.class);
            query.setString("library_id", library_id);
            query.setString("sublibraryId",sublibraryId);
            query.setString("documentId", docId);
                    obj= (BookCategory) query.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

         public static  List<MemberFineWithCheckoutDetails> searchfineDetailsByMemId(String library_id,String subLibraryId,String memId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<MemberFineWithCheckoutDetails> obj=null;
        try
        {
            session.beginTransaction();
            Query query = session.createSQLQuery("select * from book_category a,document_details d,cir_checkout cc,cir_member_account cma where a.library_id=d.library_id and a.book_type=d.book_type and a.library_id=cc.library_id and d.sublibrary_id = cc.sublibrary_id and cc.memid=cma.memid and cc.library_id=cma.library_id and cc.sublibrary_id=cma.sublibrary_id and cc.document_id=d.document_id and d.status='issued' and cc.library_id=:library_id and cc.sublibrary_id=:sublibraryId and cma.memid=:memId")
                    .addEntity(BookCategory.class)
                    .addEntity(DocumentDetails.class)
                    .addEntity(CirCheckout.class)
                    .addEntity(CirMemberAccount.class)
                    .setResultTransformer(Transformers.aliasToBean(MemberFineWithCheckoutDetails.class));
            query.setString("library_id", library_id);
            query.setString("memId", memId);
            query.setString("sublibraryId", subLibraryId);

                    obj= (List<MemberFineWithCheckoutDetails>) query.list();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
         public static  HashMap searchCirMemberStatistics(String library_id,String subLibraryId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
HashMap<String,String> demo=new HashMap<String,String>();
        try
        {
            session.beginTransaction();
           String SQL_QUERY = "select concat(monthname(insurance.reqDate),',',year(insurance.reqDate)),count (insurance.id.memid)"  + " from CirMemberAccount insurance  where insurance.id.libraryId='"+library_id+"' and insurance.id.sublibraryId='"+subLibraryId+"' and insurance.status='Active' group by monthname(insurance.reqDate)";
  query = session.createQuery(SQL_QUERY);
 for (Iterator it = query.iterate(); it.hasNext();) { Object[] row = (Object[]) it.next();
  if(!demo.containsKey(row[0].toString()))
            demo.put(row[0].toString(), row[1].toString());
 System.out.println("Invested Amount: " + row[0] + row[1]);
          session.getTransaction().commit();


        }
        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
    return demo;
     
}


             public static  String searchTotalCheckOut(int bibid,String library_id,String subLibraryId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();

String noofchk="0";
        try
        {
            session.beginTransaction();
           String SQL_QUERY = "select insurance.biblioId,count(insurance.biblioId) from DocumentDetails insurance  where insurance.id.libraryId='"+library_id+"' and insurance.id.sublibraryId='"+subLibraryId+"' and insurance.status='issued' and insurance.biblioId="+bibid+" group by insurance.biblioId";
  query = session.createQuery(SQL_QUERY);
 for (Iterator it = query.iterate(); it.hasNext();) {

     Object[] row = (Object[]) it.next();

noofchk=row[1].toString();

System.out.println("rows"+row[1].toString());

        }
  session.getTransaction().commit();
        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
   return noofchk;
      
}

        public Integer returnMaxCheckInId(String library_id, String sublibrary_id) {

       

    Integer maxbiblio =null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckin.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = criteria.add(le).setProjection(Projections.count("id.checkinId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(le).setProjection(Projections.count("id.checkinId")).uniqueResult().toString());
           

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
session.getTransaction().commit();
        
        }  catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return maxbiblio;
}

        public static boolean updateCheckin(CirCheckin obj1,CirCheckout obj2,CirMemberAccount obj3,DocumentDetails obj4,CirTransactionHistory obj5)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(obj1);
            session.update(obj2);
            session.update(obj3);
            session.update(obj4);
            session.update(obj5);
            tx.commit();
            return true;
        }
         catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;

        }
        finally
        {
           session.close();
        }
        
}

         public static boolean updateCheckOut(CirOpacRequest cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
        
            session.update(cmd);
            tx.commit();
            return true;
        }
        catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;

        }
        finally
        {
           session.close();
        }
       
}

public static boolean delete(String library_id,String sublibrary_id,String memid) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
         
            Query query = session.createQuery("DELETE FROM CirMemberDetail  WHERE  id.memId = :mem_id and id.libraryId=:library_id and id.sublibraryId=:sublibrary_id");

            query.setString("mem_id",memid );
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );

            query.executeUpdate();
            tx.commit();
return true;
        }
 catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;

        }
        finally
        {
           session.close();
        }
       
}
public static boolean deleteDelinq(String library_id,String sublibrary_id,String id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            query = session.createQuery("DELETE FROM DeliquencyReason  WHERE  id.id = :id and id.libraryId=:library_id and id.sublibraryId=:sublibrary_id");

            query.setString("id",id );
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );

            query.executeUpdate();
            tx.commit();
return true;
        }
 catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;

        }
        finally
        {
           session.close();
        }
      
}

public static boolean deleteCancelReason(String library_id,String sublibrary_id,String id) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
           
            query = session.createQuery("DELETE FROM CancellationReason  WHERE  id.id = :id and id.libraryId=:library_id and id.sublibraryId=:sublibrary_id");

            query.setString("id",id );
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );

            query.executeUpdate();
            tx.commit();
return true;
        }
 catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;

        }
        finally
        {
           session.close();
        }
      
}

public static boolean deleteAccount(String library_id,String sublibrary_id,String memid) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
          
           Query query = session.createQuery("DELETE FROM CirMemberAccount WHERE  id.memid = :mem_id and id.libraryId=:library_id and id.sublibraryId=:sublibrary_id");

            query.setString("mem_id",memid );
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );
            query.executeUpdate();
            tx.commit();
return true;
        }
 catch(Exception e){
        e.printStackTrace();
tx.rollback();
return false;
        }
        finally
        {
           session.close();
        }
       
}
  public static boolean update(CirMemberDetail cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(cmd);

            tx.commit();
            return true;
        }
        catch (RuntimeException e) {
                e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

 

        }

   public static boolean insert(CirMemberDetail cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cmd);

            tx.commit();
              return true;
        }
        catch (RuntimeException e) {
              e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

 

        }

      public static List<CirMemberAccount> getMaxReservationId(String library_id, String mem_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

         List<CirMemberAccount> obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class);
            criteria.add(Restrictions.eq("id.libraryId", library_id));
            criteria.add(Restrictions.eq("id.memid", mem_id));
            
            

        obj= (List<CirMemberAccount>)criteria.list();
        session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
  
 public static boolean insert(CirOpacRequest cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(cmd);

            tx.commit();
            return true;
        }
        catch (RuntimeException e) {
               e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   

        }

public static  List<MixDocumentType> getDocument_Cat_Details(String library_id,String sublibrary_id,String doc_type)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<MixDocumentType> obj=null;
        try
        {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";

            sql = "select * from document_details a,cir_checkout b where a.document_id=b.document_id and a.library_id=b.library_id and a.sublibrary_id=b.sublibrary_id and a.book_type=:book_type and b.library_id=:library_id and b.sublibrary_id=:sublibrary_id";

            System.out.println(sql);

     query =  session.createSQLQuery(sql)
                    .addEntity(DocumentDetails.class)
                    .addEntity(CirCheckout.class)
                    .setResultTransformer(Transformers.aliasToBean(MixDocumentType.class));
          query.setString("library_id", library_id);
          query.setString("sublibrary_id", sublibrary_id);
          query.setString("book_type", doc_type);
           obj= (List<MixDocumentType>) query.list();
           session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
    






 public static  List<CirMemberDetail> searchCirMemDetails1(String library_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirMemberDetail> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberDetail.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    );
            obj= (List<CirMemberDetail>) criteria.list();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static List  CheckInReport(String library_id,String sub_lib,String year1,String year2,String memid)
    {
        List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(CirCheckin.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));

         if(!memid.equalsIgnoreCase("all"))
             if(!memid.equals(""))
         criteria.add(Restrictions.eq("memberId",memid));

         if(year1!=null){
           if(!year1.equals(""))
         criteria.add(Restrictions.gt("returningDate",year1));
         }
         if(year2!=null){
          if(!year2.equals(""))
         criteria.add(Restrictions.lt("returningDate",year2));
         }

    obj=criteria.list();
    hsession.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           hsession.close();
        }
        return obj;
}
public static List  CheckInReport(String library_id,String sub_lib)
    {
       List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(CirCheckin.class);

         criteria.add(Restrictions.eq("id.libraryId",library_id));

         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));



         obj= criteria.list();
         hsession.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           hsession.close();
        }
        return obj;
}
 public static List  CheckoutReport(String library_id,String sub_lib,String year1,String year2,String memid)
    {
       List obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(CirCheckout.class);

         criteria.add(Restrictions.eq("id.libraryId",library_id));
         System.out.println("library_id="+library_id);

         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         System.out.println("sublibrary_id="+sub_lib);
          System.out.println("member_id="+memid);

           if(memid!=null)
               if(!memid.equals(""))
        {  criteria.add(Restrictions.eq("memid",memid));}


          System.out.println("year1="+year1);
         if(year1!=null){
           if(!year1.equals(""))
         criteria.add(Restrictions.ge("issueDate",year1));
         }

         System.out.println("year2="+year2);
         if(year2!=null){
           if(!year2.equals(""))
         criteria.add(Restrictions.le("issueDate",year2));
         }

         obj= criteria.list();
hsession.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
          hsession.close();
        }
        return obj;
}
 public static  CirCheckout searchCheckoutMemDetails(String library_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
 CirCheckout obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))

                    .add(Restrictions.eq("memid", mem_id)));
            obj= (CirCheckout) criteria.uniqueResult();
session.getTransaction().commit();

        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  List<CirCheckout> searchCheckoutMemDetails(String library_id,String sublibrary_id,String mem_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CirCheckout> obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("status", "issued"))
                    .add(Restrictions.eq("memid", mem_id)));
            obj= (List<CirCheckout>) criteria.list();
            session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  CirCheckout searchCheckoutMemDetails(String library_id,String sublibrary_id,String mem_id,String checkoutId)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirCheckout obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirCheckout.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("memid", mem_id))
                    .add(Restrictions.eq("id.checkoutId",Integer.parseInt(checkoutId)))
                    );
            obj= (CirCheckout) criteria.uniqueResult();
            session.getTransaction().commit();

        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static List<EmployeeType> getAllEmployeeTypes(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<EmployeeType> obj=null;
        try {
            session.beginTransaction();
            session.flush();
            Query query1 = session.createQuery("FROM  EmployeeType  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
            obj=(List<EmployeeType>) query1.list();
               session.getTransaction().commit();


        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static List<SubEmployeeType> getAllSubEmployeeTypes(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubEmployeeType> obj=null;
        try {
            session.beginTransaction();
            session.flush();
            Query query1 = session.createQuery("FROM  SubEmployeeType  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
            obj= (List<SubEmployeeType>) query1.list();
                 session.getTransaction().commit();


        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static List<Faculty> getAllFaculty(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<Faculty> obj=null;
        try {
            session.beginTransaction();
            session.flush();
            Query query1 = session.createQuery("FROM  Faculty  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
        obj=(List<Faculty>) query1.list();
     session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static List<SubLibrary> getAllSubLibrary(String library_id,String sublibraryId,String memId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createSQLQuery("select * from sub_library s where s.library_id = :library_id and s.library_id=s.sublibrary_id and s.sublibrary_id not in (select cma.sublibrary_id from cir_member_account cma where cma.memid=:memId and  cma.status='Active')")
                    .addEntity(SubLibrary.class);
            query1.setString("library_id", library_id);
            query1.setString("memId", memId);
           obj= (List<SubLibrary>) query1.list();
           session.getTransaction().commit();
        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static List<SubLibrary> getAllSubLibrary1(String library_id,String sublibraryId,String memId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createSQLQuery("select * from sub_library s where s.library_id = :library_id and s.sublibrary_id IN (:library_id,:sublibraryId) and s.sublibrary_id not in (select cma.sublibrary_id from cir_member_account cma where cma.memid=:memId and cma.status='Active')")
                    .addEntity(SubLibrary.class);
                    
            query1.setString("library_id", library_id);
            query1.setString("sublibraryId", sublibraryId);
            query1.setString("memId", memId);
obj= (List<SubLibrary>) query1.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static List<SubLibrary> getAllSubLibrary2(String library_id,String sublibraryId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createSQLQuery("select * from sub_library s where s.library_id = :library_id and s.sublibrary_id =:sublibraryId")
                    .addEntity(SubLibrary.class);
                    
            query1.setString("library_id", library_id);
            query1.setString("sublibraryId", sublibraryId);
           
           obj=(List<SubLibrary>) query1.list();
           session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  CirMemberAccount searchCirMemAccountDetails1(String library_id,String sublibrary_id,String mem_id,String password)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirMemberAccount obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.gt("expiryDate", DateCalculation.now()))
                    .add(Restrictions.eq("id.memid", mem_id))
                    .add(Restrictions.eq("password", password)));
            obj=(CirMemberAccount) criteria.uniqueResult();
            session.getTransaction().commit();


        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static  CirMemberAccount searchCirMemAccountDetails(String library_id,String sublibrary_id,String mem_id,String password)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
CirMemberAccount obj=null;
        try
        {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.memid", mem_id))
                    .add(Restrictions.eq("password", password)));
            obj= (CirMemberAccount) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
  public static CirMemberAccount getAccountDate(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       CirMemberAccount obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CirMemberAccount.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.memid", memid))
                    .add(Restrictions.eq("status", "Active")));
            obj= (CirMemberAccount) criteria.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

 public static List searchDoc1(String library_id, String sub_library_id,String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("callNo", call_no))
                    .add(Restrictions.eq("status", "available"))
                
                    );
            obj= criteria.list();
            session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
 public static String DistinctDocType(String library_id,String sublibrary_id,String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String status="available";
        String obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("Select distinct bookType From  DocumentDetails where id.libraryId =:library_id and callNo= :callNo and status = :status and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("callNo", call_no);
            query.setString("status", status);
            query.setString("sublibrary_id",sublibrary_id);
            obj= (String)  query.uniqueResult();
            session.getTransaction().commit();
        }  catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static CirMemberAccount getAccount2(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CirMemberAccount obj=null;
        
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and id.memid = :memid and id.sublibraryId=:sublibrary_id ");
            query.setString("library_id", library_id);
            query.setString("memid",memid);
            query.setString("sublibrary_id",sublibrary_id);

            obj= (CirMemberAccount) query.uniqueResult();
            session.getTransaction().commit();
        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}



public static CirMemberDetail getMemberDetail(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
CirMemberDetail obj=null;
                try {
            session.beginTransaction();
            query = session.createQuery("FROM  CirMemberDetail  WHERE id.libraryId =:library_id and id.memId = :memid");
            query.setString("library_id", library_id);
            query.setString("memid",memid);

            obj= (CirMemberDetail) query.uniqueResult();
            session.getTransaction().commit();
        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}



public static  boolean update2(CirMemberAccount obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (Exception e) {
               e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}



public static CirMemberDetail getMemid(String library_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CirMemberDetail obj = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  CirMemberDetail  WHERE id.libraryId =:library_id and id.memId = :memId ");
            query.setString("library_id", library_id);
            query.setString("memId",memid);

            obj= (CirMemberDetail) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static CirMemberAccount getAccount(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CirMemberAccount obj = null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and id.memid = :memid and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("memid",memid);
            query.setString("sublibrary_id",sublibrary_id);
            obj= (CirMemberAccount) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static List<CirOpacRequest> getOpacCheckOut(String library_id,String sublibrary_id,String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CirOpacRequest> obj = null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  CirOpacRequest  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id and status=:status");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("status",status);
            obj= (List<CirOpacRequest>) query.list();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}


public static List<DocumentDetails> searchBYCallno(String library_id,String sublibrary_id,String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String status="available";
        List<DocumentDetails>  obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId= :library_id  and callNo= :callNo and status = :status and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("callNo", call_no);
            query.setString("status", status);
             query.setString("sublibrary_id",sublibrary_id);
            obj=(List<DocumentDetails>) query.list();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static DocumentDetails getBook(String library_id,String sublibrary_id,String accession_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       DocumentDetails obj = null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and accessionNo = :accession_no");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("accession_no",accession_no);
            obj= (DocumentDetails) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static DocumentDetails getBookStatus(String library_id,String sublibrary_id,String accession_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DocumentDetails obj = null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and accessionNo = :accession_no and status=:status");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("accession_no",accession_no);
            query.setString("status", "issued");
            obj= (DocumentDetails) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}
public static DocumentDetails getDocument(String library_id,String sublibrary_id,int document_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DocumentDetails obj = null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  DocumentDetails  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and id.documentId = :document_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setInteger("document_id",document_id);
           obj= (DocumentDetails) query.uniqueResult();
           session.getTransaction().commit();
        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static CirMemberAccount getCirMem(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CirMemberAccount obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  CirMemberAccount  WHERE id.libraryId =:library_id and id.sublibraryId = :sublibrary_id and id.memid = :memid");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("memid",memid);
            obj= (CirMemberAccount) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}


public static  boolean update(CirMemberAccount obj3,DocumentDetails obj4)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.update(obj3);
            session.update(obj4);
            tx.commit();
        }
        catch (Exception e) {
                e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}











 public static List getMaxChkoutId(String library_id,String sublibrary_id) {
        Session session =HibernateUtil.getSessionFactory().openSession();
        List obj = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.checkoutId)FROM CirCheckout where id.libraryId = :library_id and id.sublibraryId = :sublibrary_id");
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );
            obj=  query.list();
            session.getTransaction().commit();
        }
       catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}


 public static List getMaxTransId(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.transactionId)FROM CirTransactionHistory where id.libraryId = :library_id ");
            query.setString("library_id",library_id );
            obj= query.list();
            session.getTransaction().commit();
        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
        return obj;
}

public static  boolean delete(CirMemberAccount obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (Exception e) {
               e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}
 public static boolean updateAccount(CirMemberAccount cmd)
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(cmd);

            tx.commit();
        }
        catch (Exception e) {
               e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

        }

public static  boolean insert1(CirMemberAccount obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
               e.printStackTrace();
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}

  
}
