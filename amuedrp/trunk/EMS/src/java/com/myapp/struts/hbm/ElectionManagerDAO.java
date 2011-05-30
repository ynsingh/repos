/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;


import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Edrp-04
 */
public class ElectionManagerDAO {
public void insert(ElectionManager electionmanager)
    {
    Session session=null;
    Transaction tx=null;


    try{
        session=HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction();
        session.save(electionmanager);
        tx.commit();
    }

    catch(RuntimeException e){
        if(electionmanager!=null)
            tx.rollback();
        throw e;
    }

    finally{
     //   session.close();
    }

}
public void update(ElectionManager electionmanager)
    {
Session session=null;
Transaction tx=null;
try{
session=HibernateUtil.getSessionFactory().openSession();
tx=session.beginTransaction();
session.update(electionmanager);
tx.commit();
}
catch(RuntimeException e){
    tx.rollback();
    throw e;
    
}

}
public void delete(){

    }
public List<Election_Manager_StaffDetail> GetManagerDetails(String ElectionManagerId,String instituteId)
    {
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from staff_detail a, election_manager b,login c where a.staff_id=b.staff_id and a.staff_id=c.staff_id and a.institute_id=c.institute_id and a.institute_id=b.institute_id  and b.manager_id=:manager_id and b.institute_id=:institute_id";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(ElectionManager.class)
                    .addEntity(StaffDetail.class)
                    .addEntity(Login.class)
                    .setResultTransformer(Transformers.aliasToBean(Election_Manager_StaffDetail.class));
          query.setString("manager_id", ElectionManagerId);
          query.setString("institute_id", instituteId);
            return (List<Election_Manager_StaffDetail>)query.list();
        }
    finally {
            session.close();
        }
}

public List<Election_Manager_StaffDetail> GetManagers(String institute_id)
    {
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql="";

            sql = "select a.*,b.*,c.* from staff_detail a, election_manager b,login c where a.staff_id=b.staff_id and a.staff_id=c.staff_id and a.institute_id=c.institute_id  and  a.institute_id=b.institute_id and a.institute_id=:institute_id";


            System.out.println(sql);

          Query query =  session.createSQLQuery(sql)
                    .addEntity(ElectionManager.class)
                    .addEntity(StaffDetail.class)
                    .addEntity(Login.class)
                    .setResultTransformer(Transformers.aliasToBean(Election_Manager_StaffDetail.class));
          query.setString("institute_id", institute_id);
            return (List<Election_Manager_StaffDetail>) query.list();
        }
    finally {
            session.close();
        }
}


public List getStaffDetails(String staffId,String instituteId){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM ElectionManager where staffId=:staffId and id.instituteId=:instituteId");
             query.setString("staffId", staffId);
             query.setString("instituteId", instituteId);
            return query.list();
        }
        finally {
            session.close();
        }
}

public List getUserId(String user_id){
 Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM ElectionManager  where userId = :userId");
                query.setString("userId",user_id );
                return query.list();
        }
        finally {
            session.close();
        }
}


public List ManagerDeatils(String managerId,String instituteId)
{
Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("FROM ElectionManager  where id.managerId = :managerId and  id.instituteId=:instituteId");
                query.setString("managerId",managerId );
               query.setString("instituteId",instituteId);
                return query.list();
        }
        finally {
            session.close();
        }
}


}
