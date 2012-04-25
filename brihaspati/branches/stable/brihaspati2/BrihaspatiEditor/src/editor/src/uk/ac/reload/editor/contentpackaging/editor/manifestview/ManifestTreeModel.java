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

package uk.ac.reload.editor.contentpackaging.editor.manifestview;

import java.util.Iterator;

import org.jdom.Element;
import org.jdom.Namespace;

import uk.ac.reload.dweezil.gui.tree.DweezilTreeModel;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.prefs.EditorPrefs;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;
import uk.ac.reload.moonunit.contentpackaging.SCORM12_Core;
import uk.ac.reload.moonunit.schema.SchemaElement;

/**
 * A Tree Model for the Manifest Tree
 *
 * @author Phillip Beauvoir
 * @version $Id: ManifestTreeModel.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestTreeModel
extends DweezilTreeModel
{
	/**
	 * The CP Document
	 */
    private ContentPackage _contentPackage;
	
	public ManifestTreeModel() {
		
	}
	
	public void setContentPackage(ContentPackage cp) {
		_contentPackage = cp;
		// Create the root node
		root = new ManifestTreeNode(_contentPackage);
		// Build the child nodes
		buildChildren((ManifestTreeNode)root);
		// Reload the Model
		reload();
	}
	
	/**
	 * Build up child nodes
	 * @param node The Parent Node
	 */
	public void buildChildren(ManifestTreeNode node) {
		Iterator children = node.getElement().getChildren().iterator();
		while(children.hasNext()) {
			Element child = (Element)children.next();
			if(doShowNode(child)) {
				ManifestTreeNode newNode = new ManifestTreeNode(child);
				node.add(newNode);
				buildChildren(newNode);
			}
		}
	}
	
	/**
	 * @return Whether to display an Element on the Tree
	 */
	protected boolean doShowNode(Element element) {
		return doShowNode(element.getName(), element.getNamespace());
	}
	
	/**
	 * @return Whether to display an Element on the Tree
	 */
	protected boolean doShowNode(SchemaElement schemaElement) {
		return doShowNode(schemaElement.getName(), schemaElement.getNamespace());
	}
	
	/**
	 * @return Whether to display an Element on the Tree
	 */
	protected boolean doShowNode(String elementName, Namespace ns) {
		// No Metadata LOM Node
		if(_contentPackage.isMetadataRoot(elementName)) return false;
		
		// No TITLE Node
		if(elementName.equals(CP_Core.TITLE) &&
				ns.equals(_contentPackage.getRootNamespace())) return false;
		
		// SCORM Nodes
		if(ns.equals(CP_EditorHandler.ADLCP_NAMESPACE_12)) {
			// Hide all nodes except the Location one
			if(!elementName.equals(SCORM12_Core.LOCATION)) {
				boolean hideSCORM = EditorPrefs.getInstance().getBooleanValue(EditorPrefs.CP_HIDE_SCORM);
				return(hideSCORM == false);
			}
			else return true;
		}
		
		// RESOURCES Node
		if(elementName.equals(CP_Core.RESOURCES)) {
			boolean hideResources = EditorPrefs.getInstance().getBooleanValue(EditorPrefs.CP_HIDE_RESOURCES);
			return (hideResources == false);
		}
		
		return true;
	}
	
	/**
	 * @return Whether to allow to add an Element on the Tree
	 */
	protected boolean canAddNode(Element element) {
		return canAddNode(element.getName(), element.getNamespace());
	}
	
	/**
	 * @return Whether to allow to add an Element on the Tree
	 */
	protected boolean canAddNode(SchemaElement schemaElement) {
		return canAddNode(schemaElement.getName(), schemaElement.getNamespace());
	}
	
	/**
	 * @return Whether to allow to add an Element on the Tree
	 */
	protected boolean canAddNode(String elementName, Namespace ns) {
		if(elementName.equals(CP_Core.MANIFEST) &&
				ns.equals(_contentPackage.getRootNamespace()))
			return false;
		else if(elementName.equals(CP_Core.ORGANIZATIONS) &&
				ns.equals(_contentPackage.getRootNamespace()))
			return false;
		else if(elementName.equals(CP_Core.RESOURCES) &&
				ns.equals(_contentPackage.getRootNamespace()))
			return false;
		else return doShowNode(elementName, ns);
	}
}