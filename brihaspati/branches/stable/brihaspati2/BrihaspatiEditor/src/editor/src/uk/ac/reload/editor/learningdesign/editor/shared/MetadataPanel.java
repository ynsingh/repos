/**
 *  RELOAD TOOLS
 *
 *  Copyright (c) 2004 Oleg Liber, Bill Olivier, Phillip Beauvoir
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

package uk.ac.reload.editor.learningdesign.editor.shared;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.ErrorDialogBox;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.dweezil.menu.MenuAction;
import uk.ac.reload.dweezil.util.DweezilUIManager;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.Messages;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.datamodel.MetadataType;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.editor.metadata.editor.MD_EditorDialog;
import uk.ac.reload.editor.properties.EditorProperties;

/**
 * A Panel to edit the Metadata elements.<br>
 * <br>
 *
 * @author Phillip Beauvoir
 * @version $Id: MetadataPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MetadataPanel
extends JPanel
implements IIcons
{
	
    /**
     * The Metadata Component
     */
    private MetadataType _mdType;
    
    /**
	 * Schema field
	 */
	private DataElementTextField _tfSchema;

    /**
	 * Schema Version field
	 */
	private DataElementTextField _tfSchemaVersion;
	
	/**
	 * Metadata Menu Action
	 */
	private MenuAction _mdAction;
	
	/**
	 * Hider Panel
	 */
	private ComponentHiderLabelPanel _hiderPanel;
	
	
	/**
	 * Constructor
	 */
	public MetadataPanel() {
	    setupView();
	}
	
	/**
	 * Set the MetadataType for the "metadata" node
	 * @param mdType
	 */
	public void setMetadataType(MetadataType mdType) {
	    _mdType = mdType;
	    
	    // Schema
	    _tfSchema.setElement(mdType.getSchemaDataElement());

	    // Schema Version
	    _tfSchemaVersion.setElement(mdType.getSchemaVersionDataElement());
	    
	    // Panel
	    _hiderPanel.getTitleLabel().setText(mdType.getTitle());
	    _hiderPanel.getDescriptionLabel().setText(mdType.getDescription());
	}
	
	/**
	 * Set up the view
	 */
	protected void setupView() {
		setOpaque(false);
		setLayout(new BorderLayout(10, 10));
		
		// Sub-panel
		JPanel subPanel = new JPanel(new BorderLayout());
		subPanel.setBackground(new Color(250, 254, 250));
		subPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray),
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add(subPanel, BorderLayout.CENTER);

		// ComponentHider Panel to show/hide the sub-panel
		_hiderPanel = new ComponentHiderLabelPanel("", "");
		_hiderPanel.getComponentHiderButton().setComponent(subPanel);
		_hiderPanel.getComponentHiderButton().showComponent(false);
		add(_hiderPanel, BorderLayout.NORTH);

		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(1, 2, 5, 5);
		JPanel fieldPanel = new JPanel(fieldPanelLayout);
		fieldPanel.setOpaque(false);
		subPanel.add(fieldPanel, BorderLayout.CENTER);
		
		// Schema Field
		_tfSchema = new DataElementTextField();
		LabelComponentPanel panelSchema = new LabelComponentPanel("Schema:", _tfSchema, 0.3, 0.0);
		fieldPanel.add(panelSchema);

		// Schema Version Field
		_tfSchemaVersion = new DataElementTextField();
		LabelComponentPanel panelSchemaVersion = new LabelComponentPanel("Version:", _tfSchemaVersion, 0.3, 0.0);
		fieldPanel.add(panelSchemaVersion);
		
		// Metadata button
		_mdAction = new MetadataAction();
		JPanel mdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
		mdPanel.setOpaque(false);
		subPanel.add(mdPanel, BorderLayout.SOUTH);
		mdPanel.add(_mdAction.getButton());
	}
	
	/**
	 * Edit the Metadata in the Metadata dialog Editor
	 */
	protected void editMetadata() {
	    if(_mdType == null) {
	        return;
	    }
	    
	    MD_EditorDialog mdDialog = null;
	    
	    try {
	        _mdAction.setEnabled(false);  // block multiple key presses
	        
	        setCursor(DweezilUIManager.WAIT_CURSOR);
	        
	        LearningDesign ld = ((LD_DataModel)_mdType.getLD_DataModel()).getLearningDesign();
	        DataElement mdDataElement = _mdType.getMetadataDataElement();
	        Element mdElement = mdDataElement.createElement();
	        
	        mdDialog = new MD_EditorDialog(ld, mdElement);
	        mdDialog.show();
	        
	        _mdAction.setEnabled(true);
	    }
	    catch(Exception ex) {
	        if(EditorProperties.getString("DEBUG").equals("true")) { //$NON-NLS-1$ //$NON-NLS-2$
	            ex.printStackTrace();
	        }
	        if(mdDialog != null) {
	            mdDialog.dispose();
	        }
	        ErrorDialogBox.showWarning(Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.0"), //$NON-NLS-1$
	                Messages.getString("uk.ac.reload.editor.contentpackaging.manifestview.ManifestTree.1"), //$NON-NLS-1$
	                ex);
	    }
	    finally {
	        setCursor(DweezilUIManager.DEFAULT_CURSOR);
	    }
	}

	
	/** 
	 * Clean up
	 */
	public void cleanup() {
	    _mdType = null;
	}
	
	
	class MetadataAction extends MenuAction {
	    MetadataAction() {
	        super("Edit Metadata");
	        setButtonText("Edit");
	    }
	    
	    public void actionPerformed(ActionEvent e) {
	        editMetadata();
	    }
	}
}
