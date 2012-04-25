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

package uk.ac.reload.editor.metadata.editor.tableview;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uk.ac.reload.editor.gui.ElementInfoPanel;
import uk.ac.reload.editor.menu.Menu_Edit;
import uk.ac.reload.editor.metadata.xml.Metadata;

/**
 * A Metadata Table View Panel for the Metadata UI
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_TablePanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_TablePanel
extends JPanel
{
    /**
     * The Treetable
     */
    private MD_TreeTable _treeTable;

    /**
     * The info Panel
     */
    private ElementInfoPanel _infoPanel;

    /**
     * ScrollPane
     */
    private JScrollPane _scrollPane;

    /**
     * Splitpane
     */
    private JSplitPane splitPane;

    /**
     * The Edit menu which we will use
     */
    private Menu_Edit _editMenu;

    /**
     * Constructor
     * @param editMenu The Metadata Model that we will View/Edit
     */
    public MD_TablePanel(Menu_Edit editMenu) {
        _editMenu = editMenu;

        setLayout(new BorderLayout());

        // Create new ScrollPane
        _scrollPane = new JScrollPane();

        // Create new Info panel
        _infoPanel = new ElementInfoPanel();

        // Set up a Split Pane
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setOneTouchExpandable(true);
        splitPane.setTopComponent(_scrollPane);
        splitPane.setBottomComponent(_infoPanel);
        add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Set the view up to display the Metadata Document
     * @param metadata
     */
    public void setDocument(Metadata metadata) {
        // Clean up first
        cleanup();

        // Set up a TreeTable and Model
        MD_TreeTableModel model = new MD_TreeTableModel(metadata);
        _treeTable = new MD_TreeTable(model, _infoPanel, _editMenu);

        // Add it to the JScrollPane
        _scrollPane.setViewportView(_treeTable);

        // Select Root Node
        _treeTable.selectNode((MD_TreeNode)model.getRoot());
    }

    /**
     * Clean up
     */
    public void cleanup() {
        if(_treeTable != null) {
            _treeTable.cleanup();
        }
        
        if(_infoPanel != null) {
            _infoPanel.cleanup();
        }
    }

    /**
     * Refresh the View
     */
    public void refresh() {
        _treeTable.refresh();
    }

    /**
     * Called after Panel is shown so we can init stuff
     */
    public void initView() {
        splitPane.setDividerLocation(0.7);
        _infoPanel.initView();
    }

    /**
     * @return MD_TreeTable
     */
    public MD_TreeTable getMD_TreeTable() {
        return _treeTable;
    }
}
