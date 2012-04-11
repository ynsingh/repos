package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmCapitalCategoryDao extends BaseDAO {
    public void save(ErpmCapitalCategory erpmcc) {
        try {
            beginTransaction();
            getSession().save(erpmcc);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(ErpmCapitalCategory erpmcc) {
        try {
            beginTransaction();
            getSession().update(erpmcc);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(ErpmCapitalCategory erpmcc) {
        try {
            beginTransaction();
            getSession().delete(erpmcc);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<ErpmCapitalCategory> findAll() {
        beginTransaction();
        List<ErpmCapitalCategory> list = getSession().createQuery("from ErpmCapitalCategory").list();
        commitTransaction();
        return list;
    }

    public ErpmCapitalCategory findByErpmccId(Integer erpmccId) {
        beginTransaction();
        ErpmCapitalCategory erpmcc  = (ErpmCapitalCategory) getSession().load(ErpmCapitalCategory.class , erpmccId);
        commitTransaction();
        return erpmcc;
}


 public List<ErpmCapitalCategory> findByImId(Short imId) {
        beginTransaction();
        List<ErpmCapitalCategory> erpmccList  =  getSession().createQuery("Select u from ErpmCapitalCategory u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
        commitTransaction();
        return erpmccList;
}

 public List<ErpmCapitalCategory> findForUser(Integer erpmuId) {
        beginTransaction();
        List<ErpmCapitalCategory> erpmccList  = getSession().createQuery("Select u from ErpmCapitalCategory u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return erpmccList;
}


    public Integer findDuplicateCC(Short imId, String erpmccDesc) {
        beginTransaction();
        Integer matchingRecords =  Integer.parseInt(getSession().createQuery("Select count(u)  from ErpmCapitalCategory u where u.institutionmaster.imId = :imId and upper(u.ermccDesc) = upper(:erpmccDesc)").setParameter("imId", imId).setParameter("erpmccDesc",erpmccDesc).list().get(0).toString());
        commitTransaction();
        return matchingRecords;
}

}