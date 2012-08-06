/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;

/**
 *
 * @author afreen
 */

public class UserMessageDAO  extends BaseDAO {

    public void save(UserMessage um) {
        try {
            beginTransaction();
            getSession().save(um);
            commitTransaction();
            }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;

        }
    }

    public void update(UserMessage um) {
        try {
            beginTransaction();
            getSession().update(um);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(UserMessage  um) {
        try {
            beginTransaction();
            getSession().delete(um);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<UserMessage> findAll() {
        beginTransaction();
        List<UserMessage> list = getSession().createQuery("select u from UserMessage  u").list();
        commitTransaction();
        return list;
    }

    public UserMessage  findByumId(int umId)
    {
        beginTransaction();
        UserMessage  um  = (UserMessage ) getSession().load(UserMessage.class , umId);
        commitTransaction();
        return um;
    }
 public List<UserMessage> findByUserId(Integer erpmuId) {
        beginTransaction();
        List<UserMessage> umList  =  getSession().createQuery("Select u from UserMessage u where u.erpmusersByUmToErpmuId.erpmuId = :erpmuId").setParameter("erpmuId", erpmuId).list();
        commitTransaction();
        return umList;
   }
public List<UserMessage> findByUserIdAndReqType(Integer erpmuId,String umReqType) {
        beginTransaction();
        List<UserMessage> umList  =  getSession().createQuery("Select u from UserMessage u where u.erpmusersByUmToErpmuId.erpmuId = :erpmuId AND u.umReqType=:umReqType").setParameter("erpmuId", erpmuId).setParameter("umReqType", umReqType).list();
        commitTransaction();
        return umList;
   }

public List<UserMessage> findByUserMessage(Integer umId) {
        beginTransaction();
        List<UserMessage> umList  =  getSession().createQuery("Select u from UserMessage u where u.umId = :umId").setParameter("umId", umId).list();
        commitTransaction();
        return umList;
   }

public Integer countUserMessages(Integer umId) {
        beginTransaction();
        Integer messageCount  =  Integer.parseInt(getSession().createQuery("Select count(u) from UserMessage u where u.erpmusersByUmToErpmuId.erpmuId = :umId and u.erpmGenMaster.erpmgmEgmId is null")
                                                  .setParameter("umId", umId).uniqueResult().toString());
        commitTransaction();
        return messageCount;
   }
}
