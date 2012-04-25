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

package uk.ac.reload.editor.metadata.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import uk.ac.reload.dweezil.gui.DweezilFileChooser;
import uk.ac.reload.dweezil.gui.DweezilFileFilter;
import uk.ac.reload.dweezil.gui.DweezilToolBar;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.editor.EditorFrame;
import uk.ac.reload.editor.EditorHandler;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.contentpackaging.xml.CP_SchemaController;
import uk.ac.reload.editor.contentpackaging.xml.ContentPackage;
import uk.ac.reload.editor.menu.Menu_Edit;
import uk.ac.reload.editor.metadata.xml.MD_SchemaController;
import uk.ac.reload.editor.metadata.xml.Metadata;
import uk.ac.reload.moonunit.schema.SchemaException;

/**
 * The Metadata Editor Dialog used in the CP Editor
 * It edits MD from a node of a CP
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_EditorDialog.java,v 1.1 1998/03/26 15:32:14 ynsingh Exp $
 */
public class MD_EditorDialog
extends JDialog
{

    /**
     * The Metadata Editor Panel
     */
    private MD_EditorPanel _mdEditorPanel;

    /**
     * The Content Package to which this MD Element belongs
     */
    private ContentPackage _contentPackage;

    /**
     * The parent CP "metadata" element containing the lom
     */
    private Element _mdElement;

    /**
     * An existing MD Lom Node
     */
    private Element _existingLomElement;

    /**
     * Constructor
     * @param contentPackage The Content Package that is the parent Document
     * @param mdElement The "metadata" Element in the Content Package
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    public MD_EditorDialog(ContentPackage contentPackage, Element mdElement) throws JDOMException, IOException, SchemaException {
        super(EditorFrame.getInstance(),
                Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.0") //$NON-NLS-1$
                + " - " + contentPackage.getProjectName(), //$NON-NLS-1$
                true); 

        _contentPackage = contentPackage;
        _mdElement = mdElement;

        // Trap window closing event for our Window listener
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a listener to Close our window down on exit
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        Menu_Edit editMenu = new Menu_Edit();
        menuBar.add(editMenu);

        // ToolBar
        DweezilToolBar toolBar = createToolBar(editMenu);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        // Add MD Editor
        _mdEditorPanel = new MD_EditorPanel(editMenu);
        getContentPane().add(_mdEditorPanel, BorderLayout.CENTER);

        // We'll put the Prefs Panel here, actually!
        toolBar.add(_mdEditorPanel.getPrefsPanel());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // OK Button
        JButton btnOK = new JButton(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.1")); //$NON-NLS-1$
        btnOK.addActionListener(new OKClick());
        getRootPane().setDefaultButton(btnOK);
        buttonPanel.add(btnOK);

        // Cancel Buttton
        JButton btnCancel = new JButton(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.2")); //$NON-NLS-1$
        btnCancel.addActionListener(new CancelClick());
        buttonPanel.add(btnCancel);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Import Button
        JButton btnImport = new JButton(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.3")); //$NON-NLS-1$
        btnImport.setToolTipText(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.4")); //$NON-NLS-1$
        btnImport.addActionListener(new ImportMetadata());
        leftPanel.add(btnImport);

        // Export Button
        JButton btnExport = new JButton(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.5")); //$NON-NLS-1$
        btnExport.setToolTipText(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.6")); //$NON-NLS-1$
        btnExport.addActionListener(new ExportMetadata());
        leftPanel.add(btnExport);

        // Add bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Dialog Size
        setSize(EditorFrame.getInstance().getWidth() * 3/4, EditorFrame.getInstance().getHeight() * 3/4);
        setLocationRelativeTo(EditorFrame.getInstance());

        // Create the MD JDOM Document
        createMetadataDocument(contentPackage, mdElement);
    }

    /**
     * Create a Metadata Document for Editing
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    protected void createMetadataDocument(ContentPackage cp, Element mdElement) throws JDOMException, IOException, SchemaException {
        // Firstly, we have to find the "lom" element with a MD Namespace
        _existingLomElement = cp.getMetadataLomElement(mdElement);
        
        // Yes, we got the "lom" Element - so edit a clone of the "lom" element
        if(_existingLomElement != null) {
            // Create a new JDOM Document cloned from the existing "lom" element
            Document doc = new Document();
            doc.setRootElement((Element)_existingLomElement.clone());
            
            Metadata md = new Metadata(false, doc);
            _mdEditorPanel.setDocument(md);
        }

        // No we didn't find a "lom" element, so create a new blank MD document based on the
        // Parent CP's MD Namespace (if there is one)
        else {
            // Do we have one?
            MD_SchemaController mdController =
                ((CP_SchemaController)cp.getSchemaController()).getMD_SchemaController();
            
        	// No root Namespace, so use default Controller
        	if(mdController == null) {
        	    mdController = ((CP_SchemaController)cp.getSchemaController()).getDefaultMD_SchemaController();
        	}

            // Edit a new document
            Metadata md = new Metadata(false, mdController);
            _mdEditorPanel.setDocument(md);
        }

        // Tell Undo Manager we got the Focus - this wakes it up
        _mdEditorPanel.getUndoManager().setFocusGained();
    }
    
    /**
     * Import a standalone MD File into a CP LOM MD
     * @throws JDOMException
     * @throws IOException
     * @throws SchemaException
     */
    protected void importMetadata() throws JDOMException, SchemaException, IOException {
        // Get the chosen File
        DweezilFileFilter filter = new DweezilFileFilter(new String[] {"xml"}, "xml files"); //$NON-NLS-1$ //$NON-NLS-2$
        File file = DweezilFileChooser.askFileNameOpen(this, Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.7"), filter); //$NON-NLS-1$

        if(file != null) {
            // Let's see what it is
            // It has to be a Metadata Document we support
            if(!EditorHandler.MD_EDITORHANDLER.canEditFile(file)) {
                throw new IOException(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.8")); //$NON-NLS-1$
            }
            
        	// Get a new Metadata instance
        	Metadata md = new Metadata(file);
            
        	// Create embedded version
            md = md.createEmbeddedMetadata();
            
            md.setDirty(true);
            _mdEditorPanel.setDocument(md);

            // Tell Undo Manager we got the Focus
            _mdEditorPanel.getUndoManager().setFocusGained();
        }
    }


    /**
     * Export a standalone MD File
     */
    protected void exportMetadata() {
        try {
            // Ask for the File Name
            DweezilFileFilter filter = new DweezilFileFilter(new String[] {"xml"}, "xml files"); //$NON-NLS-1$ //$NON-NLS-2$
            File file = DweezilFileChooser.askFileNameSave(this, Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.9"), filter, "xml"); //$NON-NLS-1$ //$NON-NLS-2$
            if(file != null) {
                Metadata md = _mdEditorPanel.getMetadata().createStandaloneMetadata();
                md.saveAsDocument(file);
            }
        }
        catch(IOException ex) {
            ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.10"), //$NON-NLS-1$
                    Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.11"), //$NON-NLS-1$
                    ex);
        }
    }

    /**
     * Show the Dialog
     */
    public void show() {
        // Do this later because a modal dialog will block
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                _mdEditorPanel.initView();
            }
        });

        super.show();
    }


    /**
     * User pressed OK
     */
    protected void finish() {
        // Get the Metadata from the Editor
        Metadata md = _mdEditorPanel.getMetadata();

        // If user edited the MD
        if(md.isDirty()) {
            // If we had an old node, remove it
            if(_existingLomElement != null) {
            	_mdElement.removeContent(_existingLomElement);
            }

            // Created embedded version (add prefixes)
            Metadata md2 = md.createEmbeddedMetadata();
            
            // Get root element from MD Document
            Element newRoot = md2.getRootElement();
            newRoot.detach();

            // Attach it
            _mdElement.addContent(newRoot);

            // Tell listeners
            _contentPackage.changedElement(this, _mdElement);
        }

        // Finished
        dispose();
    }


    /**
     * Dispose and Clean up
     */
    public void dispose() {
        cleanup();
        super.dispose();
    }

    /**
     * Clean up
     */
    public void cleanup() {
        _mdEditorPanel.cleanup();
    }

    /**
     * Create the Toolbar.
     * @return The Toolbar
     */
    private DweezilToolBar createToolBar(Menu_Edit editMenu) {
        DweezilToolBar tBar = new DweezilToolBar();

        // Undo
        tBar.add(editMenu.actionUndo);

        // Redo
        tBar.add(editMenu.actionRedo);

        // Add a separator
        tBar.addSeparator();

        // Cut
        tBar.add(editMenu.actionCut);

        // Copy
        tBar.add(editMenu.actionCopy);

        // Paste
        tBar.add(editMenu.actionPaste);

        // Delete
        tBar.add(editMenu.actionDelete);

        // Add a separator
        tBar.addSeparator();

        // Move Up
        tBar.add(editMenu.actionMoveUp);

        // Move Down
        tBar.add(editMenu.actionMoveDown);

        // Add a separator
        tBar.addSeparator();

        return tBar;
    }

    /**
     * Export a Metadata file
     */
    private class ExportMetadata extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            exportMetadata();
        }
    }

    /**
     * Import a Metadata file
     */
    private class ImportMetadata extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            try {
                importMetadata();
            }
            catch(Exception ex) {
                ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.10"), //$NON-NLS-1$
                        Messages.getString("uk.ac.reload.editor.metadata.MD_EditorDialog.12"), //$NON-NLS-1$
                        ex);
            }
        }
    }

    /**
     * OK handler
     */
    private class OKClick extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            finish();
        }
    }

    /**
     * Cancel handler
     */
    private class CancelClick extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

}