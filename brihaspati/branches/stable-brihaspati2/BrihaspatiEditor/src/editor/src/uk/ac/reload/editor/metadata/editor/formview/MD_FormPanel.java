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

package uk.ac.reload.editor.metadata.editor.formview;

import java.awt.*;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.border.EmptyBorder;

import uk.ac.reload.dweezil.gui.GradientPanel;
import uk.ac.reload.editor.metadata.xml.Metadata;


/**
 * A Metadata Form Panel for the Metadata UI
 *
 * @author Phillip Beauvoir
 * @version $Id: MD_FormPanel.java,v 1.1 1998/03/26 15:40:32 ynsingh Exp $
 */
public class MD_FormPanel
extends GradientPanel
implements Scrollable
{
    /**
     * The Form Model
     */
    private MD_FormModel _mdFormModel;

    /**
     * Keep track of fields so we can release listeners and clean up
     */
    private Vector _fields = new Vector();

    /**
     * Constructor
     */
    public MD_FormPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // New form model
        _mdFormModel = new MD_FormModel();
        
        setBackground(new Color(250, 250, 250));
    }

    /**
     * Set the view up to display the Metadata Document
     */
    public void setDocument(Metadata metadata) {
        // Clean up
        cleanup();
        removeAll();

        // Update Model
        _mdFormModel.setDocument(metadata);

        // Get Root
        final MD_ProfileElement rootElement = _mdFormModel.getRootElement();
        
        // If we put this on a thread then the form view will stay at the top
        // and not scroll to the bottom
        Thread thread = new Thread() {
            public void run() {
                // Add Root Panel
                add(new GroupPanel(rootElement));
                revalidate();
            }
        };
        thread.start();
    }
    
    /**
     * Set it all out again
     */
    public void refresh() {
        if(_mdFormModel.getDocument() != null) {
            setDocument(_mdFormModel.getDocument());
        }
    }

    /**
     * Clean up
     */
    public void cleanup() {
        for(int i = 0; i < _fields.size(); i++) {
            MD_Field mdField = (MD_Field)_fields.get(i);
            mdField.cleanup();
        }
        _fields.clear();
    }
    
	/* (non-Javadoc)
	 * @see javax.swing.Scrollable#getScrollableTracksViewportHeight()
	 */
	public boolean getScrollableTracksViewportHeight() {
		if (getParent() instanceof JViewport) {
		    return (((JViewport)getParent()).getHeight() > getPreferredSize().height);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Scrollable#getScrollableTracksViewportWidth()
	 */
	public boolean getScrollableTracksViewportWidth() {
		if (getParent() instanceof JViewport) {
		    return (((JViewport)getParent()).getWidth() > getPreferredSize().width);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.Scrollable#getPreferredScrollableViewportSize()
	 */
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	/* (non-Javadoc)
	 * @see javax.swing.Scrollable#getScrollableBlockIncrement(java.awt.Rectangle, int, int)
	 */
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 20; 
	}

	/* (non-Javadoc)
	 * @see javax.swing.Scrollable#getScrollableUnitIncrement(java.awt.Rectangle, int, int)
	 */
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 20; 
	}


    /**
     * A Panel that uses GridBagLayout to set out its components
     */
    class GroupPanel extends JPanel {
        /**
         * The Constraints
         */
        protected GridBagConstraints constraints;

        /**
         * Left Indent factor
         */
        int INDENT = 15;

        public GroupPanel(MD_ProfileElement profileElement) {
        	setOpaque(false);
            // Set up the constraints
            setLayout(new GridBagLayout());
            constraints = new GridBagConstraints();
            constraints.insets = new Insets(0, INDENT, 5, 0);
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.weighty = 0.0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.WEST;

            // Add the Group Label
            if(profileElement.getName() != null) addGroupLabel(profileElement.getName());

            // Get the Children
            MD_ProfileElement[] children = profileElement.getChildren();
            for(int i = 0; i < children.length; i++) {
                MD_ProfileElement child = children[i];
                // If it's a container type. add new sub-Panel
                if(child.isGroup()) {
                    constraints.gridx = 0;
                    constraints.gridwidth = 2;
                    constraints.weightx = 1.0;
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    add(new GroupPanel(child), constraints);
                    constraints.gridy++;
                }
                // Else add a field
                else addField(child);
            }
        }

        /**
         * Add a single field for an Element
         * @param profileElement
         */
        protected void addField(MD_ProfileElement profileElement) {
            constraints.fill = GridBagConstraints.NONE;
            // Add a Label
            JLabel l = new JLabel(profileElement.getName());
            l.setBorder(new EmptyBorder(0, INDENT, 0, 0));
            constraints.gridx = 0;
            constraints.gridwidth = 1;
            constraints.weightx = 0;
            add(l, constraints);

            // Ask the Model for the Widget to use
            MD_Field mdField = _mdFormModel.createWidget(profileElement);
            if(mdField != null) _fields.add(mdField);

            // Add it
            constraints.gridx = 1;
            if(mdField != null) add(mdField.getComponent(), constraints);
            constraints.gridy++;
        }


        /**
         * Add a JLabel to mark a Group
         * @param name The Group Name
         */
        protected void addGroupLabel(String name) {
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 2;
            constraints.gridx = 0;

            JLabel label = new JLabel(name);
            //label.setFont(label.getFont().deriveFont(12f));
            label.setOpaque(true);
            label.setBackground(Color.lightGray);
            label.setForeground(Color.black);
            label.setBorder(new EmptyBorder(0, 3, 0, 0));

            add(label, constraints);
            constraints.gridy++;
        }
    }


}