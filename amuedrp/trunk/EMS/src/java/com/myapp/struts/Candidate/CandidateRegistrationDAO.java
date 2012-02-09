/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import com.myapp.struts.hbm.Candidate1;
import com.myapp.struts.hbm.CandidateRegLoginDetails;
import com.myapp.struts.hbm.CandidateRegistration;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.VoterCandidate;
import com.myapp.struts.hbm.VoterRegistration;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.hbm.VoterList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;


/**
 *
 * @author akhtar
 */
public class CandidateRegistrationDAO {
public static void insert(CandidateRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
     
                tx.rollback();
              ex.printStackTrace();

        } finally {
            session.close();
        }
     


}

public static boolean update(CandidateRegistration obj,VoterRegistration obj1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            if(obj!=null)
                session.update(obj);
            if(obj1!=null)
                session.update(obj1);
            tx.commit();
            
        } catch (RuntimeException e) {

            tx.rollback();
            return false;

        }

        return true;

    }
public static boolean updateCandidature(CandidateRegistration obj,Candidate1 cand) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            
            if(obj!=null)
                session.update(obj);
            if(cand!=null)
            {
                Criteria crit = session.createCriteria(Candidate1.class)
                        .setProjection(Projections.max("id.candidateId"))
                        .add(Restrictions.conjunction()
                        .add(Restrictions.eq("id.positionId", cand.getId().getPositionId()))
                        .add(Restrictions.eq("id.electionId", cand.getId().getElectionId()))
                        .add(Restrictions.eq("id.instituteId", cand.getId().getInstituteId())));
                    Integer max = (Integer)(crit.uniqueResult()==null?0:crit.uniqueResult());
                    System.out.println("candidateId="+max);
                    max++;
                    System.out.println("candidateId="+max);
                    
                    cand.getId().setCandidateId(max);

                    session.save(cand);
                    crit = null;
                    tx.commit();
            }
            
        } catch (RuntimeException e) {

            tx.rollback();
            e.printStackTrace();
              return false;
            
          

        }
        finally{
        session.close();}

        return true;

    }

public static boolean updateCandidate1(Candidate1 cand) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();

            session.update(cand);
            tx.commit();
           
        } catch (RuntimeException e) {

            tx.rollback();

            e.printStackTrace();
            return false;
        }
 finally {
            session.close();
        }
        return true;

    }

public static boolean update(CandidateRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            session.update(obj);
            
            tx.commit();
             
        } catch (RuntimeException e) {

            tx.rollback();
            return false;

        }
 finally {
            session.close();
        }
        return true;

    }

 public static VoterRegistration searchVoterRegistration(String instituteId,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
VoterRegistration voter=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                   .add(Restrictions.eq("id.instituteId", instituteId)));
            voter=(VoterRegistration) criteria.uniqueResult();
              session.getTransaction().commit();


        }
        catch(Exception e){
        e.printStackTrace();
        }

        finally {
            session.close();
        }
        return voter;
    }
    public static Candidate1 getCandidate(String instituteId,String Enrollment,String electionId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Candidate1 candi=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Candidate1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("enrollment", Enrollment))
                    .add(Restrictions.eq("id.electionId", electionId))
                   .add(Restrictions.eq("id.instituteId", instituteId)));
            candi=(Candidate1) (criteria.list()!=null?criteria.list().get(0):null);
  session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }

        finally {
            session.close();
        }
        return candi;
    }
    public static Candidate1 searchcandidateMenifesto(String instituteId,String Enrollment,String election_id,String position_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Candidate1 candi=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Candidate1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("enrollment", Enrollment))
                   .add(Restrictions.eq("id.instituteId", instituteId))
                   .add(Restrictions.eq("id.electionId", election_id))
                   .add(Restrictions.eq("id.positionId", Integer.parseInt(position_id)))
                   );
            candi= (Candidate1) criteria.uniqueResult();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }
    public static List<Candidate1> searchcandidate(String instituteId,String Enrollment)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<Candidate1> candi=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Candidate1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("enrollment", Enrollment))
                   .add(Restrictions.eq("id.instituteId", instituteId))
                   
                   );
            candi= (List<Candidate1>) criteria.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }


    public static List<CandidateRegLoginDetails> searchfinalCandidate(String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CandidateRegLoginDetails> candi=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select l.*,c1.*,cr.*,vr.*,e.*,p.* from login l,candidate1 c1,candidate_registration cr,voter_registration vr,position1 p,election e where l.institute_id=c1.institute_id and l.staff_id = cr.enrollment and c1.position_id = cr.position and c1.election_id = cr.election_id and c1.institute_id = cr.institute_id and c1.institute_id = vr.institute_id and cr.enrollment = vr.enrollment and c1.enrolment=cr.enrollment and cr.institute_id = e.institute_id and cr.election_id = e.election_id and e.election_id = p.election_id and c1.position_id = p.position_id and l.`role` = 'candidate' and l.institute_id = :institute_id and c1.position_id IN (select distinct c2.position from candidate_registration c2 where c2.enrollment = :enrollment) and cr.status1 = 'REGISTERED'")
                    .addEntity(Login.class).addEntity(Candidate1.class).addEntity(CandidateRegistration.class).addEntity(VoterRegistration.class).addEntity(Election.class).addEntity(Position1.class)
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class))
                    ;

            query.setString("enrollment", Enrollment);
            query.setString("institute_id", instituteId);
            System.out.println("enrollment="+Enrollment + " instituteId="+ instituteId+" query size="+query.list().size());

           candi= (List<CandidateRegLoginDetails>)query.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }

        finally {
            session.close();
        }
        return candi;
    }

    public static List<CandidateRegLoginDetails> searchCandidateDetail(String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CandidateRegLoginDetails> candi=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select l.*,c1.*,cr.*,vr.*,e.*,p.* from login l,candidate1 c1,candidate_registration cr,voter_registration vr,position1 p,election e where l.institute_id=c1.institute_id and l.staff_id = cr.enrollment and c1.position_id = cr.position and c1.election_id = cr.election_id and c1.institute_id = cr.institute_id and c1.institute_id = vr.institute_id and cr.enrollment = vr.enrollment and c1.enrolment=cr.enrollment and cr.institute_id = e.institute_id and cr.election_id = e.election_id and e.election_id = p.election_id and c1.position_id = p.position_id and l.`role` = 'candidate' and l.institute_id = :institute_id and c1.position_id IN (select distinct c2.position from candidate_registration c2 where c2.enrollment = :enrollment) and cr.status1 = 'REGISTERED'")
                    .addEntity(Login.class).addEntity(Candidate1.class).addEntity(CandidateRegistration.class).addEntity(VoterRegistration.class).addEntity(Election.class).addEntity(Position1.class)
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class))
                    ;

            query.setString("enrollment", Enrollment);
            query.setString("institute_id", instituteId);
            System.out.println("enrollment="+Enrollment + " instituteId="+ instituteId+" query size="+query.list().size());

           candi= (List<CandidateRegLoginDetails>)query.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }

        finally {
            session.close();
        }
        return candi;
    }
 public static List<CandidateRegLoginDetails> searchCandidatedetail(String Enrollment, String instituteId,String pos_id,String elec_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CandidateRegLoginDetails> candi=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select cr.*,vr.*,e.*,p.* from voter_registration vr,candidate_registration cr,position1 p,election e where vr.institute_id=cr.institute_id and vr.enrollment=cr.enrollment and cr.institute_id= p.institute_id and cr.election_id = p.election_id and cr.`position`=p.position_id and p.institute_id=e.institute_id and p.election_id=e.election_id  and vr.institute_id = :inst  and vr.enrollment=:enroll and cr.`position`=:pos and cr.election_id=:elec")
                    .addEntity(CandidateRegistration.class).addEntity(VoterRegistration.class).addEntity(Election.class).addEntity(Position1.class)
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class))
                    ;

            query.setString("enroll", Enrollment);
            query.setString("inst", instituteId);
            query.setString("pos", pos_id);
            query.setString("elec", elec_id);

      System.out.println("enrollment="+Enrollment + " instituteId="+ instituteId+" query size="+query.list().size());

            candi= (List<CandidateRegLoginDetails>)query.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }



    public static List<CandidateRegLoginDetails> searchCandidate(String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CandidateRegLoginDetails> candi=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select l.*,c1.*,cr.*,vr.*,e.*,p.* from login l,candidate1 c1,candidate_registration cr,voter_registration vr,position1 p,election e where l.institute_id=c1.institute_id and l.staff_id = cr.enrollment and c1.position_id = cr.position and c1.election_id = cr.election_id and c1.institute_id = cr.institute_id and c1.institute_id = vr.institute_id and cr.enrollment = vr.enrollment and c1.enrolment=cr.enrollment and cr.institute_id = e.institute_id and cr.election_id = e.election_id and e.election_id = p.election_id and c1.position_id = p.position_id and l.`role` = 'candidate' and l.institute_id = :institute_id and c1.position_id IN (select distinct c2.position from candidate_registration c2 where c2.enrollment = :enrollment)")
                    .addEntity(Login.class).addEntity(Candidate1.class).addEntity(CandidateRegistration.class).addEntity(VoterRegistration.class).addEntity(Election.class).addEntity(Position1.class)
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class))
                    ;

            query.setString("enrollment", Enrollment);
            query.setString("institute_id", instituteId);
            System.out.println("enrollment="+Enrollment + " instituteId="+ instituteId+" query size="+query.list().size());

            candi= (List<CandidateRegLoginDetails>)query.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }

    public static List<CandidateRegLoginDetails> searchCandidate1(String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<CandidateRegLoginDetails> candi=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select v.*,l.*,c1.*,cr.*,p.*,e.* from login l,voter_registration v,candidate1 c1,candidate_registration cr,position1 p,election e where  l.institute_id=v.institute_id and l.staff_id=v.enrollment and v.enrollment = c1.enrolment and v.institute_id=c1.institute_id and  c1.enrolment=cr.enrollment and  c1.position_id=cr.`position` and c1.institute_id=cr.institute_id and c1.election_id=cr.election_id and cr.`position`=p.position_id and cr.institute_id=p.institute_id and cr.election_id=p.election_id and p.election_id=e.election_id and p.institute_id=e.institute_id and l.institute_id=:institute_id and l.user_id=:user_id and cr.status1!='Withdraw'")
                    .addEntity(Login.class).addEntity(Candidate1.class).addEntity(VoterRegistration.class).addEntity(CandidateRegistration.class).addEntity(Election.class).addEntity(Position1.class)
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class))
                    ;

            query.setString("user_id", Enrollment);
            query.setString("institute_id", instituteId);
        System.out.println(query.list().size());

            candi= (List<CandidateRegLoginDetails>)query.list();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }
    public static CandidateRegLoginDetails searchCandidateElection(String Enrollment, String instituteId,String election_id,String position_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
CandidateRegLoginDetails candi=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select v.*,l.*,c1.*,cr.*,p.*,e.* from login l,voter_registration v,candidate1 c1,candidate_registration cr,position1 p,election e where  l.institute_id=v.institute_id and l.staff_id=v.enrollment and v.enrollment = c1.enrolment and v.institute_id=c1.institute_id and  c1.enrolment=cr.enrollment and c1.institute_id=cr.institute_id and c1.position_id=cr.`position`   and c1.election_id=cr.election_id and cr.`position`=p.position_id and cr.institute_id=p.institute_id and cr.election_id=p.election_id and p.election_id=e.election_id and p.institute_id=e.institute_id and l.institute_id=:institute_id and l.user_id=:user_id and p.position_id=:pos and p.election_id=:election_id")
                    .addEntity(Login.class).addEntity(Candidate1.class).addEntity(VoterRegistration.class).addEntity(CandidateRegistration.class).addEntity(Election.class).addEntity(Position1.class)
                    .setResultTransformer(Transformers.aliasToBean(CandidateRegLoginDetails.class))
                    ;

            query.setString("user_id", Enrollment);
            query.setString("election_id", election_id);
            query.setString("pos", position_id);
            query.setString("institute_id", instituteId);
        System.out.println(query.list().size());

            candi= (CandidateRegLoginDetails)query.uniqueResult();
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }

public static CandidateRegistration searchCandidature(String electionId,String Enrollment, String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
CandidateRegistration candi=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CandidateRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                   .add(Restrictions.eq("id.electionId", electionId)))
                   .add(Restrictions.eq("id.instituteId", instituteId));
            candi=(CandidateRegistration) ((criteria.list()!=null && criteria.list().size()==1)?criteria.uniqueResult():null);
   session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }

      public static CandidateRegistration searchCandidateRegistration(String instituteId,String Enrollment,String position) {
        Session session = HibernateUtil.getSessionFactory().openSession();
CandidateRegistration candi=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(CandidateRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("id.position", position))
                   .add(Restrictions.eq("id.instituteId", instituteId)));
        candi= (CandidateRegistration) criteria.uniqueResult();
           session.getTransaction().commit();


        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return candi;
    }


     public List getCandidateDetailsByStatus(String instituteid,String status){
  Session session =null;
    
    List candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where status = :status and id.instituteId=:instituteId");
             query.setString("status",status );
             query.setString("instituteId",instituteid );

            candi= query.list();
               session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return candi;
}

      public static CandidateRegistration getCandidateDetails1(String instituteid,String enrollment,String election_id,String position_id){
  Session session =null;
        CandidateRegistration candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where enrollment = :enrollment and id.instituteId=:instituteId and election_id=:election and position=:pos");
             query.setString("enrollment",enrollment );
             query.setString("instituteId",instituteid );
             query.setString("election",election_id);
             query.setString("pos",position_id);

            candi= (CandidateRegistration)query.uniqueResult();
               session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return candi;
}
public static List<CandidateRegistration> getCandidateDetails(String instituteid,String enrollment){
  Session session =null;
  
    List<CandidateRegistration> candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where enrollment = :enrollment and id.instituteId=:instituteId");
             query.setString("enrollment",enrollment );
             query.setString("instituteId",instituteid );

            candi=query.list();
               session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return candi;
}
public static List<SetVoter> getVoterDetails(String instituteid){
  Session session =null;

    List<SetVoter> candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM SetVoter where instituteId=:instituteId");

             query.setString("instituteId",instituteid );

            candi=query.list();
               session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return candi;
}

     public List<VoterCandidate> GetDetails(String instituteId,String status)
    {
         Session session =null;
   
    List<VoterCandidate> candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id and b.status1=:status";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(VoterRegistration.class)
                    .addEntity(CandidateRegistration.class)

                    .setResultTransformer(Transformers.aliasToBean(VoterCandidate.class));
          query.setString("institute_id",instituteId );
          query.setString("status", "not registered");

         candi= (List<VoterCandidate>)query.list();
            session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return candi;
}



      public List<VoterCandidate> GetDetails1(String instituteId,String status)
    {
         Session session =null;
    
    List<VoterCandidate> candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";
            System.out.println("status="+ status);
            sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id";
            if(status!=null && !status.equalsIgnoreCase("null"))
                {sql += " and b.status1=:status";}

            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(VoterRegistration.class)
                    .addEntity(CandidateRegistration.class)

                    .setResultTransformer(Transformers.aliasToBean(VoterCandidate.class));
          query.setString("institute_id",instituteId );
          if(status!=null && !status.equalsIgnoreCase("null"))
          query.setString("status", status);

          candi= (List<VoterCandidate>)query.list();
             session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return candi;
}

       public List<VoterCandidate> GetDetails2(String instituteId,String status,String field,String fieldvalue,String sort)
    {
            field="a."+field;
            sort="a."+sort;

           Session session =null;
           List<VoterCandidate> candi=null;
          
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";
            System.out.println("status="+ status);
           // sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and "+field+" like '"+fieldvalue+"' and b.institute_id=:institute_id order by"+sort;
           if(status==null)
           {
               if(fieldvalue!=null && fieldvalue.isEmpty()==false)
                   sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and "+field+" like '"+fieldvalue+"' and b.institute_id=:institute_id order by "+sort;
               else
                   sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id order by "+sort;
           }
            
           else
           {
            if(fieldvalue!=null && fieldvalue.isEmpty()==false)
            sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id and "+field+" like '"+fieldvalue+"'";
            else
                  sql = "select a.*,b.* from  candidate_registration b, voter_registration a where a.enrollment=b.enrollment and a.institute_id=b.institute_id and b.institute_id=:institute_id";

            if(status!=null && !status.equalsIgnoreCase("null") && status.isEmpty()==false )
                {sql += " and b.status1=:status";}

            sql+=" order by "+sort;
           }
            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(VoterRegistration.class)
                    .addEntity(CandidateRegistration.class)

                    .setResultTransformer(Transformers.aliasToBean(VoterCandidate.class));
          query.setString("institute_id",instituteId );
          if(status!=null && !status.equalsIgnoreCase("null") && status.isEmpty()==false)
          query.setString("status", status);

         candi=(List<VoterCandidate>)query.list();
            session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return candi;
}

   public List<VoterRegistration> GetVoterList(String instituteId)
    {

           Session session =null;
          List<VoterRegistration> voter=null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            voter=   session.createCriteria(VoterList.class)
//                    .createAlias( "VoterRegistration","voterlist")
//                    .createAlias("SetVoter","setlist")
//                    .add( Restrictions.eqProperty("voterlist.instituteId", "setlist.instituteId") )
//                    .add(Restrictions.neProperty("voterlist.enrollment", "setlist.enrollment"))
//                    .add(Restrictions.eqProperty("voterlist.instituteId",instituteId))
//                    .list();



            Query query = session.createQuery("FROM VoterRegistration where   id.instituteId=:instituteId and setVoter is null");
             
             query.setString("instituteId",instituteId );

            voter=(List<VoterRegistration>)query.list();
               session.getTransaction().commit();
                       System.out.println(voter.size()+"........bbbb............");
        }
    catch(Exception e){
    e.printStackTrace();
    }
    finally {
            session.close();
        }
    return voter;
    
}


      public List<CandidateRegistration> getCandidateDetailsByStatus1(String instituteid,String electionId){
  Session session =null;
    
    List<CandidateRegistration> candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where status = :status and id.instituteId=:instituteId and id.electionId=:electionId");
             query.setString("status","not registered" );
             query.setString("instituteId",instituteid );
                     query.setString("electionId",electionId );

        candi= (List<CandidateRegistration>) query.list();
           session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return candi;
}
 public List<CandidateRegistration> getCandidateDetailsByStatus1(String instituteid,String electionId,String enrollment){
  Session session =null;
  
    List<CandidateRegistration> candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM CandidateRegistration where status = :status and id.instituteId=:instituteId and id.electionId=:electionId and id.enrollment=:enrollment");
             query.setString("status","not registered" );
             query.setString("instituteId",instituteid );
             query.setString("enrollment",enrollment );
                     query.setString("electionId",electionId );

            candi= (List<CandidateRegistration>) query.list();
            session.getTransaction().commit();
        }
     catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
        return candi;
}

      public List getEmail(String institute_id,String Enrollment)
      {
       Session session =null;
  
List candi=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM VoterRegistration where id.enrollment=:enrollment and id.instituteId=:instituteId");
             query.setString("enrollment",Enrollment );
              query.setString("instituteId",institute_id);


        candi= query.list();
         session.getTransaction().commit();
        }
    catch(Exception e){
    e.printStackTrace();
    }
        finally {
            session.close();
        }
return candi;
      }

}