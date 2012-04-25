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

import javax.swing.Icon;

import org.jdom.Element;

import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.contentpackaging.CP_EditorHandler;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.gui.ElementTreeNode;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;



/**
 * A Tree Node for CP
 *
 * @author Phillip Beauvoir
 * @version $Id: ManifestTreeNode.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestTreeNode
extends ElementTreeNode
implements IIcons
{
	/**
	 * Constructor for Root Element
	 * @param cp The ContentPackage
	 */
	public ManifestTreeNode(ContentPackage cp) {
		super(cp);
	}
	
	/**
	 * Constructor for child Element
	 * @param element The JDOM Element
	 */
	public ManifestTreeNode(Element element) {
		super(element);
	}
	
	/**
	 * Over-ride this to do special stuff for a SCORM node
	 */
	public Icon getLeafIcon() {
		// See if Item has SCORM elements
	    String name = getElement().getName();
		if(name.equals(CP_Core.ITEM) && hasScormElements(getElement())) {
			return DweezilUIManager.getIcon(ICON_ITEMSCORM);
		}
		
		return super.getLeafIcon();
	}

	/**
	 * @return true if element has SCORM sub-elements
	 */
	public boolean hasScormElements(Element element) {
		Iterator it = element.getChildren().iterator();
		while(it.hasNext()) {
			Element child = (Element)it.next();
			if(child.getNamespacePrefix().equals(CP_EditorHandler.ADLCP_NAMESPACE_PREFIX)) {
			    return true;
			}
		}
		return false;
	}

	/**
	 * Over-ride this to do special stuff
	 */
	public String toString() {
		ContentPackage cp = (ContentPackage)getSchemaDocument();
		Element element = getElement();
		return cp.getElementDisplayName(element);
	}
}
