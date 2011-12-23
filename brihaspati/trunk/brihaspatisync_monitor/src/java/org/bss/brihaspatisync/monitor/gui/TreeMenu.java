package org.bss.brihaspatisync.monitor.gui;

/**
 * TreeMenu.java
 *      
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.util.Vector;
import java.util.StringTokenizer;

import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListCellRenderer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Component;
import java.net.MalformedURLException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bss.brihaspatisync.monitor.graphlayout.TGPanel;
import org.bss.brihaspatisync.monitor.util.ServerLog;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */


public class TreeMenu implements TreeSelectionListener{
	private JPanel mainPanel;
	private JScrollPane scrollPane=null;
	private JTree tree =null;
	
	private static TreeMenu tree_menu=null;

	public static TreeMenu getController(){
		if(tree_menu==null){
			tree_menu=new TreeMenu();
		}
		return tree_menu;
	}

	 public JScrollPane createGUI(){
		tree = new JTree(createModel());
		tree.setRowHeight(25);
		tree.expandPath(tree.getPathForRow(2));
                tree.expandPath(tree.getPathForRow(1));
		tree.expandPath(tree.getPathForRow(3));
		tree.setCellRenderer(new MyTreeCellRenderer());
		tree.addTreeSelectionListener(this);
		for (int i = 0; i < tree.getRowCount(); i++) {
         		tree.expandRow(i);
		}			
	   	scrollPane = new JScrollPane(tree);
		return scrollPane;

	}
		
	protected static Vector brihaspatisync_v=new Vector();
        protected static Vector brihaspati_reflector=new Vector();
	
	private static TreeModel createModel() {
		DefaultMutableTreeNode parent			= new DefaultMutableTreeNode("<html><font size=3 ><b>Brihaspati</b></font></html>", true);
      		DefaultMutableTreeNode brihaspati 		= new DefaultMutableTreeNode("<html><font size=3 ><b>brihaspati</b></font></html>",true);
      		DefaultMutableTreeNode brihaspatisync 		= new DefaultMutableTreeNode("<html><font size=3 ><b>brihaspatisync</b></font></html>",true);
      		DefaultMutableTreeNode reflector 		= new DefaultMutableTreeNode("<html><font size=3 ><b>Reflector</b></font></html>",true);
		brihaspatisync_v.clear();
		Vector brihaspati_v=new Vector();
		brihaspati_v.clear();
		brihaspati_v=org.bss.brihaspatisync.monitor.util.ClientObject.getController().getbrihaspatiServerAddr();
		if(!brihaspati_v.contains("reflector")) 
		brihaspati_v.add("reflector");
		brihaspati_reflector.clear();
		brihaspati_reflector=org.bss.brihaspatisync.monitor.ReflectorManager.getController().getReflectorList();
		for(int j=0;j<brihaspati_reflector.size();j++){       
			String s="https://"+(brihaspati_reflector.elementAt(j))+":"+"9999"+"/brihaspatisync_reflector";
			for(int i=0;i<brihaspati_v.size();i++) {
				if(!brihaspati_v.contains(s)) {
		          		brihaspati_v.add("https://"+(brihaspati_reflector.elementAt(j))+":"+"9999"+"/brihaspatisync_reflector");
				}
			}
		}
		parent.add(brihaspatisync);
		parent.add(reflector);
		parent.add(brihaspati);
		
		boolean rflag=false;
		boolean flag=false;
		for(int i=0;i<brihaspati_v.size();i++){
			String str=brihaspati_v.get(i).toString().trim();
			if(!flag){
				if(str.equals("brihaspatisync")) {
					flag=true;
				}
				else if(!str.equals("brihaspati")) {
					DefaultMutableTreeNode abc = new DefaultMutableTreeNode(brihaspati_v.get(i).toString());
					brihaspati.add(abc);
					brihaspatisync_v.add(brihaspati_v.get(i).toString());
				}
			}if(flag)  {
				if(!rflag){
					 if(str.equals("reflector")) {
					 	rflag=true;
					 }
					else if(!str.equals("brihaspatisync")){
						DefaultMutableTreeNode abc = new DefaultMutableTreeNode(brihaspati_v.get(i).toString());
				        	brihaspatisync.add(abc);		
						brihaspatisync_v.add(brihaspati_v.get(i).toString());
					}
				}
			}if(rflag && !str.equals("reflector")){
				 DefaultMutableTreeNode abc = new DefaultMutableTreeNode(brihaspati_v.get(i).toString());
                                 reflector.add(abc);                
                                 brihaspatisync_v.add(brihaspati_v.get(i).toString());

			 }
		}
		CheckStatusServer.getController().checkServer(brihaspatisync_v);	
		return new DefaultTreeModel(parent);
	}
	public void valueChanged(TreeSelectionEvent e) {
		try {	
			String tempstr=tree.getLastSelectedPathComponent().toString().trim();
			String temp="";
                        if(tempstr.startsWith("http://"))
                        	temp=tempstr.replace("http://","");
                      	if(tempstr.startsWith("https://"))
                        	temp=tempstr.replace("https://","");
                       	temp=temp.substring(0,temp.indexOf("/"));
                        temp=temp.trim();
                        String str[]=temp.split(":");
			TGPanel tgp=new TGPanel();
                        tgp.getSelectedRef(str[0]);
			MainWindow.getController().getRightPanel().remove(1);
			MainWindow.getController().getRightPanel().add(MainWindow.getController().createMonitorPanel(),BorderLayout.PAGE_START);
			MainWindow.getController().getRightPanel().revalidate();
		}catch(Exception ex){System.out.println("Error in catch valueChanged in TreeMenu.java!!"+ex.getMessage());}
	}
	private static class MyTreeCellRenderer extends DefaultTreeCellRenderer {
		private ClassLoader clr= this.getClass().getClassLoader();
               	Vector brihaspatisync_v=TreeMenu.getController().brihaspatisync_v;
		private MyTreeCellRenderer() {}
                public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,boolean expanded, boolean leaf, int row, boolean hasFocus) {
                        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                        if (!(value instanceof DefaultMutableTreeNode))
                                return this;
                        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)value;
                        String tempstr=treeNode.toString().trim();
			try{
				if(brihaspatisync_v.contains(tempstr)){	
					try {
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
					        JLabel label = (JLabel) this ;
						String temp="";
						if(tempstr.startsWith("https://"))
							temp=tempstr.replace("https://","");
						if(tempstr.startsWith("http://"))
                                                        temp=tempstr.replace("http://","");
						temp=temp.substring(0,temp.indexOf("/"));
						temp=temp.trim();
						String str[]=temp.split(":");
						boolean flag=CheckStatusServer.getController().flagServer_Running(str[0].trim()+":"+str[1].trim());
						if(flag){
	                                                label.setIcon(new ImageIcon(clr.getResource("Icons&Logos/green.png")));
					
                                                }else {
                                                       	label.setIcon(new ImageIcon(clr.getResource("Icons&Logos/red.png")));
							ServerLog.getController().Log(str[0].trim()+":"+str[1].trim());

						}
					}catch(Exception e){System.out.println("Error catch in getTreeCellRendererComponent in TreeMenu.java !!"+e);}	
				}
				
                        }catch(Exception trexc){ System.out.println("Error in catch MyTreeCellRenderer in TreeMenu.java !!"+trexc); }
                        return this;
         	}
        }

}

