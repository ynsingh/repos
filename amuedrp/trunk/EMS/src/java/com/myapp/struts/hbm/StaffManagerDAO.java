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
public class StaffManagerDAO {




    public void insert(Election_Manager_StaffDetail staffmanager){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(staffmanager);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(staffmanager != null)
                tx.rollback();
            throw e;
        }
        finally {
          session.close();
        }
    }




    public void update(Election_Manager_StaffDetail staffmanager) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(staffmanager.getStaffDetail());
             session.update(staffmanager.getElectionManager());
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
                tx.rollback();
            throw e;
        }

}
}
