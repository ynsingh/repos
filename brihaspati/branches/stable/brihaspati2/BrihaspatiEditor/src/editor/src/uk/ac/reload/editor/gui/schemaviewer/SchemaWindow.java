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

package uk.ac.reload.editor.gui.schemaviewer;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import uk.ac.reload.editor.EditorInternalFrame;
import uk.ac.reload.moonunit.SchemaController;

/**
 * A Viewer Frame that will display the information associated with
 * a SchemaElement and its Attributes.  This is for testing purposes only.
 *
 * @author Phillip Beauvoir
 * @version $Id: SchemaWindow.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class SchemaWindow
extends EditorInternalFrame
{

    public SchemaWindow(SchemaController schemaController, String title) {
        super(title + " - " + schemaController.getSchemaModel().getTargetNamespaceURI());
        
        // New Viewer and Tree
        NodeViewer nodeViewer = new NodeViewer(schemaController);
        SchemaTree tree = new SchemaTree(schemaController, nodeViewer);

        // Split pane
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jsp.setOneTouchExpandable(true);
        jsp.setLeftComponent(new JScrollPane(tree));
        jsp.setRightComponent(new JScrollPane(nodeViewer));
        getContentPane().add(jsp);
        jsp.setDividerLocation(320);
    }

    /* (non-Javadoc)
     * @see uk.ac.reload.editor.ApplicationListener#applicationClosing()
     */
    public boolean applicationClosing() {
        return true;
    }
}