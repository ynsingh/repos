/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
import com.myapp.struts.admin.AdminReg_Institute;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
/**
 *
 * @author Edrp-04
 */
public class InstituteDAO {
     public void insert(Institute instituteDetails){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(instituteDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(instituteDetails != null)
                tx.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
          session.close();
        }
    }
public void update(Institute instituteDetails) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(instituteDetails);
            tx.commit();
        }
        catch (RuntimeException e) {
       
            e.printStackTrace();
                tx.rollback();
            throw e;
        }
        finally {
           session.close();
        }
    }
public void delete(int instituteId) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
         
            Query query = session.createQuery("DELETE FROM Institute  WHERE  id.instituteId = :instituteId");

            query.setInteger("instituteId",instituteId );

            query.executeUpdate();
            tx.commit();
       
        }
    catch (RuntimeException e) {
        
            e.printStackTrace();
                tx.rollback();
            throw e;
        }

        finally {
            session.close();
        }
    }

public Integer getInstituteRequestCount(String status){
        Session session = null;
        Integer obj=null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
      
        Criteria criteria = session.createCriteria(Institute.class)
                 .setProjection(Projections.count("id.instituteId"));
        criteria.add(Restrictions.eq("status",status ));
            Integer countrequest = Integer.parseInt((String)criteria.uniqueResult());

  


            obj= countrequest;
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return obj;
}
public List getInstituteDetailsByStatus(String institute_id, String status){
  Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Institute where id.instituteId = :instituteId and working_status = :status");
            query.setString("instituteId",institute_id );
            query.setString("status",status );

            tx= (List)query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}
public Institute getInstituteDetails(String instituteId){
  Session session =null;
    Institute tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Institute where id.instituteId = :instituteId");
            query.setString("instituteId", instituteId);
            tx= (Institute)query.uniqueResult();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}

public List<Candidate1> getCandidatePosition(String instituteId,String enrolment){
  Session session =null;
   List<Candidate1> tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Candidate1 where id.instituteId =:instituteId and enrolment=:enrolment");
            query.setString("instituteId", instituteId);
             query.setString("enrolment", enrolment);
            tx= (List<Candidate1>)query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}
public Position1 getCandidatePositionName(String instituteId,String electionId,String positionId){
  Session session =null;
    Position1 tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Position1 where id.instituteId =:instituteId and id.electionId =:electionId and id.positionId=:positionId");
            query.setString("instituteId", instituteId);
         
              query.setString("electionId", electionId);
              query.setString("positionId", positionId);
            tx= (Position1)query.uniqueResult();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}
public ElectionManager getElectionManagerDetails(String userId,String instituteId){
  Session session =null;
    ElectionManager tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM ElectionManager where id.instituteId = :instituteId and userId =:userId");
            query.setString("instituteId", instituteId);
            query.setString("userId", userId);
            tx= (ElectionManager)query.uniqueResult();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}


public VoterRegistration getVoterDetails(String staffId,String instituteId){
  Session session =null;
    VoterRegistration tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM VoterRegistration where id.enrollment = :staffId and id.instituteId =:instituteId");
            query.setString("instituteId", instituteId);
            query.setString("staffId", staffId);
          tx= (VoterRegistration)query.uniqueResult();
          session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}


public Institute getInstituteDetailsByRegistrationId(Integer RegistrationId){
  Session session =null;
    Institute tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Institute where registrationId = :registrationId");
            query.setInteger("registrationId", RegistrationId);
            tx= (Institute)query.uniqueResult();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}

public List getInstituteSearch(String search_by, String search_keyword, String sort_by){
  Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql="";
            if (search_by.equals("institute_id")){
            sql = "select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id  where b."+search_by+" like '"+search_keyword +"%' order by a."+sort_by+" asc";}
            else{
            sql = "select a.*,b.* from admin_registration a left outer join institute b on a.registration_id=b.registration_id  where a."+search_by+" like '"+search_keyword +"%' order by a."+sort_by+" asc";}
            System.out.println(sql);
            
          Query query =  session.createSQLQuery(sql)
                    .addEntity(AdminRegistration.class)
                    .addEntity(Institute.class)
                    .setResultTransformer(Transformers.aliasToBean(AdminReg_Institute.class));
            tx=query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;
}


public Integer getInstituteRequestCount(){
        Session session = null;
        Integer tx=null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Institute.class)
                 .setProjection(Projections.count("id.instituteId"));
       
            Integer countrequest = Integer.parseInt((String)criteria.uniqueResult());

  

           tx= countrequest;
           session.getTransaction().commit();
        }catch(RuntimeException e){
    e.printStackTrace();
    }

        finally {
            session.close();
        }
        return tx;
}

public List getInstituteNameByStatus(String status){
  Session session =null;
    List tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Institute where  working_status = :status and institute_id!='ems'");
            
            query.setString("status",status );

            tx= (List)query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return tx;

}
 

}
