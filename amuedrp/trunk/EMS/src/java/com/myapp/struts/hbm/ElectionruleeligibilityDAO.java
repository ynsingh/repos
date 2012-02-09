/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

/**
 *
 * @author Edrp-04
 */

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ElectionruleeligibilityDAO {


    public void insert(ElectionRuleEligiblity1 electionruleeligibility){
    Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(electionruleeligibility.getElection());
            session.save(electionruleeligibility.getElectionrule());
            session.save(electionruleeligibility.getEligibility());
            session.save(electionruleeligibility.getBallot());
            tx.commit();
        }
        catch (RuntimeException e) {
            if(electionruleeligibility != null)
                tx.rollback();
            throw e;
        }
        finally {
          session.close();
        }
    }

    public void update(ElectionRuleEligiblity1 electionruleeligibility) {
        Session session =null;
    Transaction tx = null;
    try {
        session= HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(electionruleeligibility.getElection());
            session.update(electionruleeligibility.getElectionrule());
            session.update(electionruleeligibility.getEligibility());
            session.update(electionruleeligibility.getBallot());
            tx.commit();
        }
        catch (RuntimeException e) {
          //  if(bibDetails != null)
            e.printStackTrace();
                tx.rollback();
            throw e;
        }
finally {
          session.close();
        }
}


}
