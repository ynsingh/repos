/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2003 Oleg Liber, Bill Olivier, Phillip Beauvoir
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *  Project Management Contact:
 *
 *  Oleg Liber
 *  Bolton Institute of Higher Education
 *  Deane Road
 *  Bolton BL3 5AB
 *  UK
 *
 *  e-mail:   o.liber@bolton.ac.uk
 *
 *
 *  Technical Contact:
 *
 *  Phillip Beauvoir
 *  e-mail:   p.beauvoir@bolton.ac.uk
 *
 *  Web:      http://www.reload.ac.uk
 *
 */

package uk.ac.reload.editor.contentpackaging.editor.resourceview;

import java.io.File;
import java.util.Enumeration;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeModel;
import uk.ac.reload.editor.contentpackaging.datamodel.CP_Resource;


/**
 * A Tree Model for the CP Resources Tree
 *
 * @author Phillip Beauvoir
 * @author Paul Sharples
 * @version $Id: CP_ResourcesTreeModel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class CP_ResourcesTreeModel
extends DweezilTreeModel
{
	/**
	 * The Root Folder
	 */
    private CP_Resource _rootFolder;
	
	/**
	 * Constructor
	 */
	public CP_ResourcesTreeModel() {
		super();
	}
	
	/**
	 * Set a new CP Project to be displayed
	 */
	public void setFileView(File rootFolder) {
		_rootFolder = new CP_Resource(rootFolder);
		refresh();
	}
	
	/**
	 * Refresh the model from scratch
	 */
	public void refresh() {
		root = new CP_ResourcesTreeNode(_rootFolder);
		buildFirstNodes((CP_ResourcesTreeNode)root);
		reload();
	}
	
	/**
	 * Build up child nodes
	 * Only dig down one level, the Tree Expansion Listener in the Tree will do the rest
	 */
	private void buildFirstNodes(CP_ResourcesTreeNode parentNode) {
		if(parentNode == null) {
		    return;
		}
	    
		// Already done
		if(parentNode.childNodesAdded) {
		    return;
		}

		CP_Resource resource = parentNode.getCP_Resource();
		CP_Resource[] children = resource.getChildren();
		
		if(children != null) {
			for(int i = 0; i < children.length; i++) {
				CP_ResourcesTreeNode newNode = new CP_ResourcesTreeNode(children[i]);
				parentNode.add(newNode);
				if(children[i].isDirectory()) {
				    CP_Resource[] children2 = children[i].getChildren();
				    if(children2 != null) for(int j = 0; j < children2.length; j++) {
				        CP_ResourcesTreeNode newNode2 = new CP_ResourcesTreeNode(children2[j]);
				        newNode.add(newNode2);
				    }
				}
			}
		}
		
		parentNode.childNodesAdded = true;
	}
	
    /**
	 * Add the grandchild nodes of the parent node
	 * @param parentNode The node to add the grandchildren
	 */
	public void buildChildNodes(CP_ResourcesTreeNode parentNode) {
		if(parentNode == null) {
		    return;
		}
	    
		// Already done
		if(parentNode.childNodesAdded) {
		    return;
		}

		Enumeration enum1 = parentNode.children();
		while(enum1.hasMoreElements()) {
		    CP_ResourcesTreeNode sub_node = (CP_ResourcesTreeNode)enum1.nextElement();
		    CP_Resource resource = sub_node.getCP_Resource();
		    if(resource.isDirectory()) {
		        CP_Resource[] children = resource.getChildren();
		        if(children != null) {
		            for(int i = 0; i < children.length; i++) {
		                CP_ResourcesTreeNode newNode = new CP_ResourcesTreeNode(children[i]);
		                insertNodeInto(newNode, sub_node, sub_node.getChildCount());
		            }
		        }
		    }
		}
		
		parentNode.childNodesAdded = true;
	}
}
