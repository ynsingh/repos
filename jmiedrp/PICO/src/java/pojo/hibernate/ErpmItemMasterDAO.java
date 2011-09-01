package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmItemMasterDAO extends BaseDAO {
    public void save(ErpmItemMaster erpmim) {
        try {
            beginTransaction();
            getSession().save(erpmim);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(ErpmItemMaster erpmim) {
        try {
            beginTransaction();
            getSession().update(erpmim);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(ErpmItemMaster erpmim) {
        try {
            beginTransaction();
            getSession().delete(erpmim);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<ErpmItemMaster> findAll() {
        beginTransaction();
        List<ErpmItemMaster> list = getSession().createQuery("from ErpmItemMaster").list();
        commitTransaction();
        return list;
    }

    public List<ErpmItemMaster> findByImId(Short imId) {
        beginTransaction();
        List<ErpmItemMaster> erpmimList  = getSession().createQuery("Select u from ErpmItemMaster u where u.institutionmaster.imId = :imId").setParameter("imId",imId).list();
        commitTransaction();
        return erpmimList;
}

        public ErpmItemMaster findByErpmimId(Integer erpmimId) {
        beginTransaction();
        ErpmItemMaster erpmIM  = (ErpmItemMaster) getSession().load(ErpmItemMaster.class , erpmimId);
        commitTransaction();
        return erpmIM;
    }

    public List<ErpmItemMaster> findItemsForUserInstitutes(Integer erpmuId) {
        beginTransaction();
        List<ErpmItemMaster> erpmimList  = getSession().createQuery("Select u from ErpmItemMaster u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return erpmimList;
}

    public Boolean hasDuplicate(String erpmuItemBriefDesc, Short imId) {
        beginTransaction();
        List<ErpmItemMaster> erpmimList  = getSession().createQuery("Select u from ErpmItemMaster u where u.erpmimItemBriefDesc = :erpmuItemBriefDesc and u.institutionmaster.imId = :imId").setParameter("erpmuItemBriefDesc",erpmuItemBriefDesc).setParameter("imId",imId).list();
        commitTransaction();
        if (erpmimList.isEmpty())
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
}

}