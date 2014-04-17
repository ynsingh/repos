package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.nmeict.smvdu.Beans.BranchMaster;
import org.nmeict.smvdu.Beans.DegreeType;
import org.nmeict.smvdu.Beans.OrgDepartmentType;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;
import org.nmeict.smvdu.Beans.SemesterMaster;
import org.nmeict.smvdu.HibernateHelper.HibernateDataSourceConnection;
import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/*
 * @author SHAISTA BANO
 */
 
public class OrgOtherFeeHeadMasterDAO implements IOrgOtherFeeHeadMasterDAO{

	
    private HibernateDataSourceConnection hibernateSessionFactory = new HibernateDataSourceConnection();
    public HibernateDataSourceConnection getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }

    public void setHibernateSessionFactory(HibernateDataSourceConnection hibernateSessionFactory) {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }

    @Override
    public void addNewFeeHeadCode(OtherFeeHeadMaster otherFeeHeadCode) {
        Session s=null;
	try {
            //System.out.println("Inserting...");
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            s.save(otherFeeHeadCode);
            s.getTransaction().commit();
	}
        catch(Exception ex){
            s.getTransaction().rollback();
            ex.printStackTrace();
	}
        finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
	}
		
    }

    @Override
    public void updateOtherFeeHeadCode(List<OtherFeeHeadMaster> otherFeeHeadCode) {
        Session s=null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            for(OtherFeeHeadMaster ofm : otherFeeHeadCode){
                OrgProfile op = new OrgProfile();
                OrgDepartmentType odt = new OrgDepartmentType();
                DegreeType dt = new DegreeType();
                
                SemesterMaster sm = new SemesterMaster();
                BranchMaster bm = new BranchMaster();
                op.setOrgId(new OrgProfileSessionDetails().getOrgProfileSession().getOrgId());
                odt.setOdtSeqNo(ofm.getDepartmentCode());
                dt.setSeqNo(ofm.getDegreeCode());
                sm.setSemSeqNo(ofm.getSemCode());
                bm.setBmSeqNo(ofm.getBranchCode());
                
                ofm.setOrgProfile(op);
                ofm.setOrgDepartmentType(odt);
                ofm.setDegreeType(dt);
                ofm.setSemesterMaster(sm);
                ofm.setBranchMaster(bm);
                
                s.update(ofm.getFeeHeadCode().toString(),ofm);
                s.flush();
                s.clear();
            }
            s.getTransaction().commit();
        }
        catch(Exception ex){
            s.getTransaction().rollback();
        }
    
        finally{
            if(s.isOpen()==true)
                HibernateDataSourceConnection.closeSession();
            }
        }

    @Override
    public List<OtherFeeHeadMaster> loadOtherFeeHeadCode() {
        List<OtherFeeHeadMaster> returnFeeHeadValue = new ArrayList<OtherFeeHeadMaster>();
	Session s=null;
        try{
            s = HibernateDataSourceConnection.currentSession();
            s.beginTransaction();
            ProjectionList projection  = Projections.projectionList();
            Criteria criteria = s.createCriteria(OtherFeeHeadMaster.class,"ofm");
            criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("semesterMaster","sem",CriteriaSpecification.LEFT_JOIN);
            criteria.createAlias("branchMaster","bm",CriteriaSpecification.LEFT_JOIN);
            projection.add(Projections.property("odt.orgDepartmentName"));
            projection.add(Projections.property("odt.odtSeqNo"));
            projection.add(Projections.property("degType.degreeName"));
            projection.add(Projections.property("degType.seqNo"));
            projection.add(Projections.property("sem.semesterName"));
            projection.add(Projections.property("sem.semSeqNo"));
            projection.add(Projections.property("bm.branchName"));
            projection.add(Projections.property("bm.bmSeqNo"));
            projection.add(Projections.property("ofm.feeHeadName"));
            projection.add(Projections.property("ofm.feeHeadCode"));
            projection.add(Projections.property("ofm.feeHeadValue"));
            projection.add(Projections.property("ofm.programId"));
            projection.add(Projections.property("ofm.feeHeadId"));
            criteria.setProjection(projection);
            criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
            List list = (List) criteria.list();
            Iterator i = list.iterator();
            while(i.hasNext()){
            	Object[] o = (Object[])i.next();
            	OtherFeeHeadMaster ofm = new OtherFeeHeadMaster();
            	ofm.setDepartmentName(o[0].toString());
            	ofm.setDepartmentCode((Integer)o[1]);
            	ofm.setDegreeName(o[2].toString());
            	ofm.setDegreeCode((Integer)o[3]);
            	ofm.setSemesterName(o[4].toString());
            	ofm.setSemCode((Integer)o[5]);
            	ofm.setBranchName(o[6].toString());
            	ofm.setBranchCode((Integer)o[7]);
            	ofm.setFeeHeadName(o[8].toString());
            	ofm.setFeeHeadCode((Integer)o[9]);
            	ofm.setFeeHeadValue((Double)o[10]); 
            	ofm.setProgramId(o[11].toString());
                ofm.setFeeHeadId((Integer)o[12]);
            	returnFeeHeadValue.add(ofm);
            }
            s.getTransaction().commit();
        }
	catch(Exception ex){
            s.getTransaction().rollback();
	}
        finally {
            if(s.isOpen()==true)
	        HibernateDataSourceConnection.closeSession();
	}
        return returnFeeHeadValue;
    }
        /*
         * Return All Fee heads as per degree, branch, semester
         */
    
        public List<OtherFeeHeadMaster> loadFeeHeadCode(int departmentCode, int degreeCode, int branchCode, int semCode) {
            List<OtherFeeHeadMaster> returnFeeHeadValue = new ArrayList<OtherFeeHeadMaster>();
            Session s=null;
            try{
                //System.out.println("departmentCode="+departmentCode+"\n degreeCode="+degreeCode+"\nbranchCode="+branchCode+"\nsemCode="+semCode);
		s = HibernateDataSourceConnection.currentSession();
                s.beginTransaction();
            	ProjectionList projection  = Projections.projectionList();
                Criteria criteria = s.createCriteria(OtherFeeHeadMaster.class,"ofm");
                criteria.createAlias("orgProfile","op",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("orgDepartmentType","odt",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("degreeType","degType",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("branchMaster","bm",CriteriaSpecification.LEFT_JOIN);
                criteria.createAlias("semesterMaster","sem",CriteriaSpecification.LEFT_JOIN);
                projection.add(Projections.property("odt.orgDepartmentName"));
                projection.add(Projections.property("odt.odtSeqNo"));
                projection.add(Projections.property("degType.degreeName"));
                projection.add(Projections.property("degType.seqNo"));
                projection.add(Projections.property("bm.branchName"));
                projection.add(Projections.property("bm.bmSeqNo"));
                projection.add(Projections.property("sem.semesterName"));
                projection.add(Projections.property("sem.semSeqNo"));
                projection.add(Projections.property("ofm.feeHeadName"));
                projection.add(Projections.property("ofm.feeHeadCode"));
                projection.add(Projections.property("ofm.feeHeadValue"));
                projection.add(Projections.property("ofm.programId"));
                projection.add(Projections.property("ofm.feeHeadId"));
                criteria.setProjection(projection);
                criteria.add(Restrictions.eq("op.orgId",new OrgProfileSessionDetails().getUserId()));
                criteria.add(Restrictions.eq("degType.seqNo", degreeCode));
                criteria.add(Restrictions.eq("bm.bmSeqNo", branchCode));
                criteria.add(Restrictions.eq("sem.semSeqNo", semCode));
                //System.out.println("OrgOtherFeeHeadMasterDAO 666666666="+criteria.list());
                List list = (List) criteria.list();
                Iterator i = list.iterator();
               
                while(i.hasNext()){
                    Object[] o = (Object[])i.next();
                    OtherFeeHeadMaster ofm = new OtherFeeHeadMaster();
                    if((Integer)o[5] == semCode){
	                ofm.setDepartmentName(o[0].toString());
        	        ofm.setDepartmentCode((Integer)o[1]);
                	ofm.setDegreeName(o[2].toString());
	                ofm.setDegreeCode((Integer)o[3]);
        	        ofm.setSemesterName(o[4].toString());
                	ofm.setSemCode((Integer)o[5]);
	                ofm.setBranchName(o[6].toString());
        	        ofm.setBranchCode((Integer)o[7]);
                	ofm.setFeeHeadName(o[8].toString());
	                ofm.setFeeHeadCode((Integer)o[9]);
        	        ofm.setFeeHeadValue((Double)o[10]); 
                	ofm.setProgramId(o[11].toString());
	                ofm.setFeeHeadId((Integer)o[12]);
        	        returnFeeHeadValue.add(ofm);
			}
		}
                s.getTransaction().commit();
            }
            catch(Exception ex){
                s.getTransaction().rollback();
            }
            finally {
                if(s.isOpen()==true)
        	        HibernateDataSourceConnection.closeSession();
            }
            return returnFeeHeadValue;
        }
        
	@Override
	public OtherFeeHeadMaster searchOtherFeeHeadCode(String orgEntity) {
		// TODO Auto-generated method stub
		return null;
	}
        
        public Exception deleteFeeHeadCode(OtherFeeHeadMaster otherFeeHeadCode) {
            
            Session s=null;
            try{
                //System.out.println("Deleting fee head code...");
                s = HibernateDataSourceConnection.currentSession();
                s.beginTransaction();
                s.delete(otherFeeHeadCode);
                s.getTransaction().commit();
                return null;
            }
            catch(Exception ex){
                s.getTransaction().rollback();
                ex.printStackTrace();
                return ex;
            }
            
            finally{
                if(s.isOpen()==true)
                    HibernateDataSourceConnection.closeSession();
            }

        }

    }
