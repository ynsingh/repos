package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmGenMasterDao extends BaseDAO {
    public void save(ErpmGenMaster erpmgm) {
        try {
            beginTransaction();
            getSession().save(erpmgm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(ErpmGenMaster erpmgm) {
        try {
            beginTransaction();
            getSession().update(erpmgm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(ErpmGenMaster erpmgm) {
        try {
            beginTransaction();
            getSession().delete(erpmgm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<ErpmGenMaster> findAll() {
        beginTransaction();
        List<ErpmGenMaster> list = getSession().createQuery("from ErpmGenMaster").list();
        commitTransaction();
        return list;
    }

    public ErpmGenMaster findByErpmGmId(int erpmgmEgmId) {
        beginTransaction();
        ErpmGenMaster erpmgm  = (ErpmGenMaster) getSession().load(ErpmGenMaster.class , erpmgmEgmId);
        commitTransaction();
        return erpmgm;
}

    public List<ErpmGenMaster> findByErpmGmType(short erpmgmEgmType) {
        beginTransaction();
        List<ErpmGenMaster> erpmgmlist  = getSession().createQuery("Select u from ErpmGenMaster u where u.erpmGenCtrl.erpmgcGenType = :erpmgmEgmType").setParameter("erpmgmEgmType",erpmgmEgmType).list();
        commitTransaction();
        return erpmgmlist;
    }

    public Integer findDuplicateGeneralMasterEntry(short erpmgcGenType, String erpmgmEgmDesc) {
        beginTransaction();
        Integer matchingRecords  = Integer.parseInt(getSession().createQuery("Select count(u) from ErpmGenMaster u.erpmGenCtrl.erpmgcGenType = :erpmgcGenType and upper(u.erpmgmEgmDesc) = upper(:erpmgmEgmDesc)").setParameter("erpmgcGenType",erpmgcGenType).setParameter("erpmgmEgmDesc",erpmgmEgmDesc).list().get(0).toString());
        commitTransaction();
        return matchingRecords;
}
public ErpmGenMaster findByErpmGmDesc(String erpmgmEgmDesc) {
        beginTransaction();
        List<ErpmGenMaster> erpmgmlist  = getSession().createQuery("Select u from ErpmGenMaster u where u.erpmgmEgmDesc = :erpmgmEgmDesc").setParameter("erpmgmEgmDesc",erpmgmEgmDesc).list();
        commitTransaction();
        return erpmgmlist.get(0);
    }

public int findDefaultCurrency(String erpmgmEgmDesc) {
        beginTransaction();
        List<ErpmGenMaster> erpmgmlist  = getSession().createQuery("Select u from ErpmGenMaster u where u.erpmgmEgmDesc = :erpmgmEgmDesc").setParameter("erpmgmEgmDesc",erpmgmEgmDesc).list();
        commitTransaction();
        return erpmgmlist.get(0).getErpmgmEgmId();
    }


 public List<ErpmGenMaster> findByErpmGmTypebyInsitute(short erpmgmEgmType,Short imId) {
        beginTransaction();
        List<ErpmGenMaster> erpmgmlist  = getSession().createQuery("Select u from ErpmGenMaster r,ErpmGeneralTerms u where  r.erpmgmEgmId=u.erpmGenMaster.erpmgmEgmId and r.erpmGenCtrl.erpmgcGenType = :erpmgmEgmType and u.institutionmaster.imId=:imId").setParameter("erpmgmEgmType",erpmgmEgmType).setParameter("imId", imId).list();
        commitTransaction();
        return erpmgmlist;
    }







    
}