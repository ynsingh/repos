/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import com.myapp.struts.Voter.PagingAction;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author akhtar
 */
public class ElectionDAO {
     public static String returnMaxElectionId(String institute_id) {

         
String id="";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Election.class);
            Criterion a = Restrictions.eq("id.instituteId", institute_id);
            
           // LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = criteria.add(a).setProjection(Projections.count("id.electionId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(a).setProjection(Projections.count("id.electionId")).uniqueResult().toString());
           System.out.println(maxbiblio);

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           id=String.valueOf(maxbiblio);
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally {
            session.close();
        }
 return id;
    }
     public static String returnMaxElectionRuleId(String institute_id,String election_id) {


String id="";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Electionrule.class);
            Criterion a = Restrictions.eq("id.instituteId", institute_id);
            Criterion b = Restrictions.eq("id.electionId", election_id);

            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = criteria.add(le).setProjection(Projections.count("id.ruleId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(a).setProjection(Projections.count("id.ruleId")).uniqueResult().toString());
           System.out.println(maxbiblio);

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           id=String.valueOf(maxbiblio);
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally {
            session.close();
        }
 return id;
    }
     public static String returnMaxElectionManagerId(String institute_id) {


String id="";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(ElectionManager.class);
            Criterion a = Restrictions.eq("id.instituteId", institute_id);

           
            Integer maxbiblio = criteria.add(a).setProjection(Projections.count("id.managerId")).uniqueResult()==null?0:Integer.valueOf(criteria.add(a).setProjection(Projections.count("id.managerId")).uniqueResult().toString());
           System.out.println(maxbiblio);

            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

           id=String.valueOf(maxbiblio);
            session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();

        }
        finally {
            session.close();
        }
 return id;
    }

    
       public static SetVoter searchVoter(String Election_id,String institue_id,String enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SetVoter obj = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SetVoter.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.electionId", Election_id))
                    .add(Restrictions.eq("id.instituteId",institue_id))
                    .add(Restrictions.eq("id.enrollment",enrollment)));
            obj= (SetVoter) criteria.uniqueResult();
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
 public static void insert(Election obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
        //    return false;
                ex.printStackTrace();
                tx.rollback();
            //  System.out.println(ex.toString());

        } finally {
            session.close();
        }
     

    }
        public static boolean update(Election obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            return false;

        }finally {
            session.close();
        }

        return true;

    }
        public static void delete(String electionId,String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Election where id.electionId = :electionid2  and id.instituteId= :instituteid");

            query.setString("electionid2", electionId);
            query.setString("instituteid", instituteId);
            query.executeUpdate();
            tx.commit();



        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
            System.out.println(ex);
       //     return false;

            //  System.out.println(ex.toString());

        } finally {
            session.close();
        }
      //  return true;

    }
    public static Election searchElection(String Election_id,String institue_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Election obj = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Election.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.electionId", Election_id))
                    .add(Restrictions.eq("id.instituteId",institue_id)));
            obj= (Election) criteria.uniqueResult();
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
        public static List<VoterRegistration> searchVoter(String institue_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<VoterRegistration> obj = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.instituteId",institue_id)));
            obj= (List<VoterRegistration>) criteria.list();
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
         public static List<Candidate1> searchCandidate(String institue_id,String election_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Candidate1> obj = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Candidate1.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.instituteId",institue_id))
             .add(Restrictions.eq("id.electionId",election_id)));
            obj= (List<Candidate1>) criteria.list();
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
    public static Election searchElectionByName(String ElectionName,String institue_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Election obj = null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Election.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("electionName", ElectionName))
                    .add(Restrictions.eq("id.instituteId",institue_id)));
            obj= (Election) criteria.uniqueResult();
            session.getTransaction().commit();

        }
        catch(RuntimeException e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  obj;
    }
    public static List <ElectionRuleEligiblity1> GetElectionDetails(String institute_id,String Election_id)
    {
    Session session =null;
    List <ElectionRuleEligiblity1> obj= null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from election a, electionrule b,eligibility c where a.election_id=b.election_id and a.election_id=c.election_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id and a.election_id=:election_id";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(Election.class)
                    .addEntity(Electionrule.class)
                    .addEntity(Eligibility.class)
                    .setResultTransformer(Transformers.aliasToBean(ElectionRuleEligiblity1.class));
          query.setString("institute_id", institute_id);
          query.setString("election_id", Election_id);
            obj= (List<ElectionRuleEligiblity1>) query.list();
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

    public static List ElectionID(String ElectionID,String instituteId)
{
Session session =null;
    List obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.electionId = :electionId and  id.instituteId=:instituteId");
                query.setString("electionId",ElectionID );
               query.setString("instituteId",instituteId);
                obj= query.list();
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
    public static List<Election> Elections(String instituteId)
{
Session session =null;
   List<Election> obj= null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.instituteId=:instituteId");
                query.setString("instituteId",instituteId);
                obj= query.list();
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

    public static List<Election> getCurrentElections(String instituteId,Date t)
{
Session session =null;
    List<Election> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.instituteId=:instituteId and nstart<=:systime and nend>=:systime");
                query.setString("instituteId",instituteId);
                query.setDate("systime",t);
                obj= query.list();
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

    public static List <Election> GetElectionDetails1(String institute_id,String managerid,int pageNumber)
    {
    Session session =null;
    List <Election> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "From Election  where id.instituteId=:institute_id and createdBy=:created_by";


            System.out.println(sql);

          Query query =  session.createQuery(sql);
              //    .addEntity(Election.class)
//                    .addEntity(Electionrule.class)
//                    .addEntity(Eligibility.class)
             //       .setResultTransformer(Transformers.aliasToBean(Election.class));
          query.setString("institute_id", institute_id);
          query.setString("created_by", managerid);


          //System.out.println(query1);
if(pageNumber==0){

            query = query.setFirstResult(0);
              query.setMaxResults(100);
             obj= (List<Election>) query.list();
}
else{             PagingAction o=new PagingAction(query,pageNumber,100);

obj= (List<Election>) query.list();
// System.out.println("Size of Record"+obj.size()+".........................."+pageNumber);
}


            
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



    public static List <Election> GetElectionDetailsbyinstituteId(String institute_id,String field,String value,String field1)
    {
//        if(field!=null)
//        field="a."+field;
//        if(field1!=null)
//        field1="a."+field1;
//
//        System.out.println(field+"   "+field1);
      
    Session session =null;
    List <Election> obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";
if(value!=null && value.isEmpty()==false)
{
//            sql = "select a.*,b.*,c.* from election a, electionrule b,eligibility c where a.election_id=b.election_id and "+field+" like '"+value+"%' and a.election_id=c.election_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id order by "+field1;
            sql = "From Election  where id.instituteId=:institute_id and "+field+" like '"+value+"%'  order by "+field1;
}
else
//            sql = "select a.*,b.*,c.* from election a, electionrule b,eligibility c where a.election_id=b.election_id and  a.election_id=c.election_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id order by "+field1;
            sql = "From Election  where id.instituteId=:institute_id  order by "+field1;


            System.out.println(sql);

          Query query =  session.createQuery(sql);
//                    .addEntity(Election.class)
//                    .addEntity(Electionrule.class)
//                    .addEntity(Eligibility.class)
//                    .setResultTransformer(Transformers.aliasToBean(ElectionRuleEligiblity1.class));
                   query.setString("institute_id", institute_id);
                  
            obj= (List<Election>) query.list();
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



     public List Positions(String instituteId)
{
Session session =null;
    List obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Position1  where id.instituteId=:instituteId");
                query.setString("instituteId",instituteId);
               obj= query.list();
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


      public List Position1(String instituteId,String electionId)
{
Session session =null;
    List obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Position1  where id.instituteId=:instituteId and id.electionId=:electionId");
                query.setString("instituteId",instituteId);
                query.setString("electionId",electionId);
                obj= query.list();
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


       public static Election Electionname(String instituteId,String electionId)
{
Session session =null;
    Election obj = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM Election  where id.instituteId=:instituteId and id.electionId=:electionId");
                query.setString("instituteId",instituteId);
                query.setString("electionId",electionId);
                obj=(Election) query.uniqueResult();
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

}
