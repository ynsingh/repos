/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;
import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

/**
 *
 * @author afreen
 */

public class UserMessageDAO  {

    public void save(UserMessage um) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.save(um);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(um != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void update(UserMessage um) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
             session.update(um);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(um != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void delete(UserMessage  um) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
             session.delete(um);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(um != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public List<UserMessage> findAll() {
       Session session = HibernateUtil.getSession();
         try {
       session.beginTransaction();
        List<UserMessage> list = session.createQuery("select u from UserMessage  u").list();
        
        return list;
         }
        finally {
            session.close();
            }
    }

    public UserMessage  findByumId(int umId)
    {
        Session session = HibernateUtil.getSession();
         try {
       session.beginTransaction();
        UserMessage  um  = (UserMessage ) session.load(UserMessage.class , umId);
        
        return um;
         }
        finally {
            session.close();
            }
    }
 public List<UserMessage> findByUserId(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
         try {
             int index = 0;
       session.beginTransaction();
        List<UserMessage> umList  =  session.createQuery("Select u from UserMessage u where u.erpmusersByUmToErpmuId.erpmuId = :erpmuId and u.erpmGenMaster.erpmgmEgmId is null").setParameter("erpmuId", erpmuId).list();
        for (index = 0; index < umList.size(); ++index) {
                Hibernate.initialize(umList.get(index).getErpmusersByUmFromErpmuId());

            }        
        return umList;
         }
        finally {
            session.close();
            }
   }
public List<UserMessage> findByUserIdAndReqType(Integer erpmuId,String umReqType) {
       Session session = HibernateUtil.getSession();
         try {
       session.beginTransaction();
        List<UserMessage> umList  =  session.createQuery("Select u from UserMessage u where u.erpmusersByUmToErpmuId.erpmuId = :erpmuId AND u.umReqType=:umReqType").setParameter("erpmuId", erpmuId).setParameter("umReqType", umReqType).list();
        
        return umList;
         }
        finally {
            session.close();
            }
   }

public List<UserMessage> findByUserMessage(Integer umId) {
       Session session = HibernateUtil.getSession();
         try {
       session.beginTransaction();
        List<UserMessage> umList  =  session.createQuery("Select u from UserMessage u where u.umId = :umId").setParameter("umId", umId).list();
        
        return umList;
         }
        finally {
            session.close();
            }
   }

public Integer countUserMessages(Integer umId) {
        Session session = HibernateUtil.getSession();
         try {
       session.beginTransaction();
        Integer messageCount  =  Integer.parseInt(session.createQuery("Select count(u) from UserMessage u where u.erpmusersByUmToErpmuId.erpmuId = :umId and u.erpmGenMaster.erpmgmEgmId is null")
                                                 .setParameter("umId", umId).uniqueResult().toString());
        
        return messageCount;
         }
        finally {
            session.close();
            }
   }


}



