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

package uk.ac.reload.editor.learningdesign.editor.general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jdom.Element;

import uk.ac.reload.dweezil.gui.ComponentHiderLabelPanel;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.IdentifierLabelTextField;
import uk.ac.reload.editor.gui.NotesPanel;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.LD_EditorHandler;
import uk.ac.reload.editor.learningdesign.datamodel.ILD_DataModelHandler;
import uk.ac.reload.editor.learningdesign.datamodel.LD_DataModel;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.editor.learningdesign.xml.LearningDesign;
import uk.ac.reload.jdom.XMLPath;
import uk.ac.reload.moonunit.learningdesign.LD_Core;

/**
 * A Panel to edit the Overview of the Learning Design.<br>
 * <br>
 * This panel contains:<br>
 * <br>
 * Root Attributes:<br>
 * <br>
 * learning-design-identifier<br>
 * learning-design-level<br>
 * learning-design-sequence-used<br>
 * learning-design-uri<br>
 * learning-design-version<br>
 * <br>
 * Elements:<br>
 * <br>
 * learning-design/title<br>
 * <br>
 * And a button to launch the embedded Metadata Editor<br>
 *
 * @author Phillip Beauvoir
 * @version $Id: OverViewPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class OverViewPanel
extends JPanel
implements ILD_DataModelHandler
{
	static String TITLE = "Overview";
    static String DESCRIPTION = "This section describes the general features of the Learning Design.";
    
	static final int FIELDY_OFFSET = 5;
	static final int FIELDX_OFFSET = 100;
	
	/**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;

    /**
     * The LD_DataModel
     */
    private LD_DataModel _ldDataModel;
    
	/**
	 * The hider for the panel
	 */
    private ComponentHiderLabelPanel _hiderPanel;
    
    /**
     * Identifier TextField
     */
    private IdentifierLabelTextField _tfIdentifier;
    
    /**
     * Title Textfield
     */
    private DataElementTextField _tfTitle;
    
    /**
     * URI Textfield
     */
    private DataElementTextField _tfURI;
    
    /**
     * Version Textfield
     */
    private DataElementTextField _tfVersion;
    
    /**
     * Author's Notes
     */
    private NotesPanel _notesPanel;
    
    /**
     * Metadata
     */
    private MetadataPanel _metadataPanel;
    

    /**
	 * Constructor
     */
	public OverViewPanel() {
		setOpaque(false);
		setLayout(new BorderLayout(10, 10));
		
		// Main SubPanel
		JPanel subPanel = new JPanel(new BorderLayout());
		subPanel.setBackground(new Color(240, 240, 254));
		subPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray),
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		add(subPanel, BorderLayout.CENTER);
		
		// ComponentHider Panel to show/hide the sub-panel
		_hiderPanel = new ComponentHiderLabelPanel("<html>" + TITLE, "<html>" + DESCRIPTION);
		_hiderPanel.getComponentHiderButton().setComponent(subPanel);
		_hiderPanel.getComponentHiderButton().showComponent(false);
		add(_hiderPanel, BorderLayout.NORTH);
		
		// Centre Panel
		JPanel centrePanel = new JPanel(new BorderLayout());
		centrePanel.setOpaque(false);
		subPanel.add(centrePanel, BorderLayout.CENTER);
		
		// Field Panel
		SGLayout fieldPanelLayout = new SGLayout(SHOW_IDENTIFIER ? 4 : 3, 1, 0, 5);
		JPanel fieldPanel = new JPanel(fieldPanelLayout);
		fieldPanel.setOpaque(false);
		centrePanel.add(fieldPanel, BorderLayout.CENTER);

		// Identifier Label & Field
		if(SHOW_IDENTIFIER) {
			_tfIdentifier = new IdentifierLabelTextField();
			LabelComponentPanel panelIdentifier = new LabelComponentPanel("Identifier:", _tfIdentifier, 0.3, 0);
			fieldPanel.add(panelIdentifier);
		}

		// Title Label & Field
		_tfTitle = new DataElementTextField();
		LabelComponentPanel panelTitle = new LabelComponentPanel("Title:", _tfTitle, 0.3, 0);
		fieldPanel.add(panelTitle);

		// URI Label & Field
		_tfURI = new DataElementTextField();
		LabelComponentPanel panelURI = new LabelComponentPanel("URI:", _tfURI, 0.3, 0);
		fieldPanel.add(panelURI);
		
		// Version Label & Field
		_tfVersion = new DataElementTextField();
		LabelComponentPanel panelVersion = new LabelComponentPanel("Version:", _tfVersion, 0.3, 0);
		fieldPanel.add(panelVersion);
		
/*
		// Level Label & ComboBox
		// (Not used at the moment)
		LD_ComboBox comboLevel = new LD_ComboBox(new String[] {"Level A", "Level B", "Level C"});
		LabelComponentPanel panelLevel = new LabelComponentPanel("Level:", comboLevel,
				0.3);
		
		// Sequence Used Checkbox
		// (Not used at the moment)		
		LD_CheckBox cbSequence = new LD_CheckBox("Sequence Used");
*/		
		
		// Metadata
		_metadataPanel = new MetadataPanel();
		_metadataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		centrePanel.add(_metadataPanel, BorderLayout.SOUTH);
		
		// Author's Notes
		_notesPanel = new NotesPanel("Author's Notes", "Author's local notes.");
		_notesPanel.setPreferredSize(new Dimension(0, 200));
		_notesPanel.showTextPane(false);
		_notesPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		subPanel.add(_notesPanel, BorderLayout.SOUTH);
	}

	/**
	 * Set the LD_DataModel
	 * @param ldDataModel The LD_DataModel
	 */
	public void setDataModel(LD_DataModel ldDataModel) {
	    _ldDataModel = ldDataModel;
	    
	    // Get the LD Document from the LDCP Document
	    LearningDesign ld = ldDataModel.getLearningDesign();
	    
	    // We're working on the LD root element
	    Element rootLDElement = ld.getLDElement();
	    
	    // Wrap the Root Element
	    DataElement ldRootElement = new DataElement(ldDataModel, rootLDElement);
	    
	    // Identifier attribute
	    if(SHOW_IDENTIFIER) {
	        _tfIdentifier.setElement(ldRootElement);
	    }
	    
	    // Title element
	    DataElement titleElement = new DataElement(ldDataModel, rootLDElement, new XMLPath("title"));
	    _tfTitle.setElement(titleElement);
	    
	    // URI attribute
	    _tfURI.setAttribute(ldRootElement, LD_Core.URI, null);
	    
	    // VERSION attribute
	    _tfVersion.setAttribute(ldRootElement, LD_Core.VERSION, null);
	    
	    // Metadata
	    _metadataPanel.setMetadataType(ldDataModel.getMetadataType());
	    
        // AUTHOR NOTES
	    try {
            // Add Author Notes taken from file
            _notesPanel.loadFile(new File(ldDataModel.getLearningDesign().getProjectFolder(), LD_EditorHandler.AUTHOR_NOTES_FILE));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
	}

	/** 
	 * Clean up
	 */
	public void cleanup() {
	    if(_notesPanel != null) {
	        _notesPanel.cleanup();
	    }
	    if(_metadataPanel != null) {
	        _metadataPanel.cleanup();
	    }
	    _ldDataModel = null;
	}

	/**
     * Save any additional information
     * @return True if all OK, false if not
     */
    public boolean doSave() {
        return _notesPanel.saveDocument();
    }
}
