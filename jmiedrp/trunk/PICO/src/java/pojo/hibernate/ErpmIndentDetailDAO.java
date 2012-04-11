



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;
import java.util.*;
/**
 *
 * @author SajidAziz
 */
public class ErpmIndentDetailDAO  extends BaseDAO {
public void save(ErpmIndentDetail erpmindtdet) {
        try {
            beginTransaction();
            getSession().save(erpmindtdet);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmIndentDetail erpmindtdet) {
        try {
            beginTransaction();
            getSession().delete(erpmindtdet);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmIndentDetail erpmindtdet) {
        try {
            beginTransaction();
            getSession().update(erpmindtdet);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

  public List<ErpmIndentDetail> findAll() {
        beginTransaction();
        List<ErpmIndentDetail> list = getSession().createQuery("from ErpmIndentDetail").list();
        commitTransaction();
        return list;
    }

  /*
  select Indt_quantity  from erpm_indent_master as m,erpm_indent_detail as
d  where d.Indt_indt_mst_Indent_Id=m.Indt_Indent_Id and indt_USER_id=326;*/
  /*
   * public List<ErpmIndentMaster> findIndentsForUserDepartments(Integer erpmuId) {
        beginTransaction();
        String SQL = "Select u from ErpmIndentMaster u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId "
                    + "from Erpmuserrole d "
                    + "where d.erpmusers.erpmuId = :erpmuId)";
                    //+ "AND u.erpmusers.erpmuId =:erpmuId";
        List<ErpmIndentMaster> erpmindtmastList  = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return erpmindtmastList;
    }
   */

  public List<ErpmIndentDetail> findbyAll(Integer pomPoMasterId) {
        beginTransaction();
        String SQL=    "Select u from ErpmIndentDetail u , ErpmIndentMaster m "
                    +  "where m.erpmusers.erpmuId =:erpmuId";
                   // +  "and  u.Indt_indt_mst_Indent_Id=m.Indt_Indent_Id ";


        List<ErpmIndentDetail> list = getSession().createQuery(SQL).setParameter(pomPoMasterId, pomPoMasterId).list();
                                                                  // .setParameter(indtIndentId, indtIndentId)
                                                                   //.list();
        commitTransaction();
        return list;
    }

public List<ErpmIndentDetail> findAll(Integer pomPoMasterId) {
        String SQL = "select u  from ErpmIndentDetail u where u.indtDetailId not in " +
                        "(select v.erpmIndentDetail.indtDetailId from ErpmPoDetails v where v.erpmPoMaster.pomPoMasterId = :pomPoMasterId)";
        beginTransaction();
        List<ErpmIndentDetail> list = getSession().createQuery(SQL).setParameter("pomPoMasterId", pomPoMasterId).list();
        commitTransaction();
        return list;
    }

 public List<ErpmIndentDetail>  findByIndentMasterandDetail(Integer erpmuId,Integer simId) {
        beginTransaction();
        List<ErpmIndentDetail> Browselist  = getSession().createQuery("select distinct(u) from Departmentmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.departmentmaster.dmId = u.dmId and u.subinstitutionmaster.simId =:simId").setParameter("erpmuId", erpmuId).setParameter("simId",simId).list();
        commitTransaction();
        return Browselist;
   }

public List<ErpmIndentDetail> findDepartmentForUser(Short Indt_indt_mst_Indent_Id,Integer dmId) {
        beginTransaction();
        List<ErpmIndentDetail> dimList = getSession().createQuery("select u  from ErpmIndentDetail u, ErpmIndentMaster m where u.erpmIndentMaster.indtIndentId = :Indt_indt_mst_Indent_Id and m.departmentmaster.dmId=:dmId")
                                                    .setParameter("Indt_indt_mst_Indent_Id", Indt_indt_mst_Indent_Id)
                                                    .setParameter("dmId", dmId).list();

        commitTransaction();
        return dimList;
    }

   public ErpmIndentDetail  findByindtDetailId(short indtDetailId)
    {
        beginTransaction();
        ErpmIndentDetail  erpmindtdet  = (ErpmIndentDetail ) getSession().load(ErpmIndentDetail .class , indtDetailId);
        commitTransaction();
        return erpmindtdet;
    }


      public ErpmIndentDetail findByindtDetailByID(short indtDetailId) {
        beginTransaction();
        List<ErpmIndentDetail> pod  = getSession().createQuery("Select u from ErpmIndentDetail u where u.indtDetailId = :indtDetailId").setParameter("indtDetailId",indtDetailId).list();
        commitTransaction();
        return pod.get(0);
    }

   public List<ErpmIndentDetail>  findByindtIndentId(Short indtIndentId) {
        beginTransaction();
        List<ErpmIndentDetail> indentList  = getSession().createQuery("Select u from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :indtIndentId").setParameter("indtIndentId", indtIndentId).list();
        commitTransaction();
       return indentList;
   }

   public List<ErpmIndentDetail>  findByIndt_indt_mst_Indent_Id(Short Indt_indt_mst_Indent_Id) {
        beginTransaction();
        List<ErpmIndentDetail> Browselist  = getSession().createQuery("Select u from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :Indt_indt_mst_Indent_Id").setParameter("Indt_indt_mst_Indent_Id", Indt_indt_mst_Indent_Id).list();
        commitTransaction();
        return Browselist;
   }

      public List<ErpmIndentDetail>  findByIndentMasterandDetail(Short Indt_indt_mst_Indent_Id   ,short indtDetailId) {
        beginTransaction();
        List<ErpmIndentDetail> Browselist  = getSession().createQuery("Select u from ErpmIndentDetail u where u.erpmIndentMaster.indtIndentId = :Indt_indt_mst_Indent_Id and u.indtDetailId:indtDetailId")
                .setParameter("Indt_indt_mst_Indent_Id", Indt_indt_mst_Indent_Id)
                .setParameter("indtDetailId", indtDetailId).list();
        commitTransaction();
        return Browselist;
   }



}


