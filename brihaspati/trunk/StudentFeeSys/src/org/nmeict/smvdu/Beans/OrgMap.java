package org.nmeict.smvdu.Beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.nmeict.smvdu.HibernateHelper.OrgProfileSessionDetails;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;


@ManagedBean (name="orgMap")
@SessionScoped
public class OrgMap implements Serializable{

	
	private MindmapNode rootNode;
	
	public OrgMap()
	{
		rootNode = new DefaultMindmapNode("New College"); 
		System.out.println("Name : "+new OrgProfileSessionDetails().getOrgProfileSession().getOrgName());
	}

	public MindmapNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(MindmapNode rootNode) {
		this.rootNode = rootNode;
	}
	
}
