package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmuserroleDAO extends BaseDAO {
    public void save(Erpmuserrole erpmur) {
        try {
            beginTransaction();
            getSession().save(erpmur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Erpmuserrole erpmur) {
        try {
            beginTransaction();
            getSession().update(erpmur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Erpmuserrole erpmur) {
        try {
            beginTransaction();
            getSession().delete(erpmur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<Erpmuserrole> findAll() {
        beginTransaction();
        List<Erpmuserrole> list = getSession().createQuery("from Erpmuserrole").list();
        commitTransaction();
        return list;
    }

    public List<Erpmuserrole> findAllInactiveUsers() {
        beginTransaction();
        List<Erpmuserrole> list = getSession().createQuery("from Erpmuserrole where erpmurActive='N'").list();
        commitTransaction();
        return list;
    }

    public List<Erpmuserrole> findByErpmUserId(Integer erpmurId) {
        beginTransaction();
        List<Erpmuserrole> list = getSession().createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmurId").setParameter("erpmurId",erpmurId).list();
        commitTransaction();
        return list;
}

     public Erpmuserrole findByErpmUserRole(Integer erpmurId) {
        beginTransaction();
        List<Erpmuserrole> list = getSession().createQuery("Select u from Erpmuserrole u where u.erpmurId = :erpmurId").setParameter("erpmurId",erpmurId).list();
        commitTransaction();
        return list.get(0);
}

     public Erpmuserrole findDefaultUserRole(Integer erpmurId) throws Exception {
        try {
            beginTransaction();
            List<Erpmuserrole> list = getSession().createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmuId and u.erpmurDefault = 'Y'").setParameter("erpmuId",erpmurId).list();
            if (list.isEmpty()) {
                List<Erpmuserrole> list2 = getSession().createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmurId").setParameter("erpmurId",erpmurId).list();
                commitTransaction();
                return list2.get(0);
            }
            else{
                commitTransaction();
                return list.get(0);
            }
        }
        catch (Exception e)
        {
            return null;
         }
        }

    public List<Erpmuserrole> findActiveRolesByErpmUserId(Integer erpmurId) {
        beginTransaction();
        List<Erpmuserrole> list = getSession().createQuery("Select u from Erpmuserrole u where u.erpmusers.erpmuId = :erpmurId and erpmurActive = 'Y'").setParameter("erpmurId",erpmurId).list();
        commitTransaction();
        return list;
}

     public int  ClearDefaultProfile(Integer erpmurId) {
            beginTransaction();
            int x = getSession().createQuery("Update Erpmuserrole u set u.erpmurDefault = 0 where u.erpmusers.erpmuId = :erpmurId").setParameter("erpmurId",erpmurId).executeUpdate();
            commitTransaction();
        return x;}
    }

