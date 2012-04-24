/**
 *  RefToRefPeering.java
 *      
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */



/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 15/04/2012
 *    
 *  */


package org.bss.brihaspatisync.monitor.gui;

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
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bss.brihaspatisync.monitor.graphlayout.TGPanel;
import org.bss.brihaspatisync.monitor.util.ServerLog;


public class RefToRefPeering implements TreeSelectionListener{
	private JPanel mainPanel;
	private JScrollPane scrollPane=null;
	private JTree tree =null;
	static String refIP;

	
	private static RefToRefPeering tree_menu=null;

	public static RefToRefPeering getController(){
		if(tree_menu==null){
			tree_menu=new RefToRefPeering();
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
	
	 public void getSelectedRef(String str){
                refIP=str;
        }
 	
	
	protected static Vector brihaspatisync_v=new Vector();
        public static Vector brihaspati_reflector=new Vector();
	
	private static TreeModel createModel() {
		DefaultMutableTreeNode parent			= new DefaultMutableTreeNode("<html><font size=3 ><b>Reflector</b></font></html>", true);
		brihaspatisync_v.clear();
		Vector brihaspati_v=new Vector();
		brihaspati_v.clear();
		brihaspati_reflector.add(refIP);
		brihaspati_reflector.clear();
		brihaspati_reflector=org.bss.brihaspatisync.monitor.ReflectorManager.getController().getReflectorList();
		for(int j=0;j<brihaspati_reflector.size();j++){       
			String s="https://"+(brihaspati_reflector.elementAt(j))+":"+"9999"+"/brihaspatisync_reflector";
			for(int i=0;i<=brihaspati_v.size();i++) {
				if(!brihaspati_v.contains(s)) {
		          		brihaspati_v.add("https://"+(brihaspati_reflector.elementAt(j))+":"+"9999"+"/brihaspatisync_reflector");
				}
			}
		}
		
		for(int i=0;i<brihaspati_v.size();i++){
			        DefaultMutableTreeNode ab = new DefaultMutableTreeNode(brihaspati_v.firstElement().toString());
                                 parent.add(ab);

				 DefaultMutableTreeNode abc = new DefaultMutableTreeNode(brihaspati_v.get(i).toString());
                                 ab.add(abc);                
                                 brihaspatisync_v.add(brihaspati_v.get(i).toString());

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
			MainWindow.getController().getSelectedRef(str[0]);
                        MainWindow.getController().getdisplayPanel().add(MainWindow.getController().createCoursePanel(),BorderLayout.CENTER);
		}catch(Exception ex){System.out.println("======valueChanged===========>  "+ex.getMessage());}
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
					}catch(Exception e){System.out.println("---------");}	
				}
				
                        }catch(Exception trexc){ System.out.println("Error prppprr  prr   "); }
                        return this;
         	}
        }

}

