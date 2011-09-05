/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;
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
public class EmailSetupDAO {


    public void insert(EmailSetup emailsetup)
    {
    Session session=null;
    Transaction tx=null;


    try{
        session=HibernateUtil.getSessionFactory().openSession();
        tx=session.beginTransaction();
        session.save(emailsetup);
        tx.commit();
    }

    catch(RuntimeException e){
        if(emailsetup!=null)
            tx.rollback();
        throw e;
    }

    
}

    public void update(EmailSetup emailsetup)
    {
Session session=null;
Transaction tx=null;
try{
session=HibernateUtil.getSessionFactory().openSession();
tx=session.beginTransaction();
session.update(emailsetup);
tx.commit();
}
catch(RuntimeException e){
    tx.rollback();
    throw e;

}

}

    public EmailSetup getEmailId(String instituteId){
  Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM EmailSetup where id.instituteId = :instituteId");
            query.setString("instituteId", instituteId);
            return (EmailSetup)query.uniqueResult();
        }
        finally {
            session.close();
        }
}

}
