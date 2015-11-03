/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author sknaqvi
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */

package pojo.hibernate;

import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;


public class WorkflowdetailDAO {


  public void save(Workflowdetail wfd) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.save(wfd);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(wfd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void update(Workflowdetail wfd) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.update(wfd);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(wfd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }


    public void delete(Workflowdetail wfd) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.delete(wfd);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(wfd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public List<Workflowdetail> findAll() {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
        List<Workflowdetail> list = session.createQuery("from Workflowdetail").list();
        
        return list;
        }
        finally {
            session.close();
            }
    }

    public Workflowdetail findWorkFlowDetailById(Integer wfdId) {
       
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
              String SQL =    "select u from Workflowdetail u where u.wfdId= :wfdId";
             tx = session.beginTransaction();
        List<Workflowdetail> wfd = session.createQuery(SQL).setParameter("wfdId", wfdId).list();
        
        return wfd.get(0);
        }
        finally {
            session.close();
            }
    }

   public Integer findWorkFlowDetailForWFM(Integer wfmId) {
        
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             String SQL =    "select max(u.wfdStage) from Workflowdetail u where u.workflowmaster.wfmId= :wfmId";
             tx = session.beginTransaction();
            Integer wfdStageCount  = Integer.parseInt(session.createQuery(SQL).setParameter("wfmId", wfmId).list().get(0).toString());
           
            return wfdStageCount + 1;
        }
        catch (Exception e)
        {   //This block is to handle no record case 
            return 1;
        }

        finally {
            session.close();
            }
    }

public List<Workflowdetail> findWorkFlowDetailsForWFM(Integer wfmId) {
       int index = 0;
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
              String SQL =    "select u from Workflowdetail u where u.workflowmaster.wfmId= :wfmId";
             tx = session.beginTransaction();
        List<Workflowdetail> wfdList  = session.createQuery(SQL).setParameter("wfmId", wfmId).list();
        for (index=0; index < wfdList.size(); ++index) {
            Hibernate.initialize(wfdList.get(index).getCommitteemasterByWfdSourceCommittee());
            Hibernate.initialize(wfdList.get(index).getCommitteemasterByWfdDestinationCommittee());
        }
       
        return wfdList;
        }
        finally {
            session.close();
            }
    }

public Committeemaster findSourceCommittee(int stage, Integer wfmId) {
       
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
              String SQL =    "select u from Workflowdetail u where u.wfdStage = :stage and u.workflowmaster.wfmId= :wfmId";
             tx = session.beginTransaction();
        List<Workflowdetail> wfd = session.createQuery(SQL).setParameter("stage", stage).setParameter("wfmId", wfmId).list();
            Hibernate.initialize(wfd.get(0).getCommitteemasterByWfdSourceCommittee());
        
        return wfd.get(0).getCommitteemasterByWfdSourceCommittee();
        }
        finally {
            session.close();
            }
}

public Committeemaster findDestinationCommittee(int stage, Integer wfmId) {
        
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             String SQL =    "select u from Workflowdetail u where u.wfdStage = :stage and u.workflowmaster.wfmId= :wfmId";
             tx = session.beginTransaction();
        List<Workflowdetail> wfd = session.createQuery(SQL).setParameter("stage", stage).setParameter("wfmId", wfmId).list();
            Hibernate.initialize(wfd.get(0).getCommitteemasterByWfdDestinationCommittee());
       
        return wfd.get(0).getCommitteemasterByWfdDestinationCommittee();
        }
        finally {
            session.close();
            }
}


public int findWorkFlowDetailIdByStage(int stage, Integer wfmId) {
        
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             String SQL =    "select u from Workflowdetail u where u.wfdStage = :stage and u.workflowmaster.wfmId= :wfmId";
             tx = session.beginTransaction();
        List<Workflowdetail> wfd = session.createQuery(SQL).setParameter("stage", stage).setParameter("wfmId", wfmId).list();
       
        return wfd.get(0).getWfdId();
        }
        finally {
            session.close();
            }
}


public Integer findLastWorkFlowStage(int wfmId) throws Exception{
        
        
           Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             String SQL =    "select if(max(u.wfdStage), max(u.wfdStage), 0)+0 from Workflowdetail u where u.workflowmaster.wfmId= :wfmId";
             tx = session.beginTransaction();
            Integer lastStage = Integer.parseInt(session.createQuery(SQL).setParameter("wfmId", wfmId).uniqueResult().toString());
           
                    return lastStage;
                    }

    catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
         finally {
            session.close();
        }
    }
}
