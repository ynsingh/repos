/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;

import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author edrp02
 */
public class NewDemandDAO {
  Criterion criterion;

  public Demandlist DemandId(String library_id,String sub_library_id,int demand_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 Demandlist obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Demandlist.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.demandId",demand_id )));
            obj=(Demandlist) criteria.uniqueResult();
            session.getTransaction().commit();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }

public void updateDemandStatus(Demandlist bibDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bibDetails);
            tx.commit();
        } catch (Exception e)
        {

            tx.rollback();
           e.printStackTrace();
        } finally {
            session.close();
        }
    }




 public Integer returnMaxDemandId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Integer maxbiblio=null;
        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(Demandlist.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.demandId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
session.getTransaction().commit();

        } catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return maxbiblio;
    }


  public  Notices ViewNotice(String library_id,String sublibrary_id,String notice_id) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
       Notices obj = null;
        System.out.println("LibraryID="+library_id+" SublibraryId="+sublibrary_id+" noticeId="+notice_id);
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Notices where id.libraryId = :library_id and id.sublibraryId = :sublibrary_id and id.noticeId = :notice_id");
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("notice_id",notice_id);
           obj= (Notices) query.uniqueResult();
                       session.getTransaction().commit();
        }
        catch(Exception e){
        System.out.println("Notices NewDemandDAO Action "+e);
        return null;
        }
        finally {
            session.close();
        }
return obj;


}


    public  List<Notices> Notice(String library_id,String sub_lib)
    {
        List<Notices> obj=null;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(Notices.class);
         if(library_id!=null)
         {
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         obj= criteria.list();
         }
         
                     hsession.getTransaction().commit();

        }
        catch(Exception e)
        {
            e.printStackTrace();
           
           
        }
        finally
        {
           hsession.close();
        }
        return obj;
   }





    public  List NewArrival(String library_id,String sub_lib,String year1,String year2,String cat,int pageNumber)
    {
       List obj=null;
       OpacSearchDAO opac=new OpacSearchDAO();
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
            String query="select distinct a from BibliographicDetails as a right join a.documentDetailses as l where  (a.entryLanguage is null or a.entryLanguage='') ";



            query+=" and  a.documentType='"+cat+"'";


         if(!library_id.equalsIgnoreCase("all"))
            query+=" and   a.id.libraryId='"+library_id+"' ";


             if(!sub_lib.equalsIgnoreCase("all"))
                   query+=" and a.id.sublibraryId='"+sub_lib+"' ";
            

            System.out.println(year1  +  " <  "+year2+library_id+sub_lib);
         if(year1!=null){

                query+=" and a.dateAcquired>="+year1;
         }



System.out.println(query);

           Query simple=hsession.createQuery(query);


             //Total Number of Record Found View in OPAC
            opac.setSearchSize(simple.list());
             if(pageNumber==0)
                {
                    simple = simple.setFirstResult(0);
                    simple.setMaxResults(100);
                    obj=(List)simple.list();
                }
                else
                {
                    PagingAction o=new PagingAction(simple,pageNumber,100);
                    obj=(List)o.getList();
                }


      
hsession.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
           
        }
        finally
        {
           hsession.close();
        }
        return obj;
   }


  public   boolean insert2(Feedback obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
            tx.rollback();
            ex.printStackTrace();
             return false;

      

        }
        finally
        {
          session.close();
        }


}


    public   boolean insert(Demandlist obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
System.out.println("In Insert Case DAO");
            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            tx.rollback();
             return false;

       

        }
        finally
        {
          session.close();
        }


}

       public   boolean insert1(Reservationlist obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            tx.rollback();
             return false;



        }
        finally
        {
          session.close();
        }


}
     public  Demandlist getDemandList(String library_id,String sublibrary_id,String memid,String title,String status) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        Demandlist obj=null;
       
        try {
            System.out.println("Call");
            session.beginTransaction();
            Query query = session.createQuery("FROM Demandlist where id.libraryId = :library_id and id.sublibraryId=:sublibrary_id and id.memId=:mem_id and id.title=:title and status=:status");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);
            query.setString("title",title);
            query.setString("status",status);
           obj=  (Demandlist)query.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
return obj;
     }


       public  List getMaxReservationId(String library_id,String sublibrary_id,String memId) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.requestId)FROM Reservationlist where id.libraryId = :library_id and id.sublibraryId = :sublibrary and id.memid = :memId ");
            query.setString("library_id",library_id );
            query.setString("sublibrary",sublibrary_id );
            query.setString("memId",memId );
            obj=query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }

return obj;

}


}
