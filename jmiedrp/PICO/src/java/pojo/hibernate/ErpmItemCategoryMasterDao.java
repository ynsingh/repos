package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmItemCategoryMasterDao extends BaseDAO {
     public void save(ErpmItemCategoryMasterDao erpmicm) {
        try {
            beginTransaction();
            getSession().save(erpmicm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(ErpmItemCategoryMasterDao erpmicm) {
        try {
            beginTransaction();
            getSession().update(erpmicm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(ErpmItemCategoryMasterDao erpmicm) {
        try {
            beginTransaction();
            getSession().delete(erpmicm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<ErpmItemCategoryMaster> findAll() {
        beginTransaction();
        List<ErpmItemCategoryMaster> list = getSession().createQuery("from ErpmItemCategoryMaster").list();
        commitTransaction();
        return list;
    }

    public ErpmItemCategoryMaster findByErpmicmItemId(Integer erpmicmItemId) {
        beginTransaction();
        ErpmItemCategoryMaster erpmicm  = (ErpmItemCategoryMaster) getSession().load(ErpmItemCategoryMaster.class , erpmicmItemId);
        commitTransaction();
        return erpmicm;
}

    public List<ErpmItemCategoryMaster> findByErpmicmItemLevel(Short erpmicmItemLevel) {
        beginTransaction();
        List<ErpmItemCategoryMaster> erpmicmList  = getSession().createQuery("Select u from ErpmItemCategoryMaster u where u.erpmicmItemLevel = :erpmicmItemLevel").setParameter("erpmicmItemLevel",erpmicmItemLevel).list();
        commitTransaction();
        return erpmicmList;
}

    public List<ErpmItemCategoryMaster> findByerpmItemCategoryMaster(Integer erpmItemCategoryMaster) {
        beginTransaction();
        List<ErpmItemCategoryMaster> erpmicmList  = getSession().createQuery("Select u from ErpmItemCategoryMaster u where u.erpmItemCategoryMaster.erpmicmItemId = :erpmItemCategoryMaster").setParameter("erpmItemCategoryMaster",erpmItemCategoryMaster).list();
        commitTransaction();
        return erpmicmList;
}

}