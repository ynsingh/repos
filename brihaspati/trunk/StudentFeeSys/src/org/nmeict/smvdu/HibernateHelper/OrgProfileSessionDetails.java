package org.nmeict.smvdu.HibernateHelper;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.OrgLogoDetails;
import org.nmeict.smvdu.Beans.OrgProfile;


@ManagedBean(name="orgProfileSessionDetails")
@SessionScoped
public class OrgProfileSessionDetails implements Serializable{

	
	
	private OrgLoginDetails old;
	
	public OrgProfileSessionDetails() {
		old = (OrgLoginDetails) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("org");
		if(old == null)
		{
			
		}
		else
		{
			userId = old.getOrgId();
			//path= FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\")+"img"+File.separator+userId;
			path= FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"img"+File.separator+userId;
			//"E:\\"+File.separator+userId+File.separator;
			//System.out.println("Path in OrgProfileSessionDetail class==: "+path);
			
			this.orgProfileSession= old.getOrgProfile(); 
//			this.orgProfileSession.setOrgAdminemailid(old.getAdminId());
		}
	}
	private String userId;
	private OrgProfile orgProfileSession;
	private String path;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPath() {
		//path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public OrgProfile getOrgProfileSession() {
		return orgProfileSession;
	}
	public void setOrgProfileSession(OrgProfile orgProfileSession) {
		this.orgProfileSession = orgProfileSession;
	}
	
}
