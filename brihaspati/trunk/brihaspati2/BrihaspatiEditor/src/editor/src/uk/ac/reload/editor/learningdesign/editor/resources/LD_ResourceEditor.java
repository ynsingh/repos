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

package uk.ac.reload.editor.learningdesign.editor.resources;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdom.Namespace;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.dweezil.gui.LabelComponentPanel;
import uk.ac.reload.dweezil.gui.layout.SGLayout;
import uk.ac.reload.editor.IIcons;
import uk.ac.reload.editor.datamodel.DataElement;
import uk.ac.reload.editor.gui.IdentifierLabelTextField;
import uk.ac.reload.editor.gui.widgets.DataElementTextField;
import uk.ac.reload.editor.learningdesign.datamodel.LD_Component;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Dependency;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_File;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resource;
import uk.ac.reload.editor.learningdesign.datamodel.resources.LD_Resources;
import uk.ac.reload.editor.learningdesign.editor.shared.LD_ResourceSelector;
import uk.ac.reload.editor.learningdesign.editor.shared.MetadataPanel;
import uk.ac.reload.moonunit.contentpackaging.CP_Core;

/**
 * Resource Editor sub-Panel
 *
 * @author Phillip Beauvoir
 * @version $Id: LD_ResourceEditor.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class LD_ResourceEditor
extends JPanel
implements IIcons
{
    static final String DESCRIPTION = "Add Resources that you wish to include and reference in the Learning Design. " +
    		"You can add Resources directly for on-line web pages, or drag and drop files from the File Viewer.";
    
	/**
	 * Whether or not to show the Identifier field
	 */
	static final boolean SHOW_IDENTIFIER = false;

    /**
     * The Resources Panel
     */
    private ResourcesPanel _resourcesPanel;

    /**
     * The Resource Panel
     */
    private ResourcePanel _resourcePanel;

    /**
     * The File Panel
     */
    private FilePanel _filePanel;

    /**
     * The Dependency Panel
     */
    private DependencyPanel _dependencyPanel;
    
    /**
	 * Default Constructor
	 */
	public LD_ResourceEditor() {
	    setLayout(new CardLayout());
	    setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}
	
	/**
	 * Set the component to edit
	 * @param ldComponent The Resource type component to edit
	 */
	public void setComponent(LD_Component ldComponent) {
	    if(ldComponent instanceof LD_Resources) {
	        getResourcesPanel().setComponent(ldComponent);
	        ((CardLayout)getLayout()).show(this, "#RESOURCES");
	    }
	    else if(ldComponent instanceof LD_Resource) {
	        getResourcePanel().setComponent(ldComponent);
	        ((CardLayout)getLayout()).show(this, "#RESOURCE");
	    }
	    else if(ldComponent instanceof LD_File) {
	        getFilePanel().setComponent(ldComponent);
	        ((CardLayout)getLayout()).show(this, "#FILE");
	    }
	    else if(ldComponent instanceof LD_Dependency) {
	        getDependencyPanel().setComponent(ldComponent);
	        ((CardLayout)getLayout()).show(this, "#DEPENDENCY");
	    }
	}
	
	/**
	 * Clean up
	 */
	public void cleanup() {
	    
	}
	
	/**
	 * @return the Resources Panel
	 */
	private ResourcesPanel getResourcesPanel() {
	    if(_resourcesPanel == null) {
	        _resourcesPanel = new ResourcesPanel();
	        add(_resourcesPanel, "#RESOURCES");
	    }
	    return _resourcesPanel;
	}
	
	/**
	 * @return the Resource Panel
	 */
	private ResourcePanel getResourcePanel() {
	    if(_resourcePanel == null) {
	        _resourcePanel = new ResourcePanel();
	        add(_resourcePanel, "#RESOURCE");
	    }
	    return _resourcePanel;
	}

	/**
	 * @return the File Panel
	 */
	private FilePanel getFilePanel() {
	    if(_filePanel == null) {
	        _filePanel = new FilePanel();
	        add(_filePanel, "#FILE");
	    }
	    return _filePanel;
	}
	
	/**
	 * @return the Dependency Panel
	 */
	private DependencyPanel getDependencyPanel() {
	    if(_dependencyPanel == null) {
	        _dependencyPanel = new DependencyPanel();
	        add(_dependencyPanel, "#DEPENDENCY");
	    }
	    return _dependencyPanel;
	}

	// ============================= RESOURCES EDITOR ==========================================

	/**
	 * Panel for Resources Element
	 */
	class ResourcesPanel extends GradientPanel {
		/**
		 * Base
		 */
		private DataElementTextField _tfBase;
	    
	    /**
	     * Constructor
	     */
	    ResourcesPanel() {
	        setLayout(new BorderLayout());
	        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        JLabel label = new JLabel("<html>" + DESCRIPTION);
	        add(label, BorderLayout.NORTH);
	        
			// Base
			_tfBase = new DataElementTextField();
			LabelComponentPanel panelBase = new LabelComponentPanel("Base path:", _tfBase, 0.3, 0);
			add(panelBase, BorderLayout.SOUTH);
	    }

	    /**
		 * Set the component to edit
		 * @param ldComponent The Resource type component to edit
		 */
		public void setComponent(LD_Component ldComponent) {
		    // Wrap the Resources Element
		    DataElement resourcesElement = ldComponent.getDataElement();
		    
		    // Base
		    _tfBase.setAttribute(resourcesElement, CP_Core.BASE, Namespace.XML_NAMESPACE);
		}
	}
	
	// =============================== RESOURCE EDITOR ========================================
	
	/**
	 * Panel for Resource Element
	 */
	class ResourcePanel extends GradientPanel {
		
	    /**
		 * Identifier
		 */
		private IdentifierLabelTextField _tfIdentifier;
	    
		/**
		 * Type
		 */
		private DataElementTextField _tfType;
		
		/**
		 * Base
		 */
		private DataElementTextField _tfBase;
		
		/**
		 * HREF
		 */
		private DataElementTextField _tfHREF;
		
	    /**
	     * Metadata
	     */
	    private MetadataPanel _metadataPanel;

	    /**
	     * Constructor
	     */
	    ResourcePanel() {
	        setLayout(new BorderLayout());
	        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        
			// Field Panel
			SGLayout fieldPanelLayout = new SGLayout(SHOW_IDENTIFIER ? 4 : 3, 1, 0, 5);
			JPanel fieldPanel = new JPanel(fieldPanelLayout);
			fieldPanel.setOpaque(false);
			add(fieldPanel, BorderLayout.NORTH);
			
			// Identifier Label & Field
			if(SHOW_IDENTIFIER) {
				_tfIdentifier = new IdentifierLabelTextField();
				LabelComponentPanel panelIdentifier = new LabelComponentPanel("Identifier:", _tfIdentifier, 0.3, 0);
				fieldPanel.add(panelIdentifier);
			}
			
			// HREF
			_tfHREF = new DataElementTextField();
			LabelComponentPanel panelHREF = new LabelComponentPanel("Location:", _tfHREF, 0.3, 0);
			fieldPanel.add(panelHREF);

			// Type
			_tfType = new DataElementTextField();
			LabelComponentPanel panelType = new LabelComponentPanel("Type:", _tfType, 0.3, 0);
			fieldPanel.add(panelType);

			// Base
			_tfBase = new DataElementTextField();
			LabelComponentPanel panelBase = new LabelComponentPanel("Base path:", _tfBase, 0.3, 0);
			fieldPanel.add(panelBase);

			// Metadata
			_metadataPanel = new MetadataPanel();
			_metadataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			add(_metadataPanel, BorderLayout.SOUTH);
	    }
	    
		/**
		 * Set the component to edit
		 * @param ldComponent The Resource type component to edit
		 */
		public void setComponent(LD_Component ldComponent) {
		    // Wrap the Resource Element
		    DataElement resourceElement = ldComponent.getDataElement();

		    // Identifier
		    if(SHOW_IDENTIFIER) {
		        _tfIdentifier.setElement(resourceElement);
		    }
		
		    // Type
		    _tfType.setAttribute(resourceElement, CP_Core.TYPE, null);
		    
		    // Base
		    _tfBase.setAttribute(resourceElement, CP_Core.BASE, Namespace.XML_NAMESPACE);

		    // HREF
		    _tfHREF.setAttribute(resourceElement, CP_Core.HREF, null);
		    
		    // Metadata
		    _metadataPanel.setMetadataType(((LD_Resource)ldComponent).getMetadataType());
		}
	}
	
	// ============================== FILE EDITOR ================================================

	/**
	 * Panel for File Element
	 */
	class FilePanel extends GradientPanel {
		
	    /**
		 * HREF
		 */
		private DataElementTextField _tfHREF;
	    
	    /**
	     * Metadata
	     */
	    private MetadataPanel _metadataPanel;

	    /**
	     * Constructor
	     */
		FilePanel() {
	        setLayout(new BorderLayout());
	        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        
			// HREF
	        _tfHREF = new DataElementTextField();
			LabelComponentPanel panelHREF = new LabelComponentPanel("Location:", _tfHREF, 0.3, 0);
			add(panelHREF, BorderLayout.NORTH);
			
			// Metadata
			_metadataPanel = new MetadataPanel();
			_metadataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			add(_metadataPanel, BorderLayout.SOUTH);
	    }

	    /**
		 * Set the component to edit
		 * @param ldComponent The File type component to edit
		 */
		public void setComponent(LD_Component ldComponent) {
		    // Wrap the File Element
		    DataElement fileElement = ldComponent.getDataElement();
		    
		    // HREF
		    _tfHREF.setAttribute(fileElement, CP_Core.HREF, null);
		    
		    // Metadata
		    _metadataPanel.setMetadataType(((LD_File)ldComponent).getMetadataType());
		}
	}

	// ============================== DEPENDENCY EDITOR ==========================================
	
	/**
	 * Panel for Dependency Element
	 */
	class DependencyPanel extends GradientPanel {
		
	    /**
		 * IDREF
		 */
		private LD_ResourceSelector _selector;
	    
	    /**
	     * Constructor
	     */
		DependencyPanel() {
	        setLayout(new BorderLayout());
	        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        
			// HREF
	        _selector = new LD_ResourceSelector();
			LabelComponentPanel panelIDREF = new LabelComponentPanel("Dependency:", _selector, 0.3, 0);
			add(panelIDREF, BorderLayout.NORTH);
	    }

	    /**
		 * Set the component to edit
		 * @param ldComponent The Dependency type component to edit
		 */
		public void setComponent(LD_Component ldComponent) {
		    // Wrap the Dependency Element
		    DataElement dependencyElement = ldComponent.getDataElement();
		    
		    // HREF
		    _selector.setElement(dependencyElement);
		}
	}
	
}
