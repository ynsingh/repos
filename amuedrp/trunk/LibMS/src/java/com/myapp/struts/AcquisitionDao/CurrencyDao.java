/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zeeshan
 */
public class CurrencyDao {

     public void insert(AcqCurrency employee){
    Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(employee != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

}
