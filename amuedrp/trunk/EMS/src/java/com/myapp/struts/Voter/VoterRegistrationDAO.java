/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.SetVoter;
import com.myapp.struts.hbm.VoterRegistration;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author akhtar
 */
public class VoterRegistrationDAO {
            public static VoterRegistration searchVoterEmail(String instituteid,String user_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        VoterRegistration obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("email",user_id))
                    .add(Restrictions.eq("id.instituteId", instituteid)));

            obj= (VoterRegistration) criteria.uniqueResult();
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


	public List<VoterRegistration> getVoterDetails(String instituteid){
  Session session =null;
    List<VoterRegistration> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
         session.beginTransaction();
            Query query = session.createQuery("FROM VoterRegistration where id.instituteId=:instituteId");
           
             query.setString("instituteId",instituteid );

            obj= (List<VoterRegistration>) query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException s){
    s.printStackTrace();
    }

        finally {
            session.close();
        }
        return obj;
}
    public static SetVoter searchVoterList(String instituteid,String electionId,String Enrollment,String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SetVoter obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SetVoter.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("id.electionId", electionId))
                            .add(Restrictions.eq("password", password))
                    .add(Restrictions.eq("id.instituteId", instituteid)));


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

public static SetVoter searchVoterList(String instituteid,String electionId,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SetVoter obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SetVoter.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("id.electionId", electionId))
                    .add(Restrictions.eq("id.instituteId", instituteid)));

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
    
    public static void setVoter(SetVoter obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
		session.saveOrUpdate(obj);
		boolean flg1;
	        do{
        		flg1=true;
            		wait1(1000);
            		tx = session.beginTransaction();
            		tx.commit();
                	if(!(tx.wasCommitted())){
                        	tx.rollback();
                        	flg1=false;
                	}
        	}
        	while(!(flg1));
           // session.saveOrUpdate(obj);
         //   tx = (Transaction) session.beginTransaction();
         //  tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
}
	private static void wait1(int k){
                do{
       			k--;
                }
                while(k>0);
        }
public static void insert(VoterRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
        
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
     //   return true;


}
public static boolean update(VoterRegistration obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            return false;

        }
        finally{
        session.close();
        }

        return true;

    }
        public static void delete(String enrollment,String instituteId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = (Query) session.createQuery("Delete From  VoterRegistration where id.enrollment = :en  and id.instituteId= :instituteid");

            query.setString("en", enrollment);
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
    public static VoterRegistration searchVoterRegistration(String instituteid,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        VoterRegistration obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(VoterRegistration.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("id.instituteId", instituteid)));

            obj= (VoterRegistration) criteria.uniqueResult();
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
  /*  public static SetVoter searchVoterList(String instituteid,String electionId,String Enrollment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SetVoter obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SetVoter.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.enrollment", Enrollment))
                    .add(Restrictions.eq("id.electionId", electionId))
                    .add(Restrictions.eq("id.instituteId", instituteid)));

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
   */ 
 public List getVoterList(String instituteid,String status,String field,String fieldvalue,String sort, int pageNumber){
  Session session =null;
    List obj=null;
    List finalResult = new ArrayList();
    try {
                session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                String query1 = "FROM VoterRegistration where id.instituteId=:instituteId";
            if(status==null){
            if(fieldvalue!=null)
                query1 = query1 + "  and "+field+"=:infield";

   query1=query1+" order by "+sort;
System.out.println(query1);

}
else{
            if(status!=null && !status.equalsIgnoreCase("AB") && fieldvalue!=null)
                query1 = query1 + " and status = :status and "+field+"=:infield ";
            else if(status!=null && !status.equalsIgnoreCase("AB"))
                query1 = query1 + " and status = :status ";

            if(status!=null && status.equalsIgnoreCase("AB"))
                    query1 = query1 + " and (status like 'Block' or status like 'Registered'";
           
query1=query1+" order by "+sort;
}

            Query query = session.createQuery(query1);

            if(fieldvalue!=null)
            {query.setString("infield", fieldvalue);

            }
            if(status!=null && !status.equalsIgnoreCase("AB"))
                query.setString("status",status );
             query.setString("instituteId",instituteid );
if(pageNumber==0){

            query = query.setFirstResult(0);
              query.setMaxResults(30);
              obj=query.list();
}
else{             PagingAction o=new PagingAction(query,pageNumber,30);

 obj= o.getList();

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

   public List getVoterDetailsByStatus(String instituteid,String status,String field,String fieldvalue,String sort, int pageNumber){
  Session session =null;
    List obj=null;
    List finalResult = new ArrayList();
    try {
        session= HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            String query1 = "FROM VoterRegistration where id.instituteId=:instituteId";
if(status==null){
  if(fieldvalue!=null)
                query1 = query1 + "  and "+field+"=:infield";

   query1=query1+" order by "+sort;
System.out.println(query1);




}
else{
            if(status!=null && !status.equalsIgnoreCase("AB") && fieldvalue!=null)
                query1 = query1 + " and status = :status and "+field+"=:infield ";
            else if(status!=null && !status.equalsIgnoreCase("AB"))
                query1 = query1 + " and status = :status ";

            if(status!=null && status.equalsIgnoreCase("AB"))
                    query1 = query1 + " and (status like 'Block' or status like 'Registered'";
            //else if(status!=null && status.equalsIgnoreCase("AB"))
              //      query1 = query1 + " and (status like 'Block' or status like 'Registered'";
query1=query1+" order by "+sort;
}

            Query query = session.createQuery(query1);

            if(fieldvalue!=null)
            {query.setString("infield", fieldvalue);

            }


            if(status!=null && !status.equalsIgnoreCase("AB"))
                query.setString("status",status );
             query.setString("instituteId",instituteid );


//System.out.println(query1);
if(pageNumber==0){

            query = query.setFirstResult(0);
              query.setMaxResults(100);
              obj=query.list();
}
else{             PagingAction o=new PagingAction(query,pageNumber,100);

 obj= o.getList();
// System.out.println("Size of Record"+obj.size()+".........................."+pageNumber);
}
//            //code for paging
//
//            int setMaxRecords = startLimit + noOfRecords + 1;
//            List result = obj;
//
//int count = result.size()-startLimit;
//if( count < noOfRecords) {
//noOfRecords = count;
//}
//int i=0;
////logger.info("Start Count "+startLimit + ".Records Accessed "+noOfRecords);
//while (noOfRecords > i) {
////logger.info("Start Count "+startLimit+i+",Rdcord "+result.get(startLimit+i));
//finalResult.add(result.get(startLimit+i));
//i++;
//}
//
//
//
//
//
//
//






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

public List getVoterDetailsByStatus(String instituteid,String status){
  Session session =null;
   Query query;
    List obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String query1 = "FROM VoterRegistration where id.instituteId=:instituteId";



            if(status==null){
            
            
            }else
            {
            if(status!=null && !status.equalsIgnoreCase("AB"))
                query1 = query1 + " and status = :status";
            if(status!=null && status.equalsIgnoreCase("AB"))

                query1 = query1 + " and (status like 'Block' or status like 'Registered'";



        
            



            }
                query = session.createQuery(query1);


                if(status!=null && !status.equalsIgnoreCase("AB"))
                query.setString("status",status );

                query.setString("instituteId",instituteid );

            obj= query.list();
            session.getTransaction().commit();
        }

    catch(RuntimeException r){
    r.printStackTrace();
    }
        finally {
            session.close();
        }
        return obj;
}

     public List<VoterRegistration> getVoterDetailsByStatus1(String instituteid){
  Session session =null;
    List<VoterRegistration> obj=null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
         
            Query query = session.createQuery("FROM VoterRegistration where status = :status and id.instituteId=:instituteId");
             query.setString("status","NOT REGISTERED" );
             query.setString("instituteId",instituteid );

            obj= (List<VoterRegistration>) query.list();
            session.getTransaction().commit();
        }
    catch(RuntimeException s){
    s.printStackTrace();
    }

        finally {
            session.close();
        }
        return obj;
}

   public Integer getVoterRequestCount(String instituteid,String status){
        Session session =null;
    Integer obj=null;
    try {

        session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
        Criteria criteria = session.createCriteria(VoterRegistration.class)
                 .setProjection(Projections.count("id.instituteId"));
        criteria.add(Restrictions.eq("status",status ));
         criteria.add(Restrictions.eq("id.instituteId",instituteid ));
            Integer countrequest = (Integer)criteria.uniqueResult();

           obj= countrequest;
         session.getTransaction().commit();
        }
    catch(RuntimeException d){
    d.printStackTrace();

    }
        finally {
             
            session.close();
        }
         
        return obj;
}
}


