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

   public ErpmIndentDetail  findByindtDetailId(short indtDetailId)
    {
        beginTransaction();
        ErpmIndentDetail  erpmindtdet  = (ErpmIndentDetail ) getSession().load(ErpmIndentDetail .class , indtDetailId);
        commitTransaction();
        return erpmindtdet;
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



}

