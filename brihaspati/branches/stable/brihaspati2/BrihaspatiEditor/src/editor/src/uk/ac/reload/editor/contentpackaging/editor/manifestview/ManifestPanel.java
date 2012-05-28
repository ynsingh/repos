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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uk.ac.reload.editor.contentpackaging.editor.CP_Editor;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;


/**
 * A Content Package Manifest Panel for the CP UI
 *
 * @author Phillip Beauvoir
 * @version $Id: ManifestPanel.java,v 1.1 1998/03/26 15:40:31 ynsingh Exp $
 */
public class ManifestPanel
extends JPanel
{
	/**
	 * The Tree
	 */
    private ManifestTree _manifestTree;
	
	/**
	 * The info Panel
	 */
    private ManifestInfoPanel _infoPanel;
	
	/**
	 * Splitpane
	 */
	private JSplitPane _splitPane;
	
	/**
	 * Constructor
	 */
	public ManifestPanel(CP_Editor cpEditor) {
		setLayout(new BorderLayout());
		
		// Create new Info panel
		_infoPanel = new ManifestInfoPanel();
		
		_manifestTree = new ManifestTree(cpEditor, _infoPanel);
		
		// Set up a Split Pane
		_splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		_splitPane.setOneTouchExpandable(true);
		
		JScrollPane sp = new JScrollPane(_manifestTree);
		sp.setBorder(null);
		_splitPane.setTopComponent(sp);
		
		_splitPane.setBottomComponent(_infoPanel);
		
		add(_splitPane, BorderLayout.CENTER);
	}
	
	/**
	 * Set a new Content Package to be displayed
	 * @param cp
	 */
	public void setContentPackage(ContentPackage cp) {
		_manifestTree.setContentPackage(cp);
	}
	
	/**
	 * @return The ManifestTree
	 */
	public ManifestTree getManifestTree() {
	    return _manifestTree;
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
		if(_manifestTree != null) {
		    _manifestTree.cleanup();
		}
		
		if(_infoPanel != null) {
		    _infoPanel.cleanup();
		}
	}
	
	/**
	 * Called after Panel is shown so we can init stuff
	 */
	public void initView() {
		_splitPane.setDividerLocation(0.7);
		_infoPanel.initView();
	}
	
}